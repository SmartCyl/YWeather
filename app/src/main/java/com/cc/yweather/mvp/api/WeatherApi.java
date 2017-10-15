package com.cc.yweather.mvp.api;

import com.cc.yweather.mvp.model.WeatherInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by CC on 2017/7/19.
 */

public interface WeatherApi {
    // 获取天气数据
    @GET("9-2/")
    Observable<WeatherInfo> getWeather(String app_id, String sign, String area, String moreDay, String index, String hourData, String alarm);
}
