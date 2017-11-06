package com.example.yashladha.android_seller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yashladha.android_seller.R;
import com.example.yashladha.android_seller.data.Product;
import com.example.yashladha.android_seller.data.ProductAdapter;
import com.example.yashladha.android_seller.data.SalesItem;
import com.example.yashladha.android_seller.data.SalesItemAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesFrag extends Fragment {

    TextView tvProductCode,tvProductName,tvNumItemsSold,tvNum,tvTotalAmount,tvAmount;
    ImageView ivProduct;

    public SalesFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.activity_sales, container, false);

        tvProductCode = (TextView)rootview.findViewById(R.id.tvProductCode);
        tvProductName = (TextView)rootview.findViewById(R.id.tvProductName);
        tvNumItemsSold = (TextView)rootview.findViewById(R.id.tvNumItemsSold);
        tvNum = (TextView)rootview.findViewById(R.id.tvNum);
        tvTotalAmount = (TextView)rootview.findViewById(R.id.tvTotalAmount);
        tvAmount = (TextView)rootview.findViewById(R.id.tvAmount);

        ivProduct = (ImageView)rootview.findViewById(R.id.ivProduct);

        final ArrayList<SalesItem> salesItems = new ArrayList<SalesItem>();

        salesItems.add(new SalesItem("Baskets","0321323135","Number of items sold",
                "3","Total Amount :","₹1485",R.drawable.products_basket));
        salesItems.add(new SalesItem("Duck","0321323135","Number of items sold",
                "2","Total Amount :","₹1485",R.drawable.products_basket));
        salesItems.add(new SalesItem("Baskets","0321323135","Number of items sold",
                "3","Total Amount :","₹1485",R.drawable.products_basket));
        salesItems.add(new SalesItem("Baskets","0321323135","Number of items sold",
                "3","Total Amount :","₹1485",R.drawable.products_basket));
        salesItems.add(new SalesItem("Baskets","0321323135","Number of items sold",
                "3","Total Amount :","₹1485",R.drawable.products_basket));
        salesItems.add(new SalesItem("Baskets","0321323135","Number of items sold",
                "3","Total Amount :","₹1485",R.drawable.products_basket));

        SalesItemAdapter salesItemAdapter = new SalesItemAdapter(getActivity(),salesItems,R.color.home_page_fragments);
        ListView listView = (ListView)rootview.findViewById(R.id.lvSalesList);
        listView.setAdapter(salesItemAdapter);
        return rootview;

    }

}
