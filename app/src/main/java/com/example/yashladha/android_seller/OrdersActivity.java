package com.example.yashladha.android_seller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yashladha.android_seller.fragments.OrdersFrag;


public class OrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_list);
        setTitle("Orders");
        getSupportFragmentManager().beginTransaction().replace(R.id.container1, new OrdersFrag()).commit();
    }
}
