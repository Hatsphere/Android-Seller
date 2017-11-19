package com.example.yashladha.android_seller.fragments;


import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.yashladha.android_seller.data.Order;
import com.example.yashladha.android_seller.data.OrderAdapter;
import com.example.yashladha.android_seller.data.Product;
import com.example.yashladha.android_seller.data.ProductAdapter;
import com.example.yashladha.android_seller.helper.BaseUrlConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFrag extends Fragment {
    private String order_date = "", pay_id = "", productName = "", status = "", order_id = "", Exchange = "Exchange", uid = "", del_date = "", sellerId = "";
    private Boolean availability = false, sales = false;
    String imgURL = "";
    int numOfRequest = 0;
    String typeOfRequest = "";
    String orderId = "";
    ImageView iv;
    String date = "";
    private Context mContext;
    private int quantity = 0;

    public OrdersFrag() {
        // Required empty public constructor
    }

    public static OrderAdapter orderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.orders_list, container, false);
        mContext = rootview.getContext();
        SharedPreferences myPrefs = getActivity().getSharedPreferences("myprfs", Context.MODE_PRIVATE);
        String UID = myPrefs.getString("UID", "");
        final ArrayList<Order> orders = new ArrayList<Order>();
        //plan = myPrefs.getString("Plan", "");
        final RequestQueue[] rq = {Volley.newRequestQueue(getContext())};
        final String url = BaseUrlConfig.getBaseURL() + "order/all/" + UID;
        JSONObject data = new JSONObject();
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
                               /* this.mProductDate = mProductDate;
                                this.mProductName = mProductName;
                                this.mTypeOfRequest = mTypeOfRequest;
                                this.mNumOfRequest = mNumOfRequest;
                                this.mOrderID = mOrderId;
                                this.mNum = mNum;
                                this.mProductImageResourceId = mProductImageResourceId;*/
                                order_date = value.getString("order_date");
                                pay_id = value.getString("pay_id");
                                productName = value.getString("productName");
                                status = value.getString("status");
                                order_id = value.getString("order_id");
                                uid = value.getString("uid");
                                del_date = value.getString("del_date");
                                quantity = value.getInt("quantity");
                                sellerId = value.getString("sellerId");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            orders.add(new Order(order_date, pay_id, productName, status, order_id, uid, del_date, quantity, sellerId));
                            //my_button.setBackgroundResource(R.drawable.defaultcard);
                            orderAdapter.setNotifyOnChange(true);
                            orderAdapter.notifyDataSetChanged();
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


        orderAdapter = new OrderAdapter(getActivity(), orders, R.color.back5);
        ListView listView = (ListView) rootview.findViewById(R.id.lvOrderList);
        listView.setAdapter(orderAdapter);

        return rootview;
    }

}
