package com.cc.yweather.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by CC on 2017/11/7.
 * 空气质量
 */

public class AirQualityView extends View {
    private Paint circlePaint; // 圆环画笔
    private Paint sectorPaint; // 扇形画笔
    private Paint aqiPaint, qualityPaint; // 文字画笔
    private static String CIRCLE_COLOR = "#33E7E7E7";
    private static String SECTOR_COLOR = "#77FFFFFF";
    private static String TEXT_COLOR = "#FFFFFF";
    private static float CIRCLE_WIDTH = 30;
    private int width, height;
    private String aqi; // 空气质量值
    private String quality; // 空气质量描述

    public AirQualityView(Context context) {
        this(context, null);
    }

    public AirQualityView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AirQualityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.parseColor(CIRCLE_COLOR));
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(CIRCLE_WIDTH);
        circlePaint.setStyle(Paint.Style.STROKE);

        sectorPaint = new Paint();
        sectorPaint.setColor(Color.parseColor(SECTOR_COLOR));
        sectorPaint.setAntiAlias(true);
        sectorPaint.setStyle(Paint.Style.FILL);

        aqiPaint = new Paint();
        aqiPaint.setColor(Color.parseColor(TEXT_COLOR));
        aqiPaint.setAntiAlias(true);
        aqiPaint.setTextSize(80);

        qualityPaint = new Paint();
        qualityPaint.setColor(Color.parseColor(TEXT_COLOR));
        qualityPaint.setAntiAlias(true);
        qualityPaint.setTextSize(30);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, 400);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, 400);
        }
        width = getWidth() - getPaddingLeft() - getPaddingRight();
        height = getHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawSector(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        canvas.drawText(aqi, width / 2 - getTextWidth(aqi, aqiPaint) / 2, height / 2 + getTextHeight(aqi, aqiPaint) / 2, aqiPaint);
        canvas.drawText(quality, width / 2 - getTextWidth(quality, qualityPaint) / 2, 3 * height / 4 + getTextHeight(quality, qualityPaint) / 2, qualityPaint);
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    // 绘制弧度
    private void drawSector(Canvas canvas) {
        RectF rectF = new RectF(CIRCLE_WIDTH / 2, CIRCLE_WIDTH / 2, width - CIRCLE_WIDTH / 2, height - CIRCLE_WIDTH / 2);
        canvas.drawArc(rectF, -90, Float.valueOf(aqi) / 600 * 360, true, sectorPaint);
    }

    // 绘制圆环
    private void drawCircle(Canvas canvas) {
        RectF rectF = new RectF(CIRCLE_WIDTH / 2, CIRCLE_WIDTH / 2, width - CIRCLE_WIDTH / 2, height - CIRCLE_WIDTH / 2);
        canvas.drawArc(rectF, -90, 360, false, circlePaint);
    }

    private int getTextHeight(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }

    private int getTextWidth(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.width();
    }
}