package com.cc.yweather.database.controller;

import android.location.Address;

import com.cc.yweather.database.bean.CurrentCity;

/**
 * Created by CC on 2017/10/18.
 */

public class CityController {
    private static volatile CityController controller;

    public static CityController getController() {
        if (controller == null) {
            synchronized (CityController.class) {
                if (controller == null) {
                    controller = new CityController();
                }
            }
        }
        return controller;
    }

    // 保存当前城市
    public void saveCurrentCity(Address address) {
        CurrentCity city = new CurrentCity();
        city.setProvince(address.getAdminArea());
        city.setCity(address.getLocality());
        city.setArea(address.getSubLocality());
        city.setStreet(address.getThoroughfare());
        city.saveOrUpdate("id = 1");
    }
}
