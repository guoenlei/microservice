package com.aldwx.app.modules.portrait.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ald_device_statistics
 * @author 
 */
public class AldDeviceStatistics implements Serializable {
    /**
     * 自增id
     */
    private Long id;

    /**
     * appkey
     */
    private String app_key;

    /**
     * 日期
     */
    private Long date;

    /**
     * 手机型号
     */
    private String phone_model;

    /**
     * 新访问用户数
     */
    private Integer new_user_count;

    /**
     * 打开次数
     */
    private Integer open_count;

    /**
     * 访问次数
     */
    private Integer page_count;

    /**
     * 访问人数
     */
    private Integer visitor_count;

    /**
     * 总停留时长
     */
    private Long total_stay_time;

    /**
     * 次均停留时长
     */
    private Float secondary_stay_time;

    /**
     * 跳出页个数
     */
    private Integer one_page_count;

    /**
     * 跳出率
     */
    private Float bounce_rate;

    /**
     * 更新时间
     */
    private Date update_at;

    /**
     * 日期
     */
    private Date day;

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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getPhone_model() {
        return phone_model;
    }

    public void setPhone_model(String phone_model) {
        this.phone_model = phone_model;
    }

    public Integer getNew_user_count() {
        return new_user_count;
    }

    public void setNew_user_count(Integer new_user_count) {
        this.new_user_count = new_user_count;
    }

    public Integer getOpen_count() {
        return open_count;
    }

    public void setOpen_count(Integer open_count) {
        this.open_count = open_count;
    }

    public Integer getPage_count() {
        return page_count;
    }

    public void setPage_count(Integer page_count) {
        this.page_count = page_count;
    }

    public Integer getVisitor_count() {
        return visitor_count;
    }

    public void setVisitor_count(Integer visitor_count) {
        this.visitor_count = visitor_count;
    }

    public Long getTotal_stay_time() {
        return total_stay_time;
    }

    public void setTotal_stay_time(Long total_stay_time) {
        this.total_stay_time = total_stay_time;
    }

    public Float getSecondary_stay_time() {
        return secondary_stay_time;
    }

    public void setSecondary_stay_time(Float secondary_stay_time) {
        this.secondary_stay_time = secondary_stay_time;
    }

    public Integer getOne_page_count() {
        return one_page_count;
    }

    public void setOne_page_count(Integer one_page_count) {
        this.one_page_count = one_page_count;
    }

    public Float getBounce_rate() {
        return bounce_rate;
    }

    public void setBounce_rate(Float bounce_rate) {
        this.bounce_rate = bounce_rate;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}