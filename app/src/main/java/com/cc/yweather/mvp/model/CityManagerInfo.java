package com.cc.yweather.mvp.model;

/**
 * Created by CC on 2017/7/15.
 */

public class CityManagerInfo {
    private String areaName; // 地名
    private String weather; // 天气（阴、晴）
    private String temperature; // 温度
    private boolean isCurrentCity; // 是否当前城市

    public CityManagerInfo(String areaName, String weather, String temperature, boolean isCurrentCity) {
        this.areaName = areaName;
        this.weather = weather;
        this.temperature = temperature;
        this.isCurrentCity = isCurrentCity;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public boolean isCurrentCity() {
        return isCurrentCity;
    }

    public void setCurrentCity(boolean currentCity) {
        isCurrentCity = currentCity;
    }
}
