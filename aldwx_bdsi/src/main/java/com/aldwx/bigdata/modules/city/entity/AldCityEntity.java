package com.aldwx.bigdata.modules.city.entity;

import com.aldwx.bigdata.common.base.BaseEntity;


/**
 * 城市
 */
public class AldCityEntity extends BaseEntity {


    private String appKey;                  //appkey
    private String day;                     //日期
    private String city;                    //城市
    private String newUserCount;            //新访问用户数
    private String visitorCount;            //访问人数
    private String openCount;               //打开次数
    private String pageCount;               //访问次数
    private String totalStayTime;           //平均停留时长
    private String secondaryStayTime;       //次均停留时长
    private String onePageCount;            //跳出页个数
    private String bounceRate;              //跳出率

    public AldCityEntity() {super();}

    public AldCityEntity(String appKey, String day, String city, String newUserCount, String visitorCount, String openCount, String pageCount, String totalStayTime,
                         String secondaryStayTime, String onePageCount, String bounceRate) {
        this.appKey = appKey;
        this.day = day;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
