package com.cc.yweather.mvp.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by CC on 2017/7/19.
 */

public class BaseRequest {
    protected static final Object monitor = new Object();

    static OkHttpClient client = new OkHttpClient.Builder().
            retryOnConnectionFailure(true).
            connectTimeout(5, TimeUnit.SECONDS).
            readTimeout(10, TimeUnit.SECONDS).
            writeTimeout(10, TimeUnit.SECONDS).
            addNetworkInterceptor(new StethoInterceptor()).
            build();
}
