package com.microfun.myapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.microfun.myapp.model.ClassicBussiness;
import com.microfun.myapp.model.ClassicModel;

public class PagerFragment extends Fragment implements ClassicBussiness.ClassicListener {

    private static final String TAG = "ViewPager_PagerFragment";

    private Context mContext;
    private TextView tvClassicTitle, tvClassicContent;
    private ImageView imgClassic;
    private String tag = null;
    private static final int index = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("test123456", "onCreate");
        mContext = getActivity();
        Bundle bundle = getArguments();
        tag = bundle.getString("tag");
        ClassicBussiness.getInstance().addListener(this);
        // 获取最新一期
        ClassicBussiness.getInstance().requestLatest();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("test123456", "onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("test123456", "onCreateView");
        View view = inflater.inflate(R.layout.layout_vp_classic, container, false);
        tvClassicContent = view.findViewById(R.id.tv_classic_content);
        tvClassicTitle = view.findViewById(R.id.tv_classic_title);
        imgClassic = view.findViewById(R.id.img_classic);
        return view;
    }

    @Override
    public void onRequestLatestResult(final int code, final ClassicModel classicModel) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(code == 0) {
                    tvClassicTitle.setText(classicModel.getTitle());
                    tvClassicContent.setText(classicModel.getContent());
                } else {
                    Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestNextResult(int code, ClassicModel classicModel) {

    }

    @Override
    public void onRequestPrevious(int code, ClassicModel classicModel) {

    }

    @Override
    public void onDestroy() {
        ClassicBussiness.getInstance().removeListener(this);
        super.onDestroy();
    }
}
