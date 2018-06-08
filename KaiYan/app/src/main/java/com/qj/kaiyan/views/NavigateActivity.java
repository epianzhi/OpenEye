package com.qj.kaiyan.views;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qj.kaiyan.R;
import com.qj.kaiyan.base.BaseActitvity;
import com.qj.kaiyan.fragments.navigate.FirstFragment;
import com.qj.kaiyan.fragments.navigate.ThreeFragment;
import com.qj.kaiyan.fragments.navigate.TwoFragment;
import com.qj.kaiyan.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class NavigateActivity extends BaseActitvity {


    List<Fragment> fragmentList=new ArrayList<>();
    @BindView(R.id.navigate_viewpager)
    ViewPager navigateViewpager;
    @BindView(R.id.navigate_dot)
    CircleIndicator navigateDot;
    @BindView(R.id.navigate_enter)
    ImageView navigateEnter;

    @Override
    protected void initView() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_navigate);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

        fragmentList.add(new FirstFragment());
        fragmentList.add(new TwoFragment());
        fragmentList.add(new ThreeFragment());

        navigateViewpager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));
        navigateViewpager.setOffscreenPageLimit(2);
        navigateViewpager.setCurrentItem(0);

        navigateDot.setViewPager(navigateViewpager);
        SpUtils.put(this,"isfirst",false);

        navigateViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Log.d("flag", "onPageSelected: "+position);
                if ((position == 2)) {
                    navigateEnter.setVisibility(View.VISIBLE);
                } else {
                    navigateEnter.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initListener() {

    }



    @OnClick(R.id.navigate_enter)
    public void onViewClicked() {
        Intent intent=new Intent(NavigateActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * viewpager适配器
     */
    class MyFrageStatePagerAdapter extends FragmentPagerAdapter {

        public MyFrageStatePagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
