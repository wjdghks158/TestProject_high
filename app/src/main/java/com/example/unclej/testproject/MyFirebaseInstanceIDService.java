package com.example.unclej.testproject;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by uncleJ on 2018-02-15.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyIID";

    @Override
    public void onTokenRefresh() {
        Log.d(TAG, "onTokenRefresh() 호출됨.");

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed Token : " + refreshedToken);
    }

}
