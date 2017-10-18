package com.cc.yweather.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by CC on 2017/10/16.
 */

public class TimeUtils {
    /**
     * 获得指定日期的后*天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay, String format, long count) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(format, Locale.getDefault()).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, (int) (day + count));
        return new SimpleDateFormat(format, Locale.getDefault()).format(c.getTime());
    }
}
