package com.cc.yweather.util;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by CC on 2017/10/17.
 */

public class GsonConvert {
    public static <T> T convert(ResponseBody responseBody, Class<T> clazz) {
        Gson gson = new Gson();
        T t = null;
        try {
            t = gson.fromJson(new String(responseBody.bytes()), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }
}
