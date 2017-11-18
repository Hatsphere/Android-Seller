package com.example.yashladha.android_seller.navigation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.yashladha.android_seller.LoginActivity;

import org.json.JSONObject;

/**
 * Created by Shiv_PC on 11/17/2017.
 */

public class DeactivateDialog extends DialogFragment {
    Context mContext;
    private RequestQueue rq;
    String uid = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setTitle("Deactivating Account ")
                .setMessage("Do you really want to deactivate your account out!")
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing (will close dialog)
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do something
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("myprfs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        uid = sharedPreferences.getString("UID", "");
                        System.out.println(uid);
                        rq = Volley.newRequestQueue(getActivity().getApplicationContext());
                        MyAccountFragment m = new MyAccountFragment();
                        //m.logOut();
                        JSONObject obj = new JSONObject();
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.GET,
                                "http://10.0.2.2:3000/user/deleteUser/" + uid + "/", obj,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(getActivity(), "The login credentials are correct, Please click on proceed",
                                                Toast.LENGTH_LONG).show();

                                        if (response.toString().equals("200")) {
                                            Log.e("removed user", "remove user");
                                            //Toast.makeText(getActivity(), "Account deactivated", Toast.LENGTH_SHORT).show();
                                        } else {
                                            //Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
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
                        Intent i = new Intent(getContext(), LoginActivity.class);
                        getContext().startActivity(i);
                        getActivity().finish();
                    }
                })
                .create();

    }
}
