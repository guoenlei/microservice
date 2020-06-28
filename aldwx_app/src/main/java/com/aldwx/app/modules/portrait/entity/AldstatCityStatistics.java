package com.aldwx.app.modules.portrait.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * aldstat_city_statistics
 * @author 
 */
public class AldstatCityStatistics implements Serializable {
    private Integer id;

    /**
     * appkey
     */
    private String app_key;

    /**
     * 日期
     */
    private Date day;

    /**
     * 市
     */
    private String city;

    /**
     * 新访问用户数
     */
    private Integer new_user_count;

    /**
     * 访问人数
     */
    private Integer visitor_count;

    /**
     * 打开次数
     */
    private Integer open_count;

    /**
     * 访问次数
     */
    private Integer page_count;

    private Long total_stay_time;

    /**
     * 次均停留时长
     */
    private Double secondary_stay_time;

    /**
     * 跳出页个数
     */
    private Integer one_page_count;

    /**
     * 跳出率
     */
    private Double bounce_rate;

    /**
     * 更新时间
     */
    private Date update_at;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNew_user_count() {
        return new_user_count;
    }

    public void setNew_user_count(Integer new_user_count) {
        this.new_user_count = new_user_count;
    }

    public Integer getVisitor_count() {
        return visitor_count;
    }

    public void setVisitor_count(Integer visitor_count) {
        this.visitor_count = visitor_count;
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

    public Long getTotal_stay_time() {
        return total_stay_time;
    }

    public void setTotal_stay_time(Long total_stay_time) {
        this.total_stay_time = total_stay_time;
    }

    public Double getSecondary_stay_time() {
        return secondary_stay_time;
    }

    public void setSecondary_stay_time(Double secondary_stay_time) {
        this.secondary_stay_time = secondary_stay_time;
    }

    public Integer getOne_page_count() {
        return one_page_count;
    }

    public void setOne_page_count(Integer one_page_count) {
        this.one_page_count = one_page_count;
    }

    public Double getBounce_rate() {
        return bounce_rate;
    }

    public void setBounce_rate(Double bounce_rate) {
        this.bounce_rate = bounce_rate;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
}