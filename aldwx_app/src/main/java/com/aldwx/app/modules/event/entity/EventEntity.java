package com.aldwx.app.modules.event.entity;

import com.aldwx.app.common.base.BaseEntity;

/**
 * 事件
 * @author
 * @description
 * @createTime
 **/
public class EventEntity extends BaseEntity {

    private String appKey;
    private String day;
    private String evId;
    private String eventKey;
    private String evName;
    private double triggerUserCount;
    private double triggerCount;
    private float avgTriggerCount;
    private String sourceKey;
    private String sourceValue;

    public EventEntity(){}

    public EventEntity(String appKey, String day, String evId, String eventKey, double triggerUserCount, double triggerCount, float avgTriggerCount, String sourceKey, String sourceValue) {
        this.appKey = appKey;
        this.day = day;
        this.evId = evId;
        this.eventKey = eventKey;
        this.triggerUserCount = triggerUserCount;
        this.triggerCount = triggerCount;
        this.avgTriggerCount = avgTriggerCount;
        this.sourceKey = sourceKey;
        this.sourceValue = sourceValue;
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

    public String getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName = evName;
    }

    public double getTriggerUserCount() {
        return triggerUserCount;
    }

    public void setTriggerUserCount(double triggerUserCount) {
        this.triggerUserCount = triggerUserCount;
    }

    public double getTriggerCount() {
        return triggerCount;
    }

    public void setTriggerCount(double triggerCount) {
        this.triggerCount = triggerCount;
    }

    public float getAvgTriggerCount() {
        return avgTriggerCount;
    }

    public void setAvgTriggerCount(float avgTriggerCount) {
        this.avgTriggerCount = avgTriggerCount;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
    }
}

