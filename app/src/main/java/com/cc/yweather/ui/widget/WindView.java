package com.cc.yweather.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by CC on 2017/11/7.
 * 风车
 */

public class WindView extends View {
    private Paint mPaint;
    private Path mPath;
    private static int LINE_COLOR = Color.WHITE;
    private static int LINE_WIDTH = 5;
    private static int WIND_LENGTH; // 扇叶长度
    private static float LENGTH_OF_SIDE; // 扇叶小三角形的斜边长
    private float ANGLE = 0F;
    private int width, height;

    public WindView(Context context) {
        this(context, null);
    }

    public WindView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WindView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setColor(LINE_COLOR);
        mPaint.setStrokeWidth(LINE_WIDTH);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, 450);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, 450);
        }
        width = getWidth() - getPaddingLeft() - getPaddingRight();
        height = getHeight() - getPaddingTop() - getPaddingBottom();
        WIND_LENGTH = Math.min(width, height) / 2 - 20;
        LENGTH_OF_SIDE = (float) (WIND_LENGTH / 10 / Math.cos(45));
    }

    public void setSpeed(float angle) {
        this.ANGLE = angle;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLines(canvas);
        drawWindMills(canvas);
    }

    // 每个扇叶由2个三角形组成，小三角形是由2个等边直角三角形组成,变成是扇叶长度的十分之一
    // Math.sin() Math.cos()的参数都是弧度，而不是角度
    private void drawWindMills(Canvas canvas) {
        if (ANGLE >= 360) ANGLE = 0;
        ANGLE += 5;
        for (int i = 0; i < 3; i++) {
            mPath.moveTo(width / 2, WIND_LENGTH);
            mPath.lineTo(width / 2 + LENGTH_OF_SIDE * (float) Math.sin(Math.PI / 180 * (-45 + ANGLE + 120 * i)),
                    WIND_LENGTH - LENGTH_OF_SIDE * (float) Math.cos(Math.PI / 180 * (-45 + ANGLE + 120 * i)));
            mPath.lineTo(width / 2 + WIND_LENGTH * (float) Math.sin(Math.PI / 180 * (120 * i + ANGLE)),
                    WIND_LENGTH - WIND_LENGTH * (float) Math.cos(Math.PI / 180 * (120 * i + ANGLE)));
            mPath.lineTo(width / 2 + LENGTH_OF_SIDE * (float) Math.sin(Math.PI / 180 * (45 + ANGLE + 120 * i)),
                    WIND_LENGTH - LENGTH_OF_SIDE * (float) Math.cos(Math.PI / 180 * (45 + ANGLE + 120 * i)));
            mPath.lineTo(width / 2, WIND_LENGTH);
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }
        postInvalidateDelayed(30);
        mPath.reset();
    }

    // 绘制风车支架
    private void drawLines(Canvas canvas) {
        canvas.drawLine(width / 3, height, width / 2, WIND_LENGTH, mPaint);
        canvas.drawLine(width / 2, WIND_LENGTH, 2 * width / 3, height, mPaint);
        canvas.drawPath(mPath, mPaint);
    }
}
