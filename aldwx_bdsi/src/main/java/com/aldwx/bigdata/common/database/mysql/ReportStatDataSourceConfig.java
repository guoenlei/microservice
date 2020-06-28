package com.aldwx.bigdata.common.database.mysql;

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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
// 扫描 Mapper 接口并容器管理
//@PropertySource("jdbc.properties")
//@ConfigurationProperties()
@MapperScan(basePackages = ReportStatDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "statSqlSessionFactory")
public class ReportStatDataSourceConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.aldwx.bigdata.modules.*.dao.stat";
    static final String MAPPER_LOCATION = "classpath:mapper/report/Stat*.xml,classpath:mapper/report/Report*.xml,";

    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String user;

    @Value("${master.datasource.password}")
    private String password;

    @Value("${master.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "statDataSource")
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "statTransactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }


    @Bean(name = "statSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("statDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ReportStatDataSourceConfig.MAPPER_LOCATION));
        Resource[] resources = null;
        List<Resource> list = new ArrayList<>();
        if (ReportStatDataSourceConfig.MAPPER_LOCATION.indexOf(",") > 0) {
            String[] paths = ReportStatDataSourceConfig.MAPPER_LOCATION.split(",");
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

       /* //分页处理
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();

        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //是否分页合理化
        properties.setProperty("reasonable", "false");
        interceptor.setProperties(properties);

        sessionFactory.setPlugins(new Interceptor[]{interceptor});*/

        return sessionFactory.getObject();
    }
}

