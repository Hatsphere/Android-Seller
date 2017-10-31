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
        myAddress.add("India");
        myAddress.add("Pakistan");
        myAddress.add("Australia");
        myAddress.add("England");
        myAddress.add("South Africa");

        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");

        List<String> basketball = new ArrayList<String>();
        basketball.add("United States");
        basketball.add("Spain");
        basketball.add("Argentina");
        basketball.add("France");
        basketball.add("Russia");

        expandableListDetail.put("CRICKET TEAMS", myAddress);
        expandableListDetail.put("FOOTBALL TEAMS", football);
        expandableListDetail.put("BASKETBALL TEAMS", basketball);
        return expandableListDetail;
    }
}