package com.example.yashladha.android_seller.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yashladha.android_seller.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFrag extends Fragment {


    public OrdersFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.orders_list, container, false);
    }

}
