package com.ald.bigdata.common.event.vo;


import com.ald.bigdata.common.base.BaseVo;

/**
 * 参数
 */
public class EventVo extends BaseVo {


    private String date;
    private String prop;
    private String order;
    private String appKey;
    private String eventKey;
    private String eventName;
    private String isDownload;
    // type：1-明细；2-列表
    private String type;

    // 平台类型，对应前端的part字段。wx：微信小程序；wg：微信小游戏。
    private String platform;

    public EventVo() {
    }

    public EventVo(String date, String prop, String order, String appKey,
                   String eventKey, String eventName, String isDownload, String platform) {
        this.date = date;
        this.prop = prop;
        this.order = order;
        this.appKey = appKey;
        this.eventKey = eventKey;
        this.eventName = eventName;
        this.isDownload = isDownload;
        this.platform = platform;
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

    @Override
    public String getAppKey() {
        return appKey;
    }

    @Override
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

    @Override
    public String getIsDownload() {
        return isDownload;
    }

    @Override
    public void setIsDownload(String isDownload) {
        this.isDownload = isDownload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getPlatform() {
        return platform;
    }

    @Override
    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
