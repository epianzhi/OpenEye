package com.qj.kaiyan.views;

import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qj.kaiyan.R;
import com.qj.kaiyan.utils.ToastUtils;
import com.qj.kaiyan.weight.CustomChartView;
import com.qj.kaiyan.weight.DoubleLineChatView;
import com.qj.kaiyan.weight.DoublezhuView;
import com.qj.kaiyan.weight.HorizontalBarChartView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChartActivity extends AppCompatActivity implements DoubleLineChatView.Onclicklistener {


    @BindView(R.id.linearlayout)
    LinearLayout linearlayout;
    @BindView(R.id.doubleline)
    DoubleLineChatView doubleline;
    @BindView(R.id.btn_random)
    CheckBox btnRandom;
    @BindView(R.id.btn_rigth)
    CheckBox btnRight;
    @BindView(R.id.totalsale)
    TextView totalsale;
    @BindView(R.id.personsale)
    TextView personsale;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.doublezhuview)
    DoublezhuView doublezhuView;

    private int[] mDataLeftTwo = {160, 181, 130, 100};
    private int[] mDataRightTwo = {151, 65, 40, 20};
    private String[] mDataLeftY = {"0", "30", "60", "90", "120", "150", "180"};
    private int[] mDataLeftTwo1 = {150, 171, 120, 80, 50};
    private int[] mDataRightTwo1 = {121, 45, 20, 40, 50};
    private String[] mDataTextXTwo = {"一", "二", "三", "四"};
    private String[] mDataTextXTwo1 = {"一月", "二月", "三月", "四月", "五月"};
    private int i;
    private int i1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);

        String[] xLabel = {"0", "1", "2", "3", "4", "5"};
        String[] yLabel = {"", "100", "200", "300", "400", "500", "600"};
        int[] data1 = {300, 450, 100, 500, 300, 500};
        int[] data2 = {200, 350, 400, 400, 200, 300};
        List<int[]> data = new ArrayList<>();
        data.add(data1);
        data.add(data2);
        List<Integer> color = new ArrayList<>();
        color.add(R.color.colorPrimary);
        color.add(R.color.black);
        color.add(R.color.color11);
        linearlayout.addView(new CustomChartView(this, xLabel, yLabel, data, color));


        doubleline.setData(mDataLeftY, mDataLeftTwo, mDataRightTwo, mDataTextXTwo);
        doubleline.start();

        btnRandom.setChecked(true);
        totalsale.setText("总销量："+mDataLeftTwo[mDataRightTwo.length-1]);
        personsale.setText("个人销量："+mDataRightTwo[mDataRightTwo.length-1]);
        doubleline.setOnbottomitemclicklistener(this);


        content.removeAllViews();
        HorizontalBarChartView horizontalBarChartView=new HorizontalBarChartView(this);
        horizontalBarChartView.notifyDataChanged();
        content.addView(horizontalBarChartView);

        doublezhuView.setDateRight(mDataLeftTwo1,mDataRightTwo1,mDataTextXTwo1,300);
        doublezhuView.startAnimation();
        doublezhuView.setOnclicklistener(new DoublezhuView.Onclicklistener() {
            @Override
            public void onclick(int position) {
                totalsale.setText("总销量："+mDataLeftTwo1[position]);
                personsale.setText("个人销量："+mDataRightTwo1[position]);
            }

        });
    }


    @OnClick({R.id.btn_random, R.id.btn_rigth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_random:
                doubleline.setData(mDataLeftY, mDataLeftTwo, mDataRightTwo, mDataTextXTwo);
                doubleline.requestLayout();
                btnRandom.setChecked(true);
                btnRight.setChecked(false);
                totalsale.setText("总销量："+mDataLeftTwo[mDataRightTwo.length-1]);
                personsale.setText("个人销量："+mDataRightTwo[mDataRightTwo.length-1]);
                break;
            case R.id.btn_rigth:

                doubleline.setData(mDataLeftY, mDataLeftTwo1, mDataRightTwo1, mDataTextXTwo1);
                doubleline.requestLayout();
                btnRandom.setChecked(false);
                btnRight.setChecked(true);
                totalsale.setText("总销量："+mDataLeftTwo1[mDataRightTwo1.length-1]);
                personsale.setText("个人销量："+mDataRightTwo1[mDataRightTwo1.length-1]);
                break;
        }
    }


    @Override
    public void onclick(int position) {
        if (btnRandom.isChecked()) {
            i = mDataLeftTwo[position];
            i1 = mDataRightTwo[position];
        } else {
            i = mDataLeftTwo1[position];
            i1 = mDataRightTwo1[position];
        }
        totalsale.setText("总销量："+i);
        personsale.setText("个人销量："+i1);
    }
}
