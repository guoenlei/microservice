package com.aldwx.app.modules.ad.entity;

import com.aldwx.app.common.base.BaseEntity;

/**
 * 广告监测
 * @author
 * @description
 * @createTime
 **/
public class AdEntity extends BaseEntity {

    private String appKey;
    private String day;
    private String hour;
    private String linkName;
    private String linkKey;
    private long linkVisitorCount;
    private long linkAuthuserCount;
    private long linkOpenCount;
    private long linkPageCount;
    private long linkClickCount;
    private long linkNewerForApp;
    private long totalStayTime;
    private float secondaryStayTime;
    private long onePageCount;
    private float bounceRate;


    public AdEntity() {
    }


    public AdEntity(String appKey) {
        this.appKey = appKey;
    }

    public AdEntity(String appKey, String day, long linkVisitorCount, long linkAuthuserCount, long linkOpenCount, long linkPageCount, long linkClickCount, long linkNewerForApp, long totalStayTime, float secondaryStayTime, long onePageCount, float bounceRate) {
        this.appKey = appKey;
        this.day = day;
        this.linkVisitorCount = linkVisitorCount;
        this.linkAuthuserCount = linkAuthuserCount;
        this.linkOpenCount = linkOpenCount;
        this.linkPageCount = linkPageCount;
        this.linkClickCount = linkClickCount;
        this.linkNewerForApp = linkNewerForApp;
        this.totalStayTime = totalStayTime;
        this.secondaryStayTime = secondaryStayTime;
        this.onePageCount = onePageCount;
        this.bounceRate = bounceRate;
    }

    public String getLinkKey() {
        return linkKey;
    }

    public void setLinkKey(String linkKey) {
        this.linkKey = linkKey;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
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

    public long getLinkVisitorCount() {
        return linkVisitorCount;
    }

    public void setLinkVisitorCount(long linkVisitorCount) {
        this.linkVisitorCount = linkVisitorCount;
    }

    public long getLinkAuthuserCount() {
        return linkAuthuserCount;
    }

    public void setLinkAuthuserCount(long linkAuthuserCount) {
        this.linkAuthuserCount = linkAuthuserCount;
    }

    public long getLinkOpenCount() {
        return linkOpenCount;
    }

    public void setLinkOpenCount(long linkOpenCount) {
        this.linkOpenCount = linkOpenCount;
    }

    public long getLinkPageCount() {
        return linkPageCount;
    }

    public void setLinkPageCount(long linkPageCount) {
        this.linkPageCount = linkPageCount;
    }

    public long getLinkClickCount() {
        return linkClickCount;
    }

    public void setLinkClickCount(long linkClickCount) {
        this.linkClickCount = linkClickCount;
    }

    public long getLinkNewerForApp() {
        return linkNewerForApp;
    }

    public void setLinkNewerForApp(long linkNewerForApp) {
        this.linkNewerForApp = linkNewerForApp;
    }

    public long getTotalStayTime() {
        return totalStayTime;
    }

    public void setTotalStayTime(long totalStayTime) {
        this.totalStayTime = totalStayTime;
    }

    public float getSecondaryStayTime() {
        return secondaryStayTime;
    }

    public void setSecondaryStayTime(float secondaryStayTime) {
        this.secondaryStayTime = secondaryStayTime;
    }

    public long getOnePageCount() {
        return onePageCount;
    }

    public void setOnePageCount(long onePageCount) {
        this.onePageCount = onePageCount;
    }

    public float getBounceRate() {
        return bounceRate;
    }

    public void setBounceRate(float bounceRate) {
        this.bounceRate = bounceRate;
    }
}
