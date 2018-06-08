package com.qj.kaiyan.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qj.kaiyan.R;
import com.qj.kaiyan.adapters.MyPagerAdapter;
import com.qj.kaiyan.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment {


    @BindView(R.id.hot_tablayout)
    TabLayout hotTablayout;
    @BindView(R.id.hot_viewpager)
    ViewPager hotViewpager;
    Unbinder unbinder;
    private String[] stringRank;

    List<Fragment> fragmentList=new ArrayList<>();
    Fragment weekFragment,monthFragment,totalFragment;
    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {

        weekFragment=new RankFragment("weekly");
        monthFragment=new RankFragment("monthly");
        totalFragment=new RankFragment("historical");

        fragmentList.add(weekFragment);
        fragmentList.add(monthFragment);
        fragmentList.add(totalFragment);

        stringRank = getResources().getStringArray(R.array.hot);

        hotViewpager.setOffscreenPageLimit(3);
        hotViewpager.setAdapter(new MyPagerAdapter(getChildFragmentManager(),fragmentList,stringRank));
        hotTablayout.setupWithViewPager(hotViewpager);
    }

    @Override
    protected void initListener() {

        hotTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                hotViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
