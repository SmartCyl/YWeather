package com.cc.yweather.database.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by CC on 2017/10/29.
 * 逐3小时预报
 */

public class ThreeHourForecast extends DataSupport {

    /**
     * wind_direction : 北风
     * temperature_max : 10
     * wind_power : 3-4级
     * weather : 晴
     * weather_pic : http://app1.showapi.com/weather/icon/day/00.png
     * temperature_min : 4
     * hour : 8时-11时
     * temperature : 4
     */
    private Weather weatherKey;
    private int forecastId;
    private String wind_direction;
    private String temperature_max;
    private String wind_power;
    private String weather;
    private String weather_pic;
    private String temperature_min;
    private String hour;
    private String temperature;

    public int getForecastId() {
        return forecastId;
    }

    public void setForecastId(int forecastId) {
        this.forecastId = forecastId;
    }

    public Weather getWeatherKey() {
        return weatherKey;
    }

    public void setWeatherKey(Weather weatherKey) {
        this.weatherKey = weatherKey;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getTemperature_max() {
        return temperature_max;
    }

    public void setTemperature_max(String temperature_max) {
        this.temperature_max = temperature_max;
    }

    public String getWind_power() {
        return wind_power;
    }

    public void setWind_power(String wind_power) {
        this.wind_power = wind_power;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeather_pic() {
        return weather_pic;
    }

    public void setWeather_pic(String weather_pic) {
        this.weather_pic = weather_pic;
    }

    public String getTemperature_min() {
        return temperature_min;
    }

    public void setTemperature_min(String temperature_min) {
        this.temperature_min = temperature_min;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
