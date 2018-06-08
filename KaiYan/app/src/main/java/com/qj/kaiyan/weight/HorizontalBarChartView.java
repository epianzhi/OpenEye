package com.qj.kaiyan.weight;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.qj.kaiyan.R;

import java.util.ArrayList;

public class HorizontalBarChartView extends LinearLayout {
    private Context context;
    private TextView mChartTitle;
    private HorizontalBarChart mBarChart;
    private TextView mBottomTitle;

    public HorizontalBarChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initializeView();
        setUpBarChart();
    }

    public HorizontalBarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeView();
        setUpBarChart();
    }

    public HorizontalBarChartView(Context context) {
        super(context);
        this.context = context;
        initializeView();
        setUpBarChart();
    }

    private void initializeView() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_horizontal_bar_chartview, this);
        mChartTitle = (TextView) findViewById(R.id.mChartTitle);
        mBarChart = (HorizontalBarChart)findViewById(R.id.mBarChart);
        mBottomTitle = (TextView)findViewById(R.id.mBottomTitle);
    }


    /**
     * 更新数据
     * @param
     */
    public void notifyDataChanged() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        BarEntry bar1 = new BarEntry(1200, 0);
        BarEntry bar2 = new BarEntry(1550, 1);
        BarEntry bar3 = new BarEntry(330, 2);
        BarEntry bar4 = new BarEntry(160, 3);
        entries.add(bar1);
        entries.add(bar2);
        entries.add(bar3);
        entries.add(bar4);

        BarDataSet mBarDataSet = new BarDataSet(entries, "");
        mBarDataSet.setColors(new int[]{Color.rgb(8, 93, 245), Color.rgb(248, 9, 9), Color.rgb(8, 93, 245), Color.rgb(248, 9, 9)});
        mBarDataSet.setBarSpacePercent(60f);
        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(mBarDataSet);
        mBarChart.getXAxis().setTextSize(14f);
        mBarDataSet.setValueFormatter(new ValueFormatter());
        BarData data = new BarData(getXAxisValues(), dataSets);

        data.setValueTextSize(12f);
        data.setValueTextColor(context.getResources().getColor(R.color.black));
        mBarChart.setData(data);

        mChartTitle.setText("销量统计" );
        mBottomTitle.setText("比例");
    }


    //写死X轴数据
    public ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<String>();
        xAxis.add("芝麻粨");
        xAxis.add("DDGS");
        xAxis.add("棕榈粨");
        xAxis.add("玉米胚芽粨");
        return xAxis;
    }



    private void setUpBarChart(){
        //关闭所有数据轴的缩放
        mBarChart.setScaleEnabled(false);
        mBarChart.setDrawGridBackground(false);
        mBarChart.setDescription(null);
        mBarChart.setTouchEnabled(false);
        //竖直方向上
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setTextColor(context.getResources().getColor(R.color.black));


        //水平方向上顶部
        YAxis leftYAxis = mBarChart.getAxisLeft();
        leftYAxis.setDrawAxisLine(false);
        leftYAxis.setDrawGridLines(false);
        leftYAxis.setDrawLabels(false);

        //水平方向上底部
        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawLabels(false);
        mBarChart.animateY(0);
        // 图例设置
        Legend mLegend = mBarChart.getLegend();
        mLegend.setEnabled(false);
    }


    class ValueFormatter implements com.github.mikephil.charting.utils.ValueFormatter{

        @Override
        public String getFormattedValue(float value) {
            int dateValue = (int)(value);
            return dateValue + "";
        }
    }
}
