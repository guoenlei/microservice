package com.aldwx.bigdata.modules.ad.entity;

import java.io.Serializable;
import java.util.Date;

/** 
 *
 * 
ald_prevent_cheat
 *
 *  
 */
public class AldPreventCheat implements Serializable {
    /** 自增id **/
    private Long id;

    /** 用户id **/
    private Integer uid;

    /** 客户标识 **/
    private String appKey;

    /** 异常时差时间（如，3，5，10，60，120，单位秒）,默认-1，没有时间限制 **/
    private Integer diffTimeType;

    /** 异常时差时间（如，2，4，6，8，10，单位秒）,默认-1，没有时间限制 **/
    private Integer oauthCountType;

    /** 异常IP点击次数（如，2，5，10，15，20，单位次）,默认-1，没有次数限制 **/
    private Integer ipclkCountType;

    /** 更新时间 **/
    private Date updateAt;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Integer getDiffTimeType() {
        return diffTimeType;
    }

    public void setDiffTimeType(Integer diffTimeType) {
        this.diffTimeType = diffTimeType;
    }

    public Integer getIpclkCountType() {
        return ipclkCountType;
    }

    public void setIpclkCountType(Integer ipclkCountType) {
        this.ipclkCountType = ipclkCountType;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getOauthCountType() {
        return oauthCountType;
    }

    public void setOauthCountType(Integer oauthCountType) {
        this.oauthCountType = oauthCountType;
    }

}