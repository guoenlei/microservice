package com.aldwx.app.modules.smart.entity;

import com.aldwx.app.common.base.BaseEntity;

/**
 * 智能外链
 * @author
 * @description
 * @createTime
 **/
public class SmartEntity extends BaseEntity {

    private String appKey;
    private String day;
    private String hour;
    private String appId;
    private String linkTypeGroup;
    private String linkType;
    private String osType;
    private long newComerCount;
    private long authNewComerCount;
    private long visitorCount;
    private long openCount;
    private long totalPageCount;
    private long avgStayTime;
    private long totalStayTime;
    private float bounceRate;
    private long onePageCount;

    private String linkName;

    private String appName;
    private String appLogo;
    private String appStyle;        //小程序或 公众号

    public SmartEntity(){}

    public SmartEntity(String appKey, String day, String hour, String appId, String linkTypeGroup, String linkType, String osType, long newComerCount, long authNewComerCount, long visitorCount, long openCount, long totalPageCount, long avgStayTime, long totalStayTime, float bounceRate, long onePageCount, String linkName, String appName, String appLogo, String appStyle) {
        this.appKey = appKey;
        this.day = day;
        this.hour = hour;
        this.appId = appId;
        this.linkTypeGroup = linkTypeGroup;
        this.linkType = linkType;
        this.osType = osType;
        this.newComerCount = newComerCount;
        this.authNewComerCount = authNewComerCount;
        this.visitorCount = visitorCount;
        this.openCount = openCount;
        this.totalPageCount = totalPageCount;
        this.avgStayTime = avgStayTime;
        this.totalStayTime = totalStayTime;
        this.bounceRate = bounceRate;
        this.onePageCount = onePageCount;
        this.linkName = linkName;
        this.appName = appName;
        this.appLogo = appLogo;
        this.appStyle = appStyle;
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getLinkTypeGroup() {
        return linkTypeGroup;
    }

    public void setLinkTypeGroup(String linkTypeGroup) {
        this.linkTypeGroup = linkTypeGroup;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public long getNewComerCount() {
        return newComerCount;
    }

    public void setNewComerCount(long newComerCount) {
        this.newComerCount = newComerCount;
    }

    public long getAuthNewComerCount() {
        return authNewComerCount;
    }

    public void setAuthNewComerCount(long authNewComerCount) {
        this.authNewComerCount = authNewComerCount;
    }

    public long getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(long visitorCount) {
        this.visitorCount = visitorCount;
    }

    public long getOpenCount() {
        return openCount;
    }

    public void setOpenCount(long openCount) {
        this.openCount = openCount;
    }

    public long getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public long getAvgStayTime() {
        return avgStayTime;
    }

    public void setAvgStayTime(long avgStayTime) {
        this.avgStayTime = avgStayTime;
    }

    public long getTotalStayTime() {
        return totalStayTime;
    }

    public void setTotalStayTime(long totalStayTime) {
        this.totalStayTime = totalStayTime;
    }

    public float getBounceRate() {
        return bounceRate;
    }

    public void setBounceRate(float bounceRate) {
        this.bounceRate = bounceRate;
    }

    public long getOnePageCount() {
        return onePageCount;
    }

    public void setOnePageCount(long onePageCount) {
        this.onePageCount = onePageCount;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    public String getAppStyle() {
        return appStyle;
    }

    public void setAppStyle(String appStyle) {
        this.appStyle = appStyle;
    }
}
