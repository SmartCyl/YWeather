package com.cc.yweather.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cc.yweather.R;
import com.cc.yweather.app.Type;
import com.cc.yweather.database.bean.Future;
import com.cc.yweather.database.bean.ThreeHourForecast;
import com.cc.yweather.database.bean.Weather;
import com.cc.yweather.database.controller.WeatherController;
import com.cc.yweather.ui.widget.AirQualityView;
import com.cc.yweather.ui.widget.TemperatureItem;
import com.cc.yweather.ui.widget.TemperatureView;
import com.cc.yweather.ui.widget.WeatherStatus;
import com.cc.yweather.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherFragment extends Fragment {
    @BindView(R.id.dayTemperatureView)
    TemperatureView dayTemperatureView;
    @BindView(R.id.nightTemperatureView)
    TemperatureView nightTemperatureView;
    @BindView(R.id.threeHourForecast)
    TemperatureView threeHourForecast; // 三小时预报
    @BindView(R.id.iv_weather_icon)
    ImageView ivWeatherIcon;
    @BindView(R.id.tv_weather_status)
    TextView tvWeatherStatus;// 天气状态（晴）
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;// 温度
    @BindView(R.id.tv_wind)
    TextView tvWind;// 风
    @BindView(R.id.tv_air_quality)
    TextView tvAirQuality; // 空气质量
    @BindView(R.id.tv_forecast)
    TextView tvForecast; // 预警
    @BindView(R.id.ll_weather_status)
    LinearLayout llWeatherStatus;
    @BindView(R.id.ll_water)
    LinearLayout llWater;
    @BindView(R.id.tv_wind_direction)
    TextView tvWindDirection; // 风向
    @BindView(R.id.tv_wind_power)
    TextView tvWindPower; // 风力
    @BindView(R.id.tv_wet)
    TextView tvWet; // 湿度
    @BindView(R.id.air_quality_view)
    AirQualityView mAirQualityView;
    @BindView(R.id.tv_pm_25)
    TextView tvPm25; // PM2.5
    @BindView(R.id.tv_pm10)
    TextView tvPm10; // PM10
    @BindView(R.id.tv_so2)
    TextView tvSo2; // 二氧化硫
    @BindView(R.id.tv_no2)
    TextView tvNo2; // 二氧化氮
    @BindView(R.id.tv_co)
    TextView tvCo; // 一氧化碳
    @BindView(R.id.tv_o3)
    TextView tvO3; // 臭氧

    public static WeatherFragment getInstance() {
        return new WeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);

        dayTemperatureView.setItems(getTemperatures(Type.DAY));
        nightTemperatureView.setItems(getTemperatures(Type.NIGHT));
        threeHourForecast.setForecast(true); // 绘制三小时天气
        threeHourForecast.setPointRadius(8);
        threeHourForecast.setItems(getForecasts());
        WeatherStatus.getManager().showWeatherStatus(getActivity(), llWeatherStatus, getTemperatures(Type.WEATHER_STATUS));
        WeatherStatus.getManager().showWater(getActivity(), llWater, getTemperatures(Type.WATER));
        showWeather(); // 显示今日天气图标和天气状况

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(getActivity()).unbind();
    }

    // 根据白天温度或晚上温度绘制折线图
    public ArrayList<TemperatureItem> getTemperatures(Type type) {
        List<Weather> weathers = WeatherController.getController().getWeathers(getActivity());
        if (weathers == null || weathers.size() == 0) return null;
        int weatherId = weathers.get(0).getId();
        List<Future> futures = WeatherController.getController().getFutures(weatherId);
        if (futures.size() == 0) return null;
        ArrayList<TemperatureItem> items = new ArrayList<>();
        for (Future future : futures) {
            if (type == Type.DAY) {
                items.add(new TemperatureItem("", Integer.valueOf(future.getDayTemperature())));
            } else if (type == Type.NIGHT) {
                items.add(new TemperatureItem(TimeUtils.getWeekByDigital(
                        future.getWeekday(), future.getFutureId()),
                        Integer.valueOf(future.getNightTemperature())));
            } else if (type == Type.WEATHER_STATUS) {
                items.add(new TemperatureItem(future.getDayWeather(), future.getDayWeatherPic()));
            } else if (type == Type.WATER) {
                items.add(new TemperatureItem(future.getWater()));
            }
        }
        return items;
    }

    private ArrayList<TemperatureItem> getForecasts() {
        List<ThreeHourForecast> forecasts = WeatherController.getController().getForecasts(getActivity());
        if (forecasts == null || forecasts.size() == 0) return null;
        ArrayList<TemperatureItem> items = new ArrayList<>();
        for (ThreeHourForecast forecast : forecasts) {
            items.add(new TemperatureItem(forecast.getHour().split("-")[0].split("时")[0] + ":00",
                    Integer.valueOf(forecast.getTemperature()), forecast.getWeather()));
        }
        return items;
    }

    private void showWeather() {
        List<Weather> weathers = WeatherController.getController().getWeathers(getActivity());
        if (weathers == null || weathers.size() == 0) return;
        Weather weather = weathers.get(0);
        String weatherPicUrl = weather.getWeatherPic();
        Glide.with(this).load(weatherPicUrl).into(ivWeatherIcon);
        String status = weather.getWeather();
        tvWeatherStatus.setText(status == null ? "-" : status);
        tvTemperature.setText(weather.getTemperature());
        tvWind.setText(weather.getWindDirection() + " " + weather.getWindPower());
        if (TextUtils.isEmpty(weather.getSignalLevel()) || TextUtils.isEmpty(weather.getSignalType())) {
            tvForecast.setVisibility(View.GONE);
        } else {
            tvForecast.setVisibility(View.VISIBLE);
            tvForecast.setText(weather.getSignalType() + weather.getSignalLevel() + "预警");
        }
        tvAirQuality.setText(weather.getQuality() + " " + weather.getAqi());
        tvWindDirection.setText(weather.getWindDirection());
        tvWindPower.setText(weather.getWindPower());
        tvWet.setText(weather.getWet());
        mAirQualityView.setAqi(weather.getAqi());
        mAirQualityView.setQuality(weather.getQuality());
        tvPm25.setText(weather.getPm2_5() + "μg/m³");
        tvPm10.setText(weather.getPm10() + "μg/m³");
        tvSo2.setText(weather.getSo2() + "μg/m³");
        tvNo2.setText(weather.getNo2() + "μg/m³");
        tvCo.setText(weather.getCo() + "μg/m³");
        tvO3.setText(weather.getO3() + "μg/m³");
    }
}
