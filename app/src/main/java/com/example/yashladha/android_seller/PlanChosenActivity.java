package com.example.yashladha.android_seller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlanChosenActivity extends AppCompatActivity {

    TextView tvCongrats, tvMembership, tvTypeOfPlan, tvExpiry, tvExpiryDate;
    Button btGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_chosen);

        tvCongrats = (TextView) findViewById(R.id.tvCongrats);
        tvMembership = (TextView) findViewById(R.id.tvMembership);
        tvTypeOfPlan = (TextView) findViewById(R.id.tvTypeOfPlan);
        tvExpiry = (TextView) findViewById(R.id.tvExpiry);
        tvExpiryDate = (TextView) findViewById(R.id.tvExpiryDate);
        btGetStarted = (Button) findViewById(R.id.btGetStarted);
        String type = getIntent().getStringExtra("planChosen");
        type = type + " Plan.";
        tvTypeOfPlan.setText(type);
        btGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("myprfs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Registered", "Yes");
                editor.commit();
                editor.apply();
                Intent i = new Intent(PlanChosenActivity.this, HomePageActivity.class);
                startActivity(i);
            }
        });

    }
}
