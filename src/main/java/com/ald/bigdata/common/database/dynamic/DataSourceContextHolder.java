package com.ald.bigdata.common.database.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceContextHolder {
    private static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);


    /**
     * 默认数据源
     */
    static final String DEFAULT_DS = "db-master";
    static final String DEFAULT_DS_GAME = "dbGame-master";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    static void setDB(String dbType) {
        log.debug("切换到{}数据源", dbType);
        contextHolder.set(dbType);
    }

    // 获取数据源名
    static String getDB() {
        if(contextHolder.get() == null)
        return DEFAULT_DS;
        else
            return contextHolder.get();
    }

    // 清除数据源名
    static void clearDB() {
        contextHolder.remove();
    }
}
