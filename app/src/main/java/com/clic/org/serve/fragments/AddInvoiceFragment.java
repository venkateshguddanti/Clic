package com.clic.org.serve.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CallLog;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.clic.imageservices.ui.GalleryActivityThumbs;
import com.clic.org.R;
import com.clic.imageservices.model.ImageCaptureType;
import com.clic.imageservices.utils.Constants;
import com.clic.imageservices.utils.ImageServices;
import com.clic.org.serve.AppController.ClicController;
import com.clic.org.serve.Utils.ClicUtils;
import com.clic.org.serve.Utils.JsonUtils;
import com.clic.org.serve.constants.ClicConstants;
import com.clic.org.serve.data.Address;
import com.clic.org.serve.data.ItemDocs;
import com.clic.org.serve.data.UserItem;
import com.clic.org.serve.listener.ServiceListener;
import com.clic.org.serve.services.ServiceConstants;
import com.clic.org.serve.services.ServiceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by Venkatesh on 25-05-2016.
 */
public class AddInvoiceFragment extends Fragment implements DatePickerFragment.DateFromPickerListener,View.OnClickListener{

    ImageView chooseDocument,chooseWarranty,chooseInsurance;
    ImageView documentStatus,warrantyStatus,insuranceStatus;
    Button btnSubmit;
    Uri imageUri = null;
    String mCurrentPath;
    InVoicePathListener mListener;

    private Calendar calendar;
    private Button dateView;
    public static final int DATEPICKER_FRAGMENT=1;
    public static final int READ_REQUEST_CODE=2;
    UserItem mUserItem = new UserItem();
    ItemDocs itemDocInvo = new ItemDocs();
    ItemDocs itemDocWarranty = new ItemDocs();
    ItemDocs itemDocInsu = new ItemDocs();

    EditText warrantyYears;

    String DOCUMENT_TYPE;

    ArrayList<ItemDocs> itemDocuments = new ArrayList<>();

    CheckBox itemCheck;
    Button itemAddress;
    Address mAddress = new Address();

