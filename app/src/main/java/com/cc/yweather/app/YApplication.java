package com.cc.yweather.app;

import android.app.Application;

import com.amitshekhar.DebugDB;

import org.litepal.LitePal;

/**
 * Created by CC on 2017/10/17.
 */

public class YApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
//        Stetho.initializeWithDefaults(this);
        DebugDB.getAddressLog();
    }
}
