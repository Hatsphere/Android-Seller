package com.example.yashladha.android_seller;

import android.util.Log;

import com.example.yashladha.android_seller.helper.BaseUrlConfig;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by yashladha on 1/11/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "InstanceIDService";

    private static final String updateURL = BaseUrlConfig.getBaseURL() + "/updateToken";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }
}
