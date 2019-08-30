package com.microfun.myapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.microfun.myapp.model.ClassicBussiness;
import com.microfun.myapp.model.ClassicModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClassicFragment extends Fragment implements View.OnClickListener, ClassicBussiness.ClassicListener {

    private static final String TAG = "MyApp_ClassicFragment";

    private Context mContext;
    private ViewPager viewPager = null;
    private ImageButton imgBtnPrevious, imgBtnNext;
    private TextView tvClassicNavigation;
    private List<ClassicModel> classicModelList = new ArrayList<>();
    private List<Integer> integerList = new ArrayList<>();
    private ClassicPagerAdapter adapter = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        ClassicBussiness.getInstance().addListener(this);
        ClassicBussiness.getInstance().requestLatest();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classic, container, false);
        viewPager = view.findViewById(R.id.vp_classic);
        View navigation = view.findViewById(R.id.classic_navigation);
        imgBtnPrevious = navigation.findViewById(R.id.img_btn_previous_classic_navigation);
        imgBtnPrevious.setOnClickListener(this);
        imgBtnNext = navigation.findViewById(R.id.img_btn_next_classic_navigation);
        imgBtnNext.setOnClickListener(this);
        tvClassicNavigation = navigation.findViewById(R.id.tv_classic_navigation);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.img_btn_previous_classic_navigation:
                break;
            case R.id.img_btn_next_classic_navigation:
                break;
        }
    }

    @Override
    public void onDestroy() {
        ClassicBussiness.getInstance().removeListener(this);
        super.onDestroy();
    }

    @Override
    public void onRequestLatestResult(int code, final ClassicModel classicModel) {
        if(code == 0) {
            classicModelList.add(classicModel);
            integerList.add(classicModel.getIndex());
            adapter = new ClassicPagerAdapter(mContext, classicModelList);
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    Log.i("test123456", "position:" + position);
                    if(position == 0 && classicModelList.size()<8 && classicModelList.size() != 1) {
                        ClassicBussiness.getInstance().requestPrevious(classicModelList.get(0).getIndex());
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            if((classicModel.getIndex()-1)>0) {
                ClassicBussiness.getInstance().requestPrevious(classicModel.getIndex());
            }
        } else {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onRequestNextResult(int code, ClassicModel classicModel) {

    }

    @Override
    public void onRequestPrevious(int code, ClassicModel classicModel) {
        if(code == 0) {
            if(classicModelList.contains(classicModel))
                return;
            classicModelList.add(0, classicModel);
            integerList.add(classicModel.getIndex());
            for(ClassicModel model : classicModelList) {
                Log.i("test123456", "aaa " + model.getContent());
            }
            adapter.notifyDataSetChanged();
            if(classicModelList.size() == 2) {
                viewPager.setCurrentItem(classicModelList.size()-1);
            }
        }
    }
}
