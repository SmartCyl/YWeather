package com.cc.yweather.mvp.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by CC on 2017/7/19.
 */

public interface WeatherApi {
    // 获取天气数据
    @GET("9-5/")
    Observable<ResponseBody> getWeather(
            @Query("showapi_appid") String appId,
            @Query("showapi_sign") String sign,
            @Query("from") String from, @Query("lng") String lng,
            @Query("lat") String lat, @Query("needMoreDay") String moreDay,
            @Query("needIndex") String index, @Query("needHourData") String hourData,
            @Query("needAlarm") String alarm);
}
