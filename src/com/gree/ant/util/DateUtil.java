package com.gree.ant.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    /**
     * Add second date.
     *
     * @param date   the date
     * @param second the second
     * @return the date
     * @description 将日期增加一定时间-根据second
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:32.
     */
    public static Date addSecond(Date date, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + second);
        return cal.getTime();
    }

    /**
     * Format date date.
     *
     * @param date the date
     * @return the date
     * @description 将日期转化成年月日的日期格式
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:58.
     */
    public static Date formatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return java.sql.Date.valueOf(format.format(date));
    }

    /**
     * Format date date.
     *
     * @param date t日期的标准格式，为String格式，如：Mon Aug 14 00:00:00 CST 2017
     * @return the date
     * @description 将String转成date格式
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:25 04:09:49.
     */
    public static Date formatDate(String date){
        Date day = new Date();
        SimpleDateFormat simple = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        try {
            day = simple.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * Format date 0 date.
     *
     * @param date 日期的标准格式，为String格式，如：yyyy-MM-dd
     * @return the date
     * @description 将String转成date格式
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:25 04:09:13.
     */
    public static Date formatYMDDate(String date){
        Date day = new Date();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        try {
            day = simple.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * Format ymdhmd date date.
     *
     * @param date 日期的标准格式，为String格式，如：yyyy-MM-dd HH:mm:dd
     * @return the date
     * @description 将String转成date格式
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 05:10:32.
     */
    public static Date formatYMDHMDDate(String date){
        Date day = null;
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        try {
            day = simple.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * Format ymd date string.
     *
     * @param date the date
     * @return the string
     * @description 生成年-月-日格式的String日期
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:27 02:09:04.
     */
    public static String formatYMDDate(Date date){
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        return simple.format(date);
    }

    /**
     * Format ym date string.
     *
     * @param date the date
     * @return the string
     * @description 生成年-月格式的String日期
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:27 02:09:36.
     */
    public static String formatYMDate(Date date){
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM");
        return simple.format(date);
    }

}
