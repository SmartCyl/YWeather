package com.cc.yweather.ui.widget;

/**
 * Created by CC on 2017/10/31.
 */

public class TemperatureItem {
    private String xValue;
    private int yValue;
    private String url; // 图片的UTL
    private String water; // 降水量
    private String clock; // 三小时预报时间
    private String weather; // 三小时预报的天气状况

    // 画折线图
    public TemperatureItem(String xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    // 天气图片和文字
    public TemperatureItem(String xValue, String url) {
        this.url = url;
        this.xValue = xValue;
    }

    // 降水量
    public TemperatureItem(String water) {
        this.water = water;
    }

    public TemperatureItem(String xValue, int yValue, String weather) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.weather = weather;
    }

    public String getxValue() {
        return xValue;
    }

    public void setxValue(String xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
