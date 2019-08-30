package com.microfun.myapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.microfun.myapp.model.ClassicModel;

import java.util.LinkedList;
import java.util.List;


public class ClassicPagerAdapter extends PagerAdapter {

    private Context mContext = null;
    private List<ClassicModel> datas = new LinkedList<>();

    public ClassicPagerAdapter(Context context, List<ClassicModel> list) {
        this.datas = list;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        Log.i("test123456", "count:" +datas.size());
        return datas.size();
    }

//    public void setDatas(List<ClassicModel> list) {
//        this.datas.clear();
//        this.datas.addAll(list);
//    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(datas.size() == 0)
            return null;
        ClassicModel classicModel = datas.get(position);
        ViewGroup view = null;
        if(classicModel != null) {
            view = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.layout_vp_classic, null);
            TextView tvClassicTitle = view.findViewById(R.id.tv_classic_title);
            tvClassicTitle.setText(classicModel.getTitle());
            TextView tvClassicContent = view.findViewById(R.id.tv_classic_content);
            tvClassicContent.setText(classicModel.getContent());
            container.addView(view);
            Log.i("test123456", classicModel.getContent());
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeViewAt(position);
    }
}
