package com.aldwx.app.modules.retain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * aldstat_visit_duration
 * @author 
 */
public class AldstatVisitDuration implements Serializable {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 小程序 Key
     */
    private String app_key;

    private Date day;

    /**
     * 1:0-2秒,2:3-5秒,3:6-10,4:11-20,5:20-30,6:30-50,7:50-100,8:大于100秒
     */
    private Integer visit_duration;

    /**
     * 访问人数
     */
    private Integer visitor_count;

    /**
     * 访问人数占比
     */
    private Double visitor_ratio;

    /**
     * 打开次数
     */
    private Integer open_count;

    private Double open_ratio;

    /**
     * 更新时间
     */
    private Date update_at;

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

    public Integer getVisit_duration() {
        return visit_duration;
    }

    public void setVisit_duration(Integer visit_duration) {
        this.visit_duration = visit_duration;
    }

    public Integer getVisitor_count() {
        return visitor_count;
    }

    public void setVisitor_count(Integer visitor_count) {
        this.visitor_count = visitor_count;
    }

    public Double getVisitor_ratio() {
        return visitor_ratio;
    }

    public void setVisitor_ratio(Double visitor_ratio) {
        this.visitor_ratio = visitor_ratio;
    }

    public Integer getOpen_count() {
        return open_count;
    }

    public void setOpen_count(Integer open_count) {
        this.open_count = open_count;
    }

    public Double getOpen_ratio() {
        return open_ratio;
    }

    public void setOpen_ratio(Double open_ratio) {
        this.open_ratio = open_ratio;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
}