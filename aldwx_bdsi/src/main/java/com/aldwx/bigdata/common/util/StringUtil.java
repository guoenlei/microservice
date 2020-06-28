package com.aldwx.bigdata.common.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StringUtil {





    /**
     * 格式化时长
     * @param stayTime
     * @return
     */
    public static String formatTime(double stayTime) {
        final int A = 60;
        String result = null;
        if(stayTime >= 60) {
            int minuter = (int)stayTime / A;
            int min = (int)stayTime % A;
            if(minuter >= 60) {
                int hour = minuter / A;
                int h = minuter % A;
                return String.format("%s:%s:%s", (hour >= 10 ? hour : "0"+hour),
                        (h >= 10 ? h : "0"+h), (min >= 10 ? min : "0"+min));
            } else {
                result = String.format("00:%s:%s", (minuter >= 10 ? minuter : "0"+minuter),
                        (min >= 10 ? min : "0"+min));
            }
        } else {
            result = String.format("00:00:%s", (stayTime >= 10 ? (int)stayTime : "0"+(int)stayTime));
        }
        return result;
    }

    /**
     * 格式化数字 转千分位
     * @param num
     * @return
     */
    public static String formatMathNum(double num, String floatType) {
        BigDecimal a = new BigDecimal(num);
        DecimalFormat df = null;
        String resultNum = "";
        try {
            if(floatType.equals("1")) {
                df = new DecimalFormat(",###,##0"); //没有小数
            } else if(floatType.equals("2")) {
                df = new DecimalFormat(",###,##0.0"); //保留一位小数
            } else if(floatType.equals("3")) {
                df = new DecimalFormat(",###,##0.00"); //保留二位小数
            }
            resultNum = df.format(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultNum;
    }

    /**
     * 格式化百分比
     * @return
     */
    public static String formatPercent(float percent) {
        if(percent > 0) {
            DecimalFormat fnum = new DecimalFormat("##0.00");
            return fnum.format(percent) + "%";
        }
        return "0.00%";
    }
    public static String formatPercent1(float percent) {
        if(percent > 0) {
            DecimalFormat fnum = new DecimalFormat("##0.00");
            return fnum.format(percent*100) + "%";
        }
        return "0%";
    }
    /**
     * 格式化百分比
     * @return
     */
    public static String formatPercent2(double percent) {
        if(percent > 0) {
            DecimalFormat fnum = new DecimalFormat("##0.00");
            return fnum.format(percent) + "%";
        }
        return "0.00%";
    }
    /**
     * 格式化千分符
     * @return
     */
    public static String formatThousand(double percent) {
        NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
        return numberFormat1.format(percent);
    }
    /**
     * 格式化千分符
     * @return
     */
    public static String formatThousand(long percent) {
        NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
        return numberFormat1.format(percent);
    }
    /**
     * 格式化千分符
     * @return
     */
    public static String formatThousand(float percent) {
        NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
        return numberFormat1.format(percent);
    }
    /**
     * 格式化千分符
     * @return
     */
    public static String formatThousand(BigDecimal percent) {
        NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
        return numberFormat1.format(percent);
    }
    /**
     * double保留两位小数
     * @return
     */
    public static double formatPercent3(double percent) {
        if(percent > 0) {
            DecimalFormat fnum = new DecimalFormat("##0.00");
            return NumberUtils.toDouble(fnum.format(percent));
        }
        return 0.00;
    }

    public static void main(String[] args) {

        System.out.println(formatPercent3(0.98903));

    }
}
