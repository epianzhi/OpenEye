package com.qj.kaiyan.base;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;


/**
 * @Description: 延迟加载fragment (实现延迟加载和绑定、解绑ButterKnife、绑定、解绑EventBus)
 * @author: Cts
 * @date: 2016-11-21 14:11
 */

public abstract class LazyLoadFragment extends Fragment {
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    public Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initListener();

    }

    protected abstract void initView();

    protected   abstract void initData();

    protected abstract void initListener();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {
    }


    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(getActivity(), targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}