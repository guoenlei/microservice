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
public class ReportMiniDataSourceConfig {

    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String user;

    @Value("${master.datasource.password}")
    private String password;

    @Value("${master.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "wxMiniDataSource")
    public DataSource appDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "miniTransactionManager")
    public DataSourceTransactionManager appTransactionManager() {
        return new DataSourceTransactionManager(appDataSource());
    }

    // JDBC template
    @Bean(name="wxMiniJdbcTemplate")
    public JdbcTemplate wxMiniJdbcTemplate (
            @Qualifier("wxMiniDataSource")  DataSource wxGameDataSource ) {
        return new JdbcTemplate(wxGameDataSource);
    }


    @Bean(name = "wxMiniSqlSessionFactory")
    public SqlSessionFactory wxMiniSqlSessionFactory(@Qualifier("wxMiniDataSource") DataSource appDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(appDataSource);

        return sessionFactory.getObject();
    }
}