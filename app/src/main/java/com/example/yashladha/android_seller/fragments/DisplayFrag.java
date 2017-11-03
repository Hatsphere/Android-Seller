package com.example.yashladha.android_seller.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.yashladha.android_seller.AddProductsActivity;
import com.example.yashladha.android_seller.R;
import com.example.yashladha.android_seller.data.Order;
import com.example.yashladha.android_seller.data.OrderAdapter;
import com.example.yashladha.android_seller.data.Product;
import com.example.yashladha.android_seller.data.ProductAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class DisplayFrag extends Fragment {

    public DisplayFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.product_display_list, container, false);

        final ArrayList<Product> products = new ArrayList<Product>();

        products.add(new Product("Basket","","₹495","₹550","10%","Exchange",
                "Yes",R.drawable.products_basket,R.drawable.remove));
        products.add(new Product("Box","","₹136","₹170","20%","Exchange",
                "Yes",R.drawable.products_box,R.drawable.remove));
        products.add(new Product("Bulldozer","","₹255","₹300","15%","Exchange",
                "No",R.drawable.products_bulldozer,R.drawable.remove));
        products.add(new Product("Chair","","₹1500","₹2000","25%","Exchange",
                "Yes",R.drawable.products_chair,R.drawable.remove));
        products.add(new Product("Hat","","₹475","₹500","5%","Exchange",
                "No",R.drawable.products_hat,R.drawable.remove));
        products.add(new Product("Storage Can","","₹648","₹720","10%","Exchange",
                "Yes",R.drawable.products_storage_can,R.drawable.remove));
        products.add(new Product("Vase","","₹680","₹800","15%","Exchange",
                "Yes",R.drawable.products_vase,R.drawable.remove));


        ProductAdapter productAdapter = new ProductAdapter(getActivity(),products,R.color.home_page_fragments);
        ListView listView = (ListView)rootview.findViewById(R.id.display);
        listView.setAdapter(productAdapter);


        FloatingActionButton floatingActionButton = (FloatingActionButton)rootview.findViewById(R.id.fbAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), AddProductsActivity.class);
            }
        });
        return rootview;
    }



}
