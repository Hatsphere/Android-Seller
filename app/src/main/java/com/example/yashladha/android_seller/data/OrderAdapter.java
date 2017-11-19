package com.example.yashladha.android_seller.data;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.yashladha.android_seller.R;
import com.example.yashladha.android_seller.classes.RoundImage;
import com.example.yashladha.android_seller.helper.BaseUrlConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dell pc on 22-10-2017.
 */

public class OrderAdapter extends ArrayAdapter<Order> {
    private RequestQueue rq;
    private int mColorResourceId;
    private ArrayList<Product> adapOrder;
    RoundImage roundedImage;
    private Context mContext;

    public OrderAdapter(Activity context, ArrayList<Order> orders, int colorResourceId) {
        super(context, 0, orders);
        mColorResourceId = colorResourceId;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_orders_list_item, parent, false);
        }
        final Order currentOrder = getItem(position);

        rq = Volley.newRequestQueue(getContext());
        TextView mProductName = (TextView) listItemView.findViewById(R.id.tvProductName);
        mProductName.setText(currentOrder.getmProductName());

        TextView mProductDate = (TextView) listItemView.findViewById(R.id.tvProductDate);
        mProductDate.setText(currentOrder.getmProductDate());

        TextView mID = (TextView) listItemView.findViewById(R.id.tvID);
        mID.setText(currentOrder.getmOrderID());

        TextView mTypeOfRequest = (TextView) listItemView.findViewById(R.id.tvTypeOfRequest);
        mTypeOfRequest.setText(currentOrder.getmTypeOfRequest());

        TextView mNumOfRequest = (TextView) listItemView.findViewById(R.id.tvNumOfRequests);
        mNumOfRequest.setText("Number of Request");

        final Button mAccept = (Button) listItemView.findViewById(R.id.btAccept);
        mAccept.setText("Accept");

        //final Button mReject = (Button) listItemView.findViewById(R.id.btReject);
        //mReject.setText(currentOrder.getmReject());

        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
                adb.setTitle("Accept");
                adb.setMessage("Are you sure you want to accept the request for " + currentOrder.getmTypeOfRequest()
                        + " of " + currentOrder.getmProductName() + "?");
                final int positionToRemove = pos;
                final String url = BaseUrlConfig.getBaseURL() + "order/accept/";

                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        JSONObject data = new JSONObject();
                        try {
                            data.put("order", currentOrder);
                            data.put("timeStamp", getCurrentTimeStamp());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.POST, url, data, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    if (response.get("response").toString().equals("200")) {
                                        Toast.makeText(getContext(), "Accepted your order", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(getContext(), "There is some problem", Toast.LENGTH_SHORT).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("error", error.toString());
                                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        rq.add(jsonObjectRequest);


                        mAccept.setText("ACCEPTED !!!");
                        mAccept.setBackgroundColor(Color.GREEN);
                        mAccept.setEnabled(false);
                        //mReject.setVisibility(View.GONE);

                        /*ViewGroup layout = (ViewGroup) mReject.getParent();
                        if(null!=layout) //for safety only  as you are doing onClick
                            layout.removeView(mReject);
                        */

                    }
                });
                adb.show();

            }
        });


        TextView mNum = (TextView) listItemView.findViewById(R.id.tvNum);
        mNum.setText(currentOrder.getmNum());

        ImageView mProductImageResource = (ImageView) listItemView.findViewById(R.id.ivProduct);

        View textContainer = listItemView.findViewById(R.id.linearLayout1);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
        //return super.getView(position, convertView, parent);
    }
    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
