package com.cc.yweather.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by CC on 2017/7/13.
 */

public class IndicatorView extends View {
    private Paint mPaint;
    private boolean isSelect = false;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = 10;
        canvas.translate(radius / 2, radius / 2);
        if (isSelect) {
            mPaint.setColor(Color.WHITE);
        } else {
            mPaint.setColor(Color.GRAY);
        }
        canvas.drawCircle(0, 0, radius / 2, mPaint);
    }

    public void setSelect(boolean select) {
        this.isSelect = select;
    }
}
