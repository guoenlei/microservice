package com.aldwx.bigdata.modules.city.vo;

import com.aldwx.bigdata.common.base.BaseVo;


/**
 * 城市
 */
public class AldCityVo extends BaseVo {


    private String appKey;
    private String type;
    private String date;
    private String prop;
    private String order;


    public AldCityVo(){super();}


    public AldCityVo(String date, String type, String appKey) {
        super();
        this.appKey = appKey;
        this.type = type;
        this.date = date;
    }

    public AldCityVo(String date, String type, String appKey, String prop, String order) {
        super();
        this.appKey = appKey;
        this.type = type;
        this.date = date;
        this.prop = prop;
        this.order = order;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
