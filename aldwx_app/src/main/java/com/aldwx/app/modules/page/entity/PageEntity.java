package com.aldwx.app.modules.page.entity;

import com.aldwx.app.common.base.BaseEntity;

/**
 * 受访页
 * @author lx
 * @description
 * @createTime
 **/
public class PageEntity extends BaseEntity {

    private String appKey;
    private String day;
    private String pagePath;
    private long pageCount;
    private long visitorCount;
    private long downstreamCount;
    private long totalStayTime;
    private long abortPageCount;
    private long entryPageCount;
    private float abortRatio;
    private long shareCount;
    private long openCount;
    private long avgStayTime;
    private float bounceRate;
    private long totalTime;
    private long onePageCount;

    public PageEntity() {
    }

    public PageEntity(String appKey, String day, String pagePath, long pageCount, long visitorCount, long downstreamCount, long totalStayTime, long abortPageCount, float abortRatio, long shareCount, long openCount, long avgStayTime, float bounceRate, long totalTime) {
        this.appKey = appKey;
        this.day = day;
        this.pagePath = pagePath;
        this.pageCount = pageCount;
        this.visitorCount = visitorCount;
        this.downstreamCount = downstreamCount;
        this.totalStayTime = totalStayTime;
        this.abortPageCount = abortPageCount;
        this.abortRatio = abortRatio;
        this.shareCount = shareCount;
        this.openCount = openCount;
        this.avgStayTime = avgStayTime;
        this.bounceRate = bounceRate;
        this.totalTime = totalTime;
    }

    public PageEntity(String appKey) {
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

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(long visitorCount) {
        this.visitorCount = visitorCount;
    }

    public long getDownstreamCount() {
        return downstreamCount;
    }

    public void setDownstreamCount(long downstreamCount) {
        this.downstreamCount = downstreamCount;
    }

    public long getTotalStayTime() {
        return totalStayTime;
    }

    public void setTotalStayTime(long totalStayTime) {
        this.totalStayTime = totalStayTime;
    }

    public long getAbortPageCount() {
        return abortPageCount;
    }

    public void setAbortPageCount(long abortPageCount) {
        this.abortPageCount = abortPageCount;
    }

    public float getAbortRatio() {
        return abortRatio;
    }

    public void setAbortRatio(float abortRatio) {
        this.abortRatio = abortRatio;
    }

    public long getShareCount() {
        return shareCount;
    }

    public void setShareCount(long shareCount) {
        this.shareCount = shareCount;
    }

    public long getOpenCount() {
        return openCount;
    }

    public void setOpenCount(long openCount) {
        this.openCount = openCount;
    }

    public long getAvgStayTime() {
        return avgStayTime;
    }

    public void setAvgStayTime(long avgStayTime) {
        this.avgStayTime = avgStayTime;
    }

    public float getBounceRate() {
        return bounceRate;
    }

    public void setBounceRate(float bounceRate) {
        this.bounceRate = bounceRate;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public long getEntryPageCount() {
        return entryPageCount;
    }

    public void setEntryPageCount(long entryPageCount) {
        this.entryPageCount = entryPageCount;
    }

    public long getOnePageCount() {
        return onePageCount;
    }

    public void setOnePageCount(long onePageCount) {
        this.onePageCount = onePageCount;
    }
}
