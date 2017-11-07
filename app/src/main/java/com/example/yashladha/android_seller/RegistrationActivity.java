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
import com.example.yashladha.android_seller.helper.EmailHelper;
import com.example.yashladha.android_seller.helper.HelperDef;

import org.json.JSONException;
import org.json.JSONObject;


public class RegistrationActivity extends AppCompatActivity {
    public static final String TITLE = "Register";
    TextView tvName, tvPassword, tvLogin;
    EditText etPassword, etEmail;
    boolean ans2 = false;
    Button btLogin, btFacebook, btGoogle, btCheckEmail;
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
        btCheckEmail = (Button) findViewById(R.id.btCheckEmail);
        rq = Volley.newRequestQueue(RegistrationActivity.this);

        btGoogle = (Button) findViewById(R.id.btGoogle);
        ibPassword = (ImageButton) findViewById(R.id.ibPassword);
        btRegister = btLogin;
        ans2 = false;


        btCheckEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj2 = new JSONObject();
                try {
                    obj2.put("email", email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(
                        Request.Method.POST, "http://10.0.2.2:3000/user/check/email/", obj2, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("email check", response.toString());
                            if (response.get("code").toString().equals("201")) {
                                Toast.makeText(RegistrationActivity.this, "Email already used in registration", Toast.LENGTH_LONG).show();
                            } else {
                                ans2 = true;
                                Toast.makeText(RegistrationActivity.this, "Email not used in registration", Toast.LENGTH_LONG).show();
                                btRegister.setEnabled(true);
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
                rq.add(jsonObjectRequest2);

            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tempEmail = email;
                boolean ans = validateUserName() && validatePassword() && ans2;
                if (ans) {
                    final JSONObject obj = new JSONObject();
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
                                if (response.get("response").toString().equals("200")) {
                                    UID_i = response.getString("uid");
                                    obj.put("uid", response.get("uid"));
                                    JsonObjectRequest dataPushRequest = new JsonObjectRequest(
                                            Request.Method.POST,
                                            "http://10.0.2.2:3000/user/push/seller",
                                            obj,
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    try {
                                                        if (response.get("response").toString().equals("200")) {
                                                            Toast.makeText(RegistrationActivity.this, response.get("response").toString(), Toast.LENGTH_SHORT).show();
                                                            SavePreference();
                                                            btLogin.setEnabled(false);
                                                            Toast.makeText(RegistrationActivity.this, UID_i, Toast.LENGTH_SHORT).show();
                                                            startIntent();
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Log.e("onErrorResponse", error.getMessage());
                                                }
                                            }
                                    );
                                    rq.add(dataPushRequest);
                                } else {
                                    Log.d("500 res", "Response catches " + tempEmail);
                                    HelperDef.getUID(tempEmail, RegistrationActivity.this, new EmailHelper() {
                                        @Override
                                        public void getUID(JSONObject res, Context context) {
                                            try {
                                                Log.d("Response", res.toString());
                                                UID_i = res.getString("uid");
                                                Log.d("UID", UID_i);
                                                SavePreference();
                                                startIntent();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
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
                email = etEmail.getText().toString().trim();
            }
        });
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etEmail.getText().toString().trim().length() < 5) {
                        etEmail.setError("Minimum length should be 5 characters");
                    } else {
                        etEmail.setError(null);
                    }
                }
            }
        });


        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etPassword.getText().toString().trim().length() < 8) {
                        etPassword.setError("Minimum length should be 8 characters");
                    } else {
                        etPassword.setError(null);
                    }
                }
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

    private void startIntent() {
        Intent i = new Intent(RegistrationActivity.this, RegisterActivity_2.class);
        startActivity(i);
    }

    private void SavePreference() {
        SharedPreferences.Editor editor = getSharedPreferences("myprfs", MODE_PRIVATE).edit();
        editor.putString("UID", UID_i);
        editor.commit();
        editor.apply();
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