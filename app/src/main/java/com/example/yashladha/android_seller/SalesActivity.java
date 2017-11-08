package com.example.yashladha.android_seller;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.util.Calendar;

public class SalesActivity extends AppCompatActivity {

    PieChart pieChart;
    ArrayList<Entry> entries;
    ArrayList<String> PieEntryLabels;
    PieDataSet pieDataSet;
    PieData pieData;
    FloatingActionButton btDate;
    TextView tvDate, tvMostBought, tvMostProductName, tvAnalysis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        setTitle("Sales");
        getSupportFragmentManager().beginTransaction().replace(R.id.container1, new SalesFrag()).commit();


        tvMostBought = (TextView) findViewById(R.id.tvMostBought);
        tvDate = (TextView) findViewById(R.id.tvDate);
        btDate = (FloatingActionButton) findViewById(R.id.btDate);
        tvMostProductName = (TextView) findViewById(R.id.tvMostProductName);
        tvAnalysis = (TextView) findViewById(R.id.tvAnalysis);
        pieChart = (PieChart) findViewById(R.id.piechart);
        btDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createDialogWithoutDateField();
            }
        });
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
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);}

        // Apply the adapter to the spinner


    public void AddValuesToPIEENTRY() {

        entries.add(new BarEntry(2f, 0));
        entries.add(new BarEntry(4f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(8f, 3));
        entries.add(new BarEntry(7f, 4));
        entries.add(new BarEntry(3f, 5));

    }

    public void AddValuesToPieEntryLabels() {

        PieEntryLabels.add("January");
        PieEntryLabels.add("February");
        PieEntryLabels.add("March");
        PieEntryLabels.add("April");
        PieEntryLabels.add("May");
        PieEntryLabels.add("June");

    }
    private DatePickerDialog createDialogWithoutDateField() {
        DatePickerDialog dpd = new DatePickerDialog(this, null, 2014, 1, 24);
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
        }
        return dpd;
    }
}
