package com.example.unclej.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class NavigationActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("박정환","NavigationActivity-oncreate");
    }
}
