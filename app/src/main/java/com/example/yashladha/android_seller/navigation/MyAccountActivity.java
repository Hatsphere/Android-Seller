package com.example.yashladha.android_seller.navigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yashladha.android_seller.R;
import com.example.yashladha.android_seller.classes.MyAccountCustomExpandableListAdapter;
import com.example.yashladha.android_seller.classes.MyAccountExpandableListDataPump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAccountActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    TextView tvMyName,tvContact,tvEmail,tvDeactivate,tvLogOut;
    ImageView ivMyPic,ivEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_nav_my_account);

        ivMyPic = (ImageView)findViewById(R.id.ivMyPic);
        ivEdit = (ImageView)findViewById(R.id.ivEdit);

        tvMyName = (TextView)findViewById(R.id.tvMyName);
        tvContact = (TextView)findViewById(R.id.tvContact);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvDeactivate = (TextView)findViewById(R.id.tvDeactivate);
        tvLogOut = (TextView)findViewById(R.id.tvLogOut);

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this,MyAccountEditActivity.class);
                startActivity(intent);
            }
        });

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = MyAccountExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new MyAccountCustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }



}

