package com.example.yashladha.android_seller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yashladha.android_seller.fragments.SalesFrag;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class SalesActivity extends AppCompatActivity {

    private Spinner mGenderSpinner;
    private String mGender;
    PieChart pieChart ;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;
    TextView tvMonth,tvMostBought,tvMostProductName,tvAnalysis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        setTitle("Sales");
        getSupportFragmentManager().beginTransaction().replace(R.id.container1,new SalesFrag()).commit();

        mGenderSpinner = (Spinner) findViewById(R.id.spinner_month);

        tvMostBought = (TextView)findViewById(R.id.tvMostBought);
        tvMonth = (TextView)findViewById(R.id.tvMonth);
        tvMostProductName = (TextView)findViewById(R.id.tvMostProductName);
        tvAnalysis = (TextView)findViewById(R.id.tvAnalysis);


        pieChart = (PieChart) findViewById(R.id.piechart);
        entries = new ArrayList<>();
        PieEntryLabels = new ArrayList<String>();
        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");
        pieData = new PieData(PieEntryLabels, pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(pieData);
        pieChart.animateY(3000);

        setupSpinner();
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_month_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String)adapterView.getItemAtPosition(i);
                if(!TextUtils.isEmpty(selection)){
                    if (selection.equals(getString(R.string.mon_jan))) {
                        mGender = "January";
                    }else if (selection.equals(getString(R.string.mon_feb))) {
                        mGender = "February";
                    }else if (selection.equals(getString(R.string.mon_mar))) {
                        mGender = "March";
                    }else if (selection.equals(getString(R.string.mon_apr))) {
                        mGender = "April";
                    }else if (selection.equals(getString(R.string.mon_may))) {
                        mGender = "May";
                    }else if (selection.equals(getString(R.string.mon_jun))) {
                        mGender = "June";
                    }else if (selection.equals(getString(R.string.mon_jul))) {
                        mGender = "July";
                    }else if (selection.equals(getString(R.string.mon_aug))) {
                        mGender = "August";
                    }else if (selection.equals(getString(R.string.mon_sep))) {
                        mGender = "September";
                    }else if (selection.equals(getString(R.string.mon_oct))) {
                        mGender = "October";
                    }else if (selection.equals(getString(R.string.mon_nov))) {
                        mGender = "November";
                    }else if (selection.equals(getString(R.string.mon_dec))) {
                        mGender = "December";
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mGender = "January";
            }
        });
    }


    public void AddValuesToPIEENTRY(){

        entries.add(new BarEntry(2f, 0));
        entries.add(new BarEntry(4f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(8f, 3));
        entries.add(new BarEntry(7f, 4));
        entries.add(new BarEntry(3f, 5));

    }

    public void AddValuesToPieEntryLabels(){

        PieEntryLabels.add("January");
        PieEntryLabels.add("February");
        PieEntryLabels.add("March");
        PieEntryLabels.add("April");
        PieEntryLabels.add("May");
        PieEntryLabels.add("June");

    }
}
