package com.cc.yweather.mvp.presenter.impl;

import com.cc.yweather.mvp.api.WeatherRequest;
import com.cc.yweather.mvp.presenter.IWeatherPresenter;
import com.cc.yweather.mvp.view.IWeatherView;
import com.cc.yweather.util.ErrorUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by CC on 2017/10/16.
 * 获取天气数据
 */

public class WeatherPresenterImpl implements IWeatherPresenter {
    private IWeatherView mIWeatherView;

    public WeatherPresenterImpl(IWeatherView IWeatherView) {
        mIWeatherView = IWeatherView;
    }

    @Override
    public void getWeather(String app_id, String sign, String from, final String lng, final String lat,
                           String moreDay, String index, String hourData, String alarm) {
        WeatherRequest.getWeatherApi()
                .getWeather(app_id, sign, from, lng, lat, moreDay, index, hourData, alarm)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
                mIWeatherView.getDisposable(d);
            }

            @Override
            public void onNext(ResponseBody responseBody) {
//                Weather weather = GsonConvert.convert(responseBody, Weather.class);
                try {
                    mIWeatherView.getWeatherSuccess(new JSONObject(new String(responseBody.bytes())), lng, lat);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                mIWeatherView.getInformationFailed(ErrorUtils.getMessage(e));
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
