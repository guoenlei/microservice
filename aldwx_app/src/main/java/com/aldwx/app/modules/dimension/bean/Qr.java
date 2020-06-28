package com.aldwx.app.modules.dimension.bean;

import com.aldwx.app.common.base.BaseBean;
import org.apache.commons.lang3.StringUtils;

/**
 * 二维码
 * @author
 * @description
 * @createTime
 **/
public class Qr extends BaseBean {


    private String type;        //类型 1新增  2活跃

    private String qrKey;

    private static final String TABLE_NAME_DAY = "aldstat_qr_code_statistics";
    private static final String TABLE_NAME_7DAY = "aldstat_7days_all_qr";
    private static final String TABLE_NAME_30DAY = "aldstat_30days_all_qr";

    private static final String ALDSTAT_SMARTLINK_HOUR_ANALYSIS = "aldstat_qr_code_statistics";
    private static final String ALDSTAT_SMARTLINK_SUMMARY_ANALYSIS = "aldstat_qr_code_statistics";

    public Qr(){}

    public Qr(String appKey, String date, String appType) {
        super(date);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
    }
    public Qr(String appKey, String date, String appType,int currentPage) {
        super(date);
        super.setCurrentPage(currentPage);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
    }

    public Qr(String appKey, String date, String appType, String type) {
        super(date);
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

    public String getQrKey() {
        return qrKey;
    }

    public void setQrKey(String qrKey) {
        this.qrKey = qrKey;
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
