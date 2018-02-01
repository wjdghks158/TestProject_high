package com.example.unclej.testproject.util;


import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;


public class WeakReferenceHandler<T> extends Handler {
    WeakReference<T> weakReferenceHandler;

    public WeakReferenceHandler(T t) {
        weakReferenceHandler = new WeakReference<T>(t);
    }
}