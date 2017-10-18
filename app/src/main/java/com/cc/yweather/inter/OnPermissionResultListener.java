package com.cc.yweather.inter;

/**
 * Created by CC on 2017/10/18.
 * 权限申请回调
 */

public interface OnPermissionResultListener {
    void grant(String permission);

    void refused(String permission);
}
