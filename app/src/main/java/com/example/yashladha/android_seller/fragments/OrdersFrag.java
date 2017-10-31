package com.example.yashladha.android_seller.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.yashladha.android_seller.R;
import com.example.yashladha.android_seller.data.Order;
import com.example.yashladha.android_seller.data.OrderAdapter;

import java.util.ArrayList;


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
        View rootview = inflater.inflate(R.layout.orders_list, container, false);

        final ArrayList<Order> orders = new ArrayList<Order>();

        orders.add(new Order("Baskets","₹400",R.string.purchase_request,"Accept","Reject",
                R.string.num_request,"2",R.drawable.order_baskets));
        orders.add(new Order("Handbags","₹1300",R.string.purchase_request,"Accept","Reject",
                R.string.num_request,"3",R.drawable.order_handbags));
        orders.add(new Order("Sandals","₹1500",R.string.refund_request,"Accept","Reject",
                R.string.num_request,"1",R.drawable.order_sandals));
        orders.add(new Order("Candles","₹220",R.string.purchase_request,"Accept","Reject",
                R.string.num_request,"5",R.drawable.orders_candles));
        orders.add(new Order("Duck","₹350",R.string.exchange_request,"Accept","Reject",
                R.string.num_request,"2",R.drawable.orders_duck));
        orders.add(new Order("Owl","₹300",R.string.refund_request,"Accept","Reject",
                R.string.num_request,"1",R.drawable.orders_owl));
        orders.add(new Order("Vase","₹1200",R.string.exchange_request,"Accept","Reject",
                R.string.num_request,"3",R.drawable.orders_vase));


        OrderAdapter orderAdapter = new OrderAdapter(getActivity(),orders,R.color.home_page_fragments);
        ListView listView = (ListView)rootview.findViewById(R.id.order);
        listView.setAdapter(orderAdapter);

        Button accept = (Button)rootview.findViewById(R.id.btAccept);
        Button reject = (Button)rootview.findViewById(R.id.btReject);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return rootview;
    }

}
