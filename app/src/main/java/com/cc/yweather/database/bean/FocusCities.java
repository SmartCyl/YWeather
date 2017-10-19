package com.cc.yweather.database.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by CC on 2017/10/18.
 * 添加的所有城市
 */

public class FocusCities extends DataSupport {
    private int id;
    private String province; // 省
    private String city; // 城市
    private String area; // 区
    private boolean isCurrent; // 是否是当前城市

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }
}
