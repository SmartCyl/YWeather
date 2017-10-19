package com.cc.yweather.manager;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.cc.yweather.eventbus.BusCase;
import com.cc.yweather.eventbus.WeatherEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by CC on 2017/10/15.
 */

public class LocateManager {
    private volatile static LocateManager sManager;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private int locateTimes = 0; // 定位次数

    private LocateManager(Context context) {
        //初始化client
        locationClient = new AMapLocationClient(context.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    public static LocateManager getInstance(Context context) {
        if (sManager == null) {
            synchronized (LocateManager.class) {
                if (sManager == null) {
                    sManager = new LocateManager(context);
                }
            }
        }
        return sManager;
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                StringBuilder sb = new StringBuilder();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    locateTimes = 0;
                    EventBus.getDefault().postSticky(new WeatherEvent(location));
                    stopLocation();
                    sb.append("定位成功" + "\n");
                    //定位完成的时间
                } else {
                    //定位失败
                    ++locateTimes;
                    if (locateTimes == 5) {
                        stopLocation(); // 定位失败5次停止定位}
                        EventBus.getDefault().postSticky(BusCase.LOCATE_TIMEOUT);
                    }
                }
            } else {
                // 定位失败
                Log.i("onLocationChanged", "location == null");
            }
        }
    };

    public void startLocation() {
        if (locationClient == null) return;
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        if (locationClient == null) return;
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     * 如果AMapLocationClient是在当前Activity实例化的，
     * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
     */
    public void destroyLocation() {
        if (null != locationClient) {
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
            sManager = null;
        }
    }

    // 根据经纬度获取城市名
    public Address getAddressFromLat(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> fromLocation = null;
        try {
            fromLocation = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (fromLocation == null || fromLocation.size() == 0) ? null : fromLocation.get(0);
    }
}
