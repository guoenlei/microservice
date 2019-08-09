package com.ald.bigdata.modules.event.vo;


import com.ald.bigdata.common.base.BaseVo;

/**
 * 参数
 */
public class EventVo extends BaseVo {


    private String date;
//    private String typeId;
//    private String keyword;
    private String prop;
    private String order;
    private String eventKey;
    private String eventName;
    private String isDownload;
    private String type;

    public EventVo() {
    }

    public EventVo(String date, String typeId, String keyword, String prop, String order,
                   String appKey, String eventKey, String eventName, String isDownLoad) {
        this.date = date;
//        this.typeId = typeId;
//        this.keyword = keyword;
        this.prop = prop;
        this.order = order;
        this.appKey = appKey;
        this.eventKey = eventKey;
        this.eventName = eventName;
        this.isDownload = isDownLoad;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public String getTypeId() {
//        return typeId;
//    }
//
//    public void setTypeId(String typeId) {
//        this.typeId = typeId;
//    }
//
//    public String getKeyword() {
//        return keyword;
//    }
//
//    public void setKeyword(String keyword) {
//        this.keyword = keyword;
//    }

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

    public String getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(String isDownload) {
        this.isDownload = isDownload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
