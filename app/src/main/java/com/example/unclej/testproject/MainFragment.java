package com.example.unclej.testproject;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.unclej.testproject.response.MainMenuItemData;
import com.example.unclej.testproject.util.WeakReferenceHandler;

import java.util.ArrayList;

/**
 * Created by uncleJ on 2018-01-23.
 */

public class MainFragment extends BaseFragment implements View.OnClickListener{
    private WeakReferenceHandler<MainFragment> mUIHandler;
    private Button mSearchBtn;
    private LinearLayout mTabBarContainer;
    private LinearLayout mTabLayout;
    private GridView mGridView;
    ImageButton navi_btn;
    private long mLastClickTime = 0;

    @Override
    protected int getLayoutResource() {
        Log.d("박정환","MainFragment-getLayoutResource");
        return R.layout.fragment_main_layout;
    }

    //Layout을 inflater을하여 View작업을 하는곳이다.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("박정환","MainFragment-onCreateView");
        View root = super.onCreateView(inflater, container, savedInstanceState);
        this.mUIHandler = new WeakReferenceHandler(this);
        getActivity().getSupportFragmentManager().addOnBackStackChangedListener(this);
        initView(root);

        return root;

    }

    private void initView(View view) {
        Log.d("박정환","MainFragment-initView");
        this.mSearchBtn = (Button)view.findViewById(R.id.searchbtn);
        this.mSearchBtn.setOnClickListener(this);
        this.mTabBarContainer = (LinearLayout)view.findViewById(R.id.tabbar_container);
        this.mTabLayout = (LinearLayout)view.findViewById(R.id.tabLayout);
        this.mGridView = (GridView)view.findViewById(R.id.gridview1);
        this.navi_btn = (ImageButton)view.findViewById(R.id.menuLeft);
        this.navi_btn.setOnClickListener(this);
        setMymenu();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("박정환","MainFragment-onCreate");
        int id=0;
        CharSequence s = "전달 받은 값은 ";
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null) {
            s = "error";
        }
        else {
            id = extras.getInt("notificationId");
        }
       // NotificationManager nm =
         //       (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        //노티피케이션 제거
        //nm.cancel(id);


    }

    private void setMymenu() {
        int myMenuSize = 0;
        ArrayList<MainMenuItemData> myMenuList = new ArrayList();

        if (myMenuSize < 12) {
            MainMenuItemData item1 = new MainMenuItemData();
            item1.MENUNM = "전혜원";
            MainMenuItemData item2 = new MainMenuItemData();
            item2.MENUNM = "최민영";
            MainMenuItemData item3 = new MainMenuItemData();
            item3.MENUNM = "이유림";


            myMenuList.add(item1);
            myMenuList.add(item2);
            myMenuList.add(item3);

        }

              //  this.mGridView.setLayoutParams(new LinearLayout.LayoutParams(-1, (this.coloumSize * height) + (this.coloumSize * extraHeight)));
        this.mGridView.setAdapter(new MyMenuGridViewAdapter(myMenuList));

        }


    @Override
    public void onStart() {
        super.onStart();
        Log.d("박정환","onStart이것도 도나?");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("박정환","onResume이것도 도나?");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("박정환","onPause이것도 도나?");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("박정환","onStop이것도 도나?");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("박정환","onDestroy이것도 도나?");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("박정환","onDetach이것도 도나?");
    }

    // static으로 MainFragment 생성
    public static MainFragment createInstance() {
        return new MainFragment();
    }

    @Override
    public void onBackStackChanged() {
        updateToolbarVisibility();

    }

    private void updateToolbarVisibility() {
        this.mUIHandler.sendEmptyMessage(2000);
    }

    @Override
    public void onClick(View view) {
        Log.d("박정환","클릭함");
        switch (view.getId()) {
            case R.id.searchbtn :
                Log.d("박정환","searchbtn누름");
                return;
            case R.id.menuLeft :
                Log.d("박정환", "네비 버튼 맞네");
                if (!doubleClickRestric()) {
                  //  ((MainActivity) getActivity()).openDrawers(GravityCompat.START);
                    return;
                }

           // case R.id.
        }

    }

    private class GridViewChildHolder {
        public TextView menuName;
        LinearLayout menuParent;
        ImageView plusIcon;
    }

    private class MyMenuGridViewAdapter extends BaseAdapter {
        private ArrayList<MainMenuItemData> mData;

        public MyMenuGridViewAdapter(ArrayList<MainMenuItemData> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            Log.d("박정환","MyMenuGridViewAdapter-getCount :");
            Log.d("박정환", String.valueOf(this.mData.size()));
            //this.mData.size();
            return this.mData.size();
        }

        @Override
        public Object getItem(int i) {
            Log.d("박정환","MyMenuGridViewAdapter-getItem");
            return this.mData.get(i);
        }

        @Override
        public long getItemId(int i) {
            Log.d("박정환","MyMenuGridViewAdapter-getItemId");
            return (long)i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            Log.d("박정환","MyMenuGridViewAdapter-getView");
            GridViewChildHolder gridHolder;
            View v = convertView;
            final MainMenuItemData mi = (MainMenuItemData) this.mData.get(position);
            if (v == null) {
                Log.d("박정환","MyMenuGridViewAdapter-getView v가 null일때");
                v = LayoutInflater.from(MainFragment.this.getActivity()).inflate(R.layout.my_menu_item_layout, null);
                gridHolder = new GridViewChildHolder();
                gridHolder.menuName = (TextView) v.findViewById(R.id.my_menu_name);
                gridHolder.menuParent = (LinearLayout) v.findViewById(R.id.my_menu_parent);
                //gridHolder.plusIcon = (ImageView) UiUtilities.getView(v, R.id.plus_img);
                //gridHolder.menuParent.setLayoutParams(new AbsListView.LayoutParams(-1, AppUtils.dpToPx(MainFragment.this.getActivity(), 39)));
                v.setTag(gridHolder);
            } else {
                gridHolder = (GridViewChildHolder) v.getTag();
            }
            if (mi.MENUNM.isEmpty()) {
              //  gridHolder.menuParent.setVisibility(View.GONE);
            } else {
               // gridHolder.menuParent.setVisibility(View.VISIBLE);
                if (mi.MENUNM.equalsIgnoreCase("추가")) {
//                    gridHolder.menuName.setTextColor(-6710887);
              //      gridHolder.plusIcon.setVisibility(View.VISIBLE);
                }
            }
            gridHolder.menuName.setText(mi.MENUNM);
            gridHolder.menuParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("박정환",mi.MENUNM);
                    MainFragment.this.startActivity(new Intent(MainFragment.this.getActivity(), MenuEditActivity.class));
                }
            });
            /*
            gridHolder.menuParent.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (!mi.MENUNM.isEmpty()) {
                        if (MainFragment.this.getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            MainFragment.this.getActivity().getSupportFragmentManager().popBackStack();
                        }
                        if (mi.MENULINK.contains("/job/CustomJobList.asp")) {
                            boolean isLogin = false;

                        }
                    }
                }
            });
            */
            return v;

        }
    }

    private boolean doubleClickRestric() {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return true;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        return false;
    }
}
