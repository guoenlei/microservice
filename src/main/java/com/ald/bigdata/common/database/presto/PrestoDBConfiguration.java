package com.ald.bigdata.common.database.presto;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@MapperScan(basePackages = PrestoDBConfiguration.PACKAGE, sqlSessionFactoryRef = "prestoSqlSessionFactory")
public class PrestoDBConfiguration {

    // 精确到 presto 目录，以便跟其他数据源隔离
//    static final String PACKAGE = "com.aldwx.bigdata.modules.*.dao.presto";
//    static final String MAPPER_LOCATION = "classpath:mapper/presto/*.xml";
//    static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

    @Value("${presto.datasource.url}")
    private String url;

    @Value("${presto.datasource.username}")
    private String user;

    @Value("${presto.datasource.password}")
    private String password;

    @Value("${presto.datasource.driverClassName}")
    private String driverClass;


    @Bean(name = "prestoDataSource")
    public DataSource prestoDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
//        dataSource.setUsername(user);
//        dataSource.setPassword(password);
        Properties properties = new Properties();
        properties.setProperty("user","root");
        dataSource.setConnectProperties(properties);
        return dataSource;
    }


    @Bean(name = "prestoTransactionManager")
    public DataSourceTransactionManager prestoTransactionManager() {
        return new DataSourceTransactionManager(prestoDataSource());
    }


    @Bean(name = "prestoSqlSessionFactory")
    public SqlSessionFactory prestoSqlSessionFactory(@Qualifier("prestoDataSource") DataSource prestoDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(prestoDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(PrestoDBConfiguration.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
