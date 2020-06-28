package com.aldwx.app.modules.portrait.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * aldstat_terminal_analysis
 * @author 
 */
public class AldstatTerminalAnalysis implements Serializable {
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
     * 终端的总体类型, 如系统,网络,像素比,语言...
     */
    private String type;

    /**
     * 每个类型下的小类型, 如网络类型下有 4g, wifi
     */
    private String type_values;

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
     * 次均停留时长
     */
    private Double avg_stay_time;

    /**
     * 跳出率
     */
    private Float bounce_rate;

    /**
     * 更新时间
     */
    private Date update_at;

    /**
     * 总停留时长
     */
    private Long total_stay_time;

    private Integer one_page_count;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_values() {
        return type_values;
    }

    public void setType_values(String type_values) {
        this.type_values = type_values;
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

    public Long getTotal_stay_time() {
        return total_stay_time;
    }

    public void setTotal_stay_time(Long total_stay_time) {
        this.total_stay_time = total_stay_time;
    }

    public Integer getOne_page_count() {
        return one_page_count;
    }

    public void setOne_page_count(Integer one_page_count) {
        this.one_page_count = one_page_count;
    }
}