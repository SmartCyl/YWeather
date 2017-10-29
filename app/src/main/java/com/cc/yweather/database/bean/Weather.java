package com.cc.yweather.database.bean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CC on 2017/10/16.
 */

public class Weather extends DataSupport {
    private int id;
    private String time; // 时间
    private String country; // 国家（C9）
    private String province; // 省（C7）
    private String city; // 市（C5）
    private String area; // 区（C3）

    // 报警
    private String signalLevel; // 等级
    private String issueContent;
    private String signalType;

    // 今天气温
    private String dayTemperature;
    private String nightTemperature;

    // 空气质量
    private String co; // 一氧化碳
    private String so2; // 二氧化硫
    private String o3; // 臭氧
    private String no2; // 二氧化氮
    private String quality; // 空气质量
    private String pm10; // PH10
    private String pm2_5; // PH2.5
    private String aqi; // 空气质量指数
    private String weather;
    private String weatherPic;
    private String temperature;
    private String windDirection;
    private String windPower;
    private String wet; // 湿度

    // 生活指数
    private String shoppingTitle; // 逛街指数
    private String shoppingDesc;
    private String lsTitle; // 晾晒指数
    private String lsDesc;
    private String clothesTitle; // 穿衣指数
    private String clothesDesc;
    private String beautyTitle; // 化妆指数
    private String beautyDesc;
    private String sportsTitle; // 运动指数
    private String sportsDesc;
    private String travelTitle; // 旅游指数
    private String travelDesc;
    private String uvTitle; // 紫外线指数
    private String uvDesc;
    private String washCarTitle; // 洗车指数
    private String washCarDesc;
    // 未来6天天气
    private List<Future> mFutures = new ArrayList<>();
    private List<ThreeHourForecast> mThreeHourForecasts = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getSignalLevel() {
        return signalLevel;
    }

    public void setSignalLevel(String signalLevel) {
        this.signalLevel = signalLevel;
    }

    public String getIssueContent() {
        return issueContent;
    }

    public void setIssueContent(String issueContent) {
        this.issueContent = issueContent;
    }

    public String getSignalType() {
        return signalType;
    }

    public void setSignalType(String signalType) {
        this.signalType = signalType;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getWet() {
        return wet;
    }

    public void setWet(String wet) {
        this.wet = wet;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(String pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherPic() {
        return weatherPic;
    }

    public void setWeatherPic(String weatherPic) {
        this.weatherPic = weatherPic;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getShoppingTitle() {
        return shoppingTitle;
    }

    public void setShoppingTitle(String shoppingTitle) {
        this.shoppingTitle = shoppingTitle;
    }

    public String getShoppingDesc() {
        return shoppingDesc;
    }

    public void setShoppingDesc(String shoppingDesc) {
        this.shoppingDesc = shoppingDesc;
    }

    public String getLsTitle() {
        return lsTitle;
    }

    public void setLsTitle(String lsTitle) {
        this.lsTitle = lsTitle;
    }

    public String getLsDesc() {
        return lsDesc;
    }

    public void setLsDesc(String lsDesc) {
        this.lsDesc = lsDesc;
    }

    public String getClothesTitle() {
        return clothesTitle;
    }

    public void setClothesTitle(String clothesTitle) {
        this.clothesTitle = clothesTitle;
    }

    public String getClothesDesc() {
        return clothesDesc;
    }

    public void setClothesDesc(String clothesDesc) {
        this.clothesDesc = clothesDesc;
    }

    public String getBeautyTitle() {
        return beautyTitle;
    }

    public void setBeautyTitle(String beautyTitle) {
        this.beautyTitle = beautyTitle;
    }

    public String getBeautyDesc() {
        return beautyDesc;
    }

    public void setBeautyDesc(String beautyDesc) {
        this.beautyDesc = beautyDesc;
    }

    public String getSportsTitle() {
        return sportsTitle;
    }

    public void setSportsTitle(String sportsTitle) {
        this.sportsTitle = sportsTitle;
    }

    public String getSportsDesc() {
        return sportsDesc;
    }

    public void setSportsDesc(String sportsDesc) {
        this.sportsDesc = sportsDesc;
    }

    public String getTravelTitle() {
        return travelTitle;
    }

    public void setTravelTitle(String travelTitle) {
        this.travelTitle = travelTitle;
    }

    public String getTravelDesc() {
        return travelDesc;
    }

    public void setTravelDesc(String travelDesc) {
        this.travelDesc = travelDesc;
    }

    public String getUvTitle() {
        return uvTitle;
    }

    public void setUvTitle(String uvTitle) {
        this.uvTitle = uvTitle;
    }

    public String getUvDesc() {
        return uvDesc;
    }

    public void setUvDesc(String uvDesc) {
        this.uvDesc = uvDesc;
    }

    public String getWashCarTitle() {
        return washCarTitle;
    }

    public void setWashCarTitle(String washCarTitle) {
        this.washCarTitle = washCarTitle;
    }

    public String getWashCarDesc() {
        return washCarDesc;
    }

    public void setWashCarDesc(String washCarDesc) {
        this.washCarDesc = washCarDesc;
    }

    public List<Future> getFutures() {
        return mFutures;
    }

    public void setFutures(List<Future> futures) {
        mFutures = futures;
    }

    public List<ThreeHourForecast> getThreeHourForecasts() {
        return mThreeHourForecasts;
    }

    public void setThreeHourForecasts(List<ThreeHourForecast> threeHourForecasts) {
        mThreeHourForecasts = threeHourForecasts;
    }
}
