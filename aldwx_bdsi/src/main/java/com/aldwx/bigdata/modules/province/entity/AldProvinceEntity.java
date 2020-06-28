package com.aldwx.bigdata.modules.province.entity;

import com.aldwx.bigdata.common.base.BaseEntity;

public class AldProvinceEntity extends BaseEntity {


    private String appKey;                  //appkey
    private String day;                     //日期
    private String province;                //省份
    private String newUserCount;            //新访问用户数
    private String visitorCount;            //访问人数
    private String openCount;               //打开次数
    private String pageCount;               //访问次数
    private String totalStayTime;           //平均停留时长
    private String secondaryStayTime;       //次均停留时长
    private String onePageCount;            //跳出页个数
    private String bounceRate;              //跳出率

    public AldProvinceEntity() {super();}

    public AldProvinceEntity(String appKey, String day, String province, String newUserCount, String visitorCount,
                             String openCount, String pageCount, String totalStayTime, String secondaryStayTime,
                             String onePageCount, String bounceRate) {
        super();
        this.appKey = appKey;
        this.day = day;
        this.province = province;
        this.newUserCount = newUserCount;
        this.visitorCount = visitorCount;
        this.openCount = openCount;
        this.pageCount = pageCount;
        this.totalStayTime = totalStayTime;
        this.secondaryStayTime = secondaryStayTime;
        this.onePageCount = onePageCount;
        this.bounceRate = bounceRate;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNewUserCount() {
        return newUserCount;
    }

    public void setNewUserCount(String newUserCount) {
        this.newUserCount = newUserCount;
    }

    public String getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(String visitorCount) {
        this.visitorCount = visitorCount;
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
}
