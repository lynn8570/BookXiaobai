
package com.lynn.bookxiaobai.util;

import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2018/2/8.
 */

public class TimeUtil {

    //, , "hh:mm a, dd-MMM-yyyy")
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static String parseDateTimeSpan(String dateString) {
        return parseDateTimeSpan(dateString, null);
    }

    public static String parseDateTimeSpan(String dateString, String basetime) {
        Log.i("linlian", "parseDateTimeSpan=" + dateString);
        if (TextUtils.isEmpty(dateString))
            return "";

        SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT, Locale.US);
        Date date = null;
        try {
            date = formatter.parse(dateString);

//            SimpleDateFormat dateFormat=new SimpleDateFormat(outputFromat, new Locale("US"));
//
//            return dateFormat.format(date);
            ;
            long spanmillis;
            if (TextUtils.isEmpty(basetime)) {

                spanmillis = System.currentTimeMillis() - date.getTime();
            } else {
                spanmillis = formatter.parse(basetime).getTime() - date.getTime();
            }
            Log.i("linlian", "spanmillis=" + spanmillis);
            if (spanmillis < 60000) {
                return millis2FitTimeSpan(spanmillis, 4);
            } else if (spanmillis >= 60000 && spanmillis < 3600000) {
                return millis2FitTimeSpan(spanmillis, 3);
            } else// if(spanmillis>=3600000){
                return millis2FitTimeSpan(spanmillis, 2);
            //}


        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getTime(Date date) {
        if (date == null) {
            date = new Date();//current time
        }
        SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT, Locale.US);

        return formatter.format(date);

    }

    /**
     * 毫秒时间戳转合适时间长度
     *
     * @param millis    毫秒时间戳
     *                  <p>小于等于 0，返回 null</p>
     * @param precision 精度
     *                  <ul>
     *                  <li>precision = 0，返回 null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return 合适时间长度
     */
    public static String millis2FitTimeSpan(long millis, int precision) {
        if (millis <= 0 || precision <= 0) return null;
        StringBuilder sb = new StringBuilder();
        String[] units = {"天", "小时", "分钟", "秒", "毫秒"};
        int[] unitLen = {86400000, 3600000, 60000, 1000, 1};
        precision = Math.min(precision, 5);
        for (int i = 0; i < precision; i++) {
            if (millis >= unitLen[i]) {
                long mode = millis / unitLen[i];
                millis -= mode * unitLen[i];
                sb.append(mode).append(units[i]);
            }
        }
        return sb.toString();
    }
}