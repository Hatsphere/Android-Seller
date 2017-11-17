package com.example.yashladha.android_seller.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFrag extends Fragment {

    private String name = "", rating = "", newPrice = "", orignalPrice = "", productDiscount = "", Exchange = "Exchange", ExchangeType = "", description = "", category = "";
    private Boolean availability = false, sales = false;
    String imgURL = "";
    ImageView iv;
    private Context mContext;
    public static ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.product_display_list, container, false);
        mContext = rootview.getContext();
        final ArrayList<Product> products = new ArrayList<Product>();

        SharedPreferences myPrefs = getActivity().getSharedPreferences("myprfs", Context.MODE_PRIVATE);
        String UID = myPrefs.getString("UID", "");
        //plan = myPrefs.getString("Plan", "");
        final RequestQueue[] rq = {Volley.newRequestQueue(getContext())};
        JSONObject data = new JSONObject();
        productAdapter = new ProductAdapter(getActivity(), products, R.color.back5);
        final String url = BaseUrlConfig.getBaseURL() + "product/all/" + UID;
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
                                name = value.getString("Name");
                                System.out.println(name);
                                JSONObject obj = value.getJSONObject("Images");
                                imgURL = obj.getString("primaryImage");
                                System.out.println(imgURL);
                                iv = rootview.findViewById(R.id.imageView1);
                                category = value.getString("Class");
                                newPrice = value.getString("Price");
                                sales = value.getBoolean("Sale");
                                description = value.getString("Description");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            products.add(new Product(name, rating, newPrice, orignalPrice, productDiscount, "Exchange", "Yes", imgURL, R.drawable.remove));
                            //my_button.setBackgroundResource(R.drawable.defaultcard);
                            productAdapter.setNotifyOnChange(true);
                            productAdapter.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.e(rootview.getClass().getSimpleName(), error.getMessage());
                    }
                }

        );
        rq[0].add(request);

        ListView listView = (ListView) rootview.findViewById(R.id.lvDisplayList);
        listView.setAdapter(productAdapter);

        return rootview;
    }

    public static void addProduct() {

    }


}
