package com.cc.yweather.database.controller;

import android.util.Log;

import com.cc.yweather.database.bean.FocusCities;

import org.litepal.crud.DataSupport;

import java.util.List;

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
    public void saveCity(String province, String city, String area, boolean isCurrent) {
        FocusCities cities = new FocusCities();
        cities.setProvince(province);
        cities.setCity(city);
        cities.setArea(area);
        cities.setCurrent(isCurrent);
        cities.saveOrUpdate("area = ?", area);
    }

    public FocusCities getCurrentCity() {
        List<FocusCities> currentCities = DataSupport.where("isCurrent = ?", "1").find(FocusCities.class);
        Log.i("getCurrentCity", currentCities.size() + "/");
        FocusCities city = currentCities.get(0);
        Log.i("getCurrentCity", city.getProvince() + " " + city.getCity() + " " + city.getArea());
        return currentCities.size() == 0 ? null : currentCities.get(0);
    }
}
