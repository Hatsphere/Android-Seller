package com.example.yashladha.android_seller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PlanChoicesActivity extends AppCompatActivity {

    Button btPlatinuum, btSilver, btGold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_choices);
        btPlatinuum = (Button) findViewById(R.id.btPlatinum);
        btSilver = (Button) findViewById(R.id.btSilver);
        btGold = (Button) findViewById(R.id.btGold);

        btPlatinuum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btSilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
