package com.cc.yweather.mvp.model;

/**
 * Created by CC on 2017/7/13.
 */

public class DrawerMenuItem {
    private int id;
    private int icon;
    private String title; // 标题文字
    private int resId; // 标题资源ID

    public DrawerMenuItem(int id, int icon, String title) {
        this.id = id;
        this.icon = icon;
        this.title = title;
    }

    public DrawerMenuItem(int id, int icon, int resId) {
        this.id = id;
        this.icon = icon;
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
