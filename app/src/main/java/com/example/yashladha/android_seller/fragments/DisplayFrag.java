package com.example.yashladha.android_seller.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.yashladha.android_seller.R;
import com.example.yashladha.android_seller.data.Product;
import com.example.yashladha.android_seller.data.ProductAdapter;
import com.example.yashladha.android_seller.helper.BaseUrlConfig;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFrag extends Fragment {

    String name = "", rating = "", newPrice = "", orignalPrice = "", productDiscount = "", Exchange = "Exchange", ExchangeType = "", description = "", category = "";
    Boolean availability = false, sales = false;
    Context mContext = getActivity();
    JSONObject reader;
    String imgURL = "";
    Bitmap bmap;
    ImageView iv;
    Drawable drawable;
    public static ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.product_display_list, container, false);
        final ArrayList<Product> products = new ArrayList<Product>();

        SharedPreferences myPrefs = getActivity().getSharedPreferences("myprfs", Context.MODE_PRIVATE);
        String UID = myPrefs.getString("UID", "");
        //plan = myPrefs.getString("Plan", "");
        final RequestQueue[] rq = {Volley.newRequestQueue(getContext())};
        JSONObject data = new JSONObject();
        String url = BaseUrlConfig.getBaseURL() + "product/all/" + UID;
        //data.put("email", email);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url,
                data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Iterator<String> iter = response.keys();
                        while (iter.hasNext()) {
                            String key = iter.next();
                            JSONObject value = null;
                            try {
                                value = response.getJSONObject(key);
                            } catch (JSONException e) {
                                // Something went wrong!
                            }
                            try {
                                availability = value.getBoolean("Availability");
                                name = value.getString("Price");
                                JSONObject obj = value.getJSONObject("Images");
                                imgURL = obj.getString("primaryImage");
                                iv = rootview.findViewById(R.id.imageView1);
                                Picasso.with(getContext()).load(iv.toString())
                                        .into(iv, new com.squareup.picasso.Callback() {
                                            @Override
                                            public void onSuccess() {
                                                drawable = (Drawable) iv.getDrawable();
                                            }

                                            @Override
                                            public void onError() {

                                            }
                                        });
                                //
                                category = value.getString("Class");
                                newPrice = value.getString("Price");
                                sales = value.getBoolean("Sale");
                                description = value.getString("Description");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            products.add(new Product(name,rating,newPrice,orignalPrice,productDiscount,"Exchange","Yes",drawable,R.drawable.remove))
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(rootview.getClass().getSimpleName(), error.getMessage());
                    }
                }
        );
        rq[0].add(request);

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
