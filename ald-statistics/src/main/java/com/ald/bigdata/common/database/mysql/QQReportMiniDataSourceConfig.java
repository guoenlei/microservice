package com.ald.bigdata.common.database.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class QQReportMiniDataSourceConfig {

    // 精确到 app 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.ald.bigdata.common.dao.app";
    @Value("${QQmaster.datasource.url}")
    private String url;

    @Value("${QQmaster.datasource.username}")
    private String user;

    @Value("${QQmaster.datasource.password}")
    private String password;

    @Value("${QQmaster.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "qqMiniDataSource")
    public DataSource qqMiniDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "qqMiniTransactionManager")
    public DataSourceTransactionManager qqAppTransactionManager() {
        return new DataSourceTransactionManager(qqMiniDataSource());
    }

    // JDBC template
    @Bean(name="qqMiniJdbcTemplate")
    public JdbcTemplate qqMiniJdbcTemplate (
            @Qualifier("qqMiniDataSource")  DataSource qqMiniDataSource ) {
        return new JdbcTemplate(qqMiniDataSource);
    }

//    @Qualifier
//    @Primary
    @Bean(name = "qqMiniSqlSessionFactory")
    public SqlSessionFactory qqMiniSqlSessionFactory(@Qualifier("qqMiniDataSource") DataSource qqMiniDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(qqMiniDataSource);

        return sessionFactory.getObject();
    }
}