package com.microfun.myapp.http;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpManager {

    private HttpManager() {}

    private static HttpManager instance = new HttpManager();
    private OkHttpClient okHttpClient = new OkHttpClient();

    private static final String BASE_URL = "http://bl.7yue.pro/v1%s?appkey=1OKGJE46Ybr7wxwX";

    public static HttpManager getInstance() {
        return instance;
    }

    private final ExecutorService mCachedThreadPool = Executors.newCachedThreadPool();

    public interface ResponseCallback {
        void onResponseSuccessed(String json);
        void onResponseFailed(int code, String msg);
    }

    public void callRequest(boolean isPost, String strParamBody, ArrayMap<String, String> mapParamBody,
                            String url, ArrayMap<String, String> headers, final ResponseCallback callback) {
        Request.Builder builder =new Request.Builder();
        String temp = String.format(BASE_URL, url);
        builder.url(temp);
        if(isPost) {
            if(headers != null && headers.size() != 0) {
                // 添加headers
                for(int i=0; i<headers.size(); i++) {
                    builder.header(headers.keyAt(i), headers.valueAt(i));
                }
            }
            if(!TextUtils.isEmpty(strParamBody) && (mapParamBody == null || mapParamBody.size() == 0)) {
                // 添加string类型post参数
                builder.post(RequestBody.create(strParamBody, null));
            } else if(TextUtils.isEmpty(strParamBody) && mapParamBody != null && mapParamBody.size() != 0) {
                // 添加表单类型post参数
                FormBody.Builder formBuilder = new FormBody.Builder();
                for(int i=0; i<mapParamBody.size(); i++) {
                    formBuilder.add(mapParamBody.keyAt(i), mapParamBody.valueAt(i));
                }
                RequestBody formBody = formBuilder.build();
                builder.post(formBody);
            }
        }
        final Request request = builder.build();
        mCachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                Log.i("ClassicBussiness", "run:" + Thread.currentThread().getName());
                try (Response response = okHttpClient.newCall(request).execute()) {
                    Log.i("ClassicBussiness", "test:" + Thread.currentThread().getName());
                    if(!response.isSuccessful()) {
                        callback.onResponseFailed(response.code(), response.message());
                    } else {
                        callback.onResponseSuccessed(response.body().string());
                    }
                } catch (Exception e) {
                    callback.onResponseFailed(-1, e.getMessage());
                }
            }
        });
    }

}
