package com.aldwx.app.common.database.mysql.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("数据源为{}", DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }

   /* @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }*/

    @Override
    protected DataSource determineTargetDataSource() {
        DataSource dataSource = (DataSource) DataSourceConfig.dsMap.get(DataSourceContextHolder.getDB());
        return dataSource;
    }
}
