package com.aldwx.app.modules.trend.entity;

import com.aldwx.app.common.base.BaseEntity;

/**
 * 趋势分析
 * @author
 * @description
 * @createTime
 **/
public class TrendEntity extends BaseEntity {

    private String appKey;
    private String day;
    private int hour;
    private long newComerCount;
    private long visitorCount;
    private long openCount;
    private long totalPageCount;
    private double avgStayTime;
    private double secondaryAvgStayTime;
    private double avgRefreshCount;
    private long totalStayTime;
    private long dailyShareCount;
    private double bounceRate;
    private long dailyRefreshCount;
    private long reachBottomCount;
    private double avgReachBottomCount;
    private long onePageCount;
    private long totalVisitorCount;

    public TrendEntity() {
    }

    public TrendEntity(String appKey) {
        this.appKey = appKey;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public long getNewComerCount() {
        return newComerCount;
    }

    public void setNewComerCount(long newComerCount) {
        this.newComerCount = newComerCount;
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

    public double getAvgStayTime() {
        return avgStayTime;
    }

    public void setAvgStayTime(double avgStayTime) {
        this.avgStayTime = avgStayTime;
    }

    public double getSecondaryAvgStayTime() {
        return secondaryAvgStayTime;
    }

    public void setSecondaryAvgStayTime(double secondaryAvgStayTime) {
        this.secondaryAvgStayTime = secondaryAvgStayTime;
    }

    public double getBounceRate() {
        return bounceRate;
    }

    public void setBounceRate(double bounceRate) {
        this.bounceRate = bounceRate;
    }

    public double getAvgRefreshCount() {
        return avgRefreshCount;
    }

    public void setAvgRefreshCount(double avgRefreshCount) {
        this.avgRefreshCount = avgRefreshCount;
    }

    public long getTotalStayTime() {
        return totalStayTime;
    }

    public void setTotalStayTime(long totalStayTime) {
        this.totalStayTime = totalStayTime;
    }

    public long getDailyShareCount() {
        return dailyShareCount;
    }

    public void setDailyShareCount(long dailyShareCount) {
        this.dailyShareCount = dailyShareCount;
    }


    public long getDailyRefreshCount() {
        return dailyRefreshCount;
    }

    public void setDailyRefreshCount(long dailyRefreshCount) {
        this.dailyRefreshCount = dailyRefreshCount;
    }

    public long getReachBottomCount() {
        return reachBottomCount;
    }

    public void setReachBottomCount(long reachBottomCount) {
        this.reachBottomCount = reachBottomCount;
    }

    public double getAvgReachBottomCount() {
        return avgReachBottomCount;
    }

    public void setAvgReachBottomCount(double avgReachBottomCount) {
        this.avgReachBottomCount = avgReachBottomCount;
    }

    public long getOnePageCount() {
        return onePageCount;
    }

    public void setOnePageCount(long onePageCount) {
        this.onePageCount = onePageCount;
    }

    public long getTotalVisitorCount() {
        return totalVisitorCount;
    }

    public void setTotalVisitorCount(long totalVisitorCount) {
        this.totalVisitorCount = totalVisitorCount;
    }
}
