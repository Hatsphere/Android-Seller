package com.example.yashladha.android_seller.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yashladha.android_seller.R;

public class ChangePassword extends AppCompatActivity {

    TextView tvOldPassword ,tvNewPassword ,tvConfirmPassword ;
    EditText etOldPassword, etNewPassword ,etConfirmPassword ;
    Button btDone ,btCheckPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvOldPassword = (TextView)findViewById(R.id.tvOldPassword);
        tvNewPassword = (TextView)findViewById(R.id.tvNewPassword);
        tvConfirmPassword = (TextView)findViewById(R.id.tvConfirmPassword);

        etOldPassword = (EditText) findViewById(R.id.etOldPassword);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

        btDone = (Button)findViewById(R.id.btDone);
        btCheckPassword = (Button)findViewById(R.id.btCheckPassword);
    }
}
