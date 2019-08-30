package com.microfun.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.microfun.myapp.http.HttpManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private static final String baseUrl = "http://bl.7yue.pro/v1";
    private static final String latestUrl = "/classic/latest";
    private static final String keyUrl = "?appkey=1OKGJE46Ybr7wxwX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn_test_http);

        final ArrayMap<String, String> headers = new ArrayMap<>();
//        headers.put("appkey", "1OKGJE46Ybr7wxwX");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpManager.getInstance().callRequest(false, null, null, baseUrl + latestUrl + keyUrl,
                        null, new HttpManager.ResponseCallback() {
                            @Override
                            public void onResponseSuccessed(String json) {
                                Log.i(TAG, json);
                            }

                            @Override
                            public void onResponseFailed(int code, String msg) {
                                Log.i(TAG, code + ", " + msg);
                            }
                        });
            }
        });

        Button btnLin = findViewById(R.id.btn_lin);
        btnLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LinActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("test123456", "onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("test123456", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("test123456", "onDestroy");
    }
}
