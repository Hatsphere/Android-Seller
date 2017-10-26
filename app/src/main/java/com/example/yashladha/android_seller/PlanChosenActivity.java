package com.example.yashladha.android_seller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlanChosenActivity extends AppCompatActivity {

    TextView tvCongrats,tvMembership,tvTypeOfPlan,tvExpiry,tvExpiryDate;
    Button btGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_chosen);

        tvCongrats= (TextView)findViewById(R.id.tvCongrats);
        tvMembership= (TextView)findViewById(R.id.tvMembership);
        tvTypeOfPlan= (TextView)findViewById(R.id.tvTypeOfPlan);
        tvExpiry= (TextView)findViewById(R.id.tvExpiry);
        tvExpiryDate= (TextView)findViewById(R.id.tvExpiryDate);

        btGetStarted = (Button)findViewById(R.id.btGetStarted);

        btGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
