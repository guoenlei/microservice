package com.ald.bigdata.common.database;

import com.ald.bigdata.common.config.ConfigurationManager;
import com.ald.bigdata.common.constants.Constants;
import com.ald.bigdata.common.database.mysql.JDBCHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * JDBC工具类
 */
public class JDBC {

//    static {
//        try {
//            String mysqlDriver = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_DRIVER);
//            String prestoDriver = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_DRIVER);
//            Class.forName(mysqlDriver);
//            Class.forName(prestoDriver);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String mysql_driver = null;
//    private static String mysql_url = null;
//    private static String mysql_username = null;
//
//    private static String mysql_password = null;
//    private static String presto_driver = null;
//    private static String presto_url = null;
//    private static String presto_username = null;


    private static JDBC instance = null;
    public static JDBC getInstance() {
        if(instance == null) {
            synchronized(JDBCHelper.class) {
                if(instance == null) {
                    instance = new JDBC();
                }
            }
        }
        return instance;
    }

    public JDBC() {
//        //判断是否为测试
//        boolean isLocal = ConfigurationManager.getBoolean(Constants.IS_LOCAL);
//        mysql_driver = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_DRIVER);
//        presto_driver = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_DRIVER);
//        if(isLocal) {
//            mysql_url = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_URL_DEV);
//            mysql_username = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_USER_DEV);
//            mysql_password = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_PASSWORD_DEV);
//            presto_url = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_URL_DEV);
//            presto_username = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_USER_DEV);
//            presto_password = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_PASSWORD_DEV);
//        } else {
//            mysql_url = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_URL_DEV);
//            mysql_username = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_USER);
//            mysql_password = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_PASSWORD);
//            presto_url = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_URL);
//            presto_username = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_USER);
//            presto_password = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_PASSWORD);
//        }

    }


    public Connection getPrestoConnection()  {
        //判断是否为测试
        boolean isLocal = ConfigurationManager.getBoolean(Constants.IS_LOCAL);
        String presto_url = null;
        String presto_username = null;
        String presto_password = null;
        String presto_driver = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_DRIVER);
        if(isLocal) {
            presto_url = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_URL_DEV);
            presto_username = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_USER_DEV);
            presto_password = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_PASSWORD_DEV);
        } else {
            presto_url = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_URL);
            presto_username = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_USER);
            presto_password = ConfigurationManager.getProperty(Constants.PRESTO_JDBC_PASSWORD);
        }

        try {
            Class.forName(presto_driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        properties.setProperty("user","root");
        Connection conn = null;
        System.out.println("URL = " + presto_url);
        try {
            conn = DriverManager.getConnection(presto_url,properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }



    public Connection getMysqlConnection()  {
        //判断是否为测试
        boolean isLocal = ConfigurationManager.getBoolean(Constants.IS_LOCAL);
        String mysql_url = null;
        String mysql_username = null;
        String mysql_password = null;
        String mysql_driver = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_DRIVER);
        if(isLocal) {
            mysql_url = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_URL_DEV);
            mysql_username = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_USER_DEV);
            mysql_password = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_PASSWORD_DEV);
        } else {
            mysql_url = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_URL);
            mysql_username = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_USER);
            mysql_password = ConfigurationManager.getProperty(Constants.MYSQL_JDBC_PASSWORD);
        }
        try {
            Class.forName(mysql_driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            System.out.println("连接mysql url="+mysql_url);
            System.out.println("user="+mysql_username);
            System.out.println("pwd="+mysql_password);
            conn = DriverManager.getConnection(mysql_url,mysql_username,mysql_password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static void main(String[] args) {
        System.out.println(getInstance().getMysqlConnection());
    }
}
