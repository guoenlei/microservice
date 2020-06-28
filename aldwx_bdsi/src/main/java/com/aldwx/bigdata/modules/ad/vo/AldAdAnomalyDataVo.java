package com.aldwx.bigdata.modules.ad.vo;

import com.aldwx.bigdata.common.base.BaseVo;

public class AldAdAnomalyDataVo extends BaseVo {

    private String appKey;
    private String date;
    private String module;
    private String prop;
    private String order;

    private String tableName1;
    private String tableName2;
    private String tableName3;
    private String tableName4;


    private int diffTimeType;
    private int authTimes;
    private int ipclkCountType;

    public AldAdAnomalyDataVo(){super();}

    public AldAdAnomalyDataVo(String appKey, String date, String module, String prop, String order) {
        this.appKey = appKey;
        this.date = date;
        this.module = module;
        this.prop = prop;
        this.order = order;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
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

    public String getTableName1() {
        return tableName1;
    }

    public void setTableName1(String tableName1) {
        this.tableName1 = tableName1;
    }

    public String getTableName2() {
        return tableName2;
    }

    public void setTableName2(String tableName2) {
        this.tableName2 = tableName2;
    }

    public int getDiffTimeType() {
        return diffTimeType;
    }

    public void setDiffTimeType(int diffTimeType) {
        this.diffTimeType = diffTimeType;
    }

    public int getIpclkCountType() {
        return ipclkCountType;
    }

    public void setIpclkCountType(int ipclkCountType) {
        this.ipclkCountType = ipclkCountType;
    }

    public String getTableName3() {
        return tableName3;
    }

    public void setTableName3(String tableName3) {
        this.tableName3 = tableName3;
    }

    public String getTableName4() {
        return tableName4;
    }

    public void setTableName4(String tableName4) {
        this.tableName4 = tableName4;
    }

    public int getAuthTimes() {
        return authTimes;
    }

    public void setAuthTimes(int authTimes) {
        this.authTimes = authTimes;
    }
}
