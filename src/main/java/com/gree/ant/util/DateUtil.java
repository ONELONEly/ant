package com.gree.ant.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtil {

    public enum DateTypeEnum {
        week_of_year, //week of year
        day_of_month, //month of year
        week_of_month //week of month
    }

    public static Integer getWeek () {
        Calendar calendar = getCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY); //当前的小时数
        int minute = calendar.get(Calendar.MINUTE); //当前的分钟数
        int day = calendar.get(Calendar.DAY_OF_WEEK); //当前是一周的第几天
        int week = calendar.get(Calendar.WEEK_OF_YEAR); //当前是一年的第几周
        if ((day != 1 && ((hour == 8 && minute > 35) || hour > 8))) {
            week = week + 1;
        }
        return week;
    }

    public static Integer dayNumber (DateTypeEnum dateTypeEnum) {
        return dayNumber(new Date(), dateTypeEnum);
    }


    public static Integer dayNumber (Date date, DateTypeEnum dateTypeEnum) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int result;
        switch (dateTypeEnum) {
            case week_of_year:
                result = calendar.get(Calendar.WEEK_OF_YEAR);break;
            case day_of_month:
                result = calendar.get(Calendar.DAY_OF_MONTH);break;
            case week_of_month:
                result = calendar.get(Calendar.WEEK_OF_MONTH);break;
            default:
                result = 0;
        }
        return result;
    }

    private static Calendar getCalendar () {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY); //设置一周的第一天是星期几
        calendar.setTime(date);
        return calendar;
    }

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

    public static String formDateToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.format(formatter);
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
     * @param date 日期的标准格式，为String格式，如：yyyy-MM
     * @return the date
     * @description 将String转成date格式
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    public static Date formatYMDate(String date){
        Date day = null;
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM");
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

    /**
     * @param date
     * @return 返回当前日期
     * @description 生成当前日期
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    public static String getPresentDay(Date date){
        SimpleDateFormat simple = new SimpleDateFormat("dd");
        return simple.format(date);
    }



    public static String formatYMDHMSDate(Date date){
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        return simple.format(date);
    }

    public static Map<String,String> getStartEndDate(String date){
        Map<String,String> resultMap = new HashMap<>();
        if (!StringUtil.checkString(date)) {
            date = formatYMDate(new Date());

        }
        Integer lastIndex = date.lastIndexOf("-");
        int prev = Integer.parseInt(date.substring(0,lastIndex));
        int num = Integer.parseInt(date.substring(lastIndex+1));
        String prevStart = ""; //前个月26号
        String startDate = ""; //上个月26号
        String endDate = prev + "-" + num + "-26"; //本个月26号
        if(num > 1) {
            prevStart = prev + "-" + (num - 2) + "-26"; //前个月26号
            startDate = prev + "-" + (num - 1) + "-26"; //上个月26号
        }else{
            prevStart = (prev-1) +"-11-26";
            startDate = (prev-1) + "-12-26";
        }
        resultMap.put("prevDate",prevStart);
        resultMap.put("startDate",startDate);
        resultMap.put("endDate",endDate);
        return resultMap;
    }

}
