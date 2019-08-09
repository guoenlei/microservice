package com.ald.bigdata.modules.eventParam.entity;


import com.ald.bigdata.common.base.BaseEntity;

public class AldEventParamEntity extends BaseEntity {

    private String appKey;
    private String day;
    private String evId;
    private String eventKey;
    private String evParasName;
    private String evParasValue;
    private String evParasCount;
    private String evParasUv;

    public AldEventParamEntity() {}


    public AldEventParamEntity(String appKey, String day, String evId, String eventKey, String evParasName,
                               String evParasValue, String evParasCount, String evParasUv) {
        this.appKey = appKey;
        this.day = day;
        this.evId = evId;
        this.eventKey = eventKey;
        this.evParasName = evParasName;
        this.evParasValue = evParasValue;
        this.evParasCount = evParasCount;
        this.evParasUv = evParasUv;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEvId() {
        return evId;
    }

    public void setEvId(String evId) {
        this.evId = evId;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getEvParasName() {
        return evParasName;
    }

    public void setEvParasName(String evParasName) {
        this.evParasName = evParasName;
    }

    public String getEvParasValue() {
        return evParasValue;
    }

    public void setEvParasValue(String evParasValue) {
        this.evParasValue = evParasValue;
    }

    public String getEvParasCount() {
        return evParasCount;
    }

    public void setEvParasCount(String evParasCount) {
        this.evParasCount = evParasCount;
    }

    public String getEvParasUv() {
        return evParasUv;
    }

    public void setEvParasUv(String evParasUv) {
        this.evParasUv = evParasUv;
    }
}
