package com.example.yashladha.android_seller.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.yashladha.android_seller.R;
import com.example.yashladha.android_seller.data.Product;
import com.example.yashladha.android_seller.data.ProductAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFrag extends Fragment {

    public DisplayFrag() {
        // Required empty public constructor
    }

    public static ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.product_display_list, container, false);

        final ArrayList<Product> products = new ArrayList<Product>();

        products.add(new Product("Basket", "Rating - 4.2/5", "₹495", "₹550", "10%", "Exchange",
                "Yes", R.drawable.products_basket, R.drawable.remove));
        products.add(new Product("Box", "Rating - 3.8/5", "₹136", "₹170", "20%", "Exchange",
                "Yes", R.drawable.products_box, R.drawable.remove));
        products.add(new Product("Bulldozer", "Rating - 3.5/5", "₹255", "₹300", "15%", "Exchange",
                "No", R.drawable.products_bulldozer, R.drawable.remove));
        products.add(new Product("Chair", "Rating - 4/5", "₹1500", "₹2000", "25%", "Exchange",
                "Yes", R.drawable.products_chair, R.drawable.remove));
        products.add(new Product("Hat", "Rating - 3.9/5", "₹475", "₹500", "5%", "Exchange",
                "No", R.drawable.products_hat, R.drawable.remove));
        products.add(new Product("Storage Can", "Rating - 4.1/5", "₹649", "₹720", "10%", "Exchange",
                "Yes", R.drawable.products_storage_can, R.drawable.remove));
        products.add(new Product("Vase", "Rating - 4.4/5", "₹680", "₹800", "15%", "Exchange",
                "Yes", R.drawable.products_vase, R.drawable.remove));


        productAdapter = new ProductAdapter(getActivity(), products, R.color.back5);
        ListView listView = (ListView) rootview.findViewById(R.id.lvDisplayList);
        listView.setAdapter(productAdapter);

        return rootview;
    }

    public static void addProduct(){

    }
}
