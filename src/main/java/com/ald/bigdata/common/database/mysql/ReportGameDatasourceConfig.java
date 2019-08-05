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
public class ReportGameDatasourceConfig {

    @Value("${game.datasource.url}")
    private String url;

    @Value("${game.datasource.username}")
    private String user;

    @Value("${game.datasource.password}")
    private String password;

    @Value("${game.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "wxGameDataSource")
    public DataSource wxGameDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "gameTransactionManager")
    public DataSourceTransactionManager gameTransactionManager() {
        return new DataSourceTransactionManager(wxGameDataSource());
    }

    // JDBC template
    @Bean(name="wxGameJdbcTemplate")
    public JdbcTemplate wxGameJdbcTemplate (
            @Qualifier("wxGameDataSource")  DataSource wxGameDataSource ) {
        return new JdbcTemplate(wxGameDataSource);
    }

    @Bean(name = "wxGameSqlSessionFactory")
    public SqlSessionFactory wxGameSqlSessionFactory(@Qualifier("wxGameDataSource") DataSource wxGameDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(wxGameDataSource);

        return sessionFactory.getObject();
    }
}