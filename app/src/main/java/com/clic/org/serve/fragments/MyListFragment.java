package com.clic.org.serve.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.clic.org.R;
import com.clic.org.serve.Utils.ClicUtils;
import com.clic.org.serve.Utils.JsonUtils;
import com.clic.org.serve.data.Brand;
import com.clic.org.serve.data.Category;
import com.clic.org.serve.data.Item;
import com.clic.org.serve.data.OTP;
import com.clic.org.serve.data.Product;
import com.clic.org.serve.data.RequestType;
import com.clic.org.serve.data.RequestTypeResponse;
import com.clic.org.serve.data.ServiceRequest;
import com.clic.org.serve.data.ServiceType;
import com.clic.org.serve.data.SubCategory;
import com.clic.org.serve.data.UserItem;
import com.clic.org.serve.listener.ServiceListener;
import com.clic.org.serve.services.ServiceConstants;
import com.clic.org.serve.services.ServiceUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Venkatesh on 23-05-2016.
 */
public class MyListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    public MyListFragment()
    {

    }

    ListView myList;
    String type;

    AddClicProductListener mListener;

    ArrayList<String> list = new ArrayList<String>();

    MyListFragment fragment;
    Bundle b;
    Brand mBrand = new Brand();
    ArrayList<Brand> listBrands = new ArrayList<Brand>();
    Product mProduct = new Product();
    ArrayList<Product> listProducts = new ArrayList<Product>();
    Category mCategory =new Category();
    ArrayList<Category> listCategory = new ArrayList<Category>();

    SubCategory mSubCategory =new SubCategory();
    ArrayList<SubCategory> listSubCategory = new ArrayList<SubCategory>();

    Item mItem = new Item();
    ArrayList<Item> listItem = new ArrayList<Item>();

    ArrayList<ServiceType> listServiceRequest = new ArrayList<ServiceType>();
    ArrayList<RequestType> listRequestType = new ArrayList<RequestType>();

    UserItem muUserItem;

    ArrayAdapter adapter;
    RequestTypeResponse mReqestTypeResponse;
    ServiceType mServiceType;


    public interface AddClicProductListener
    {
        public void onArticalSelectedProgress(int value);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        type = getArguments().getString(getString(R.string.list_type));
        getListView().setOnItemClickListener(this);
        if(getArguments().getParcelable(getString(R.string.user_item))!= null)
        {
            muUserItem = getArguments().getParcelable(getString(R.string.user_item));
        }
        if(type.equalsIgnoreCase(getString(R.string.list_Brand))) {
            ServiceUtils.makeJsonArrayRequest(getActivity(),
                    ServiceConstants.BRANDS_LIST
                    , mServiceListener, null);
        }
        else if(type.equalsIgnoreCase(getString(R.string.list_Product)))
        {
            mBrand = getArguments().getParcelable(getString(R.string.parcel_products));
            ServiceUtils.makeJsonArrayRequest(getActivity(),
                    ServiceConstants.PRODUCT_LIST + mBrand.getBrandID()
                    , mServiceListener, null);
            muUserItem.setCustomerID(ClicUtils.readPreference(getActivity(), R.string.clic_ClientID));

        }

        else if(type.equalsIgnoreCase(getString(R.string.list_categories)))
        {
            mProduct = getArguments().getParcelable(getString(R.string.parcel_categories));

            ServiceUtils.postJsonObjectRequest(getActivity(),
                    ServiceConstants.CATEGORIE_ITEMS_LIST
                    , mServiceListener, JsonUtils.getJsonString(mProduct));
        } else if(type.equalsIgnoreCase(getString(R.string.list_Sub_categories)))
        {
            mCategory = getArguments().getParcelable(getString(R.string.parcel_sub_cat));

            ServiceUtils.postJsonObjectRequest(getActivity(),
                    ServiceConstants.CATEGORIE_SUB_ITEMS_LIST
                    , mServiceListener, JsonUtils.getJsonString(mCategory));
        }
        else if(type.equalsIgnoreCase(getString(R.string.list_model)))
        {
            mSubCategory = getArguments().getParcelable(getString(R.string.parcel_model));

            ServiceUtils.postJsonObjectRequest(getActivity(),
                    ServiceConstants.CATEGORIE_ITEM_MODEL
                    , mServiceListener, JsonUtils.getJsonString(mSubCategory));
        }
        else if(type.equalsIgnoreCase(getString(R.string.schedule_service))){

            ServiceUtils.makeJSONObjectReq(getActivity(),
                    ServiceConstants.TYPE_OF_SERVICE
                    , mServiceListener, null);

        }else if(type.equalsIgnoreCase(getString(R.string.list_repair_type)))
        {
            mServiceType = getArguments().getParcelable(getString(R.string.parcel_service_type));
            ServiceUtils.makeJSONObjectReq(getActivity(),
                           ServiceConstants.TYPE_OF_REPAIRS+muUserItem.getItemID(),
                           mServiceListener,null);

        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (AddClicProductListener)context;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



        fragment = new MyListFragment();
        b = new Bundle();


        int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
        Log.d("deubg", "fragment count" + count);
        if(type.equalsIgnoreCase(getString(R.string.list_Brand))) {

            muUserItem.setBrandID(listBrands.get(position).getBrandID());

            mListener.onArticalSelectedProgress(20);
            b.putString(getString(R.string.list_type), getString(R.string.list_Product));
            b.putParcelable(getString(R.string.parcel_products), listBrands.get(position));
            b.putParcelable(getString(R.string.user_item), muUserItem);

            fragment.setArguments(b);

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit(); }
        else if(type.equalsIgnoreCase(getString(R.string.list_Product)))
        {
            mListener.onArticalSelectedProgress(35);
            muUserItem.setProductID(listProducts.get(position).getProductID());
            b.putString(getString(R.string.list_type), getString(R.string.list_categories));
            b.putParcelable(getString(R.string.parcel_categories), listProducts.get(position));
            b.putParcelable(getString(R.string.user_item), muUserItem);

            fragment.setArguments(b);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

        }

        else if(type.equalsIgnoreCase(getString(R.string.list_categories)))
        {
            mListener.onArticalSelectedProgress(45);
            muUserItem.setCategoryID(listCategory.get(position).getCategoryID());

            b.putString(getString(R.string.list_type), getString(R.string.list_Sub_categories));
            b.putParcelable(getString(R.string.parcel_sub_cat), listCategory.get(position));
            b.putParcelable(getString(R.string.user_item), muUserItem);

            fragment.setArguments(b);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

        } else if(type.equalsIgnoreCase(getString(R.string.list_Sub_categories)))
        {
            mListener.onArticalSelectedProgress(55);
            muUserItem.setSubcategoryID(listSubCategory.get(position).getSubcategoryID());

            b.putString(getString(R.string.list_type), getString(R.string.list_model));
            b.putParcelable(getString(R.string.parcel_model), listSubCategory.get(position));
            b.putParcelable(getString(R.string.user_item), muUserItem);

            fragment.setArguments(b);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

        }
        else if(type.equalsIgnoreCase(getString(R.string.list_model)))
        {
            mListener.onArticalSelectedProgress(70);
            muUserItem.setItemID(listItem.get(position).getItemID());
            muUserItem.setModelNumber(listItem.get(position).getModelNumber());

            AddInvoiceFragment invoiceFragment = new AddInvoiceFragment();
            b.putString(getString(R.string.list_type), getString(R.string.add_invovice));
            b.putParcelable(getString(R.string.parcel_item), listItem.get(position));
            b.putParcelable(getString(R.string.user_item), muUserItem);
            invoiceFragment.setArguments(b);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, invoiceFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

        }
        else if(type.equalsIgnoreCase(getString(R.string.schedule_service))){

            mListener.onArticalSelectedProgress(50);
            ProductServiceScheduler productServiceScheduler = new ProductServiceScheduler();
            ServiceType mSerReq = listServiceRequest.get(position);
            if(mSerReq.getServiceType().equalsIgnoreCase("repair"))
            {
                b.putString(getString(R.string.list_type), getString(R.string.list_repair_type));
                b.putParcelable(getString(R.string.parcel_service_type),mSerReq);
                b.putParcelable(getString(R.string.user_item),muUserItem);
                fragment.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

            }
            else
            {
                b.putString(getString(R.string.activity_type),getString(R.string.schedule_serReq));
                b.putParcelable(getString(R.string.parcel_service_type), mSerReq);
                b.putParcelable(getString(R.string.user_item),muUserItem);
                productServiceScheduler.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, productServiceScheduler).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
            }
        }
        else if(type.equalsIgnoreCase(getString(R.string.list_repair_type)))
        {
               ProductServiceScheduler productServiceScheduler = new ProductServiceScheduler();
               RequestType mReqType = listRequestType.get(position);

              b.putString(getString(R.string.activity_type),getString(R.string.schedule_reqReq));
              b.putParcelable(getString(R.string.parcel_repiar_type_req), mReqType);
              b.putParcelable(getString(R.string.parcel_service_type), mServiceType);
                b.putParcelable(getString(R.string.parcel_repiar_type),mReqestTypeResponse);
                b.putParcelable(getString(R.string.user_item),muUserItem);
                productServiceScheduler.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, productServiceScheduler).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

        }
    }

    ServiceListener mServiceListener = new ServiceListener() {
        @Override
        public void onServiceResponse(String response) {

            list.clear();
            Bundle bundle = new Bundle();
            if(type.equalsIgnoreCase(getString(R.string.list_Brand))) {

                JsonArray lArray = JsonUtils.getJsonArray(response);

                for(int i=0 ;i<lArray.size();i++)
                {
                    listBrands.add(new Gson().fromJson(lArray.get(i).toString(),Brand.class));

                }
                for(Brand b : listBrands)
                {
                    list.add(b.getBrandName());
                }

                adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
                adapter.notifyDataSetChanged();
                setListAdapter(adapter);

            }
            else if(type.equalsIgnoreCase(getString(R.string.list_Product)))
            {
                JsonArray lArray = JsonUtils.getJsonArray(response);
                getListView().setAdapter(null);
                for(int i=0 ;i<lArray.size();i++)
                {
                    listProducts.add(new Gson().fromJson(lArray.get(i).toString(),Product.class));

                }
                for(Product b : listProducts)
                {
                    list.add(b.getProductName());
                }
                adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
                adapter.notifyDataSetChanged();
                setListAdapter(adapter);

            }

            else if(type.equalsIgnoreCase(getString(R.string.list_categories)))
            {
                JsonObject lObj = JsonUtils.getJsonObject(response);
                JsonArray lArray = lObj.getAsJsonArray("categorylist");
                for(int i=0 ;i<lArray.size();i++)
                {
                    listCategory.add(new Gson().fromJson(lArray.get(i).toString(),Category.class));

                }
                for(Category b : listCategory)
                {
                    list.add(b.getCategoryName());
                }
                adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
                adapter.notifyDataSetChanged();
                setListAdapter(adapter);
            }
            else if(type.equalsIgnoreCase(getString(R.string.list_Sub_categories)))
            {
                JsonObject lObj = JsonUtils.getJsonObject(response);
                JsonArray lArray = lObj.getAsJsonArray("subcategorylist");
                for(int i=0 ;i<lArray.size();i++)
                {
                    listSubCategory.add(new Gson().fromJson(lArray.get(i).toString(),SubCategory.class));

                }
                for(SubCategory b : listSubCategory)
                {
                    list.add(b.getSubCategoryName());
                }
                adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
                adapter.notifyDataSetChanged();
                setListAdapter(adapter); }
            else if(type.equalsIgnoreCase(getString(R.string.list_model)))
            {
                JsonObject lObj = JsonUtils.getJsonObject(response);
                JsonArray lArray = lObj.getAsJsonArray("itemsList");
                for(int i=0 ;i<lArray.size();i++)
                {
                    listItem.add(new Gson().fromJson(lArray.get(i).toString(),Item.class));

                }
                for(Item b : listItem)
                {
                    list.add(b.getModelNumber());
                }
                adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
                adapter.notifyDataSetChanged();
                setListAdapter(adapter);
            }
            else if(type.equalsIgnoreCase(getString(R.string.add_invovice)))
            {

                mListener.onArticalSelectedProgress(90);

            }
            else if(type.equalsIgnoreCase(getString(R.string.schedule_service))){

                JsonObject lObj = JsonUtils.getJsonObject(response);
                JsonArray lArray = lObj.getAsJsonArray("serviceRequest");
                for(int i=0 ;i<lArray.size();i++)
                {
                    listServiceRequest.add(new Gson().fromJson(lArray.get(i).toString(), ServiceType.class));
                }
                for(ServiceType s:listServiceRequest)
                {
                    list.add(s.getServiceType());
                }
                mListener.onArticalSelectedProgress(50);
                adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
                adapter.notifyDataSetChanged();
                setListAdapter(adapter);


            }
            else if(type.equalsIgnoreCase(getString(R.string.list_repair_type)))
            {

                JsonObject obj = JsonUtils.getJsonObject(response);
                JsonArray lArray = obj.getAsJsonArray("requestType");
                for(int i=0;i<lArray.size();i++)
                {
                    listRequestType.add(new Gson().fromJson(lArray.get(i).toString(),RequestType.class));
                }
                for(RequestType r:listRequestType)
                {
                    list.add(r.getDescription());
                }
                mReqestTypeResponse = new Gson().fromJson(obj.toString(),RequestTypeResponse.class);
                mReqestTypeResponse.setRequestType(listRequestType);
                adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
                adapter.notifyDataSetChanged();
                setListAdapter(adapter);
            }


        }

        @Override
        public void onServiceError(String response) {

            ClicUtils.displayToast(getActivity(),"Connection Error....!");
        }
    };


}
