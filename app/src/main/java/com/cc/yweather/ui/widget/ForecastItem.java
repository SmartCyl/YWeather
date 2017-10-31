package com.cc.yweather.ui.widget;

/**
 * Created by CC on 2017/10/31.
 */

public class ForecastItem {
    private String xValue;
    private int yValue;
    private String url; // 图片的UTL
    private String water; // 降水量

    // 画折线图
    public ForecastItem(String xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    // 天气图片和文字
    public ForecastItem(String xValue, String url) {
        this.url = url;
        this.xValue = xValue;
    }

    public ForecastItem(String water) {
        this.water = water;
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
}
