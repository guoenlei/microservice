package com.aldwx.app.modules.funnel.bean;

import com.aldwx.app.common.base.BaseBean;
import org.apache.commons.lang3.StringUtils;

/**
 * 漏斗
 * @author
 * @description
 * @createTime
 **/
public class Funnel extends BaseBean {

    private String source;

    private static final String TABLE_NAME_DAY = "aldstat_daily_event";
    private static final String TABLE_NAME_7DAY = "aldstat_7days_event";
    private static final String TABLE_NAME_30DAY = "aldstat_30days_event";

    public Funnel(){}

    public Funnel(String appKey, String date, String appType) {
        super(date);
        this.setTableName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
    }


    public Funnel(String appKey, String date, String appType, String source) {
        super(date);
        this.setTableName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
        this.setSource(source);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    //判断表名
    public void setTableName(String date) {
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
