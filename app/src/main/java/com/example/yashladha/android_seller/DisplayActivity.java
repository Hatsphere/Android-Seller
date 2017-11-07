package com.example.yashladha.android_seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SearchView;

import com.example.yashladha.android_seller.fragments.DisplayFrag;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_display_list);
        setTitle("Products");
        getSupportFragmentManager().beginTransaction().replace(R.id.container1,new DisplayFrag()).commit();

        SearchView searchView = (SearchView)findViewById(R.id.svProducts);

        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fbAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(DisplayActivity.this, AddProductsActivity.class);
            }
        });

    }

}
