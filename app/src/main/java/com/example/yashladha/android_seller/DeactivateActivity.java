package com.example.yashladha.android_seller;

import android.content.Context;
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
import com.example.yashladha.android_seller.navigation.DeactivateDialog;
import com.example.yashladha.android_seller.navigation.MyAccountFragment;

import org.json.JSONObject;

public class DeactivateActivity extends AppCompatActivity {
    Button btYes;
    Button btNo;
    SharedPreferences sharedPreferences;
    private RequestQueue rq;
    String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deactivate);
        btYes = (Button) findViewById(R.id.btYes);
        btNo = (Button) findViewById(R.id.btNo);
        sharedPreferences = getSharedPreferences("myprfs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        btYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid = sharedPreferences.getString("UID", "");
                System.out.println(uid);
                rq = Volley.newRequestQueue(getApplicationContext());
                MyAccountFragment m = new MyAccountFragment();
                //m.logOut();
                JSONObject obj = new JSONObject();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        "http://10.0.2.2:3000/user/deleteUser/" + uid + "/", obj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (response.toString().equals("200")) {
                                    Log.e("removed user", "remove user");
                                    Toast.makeText(DeactivateActivity.this, "Account deactivated", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(DeactivateActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//                        Log.e(rootview.getClass().getSimpleName(), error.getMessage());
                            }
                        }

                );
                rq.add(jsonObjectRequest);

                editor.clear();
                editor.commit();
                Intent i = new Intent(DeactivateActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });
    }
}
