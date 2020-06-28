package com.aldwx.app.common.database.mysql.dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    static Map<Object, Object> dsMap = new HashMap<>();

    @Autowired
    @Bean(name = "dynamicSource")
//    @Qualifier("dynamicSource")
    public DynamicDataSource dataSource(@Qualifier("dbMaster") DataSource dbMaster,
                                        @Qualifier("dbGameMaster") DataSource dbGameMaster) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dbMaster);

        // 配置多数据源
        dsMap.put("db-master", dbMaster);
        dsMap.put("dbGame-master", dbGameMaster);
//        dsMap.put("db-read", dbRead);
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    @Bean(name = "dbMaster")
    public DataSource dbMaster(@Value("${stat.app.datasource.url}") String url,
                               @Value("${stat.app.datasource.username}") String userName,
                               @Value("${stat.app.datasource.password}") String password,
                               @Value("${mysql.datasource.driverClassName}") String driverClassName) {
        DataSource dataSource = createDataSource(url,userName,password,driverClassName);
        return dataSource;

    }

    @Bean(name = "dbGameMaster")
    public DataSource dbGameMaster(@Value("${stat.game.datasource.url}") String url,
                               @Value("${stat.game.datasource.username}") String userName,
                               @Value("${stat.game.datasource.password}") String password,
                               @Value("${mysql.datasource.driverClassName}") String driverClassName) {
        DataSource dataSource = createDataSource(url,userName,password,driverClassName);
        return dataSource;

    }

    public static DataSource createDataSource(String url,
                                        String userName,
                                        String password,
                                        String driverClassName){
        return  DataSourceBuilder.create()
                .url(url)
                .username(userName)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }


}
