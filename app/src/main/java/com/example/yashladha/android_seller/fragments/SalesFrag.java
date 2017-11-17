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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.yashladha.android_seller.R;
import com.example.yashladha.android_seller.data.SalesItem;
import com.example.yashladha.android_seller.data.SalesItemAdapter;
import com.example.yashladha.android_seller.helper.BaseUrlConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesFrag extends Fragment {
    String uid;
    private String mProductName = "";
    private String mNum = "";
    private String primaryImage = "";
    private String mTotalAmount = "";
    private String mAmount = "";
    JSONObject reader;
    String imgURL = "";
    private Context mContext;
    private static SalesItemAdapter sales;
    TextView tvProductCode, tvProductName, tvNumItemsSold, tvNum, tvTotalAmount, tvAmount;
    ImageView ivProduct;
    final ArrayList<SalesItem> salesItems = new ArrayList<SalesItem>();

    public SalesFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.activity_sales, container, false);
        mContext = rootview.getContext();
        SharedPreferences myPrefs = getContext().getSharedPreferences("myprfs", Context.MODE_PRIVATE);
        uid = myPrefs.getString("UID", "");
        final RequestQueue[] rq = {Volley.newRequestQueue(getContext())};
        JSONObject data = new JSONObject();
        sales = new SalesItemAdapter(getActivity(), salesItems, R.color.back5);
        final String url = BaseUrlConfig.getBaseURL() + "product/all/" + uid;
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
                                //details for this
                                mProductName = value.getString("Name");
                                mNum = "";
                                mAmount = value.getString("Price");
                                JSONObject obj = value.getJSONObject("Images");
                                imgURL = obj.getString("primaryImage");
                                System.out.println(imgURL);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            salesItems.add(new SalesItem(mProductName, mNum, mAmount, mTotalAmount, imgURL));
                            //my_button.setBackgroundResource(R.drawable.defaultcard);
                            sales.setNotifyOnChange(true);
                            sales.notifyDataSetChanged();
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
        sales = new SalesItemAdapter(getActivity(), salesItems, R.color.back5);
        ListView listView = (ListView) rootview.findViewById(R.id.lvSalesList);
        listView.setAdapter(sales);
        return rootview;

    }


}
