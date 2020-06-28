//package com.aldwx.app.common.database.mysql.Dynamic;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 *统计平台小游戏数据源配置
 * @author
 * @description
 * @createTime
 **/
//@Configuration
//@MapperScan(value = "com.aldwx.app.common.database.mysql.Dynamic",basePackages = GameDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "gameSqlSessionFactory")
//public class GameDataSourceConfig {
//
//    private static final Logger LOG = LoggerFactory.getLogger(GameDataSourceConfig.class);
//
//    // 精确到 game 目录，以便跟其他数据源隔离
//    static final String PACKAGE = "com.aldwx.app.modules.*.dao.game";
//    static final String MAPPER_LOCATION = "classpath:mapper/game/*/*.xml";
/*
    @Value("${stat.game.datasource.url}")
    private String url;

    @Value("${stat.game.datasource.username}")
    private String user;

    @Value("${stat.game.datasource.password}")
    private String password;

    @Value("${mysql.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "gameDataSource")
    public DataSource gameDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }*/

 /*   @Bean(name = "gameTransactionManager")
    public DataSourceTransactionManager gameTransactionManager() {
        return new DataSourceTransactionManager(gameDataSource());
    }*/


//    @Bean(name = "gameSqlSessionFactory")
//    public SqlSessionFactory gameSqlSessionFactory(@Qualifier("gameDataSource") DataSource gameDataSource)
//            throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(gameDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(GameDataSourceConfig.MAPPER_LOCATION));
//
//        return sessionFactory.getObject();
//    }
  /*  @Bean(name = "gameSqlSessionFactory")
    public SqlSessionFactory gameSqlSessionFactory(@Qualifier("gameDataSource") DataSource gameDataSource)
            throws Exception {
       *//* ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(statDataSource);
        factory.setMapperLocations(resolver.getResources(GameDataSourceConfig.MAPPER_LOCATION));
*//*
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(gameDataSource);
        return sessionFactory.getObject();
    }*/
//}
