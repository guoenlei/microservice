package com.aldwx.app.modules.retain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * aldstat_user_activity_details
 * @author 
 */
public class AldstatUserActivityDetails implements Serializable {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 小程序 Key
     */
    private String app_key;

    /**
     * 当天日期-week为周一,month为1号
     */
    private Date day;

    /**
     * 活跃用户数
     */
    private Integer ucount;

    /**
     * day-日活 week-自然周 month-自然月
     */
    private String type;

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

    public Integer getUcount() {
        return ucount;
    }

    public void setUcount(Integer ucount) {
        this.ucount = ucount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
}