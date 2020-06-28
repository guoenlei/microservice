package com.aldwx.app.modules.trend.bean;

import com.aldwx.app.common.base.BaseBean;
import com.aldwx.app.modules.general.bean.General;

/**
 * 趋势分析
 * @author
 * @description
 * @createTime
 **/
public class Trend extends BaseBean {

    private String type;

    public Trend() {}


    public Trend(String appKey, String date) {
        super(date);
        this.setAppKey(appKey);
        this.setDate(date);
    }


    public Trend(General general) {
        super(general.getDate());
        this.setAppKey(general.getAppKey());
        this.setDate(general.getDate());
        this.setAppType(general.getAppType());
        this.setType(general.getType());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
