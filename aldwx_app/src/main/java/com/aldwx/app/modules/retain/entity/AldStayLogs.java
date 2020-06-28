package com.aldwx.app.modules.retain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ald_stay_logs
 * @author 
 */
public class AldStayLogs implements Serializable {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 小程序标识
     */
    private String app_key;

    /**
     * 统计日期
     */
    private Date day;

    /**
     * 几天后，0表示当天，3表示3天后
     */
    private Integer day_after;

    /**
     * 新增用户的人数
     */
    private Integer new_people_left;

    /**
     * 活跃用户的人数
     */
    private Integer people_left;

    /**
     * 新用户留存比
     */
    private Float new_people_ratio;

    /**
     * 活跃用户留存比
     */
    private Float active_people_ratio;

    /**
     * 渠道key
     */
    private String source_key;

    /**
     * 渠道value
     */
    private String source_value;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getDay_after() {
        return day_after;
    }

    public void setDay_after(Integer day_after) {
        this.day_after = day_after;
    }

    public Integer getNew_people_left() {
        return new_people_left;
    }

    public void setNew_people_left(Integer new_people_left) {
        this.new_people_left = new_people_left;
    }

    public Integer getPeople_left() {
        return people_left;
    }

    public void setPeople_left(Integer people_left) {
        this.people_left = people_left;
    }

    public Float getNew_people_ratio() {
        return new_people_ratio;
    }

    public void setNew_people_ratio(Float new_people_ratio) {
        this.new_people_ratio = new_people_ratio;
    }

    public Float getActive_people_ratio() {
        return active_people_ratio;
    }

    public void setActive_people_ratio(Float active_people_ratio) {
        this.active_people_ratio = active_people_ratio;
    }

    public String getSource_key() {
        return source_key;
    }

    public void setSource_key(String source_key) {
        this.source_key = source_key;
    }

    public String getSource_value() {
        return source_value;
    }

    public void setSource_value(String source_value) {
        this.source_value = source_value;
    }
}