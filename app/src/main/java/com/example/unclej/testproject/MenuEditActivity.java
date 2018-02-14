package com.example.unclej.testproject;

import android.media.Image;
import android.os.Bundle;
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
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuEditActivity extends BaseActivity {
    Button button;
    Button button_img;
    EditText et;
    ImageView img;
    private AQuery aq = new AQuery(this);
    @Override
    protected int getLayoutResource() {
        return R.layout.menu_edit_main_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        img = (ImageView)findViewById(R.id.menu_img);
         et = (EditText)findViewById(R.id.menu_et);


    }

    private void requestImg() {
        String url = "https://ssl.pstatic.net/tveta/libs/1182/1182640/9dec376632c078ca96d2_20180208164940580.jpg";
        aq.id(img).image(url);

    }

    public void request() {
        String url = et.getText().toString();

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
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
