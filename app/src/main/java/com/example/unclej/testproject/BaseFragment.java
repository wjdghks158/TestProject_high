package com.example.unclej.testproject;

import android.arch.lifecycle.Lifecycle;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by uncleJ on 2018-01-23.
 */

public abstract class BaseFragment extends Fragment implements FragmentManager.OnBackStackChangedListener {

    protected abstract int getLayoutResource();

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResource(), null, false);
    }
    public boolean onBackPressed() {
        return false;
    }


}