    @Override
    public void getDataFromPicker(String value) {

        dateView.setText(value);
        mUserItem.setYearop(value);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.documentStatus:
                break;
            case R.id.warrantyStatus:

                break;
            case R.id.insuranceStatus:
                break;
            case R.id.btn_submit:

                mUserItem.setSameAddress("yes");
                mUserItem.setItemDocs(itemDocuments);
                mUserItem.setAddress(mAddress);
                Log.d("debug", "json" + JsonUtils.getJsonString(mUserItem));
                getItemDocument(mUserItem.getItemDocs(),"");
                ServiceUtils.postJsonObjectRequest(getActivity(), ServiceConstants.ADD_CUSTOMER_ITEM, mServiceListener, JsonUtils.getJsonString(mUserItem));
                break;
            case R.id.btn_date:
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(AddInvoiceFragment.this, DATEPICKER_FRAGMENT);
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
                break;
            case R.id.chooseDocument:
                DOCUMENT_TYPE = ClicConstants.DOC_INVOICE;
                uploadDocument();
                break;
            case R.id.chooseInsurance:
                DOCUMENT_TYPE = ClicConstants.DOC_INSURENCE;
                uploadDocument();
                break;
            case R.id.chooseWarranty:
                DOCUMENT_TYPE = ClicConstants.DOC_WARRANTY;
                uploadDocument();
                break;
            case R.id.btn_ItemAddress:
                ClicUtils.createAddressDialog(getActivity(),R.layout.clic_address_layout,mAddress);

        }

    }


    public interface InVoicePathListener
    {
        public void getInvoicePath(String value);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_invoice,container,false);

        initWidgets(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (InVoicePathListener)context;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initWidgets(View view) {

        mUserItem = getArguments().getParcelable(getString(R.string.user_item));

        warrantyYears =(EditText)view.findViewById(R.id.edt_warranty);
        btnSubmit = (Button)view.findViewById(R.id.btn_submit);
        chooseDocument = (ImageView)view.findViewById(R.id.chooseDocument);
        chooseInsurance = (ImageView)view.findViewById(R.id.chooseInsurance);
        chooseWarranty = (ImageView)view.findViewById(R.id.chooseWarranty);
        documentStatus = (ImageView)view.findViewById(R.id.documentStatus);
        insuranceStatus = (ImageView)view.findViewById(R.id.insuranceStatus);
        warrantyStatus = (ImageView)view.findViewById(R.id.warrantyStatus);
        itemCheck =(CheckBox)view.findViewById(R.id.addressCheck);
        itemAddress = (Button)view.findViewById(R.id.btn_ItemAddress);

        itemAddress.setOnClickListener(this);
        warrantyStatus.setOnClickListener(this);
        chooseDocument.setOnClickListener(this);
        chooseInsurance.setOnClickListener(this);
        chooseWarranty.setOnClickListener(this);
        documentStatus.setOnClickListener(this);
        insuranceStatus.setOnClickListener(this);
        dateView = (Button)view.findViewById(R.id.btn_date);
        dateView.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        itemCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    mUserItem.setSameAddress("yes");
                }else
                {
                    mUserItem.setSameAddress("no");
                }
            }
        });

        warrantyYears.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
                    if(warrantyYears.getText().length() > 0)
                    {
                      mUserItem.setWarrentyMonths(warrantyYears.getText().toString());
                    }
                    else
                    {
                        ClicUtils.displayToast(getActivity(),"Please Enter Value..");
                    }
                }
                return false;
            }
        });

    }




    public void uploadDocument()
    {
        final CharSequence[] items = {"Camera", "Gallery", "Document"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Make Your Selection");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (items[which].toString().equalsIgnoreCase("Camera")) {

                    imageUri = ImageServices.getOutputMediaFileUri();
                    mCurrentPath = ImageServices.getOutputMediaFile().getPath();
                    mListener.getInvoicePath(mCurrentPath);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, 100);

                } else if (items[which].toString().equalsIgnoreCase("Gallery")) {

                    Intent i = new Intent(getActivity(), GalleryActivityThumbs.class);
                    i.putExtra(Constants.Gallery.GALLARY_TYPE, Constants.Gallery.GALLARY_TYPE_IMAGE);
                    startActivityForResult(i, ImageCaptureType.CAPTURE_BY_GALLERY.getImageCaptureType());
                    //Will return image path to activity result by resultcode ImageCaptureType.CAPTURE_BY_GALLERY
                   // ImageServices.imageCapture(getActivity(), ImageCaptureType.CAPTURE_BY_GALLERY);

                } else
                {

                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                    intent.addCategory(Intent.CATEGORY_OPENABLE);

    // Filter to show only images, using the image MIME data type.
    // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
    // To search for all documents available via installed storage providers,
    // it would be "*/*".
                    intent.setType("*/*");

                    startActivityForResult(intent, READ_REQUEST_CODE);

                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == ImageCaptureType.CAPTURE_BY_GALLERY.getImageCaptureType() && data != null)
        {

            if(ClicUtils.isFileSizeLimitExceed(data.getExtras().getString(Constants.Gallery.IMAGE_PATH)))
            {
                Toast.makeText(getActivity(), "File Size Shold be less than 1MB" , Toast.LENGTH_SHORT).show();

            }else {
                udateDocumentValue(mUserItem,data.getExtras().getString(Constants.Gallery.IMAGE_PATH));
                Toast.makeText(getActivity(), "Invoice Uploaded Successfully" + data.getExtras().getString(Constants.Gallery.IMAGE_PATH), Toast.LENGTH_SHORT).show();
            }



        }
        else  if (data == null && resultCode == Activity.RESULT_OK) {

            if(ClicUtils.isFileSizeLimitExceed(mCurrentPath))
            {
                Toast.makeText(getActivity(), "File Size Shold be less than 1MB" , Toast.LENGTH_SHORT).show();

            }else{
                udateDocumentValue(mUserItem,mCurrentPath);

                Toast.makeText(getActivity(), "Invoice Uploaded Successfully" + mCurrentPath, Toast.LENGTH_SHORT).show();
            }
        }
        else if (data != null && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
           /* Uri uri = null;
            if (data != null) {
                File myFile = new File(ClicUtils.getFilePathFromUri(getActivity(),data.getData()));

                if(ClicUtils.isFileSizeLimitExceed(myFile.getAbsolutePath()))
                {
                    Toast.makeText(getActivity(), "File Size Shold be less than 1MB" , Toast.LENGTH_SHORT).show();

                }else {
                    udateDocumentValue(mUserItem,myFile.getAbsolutePath());
                    Toast.makeText(getActivity(), "Invoice Uploaded Successfully" , Toast.LENGTH_SHORT).show();
                }

            }*/
        }
    }

    private void udateDocumentValue(UserItem mUserItem, String string) {


        if(DOCUMENT_TYPE.equalsIgnoreCase(ClicConstants.DOC_INVOICE))
        {
            itemDocInvo.setImageData(ClicUtils.convertBitmapToString(string));
            itemDocInvo.setDocType(ClicConstants.DOC_INVOICE_VALUE);
            itemDocuments.add(itemDocInvo);
        }
        else if(DOCUMENT_TYPE.equalsIgnoreCase(ClicConstants.DOC_WARRANTY))
        {
            itemDocWarranty.setDocType(ClicConstants.DOC_WARRANTY_VALUE);
            itemDocWarranty.setImageData(ClicUtils.convertBitmapToString(string));
            itemDocuments.add(itemDocWarranty);
        }
        else if(DOCUMENT_TYPE.equalsIgnoreCase(ClicConstants.DOC_INSURENCE))
        {
            itemDocInsu.setDocType(ClicConstants.DOC_INSURENCE_VALUE);
            itemDocInsu.setImageData(ClicUtils.convertBitmapToString(string));
            itemDocuments.add(itemDocInsu);
        }

    }

    public ItemDocs getItemDocument(ArrayList<ItemDocs> listItmemDocs,String documentType)
    {
        for(ItemDocs document : listItmemDocs)
        {

            if(document.getDocType().equalsIgnoreCase(documentType))
            {
                return document;
            }
        }
        return null;
    }

    ServiceListener mServiceListener = new ServiceListener() {
        @Override
        public void onServiceResponse(String response) {

            getActivity().finish();
            ClicUtils.createPreferences(getActivity(), "true", R.string.is_product_exit);
        }

        @Override
        public void onServiceError(String response) {

            ClicUtils.displayToast(getActivity(),"Connection Error....!");

        }
    };
}
