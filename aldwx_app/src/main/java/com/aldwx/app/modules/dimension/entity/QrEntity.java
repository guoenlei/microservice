package com.aldwx.app.modules.dimension.entity;

import com.aldwx.app.common.base.BaseEntity;

/**
 * 二维码
 * @author
 * @description
 * @createTime
 **/
public class QrEntity extends BaseEntity {

    private String appKey;
    private String day;
    private String hour;
    private String qrId;
    private String qrName;
    private long qrCount;
    private String pageUrl;
    private long totalScanUserCount;
    private long newScanUserCount;
    private long totalScanCount;
    private long newScanCount;
    private String qrCreateTime;
    private long qrNewComerForApp;
    private long avgScanCountTotal;
    private long avgScanCountNew;
    private String params;
    private String qrKey;

    public QrEntity() {}

    public QrEntity(String appKey, String day, String qrId, String qrName, String pageUrl, long totalScanUserCount, long newScanUserCount, long totalScanCount, long newScanCount, String qrCreateTime, long qrNewComerForApp, long avgScanCountTotal, long avgScanCountNew, String params, String qrKey) {
        this.appKey = appKey;
        this.day = day;
        this.qrId = qrId;
        this.qrName = qrName;
        this.pageUrl = pageUrl;
        this.totalScanUserCount = totalScanUserCount;
        this.newScanUserCount = newScanUserCount;
        this.totalScanCount = totalScanCount;
        this.newScanCount = newScanCount;
        this.qrCreateTime = qrCreateTime;
        this.qrNewComerForApp = qrNewComerForApp;
        this.avgScanCountTotal = avgScanCountTotal;
        this.avgScanCountNew = avgScanCountNew;
        this.params = params;
        this.qrKey = qrKey;
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

    public long getQrCount() {
        return qrCount;
    }

    public void setQrCount(long qrCount) {
        this.qrCount = qrCount;
    }

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
    }

    public String getQrName() {
        return qrName;
    }

    public void setQrName(String qrName) {
        this.qrName = qrName;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public long getTotalScanUserCount() {
        return totalScanUserCount;
    }

    public void setTotalScanUserCount(long totalScanUserCount) {
        this.totalScanUserCount = totalScanUserCount;
    }

    public long getNewScanUserCount() {
        return newScanUserCount;
    }

    public void setNewScanUserCount(long newScanUserCount) {
        this.newScanUserCount = newScanUserCount;
    }

    public long getTotalScanCount() {
        return totalScanCount;
    }

    public void setTotalScanCount(long totalScanCount) {
        this.totalScanCount = totalScanCount;
    }

    public long getNewScanCount() {
        return newScanCount;
    }

    public void setNewScanCount(long newScanCount) {
        this.newScanCount = newScanCount;
    }

    public String getQrCreateTime() {
        return qrCreateTime;
    }

    public void setQrCreateTime(String qrCreateTime) {
        this.qrCreateTime = qrCreateTime;
    }

    public long getQrNewComerForApp() {
        return qrNewComerForApp;
    }

    public void setQrNewComerForApp(long qrNewComerForApp) {
        this.qrNewComerForApp = qrNewComerForApp;
    }

    public long getAvgScanCountTotal() {
        return avgScanCountTotal;
    }

    public void setAvgScanCountTotal(long avgScanCountTotal) {
        this.avgScanCountTotal = avgScanCountTotal;
    }

    public long getAvgScanCountNew() {
        return avgScanCountNew;
    }

    public void setAvgScanCountNew(long avgScanCountNew) {
        this.avgScanCountNew = avgScanCountNew;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getQrKey() {
        return qrKey;
    }

    public void setQrKey(String qrKey) {
        this.qrKey = qrKey;
    }
}
