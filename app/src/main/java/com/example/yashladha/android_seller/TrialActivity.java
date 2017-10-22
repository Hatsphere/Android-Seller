package com.example.yashladha.android_seller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TrialActivity extends AppCompatActivity {

    ImageView logo;
    TextView tvYou,tvTrial,tvWanna;
    Button btGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);

        logo = (ImageView)findViewById(R.id.logo);

        tvYou = (TextView)findViewById(R.id.tvYou);
        tvTrial = (TextView)findViewById(R.id.tvTrial);
        tvWanna = (TextView)findViewById(R.id.tvWanna);

        btGetStarted = (Button)findViewById(R.id.btGetStarted);

        btGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvWanna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
