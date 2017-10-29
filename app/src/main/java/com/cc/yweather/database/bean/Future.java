package com.cc.yweather.database.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by CC on 2017/10/28.
 * 未来6天天气
 */

public class Future extends DataSupport {
    private int futureId;
    private Weather mWeather;
    private String dayWeather;
    private String dayWeatherPic;
    private String water; // 降水
    private String dayTemperature; // 气温
    private String nightTemperature;
    private String windDirection; // 风向
    private int weekday; // 星期几

    public int getFutureId() {
        return futureId;
    }

    public void setFutureId(int futureId) {
        this.futureId = futureId;
    }

    public Weather getWeather() {
        return mWeather;
    }

    public void setWeather(Weather weather) {
        mWeather = weather;
    }

    public String getDayWeather() {
        return dayWeather;
    }

    public void setDayWeather(String dayWeather) {
        this.dayWeather = dayWeather;
    }

    public String getDayWeatherPic() {
        return dayWeatherPic;
    }

    public void setDayWeatherPic(String dayWeatherPic) {
        this.dayWeatherPic = dayWeatherPic;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getDayTemperature() {
        return dayTemperature;
    }

    public void setDayTemperature(String dayTemperature) {
        this.dayTemperature = dayTemperature;
    }

    public String getNightTemperature() {
        return nightTemperature;
    }

    public void setNightTemperature(String nightTemperature) {
        this.nightTemperature = nightTemperature;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }
}
