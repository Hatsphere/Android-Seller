package com.example.yashladha.android_seller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yashladha.android_seller.fragments.DisplayFrag;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_display_list);
        setTitle("Products");
        getSupportFragmentManager().beginTransaction().replace(R.id.container1,new DisplayFrag()).commit();
    }
}
