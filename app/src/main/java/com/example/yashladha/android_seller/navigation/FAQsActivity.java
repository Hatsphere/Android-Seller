package com.example.yashladha.android_seller.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.yashladha.android_seller.R;

public class FAQsActivity extends AppCompatActivity {

    TextView tvQues,tvAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_faqs);
        tvQues = (TextView)findViewById(R.id.tvQues);
        tvAns = (TextView)findViewById(R.id.tvAns);
    }
}
