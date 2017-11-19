package com.example.yashladha.android_seller.helper;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yashladha on 7/11/17.
 * Helper function for getting the uid of the seller
 */

public class HelperDef {

    public static void getUID(String email, final Context context, final EmailHelper callback) {
        final String[] uid = {""};
        final RequestQueue[] rq = {Volley.newRequestQueue(context)};
        JSONObject data = new JSONObject();
        String url = String.format(BaseUrlConfig.getBaseURL() + "user/getuid?email=%1$s", email);
        try {
            data.put("email", email);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                    url,
                    data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            callback.getUID(response, context);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(context.getClass().getSimpleName(), error.getMessage());
                        }
                    }
            );
            rq[0].add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
