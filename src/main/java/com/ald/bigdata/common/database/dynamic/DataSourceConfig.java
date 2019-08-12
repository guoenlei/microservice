package com.ald.bigdata.common.database.dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: @ziyu  freedomziyua@gmail.com
 * Date: 2019-08-08
 * Time: 19:43
 * Description: 动态分配DataSorce的配置类,主要维护一个静态的Map
 */
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
//        dsMap.put("busDataSource", busDataSource);
//        dsMap.put("db-read", dbRead);
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    @Bean(name = "dbMaster")
    public DataSource dbMaster(@Value("${ald.master.datasource.url}") String url,
                               @Value("${ald.master.datasource.username}") String userName,
                               @Value("${ald.master.datasource.password}") String password,
                               @Value("${ald.spring.datasource.mysql.driver}") String driverClassName) {
        DataSource dataSource = createDataSource(url,userName,password,driverClassName);
        return dataSource;

    }

    @Bean(name = "dbGameMaster")
    public DataSource dbGameMaster(@Value("${ald.game.datasource.url}") String url,
                                   @Value("${ald.game.datasource.username}") String userName,
                                   @Value("${ald.game.datasource.password}") String password,
                                   @Value("${ald.spring.datasource.mysql.driver}") String driverClassName) {
        DataSource dataSource = createDataSource(url,userName,password,driverClassName);
        return dataSource;

    }


    @Bean(name="masterJdbcTemplate")
    public JdbcTemplate testJdbcTemplate (
            @Qualifier("dbMaster")  DataSource masterDataSource ) {
        return new JdbcTemplate(masterDataSource);
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
