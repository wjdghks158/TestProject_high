package com.example.unclej.testproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {

    TextView textView;
    EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = (TextView) findViewById(R.id.textView);
        editText3 = (EditText) findViewById(R.id.editText3);

    }

    public void onButton3Clicked(View v) {
        String regId = FirebaseInstanceId.getInstance().getToken();
        Log.d("박정환", "버튼누르고 regid : " +regId);
        sendToMobileServer(regId);
    }

    public void sendToMobileServer(final String regId) {
        String registerUrl = editText3.getText().toString();

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
