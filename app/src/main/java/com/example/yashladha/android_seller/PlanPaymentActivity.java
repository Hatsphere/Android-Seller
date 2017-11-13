package com.example.yashladha.android_seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanPaymentActivity extends AppCompatActivity {

    TextView tvAmountPaid, tvAmt, tvSelectPay, tvCalendar;
    View vTotal, vOption;
    Button btDebit, btCredit, btNet, btPickDate;
    String type;
    SimpleDateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_payment);
        tvAmountPaid = (TextView) findViewById(R.id.tvAmountPaid);
        tvAmt = (TextView) findViewById(R.id.tvAmt);
        tvSelectPay = (TextView) findViewById(R.id.tvSelectPay);
        type = getIntent().getStringExtra("planChosen");
        vTotal = (View) findViewById(R.id.vTotal);
        vOption = (View) findViewById(R.id.vOption);
        tvCalendar = (TextView) findViewById(R.id.tvCalendar);


        btDebit = (Button) findViewById(R.id.btDebit);
        btCredit = (Button) findViewById(R.id.btCredit);
        btNet = (Button) findViewById(R.id.btNet);
        btPickDate = (Button) findViewById(R.id.btPickDate);

        btDebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlanPaymentActivity.this, PlanChosenActivity.class);
                i.putExtra("planChosen", type);
                startActivity(i);
            }
        });

        btCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlanPaymentActivity.this, PlanChosenActivity.class);
                i.putExtra("planChosen", type);
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

        btPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date

                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment();
                cdp.show(PlanPaymentActivity.this.getSupportFragmentManager(), "Material Calendar");
                cdp.setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                    @Override
                    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                        try {
                            formatter = new SimpleDateFormat("dd/MM/yyyy");
                            String dateInString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                            Date date = formatter.parse(dateInString);

                            tvCalendar.setText(formatter.format(date).toString());


                        } catch (Exception ex) {

                        }
                    }
                });
            }
        });
    }
}
