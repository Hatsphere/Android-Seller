package com.example.yashladha.android_seller.navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.yashladha.android_seller.HomePageActivity;
import com.example.yashladha.android_seller.R;
import com.example.yashladha.android_seller.helper.BaseUrlConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePassword extends AppCompatActivity {

    TextView tvOldPassword, tvNewPassword, tvConfirmPassword;
    EditText etOldPassword, etNewPassword, etConfirmPassword;
    Button btDone, btCheckPassword, btProceed;
    private RequestQueue rq;
    Boolean password = false;
    String oldPassword, newPassword, confirmPassword;
    String email, uid;
    ImageButton ibConfirmPassword, ibNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences myPrefs = getSharedPreferences("myprfs", MODE_PRIVATE);
        email = myPrefs.getString("email", "");
        uid = myPrefs.getString("UID", "");
        rq = Volley.newRequestQueue(ChangePassword.this);

        tvOldPassword = (TextView) findViewById(R.id.tvOldPassword);
        tvNewPassword = (TextView) findViewById(R.id.tvNewPassword);
        tvConfirmPassword = (TextView) findViewById(R.id.tvConfirmPassword);

        etOldPassword = (EditText) findViewById(R.id.etOldPassword);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

        btDone = (Button) findViewById(R.id.btDone);
        btCheckPassword = (Button) findViewById(R.id.btCheckPassword);
        btProceed = (Button) findViewById(R.id.btProceed);

        ibNewPassword = (ImageButton)findViewById(R.id.ibNewPassword);
        ibConfirmPassword = (ImageButton)findViewById(R.id.ibConfirmPassword);

        ibNewPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (!password)
                    etNewPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                else
                    etNewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                password = !password;

            }
        });

        ibConfirmPassword.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                if (!password)
                    etConfirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                else
                    etConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                password = !password;

            }
        });

        etOldPassword.addTextChangedListener(new

                                                     TextWatcher() {
                                                         @Override
                                                         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                         }

                                                         @Override
                                                         public void onTextChanged(CharSequence s, int start, int before, int count) {

                                                         }

                                                         @Override
                                                         public void afterTextChanged(Editable s) {
                                                             oldPassword = etOldPassword.getText().toString();
                                                         }
                                                     });
        etNewPassword.addTextChangedListener(new

                                                     TextWatcher() {
                                                         @Override
                                                         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                         }

                                                         @Override
                                                         public void onTextChanged(CharSequence s, int start, int before, int count) {

                                                         }

                                                         @Override
                                                         public void afterTextChanged(Editable s) {
                                                             newPassword = etNewPassword.getText().toString();


                                                         }
                                                     });

        etConfirmPassword.addTextChangedListener(new

                                                         TextWatcher() {
                                                             @Override
                                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                             }

                                                             @Override
                                                             public void onTextChanged(CharSequence s, int start, int before, int count) {

                                                             }

                                                             @Override
                                                             public void afterTextChanged(Editable s) {
                                                                 confirmPassword = etConfirmPassword.getText().toString();


                                                             }
                                                         });

        etConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (confirmPassword != newPassword) {
                    etConfirmPassword.setError("Passwords don't match");
                    //btDone.setEnabled(false);
                }
            }
        });
        btCheckPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj = new JSONObject();

                try {
                    obj.put("email", email);
                    obj.put("password", oldPassword);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST, BaseUrlConfig.getBaseURL() + "user/login/", obj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(ChangePassword.this, response.get("flag").toString(), Toast.LENGTH_SHORT).show();

                            if (response.get("flag").toString().equals("true")) {
                                Toast.makeText(ChangePassword.this, "The password is correct",
                                        Toast.LENGTH_LONG).show();
                                btDone.setEnabled(true);
                                password = true;
                            } else {
                                Toast.makeText(ChangePassword.this, response.get("old password is wrong").toString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.toString());
                    }
                });

                rq.add(jsonObjectRequest);

            }
        });
        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!newPassword.equals("") && !oldPassword.equals(newPassword)) {
                    JSONObject obj = new JSONObject();

                    try {
                        obj.put("uid", uid);
                        obj.put("password", newPassword);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.POST, BaseUrlConfig.getBaseURL() + "user/change/password/", obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Toast.makeText(ChangePassword.this, response.get("response").toString(), Toast.LENGTH_SHORT).show();

                                if (response.get("response").toString().equals("200")) {
                                    Toast.makeText(ChangePassword.this, "Changing password, click on proceed",
                                            Toast.LENGTH_LONG).show();
                                    btProceed.setEnabled(true);

                                } else {
                                    Toast.makeText(ChangePassword.this, response.get("something went wrong").toString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                        }
                    });

                    rq.add(jsonObjectRequest);
                } else if (oldPassword.equals(newPassword)) {
                    Toast.makeText(ChangePassword.this, "please enter a new password", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ChangePassword.this, "new password not found", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChangePassword.this, HomePageActivity.class);
                startActivity(i);
            }
        });
    }
}
