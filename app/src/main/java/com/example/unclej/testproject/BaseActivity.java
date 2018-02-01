package com.example.unclej.testproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.example.unclej.testproject.customview.CustomToast;

import java.util.ArrayList;

/**
 * Created by uncleJ on 2018-01-10.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static ArrayList<Activity> activitiesList = new ArrayList();
    private boolean mIsRunning = false;
    private ProgressDialog mProgressDialog;

    //BaseActivity 클래스 상속 받은 엑티비티가 리소스 박아야함
    protected abstract int getLayoutResource();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        Log.d("박정환", "BaseActivity-onCreate");
        this.mProgressDialog = new ProgressDialog(this);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mIsRunning = false;

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mIsRunning = false;

    }


    @Override
    protected void onResume() {
        super.onResume();
        this.mIsRunning = true;

    }


    public void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(color);
        }
    }
    protected void showProgressDialog() {
        showProgressDialog(getResources().getString(R.string.msg_waiting));
    }
    protected void showProgressDialog(String message) {
        if (message != null) {
            this.mProgressDialog.setMessage(message);
        }
        this.mProgressDialog.show();
    }

    protected void clearProgressDialog() {
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    protected void showToast(String msg) {
        CustomToast.show(this, msg);
    }







}
