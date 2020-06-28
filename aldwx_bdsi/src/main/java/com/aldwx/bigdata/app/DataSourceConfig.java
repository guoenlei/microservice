//package com.aldwx.bigdata.app;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//    @Bean(name = "mysqlDataSource")
//    @Qualifier("mysqlDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.mysql")
//    public DataSource mysqlDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
////    @Bean(name = "hiveDataSource")
////    @Qualifier("hiveDataSource")
////    @Primary
////    @ConfigurationProperties(prefix = "spring.datasource.hive")
////    public DataSource hiveDataSource() {
////        return DataSourceBuilder.create().build();
////    }
//
//    @Bean(name = "prestoDataSource")
//    @Qualifier("prestoDataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.presto")
//    public DataSource prestoDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//
//    @Bean(name = "mysqlJdbcTemplate")
//    public JdbcTemplate mysqlJdbcTemplate(
//            @Qualifier("mysqlDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
////    @Bean(name = "hiveJdbcTemplate")
////    public JdbcTemplate hiveJdbcTemplate(
////            @Qualifier("hiveDataSource") DataSource dataSource) {
////        return new JdbcTemplate(dataSource);
////    }
//
//    @Bean(name = "prestoJdbcTemplate")
//    public JdbcTemplate prestoJdbcTemplate(
//            @Qualifier("prestoDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//}
