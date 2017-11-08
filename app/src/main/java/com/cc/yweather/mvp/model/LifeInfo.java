package com.cc.yweather.mvp.model;

/**
 * Created by CC on 2017/11/8.
 */

public class LifeInfo {
    private int icon; // 图标
    private String title; // 标题
    private String desc; // 描述

    public LifeInfo(int icon, String title, String desc) {
        this.icon = icon;
        this.title = title;
        this.desc = desc;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
