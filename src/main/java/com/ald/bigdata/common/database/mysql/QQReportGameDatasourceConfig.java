package com.ald.bigdata.common.database.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class QQReportGameDatasourceConfig {

    @Value("${QQgame.datasource.url}")
    private String url;

    @Value("${QQgame.datasource.username}")
    private String user;

    @Value("${QQgame.datasource.password}")
    private String password;

    @Value("${QQgame.datasource.driverClassName}")
    private String driverClass;

    @Primary
    @Bean(name = "qqGameDataSource")
    public DataSource qqGameDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Primary
    @Bean(name = "qqGameTransactionManager")
    public DataSourceTransactionManager qqGameTransactionManager() {
        return new DataSourceTransactionManager(qqGameDataSource());
    }

    // JDBC template
    @Bean(name="qqGameJdbcTemplate")
    public JdbcTemplate qqGameJdbcTemplate (
            @Qualifier("qqGameDataSource")  DataSource qqGameDataSource ) {
        return new JdbcTemplate(qqGameDataSource);
    }

    @Primary
    @Bean(name = "qqGameSqlSessionFactory")
    public SqlSessionFactory qqGameSqlSessionFactory(
            @Qualifier("qqGameDataSource") DataSource qqGameDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(qqGameDataSource);

        return sessionFactory.getObject();
    }
}
