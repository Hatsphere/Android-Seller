package com.example.yashladha.android_seller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.yashladha.android_seller.navigation.NavigationActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class TrialActivity extends AppCompatActivity {

    CheckBox cbYes;
    RadioButton rb;
    private RequestQueue rq;
    Button btSubmit;
    String uid;
    Boolean choise = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial2);
        cbYes = (CheckBox) findViewById(R.id.cbYes);
        btSubmit = (Button) findViewById(R.id.btProceed);
        rq = Volley.newRequestQueue(TrialActivity.this);
        final String address = getIntent().getStringExtra("address");
        final String contact = getIntent().getStringExtra("contact");
        final String name = getIntent().getStringExtra("name");
        SharedPreferences myPrefs = getSharedPreferences("myprfs", MODE_PRIVATE);
        uid = myPrefs.getString("UID", "");

        btSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (choise == true) {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("name", name);
                        obj.put("planChosen", -1);
                        obj.put("address", address);
                        obj.put("contactNo", contact);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.POST, "http://10.0.2.2:3000/user/profile/" + uid, obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Toast.makeText(TrialActivity.this, response.get("response").toString(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            Toast.makeText(TrialActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    rq.add(jsonObjectRequest);
                    Intent i = new Intent(TrialActivity.this, PlanChosenActivity.class);
                    i.putExtra("planChosen", "Trial");
                    startActivity(i);

                } else {
                    Intent i = new Intent(TrialActivity.this, PlanChoicesActivity.class);
                    i.putExtra("address", address);
                    i.putExtra("contact", contact);
                    i.putExtra("name", name);
                    startActivity(i);
                }
            }
        });
        cbYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbYes.isChecked()) {
                    choise = true;

                } else {
                    choise = false;
                }

            }
        });

    }
}
