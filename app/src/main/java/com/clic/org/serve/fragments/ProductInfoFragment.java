package com.clic.org.serve.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clic.org.R;
import com.clic.org.serve.Utils.ClicUtils;
import com.clic.org.serve.constants.ClicConstants;
import com.clic.org.serve.data.ItemDocs;
import com.clic.org.serve.data.UserItem;

import java.util.ArrayList;
import java.util.List;


public class ProductInfoFragment extends Fragment implements View.OnClickListener{

    UserItem mUserItem = new UserItem();

    ImageView modelIcon,warrantyStatus,insuranceStatus,invoiceStatus;
    TextView txtModelName,txtModelNumber,txtYearOfPurchase,txtwarrantyStatus,txtinsuranceStatus,txtInvoiceStatus;
    View layoutViewInvoice,layoutUserManual;
    List<ItemDocs> listItemDocs = new ArrayList<>();

    public ProductInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_info, container, false);
        initWidgets(view);
        return view;
    }

    private void initWidgets(View view) {

        mUserItem = getArguments().getParcelable(getString(R.string.user_item));

        listItemDocs = mUserItem.getItemDocs();

        modelIcon = (ImageView)view.findViewById(R.id.modelIcon);
        txtModelName = (TextView)view.findViewById(R.id.modelName);
        txtModelNumber = (TextView)view.findViewById(R.id.modelNumber);
        txtYearOfPurchase = (TextView)view.findViewById(R.id.purchaseYear);
        warrantyStatus = (ImageView)view.findViewById(R.id.warrantyStatus);
        txtwarrantyStatus = (TextView)view.findViewById(R.id.txt_statusWarranty);
        insuranceStatus = (ImageView)view.findViewById(R.id.insuranceStatus);
        txtinsuranceStatus = (TextView)view.findViewById(R.id.txt_statusInsurance);
        invoiceStatus = (ImageView)view.findViewById(R.id.invoiceStatus);
        txtInvoiceStatus = (TextView)view.findViewById(R.id.txt_statusInvoce);



        modelIcon.setOnClickListener(this);

        layoutViewInvoice = (RelativeLayout)view.findViewById(R.id.viewInvoice);
        layoutUserManual = (RelativeLayout)view.findViewById(R.id.userMannual);
        layoutUserManual.setOnClickListener(this);
        layoutViewInvoice.setOnClickListener(this);

        txtModelName.setText(mUserItem.getModelNumber());
        txtModelNumber.setText(mUserItem.getModelNumber());
        txtYearOfPurchase.setText(mUserItem.getYearop());

        if(mUserItem.getItemDocs().size() > 0) {
            setItemDocsStatus(mUserItem.getItemDocs());
        }


    }

    private void setItemDocsStatus(ArrayList<ItemDocs> itemDocs) {

        for(ItemDocs id : itemDocs)
        {
            if(id.getDocType().equalsIgnoreCase(ClicConstants.DOC_INVOICE_VALUE))
            {
                txtInvoiceStatus.setText("View");
                invoiceStatus.setBackgroundResource(R.drawable.success);
                txtInvoiceStatus.setOnClickListener(this);

            }
            else if(id.getDocType().equalsIgnoreCase(ClicConstants.DOC_WARRANTY_VALUE))
            {
                txtwarrantyStatus.setText("View");
                warrantyStatus.setBackgroundResource(R.drawable.success);
                txtwarrantyStatus.setOnClickListener(this);

            }
            else if(id.getDocType().equalsIgnoreCase(ClicConstants.DOC_INSURENCE_VALUE))
            {
                txtinsuranceStatus.setText("View");
                insuranceStatus.setBackgroundResource(R.drawable.success);
                txtinsuranceStatus.setOnClickListener(this);

            }

        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
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

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id)
        {
            case R.id.viewInvoice:

                break;

            case R.id.userMannual:
                break;
            case R.id.txt_statusInsurance:
                if(getItemDocument(mUserItem.getItemDocs(), ClicConstants.DOC_INSURENCE_VALUE) != null) {
                    ClicUtils.createImagePinchDialog(getActivity(), R.layout.pinch_zoom_image,
                            getItemDocument(mUserItem.getItemDocs(), ClicConstants.DOC_INSURENCE_VALUE).getFilePath() );
                }
                break;
            case R.id.txt_statusInvoce:
                if(getItemDocument(mUserItem.getItemDocs(), ClicConstants.DOC_INVOICE_VALUE) != null) {
                    ClicUtils.createImagePinchDialog(getActivity(), R.layout.pinch_zoom_image,
                            getItemDocument(mUserItem.getItemDocs(), ClicConstants.DOC_INVOICE_VALUE).getFilePath() );
                }
                break;
            case R.id.txt_statusWarranty:
                if(getItemDocument(mUserItem.getItemDocs(), ClicConstants.DOC_WARRANTY_VALUE) != null) {
                    ClicUtils.createImagePinchDialog(getActivity(), R.layout.pinch_zoom_image,
                            getItemDocument(mUserItem.getItemDocs(), ClicConstants.DOC_WARRANTY_VALUE).getFilePath() );
                }
                break;
        }
    }
}
