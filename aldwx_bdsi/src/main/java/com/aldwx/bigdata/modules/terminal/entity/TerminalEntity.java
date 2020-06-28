package com.aldwx.bigdata.modules.terminal.entity;


import com.aldwx.bigdata.common.base.BaseEntity;

import java.util.Date;

public class TerminalEntity extends BaseEntity {

    private String appKey;                      //小程序 Key
    private Date day;                           //日期
    private String type;                        //终端的总体类型
    private String typeValue;                   //每个类型下的小类型,
    private int newComerCount;                  //新访问用户数
    private int visitorCount;
    private int openCount;
    private int totalPageCount;
    private double avgStayTime;
    private float bounceRate;
    private int totalStayTime;
    private int onePageCount;

    private final String TABLE_NAME = "aldstat_terminal_analysis";
    private final String TABLE_NAME_7_DAY = "aldstat_7days_terminal_analysis";
    private final String TABLE_NAME_30_DAY = "aldstat_30days_terminal_analysis";

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public int getNewComerCount() {
        return newComerCount;
    }

    public void setNewComerCount(int newComerCount) {
        this.newComerCount = newComerCount;
    }

    public int getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(int visitorCount) {
        this.visitorCount = visitorCount;
    }

    public int getOpenCount() {
        return openCount;
    }

    public void setOpenCount(int openCount) {
        this.openCount = openCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public double getAvgStayTime() {
        return avgStayTime;
    }

    public void setAvgStayTime(double avgStayTime) {
        this.avgStayTime = avgStayTime;
    }

    public float getBounceRate() {
        return bounceRate;
    }

    public void setBounceRate(float bounceRate) {
        this.bounceRate = bounceRate;
    }

    public int getTotalStayTime() {
        return totalStayTime;
    }

    public void setTotalStayTime(int totalStayTime) {
        this.totalStayTime = totalStayTime;
    }

    public int getOnePageCount() {
        return onePageCount;
    }

    public void setOnePageCount(int onePageCount) {
        this.onePageCount = onePageCount;
    }
}
