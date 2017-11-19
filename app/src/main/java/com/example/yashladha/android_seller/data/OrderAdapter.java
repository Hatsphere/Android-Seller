package com.example.yashladha.android_seller.data;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yashladha.android_seller.R;

import java.util.ArrayList;

/**
 * Created by dell pc on 22-10-2017.
 */

public class OrderAdapter extends ArrayAdapter<Order> {

    private int mColorResourceId;

    public OrderAdapter(Activity context, ArrayList<Order> orders, int colorResourceId) {
        super(context, 0, orders);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_orders_list_item, parent, false);
        }
        final int pos = position;
        final Order currentOrder = getItem(position);

        TextView mProductName = (TextView) listItemView.findViewById(R.id.tvProductName);
        mProductName.setText(currentOrder.getmProductName());

        TextView mProductPrice = (TextView) listItemView.findViewById(R.id.tvPrice);
        mProductPrice.setText(currentOrder.getmProductPrice());

        TextView mDate = (TextView) listItemView.findViewById(R.id.tvDate);
        mDate.setText(currentOrder.getmDate());

        TextView mStatus = (TextView) listItemView.findViewById(R.id.tvStatus);
        mStatus.setText(currentOrder.getmStatus());

        final Button mAccept = (Button) listItemView.findViewById(R.id.btAccept);
        mAccept.setText(currentOrder.getmAccept());

        //final Button mReject = (Button) listItemView.findViewById(R.id.btReject);
        //mReject.setText(currentOrder.getmReject());

        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                AlertDialog.Builder adb=new AlertDialog.Builder(getContext());
                adb.setTitle("Accept");
                adb.setMessage("Are you sure you want to accept the request for "+ currentOrder.getmDate()
                        + " of " + currentOrder.getmProductName()+"?");
                final int positionToRemove = pos;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        mAccept.setText("ACCEPTED !!!");
                        mAccept.setBackgroundColor(Color.GREEN);
                        mAccept.setEnabled(false);
                        //mReject.setVisibility(View.GONE);

                        /*ViewGroup layout = (ViewGroup) mReject.getParent();
                        if(null!=layout) //for safety only  as you are doing onClick
                            layout.removeView(mReject);
                        */

                    }});
                adb.show();

            }
        });


        ImageView mProductImageResource = (ImageView) listItemView.findViewById(R.id.ivProduct);
        if (currentOrder.hasImage1()) {
            mProductImageResource.setImageResource(currentOrder.getmProductImageResourceId());
            mProductImageResource.setVisibility(View.VISIBLE);
        } else {
            mProductImageResource.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.linearLayout1);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
        //return super.getView(position, convertView, parent);
    }

}
