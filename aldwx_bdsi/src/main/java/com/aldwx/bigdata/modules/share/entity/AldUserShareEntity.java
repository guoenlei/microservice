package com.aldwx.bigdata.modules.share.entity;

import com.aldwx.bigdata.common.base.BaseEntity;


public class AldUserShareEntity extends BaseEntity {

    private String appKey;
    private String shareUuid;
    private String uuid;
    private String nickName;
    private String avatarUrl;
    private String userRemark;
    private String newCount;
    private String shareCount;
    private String shareOpenCount;
    private String shareOpenUserCount;
    private String shareRefluxRatio;


    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getShareUuid() {
        return shareUuid;
    }

    public void setShareUuid(String shareUuid) {
        this.shareUuid = shareUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getNewCount() {
        return newCount;
    }

    public void setNewCount(String newCount) {
        this.newCount = newCount;
    }

    public String getShareCount() {
        return shareCount;
    }

    public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }

    public String getShareOpenCount() {
        return shareOpenCount;
    }

    public void setShareOpenCount(String shareOpenCount) {
        this.shareOpenCount = shareOpenCount;
    }

    public String getShareOpenUserCount() {
        return shareOpenUserCount;
    }

    public void setShareOpenUserCount(String shareOpenUserCount) {
        this.shareOpenUserCount = shareOpenUserCount;
    }

    public String getShareRefluxRatio() {
        return shareRefluxRatio;
    }

    public void setShareRefluxRatio(String shareRefluxRatio) {
        this.shareRefluxRatio = shareRefluxRatio;
    }
}
