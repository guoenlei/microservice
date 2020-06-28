package com.aldwx.app.modules.page.bean;

import com.aldwx.app.common.base.BaseBean;
import com.aldwx.app.modules.general.bean.General;
import org.apache.commons.lang3.StringUtils;

/**
 * 受访页
 * @author
 * @description
 * @createTime
 **/
public class Page extends BaseBean {

    private String type;

    private static final String TABLE_NAME_DAY = "aldstat_page_view";
    private static final String TABLE_NAME_7DAY = "aldstat_7days_single_page_view";
    private static final String TABLE_NAME_30DAY = "aldstat_30days_single_page_view";

    public Page(){}

    public Page(String appKey, String date, String appType) {
        super(date);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
    }

    public Page(String appKey, String date, String appType, String type) {
        super(date);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
        this.setType(type);
    }
    public Page(String appKey, String date, String appType, String type,int currentPage) {
        super(date);
        super.setCurrentPage(currentPage);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
        this.setType(type);
    }

    public Page(General general) {
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
