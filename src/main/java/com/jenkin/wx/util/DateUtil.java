package com.jenkin.wx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: jenkinwang
 * @date: 2018/9/28 15:51
 * @description:
 */
public class DateUtil {

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentDatetime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 将字符串转换成日期
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date parseStringToDate(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(time);
    }

    /**
     * 获取当前时间的毫秒数
     * @return
     */
    public static long getCurrentMillis() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }

    /**
     * 获取自定义时间的毫秒数
     * @return
     */
    public static long getCustomDateMillis(String datetime) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(parseStringToDate(datetime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }

    public static void main(String[] args) throws ParseException {

        System.out.println(getCurrentMillis()/1000);

        Calendar calendar = Calendar.getInstance();

        long cur = calendar.getTimeInMillis();
        System.out.println(cur);
        calendar.setTime(parseStringToDate("2018-09-28 16:00:03"));
        long l = calendar.getTimeInMillis();
        System.out.println(l);

        System.out.println((cur - l)/1000);

    }
}
