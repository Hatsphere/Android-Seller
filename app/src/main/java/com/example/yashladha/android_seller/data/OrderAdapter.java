package com.example.yashladha.android_seller.data;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

public class OrderAdapter extends ArrayAdapter<Order>{

    private int mColorResourceId;

    public OrderAdapter(Activity context, ArrayList<Order> orders, int colorResourceId)
    {
        super(context,0,orders);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_orders_list_item,parent,false);
        }
        Order currentOrder = getItem(position);

        TextView mProductName = (TextView)listItemView.findViewById(R.id.tvProductName);
        mProductName.setText(currentOrder.getmProductName());

        TextView mProductPrice = (TextView)listItemView.findViewById(R.id.tvPrice);
        mProductPrice.setText(currentOrder.getmProductPrice());

        TextView mTypeOfRequest = (TextView)listItemView.findViewById(R.id.tvTypeOfRequest);
        mTypeOfRequest.setText(currentOrder.getmTypeOfRequest());

        TextView mNumOfRequest = (TextView)listItemView.findViewById(R.id.tvNumOfRequests);
        mNumOfRequest.setText(currentOrder.getmNumOfRequest());

        final Button mAccept = (Button) listItemView.findViewById(R.id.btAccept);
        mAccept.setText(currentOrder.getmAccept());

        final TransitionDrawable td1 = new TransitionDrawable(new Drawable[]{
                new ColorDrawable(getContext().getResources().getColor(R.color.back)),
                new ColorDrawable(getContext().getResources().getColor(R.color.back8))});
        mAccept.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    //b.setBackgroundColor();
                }
                if(event.getActionMasked() == MotionEvent.ACTION_UP) {
                    mAccept.setBackgroundDrawable(td1);
                    td1.startTransition(1000);
                }
                return false;
            }
        });

        final Button mReject = (Button) listItemView.findViewById(R.id.btReject);
        mReject.setText(currentOrder.getmReject());

        final TransitionDrawable td2 = new TransitionDrawable(new Drawable[]{
                new ColorDrawable(getContext().getResources().getColor(R.color.back3)),
                new ColorDrawable(getContext().getResources().getColor(R.color.back8))});
        mReject.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    //b.setBackgroundColor();
                }
                if(event.getActionMasked() == MotionEvent.ACTION_UP) {
                    mReject.setBackgroundDrawable(td2);
                    td2.startTransition(1000);
                }
                return false;
            }
        });

        TextView mNum = (TextView)listItemView.findViewById(R.id.tvNum);
        mNum.setText(currentOrder.getmNum());

        ImageView mProductImageResource = (ImageView)listItemView.findViewById(R.id.ivProduct);
        if(currentOrder.hasImage1())
        {
            mProductImageResource.setImageResource(currentOrder.getmProductImageResourceId());
            mProductImageResource.setVisibility(View.VISIBLE);
        }
        else{
            mProductImageResource.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.linearLayout2);
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
        //return super.getView(position, convertView, parent);
    }

}
