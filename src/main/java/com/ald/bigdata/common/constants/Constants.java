package com.ald.bigdata.common.constants;

import com.ald.bigdata.common.config.ConfigurationManager;
import org.springframework.beans.factory.annotation.Value;

/**
 * 常量
 */
public interface Constants {

    String IS_LOCAL = "is.local";

    String JDBC_POOL = "spring.datasource.pool";

    /**
     * 前端传入的part，类型标识：
     * qx：QQ小程序
     * qg：QQ小游戏
     * wx：WX小程序
     * wg：WX小游戏
     */
    String QQ_MINI = "qx";
    String QQ_GAME = "qg";
    String WX_MINI = "wx";
    String WX_GAME = "wg";

    /**
     * mysql
     */
    String MYSQL_JDBC_DRIVER = "spring.datasource.mysql.driver";

    String MYSQL_JDBC_URL_DEV = "spring.datasource.mysql.url.dev";
    String MYSQL_JDBC_USER_DEV = "spring.datasource.mysql.username.dev";
    String MYSQL_JDBC_PASSWORD_DEV = "spring.datasource.mysql.password.dev";

    String MYSQL_JDBC_URL = "spring.datasource.mysql.url";
    String MYSQL_JDBC_USER = "spring.datasource.mysql.username";
    String MYSQL_JDBC_PASSWORD = "spring.datasource.mysql.password";


    //分库信息
    String CLUSTER_DATASOURCE_IP = "cluster.datasource.ip";
    String CLUSTER_DATASOURCE_NAME = "cluster.datasource.name";


    /**
     * presto
     */
    String PRESTO_JDBC_DRIVER = "spring.datasource.presto.driver";

    String PRESTO_JDBC_URL = "spring.datasource.presto.url";
    String PRESTO_JDBC_USER = "spring.datasource.presto.username";
    String PRESTO_JDBC_PASSWORD = "spring.datasource.presto.password";

    String PRESTO_JDBC_URL_DEV = "spring.datasource.presto.url.dev";
    String PRESTO_JDBC_USER_DEV = "spring.datasource.presto.username.dev";
    String PRESTO_JDBC_PASSWORD_DEV = "spring.datasource.presto.password.dev";


    /**
     * mysql 数据库表
     */
    String PERSTO_TABLENAME = ConfigurationManager.getProperty("hive.table.aldstat.event.paras");

    String ALDSTAT_DAILY_EVENT_USER_GROUP = ConfigurationManager.getProperty("mysql.table.aldstat.event.paras");
    String ALDSTAT_7DAYS_EVENT_USER_GROUP = ConfigurationManager.getProperty("mysql.table.aldstat.7days.event.paras");
    String ALDSTAT_30DAYS_EVENT_USER_GROUP = ConfigurationManager.getProperty("mysql.table.aldstat.30days.event.paras");


    boolean IS_COUNT = false;

    //事件 时间日期
    String FLAG_01 = "~";

    String ALDSTAT_EVENT_TODAY_TIME = "1";          //今天
    String ALDSTAT_EVENT_YESTERDAY_TIME = "2";      //昨天
    String ALDSTAT_EVENT_NEAR_WEEKDAY_TIME = "3";   //最近一周
    String ALDSTAT_EVENT_NEAR_MONTH_TIME = "4";     //最近一月

    //事件 事件类型
    String ALDSTAT_EVENT_TYPE_NAME = "1";
    String ALDSTAT_EVENT_TYPE_ID = "2";


    String TOTAL_COUNT = "20"; //默认20条
    String CURRENT_PAGE = "1"; //默认第一页


    //分页默认从第一页
    String CURRENT_PAGE_DEFAULT = "1";
    //默认每页100条
    String TOTAL_DEFAULT = "100";


    //提示信息
    String ALERT_SYS_ERROR_MSG = "系统处理异常";

    String ALERT_PAGE_ERROR_MSG = "请输入正确分页信息";
    String ALERT_DATE_ERROR_MSG = "时间格式有误";

    String ALERT_NO_AK_MSG = "APPKEY不能为空";


}
