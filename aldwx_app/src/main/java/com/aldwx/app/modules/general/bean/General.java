package com.aldwx.app.modules.general.bean;

import com.aldwx.app.common.base.BaseBean;

/**
 * 数据概览
 * @author
 * @description
 * @createTime
 **/
public class General extends BaseBean {

    private String type;

    public General() {}

    public General(String appKey, String date, String appType) {
        super(date);            //转换日期
        this.setDate(date);
        this.setAppKey(appKey);
        this.setAppType(appType);
    }

    public General(String appKey, String date, String appType, String type) {
        super(date);
        this.setDate(date);
        this.setAppKey(appKey);
        this.setAppType(appType);
        this.setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
