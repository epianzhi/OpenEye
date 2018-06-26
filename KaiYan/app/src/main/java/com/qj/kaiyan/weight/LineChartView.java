package com.qj.kaiyan.weight;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.qj.kaiyan.R;

import java.util.ArrayList;
import java.util.List;

public class LineChartView extends View {


    private  Context mContext;

    //x,y轴,箭头,画笔
    private Paint  xyPaint;

    private Paint xuPaint;

    //五个数字
    private int[] mData={0,0,0,0,0};

    /**
     * 位置点画笔
     */
    private Paint pointPaint;
    /**
     * XY轴文字画笔
     * */
    private Paint mXYPaint;

    /**
     * X轴底部文字
     */
    private String[] mDataTextX = {"1", "2", "3", "4","5","6","7"};
    /**
     * shuzhi文字
     */
    private int[] mDataTextY = {100, 300, 200, 400,50,300,350};
    private int[] mDataTextY2 = {350, 450, 380, 460,250,450,500};



    /**
     * Y轴文字宽度
     */
    private float mYDistance=100;
    /**
     * X轴底部高度
     */
    private float mXDistance=50;
    /**
     * View宽度
     */
    private int mViewWidth;
    /**
     * View高度
     */
    private int mViewHeight;
    /**
     * 点与点的距离
     */
    private float distance=100;

    private int maxData=500;


    private float textSize=35;
    private float pointSize=10;
    private int pointColor=Color.RED;

    private List<Float> points;
    private List<Float> points2;

    Canvas canvas;
    float increaseX,increaseY;
    private float pointX1;
    private float pointY1;
    private float pointx2;
    private float pointY2;

    public LineChartView(Context context) {
        this(context,null);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;

        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.LineChartView);
        distance=array.getDimension(R.styleable.LineChartView_linechart_distance,dip2px(mContext,30));
        mYDistance=array.getDimension(R.styleable.LineChartView_linechart_mYDistance,dip2px(mContext,40));
        mXDistance=array.getDimension(R.styleable.LineChartView_linechart_mXDistance,dip2px(mContext,40));
        textSize=array.getDimension(R.styleable.LineChartView_linechart_textSize,sp2px(mContext,15));
        pointSize=array.getDimension(R.styleable.LineChartView_linechart_pointSize,dip2px(mContext,5));
        pointColor=array.getColor(R.styleable.LineChartView_linechart_pointColor,Color.RED);

