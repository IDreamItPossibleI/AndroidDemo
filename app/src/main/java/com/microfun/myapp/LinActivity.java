package com.microfun.myapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class LinActivity extends AppCompatActivity {

    private static final String TAG = "MyApp_LinActivity";

    private BottomNavigationView bottomNavigationView = null;
    private FrameLayout frameLayout = null;
    private SparseArray<Fragment> mFragments = new SparseArray<>(3);
    private int mCurFragmentId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lin);
        initFragment();
        frameLayout = (FrameLayout) findViewById(R.id.frm_container);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switchFragment(item.getItemId());
                return true;
            }
        });

    }

    private void initFragment() {
        mFragments.put(R.id.classic, new ClassicFragment());
        mFragments.put(R.id.book, new BookFragment());
        mFragments.put(R.id.my, new MyFragment());
        showAndHideFragment(R.id.classic, 0);
    }

    private void switchFragment(int id) {
        switch (id) {
            case R.id.classic:
                showAndHideFragment(R.id.classic, mCurFragmentId);
                break;
            case R.id.book:
                showAndHideFragment(R.id.book, mCurFragmentId);
                break;
            case R.id.my:
                showAndHideFragment(R.id.my, mCurFragmentId);
                break;
        }
    }

    private void showAndHideFragment(int willShowId, int willHideId) {
        if(willShowId != willHideId) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if(!mFragments.get(willShowId).isAdded()) {
                transaction.add(R.id.frm_container, mFragments.get(willShowId));
            }
            if(willHideId == 0) {
                // Activity 创建时显示
                transaction.show(mFragments.get(willShowId)).commitAllowingStateLoss();
            } else {
                transaction.show(mFragments.get(willShowId)).hide(mFragments.get(willHideId)).commitAllowingStateLoss();
            }
            mCurFragmentId = willShowId;
        }
    }
}
