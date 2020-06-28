package com.aldwx.bigdata.common.database.mysql;

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

@Configuration
//@ConfigurationProperties(prefix = "spring", locations="")
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = BusDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "busSqlSessionFactory")
public class BusDataSourceConfig {

    // 精确到 bus 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.aldwx.bigdata.modules.*.dao.bus";
    static final String MAPPER_LOCATION = "classpath:mapper/bus/*.xml";

    @Value("${bus.datasource.url}")
    private String url;

    @Value("${bus.datasource.username}")
    private String user;

    @Value("${bus.datasource.password}")
    private String password;

    @Value("${bus.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "busDataSource")
    public DataSource busDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "busTransactionManager")
    public DataSourceTransactionManager busTransactionManager() {
        return new DataSourceTransactionManager(busDataSource());
    }

    @Bean(name = "busSqlSessionFactory")
    public SqlSessionFactory busSqlSessionFactory(@Qualifier("busDataSource") DataSource busDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(busDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(BusDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}