package com.aldwx.app.modules.share.entity;

import com.aldwx.app.common.base.BaseEntity;

import java.util.Date;

/**
 * @author
 * @description
 * @createTime
 **/
public class ShareEntity extends BaseEntity {
    private String app_key;
    private Date day;
    private Integer hour;
    private Integer share_user_count;
    private Integer new_count;
    private Integer share_count;
    private Integer share_open_count;
    private Integer share_open_user_count;
    private Double share_reflux_ratio;
    private Date update_at;
    //三度分享
    private Integer first_share_user_count;
    private Integer first_share_count;
    private Integer secondary_share_user_count;
    private Integer secondary_share_count;
    private Integer third_share_user_count;
    private Integer third_share_count;
    //回流
    private Integer status;
    private String nickname;
    private String avatar_url;
    private String gender;
    private String source;
    private Integer count;

    //男女 未知占比
    private String manrate;
    private String womenrate;
    private String unknownrate;


    public String getManrate() {
        return manrate;
    }

    public void setManrate(String manrate) {
        this.manrate = manrate;
    }

    public String getWomenrate() {
        return womenrate;
    }

    public void setWomenrate(String womenrate) {
        this.womenrate = womenrate;
    }

    public String getUnknownrate() {
        return unknownrate;
    }

    public void setUnknownrate(String unknownrate) {
        this.unknownrate = unknownrate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Integer getFirst_share_user_count() {
        return first_share_user_count;
    }

    public void setFirst_share_user_count(Integer first_share_user_count) {
        this.first_share_user_count = first_share_user_count;
    }

    public Integer getFirst_share_count() {
        return first_share_count;
    }

    public void setFirst_share_count(Integer first_share_count) {
        this.first_share_count = first_share_count;
    }

    public Integer getSecondary_share_user_count() {
        return secondary_share_user_count;
    }

    public void setSecondary_share_user_count(Integer secondary_share_user_count) {
        this.secondary_share_user_count = secondary_share_user_count;
    }

    public Integer getSecondary_share_count() {
        return secondary_share_count;
    }

    public void setSecondary_share_count(Integer secondary_share_count) {
        this.secondary_share_count = secondary_share_count;
    }

    public Integer getThird_share_user_count() {
        return third_share_user_count;
    }

    public void setThird_share_user_count(Integer third_share_user_count) {
        this.third_share_user_count = third_share_user_count;
    }

    public Integer getThird_share_count() {
        return third_share_count;
    }

    public void setThird_share_count(Integer third_share_count) {
        this.third_share_count = third_share_count;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getShare_user_count() {
        return share_user_count;
    }

    public void setShare_user_count(Integer share_user_count) {
        this.share_user_count = share_user_count;
    }

    public Integer getNew_count() {
        return new_count;
    }

    public void setNew_count(Integer new_count) {
        this.new_count = new_count;
    }

    public Integer getShare_count() {
        return share_count;
    }

    public void setShare_count(Integer share_count) {
        this.share_count = share_count;
    }

    public Integer getShare_open_count() {
        return share_open_count;
    }

    public void setShare_open_count(Integer share_open_count) {
        this.share_open_count = share_open_count;
    }

    public Integer getShare_open_user_count() {
        return share_open_user_count;
    }

    public void setShare_open_user_count(Integer share_open_user_count) {
        this.share_open_user_count = share_open_user_count;
    }

    public Double getShare_reflux_ratio() {
        return share_reflux_ratio;
    }

    public void setShare_reflux_ratio(Double share_reflux_ratio) {
        this.share_reflux_ratio = share_reflux_ratio;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
}
