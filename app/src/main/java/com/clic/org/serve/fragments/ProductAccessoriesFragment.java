package com.clic.org.serve.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.clic.org.R;
import com.clic.org.serve.activity.ProductDetailsAndServicesActivity;
import com.clic.org.serve.adapters.ItemGridAdapter;
import com.clic.org.serve.constants.ClicConstants;


public class ProductAccessoriesFragment extends Fragment {

    String[] web = {
            "Refrigerator",
            "Washing Machine",
            "Television",
            "Micro Oven",
            "Water Purifier",
            "Mobile",
            "AIr Conditioner"


    } ;

    int[] imageId = {
            R.drawable.refrigerator,
            R.drawable.washing_machine,
            R.drawable.television,
            R.drawable.microoven,
            R.drawable.waterpurifier,
            R.drawable.mobile,
            R.drawable.airconditionar


    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product_accessories, container, false);
        initWidgets(view);
        return view;
    }

    private void initWidgets(View view) {

      /* // ItemGridAdapter myAdapter = new ItemGridAdapter(getActivity(),web,imageId, ClicConstants.CLIC_PRODUCTS);
        GridView myGrid = (GridView)view.findViewById(R.id.accessories_lsit);
        myGrid.setAdapter(myAdapter);

        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });*/

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }



    @Override
    public void onDetach() {
        super.onDetach();
    }



}
