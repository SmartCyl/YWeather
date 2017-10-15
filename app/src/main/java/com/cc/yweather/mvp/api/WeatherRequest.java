package com.cc.yweather.mvp.api;

import com.cc.yweather.app.Constance;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CC on 2017/7/19.
 */

public class WeatherRequest extends BaseRequest {
    private static WeatherApi weatherApi = null;

    public static WeatherApi getWeatherApi() {
        synchronized (monitor) {
            if (weatherApi == null) {
                weatherApi = new Retrofit.Builder()
                        .baseUrl(Constance.BASE_URL)
                        .client(client)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(WeatherApi.class);
            }
            return weatherApi;
        }
    }
}
