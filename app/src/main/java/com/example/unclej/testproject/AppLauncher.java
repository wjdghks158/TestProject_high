package com.example.unclej.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

/**
 * Created by uncleJ on 2018-01-10.
 */

public class AppLauncher extends BaseActivity implements View.OnClickListener {
    int SPLASH_TIME = 2000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gomain();
            }
        },SPLASH_TIME);

    }

    @Override
    public void onClick(View view) {
        Log.d("박정환", "AppLauncher-onClick");

    }

    @Override
    protected int getLayoutResource() {

        return R.layout.main;
    }

    private void gomain() {
        Intent inent = new Intent(this, MainActivity.class);
        this.finish();
        startActivity(inent);
    }
}
