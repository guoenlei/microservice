package com.aldwx.app.common.database.mysql.dynamic;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@MapperScan(basePackages = DynamicDbConfig.PACKAGE, sqlSessionFactoryRef = "dynamicSqlSessionFactory")
public class DynamicDbConfig {
    /**
     * mybatis mapper resource 路径
     */
    private static String MAPPER_PATH = "mybatis/mapper/**.xml";

    static final String PACKAGE = "com.aldwx.app.modules.*.dao.game,com.aldwx.app.modules.*.dao.stat";
    static final String MAPPER_LOCATION = "classpath*:mapper/stat/*/*.xml,classpath*:mapper/game/*/*.xml";
    private final DataSource dynamicDS;

    @Autowired
    public DynamicDbConfig(@Qualifier("dynamicSource") DataSource dynamicSource) {
        this.dynamicDS = dynamicSource;
    }

    @Bean
    @Primary
    public SqlSessionFactory dynamicSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
//        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_LOCATION;
        factoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(MAPPER_LOCATION));

        Resource[] resources = null;
        List<Resource> list = new ArrayList<>();
        if (DynamicDbConfig.MAPPER_LOCATION.indexOf(",") > 0) {
            String[] paths = DynamicDbConfig.MAPPER_LOCATION.split(",");
            for (String path : paths) {
                list.addAll(Arrays.asList(new PathMatchingResourcePatternResolver()
                        .getResources(path)));
            }
            resources = list.toArray(new Resource[list.size()]);
        } else {
            resources = new PathMatchingResourcePatternResolver()
                    .getResources(DynamicDbConfig.MAPPER_LOCATION);
        }
        factoryBean.setMapperLocations(resources);
        factoryBean.setDataSource(dynamicDS);
        return factoryBean.getObject();

    }
}
