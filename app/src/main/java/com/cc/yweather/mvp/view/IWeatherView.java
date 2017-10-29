package com.cc.yweather.mvp.view;

import org.json.JSONObject;

/**
 * Created by CC on 2017/7/20.
 */

public interface IWeatherView extends IBaseView {
    void getWeatherSuccess(JSONObject jsonObject, String lng, String lat);
}
