package com.aldwx.app.common.database.mysql.dynamic;

/**
 * Created with IntelliJ IDEA.
 * User: @ziyu  freedomziyua@gmail.com
 * Date: 2019-07-31
 * Time: 20:35
 * Description:
 */
public class SourceDatabaseBean {

    private long id;
    private String appKey;
    private String ConnName;
    private String dbName;
    private String dbIp;
    private int port;
    private String dbUser;
    private String dbPassword;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private String tableName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getConnName() {
        return ConnName;
    }

    public void setConnName(String connName) {
        ConnName = connName;
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
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
