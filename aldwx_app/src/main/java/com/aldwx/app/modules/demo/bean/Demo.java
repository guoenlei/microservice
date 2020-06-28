package com.aldwx.app.modules.demo.bean;

/**
 *
 * @author
 * @description
 * @createTime
 **/
public class Demo {

    private String target;
    private String orderRule;
    private String tableName;
    private String sql;

    public Demo(){}

    public Demo(String target, String orderRule, String tableName, String sql) {
        this.target = target;
        this.orderRule = orderRule;
        this.tableName = tableName;
        this.sql = sql;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getOrderRule() {
        return orderRule;
    }

    public void setOrderRule(String orderRule) {
        this.orderRule = orderRule;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
