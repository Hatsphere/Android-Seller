package com.example.yashladha.android_seller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.yashladha.android_seller.helper.BaseUrlConfig;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class PlanChoicesActivity extends AppCompatActivity {

    private Button btPlatinuum, btSilver, btGold;
    private RequestQueue rq;
    private String uid;
    private Context mContext;
    private String profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_choices);
        btPlatinuum = (Button) findViewById(R.id.btPlatinum);
        btSilver = (Button) findViewById(R.id.btSilver);
        btGold = (Button) findViewById(R.id.btGold);
        mContext = getBaseContext();
        profileImage = getIntent().getStringExtra("profileImage");
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
                    obj.put("planId", 2);
                    obj.put("address", address);
                    obj.put("contactNo", contact);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = sellerPushRequest(obj);

                rq.add(jsonObjectRequest);
                Intent i = new Intent(PlanChoicesActivity.this, PlanPaymentActivity.class);
                SharedPreferences.Editor editor = getSharedPreferences("myprfs", MODE_PRIVATE).edit();
                editor.putString("Plan", "2");
                editor.commit();
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
                    obj.put("planId", 0);
                    obj.put("address", address);
                    obj.put("contactNo", contact);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = sellerPushRequest(obj);

                rq.add(jsonObjectRequest);
                Intent i = new Intent(PlanChoicesActivity.this, PlanPaymentActivity.class);
                SharedPreferences.Editor editor = getSharedPreferences("myprfs", MODE_PRIVATE).edit();
                editor.putString("Plan", "0");
                editor.commit();
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
                    obj.put("planId", 1);
                    obj.put("address", address);
                    obj.put("contactNo", contact);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = sellerPushRequest(obj);

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

    @NonNull
    private JsonObjectRequest sellerPushRequest(JSONObject obj) {
        return new JsonObjectRequest(
                Request.Method.POST, "http://10.0.2.2:3000/user/profile/" + uid, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    uploadProfile();
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
    }

    private void uploadProfile() {
        Ion.with(mContext)
                .load(BaseUrlConfig.getBaseURL() + "user/seller/profile/" + uid)
                .setMultipartFile("UID", new File(profileImage))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Log.d("onCompleted: ", result.toString());
                    }
                });
    }
}
