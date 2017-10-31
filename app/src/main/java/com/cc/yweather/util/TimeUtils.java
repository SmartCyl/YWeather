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

    public static String getWeekByDigital(int weekday, int futureId) {
        String week = null;
        if (futureId == 1) { // futureId为1代表是今天
            week = "今天";
        } else {
            switch (weekday) {
                case 1:
                    week = "星期一";
                    break;
                case 2:
                    week = "星期二";
                    break;
                case 3:
                    week = "星期三";
                    break;
                case 4:
                    week = "星期四";
                    break;
                case 5:
                    week = "星期五";
                    break;
                case 6:
                    week = "星期六";
                    break;
                case 7:
                    week = "星期日";
                    break;
            }
        }
        return week;
    }
}
