package com.ald.bigdata.modules.eventParam.vo;


import com.ald.bigdata.common.base.BaseVo;

public class AldEventParamVo extends BaseVo {

    private String date;
    private String prop;
    private String order;
    private String appKey;
    private String eventKey;
    private String eventName;
    private String type;

    public AldEventParamVo() {
    }

    public AldEventParamVo(String date, String prop, String order,
                   String appKey, String eventKey, String eventName) {
        this.date = date;
        this.prop = prop;
        this.order = order;
        this.appKey = appKey;
        this.eventKey = eventKey;
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
