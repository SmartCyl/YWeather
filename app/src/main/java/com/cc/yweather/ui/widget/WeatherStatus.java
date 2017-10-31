package com.cc.yweather.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by CC on 2017/10/31.
 */

public class WeatherStatus {
    private static volatile WeatherStatus manager;

    public static WeatherStatus getManager() {
        if (manager == null) {
            synchronized (WeatherStatus.class) {
                if (manager == null) {
                    manager = new WeatherStatus();
                }
            }
        }
        return manager;
    }

    public void showWeatherStatus(Context context, LinearLayout linearLayout, ArrayList<TemperatureItem> items) {
        Log.i("showWeatherStatus", context + "");
        if (context == null || items == null || items.size() == 0) return;
        linearLayout.removeAllViews();
        for (TemperatureItem item : items) {
            LinearLayout.LayoutParams ivLp = new LinearLayout.LayoutParams(60, 60);
            ivLp.gravity = Gravity.CENTER;
            ImageView imageView = new ImageView(context);
            Glide.with(context).load(item.getUrl()).into(imageView);
            imageView.setLayoutParams(ivLp);

            LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            tvLp.gravity = Gravity.CENTER;
            tvLp.weight = 1;
            tvLp.topMargin = 30;
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setText(item.getxValue());
            textView.setLayoutParams(tvLp);

            LinearLayout layout = new LinearLayout(context);
            layout.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            layout.setLayoutParams(layoutParams);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(imageView);
            layout.addView(textView);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 30, 10, 30);
            linearLayout.setLayoutParams(params);
            linearLayout.addView(layout);
        }
    }

    // 降水量
    public void showWater(Context context, LinearLayout waterLinearLayout, ArrayList<TemperatureItem> items) {
        if (context == null || items == null || items.size() == 0) return;
        waterLinearLayout.removeAllViews();
        for (TemperatureItem item : items) {
            LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            tvLp.gravity = Gravity.CENTER;
            tvLp.weight = 1;
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setText(item.getWater());
            textView.setLayoutParams(tvLp);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 30, 10, 30);
            waterLinearLayout.setLayoutParams(params);
            waterLinearLayout.addView(textView);
        }
    }
}
