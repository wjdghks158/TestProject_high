package com.example.unclej.testproject;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by uncleJ on 2018-02-15.
 */
/*
MyFirebaseInstanceIDService
앱을 삭제했다 다시 설치하거나 유효시간이 지나 만료되어 토큰이 계속 바뀐다.
구글입장에서 새로운 유저는 새로운 토큰을 보내준다.
MyFirebaseInstanceIDService는 새로운 토큰을 발급하기 위해서 쓰이는 클래스이다.
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
