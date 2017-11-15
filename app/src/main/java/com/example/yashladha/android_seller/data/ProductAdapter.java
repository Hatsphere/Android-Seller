package com.example.yashladha.android_seller.data;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yashladha.android_seller.R;
import com.example.yashladha.android_seller.classes.RoundImage;
import com.example.yashladha.android_seller.fragments.DisplayFrag;

import java.util.ArrayList;

/**
 * Created by dell pc on 22-10-2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    private int mColorResourceId;
    RoundImage roundedImage;

    public ProductAdapter(Activity context, ArrayList<Product> products, int colorResourceId) {
        super(context, 0, products);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_display_list_item, parent, false);
        }
        final Product currentProduct = getItem(position);

        TextView mProductName = (TextView) listItemView.findViewById(R.id.tvProductName);
        mProductName.setText(currentProduct.getmProductName());

        TextView mProductRating = (TextView) listItemView.findViewById(R.id.tvRating);
        mProductRating.setText(currentProduct.getmProductRating());

        TextView mProductNewPrice = (TextView) listItemView.findViewById(R.id.tvNewPrice);
        mProductNewPrice.setText(currentProduct.getmProductNewPrice());

        TextView mProductOriginalPrice = (TextView) listItemView.findViewById(R.id.tvOriginalPrice);
        mProductOriginalPrice.setPaintFlags(mProductOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mProductOriginalPrice.setText(currentProduct.getmProductOriginalPrice());

        TextView mProductDiscount = (TextView) listItemView.findViewById(R.id.tvDiscount);
        mProductDiscount.setText(currentProduct.getmProductDiscount());

        TextView mExchange = (TextView) listItemView.findViewById(R.id.tvExchange);
        mExchange.setText(currentProduct.getmExchange());

        TextView mYesNo = (TextView) listItemView.findViewById(R.id.tvYesNo);
        mYesNo.setText(currentProduct.getmYesNo());

        ImageView mProductImageResource = (ImageView) listItemView.findViewById(R.id.ivProduct);

        if (currentProduct.hasImage1()) {
            mProductImageResource.setImageResource(currentProduct.getmProductImageResourceId());
            mProductImageResource.setVisibility(View.VISIBLE);
        } else {
            mProductImageResource.setVisibility(View.GONE);
        }

        ImageButton mProductRemoveImageResource = (ImageButton) listItemView.findViewById(R.id.ibProduct);
        if (currentProduct.hasImage2()) {
            mProductRemoveImageResource.setImageResource(currentProduct.getmProductRemoveImageResourceId());
            mProductRemoveImageResource.setVisibility(View.VISIBLE);


            mProductRemoveImageResource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder adb=new AlertDialog.Builder(getContext());
                    adb.setTitle("Delete?");
                    adb.setMessage("Are you sure you want to delete " + currentProduct.getmProductName()+"?");
                    final int positionToRemove = pos;
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            DisplayFrag.productAdapter.remove(getItem(positionToRemove));
                            DisplayFrag.productAdapter.notifyDataSetChanged();
                        }});
                    adb.show();

                }
            });
        } else {
            mProductRemoveImageResource.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.linearLayout2);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
        //return super.getView(position, convertView, parent);
    }


}
