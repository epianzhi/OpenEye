package com.qj.kaiyan.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.Stack;

public abstract class BaseActitvity extends FragmentActivity {

    private static Stack<Activity> activitiesList=new Stack<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitiesList.push(this);
        initView();
        initData();
        initListener();
    }
    protected abstract void initView();

    protected   abstract void initData();

    protected abstract void initListener();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activitiesList.contains(this)){
            activitiesList.remove(this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }
    public void openActivityAndCloseThis(Class<?> targetActivityClass) {
        openActivity(targetActivityClass);
        this.finish();
    }
    public void showShort(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public String getStringMethod(int mid){

        return this.getResources().getString(mid);
    }

    protected static void finishAll(){
        for (int i = 0; i < activitiesList.size(); i++) {
            activitiesList.pop().finish();
        }
    }


}
