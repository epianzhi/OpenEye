package com.qj.kaiyan.weight;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.qj.kaiyan.R;
import com.qj.kaiyan.entitys.Touchmodel;

import java.util.ArrayList;
import java.util.List;

public class DoublezhuView extends View {

    private int[] dataLeft = {0, 0, 0, 0, 0};
    private int[] dateRight = {0, 0, 0, 0, 0};

    private String[] dataX = {"", "", "", ""};

    private Context mContext;

    //左柱
    private Paint paintLeft;
    //右柱
    private Paint paintRight;
    //左柱文字
    private Paint paintTextLeft;
    //右柱文字
    private Paint paintTextRight;
    //XY轴画笔
    private Paint paintTextXY;
    //Y轴文字宽度
    private float mYdistance;
    //X轴底部高度
    private float mXdistance;
    //柱子宽度
    private float lineWidth;

    //color

    private int leftLineColor = Color.RED;
    private int rightLineColor = Color.BLUE;
    private int leftLineTextColor = Color.RED;
    private int rightLineTextColor = Color.BLUE;

    private float lineTextSize;

    private int lineXYcolor = Color.BLACK;
    private float lineXYsize;


    //是否显示箭头
    private boolean isShowArrow = true;

    private boolean isshowInterval = true;

    private int animationDuration = 3000;

    //父view宽度
    private int viewWidth;
    //view高度
    private int viewHeight;

    //Y轴的最大数据
    private int mMaxData;


    //偏大距离
    private float bigDistance;
    //偏小距离
    private float smallDistance;

    Canvas canvas;

    //当前选中位置 初始为最后一个
    private int currentChecked;

    private List<TouchModel> modelList = new ArrayList<>();
    private TouchModel touchModel;

    //X轴点击事件
    private Onclicklistener onclicklistener;

    public void setOnclicklistener(Onclicklistener onclicklistener) {
        this.onclicklistener = onclicklistener;
    }

    public DoublezhuView(Context context) {
        this(context, null);
    }

