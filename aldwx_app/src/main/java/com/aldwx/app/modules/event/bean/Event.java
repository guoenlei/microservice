package com.aldwx.app.modules.event.bean;

import com.aldwx.app.common.base.BaseBean;
import org.apache.commons.lang3.StringUtils;

/**
 * 事件分析
 * @author
 * @description
 * @createTime
 **/
public class Event extends BaseBean {

    private String type;

    private String evId;

    private static final String TABLE_NAME_DAY = "aldstat_daily_event";
    private static final String TABLE_NAME_7DAY = "aldstat_7days_event";
    private static final String TABLE_NAME_30DAY = "aldstat_30days_event";

    public Event(){}

    public Event(String appKey, String date, String appType) {
        super(date);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
    }


    public Event(String appKey, String date, String appType, String type) {
        super(date);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
        this.setType(type);
    }
    public Event(String appKey, String date, String appType, String type,int currentPage) {
        super(date);
        super.setCurrentPage(currentPage);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
        this.setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvId() {
        return evId;
    }

    public void setEvId(String evId) {
        this.evId = evId;
    }

    //判断表名
    public void setTName(String date) {
        String tableName = null;
        if(date.equals("1")) {
            tableName = TABLE_NAME_DAY;
        } else if(date.equals("2")) {
            tableName = TABLE_NAME_DAY;
        } else if(date.equals("3")) {
            tableName = TABLE_NAME_7DAY;
        } else if(date.equals("4")) {
            tableName = TABLE_NAME_30DAY;
        }

        if(StringUtils.isNotBlank(tableName)) {
            this.setTableName(tableName);
        }
    }


}
