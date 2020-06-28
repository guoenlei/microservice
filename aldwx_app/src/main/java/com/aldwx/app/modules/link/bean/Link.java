package com.aldwx.app.modules.link.bean;

import com.aldwx.app.common.base.BaseBean;
import com.aldwx.app.modules.general.bean.General;

/**
 * 智能外链
 * @author
 * @description
 * @createTime
 **/
public class Link extends BaseBean {

    public Link(){}

    public Link(String appKey, String date, String appType) {
        super(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
    }

    public Link(General general) {
        super(general.getDate());
        this.setAppKey(general.getAppKey());
        this.setDate(general.getDate());
        this.setTarget(general.getTarget());
        this.setAppType(general.getAppType());
    }

    public Link(String appKey, String date, String target, String appType) {
        super(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setTarget(target);
        this.setAppType(appType);
    }



}

