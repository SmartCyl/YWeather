package com.cc.yweather.util;

import android.app.Activity;

import com.cc.yweather.inter.OnPermissionResultListener;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by CC on 2017/10/18.
 * 安卓6.0及以上动态申请权限
 */

public class PermissionUtils {
    public static void permissionRequest(Activity activity, final OnPermissionResultListener listener,
                                         final String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEach(permissions).subscribe(new Observer<Permission>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Permission permission) {
                if (permission.granted) listener.grant(permission.name); // 授予权限
                else listener.refused(permission.name); // 拒绝
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
