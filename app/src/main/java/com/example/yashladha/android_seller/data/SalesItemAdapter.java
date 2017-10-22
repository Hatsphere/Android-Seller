package com.example.yashladha.android_seller.data;

import android.app.Activity;
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
 * Created by dell pc on 23-10-2017.
 */

public class SalesItemAdapter extends ArrayAdapter<SalesItem> {

    private int mColorResourceId;

    public SalesItemAdapter(Activity context, ArrayList<SalesItem> salesItems, int colorResourceId)
    {
        super(context,0,salesItems);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.product_display_list,parent,false);
        }
        SalesItem currentSalesItem = getItem(position);

        TextView mProductName = (TextView)listItemView.findViewById(R.id.tvProductName);
        mProductName.setText(currentSalesItem.getmProductName());

        TextView mProductCode = (TextView)listItemView.findViewById(R.id.tvProductCode);
        mProductCode.setText(currentSalesItem.getmProductCode());

        TextView mNumItemsSold = (TextView)listItemView.findViewById(R.id.tvNumItemsSold);
        mNumItemsSold.setText(currentSalesItem.getmNumItemsSold());

        TextView mNum = (TextView)listItemView.findViewById(R.id.tvNum);
        mNum.setText(currentSalesItem.getmNum());

        TextView mTotalAmount = (TextView)listItemView.findViewById(R.id.tvTotalAmount);
        mTotalAmount.setText(currentSalesItem.getmTotalAmount());

        TextView mAmount = (TextView)listItemView.findViewById(R.id.tvAmount);
        mAmount.setText(currentSalesItem.getmAmount());

        ImageView mProductImageResource = (ImageView)listItemView.findViewById(R.id.ivProduct);
        if(currentSalesItem.hasImage1())
        {
            mProductImageResource.setImageResource(currentSalesItem.getmProductImageResourceId());
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
