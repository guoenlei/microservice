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

//    微信小程序数据源
    @Value("${presto.datasource.wxmini.url}")
    private String url;
//    @Value("${presto.datasource.username}")
//    private String user;
//    @Value("${presto.datasource.password}")
//    private String password;
    @Value("${presto.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "prestoWXMiniDataSource")
    public DataSource prestoWXMiniDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
//        使用用户名密码需要SSL
//        dataSource.setUsername(user);
//        dataSource.setPassword(password);
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
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(PrestoDBConfiguration.MAPPER_LOCATION));
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
