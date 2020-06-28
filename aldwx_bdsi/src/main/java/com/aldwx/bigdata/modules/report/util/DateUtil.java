package com.aldwx.bigdata.modules.report.util;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author
 * @description
 * @createTime
 **/
public class DateUtil extends com.aldwx.bigdata.common.util.DateUtil {


    public static long yesterdayStartTime() {
        return startTime(getYesterday());
    }

    public static long yesterdayEndTime() {
        return endTime(getYesterday());
    }

    public static long weekdayStartTime() {
        return startTime(getPastDate(7));
    }

    public static long weekdayEndTime() {
        return endTime(getPastDate(0));
    }

    public static long monthStartTime() {
        return startTime(getPastDate(30));
    }

    public static long monthEndTime() {
        return endTime(getPastDate(0));
    }

    public static long yearStartTime() {
        return startTime(getPastDate(365));
    }

    public static long yearEndTime() {
        return endTime(getPastDate(0));
    }

    public static String yesterdayStartDate() {
        return startDate(getYesterday());
    }

    public static String yesterdayEndDate() {
        return endDate(getYesterday());
    }

    public static String weekdayStartDate() {
        return startDate(getPastDate(7));
    }

    public static String weekdayEndDate() {
        return endDate(getPastDate(0));
    }

    public static String monthStartDate() {
        return startDate(getPastDate(30));
    }

    public static String monthEndDate() {
        return endDate(getPastDate(0));
    }

    public static String yearStartDate() {
        return startDate(getPastDate(365));
    }

    public static String yearEndDate() {
        return endDate(getPastDate(0));
    }

    public static long startTime(String startTime) {
        String timeStr = startTime + " 00:00:00";
        return timeStrToLong(timeStr);
    }


    public static long endTime(String endTime) {
        String timeStr = endTime + " 23:59:59";
        return timeStrToLong(timeStr);
    }


    public static String startDate(String startTime) {
        String timeStr = startTime + " 00:00:00";
        return timeStr;
    }


    public static String endDate(String endTime) {
        String timeStr = endTime + " 23:59:59";
        return timeStr;
    }


    public static long timeStrToLong(String timeStr) {
        try {
            Date date = TIME_FORMAT.parse(timeStr);
            long ts = date.getTime()/1000;
            return ts;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }


    public static void main(String[] args) {

        List<String> list = DateUtil.getBetweenDates3("2019-01-01", "2019-01-11");

        System.out.println();

        System.out.println(yesterdayStartTime());
        System.out.println(yesterdayEndTime());

        System.out.println(weekdayStartTime());
        System.out.println(weekdayEndTime());

        System.out.println(monthStartTime());
        System.out.println(monthEndTime());
//        System.out.println(getPastDate(7));
//        System.out.println(getPastDate(0));
    }
}
