package com.aldwx.bigdata.common.database.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@MapperScan(basePackages = ReportMiniDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "appSqlSessionFactory1")
public class ReportMiniDataSourceConfig {

    // 精确到 app 目录，以便跟其他数据源隔离
//    static final String PACKAGE = "com.aldwx.bigdata.modules.*.dao.app";
//    static final String MAPPER_LOCATION = "classpath:mapper/report/*.xml";
    static final String PACKAGE = "com.aldwx.bigdata.modules.*.dao.app";
    static final String MAPPER_LOCATION = "classpath:mapper/report/Mini*.xml,classpath:mapper/report/Report*.xml,";
    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String user;

    @Value("${master.datasource.password}")
    private String password;

    @Value("${master.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "appDataSource1")
    public DataSource appDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "appTransactionManager1")
    public DataSourceTransactionManager appTransactionManager() {
        return new DataSourceTransactionManager(appDataSource());
    }

    @Bean(name = "appSqlSessionFactory1")
    public SqlSessionFactory appSqlSessionFactory(@Qualifier("appDataSource1") DataSource appDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(appDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ReportMiniDataSourceConfig.MAPPER_LOCATION));
        Resource[] resources = null;
        List<Resource> list = new ArrayList<>();
        if (ReportMiniDataSourceConfig.MAPPER_LOCATION.indexOf(",") > 0) {
            String[] paths = ReportMiniDataSourceConfig.MAPPER_LOCATION.split(",");
            for (String path : paths) {
                list.addAll(Arrays.asList(new PathMatchingResourcePatternResolver()
                        .getResources(path)));
            }
            resources = list.toArray(new Resource[list.size()]);
        } else {
            resources = new PathMatchingResourcePatternResolver()
                    .getResources(ReportStatDataSourceConfig.MAPPER_LOCATION);
        }
        sessionFactory.setMapperLocations(resources);
        return sessionFactory.getObject();
    }
}