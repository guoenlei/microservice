package com.aldwx.bigdata.modules.report.vo;

import com.aldwx.bigdata.common.base.BaseVo;
import com.aldwx.bigdata.modules.report.util.DateUtil;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author
 * @description
 * @createTime
 **/
public class AldReportVo extends BaseVo {

    private long startTime;
    private long endTime;
    private String startDate;
    private String endDate;
    private List<String> userIds;
    private List<String> dateList;
    private boolean isPartner = false;
    private String platform;

    public boolean isPartner() {
        return isPartner;
    }

    public void setPartner(boolean partner) {
        isPartner = partner;
    }

    public AldReportVo() {}

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public AldReportVo setYesterdayTime(AldReportVo aldReportVo) {
        aldReportVo.setStartTime(DateUtil.yesterdayStartTime());
        aldReportVo.setEndTime(DateUtil.yesterdayEndTime());
        return aldReportVo;
    }


    public AldReportVo setWorkdayTime(AldReportVo aldReportVo) {
        aldReportVo.setStartTime(DateUtil.weekdayStartTime());
        aldReportVo.setEndTime(DateUtil.weekdayEndTime());
        return aldReportVo;
    }


    public AldReportVo setMonthTime(AldReportVo aldReportVo) {
        aldReportVo.setStartTime(DateUtil.monthStartTime());
        aldReportVo.setEndTime(DateUtil.monthEndTime());
        return aldReportVo;
    }

    public AldReportVo setYearTime(AldReportVo aldReportVo) {
        aldReportVo.setStartTime(DateUtil.monthStartTime());
        aldReportVo.setEndTime(DateUtil.monthEndTime());
        return aldReportVo;
    }

    public AldReportVo setYesterdayDate(AldReportVo aldReportVo) {
        aldReportVo.setStartDate(DateUtil.yesterdayStartDate());
        aldReportVo.setEndDate(DateUtil.yesterdayEndDate());
        return aldReportVo;
    }


    public AldReportVo setWorkdayDate(AldReportVo aldReportVo) {
        aldReportVo.setStartDate(DateUtil.weekdayStartDate());
        aldReportVo.setEndDate(DateUtil.weekdayEndDate());
        return aldReportVo;
    }


    public AldReportVo setMonthDate(AldReportVo aldReportVo) {
        aldReportVo.setStartDate(DateUtil.monthStartDate());
        aldReportVo.setEndDate(DateUtil.monthEndDate());
        return aldReportVo;
    }

    public AldReportVo setYearDate(AldReportVo aldReportVo) {
        aldReportVo.setStartDate(DateUtil.monthStartDate());
        aldReportVo.setEndDate(DateUtil.monthEndDate());
        return aldReportVo;
    }

    //处理yyyy-MM-dd
    public AldReportVo setTimeDate(AldReportVo aldReportVo, String dateType) {
        if(StringUtils.isNotBlank(dateType)) {
            if(dateType.equals("1")) {
                aldReportVo.setYesterdayDate(aldReportVo);
            } else if(dateType.equals("2")) {
                aldReportVo.setWorkdayDate(aldReportVo);
            } else if(dateType.equals("3")) {
                aldReportVo.setMonthDate(aldReportVo);
            } else if(dateType.equals("4")) {
                aldReportVo.setMonthDate(aldReportVo);
            }
        }

        return aldReportVo;
    }

    public AldReportVo setTime(AldReportVo aldReportVo, String dateType) {
        if(StringUtils.isNotBlank(dateType)) {
            if(dateType.equals("1")) {
                aldReportVo.setYesterdayTime(aldReportVo);
            } else if(dateType.equals("2")) {
                aldReportVo.setWorkdayTime(aldReportVo);
            } else if(dateType.equals("3")) {
                aldReportVo.setMonthTime(aldReportVo);
            } else if(dateType.equals("4")) {
                aldReportVo.setMonthTime(aldReportVo);
            }
        }

        return aldReportVo;
    }


    public AldReportVo setDate(AldReportVo aldReportVo, String dateType) {
        if(StringUtils.isNotBlank(dateType)) {
            if(dateType.equals("1")) {
                aldReportVo.setYesterdayDate(aldReportVo);
            } else if(dateType.equals("2")) {
                aldReportVo.setWorkdayDate(aldReportVo);
            } else if(dateType.equals("3")) {
                aldReportVo.setMonthDate(aldReportVo);
            } else if(dateType.equals("4")) {
                aldReportVo.setMonthDate(aldReportVo);
            }
        }

        return aldReportVo;
    }


    public AldReportVo setDateList(AldReportVo aldReportVo, String dateType) {
        if(StringUtils.isNotBlank(dateType)) {
            List<String> data = Lists.newArrayList();
            if(dateType.equals("1")) {      //昨天
                String yesterday = DateUtil.getYesterday();
                data.add(yesterday);

                aldReportVo.setDateList(data);
            } else if(dateType.equals("2")) {       //最近一周
                String start = DateUtil.getPastDate(7);
                String end = DateUtil.getPastDate(0);
                List<String> weekday = DateUtil.getBetweenDates3(start, end);

                aldReportVo.setDateList(weekday);
            } else if(dateType.equals("3")) {          //最近1月
                String start = DateUtil.getPastDate(30);
                String end = DateUtil.getPastDate(0);
                List<String> month = DateUtil.getBetweenDates3(start, end);

                aldReportVo.setDateList(month);
            /*} else if(dateType.equals("4")) {
                aldReportVo.setMonthTime(aldReportVo);*/
            }
        }

        return aldReportVo;
    }

}
