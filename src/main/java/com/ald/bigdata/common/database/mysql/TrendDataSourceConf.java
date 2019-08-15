package com.ald.bigdata.common.database.mysql;

import com.ald.bigdata.common.util.ChooseUDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class TrendDataSourceConf {
    /**
     * 思路：
     * 1.创建一个configDataSource,静态集合(访问方便，避免竞争），包括4+1个数据源。
     * 2.在controller处加工具类（chooseYouJdbCTemplate）返回值为jdbcTemplate，然后传给service。
     * 3.工具类里有choose方法（jdbctemplate在方法体内）
     * 4.choose：先查找default库。
     * 5.如果找到了，则在configDataSource静态类中判断是否已经存在，存在直接拿，否则set连接信息put进去
     * 6.如果没找到，则判断类型是QQ还是WX，是Game还是Mini，设置其默认数据源。
     *
     * oldConnMessage是静态集合，用于存放Map<dbname, JdbcTemplate>
     *     對應關係如下：
     * default,defaultJdbcTemplate
     * qqMini,qqMiniJdbcTemplate
     * qqGame,qqGameJdbcTemplate
     * wxMini,wxMiniJdbcTemplate
     * wxGame,wxGameJdbcTemplate
     * dbSplit,dbSplitJdbcTemplate
     */
    public static ConcurrentHashMap<String, JdbcTemplate> oldConnMessage = new ConcurrentHashMap<>();

    /**
     * 分库表所在数据源
     */
    @Value("${default.datasource.url}")
    private String default_url;
    @Value("${default.datasource.username}")
    private String default_user;
    @Value("${default.datasource.password}")
    private String default_password;
    @Value("${default.datasource.driverClassName}")
    private String default_driverClass;

    @Primary
    @Bean(name = "defaultDataSource")
    public DataSource defaultDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(default_driverClass);
        dataSource.setUrl(default_url);
        dataSource.setUsername(default_user);
        dataSource.setPassword(default_password);
        return dataSource;
    }

    @Primary
    @Bean(name = "defaultTransactionManager")
    public DataSourceTransactionManager defaultTransactionManager() {
        return new DataSourceTransactionManager(defaultDataSource());
    }

    @Primary
    @Bean(name = "defaultJdbcTemplate")
    public JdbcTemplate defaultJdbcTemplate(
            @Qualifier("defaultDataSource") DataSource defaultDataSource) {
        return new JdbcTemplate(defaultDataSource);
    }

    @Primary
    @Bean(name = "defaultSqlSessionFactory")
    public SqlSessionFactory miniSqlSessionFactory(@Qualifier("defaultDataSource") DataSource defaultDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(defaultDataSource);
        oldConnMessage.put("default", defaultJdbcTemplate(defaultDataSource));
        return sessionFactory.getObject();
    }

    /**
     * QQMini数据源
     */
    @Value("${QQmaster.datasource.url}")
    private String qqMini_url;
    @Value("${QQmaster.datasource.username}")
    private String qqMini_user;
    @Value("${QQmaster.datasource.password}")
    private String qqMini_password;
    @Value("${QQmaster.datasource.driverClassName}")
    private String qqMini_driverClass;

    @Bean(name = "qqMiniDataSource")
    public DataSource qqMiniDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(qqMini_driverClass);
        dataSource.setUrl(qqMini_url);
        dataSource.setUsername(qqMini_user);
        dataSource.setPassword(qqMini_password);
        return dataSource;
    }

    @Bean(name = "qqMiniTransactionManager")
    public DataSourceTransactionManager qqMiniTransactionManager() {
        return new DataSourceTransactionManager(qqMiniDataSource());
    }

    @Bean(name = "qqMiniJdbcTemplate")
    public JdbcTemplate qqMiniJdbcTemplate(
            @Qualifier("qqMiniDataSource") DataSource qqMiniDataSource) {
        return new JdbcTemplate(qqMiniDataSource);
    }

    @Bean(name = "qqMiniSqlSessionFactory")
    public SqlSessionFactory qqMiniSqlSessionFactory(
            @Qualifier("qqMiniDataSource") DataSource qqMiniDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(qqMiniDataSource);
        oldConnMessage.put("qqMini", qqMiniJdbcTemplate(qqMiniDataSource));

        return sessionFactory.getObject();
    }

    /**
     * QQGame数据源
     */
    @Value("${QQgame.datasource.url}")
    private String qqGame_url;
    @Value("${QQgame.datasource.username}")
    private String qqGame_user;
    @Value("${QQgame.datasource.password}")
    private String qqGame_password;
    @Value("${QQgame.datasource.driverClassName}")
    private String qqGame_driverClass;

    @Bean(name = "qqGameDataSource")
    public DataSource qqGameDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(qqGame_driverClass);
        dataSource.setUrl(qqGame_url);
        dataSource.setUsername(qqGame_user);
        dataSource.setPassword(qqGame_password);
        return dataSource;
    }

    @Bean(name = "qqGameTransactionManager")
    public DataSourceTransactionManager qqGameTransactionManager() {
        return new DataSourceTransactionManager(qqGameDataSource());
    }

    @Bean(name = "qqGameJdbcTemplate")
    public JdbcTemplate qqGameJdbcTemplate(
            @Qualifier("qqGameDataSource") DataSource qqGameDataSource) {
        return new JdbcTemplate(qqGameDataSource);
    }

    @Bean(name = "qqGameSqlSessionFactory")
    public SqlSessionFactory qqGameSqlSessionFactory(
            @Qualifier("qqGameDataSource") DataSource qqGameDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(qqGameDataSource);
        oldConnMessage.put("qqGame", qqGameJdbcTemplate(qqGameDataSource));
        return sessionFactory.getObject();
    }

    /**
     * WXMini数据源
     */
    @Value("${master.datasource.url}")
    private String wxMini_url;
    @Value("${master.datasource.username}")
    private String wxMini_user;
    @Value("${master.datasource.password}")
    private String wxMini_password;
    @Value("${master.datasource.driverClassName}")
    private String wxMini_driverClass;

    @Bean(name = "wxMiniDataSource")
    public DataSource wxMiniDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(wxMini_driverClass);
        dataSource.setUrl(wxMini_url);
        dataSource.setUsername(wxMini_user);
        dataSource.setPassword(wxMini_password);
        return dataSource;
    }

    @Bean(name = "wxMiniTransactionManager")
    public DataSourceTransactionManager wxMiniTransactionManager() {
        return new DataSourceTransactionManager(wxMiniDataSource());
    }

    @Bean(name = "wxMiniJdbcTemplate")
    public JdbcTemplate wxMiniJdbcTemplate(
            @Qualifier("wxMiniDataSource") DataSource wxMiniDataSource) {
        return new JdbcTemplate(wxMiniDataSource);
    }

    @Bean(name = "wxMiniSqlSessionFactory")
    public SqlSessionFactory wxMiniSqlSessionFactory(
            @Qualifier("wxMiniDataSource") DataSource wxMiniDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(wxMiniDataSource);
        oldConnMessage.put("wxMini", wxMiniJdbcTemplate(wxMiniDataSource));
        return sessionFactory.getObject();
    }

    /**
     * WXGame数据源
     */
    @Value("${game.datasource.url}")
    private String wxGame_url;
    @Value("${game.datasource.username}")
    private String wxGame_user;
    @Value("${game.datasource.password}")
    private String wxGame_password;
    @Value("${game.datasource.driverClassName}")
    private String wxGame_driverClass;

    @Bean(name = "wxGameDataSource")
    public DataSource wxGameDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(wxGame_driverClass);
        dataSource.setUrl(wxGame_url);
        dataSource.setUsername(wxGame_user);
        dataSource.setPassword(wxGame_password);
        return dataSource;
    }

    @Bean(name = "wxGameTransactionManager")
    public DataSourceTransactionManager wxGameTransactionManager() {
        return new DataSourceTransactionManager(wxGameDataSource());
    }

    @Bean(name = "wxGameJdbcTemplate")
    public JdbcTemplate wxGameJdbcTemplate(
            @Qualifier("wxGameDataSource") DataSource wxGameDataSource) {
        return new JdbcTemplate(wxGameDataSource);
    }

    @Bean(name = "wxGameSqlSessionFactory")
    public SqlSessionFactory wxGameSqlSessionFactory(@Qualifier("wxGameDataSource") DataSource wxGameDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(wxGameDataSource);
        oldConnMessage.put("wxGame", wxGameJdbcTemplate(wxGameDataSource));
        return sessionFactory.getObject();
    }


    /**
     * 从分库索引表中查询出来不为空时。
     * @return
     */

    @Bean(name = "dbSplitDataSource")
    public DataSource dbSplitDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        // 默认把dbSplitDataSource的数据源设置为wxMini的。
        dataSource.setDriverClassName(wxMini_driverClass);
        dataSource.setUrl(wxMini_url);
        dataSource.setUsername(wxMini_user);
        dataSource.setPassword(wxMini_password);
        return dataSource;
    }

    @Bean(name = "dbSplitTransactionManager")
    public DataSourceTransactionManager dbSplitTransactionManager() {
        return new DataSourceTransactionManager(dbSplitDataSource());
    }

    @Bean(name = "dbSplitJdbcTemplate")
    public JdbcTemplate dbSplitJdbcTemplate(
            @Qualifier("dbSplitDataSource") DataSource dbSplitDataSource) {
        return new JdbcTemplate(dbSplitDataSource);
    }

    @Bean(name = "dbSplitSqlSessionFactory")
    public SqlSessionFactory dbSplitSqlSessionFactory(@Qualifier("dbSplitDataSource") DataSource dbSplitDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dbSplitDataSource);
        oldConnMessage.put("dbSplit", dbSplitJdbcTemplate(dbSplitDataSource));
        return sessionFactory.getObject();
    }

}
