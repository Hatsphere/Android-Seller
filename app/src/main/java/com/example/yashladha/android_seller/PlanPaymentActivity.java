package com.example.yashladha.android_seller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlanPaymentActivity extends AppCompatActivity {

    TextView tvAmountPaid,tvAmt,tvSelectPay;
    View vTotal,vOption;
    Button btDebit,btCredit,btNet;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_payment);
        tvAmountPaid= (TextView)findViewById(R.id.tvAmountPaid);
        tvAmt= (TextView)findViewById(R.id.tvAmt);
        tvSelectPay= (TextView)findViewById(R.id.tvSelectPay);
        type = getIntent().getStringExtra("planChosen");
        vTotal = (View)findViewById(R.id.vTotal);
        vOption = (View)findViewById(R.id.vOption);

        btDebit = (Button)findViewById(R.id.btDebit);
        btCredit = (Button)findViewById(R.id.btCredit);
        btNet = (Button)findViewById(R.id.btNet);

        btDebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlanPaymentActivity.this, PlanChosenActivity.class);
                startActivity(i);
            }
        });

        btCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlanPaymentActivity.this, PlanChosenActivity.class);
                startActivity(i);

            }
        });

        btNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlanPaymentActivity.this, PlanChosenActivity.class);
                i.putExtra("planChosen", type);
                startActivity(i);

            }
        });
    }
}
