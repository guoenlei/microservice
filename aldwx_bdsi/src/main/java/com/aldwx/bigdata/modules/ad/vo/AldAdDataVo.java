package com.aldwx.bigdata.modules.ad.vo;

import com.aldwx.bigdata.common.base.BaseVo;

public class AldAdDataVo extends BaseVo {

    private String appKey;
    private String date;
    private String hour;
    private String visitorCount;
    private String authuserCount;
    private String openCount;
    private String pageCount;
    private String clickCount;
    private String newerForApp;
    private String totalStayTime;
    private String secondaryStayTime;
    private String onePageCount;
    private String bounceRate;


    private String tableName1;
    private String tableName2;

    public AldAdDataVo() {
        super();
    }

    public AldAdDataVo(String appKey) {
        super();
        this.appKey = appKey;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(String visitorCount) {
        this.visitorCount = visitorCount;
    }

    public String getAuthuserCount() {
        return authuserCount;
    }

    public void setAuthuserCount(String authuserCount) {
        this.authuserCount = authuserCount;
    }

    public String getOpenCount() {
        return openCount;
    }

    public void setOpenCount(String openCount) {
        this.openCount = openCount;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getClickCount() {
        return clickCount;
    }

    public void setClickCount(String clickCount) {
        this.clickCount = clickCount;
    }

    public String getNewerForApp() {
        return newerForApp;
    }

    public void setNewerForApp(String newerForApp) {
        this.newerForApp = newerForApp;
    }

    public String getTotalStayTime() {
        return totalStayTime;
    }

    public void setTotalStayTime(String totalStayTime) {
        this.totalStayTime = totalStayTime;
    }

    public String getSecondaryStayTime() {
        return secondaryStayTime;
    }

    public void setSecondaryStayTime(String secondaryStayTime) {
        this.secondaryStayTime = secondaryStayTime;
    }

    public String getOnePageCount() {
        return onePageCount;
    }

    public void setOnePageCount(String onePageCount) {
        this.onePageCount = onePageCount;
    }

    public String getBounceRate() {
        return bounceRate;
    }

    public void setBounceRate(String bounceRate) {
        this.bounceRate = bounceRate;
    }

    public String getTableName1() {
        return tableName1;
    }

    public void setTableName1(String tableName1) {
        this.tableName1 = tableName1;
    }

    public String getTableName2() {
        return tableName2;
    }

    public void setTableName2(String tableName2) {
        this.tableName2 = tableName2;
    }
}
