package com.ald.bigdata.modules.ad.entity;


import com.ald.bigdata.common.base.BaseEntity;

public class AldAdTargetApiEntity extends BaseEntity {

    private String appKey;
    private String appId;
    private String appName;
    private String mediaName;
    private String linkName;
    private long openCount;
    private long visitorCount;
    private long newUserCount;
    private long newAuthCount;

    public String getAppKey() {
        return appKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public long getOpenCount() {
        return openCount;
    }

    public void setOpenCount(long openCount) {
        this.openCount = openCount;
    }

    public long getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(long visitorCount) {
        this.visitorCount = visitorCount;
    }

    public long getNewUserCount() {
        return newUserCount;
    }

    public void setNewUserCount(long newUserCount) {
        this.newUserCount = newUserCount;
    }

    public long getNewAuthCount() {
        return newAuthCount;
    }

    public void setNewAuthCount(long newAuthCount) {
        this.newAuthCount = newAuthCount;
    }
}
