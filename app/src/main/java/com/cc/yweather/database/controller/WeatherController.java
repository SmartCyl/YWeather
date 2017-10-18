package com.cc.yweather.database.controller;

/**
 * Created by CC on 2017/10/16.
 */

public class WeatherController {
    private static volatile WeatherController controller;

    public static WeatherController getController() {
        if (controller == null) {
            synchronized (WeatherController.class) {
                if (controller == null) {
                    controller = new WeatherController();
                }
            }
        }
        return controller;
    }
}
