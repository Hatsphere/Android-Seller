package com.example.yashladha.android_seller.data;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by dell pc on 22-10-2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    private int mColorResourceId;
    private ArrayList<Product> adapProduct;
    RoundImage roundedImage;
    private Context mContext;

    public ProductAdapter(Context context, ArrayList<Product> products, int colorResourceId) {
        super(context, 0, products);
        mColorResourceId = colorResourceId;
        this.mContext = context;
        this.adapProduct = products;
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

        TextView mProductName = listItemView.findViewById(R.id.tvProductName);
        mProductName.setText(currentProduct.getmProductName());

        TextView mProductRating = listItemView.findViewById(R.id.tvRating);
        mProductRating.setText(currentProduct.getmProductRating());

        TextView mProductNewPrice = listItemView.findViewById(R.id.tvNewPrice);
        mProductNewPrice.setText(currentProduct.getmProductNewPrice());

        TextView mProductOriginalPrice = listItemView.findViewById(R.id.tvOriginalPrice);
        mProductOriginalPrice.setPaintFlags(mProductOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mProductOriginalPrice.setText(currentProduct.getmProductOriginalPrice());

        TextView mProductDiscount = listItemView.findViewById(R.id.tvDiscount);
        mProductDiscount.setText(currentProduct.getmProductDiscount());

        TextView mExchange = listItemView.findViewById(R.id.tvExchange);
        mExchange.setText(currentProduct.getmExchange());

        TextView mYesNo = listItemView.findViewById(R.id.tvYesNo);
        mYesNo.setText(currentProduct.getmYesNo());

        ImageView mProductImageResource = listItemView.findViewById(R.id.ivProduct);

        ImageButton mProductRemoveImageResource = listItemView.findViewById(R.id.ibProduct);

        mProductRemoveImageResource.setImageResource(currentProduct.getmProductRemoveImageResourceId());
        mProductRemoveImageResource.setVisibility(View.VISIBLE);

        if (!Objects.equals(currentProduct.getPrimaryImage(), "")) {
            Log.d("Image", currentProduct.getPrimaryImage());
            Picasso.with(mContext)
                    .load(currentProduct.getPrimaryImage())
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
        }
        else {
            Picasso.with(mContext)
                    .load(R.mipmap.ic_launcher)
                    .into(mProductImageResource);
        }

        mProductRemoveImageResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + currentProduct.getmProductName() + "?");
                final int positionToRemove = pos;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DisplayFrag.productAdapter.remove(getItem(positionToRemove));
                        DisplayFrag.productAdapter.notifyDataSetChanged();
                    }
                });
                adb.show();

            }
        });

        View textContainer = listItemView.findViewById(R.id.linearLayout2);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }

    @Override
    public int getCount() {
        return adapProduct.size();
    }
}
