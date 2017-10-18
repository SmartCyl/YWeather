package com.cc.yweather.mvp.view;

import com.cc.yweather.database.bean.Weather;

/**
 * Created by CC on 2017/7/20.
 */

public interface IWeatherView extends IBaseView {
    void getWeatherSuccess(Weather weather);
}
