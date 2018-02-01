package com.example.unclej.testproject.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by uncleJ on 2018-01-27.
 */

public class WrappedDrawerLayout extends DrawerLayout {
    public WrappedDrawerLayout(@NonNull Context context) {
        super(context);
    }
    public WrappedDrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrappedDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
