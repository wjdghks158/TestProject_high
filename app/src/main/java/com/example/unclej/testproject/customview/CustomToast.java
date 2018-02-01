package com.example.unclej.testproject.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unclej.testproject.R;

/**
 * Created by uncleJ on 2018-01-10.
 */

public class CustomToast {
    public static synchronized void show(Context context, String msg) {
        synchronized (CustomToast.class) {
            Toast toast = new Toast(context);
            View v = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.toast_layout, null);
            ((TextView) v.findViewById(R.id.toast_msg)).setText(msg);
            toast.setView(v);
            toast.setDuration(1);
            toast.setGravity(80, 0, 0);
            toast.setDuration(0);
            toast.show();
        }
    }

}
