package com.cc.yweather.database.controller;

import android.content.Context;
import android.location.Address;

import com.cc.yweather.database.bean.Future;
import com.cc.yweather.database.bean.ThreeHourForecast;
import com.cc.yweather.database.bean.Weather;
import com.cc.yweather.manager.LocateManager;
import com.cc.yweather.ui.activity.WeatherActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.List;

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

    public void setWeather(Context context, JSONObject jsonObject, String lng, String lat) {
        Address address = LocateManager.getInstance(context)
                .getAddressFromLat(context, Double.valueOf(lat), Double.valueOf(lng));
        Weather weather = new Weather();
        JSONObject body = jsonObject.optJSONObject("showapi_res_body");
        if (body == null) return;
        weather.setTime(body.optString("time"));
//        JSONObject cityInfo = body.optJSONObject("cityInfo");
        // 城市
        if (address != null) {
            weather.setCountry(address.getCountryName());
            weather.setProvince(address.getAdminArea());
            weather.setCity(address.getLocality());
            weather.setArea(address.getSubLocality());
        }
        // 报警
        JSONArray alarmArr = body.optJSONArray("alarmList");
        if (alarmArr != null && alarmArr.length() > 0) {
            try {
                JSONObject alarm = alarmArr.getJSONObject(0);
                weather.setSignalLevel(alarm.optString("signalLevel"));
                weather.setIssueContent(alarm.optString("issueContent"));
                weather.setSignalType(alarm.optString("signalType"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // 空气质量
        JSONObject now = body.optJSONObject("now");
        if (now != null) {
            weather.setWindDirection(now.optString("wind_direction"));
            weather.setWindPower(now.optString("wind_power"));
            weather.setWet(now.optString("sd"));
            weather.setAqi(now.optString("aqi"));
            weather.setWeather(now.optString("weather"));
            weather.setWeatherPic(now.optString("weather_pic"));
            weather.setTemperature(now.optString("temperature"));
            JSONObject aqiDetail = now.optJSONObject("aqiDetail");
            weather.setCo(aqiDetail.optString("co"));
            weather.setSo2(aqiDetail.optString("so2"));
            weather.setO3(aqiDetail.optString("o3"));
            weather.setNo2(aqiDetail.optString("no2"));
            weather.setQuality(aqiDetail.optString("quality"));
            weather.setPm10(aqiDetail.optString("pm10"));
            weather.setPm2_5(aqiDetail.optString("pm2_5"));
        }

        // 生活指数
        JSONObject today = body.optJSONObject("f1");
        if (today != null) {
            weather.setDayTemperature(today.optString("day_air_temperature"));
            weather.setNightTemperature(today.optString("night_air_temperature"));
            JSONObject index = today.optJSONObject("index");
            if (index != null) {
                weather.setShoppingTitle(index.optJSONObject("gj").optString("title"));
                weather.setShoppingDesc(index.optJSONObject("gj").optString("desc"));
                weather.setLsTitle(index.optJSONObject("ls").optString("title"));
                weather.setLsDesc(index.optJSONObject("ls").optString("desc"));
                weather.setClothesTitle(index.optJSONObject("clothes").optString("title"));
                weather.setClothesDesc(index.optJSONObject("clothes").optString("desc"));
                weather.setBeautyTitle(index.optJSONObject("beauty").optString("title"));
                weather.setBeautyDesc(index.optJSONObject("beauty").optString("desc"));
                weather.setSportsTitle(index.optJSONObject("sports").optString("title"));
                weather.setSportsDesc(index.optJSONObject("sports").optString("desc"));
                weather.setTravelTitle(index.optJSONObject("travel").optString("title"));
                weather.setTravelDesc(index.optJSONObject("travel").optString("desc"));
                weather.setUvTitle(index.optJSONObject("uv").optString("title"));
                weather.setUvDesc(index.optJSONObject("uv").optString("desc"));
                weather.setWashCarTitle(index.optJSONObject("wash_car").optString("title"));
                weather.setWashCarDesc(index.optJSONObject("wash_car").optString("desc"));
            }
        }

        // 先查询出数据库中的天气ID，根据weather_id和futureId判断是更新还是保存
        List<Weather> weathers = DataSupport.where("area = ?", address == null ? "青山湖区" :
                address.getSubLocality()).find(Weather.class);
        int weatherId = 0;
        if (weathers != null && weathers.size() > 0) {
            weatherId = weathers.get(0).getId();
        }
        // 保存未来6天天气
        for (int i = 1; i < 8; i++) {
            Future future = new Future();
            JSONObject f = body.optJSONObject("f" + i);
            if (f == null) continue;
            future.setDayWeather(f.optString("day_weather"));
            future.setDayWeatherPic(f.optString("day_weather_pic"));
            future.setWater(f.optString("jiangshui"));
            future.setDayTemperature(f.optString("day_air_temperature"));
            future.setNightTemperature(f.optString("night_air_temperature"));
            future.setWindDirection(f.optString("day_wind_direction"));
            future.setWeekday(f.optInt("weekday"));
            future.setFutureId(i);

            future.saveOrUpdate("weather_id = ? and futureId = ?",
                    String.valueOf(weatherId), String.valueOf(i));
            weather.getFutures().add(future);
        }

        JSONObject f1 = body.optJSONObject("f1");
        if (f1 != null) {
            JSONArray forecastArr = f1.optJSONArray("3hourForcast");
            if (forecastArr != null && forecastArr.length() > 0) {
                for (int i = 0; i < forecastArr.length(); i++) {
                    ThreeHourForecast forecast = new ThreeHourForecast();
                    JSONObject forecastObj = forecastArr.optJSONObject(i);
                    if (forecastObj == null) continue;
                    forecast.setForecastId(i + 1);
                    forecast.setWind_direction(forecastObj.optString("wind_direction"));
                    forecast.setTemperature_max(forecastObj.optString("temperature_max"));
                    forecast.setTemperature_min(forecastObj.optString("temperature_min"));
                    forecast.setWind_power(forecastObj.optString("wind_power"));
                    forecast.setWeather(forecastObj.optString("weather"));
                    forecast.setWeather_pic(forecastObj.optString("weather_pic"));
                    forecast.setHour(forecastObj.optString("hour"));
                    forecast.setTemperature(forecastObj.optString("temperature"));
                    forecast.saveOrUpdate("weather_id = ? and forecastId = ?",
                            String.valueOf(weatherId), String.valueOf(i + 1));
                    weather.getThreeHourForecasts().add(forecast);
                }
            }
        }
        weather.saveOrUpdate("area = ?", address == null ? "青山湖区" : address.getSubLocality());
    }

    // 获取指定weather_id下的所有未来7天天气信息
    public List<Future> getFutures(int weatherId) {
        return DataSupport.where("weather_id = ?", String.valueOf(weatherId)).order("futureId").find(Future.class);
    }

    // 获取指定区域下的所有天气信息
    public List<Weather> getWeathers(Context context) {
        String showingArea = context == null ? "青山湖区" :
                ((WeatherActivity) context).getShowingArea();
        return DataSupport.where("area = ?", showingArea).find(Weather.class);
    }

    public List<ThreeHourForecast> getForecasts(Context context) {
        List<Weather> weathers = WeatherController.getController().getWeathers(context);
        if (weathers == null || weathers.size() == 0) return null;
        int weatherId = weathers.get(0).getId();
        return DataSupport.where("weather_id = ?", String.valueOf(weatherId)).order("forecastId").find(ThreeHourForecast.class);
    }
}
