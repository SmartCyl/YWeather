package com.cc.yweather.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import com.cc.yweather.ui.activity.AboutActivity;
import com.cc.yweather.ui.activity.CityManagerActivity;
import com.cc.yweather.ui.activity.SettingsActivity;
import com.cc.yweather.ui.activity.ToolListActivity;

/**
 * Created by CC on 2017/7/13.
 */

public class IntentUtils {
    // 不携带数据跳转
    public static void intent2Activity(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    // 携带数据跳转
    public static void intent2ActivityWithBundle(Context context, Class clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent, bundle);
    }

    public static void intent2ActivityAnimation(Activity context, Class clazz) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(context);
        Intent intent = new Intent(context, clazz);
        ActivityCompat.startActivity(context, intent, compat.toBundle());
    }

    public static void intent(Activity context, int position) {
        switch (position) {
            case 0:
                intent2ActivityAnimation(context, CityManagerActivity.class);
                break;
            case 1:
                break;
            case 2:
                intent2ActivityAnimation(context, SettingsActivity.class);
                break;
            case 3:
                break;
            case 4:
                intent2Activity(context, ToolListActivity.class);
                break;
            case 5:
                break;
            case 6:
                intent2ActivityAnimation(context, AboutActivity.class);
                break;
        }
    }
}