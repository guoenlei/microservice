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
@MapperScan(basePackages = ReportGameDatasourceConfig.PACKAGE, sqlSessionFactoryRef = "gameSqlSessionFactory")
public class ReportGameDatasourceConfig {

    // 精确到 game 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.aldwx.bigdata.modules.*.dao.game";
    static final String MAPPER_LOCATION = "classpath:mapper/report/Game*.xml,classpath:mapper/report/Report*.xml,";

    @Value("${game.datasource.url}")
    private String url;

    @Value("${game.datasource.username}")
    private String user;

    @Value("${game.datasource.password}")
    private String password;

    @Value("${game.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "gameDataSource")
    public DataSource gameDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "gameTransactionManager")
    public DataSourceTransactionManager gameTransactionManager() {
        return new DataSourceTransactionManager(gameDataSource());
    }

    @Bean(name = "gameSqlSessionFactory")
    public SqlSessionFactory gameSqlSessionFactory(@Qualifier("gameDataSource") DataSource gameDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(gameDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ReportGameDatasourceConfig.MAPPER_LOCATION));
        Resource[] resources = null;
        List<Resource> list = new ArrayList<>();
        if (ReportGameDatasourceConfig.MAPPER_LOCATION.indexOf(",") > 0) {
            String[] paths = ReportGameDatasourceConfig.MAPPER_LOCATION.split(",");
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