package com.ald.bigdata.common.database.presto;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class PrestoDBConfiguration {

//    微信小程序数据源
    @Value("${presto.datasource.wxmini.url}")
    private String url;
    @Value("${presto.datasource.driverClassName}")
    private String driverClass;
    @Bean(name = "prestoWXMiniDataSource")
    public DataSource prestoWXMiniDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        Properties properties = new Properties();
        properties.setProperty("user","root");
        dataSource.setConnectProperties(properties);
        return dataSource;
    }

    @Bean(name = "prestoWXMiniTransactionManager")
    public DataSourceTransactionManager prestoWXMiniTransactionManager() {
        return new DataSourceTransactionManager(prestoWXMiniDataSource());
    }

    @Bean(name = "prestoWXMiniSqlSessionFactory")
    public SqlSessionFactory prestoWXMiniSqlSessionFactory(@Qualifier("prestoWXMiniDataSource") DataSource prestoWXMiniDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(prestoWXMiniDataSource);
        return sessionFactory.getObject();
    }


//  微信小游戏Presto数据源
    @Value("${presto.datasource.wxgame.url}")
    private String gameurl;
    @Value("${presto.datasource.driverClassName}")
    private String gamedriverClass;
    @Bean(name = "prestoWXGameDataSource")
    public DataSource prestoWXGameDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        Properties properties = new Properties();
        properties.setProperty("user","root");
        dataSource.setConnectProperties(properties);
        return dataSource;
    }
    @Bean(name = "prestoWXGameTransactionManager")
    public DataSourceTransactionManager prestoWXGameTransactionManager() {
        return new DataSourceTransactionManager(prestoWXGameDataSource());
    }
    @Bean(name = "prestoWXGameSqlSessionFactory")
    public SqlSessionFactory prestoWXGameSqlSessionFactory(@Qualifier("prestoWXGameDataSource") DataSource prestoWXGameDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(prestoWXGameDataSource);
        return sessionFactory.getObject();
    }

}
