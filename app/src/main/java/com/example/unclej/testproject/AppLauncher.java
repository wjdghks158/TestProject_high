package com.example.unclej.testproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.unclej.testproject.response.DeviceInfo;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

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
        String url = "http://gongmo.herokuapp.com/process/adddevice";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            // 등록이 안돼 있다면 등록 하자
                            if(response.equals("")) {
                                String regId = FirebaseInstanceId.getInstance().getToken();
                                sendToMobileServer(regId);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("박정환","에러날때는 어케하지???");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String>  params = new HashMap<>();

                DeviceInfo device = getDeviceInfo();
                params.put("mobile", device.getMobile());
                params.put("osVersion", device.getOsVersion());
                params.put("model", device.getModel());
                params.put("display", device.getDisplay());
                params.put("manufacturer", device.getManufacturer());
                params.put("macAddress", device.getMacAddress());

                return params;
            }
        };

        request.setShouldCache(false);
        Volley.newRequestQueue(this).add(request);
        Intent inent = new Intent(this, MainActivity.class);
        this.finish();
        startActivity(inent);
    }


    @SuppressLint("MissingPermission")
    public DeviceInfo getDeviceInfo() {
        DeviceInfo device = null;

        // 1. mobile
        String mobile = null;
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if ( telephonyManager.getLine1Number() != null ) {
            mobile = telephonyManager.getLine1Number();
        }

        // 2. osVersion
        String osVersion = Build.VERSION.RELEASE;

        // 3. model
        String model = Build.MODEL;

        // 4. display
        String display = getDisplay(this);

        // 5. manufacturer
        String manufacturer = Build.MANUFACTURER;

        // 6. macAddress
        String macAddress = getMacAddress(this);

        device = new DeviceInfo(mobile, osVersion, model, display, manufacturer, macAddress);

        return device;
    }

    private static String getDisplay(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;

        return deviceWidth + "x" + deviceHeight;
    }

    /**
     * get WiFi MAC address
     */
    private static String getMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();

        return info.getMacAddress();
    }

    public void sendToMobileServer(final String regId) {
        String registerUrl = "https://gongmo.herokuapp.com/process/register";

        StringRequest request = new StringRequest(Request.Method.POST, registerUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("박정환","onResponse() 호출됨 : "+response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("박정환","에러남");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String>  params = new HashMap<>();

                params.put("mobile", getMobile());
                params.put("registrationId", regId);

                return params;
            }
        };

        request.setShouldCache(false);
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }

    @SuppressLint("MissingPermission")
    public String getMobile() {
        String mobile = null;
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if ( telephonyManager.getLine1Number() != null ) {
            mobile = telephonyManager.getLine1Number();
        }

        return mobile;
    }



}
