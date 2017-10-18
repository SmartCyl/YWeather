package com.cc.yweather.mvp.presenter;

/**
 * Created by CC on 2017/10/16.
 */

public interface IWeatherPresenter {
    /**
     * @param app_id   id
     * @param sign     签名
     * @param from     坐标类型（5-经纬度坐标）
     * @param lng 经度
     * @param lat 纬度
     * @param moreDay 7天数据中的后4天
     * @param index 指数数据，比如穿衣指数、紫外线指数等
     * @param hourData
     * @param alarm
     */
    void getWeather(String app_id, String sign, String from, String lng, String lat,
                    String moreDay, String index, String hourData, String alarm);
}
