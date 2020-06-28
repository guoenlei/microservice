package com.aldwx.bigdata.modules.ad.vo;

import com.aldwx.bigdata.common.base.BaseVo;

public class AldAdParamVo extends BaseVo {
    private String date;
    private String dateStart;
    private String dateEnd;
    private String appkey;
    private String activitiesName;//推广活动名称即
    private String appName;
    private String prop;
    private String order;
    private String activitie;
    private String channel;
    private String scene;
    private String source1;
    private String source2;
    private String source3;




    public AldAdParamVo(){

    }

    public AldAdParamVo(String date, String dateStart, String dateEnd, String appkey, String activitiesName, String appName, String prop, String order, String activitie, String channel, String scene, String source1, String source2, String source3) {
        this.date = date;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.appkey = appkey;
        this.activitiesName = activitiesName;
        this.appName = appName;
        this.prop = prop;
        this.order = order;
        this.activitie = activitie;
        this.channel = channel;
        this.scene = scene;
        this.source1 = source1;
        this.source2 = source2;
        this.source3 = source3;
    }

    public String getActivitiesName() {
        return activitiesName;
    }

    public String getSource1() {
        return source1;
    }

    public void setSource1(String source1) {
        this.source1 = source1;
    }

    public String getSource2() {
        return source2;
    }

    public void setSource2(String source2) {
        this.source2 = source2;
    }

    public String getSource3() {
        return source3;
    }

    public void setSource3(String source3) {
        this.source3 = source3;
    }

    public void setActivitiesName(String activitiesName) {
        this.activitiesName = activitiesName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
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

    public String getActivitie() {
        return activitie;
    }

    public void setActivitie(String activitie) {
        this.activitie = activitie;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
}
