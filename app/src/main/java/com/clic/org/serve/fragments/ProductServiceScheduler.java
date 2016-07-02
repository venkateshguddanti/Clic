package com.clic.org.serve.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.clic.imageservices.model.ImageCaptureType;
import com.clic.imageservices.ui.GalleryActivityThumbs;
import com.clic.imageservices.utils.Constants;
import com.clic.imageservices.utils.ImageServices;
import com.clic.org.R;
import com.clic.org.serve.Utils.ClicUtils;
import com.clic.org.serve.Utils.JsonUtils;
import com.clic.org.serve.constants.ClicConstants;
import com.clic.org.serve.data.RequestType;
import com.clic.org.serve.data.RequestTypeResponse;
import com.clic.org.serve.data.ServiceRequest;
import com.clic.org.serve.data.ServiceType;
import com.clic.org.serve.data.UserItem;
import com.clic.org.serve.listener.ServiceListener;
import com.clic.org.serve.services.ServiceConstants;
import com.clic.org.serve.services.ServiceUtils;

import java.io.File;

/**
 * Created by Venkatesh on 11-06-2016.
 */
public class ProductServiceScheduler extends Fragment implements View.OnClickListener,DatePickerFragment.DateFromPickerListener
        {

    ImageView chooseDocument,chooseLocaton;
    ImageView documentStatus,locationStatus;
    Button btnSubmit,dateView;
    public static final int DATEPICKER_FRAGMENT=1;
    public static final int READ_REQUEST_CODE=2;

    Uri imageUri = null;
    String mCurrentPath;
    ServiceRequest mServiceRequest = new ServiceRequest();
    ServiceType mServiceType = new ServiceType();
    RequestType mRequestType = new RequestType();
            EditText edt_description;
            RequestTypeResponse requestTypeResponse = new RequestTypeResponse();
   // GoogleApiClient mGoogleApiClient;

            UserItem mUserItem;

            String type;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_product_scheduler,container,false);
        initWidgets(view);
        return view;

    }

    private void initWidgets(View view) {

       /* if(mGoogleApiClient == null)
        {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }*/

        type  = getArguments().getString(getString(R.string.activity_type));
        mServiceType = getArguments().getParcelable(getString(R.string.parcel_service_type));
        if(type.equalsIgnoreCase(getString(R.string.schedule_reqReq))) {
            requestTypeResponse = getArguments().getParcelable(getString(R.string.parcel_repiar_type));
            mRequestType = getArguments().getParcelable(getString(R.string.parcel_repiar_type_req));
            mServiceRequest.setRepaiTypeId(mRequestType.getRequestTypeID());

        }
        mUserItem = getArguments().getParcelable(getString(R.string.user_item));


        chooseDocument = (ImageView)view.findViewById(R.id.chooseDocument);
        documentStatus = (ImageView)view.findViewById(R.id.documentStatus);
        chooseLocaton = (ImageView)view.findViewById(R.id.gpsLocation);
        locationStatus = (ImageView)view.findViewById(R.id.gpsLocationStatus);
        btnSubmit = (Button)view.findViewById(R.id.btn_submit);
        edt_description = (EditText)view.findViewById(R.id.edt_des);

        chooseDocument.setOnClickListener(this);
        chooseLocaton.setOnClickListener(this);
        documentStatus.setOnClickListener(this);
        locationStatus.setOnClickListener(this);
        dateView = (Button)view.findViewById(R.id.btn_date);
        dateView.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        edt_description.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
                    if(edt_description.getText().toString().length() > 0)
                    {
                        mServiceRequest.setDescription(edt_description.getText().toString());
                    }else
                    {
                        ClicUtils.displayToast(getActivity(),"Plese Enter value....");
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.chooseDocument:
                uploadDocument();
                break;
            case R.id.documentStatus:
                break;
            case R.id.gpsLocation:
                break;
            case R.id.gpsLocationStatus:
                break;
            case R.id.btn_submit:
                mServiceRequest.setTypeOfRequest(mServiceType.getServiceTypeID());
                mServiceRequest.setCustomerItemID(mUserItem.getItemID());
                ServiceUtils.postJsonObjectRequest(getActivity(),
                        ServiceConstants.SERVICE_SCHEDULE,mServiceListener, JsonUtils.getJsonString(mServiceRequest));
                break;
            case R.id.btn_date:
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(ProductServiceScheduler.this, DATEPICKER_FRAGMENT);
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
                break;
        }
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

            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {

                if(requestCode == ImageCaptureType.CAPTURE_BY_GALLERY.getImageCaptureType() && data != null)
                {

                    if(ClicUtils.isFileSizeLimitExceed(data.getExtras().getString(Constants.Gallery.IMAGE_PATH)))
                    {
                        Toast.makeText(getActivity(), "File Size Shold be less than 1MB" , Toast.LENGTH_SHORT).show();

                    }else {
                        udateDocumentValue(mServiceRequest,data.getExtras().getString(Constants.Gallery.IMAGE_PATH));
                        Toast.makeText(getActivity(), "Invoice Uploaded Successfully" + data.getExtras().getString(Constants.Gallery.IMAGE_PATH), Toast.LENGTH_SHORT).show();
                    }



                }
                else  if (data == null && resultCode == Activity.RESULT_OK) {

                    if(ClicUtils.isFileSizeLimitExceed(mCurrentPath))
                    {
                        Toast.makeText(getActivity(), "File Size Shold be less than 1MB" , Toast.LENGTH_SHORT).show();

                    }else{
                        udateDocumentValue(mServiceRequest,mCurrentPath);

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

    private void udateDocumentValue(ServiceRequest mServiceRequest, String string)
    {

        mServiceRequest.setImageString(ClicUtils.convertBitmapToString(string));

    }
    @Override
    public void getDataFromPicker(String value) {

        dateView.setText(value);
    }

            ServiceListener mServiceListener = new ServiceListener() {
                @Override
                public void onServiceResponse(String response) {

                    ClicUtils.displayToast(getActivity(),"Request Submitted Successfully");
                }

                @Override
                public void onServiceError(String response) {

                }
            };

   /* @Override
    public void onConnected(@Nullable Bundle bundle) {

        Location mLastLocation = null;
        try {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        if (mLastLocation != null) {
            mServiceRequest.setLang(String.valueOf(mLastLocation.getLongitude()));
            mServiceRequest.setLat(String.valueOf(mLastLocation.getLatitude()));

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }*/
}
