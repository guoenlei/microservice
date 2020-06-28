package com.aldwx.bigdata.modules.gameEvent.entity;

import java.io.Serializable;

public class AldGameEventEntity implements Serializable {

    private long id;                    //id
    private String appKey;              //小程序key
    private String evId;                //事件id
    private String eventKey;            //加密eventKey
    private String evName;              //事件名
    private String evStatus;            //0-已删除, 1-未删除
    private String evUpdateTime;        //事件删除时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
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

    public String getEvStatus() {
        return evStatus;
    }

    public void setEvStatus(String evStatus) {
        this.evStatus = evStatus;
    }

    public String getEvUpdateTime() {
        return evUpdateTime;
    }

    public void setEvUpdateTime(String evUpdateTime) {
        this.evUpdateTime = evUpdateTime;
    }
}
