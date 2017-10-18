package com.cc.yweather.database.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by CC on 2017/10/18.
 * 当前城市
 */

public class CurrentCity extends DataSupport {
    private String province; // 省
    private String city; // 城市
    private String area; // 区
    private String street; // 街道

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
