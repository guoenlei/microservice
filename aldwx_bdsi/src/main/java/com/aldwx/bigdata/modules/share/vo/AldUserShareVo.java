package com.aldwx.bigdata.modules.share.vo;

import com.aldwx.bigdata.common.base.BaseVo;

public class AldUserShareVo extends BaseVo {

    private String appKey;
    private String date;
    private String order;
    private String prop;
    private String type;
    private String keyword;

    public AldUserShareVo(){super();}

    public AldUserShareVo(String appKey, String date, String order, String prop,
                          String type, String keyword) {
        super();
        this.appKey = appKey;
        this.date = date;
        this.order = order;
        this.prop = prop;
        this.type = type;
        this.keyword = keyword;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
