package com.cc.yweather.eventbus;

import com.amap.api.location.AMapLocation;

/**
 * Created by CC on 2017/10/16.
 */

public class WeatherEvent {
    /**
     * 定位
     */
    private AMapLocation mAMapLocation;

    public WeatherEvent(AMapLocation AMapLocation) {
        mAMapLocation = AMapLocation;
    }

    public AMapLocation getAMapLocation() {
        return mAMapLocation;
    }

    public void setAMapLocation(AMapLocation AMapLocation) {
        mAMapLocation = AMapLocation;
    }
}
