package com.aldwx.app.modules.retain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * aldstat_user_activity
 * @author 
 */
public class AldstatUserActivity implements Serializable {
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
     * 日活跃
     */
    private Integer dau;

    /**
     * 周活跃
     */
    private Integer wau;

    /**
     * 日活跃/周活跃占比
     */
    private Double dau_wau_ratio;

    /**
     * 月活跃
     */
    private Integer mau;

    /**
     * 日活跃/月活跃占比
     */
    private Double dau_mau_ratio;

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

    public Integer getDau() {
        return dau;
    }

    public void setDau(Integer dau) {
        this.dau = dau;
    }

    public Integer getWau() {
        return wau;
    }

    public void setWau(Integer wau) {
        this.wau = wau;
    }

    public Double getDau_wau_ratio() {
        return dau_wau_ratio;
    }

    public void setDau_wau_ratio(Double dau_wau_ratio) {
        this.dau_wau_ratio = dau_wau_ratio;
    }

    public Integer getMau() {
        return mau;
    }

    public void setMau(Integer mau) {
        this.mau = mau;
    }

    public Double getDau_mau_ratio() {
        return dau_mau_ratio;
    }

    public void setDau_mau_ratio(Double dau_mau_ratio) {
        this.dau_mau_ratio = dau_mau_ratio;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
}