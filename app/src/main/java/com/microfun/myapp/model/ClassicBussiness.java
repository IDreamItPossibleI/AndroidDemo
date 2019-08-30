package com.microfun.myapp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.microfun.myapp.http.HttpManager;

import java.util.LinkedList;
import java.util.List;

public class ClassicBussiness {

    private static final String TAG = "ClassicBussiness";

    private static final String LATEEST_URL = "/classic/latest";
    private static final String NEXT_URL = "/classic/%d/next";
    private static final String PREVIOUS_URL = "/classic/%d/previous";

    private static final int REQUEST_LATEST = 100;
    private static final int REQUEST_NEXT = 101;
    private static final int REQUEST_PREVIOUS = 102;

    private ClassicModel latestModel = null;
    private ClassicModel nextModel = null;
    private ClassicModel previousModel = null;
    private Gson gson = new Gson();

    public interface ClassicListener {
        void onRequestLatestResult(int code, ClassicModel classicModel);
        void onRequestNextResult(int code, ClassicModel classicModel);
        void onRequestPrevious(int code, ClassicModel classicModel);
    }

    private static final ClassicBussiness instance = new ClassicBussiness();

    private List<ClassicListener> mListener = new LinkedList<>();

    public static ClassicBussiness getInstance() {
        return instance;
    }

    public void addListener(ClassicListener listener) {
        if(listener == null) {
            return;
        }
        synchronized (mListener) {
            if(!mListener.contains(listener)) {
                mListener.add(listener);
            }
        }
    }

    public void removeListener(ClassicListener listener) {
        if(listener == null) {
            return;
        }
        synchronized (mListener) {
            mListener.remove(listener);
        }
    }

    public void notifyListener(int requestType, int code, ClassicModel classicModel) {
        for(ClassicListener listener : mListener) {
            switch (requestType) {
                case REQUEST_LATEST:
                    listener.onRequestLatestResult(code, classicModel);
                    break;
                case REQUEST_NEXT:
                    listener.onRequestNextResult(code, classicModel);
                    break;
                case REQUEST_PREVIOUS:
                    listener.onRequestPrevious(code, classicModel);
                    break;
            }
        }
    }

    public void requestLatest() {
        HttpManager.getInstance().callRequest(false, null, null,
                LATEEST_URL, null, new HttpManager.ResponseCallback() {
                    @Override
                    public void onResponseSuccessed(String json) {
                        latestModel = gson.fromJson(json, ClassicModel.class);
                        if(latestModel != null) {
                            notifyListener(REQUEST_LATEST, 0, latestModel);
                        } else {
                            notifyListener(REQUEST_LATEST, 1, null);
                        }
                        Log.i(TAG, json + Thread.currentThread().getName());
                    }

                    @Override
                    public void onResponseFailed(int code, String msg) {
                        notifyListener(REQUEST_LATEST, code, null);
                        Log.i(TAG, code+msg);
                    }
                });
    }

    public void requestNext(int index) {
        String url_next = String.format(NEXT_URL, index);
        HttpManager.getInstance().callRequest(false, null, null,
                url_next, null, new HttpManager.ResponseCallback() {
                    @Override
                    public void onResponseSuccessed(String json) {
                        nextModel = gson.fromJson(json, ClassicModel.class);
                        if(nextModel != null) {
                            notifyListener(REQUEST_NEXT, 0, nextModel);
                        } else {
                            notifyListener(REQUEST_NEXT, 1, null);
                        }
                        Log.i(TAG, json + Thread.currentThread().getName());
                    }

                    @Override
                    public void onResponseFailed(int code, String msg) {
                        notifyListener(REQUEST_NEXT, code, null);
                        Log.i(TAG, code+msg);
                    }
                });
    }

    public void requestPrevious(int index) {
        String url_previous = String.format(PREVIOUS_URL, index);
        HttpManager.getInstance().callRequest(false, null, null,
                url_previous, null, new HttpManager.ResponseCallback() {
                    @Override
                    public void onResponseSuccessed(String json) {
                        previousModel = gson.fromJson(json, ClassicModel.class);
                        if(previousModel != null) {
                            notifyListener(REQUEST_PREVIOUS, 0, previousModel);
                        } else {
                            notifyListener(REQUEST_PREVIOUS, 1, null);
                        }
                        Log.i(TAG, json + Thread.currentThread().getName());
                    }

                    @Override
                    public void onResponseFailed(int code, String msg) {
                        notifyListener(REQUEST_PREVIOUS, code, null);
                        Log.i(TAG, code+msg);
                    }
                });
    }

}
