package com.clic.org.serve.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.clic.org.R;
import com.clic.org.serve.data.UserItem;

import java.util.ArrayList;


public class ProductServiceFragment extends Fragment {

    View raiseRequest,viewHistory,troubleShoot;
    UserItem mUserItem;

    public ProductServiceFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_product, container, false);
        initWidgets(view);
        return view;
    }

    private void initWidgets(View view) {

        raiseRequest = (RelativeLayout)view.findViewById(R.id.viewRaiseReq);
        viewHistory = (RelativeLayout)view.findViewById(R.id.viewHistory);
        troubleShoot = (RelativeLayout)view.findViewById(R.id.viewTrouble);

        mUserItem = getArguments().getParcelable(getString(R.string.user_item));

        raiseRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MyListFragment fragment = new MyListFragment();
                Bundle b = new Bundle();
                b.putString(getString(R.string.list_type),getString(R.string.schedule_service));
                b.putParcelable(getString(R.string.user_item),mUserItem);
                fragment.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
