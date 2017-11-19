package com.example.yashladha.android_seller.data;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yashladha.android_seller.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by dell pc on 23-10-2017.
 */

public class SalesItemAdapter extends ArrayAdapter<SalesItem> {

    private int mColorResourceId;
    private ArrayList<SalesItem> adapSales;
    private Context mContext;


    public SalesItemAdapter(Activity context, ArrayList<SalesItem> salesItems, int colorResourceId) {
        super(context, 0, salesItems);
        mColorResourceId = colorResourceId;
        this.mContext = context;
        this.adapSales = salesItems;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(

                    R.layout.sales_item_details, parent, false);
        }
        SalesItem currentSalesItem = getItem(position);

        TextView mProductName = (TextView) listItemView.findViewById(R.id.tvProductName);
        mProductName.setText(currentSalesItem.getmProductName());

        TextView mTotalAmount = (TextView) listItemView.findViewById(R.id.tvProductCode);
        mTotalAmount.setText(currentSalesItem.getmTotalAmount());


        TextView mNum = (TextView) listItemView.findViewById(R.id.tvNum);
        mNum.setText(currentSalesItem.getmNum());

        TextView mAmount = (TextView) listItemView.findViewById(R.id.tvAmount);
        mAmount.setText(currentSalesItem.getmAmount());

        ImageView mProductImageResource = (ImageView) listItemView.findViewById(R.id.ivProduct);

        mProductImageResource.setVisibility(View.VISIBLE);

        if (!Objects.equals(currentSalesItem.getPrimaryImage(), "")) {
//            Log.d("Image", currentSalesItem.getPrimaryImage());
            Picasso.with(mContext)
                    .load(currentSalesItem.getPrimaryImage())
                    .resize(40, 40)
                    .into(mProductImageResource, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d("success", "success");
                        }

                        @Override
                        public void onError() {
                            Log.e("error", "error found");
                        }
                    });
        } else {
            Picasso.with(mContext)
                    .load(R.mipmap.ic_launcher)
                    .into(mProductImageResource);
        }

        View textContainer = listItemView.findViewById(R.id.linearLayout2);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
        //return super.getView(position, convertView, parent);
    }

    public int getCount() {
        return adapSales.size();
    }

}
