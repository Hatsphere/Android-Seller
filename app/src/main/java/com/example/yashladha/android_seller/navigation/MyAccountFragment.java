package com.example.yashladha.android_seller.navigation;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.yashladha.android_seller.DeactivateActivity;
import com.example.yashladha.android_seller.HomePageActivity;
import com.example.yashladha.android_seller.LoginActivity;
import com.example.yashladha.android_seller.R;

import org.json.JSONException;
import org.json.JSONObject;


public class MyAccountFragment extends Fragment {

    public Context context;
    public Activity activity;
    String name = "", contact = "", email = "", plan = "", address = "";
    private FragmentActivity myContext;
    TextView tvMyName, tvContact, tvEmail, tvDeactivate, tvLogOut, tvPlan, tvResAdd, tvChangePassword;
    ImageView ivMyPic, ivEdit;
    HomePageActivity h1 = new HomePageActivity();
    String type = "", uid = "";
    private RequestQueue rq;

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        this.activity = activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_nav_my_account, container, false);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("myprfs", Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("UID", "");
        email = sharedPreferences.getString("email", "");
        JSONObject json = new JSONObject();
        rq = Volley.newRequestQueue(getContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "http://10.0.2.2:3000/user/seller/Info/all/" + uid,
                json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String code = response.getString("code");
                            JSONObject data = response.getJSONObject("data");
                            contact = data.getString("ContactNo");
                            address = data.getString("Address");
                            name = data.getString("Name");
                            plan = data.getString("PlanChosen");


                        } catch (JSONException e) {
                            e.printStackTrace();
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
        rq.add(request);


        type = sharedPreferences.getString("Plan", "");
        context = rootView.getContext();
        ivMyPic = (ImageView) rootView.findViewById(R.id.ivMyPic);
        ivEdit = (ImageView) rootView.findViewById(R.id.ivEdit);
        tvPlan = (TextView) rootView.findViewById(R.id.tvPlan);
        tvMyName = (TextView) rootView.findViewById(R.id.tvMyName);
        tvContact = (TextView) rootView.findViewById(R.id.tvContact);
        tvEmail = (TextView) rootView.findViewById(R.id.tvEmail);
        tvDeactivate = (TextView) rootView.findViewById(R.id.tvDeactivate);
        tvLogOut = (TextView) rootView.findViewById(R.id.tvLogOut);
        tvResAdd = (TextView) rootView.findViewById(R.id.tvResAdd);
        tvChangePassword = (TextView) rootView.findViewById(R.id.tvChangePassword);
        tvMyName.setText(name);
        tvPlan.setText(plan);
        tvContact.setText(contact);
        tvEmail.setText(email);

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyAccountEditActivity.class);
                startActivity(intent);
            }
        });
        tvPlan.setText(type);
        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragManager = myContext.getFragmentManager();
                FragmentTransaction ft = fragManager.beginTransaction();
                // Create and show the dialog.
                SomeDialog newFragment = new SomeDialog();
                newFragment.show(ft, "Do you want to Log Out");

            }
        });
        tvDeactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DeactivateActivity.class);
                startActivity(i);
            }
        });
        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Account");
    }


    public void logOut() {

    }
}
