package com.example.yashladha.android_seller.classes;

/**
 * Created by dell pc on 30-10-2017.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAccountExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> myAddress = new ArrayList<String>();
        myAddress.add("Gandhinagar - 226024");


        List<String> changePassword = new ArrayList<String>();
        changePassword.add("Brazil");


        expandableListDetail.put("My Address", myAddress);
        expandableListDetail.put("Change Password", changePassword);
        return expandableListDetail;
    }
}