package com.ald.bigdata.modules.ad.entity;


import com.ald.bigdata.common.base.BaseEntity;

/**
 * 广告监测 异常数据
 */
public class AldAdAnomalyDataEntity extends BaseEntity {

    private String appKey;
    private String linkKey;
    private String mediaId;
    private String linkName;
    private String mediaName;
    private String diffTimeType;
    private String authTimes;
    private String ipclkCountType;
    private String openCount;
    private String anomalyClickCount;
    private String allClickCount;
    private String anomalyClickProp;
    private String newAuthUser;
    private String anomalyNewAuthUser;
    private String anomalyAuthUserProp;
    private String day;
    private String hour;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getLinkKey() {
        return linkKey;
    }

    public void setLinkKey(String linkKey) {
        this.linkKey = linkKey;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getIpclkCountType() {
        return ipclkCountType;
    }

    public void setIpclkCountType(String ipclkCountType) {
        this.ipclkCountType = ipclkCountType;
    }

    public String getDiffTimeType() {
        return diffTimeType;
    }

    public void setDiffTimeType(String diffTimeType) {
        this.diffTimeType = diffTimeType;
    }

    public String getOpenCount() {
        return openCount;
    }

    public void setOpenCount(String openCount) {
        this.openCount = openCount;
    }

    public String getAnomalyClickCount() {
        return anomalyClickCount;
    }

    public void setAnomalyClickCount(String anomalyClickCount) {
        this.anomalyClickCount = anomalyClickCount;
    }

    public String getAllClickCount() {
        return allClickCount;
    }

    public void setAllClickCount(String allClickCount) {
        this.allClickCount = allClickCount;
    }

    public String getAnomalyClickProp() {
        return anomalyClickProp;
    }

    public void setAnomalyClickProp(String anomalyClickProp) {
        this.anomalyClickProp = anomalyClickProp;
    }

    public String getNewAuthUser() {
        return newAuthUser;
    }

    public void setNewAuthUser(String newAuthUser) {
        this.newAuthUser = newAuthUser;
    }

    public String getAnomalyNewAuthUser() {
        return anomalyNewAuthUser;
    }

    public void setAnomalyNewAuthUser(String anomalyNewAuthUser) {
        this.anomalyNewAuthUser = anomalyNewAuthUser;
    }

    public String getAnomalyAuthUserProp() {
        return anomalyAuthUserProp;
    }

    public void setAnomalyAuthUserProp(String anomalyAuthUserProp) {
        this.anomalyAuthUserProp = anomalyAuthUserProp;
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

    public String getAuthTimes() {
        return authTimes;
    }

    public void setAuthTimes(String authTimes) {
        this.authTimes = authTimes;
    }
}