        array.recycle();
        init();

    }

    private void init() {
        xyPaint=new Paint();
        xyPaint.setStrokeWidth(3);
        xyPaint.setAntiAlias(true);
        xyPaint.setColor(Color.BLACK);
        xyPaint.setStyle(Paint.Style.STROKE);

        pointPaint=new Paint();
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(pointColor);
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(pointSize);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);

        xuPaint=new Paint();
        xuPaint.setStrokeWidth(1);
        xuPaint.setColor(Color.GRAY);
        xuPaint.setAntiAlias(true);

        mXYPaint=new Paint();
        mXYPaint.setTextSize(textSize);

        canvas=new Canvas();
        points=new ArrayList<>();
        points2=new ArrayList();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setDimension(heightMeasureSpec);
    }

    private void setDimension(int heightMeasureSpec) {
        mViewWidth= (int) (mYDistance+distance+mDataTextY.length*(distance+1));
        mViewHeight=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mViewWidth,mViewHeight);
    }

    public void setmData(int[] mData) {
        this.mData = mData;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画点与线
        drawPointLine(canvas);
        //画坐标轴
        drawXY(canvas);
        //画x轴文字
        drawXtext(canvas);
        //画Y轴文字
        drawYtext(canvas);

        start();


    }

    private void drawYtext(Canvas canvas) {

        for (int i = 0; i < 6; i++) {
            String text=(maxData/5*i)+"";

            float textWidth=mXYPaint.measureText(text,0,text.length());
            float textx=mYDistance/2-textWidth/2;

            Paint.FontMetricsInt fontMetricsInt=mXYPaint.getFontMetricsInt();
            float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            float baseline=(mViewHeight-mXDistance)-(i*(mViewHeight-2*mXDistance)/5)+dy;

            canvas.drawText(text,textx,baseline,mXYPaint);

        }
    }

    private void drawXtext(Canvas canvas) {
        mXYPaint.setColor(Color.BLACK);
        for (int i = 0; i < mDataTextX.length; i++) {
           String text= mDataTextX[i];

          float textWidth= mXYPaint.measureText(text,0,text.length());
          float textx = (mYDistance + distance + 1 + (i * (distance+1))) - textWidth / 2;

            Paint.FontMetricsInt fontMetricsInt=mXYPaint.getFontMetricsInt();
            float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            float baseLine = mViewHeight - mXDistance / 2 + dy+10;

            canvas.drawText(text,textx,baseLine,mXYPaint);

        }
    }

    private void drawXY(Canvas canvas) {
        canvas.drawLine(mYDistance, mViewHeight - mXDistance, mYDistance, 15, xyPaint);
        canvas.drawLine(mYDistance, mViewHeight - mXDistance, mViewWidth - 15, mViewHeight - mXDistance, xyPaint);

        //Y轴箭头
        canvas.drawLine(mYDistance, 15, mYDistance - 10, 25, xyPaint);
        canvas.drawLine(mYDistance, 15, mYDistance + 10, 25, xyPaint);
        //X轴箭头
        canvas.drawLine(mViewWidth - 15, mViewHeight - mXDistance, mViewWidth - 25, mViewHeight - mXDistance - 10, xyPaint);
        canvas.drawLine(mViewWidth - 15, mViewHeight - mXDistance, mViewWidth - 25, mViewHeight - mXDistance + 10, xyPaint);
    }

    private void drawPointLine(Canvas canvas) {
        points.clear();
        points2.clear();

        mXYPaint.setColor(Color.BLACK);
        for (int i = 0; i < mDataTextY.length; i++) {

            pointX1 = mYDistance+distance+1+(distance+1)*i;
            pointY1 = (mViewHeight - mXDistance) - (mDataTextY[i] * (mViewHeight - 2 * mXDistance)) /maxData;
            canvas.drawPoint(pointX1, pointY1,pointPaint);


            String text=mDataTextY[i]+"";

            float textWidth= mXYPaint.measureText(text,0,text.length());
            float textx = pointX1 - textWidth / 2;

            Paint.FontMetricsInt fontMetricsInt=mXYPaint.getFontMetricsInt();
            float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            float baseLine = pointY1 - dy-10;

            canvas.drawText(text,textx,baseLine,mXYPaint);


            points.add(pointX1);
            points.add(pointY1);
            //画虚线
            while (pointY1 <((mViewHeight - mXDistance))){
                canvas.drawLine(pointX1, pointY1, pointX1, pointY1 +10,xuPaint);
                pointY1 +=20;
            }
            if (i<mDataTextY.length-1){
                pointx2 = mYDistance+distance+1+(distance+1)*(i+1);
                pointY2 = (mViewHeight - mXDistance) - (mDataTextY[i+1] * (mViewHeight - 2 * mXDistance)) /maxData;

                points.add(pointx2);
                points.add(pointY2);
            }

        }

        mXYPaint.setColor(Color.BLUE);
        for (int i = 0; i < mDataTextY2.length; i++) {
            float pointX1=mYDistance+distance+1+(distance+1)*i;
            float pointY1=(mViewHeight - mXDistance) - (mDataTextY2[i] * (mViewHeight - 2 * mXDistance)) /maxData;
            canvas.drawPoint(pointX1,pointY1,pointPaint);
            points2.add(pointX1);
            points2.add(pointY1);

            String text=mDataTextY2[i]+"";

            float textWidth= mXYPaint.measureText(text,0,text.length());
            float textx = pointX1 - textWidth / 2;

            Paint.FontMetricsInt fontMetricsInt=mXYPaint.getFontMetricsInt();
            float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            float baseLine = pointY1 - dy-10;

            canvas.drawText(text,textx,baseLine,mXYPaint);

            //画虚线
            while (pointY1<((mViewHeight - mXDistance))){
                canvas.drawLine(pointX1,pointY1,pointX1,pointY1+10,xuPaint);
                pointY1+=20;
            }
            if (i<mDataTextY.length-1){
                float pointx2=mYDistance+distance+1+(distance+1)*(i+1);
                float pointY2=(mViewHeight - mXDistance) - (mDataTextY2[i+1] * (mViewHeight - 2 * mXDistance)) /maxData;
                points2.add(pointx2);
                points2.add(pointY2);
            }


        }

        float [] fpoints=new float[points.size()];
        float [] fpoints2=new float[points.size()];

        for (int i = 0; i < points.size(); i++) {
            fpoints[i]= (float) points.get(i);

        }
        for (int i = 0; i < points2.size(); i++) {
            fpoints2[i]= (float) points2.get(i);

        }
        canvas.drawLines(fpoints,xyPaint);
        xyPaint.setColor(Color.BLUE);
        canvas.drawLines(fpoints2,xyPaint);
        xyPaint.setColor(Color.BLACK);
    }

    public void start(){
        AnimatorSet set=new AnimatorSet();
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(pointX1,pointx2);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                increaseX= (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        ValueAnimator valueAnimatory=ValueAnimator.ofFloat(pointY1,pointY2);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                increaseY= (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        set.playTogether(valueAnimator,valueAnimatory);
        set.setDuration(1000);
        set.start();
    }
    /**
     * sp转px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * dp转px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
