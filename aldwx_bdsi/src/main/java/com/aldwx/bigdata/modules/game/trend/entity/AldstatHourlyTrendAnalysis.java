package com.aldwx.bigdata.modules.game.trend.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * aldstat_hourly_trend_analysis
 * @author 
 */
public class AldstatHourlyTrendAnalysis implements Serializable {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 小程序 Key
     */
    private String app_key;

    /**
     * 日期
     */
    private Date day;

    /**
     * 小时区间,0-23,分别对应(0:00,0:59),(1:00,1:59)
     */
    private Byte hour;

    /**
     * 新访问用户数
     */
    private Integer new_comer_count;

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
    private Integer total_page_count;

    /**
     * 人均停留时长
     */
    private Double avg_stay_time;

    /**
     * 次均停留时长
     */
    private Double secondary_avg_stay_time;

    /**
     * 人均下拉刷新次数
     */
    private Double avg_refresh_count;

    private Long total_stay_time;

    /**
     * 分享次数
     */
    private Integer daily_share_count;

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

    private Integer daily_refresh_count;

    private Integer reach_bottom_count;

    private Double avg_reach_bottom_count;

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

    public Byte getHour() {
        return hour;
    }

    public void setHour(Byte hour) {
        this.hour = hour;
    }

    public Integer getNew_comer_count() {
        return new_comer_count;
    }

    public void setNew_comer_count(Integer new_comer_count) {
        this.new_comer_count = new_comer_count;
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

    public Integer getTotal_page_count() {
        return total_page_count;
    }

    public void setTotal_page_count(Integer total_page_count) {
        this.total_page_count = total_page_count;
    }

    public Double getAvg_stay_time() {
        return avg_stay_time;
    }

    public void setAvg_stay_time(Double avg_stay_time) {
        this.avg_stay_time = avg_stay_time;
    }

    public Double getSecondary_avg_stay_time() {
        return secondary_avg_stay_time;
    }

    public void setSecondary_avg_stay_time(Double secondary_avg_stay_time) {
        this.secondary_avg_stay_time = secondary_avg_stay_time;
    }

    public Double getAvg_refresh_count() {
        return avg_refresh_count;
    }

    public void setAvg_refresh_count(Double avg_refresh_count) {
        this.avg_refresh_count = avg_refresh_count;
    }

    public Long getTotal_stay_time() {
        return total_stay_time;
    }

    public void setTotal_stay_time(Long total_stay_time) {
        this.total_stay_time = total_stay_time;
    }

    public Integer getDaily_share_count() {
        return daily_share_count;
    }

    public void setDaily_share_count(Integer daily_share_count) {
        this.daily_share_count = daily_share_count;
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

    public Integer getDaily_refresh_count() {
        return daily_refresh_count;
    }

    public void setDaily_refresh_count(Integer daily_refresh_count) {
        this.daily_refresh_count = daily_refresh_count;
    }

    public Integer getReach_bottom_count() {
        return reach_bottom_count;
    }

    public void setReach_bottom_count(Integer reach_bottom_count) {
        this.reach_bottom_count = reach_bottom_count;
    }

    public Double getAvg_reach_bottom_count() {
        return avg_reach_bottom_count;
    }

    public void setAvg_reach_bottom_count(Double avg_reach_bottom_count) {
        this.avg_reach_bottom_count = avg_reach_bottom_count;
    }
}