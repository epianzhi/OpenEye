package com.qj.kaiyan.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.qj.kaiyan.R;

@SuppressLint("AppCompatCustomView")
public class CircleProgressBar extends TextView {
    //   圆实心的颜色
    private int circSolidColor;
    //    圆边框的颜色
    private int circFrameColor;

    //    圆的半径
    private int circRadius;
    //    进度条的颜色
    private int progressColor;
    //    进度条的宽度
    private int progressWidth = 8;
    //    文字的颜色
    private int textColor;

    private Paint mPaint;
    private Rect mBounds;
    private RectF mArcRectF;

    private int centerX;
    private int centerY;

    private String mtext = "达成率";
    private String mtextRate = "120%";

    private float rate;

    public CircleProgressBar(Context context) {
        super(context);
        init();
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CountDownProgress);
        if (typedArray != null) {
            if (typedArray.hasValue(R.styleable.CountDownProgress_circSolidColor)) {
                circSolidColor = typedArray.getColor(R.styleable.CountDownProgress_circSolidColor, 0);
            } else {
                circSolidColor = typedArray.getColor(R.styleable.CountDownProgress_circSolidColor, Color.parseColor("#ffffff"));
            }
            if (typedArray.hasValue(R.styleable.CountDownProgress_circFrameColor)) {
                circFrameColor = typedArray.getColor(R.styleable.CountDownProgress_circFrameColor, 0);
            } else {
                circFrameColor = typedArray.getColor(R.styleable.CountDownProgress_circFrameColor, Color.parseColor("#FFD6DEE6"));
            }

            if (typedArray.hasValue(R.styleable.CountDownProgress_textColor)) {
                textColor = typedArray.getColor(R.styleable.CountDownProgress_textColor, 0);
            } else {
                textColor = typedArray.getColor(R.styleable.CountDownProgress_textColor, Color.parseColor("#999999"));
            }
            if (typedArray.hasValue(R.styleable.CountDownProgress_progressColor)) {
                progressColor = typedArray.getColor(R.styleable.CountDownProgress_progressColor, 0);
            } else {
                progressColor = typedArray.getColor(R.styleable.CountDownProgress_progressColor, Color.parseColor("#248Bf1"));
            }
            typedArray.recycle();
        }
    }

    private void init() {

        mPaint = new Paint();
        mBounds = new Rect();
        mArcRectF = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (width > height) {
            height = width;
        } else {
            width = height;
        }

        circRadius = width / 2;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        getDrawingRect(mBounds);

        centerX = mBounds.centerX();
        centerY = mBounds.centerY();

//        画实心圆
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(circSolidColor);
        canvas.drawCircle(mBounds.centerX(), mBounds.centerY(), circRadius, mPaint);

        Paint text_paint = getPaint();
        text_paint.setColor(textColor);
        text_paint.setAntiAlias(true);
        text_paint.setTextAlign(Paint.Align.CENTER);
        text_paint.setTextSize(spToPx(10));
        float textY = centerY - (text_paint.descent() + text_paint.ascent()) / 2;

        Paint.FontMetricsInt fontMetricsInt = text_paint.getFontMetricsInt();
        float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        canvas.drawText(mtext, centerX, textY + dy + 20, text_paint);

        text_paint.setTextSize(spToPx(14));
        text_paint.setColor(Color.BLACK);

        canvas.drawText(mtextRate, centerX, textY - dy, text_paint);


        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(progressWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);


        mArcRectF.set(mBounds.left + progressWidth, mBounds.top + progressWidth, mBounds.right - progressWidth
                , mBounds.bottom - progressWidth);

        mPaint.setColor(circFrameColor);
        // -360*progress/100 逆时针      360*progress/100顺时针
        canvas.drawArc(mArcRectF, -90, 360 * 100 / 100, false, mPaint);

        if (rate > 100)
            mPaint.setColor(Color.parseColor("#FFEF5F5F"));
        else
            mPaint.setColor(progressColor);


        canvas.drawArc(mArcRectF, -90, 360 * (rate * 0.01f), false, mPaint);
    }


    /***
     * 设置文字
     * @param mtextRate
     */

    public void setMtextRate(String mtextRate) {
        this.mtextRate = mtextRate;
        rate = Float.parseFloat(mtextRate.substring(0, mtextRate.length() - 1));
        invalidate();
    }


    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public int spToPx(float spValue) {
        float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