    public DoublezhuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoublezhuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Doublezhuview);

        lineWidth = array.getDimension(R.styleable.Doublezhuview_doubleview_line_width, dip2px(mContext, 20));
        leftLineColor = array.getColor(R.styleable.Doublezhuview_doubleview_left_color, leftLineColor);
        rightLineColor = array.getColor(R.styleable.Doublezhuview_doubleview_right_color, rightLineColor);
        leftLineTextColor = array.getColor(R.styleable.Doublezhuview_doubleview_lefttext_color, leftLineTextColor);
        rightLineTextColor = array.getColor(R.styleable.Doublezhuview_doubleview_righttext_color, rightLineTextColor);
        lineTextSize = array.getDimension(R.styleable.Doublezhuview_doubleview_textdata_size, sp2px(mContext, 14));
        lineXYcolor = array.getColor(R.styleable.Doublezhuview_doubleview_text_xy_color, lineXYcolor);
        lineXYsize = array.getDimension(R.styleable.Doublezhuview_doubleview_text_xy_size, sp2px(mContext, 12));
        bigDistance = array.getDimension(R.styleable.Doublezhuview_doubleview_gib_diatance, dip2px(mContext, 15));
        smallDistance = array.getDimension(R.styleable.Doublezhuview_doubleview_small_distance, dip2px(mContext, 5));

        isShowArrow = array.getBoolean(R.styleable.Doublezhuview_doubleview_show_arrow, true);
        isshowInterval = array.getBoolean(R.styleable.Doublezhuview_doubleview_show_y_interval, true);

        animationDuration = array.getInteger(R.styleable.Doublezhuview_doubleview_animation_duration, 1000);

        mYdistance = array.getDimension(R.styleable.Doublezhuview_doubleview_y_distance, dip2px(mContext, 40));
        mXdistance = array.getDimension(R.styleable.Doublezhuview_doubleview_x_diatance, dip2px(mContext, 40));
        array.recycle();
        initView();
    }

    private void initView() {

        paintLeft = new Paint();
        paintLeft.setColor(leftLineColor);
        paintLeft.setStrokeWidth(lineWidth);
        paintLeft.setAntiAlias(true);

        paintRight = new Paint();
        paintRight.setColor(rightLineColor);
        paintRight.setStrokeWidth(lineWidth);
        paintRight.setAntiAlias(true);

        paintTextLeft = new Paint();
        paintTextLeft.setColor(leftLineTextColor);
        paintTextLeft.setTextSize(lineTextSize);
        paintTextLeft.setAntiAlias(true);

        paintTextRight = new Paint();
        paintTextRight.setColor(rightLineTextColor);
        paintTextRight.setTextSize(lineTextSize);
        paintTextRight.setAntiAlias(true);

        paintTextXY = new Paint();
        paintTextXY.setStrokeWidth(1);
        paintTextXY.setColor(lineXYcolor);
        paintTextXY.setTextSize(lineXYsize);
        paintTextXY.setAntiAlias(true);

        canvas = new Canvas();
    }

    public void setDateRight(int[] dateLeft, int[] dateRight, String[] dataX, int maxData) {
        this.dateRight = dateRight;
        this.dataLeft = dateLeft;
        this.dataX = dataX;
        mMaxData = maxData;


        currentChecked = dataLeft.length - 1;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setDimension(heightMeasureSpec);
    }

    private void setDimension(int heightMeasureSpec) {

        viewWidth = (int) (mYdistance + bigDistance + (dataLeft.length * (lineWidth * 2 + bigDistance + smallDistance)));
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(viewWidth, viewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        //绘制柱状图
        drawLineData(canvas);
        //绘制坐标轴
        drawLineXY(canvas);

        //X轴坐标值
        drawDataX(canvas);

        //Y轴坐标值
        drawDataY(canvas);
    }

    private void drawDataY(Canvas canvas) {
        for (int i = 0; i < 7; i++) {
            //绘制进度数字
            String text = (mMaxData / 6 * i) + "";
            //获取文字宽度
            float textwidth = paintTextXY.measureText(text, 0, text.length());

            float dx = mYdistance / 2 - textwidth / 2;
            Paint.FontMetricsInt fontMetricsInt = paintTextXY.getFontMetricsInt();
            float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            float baseline = (viewHeight - mXdistance) - (i * (viewHeight - 2 * mXdistance) / 6) + dy;


            canvas.drawText(text, dx, baseline, paintTextXY);

            if (isshowInterval) {
                canvas.drawLine(mYdistance, (viewHeight - mXdistance) - (i * (viewHeight - 2 * mXdistance) / 6)
                        , mYdistance + 10, (viewHeight - mXdistance) - (i * (viewHeight - 2 * mXdistance) / 6)
                        , paintTextXY);
            }
        }
    }

    private void drawDataX(Canvas canvas) {
        for (int i = 0; i < dataX.length; i++) {
            String text = dataX[i];

            float textwidth = paintTextXY.measureText(text);
            float x = (mYdistance + bigDistance + smallDistance / 2 + lineWidth + (i * (bigDistance + smallDistance + 2 * lineWidth))) - textwidth / 2;

            //计算出绘制文字基线的位置
            /**
             * fontMetricsInt.bottom 位于baseline 的下边 正数
             * fontMetricsInt.top  位于baseline 的上边 负数
             *
             * */
            Paint.FontMetricsInt fontMetricsInt = paintTextXY.getFontMetricsInt();
            float y = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            float baseline = viewHeight - mXdistance / 2 + y;

            canvas.drawText(text, x, baseline, paintTextXY);

            /***
             * 绘制X轴数据选中状态
             */
            Paint.FontMetricsInt fm = paintTextXY.getFontMetricsInt();
            float top = (baseline + fm.top);
            float bottom = (baseline + fm.bottom);

            float textHeight = bottom - top;

            RectF f = new RectF(x - 3, baseline - textHeight + 5, x + textwidth + 3, baseline + 18);

            TouchModel e = new TouchModel(f.left, f.top, f.right, f.bottom, i);
            modelList.add(e);

            if (i == currentChecked) {
                paintTextXY.setStyle(Paint.Style.STROKE);
                paintTextXY.setStrokeWidth(1);

                canvas.drawRoundRect(f, 2, 2, paintTextXY);
                paintTextXY.setStyle(Paint.Style.FILL);
            }

        }
    }

    private void drawLineXY(Canvas canvas) {
        canvas.drawLine(mYdistance, viewHeight - mXdistance, mYdistance, 15, paintTextXY);
        canvas.drawLine(mYdistance, viewHeight - mXdistance, viewWidth - 15, viewHeight - mXdistance, paintTextXY);

        String cellText = "(吨)";
        float v = paintTextXY.measureText(cellText);
        Paint.FontMetricsInt fontMetrics = paintTextXY.getFontMetricsInt();
        int i = fontMetrics.bottom - fontMetrics.top;
        canvas.drawText(cellText, mYdistance - v - 10, 10 + i, paintTextXY);

        //X轴不绘制单位
        String weekText = "";
        float v1 = paintTextXY.measureText(weekText);
        canvas.drawText(weekText, viewWidth - v1 - 10, viewHeight - i - 15, paintTextXY);

        if (isShowArrow) {
            canvas.drawLine(mYdistance, 15, mYdistance - 10, 25, paintTextXY);
            canvas.drawLine(mYdistance, 15, mYdistance + 10, 25, paintTextXY);

            canvas.drawLine(viewWidth - 15, viewHeight - mXdistance, viewWidth - 25, viewHeight - mXdistance - 10, paintTextXY);
            canvas.drawLine(viewWidth - 15, viewHeight - mXdistance, viewWidth - 25, viewHeight - mXdistance + 10, paintTextXY);
        }
    }

    private void drawLineData(Canvas canvas) {
        for (int i = 0; i < dataLeft.length; i++) {
            float startX = mYdistance + bigDistance + lineWidth / 2 + (i * (bigDistance + smallDistance + 2 * lineWidth));
            float startY = viewHeight - mXdistance;
            float endY = (viewHeight - mXdistance) - (dataLeft[i] * (viewHeight - 2 * mXdistance)) / mMaxData;
            canvas.drawLine(startX, startY, startX, endY, paintLeft);
            String text = dataLeft[i] + "";

            float textwidth = paintTextLeft.measureText(text, 0, text.length());
            canvas.drawText(text, startX - textwidth / 2, endY - 15, paintTextLeft);

        }

        for (int i = 0; i < dateRight.length; i++) {

            float startX = mYdistance + bigDistance + smallDistance + lineWidth + lineWidth / 2 + (i * (bigDistance + smallDistance + 2 * lineWidth));
            float endY = (viewHeight - mXdistance) - (dateRight[i] * (viewHeight - 2 * mXdistance)) / mMaxData;
            canvas.drawLine(startX, viewHeight - mXdistance, startX, endY, paintRight);
            String text = dateRight[i] + "";
            float textWidth = paintTextRight.measureText(text, 0, text.length());
            canvas.drawText(text, startX - textWidth / 2, endY - 15, paintTextRight);
        }

    }

    /**
     * 根据手势触摸区域判断点击位置
     * <p>
     * 这里只判断按下位置和抬起位置是否相同
     */
    float lastX, lastY;
    int current = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;

                for (int i = 0; i < modelList.size(); i++) {
                    touchModel = modelList.get(i);
                    //判断手指按下时的区域是否在X轴文字的某一个位置范围内，上下左右都满足之后，得到点击位置索引
                    if (lastX > touchModel.getLeft() && lastX < touchModel.getRight() && lastY > touchModel.getTop() && lastY < touchModel.getBottom()) {
                        current = touchModel.getI();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                for (int i = 0; i < modelList.size(); i++) {
                   touchModel= modelList.get(i);
                   //判断抬起位置是否还在该区域,重绘，并触发点击事件
                    if (lastX > touchModel.getLeft() && lastX < touchModel.getRight() && lastY > touchModel.getTop() && lastY < touchModel.getBottom()) {
                       currentChecked=current;
                       invalidate();
                       onclicklistener.onclick(current);
                    }
                }
                break;
        }
        return true;
    }

    public interface Onclicklistener{
        void onclick(int position);
    }
    /***
     * 开启动画
     */
    public void startAnimation() {
        AnimatorSet set = new AnimatorSet();

        for (int i = 0; i < dataLeft.length; i++) {
            ValueAnimator animator = ValueAnimator.ofInt(0, dataLeft[i]);

            final int finalI = i;
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    dataLeft[finalI] = (int) animation.getAnimatedValue();
                    invalidate();
                }
            });

            set.playTogether(animator);
        }

        for (int i = 0; i < dateRight.length; i++) {
            final ValueAnimator animator = ValueAnimator.ofInt(0, dateRight[i]);

            final int finalI = i;
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    dateRight[finalI] = (int) animator.getAnimatedValue();
                    invalidate();
                }
            });

            set.playTogether(animator);
        }

        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration(animationDuration);
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


    public static class TouchModel {
        private float left;
        private float top;
        private float right;
        private float bottom;

        private int i;

        public TouchModel(float left, float top, float right, float bottom, int i) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
            this.i = i;
        }

        public float getLeft() {
            return left;
        }

        public void setLeft(float left) {
            this.left = left;
        }

        public float getTop() {
            return top;
        }

        public void setTop(float top) {
            this.top = top;
        }

        public float getRight() {
            return right;
        }

        public void setRight(float right) {
            this.right = right;
        }

        public float getBottom() {
            return bottom;
        }

        public void setBottom(float bottom) {
            this.bottom = bottom;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }
}
