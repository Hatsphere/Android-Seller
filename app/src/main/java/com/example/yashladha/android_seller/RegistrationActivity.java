package com.example.yashladha.android_seller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class RegistrationActivity extends AppCompatActivity {
    public static final String TITLE = "Register";
    TextView tvName, tvPassword, tvLogin;
    EditText etPassword, etEmail;
    Button btLogin, btFacebook, btGoogle;
    ImageButton ibPassword;
    boolean password2 = false;
    String name = "";
    String UID_i = "";
    String password = "";
    private RequestQueue rq;
    String email = "";
    private Button btRegister;

    public RegistrationActivity() {
        // Required empty public constructor
    }

    public static RegistrationActivity newInstance() {
        return new RegistrationActivity();
    }

    public void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        tvName = (TextView) findViewById(R.id.tvName);
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btLogin = (Button) findViewById(R.id.btLogin);
        btFacebook = (Button) findViewById(R.id.btFacebook);
        rq = Volley.newRequestQueue(RegistrationActivity.this);

        btGoogle = (Button) findViewById(R.id.btGoogle);
        ibPassword = (ImageButton) findViewById(R.id.ibPassword);
        btRegister = btLogin;
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean ans = validateUserName() && validatePassword();
                if (ans) {


                    JSONObject obj = new JSONObject();

                    try {
                        obj.put("email", email);
                        obj.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.POST, "http://10.0.2.2:3000/user/signUp/", obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Toast.makeText(RegistrationActivity.this, response.get("response").toString(), Toast.LENGTH_SHORT).show();
                                if (response.get("response").toString() == "200") {
                                    UID_i = response.get("uid").toString();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            Toast.makeText(RegistrationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    rq.add(jsonObjectRequest);
                    etPassword.setText("");
                    etEmail.setText("");

                    if (ans == true) {
                        Intent i = new Intent(RegistrationActivity.this, RegisterActivity_2.class);
                        startActivity(i);
                        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("UID", UID_i);

                    }

                }
            }
        });


        ibPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password2 == false)
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                else
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                password2 = !password2;

            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = etPassword.getText().toString();


            }
        });
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                email = etEmail.getText().toString();
            }
        });
        btFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }

    private boolean validatePassword() {
        if (etPassword.getText().toString().trim().isEmpty()) {

            Toast.makeText(RegistrationActivity.this, "Invalid Password",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {

        }
        return true;
    }

    private boolean validateUserName() {
        if (email.isEmpty()) {
            Toast.makeText(RegistrationActivity.this, "Invalid User Name",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (!email.isEmpty() && !isValidEmail(email)) {
            Toast.makeText(RegistrationActivity.this, "Invalid User Name",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {

        }
        return true;
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}