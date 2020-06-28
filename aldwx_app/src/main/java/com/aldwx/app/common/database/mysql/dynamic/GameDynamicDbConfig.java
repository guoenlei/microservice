//package com.aldwx.app.common.database.mysql.Dynamic;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = GameDynamicDbConfig.PACKAGE, sqlSessionFactoryRef = "dynamicSqlSessionFactory2")
//public class GameDynamicDbConfig {
//    /**
//     * mybatis mapper resource 路径
//     */
//    private static String MAPPER_PATH = "mybatis/mapper/**.xml";
//
//    static final String PACKAGE = "com.aldwx.app.modules.*.dao.game";
//    static final String MAPPER_LOCATION = "classpath:mapper/game/*/*.xml";
//    private final DataSource dynamicDS;
//
//    @Autowired
//    public GameDynamicDbConfig(@Qualifier("dynamicSource") DataSource dynamicSource) {
//        this.dynamicDS = dynamicSource;
//    }
//
//    @Bean
//    @Primary
//    public SqlSessionFactory dynamicSqlSessionFactory2() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
////        factoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
//        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
////        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_LOCATION;
//        factoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(MAPPER_LOCATION));
//        factoryBean.setDataSource(dynamicDS);
//        return factoryBean.getObject();
//
//    }
//}
