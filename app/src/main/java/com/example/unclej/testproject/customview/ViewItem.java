package com.example.unclej.testproject.customview;

import android.os.Message;

/**
 * Created by uncleJ on 2018-01-28.
 */

public interface ViewItem {
    void byPassMessage(Message message);

    void onFinish();

    void onPause();

    void onStart();

    void onUpdate();

    void request();

    void setupViews();
}
