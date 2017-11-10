package com.example.yashladha.android_seller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PlanChoicesActivity extends AppCompatActivity {

    Button btPlatinuum, btSilver, btGold;
    private RequestQueue rq;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_choices);
        btPlatinuum = (Button) findViewById(R.id.btPlatinum);
        btSilver = (Button) findViewById(R.id.btSilver);
        btGold = (Button) findViewById(R.id.btGold);
        rq = Volley.newRequestQueue(PlanChoicesActivity.this);
        final String address = getIntent().getStringExtra("address");
        final String contact = getIntent().getStringExtra("contact");
        final String name = getIntent().getStringExtra("name");
        SharedPreferences myPrefs = getSharedPreferences("myprfs", MODE_PRIVATE);
        uid = myPrefs.getString("UID", "");

        Log.d(getClass().getSimpleName(), uid);

        Toast.makeText(PlanChoicesActivity.this, uid, Toast.LENGTH_LONG).show();
        btPlatinuum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("name", name);
                    obj.put("planChosen", "2");
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
                            Toast.makeText(PlanChoicesActivity.this, response.get("response").toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.toString());
                        Toast.makeText(PlanChoicesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                rq.add(jsonObjectRequest);
                Intent i = new Intent(PlanChoicesActivity.this, PlanPaymentActivity.class);
                SharedPreferences.Editor editor = getSharedPreferences("myprfs", MODE_PRIVATE).edit();
                editor.putString("Plan", "2");
                editor.apply();
                i.putExtra("planChosen", "Platinum");
                startActivity(i);

            }
        });
        btSilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj = new JSONObject();

                try {
                    obj.put("name", name);
                    obj.put("planChosen", "0");
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
                            Toast.makeText(PlanChoicesActivity.this, response.get("response").toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.toString());
                        Toast.makeText(PlanChoicesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                rq.add(jsonObjectRequest);
                Intent i = new Intent(PlanChoicesActivity.this, PlanPaymentActivity.class);
                SharedPreferences.Editor editor = getSharedPreferences("myprfs", MODE_PRIVATE).edit();
                editor.putString("Plan", "0");
                editor.apply();
                i.putExtra("planChosen", "Silver");
                startActivity(i);


            }
        });
        btGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj = new JSONObject();

                try {
                    obj.put("name", name);
                    obj.put("planChosen", "1");
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
                            Toast.makeText(PlanChoicesActivity.this, response.get("response").toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.toString());
                        Toast.makeText(PlanChoicesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                rq.add(jsonObjectRequest);
                Intent i = new Intent(PlanChoicesActivity.this, PlanPaymentActivity.class);
                SharedPreferences.Editor editor = getSharedPreferences("myprfs", MODE_PRIVATE).edit();
                editor.putString("Plan", "1");
                editor.commit();
                editor.apply();
                i.putExtra("planChosen", "Gold");
                startActivity(i);


            }
        });
    }
}
