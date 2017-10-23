package com.example.yashladha.android_seller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity_2 extends AppCompatActivity {

    EditText etAddress, etContact;
    Button btProceed;
    String address = "";
    String contact = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_2);
        etAddress = findViewById(R.id.etAddress);
        etContact = findViewById(R.id.etContact);
        btProceed = findViewById(R.id.btProcced);

        btProceed.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(RegisterActivity_2.this, TrialChoicesActivity.class);
                    }
                }
        );
        etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                address = etAddress.getText().toString().trim();
                //need to add the backend (nothing regarding that on the server files

            }
        });
        etContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                contact = etContact.getText().toString().trim();
                //need to add the backend (nothing regarding that on the server files

            }
        });
    }

}
