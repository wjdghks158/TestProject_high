package com.example.unclej.testproject;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends NavigationActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("박정환", "MainActivity-onCreate");
/**
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        Log.d("박정환","1");
        // 메뉴 선택시 동작을 지정하려면 onNavigationItemSelected 메소드에서 해주면 됩니다.
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                Log.d("박정환","2");
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_attachment:
                        Log.d("박정환","2");
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_item_images:
                        Log.d("박정환","3");
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_item_location:
                        Log.d("박정환","4");
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_sub_menu_item01:
                        Log.d("박정환","5");
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_sub_menu_item02:
                        Log.d("박정환","6");
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
**/
        goMainFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("박정환","7");
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("박정환","8");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Log.d("박정환","9");
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                Log.d("박정환","10");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goMainFragment() {
        MainFragment fragment = MainFragment.createInstance();
        //getSupportFragmentManager을 통해 FragmentManager을 받음 현재 화면에 보이고 있는 fragment를 반환해준다.
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        //replace() 메소드를 통해 동적으로 Fragment 교체하기
       // 교체하는 코드는 다음과 같다.
       // R.id.content_frame에 있는 기존의 Fragment를 제거한 후
       // 두 번째 매개인자인 fragment를 R.id.content_frame에 집어 넣는 기능이다.
        // transaction.replace(R.id.content, fragment, MainFragment.class.getSimpleName());
       // transaction.replace();
        transaction.commitAllowingStateLoss();

    }


}
