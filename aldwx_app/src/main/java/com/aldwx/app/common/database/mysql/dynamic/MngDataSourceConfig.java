package com.aldwx.app.common.database.mysql.dynamic;//package com.aldwx.app.common.database.mysql;

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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 统计平台小程序数据源配置
 * @author
 * @description
 * @createTime
 **/
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(value = "com.aldwx.app.common.database.mysql.dynamic",basePackages = MngDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mngSqlSessionFactory")
public class MngDataSourceConfig {

    private static final Logger LOG = LoggerFactory.getLogger(MngDataSourceConfig.class);

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.aldwx.app.common.database.mysql.dynamic.DataSourceDefaultDao";
    static final String MAPPER_LOCATION = "classpath:mapper/stat/*/*.xml";

    @Value("${manage.app.datasource.url}")
    private String url;

    @Value("${manage.app.datasource.username}")
    private String user;

    @Value("${manage.app.datasource.password}")
    private String password;

    @Value("${mysql.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "mngDataSource")
    public DataSource mngDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }



    @Bean(name = "mngSqlSessionFactory")
    public SqlSessionFactory mngSqlSessionFactory(@Qualifier("mngDataSource") DataSource mngDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mngDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MngDataSourceConfig.MAPPER_LOCATION));

        return sessionFactory.getObject();
    }
}
