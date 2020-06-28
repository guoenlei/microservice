package com.aldwx.app.modules.smart.bean;

import com.aldwx.app.common.base.BaseBean;
import com.aldwx.app.modules.general.bean.General;
import org.apache.commons.lang3.StringUtils;

/**
 * 智能外链
 * @author
 * @description
 * @createTime
 **/
public class Smart extends BaseBean {

    private String type;        //类型 1新增  2活跃

    private String appId;
    private String linkName;

    private static final String TABLE_NAME_DAY = "aldstat_smartLink_hour_analysis";
    private static final String TABLE_NAME_7DAY = "aldstat_smartLink_day_analysis";
    private static final String TABLE_NAME_30DAY = "aldstat_smartLink_day_analysis";

    private static final String ALDSTAT_SMARTLINK_HOUR_ANALYSIS = "aldstat_smartLink_hour_analysis";
    private static final String ALDSTAT_SMARTLINK_SUMMARY_ANALYSIS = "aldstat_smartLink_summary_analysis";

    public Smart(){}

    public Smart(String appKey, String date, String appType) {
        super(date);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
    }
    public Smart(String appKey, String date, String appType,int currentPage) {
        super(date);
        super.setCurrentPage(currentPage);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
    }

    public Smart(String appKey, String date, String appType, String type) {
        super(date);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
        this.setType(type);
    }


    public Smart(General general) {
        super(general.getDate());
        this.setTName(general.getDate());
        this.setAppKey(general.getAppKey());
        this.setDate(general.getDate());
        this.setTarget(general.getTarget());
        this.setAppType(general.getAppType());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
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
