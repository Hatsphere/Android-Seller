package com.example.yashladha.android_seller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yashladha.android_seller.fragments.SalesFrag;

public class SalesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_list);
        setTitle("Sales");
        getSupportFragmentManager().beginTransaction().replace(R.id.container1,new SalesFrag()).commit();
    }
}
