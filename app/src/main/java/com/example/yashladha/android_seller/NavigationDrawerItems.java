package com.example.yashladha.android_seller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.yashladha.android_seller.navigation.AboutUsFragment;
import com.example.yashladha.android_seller.navigation.FAQsFragment;
import com.example.yashladha.android_seller.navigation.HelpFragment;
import com.example.yashladha.android_seller.navigation.MyAccountFragment;

public class NavigationDrawerItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_items);
        String type = getIntent().getStringExtra("Type");
        Toast.makeText(NavigationDrawerItems.this, type,
                Toast.LENGTH_SHORT).show();

        if (type.equals("About Us"))
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new AboutUsFragment()).commit();
        else if (type.equals("My Account"))
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MyAccountFragment()).commit();
        else if (type.equals("Help"))
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HelpFragment()).commit();
        else if (type.equals("FAQs"))
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FAQsFragment()).commit();

    }
}
