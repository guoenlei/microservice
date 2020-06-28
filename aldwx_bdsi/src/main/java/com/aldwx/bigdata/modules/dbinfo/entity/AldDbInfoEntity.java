package com.aldwx.bigdata.modules.dbinfo.entity;

import com.aldwx.bigdata.common.base.BaseEntity;


public class AldDbInfoEntity extends BaseEntity {


    private String appKey;                  //ak
    private String connName;                //数据库连接
    private String dbName;                  //数据库名
    private String dbIp;                    //ip
    private String port;                    //端口
    private String dbUser;                  //用户名
    private String dbPassword;              //用户密码


    public AldDbInfoEntity(String appKey, String connName, String dbName, String dbIp, String port,
                           String dbUser, String dbPassword) {
        this.appKey = appKey;
        this.connName = connName;
        this.dbName = dbName;
        this.dbIp = dbIp;
        this.port = port;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getConnName() {
        return connName;
    }

    public void setConnName(String connName) {
        this.connName = connName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbIp() {
        return dbIp;
    }

    public void setDbIp(String dbIp) {
        this.dbIp = dbIp;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

}
