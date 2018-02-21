package com.example.unclej.testproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidquery.AQuery;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MenuEditActivity extends BaseActivity {
    Button button;
    Button button_img;
    Button button_Push;
    EditText et;
    ImageView img;
    String mobile = null;
    TelephonyManager tm;
    private AQuery aq = new AQuery(this);
    @Override
    protected int getLayoutResource() {
        return R.layout.menu_edit_main_layout;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        img = (ImageView)findViewById(R.id.menu_img);
        et = (EditText)findViewById(R.id.menu_et);

         button = (Button)findViewById(R.id.menu_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
        button_img = (Button)findViewById(R.id.menu_btn2);
        button_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestImg();
            }
        });

        button_Push = (Button)findViewById(R.id.menu_btn_push);
        button_Push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regId = FirebaseInstanceId.getInstance().getToken();
                Log.d("박정환", regId);

                //등록 ID를 모바일 서버에 전송
                sendToMobileServer(regId);
            }
        });
/*
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if ( tm.getLine1Number() != null ) {
            mobile = tm.getLine1Number();
        }
        else {
            mobile = "01072464117";
        }
        */
       // tm.getDeviceSoftwareVersion();
        request();
    }

    public void sendToMobileServer(final String regId) {
        Log.d("박정환", "sendToMobileServer 호출");
        //https://gongmo.herokuapp.com/process/register
        String mobile = "01072464117";
        Log.d("박정환", "sendToMobileServer 호출");
        String registrationId = regId;
        Log.d("박정환", "sendToMobileServer 호출");
        String registerUrl = "http://172.30.75.60:3000/process/register?mobile="+ mobile + "&registrationId=" + registrationId;
        String test = "https://www.naver.com/";
        StringRequest request = new StringRequest(Request.Method.GET, registerUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("박정환", "onResponse 푸쉬메시지 그거 성공");
                            Log.d("박정환", response);
                            println("onResponse() 호출됨 : " + response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("박정환", "onResponse 푸쉬메시지 그거 실패");
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String>  params = new HashMap<>();

                params.put("mobile", getMobile());
                params.put("registrationId", regId);
                Log.d("박정환","getParams이거 도는거 맞음?");
                return params;
            }
        };

        request.setShouldCache(false);
        Volley.newRequestQueue(getApplicationContext()).add(request);
        println("웹서버에 요청함 : " + registerUrl);
    }


    @SuppressLint("MissingPermission")
    public String getMobile() {
        Log.d("박정환","getMobile 호출1");
        String mobile = null;
        Log.d("박정환","getMobile 호출2");
        //TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Log.d("박정환","getMobile 호출3");

        Log.d("박정환","getMobile 호출4");
        return mobile;
    }

    private void requestImg() {
        String url = "https://ssl.pstatic.net/tveta/libs/1182/1182640/9dec376632c078ca96d2_20180208164940580.jpg";
        aq.id(img).image(url);

    }

    public void request() {
        //String url = et.getText().toString();
        String url_list = "https://gongmo.herokuapp.com/process/listpost";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url_list,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d("박정환", response.toString());
                          //  JSONArray itemsList  = response.getJSONArray("items");
                            int ddd = response.length();
                            Log.d("박정환", String.valueOf(ddd));
                            for(int i=0; i<response.length(); i++) {
                                Log.d("박정환", String.valueOf(i));
                                JSONObject item = (JSONObject)response.get(i);

                                String asd = item.optString("title");
                                Log.d("박정환", asd);
                           }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                       // String id = response.getJSONObject("name");
                       // String recordDate = response.getString("email");

                       // JSONObject distance = response.getJSONObject("phone");



                        //결과물
                       // println(response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러발생함");
                    }
                });
        //큐에다가 넣으면 알아서 전송한다.
        request.setShouldCache(false);
        Volley.newRequestQueue(this).add(request);
        println("요청함");
    }
    public void println(String data) {
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }


}
