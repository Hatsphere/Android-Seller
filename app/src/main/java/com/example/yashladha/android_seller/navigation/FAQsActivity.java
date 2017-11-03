package com.example.yashladha.android_seller.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yashladha.android_seller.R;

public class FAQsActivity extends AppCompatActivity {

    TextView tvQues1, tvAns1, tvQues2, tvAns2, tvQues3, tvAns3, tvQues4, tvAns4;
    ImageView ivFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_faqs);
        tvQues1 = (TextView) findViewById(R.id.tvQues1);
        tvAns1 = (TextView) findViewById(R.id.tvAns1);
        tvQues2 = (TextView) findViewById(R.id.tvQues2);
        tvAns2 = (TextView) findViewById(R.id.tvAns2);
        tvQues3 = (TextView) findViewById(R.id.tvQues3);
        tvAns3 = (TextView) findViewById(R.id.tvAns3);
        tvQues4 = (TextView) findViewById(R.id.tvQues4);
        tvAns4 = (TextView) findViewById(R.id.tvAns4);

        ivFaq = (ImageView) findViewById(R.id.ivFaq);
    }
}
