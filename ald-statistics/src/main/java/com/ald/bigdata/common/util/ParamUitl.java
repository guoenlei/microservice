package com.ald.bigdata.common.util;

import com.ald.bigdata.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class ParamUitl {


    /**
     * 对日期参数的校验
     * @param date
     * @return
     */
    public static boolean checkDate(String date) {
        boolean flag = false;
        if(StringUtils.isNotEmpty(date)) {
            if(!date.contains(Constants.FLAG_01)) {
                if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME) ||
                        date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME) ||
                        date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME) ||
                        date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                    return flag;
                }
            } else {
//                flag = true;
            }
        }
        return flag;
    }


    /**
     * 对分页参数判断处理
     * @param total
     * @param currentPage
     * @return
     */
    public static boolean checkPage(String total, String currentPage) {
        boolean flag = false;
        if(StringUtils.isNotBlank(total) && StringUtils.isNotBlank(currentPage)) {
            if(isInteger(total) && isInteger(currentPage)) {
                Integer iTotal = Integer.parseInt(total);
                Integer iCurrentPage = Integer.parseInt(currentPage);
                if(iTotal >= 0 && iCurrentPage >=0) {
                    flag = true;
                }
            }
        }
        return flag;
    }


    /**
     * 日期格式化
     * 将区间为今天或昨天的转为1,2
     * 如 date为2018-01-01~2018-01-01 转为 2018-01-01
     * @return
     */
    public static String formatDate(String date) {
        String result = date;
        if(StringUtils.isNotBlank(date)) {
            String d = date.replaceAll("\\s*", "");
            if(d.contains(Constants.FLAG_01)) {
                String[] dates = StringUtils.splitPreserveAllTokens(d, Constants.FLAG_01);
                if(dates.length == 2 && dates[0].equals(dates[1])) {
                    if(dates[0].equals(DateUtil.getTodayDate())) {
                        result = Constants.ALDSTAT_EVENT_TODAY_TIME;
                    } else if(dates[0].equals(DateUtil.getYesterday())){
                        result = Constants.ALDSTAT_EVENT_YESTERDAY_TIME;
                    }
                }
            }
        }

        return result;
    }



    /**
     * 判断为整数
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}