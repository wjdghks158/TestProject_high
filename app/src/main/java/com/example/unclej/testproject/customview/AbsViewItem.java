package com.example.unclej.testproject.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by uncleJ on 2018-01-28.
 */

public abstract class AbsViewItem extends RelativeLayout implements ViewItem {
    public AbsViewItem(Context context) {
        super(context);
    }

    public AbsViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
