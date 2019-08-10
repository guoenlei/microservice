package com.ald.bigdata.common.database.dynamic;

import com.ald.bigdata.common.base.BaseVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class DynamicDataSourceAspect {
    @Value("${master.datasource.driverClassName}")
    private String driverClass;

    @Autowired
    DataSourceDefaultDao dataSourceDefaultDao;

    @Autowired
    DynamicDataSource dynamicDataSource;

    //    @Before("@annotation(com.aldwx.app.common.database.mysql.Dynamic.DBSource)")
    @Before("execution(* com.ald.bigdata.modules.*.service.impl..*(..)) || execution(* com.ald.bigdata.modules.*.service..*(..))")
    public void beforeSwitchDS(JoinPoint point) throws IllegalAccessException, InstantiationException {
        // 切换数据源
//        DataSourceContextHolder.setDB(dataSource);
        for (Object o : point.getArgs()) {
            if (o != null && ((String) o).length() == 32) {
                String appKey = null;
                if (o instanceof BaseVo) {
                    appKey = ((BaseVo) o).getAppKey();
                }
                appKey = ((String) o);

                //部分接口不符合appType 的规范，则需要强转
//                if(appType==null){
//                    appType=((RetainVo) o).getType();
//                }

                //1.首先查找字典表是否为空，如果为空则设置默认的连接数据源
                SourceDatabaseBean sourceDatabaseBean = new SourceDatabaseBean();
                sourceDatabaseBean.setAppKey(appKey);
                //TODO 上线提测记得更改这部分代码，改成现上环境的字典表
                sourceDatabaseBean.setTableName("ald_db_split_copy");
                List<SourceDatabaseBean> sourceDatabaseBeans = this.dataSourceDefaultDao.queryByAppKey(sourceDatabaseBean);

                if (sourceDatabaseBeans == null || sourceDatabaseBeans.size() == 0) { //属于第一种情况
                    DataSourceContextHolder.setDB(DataSourceContextHolder.DEFAULT_DS);
                    dynamicDataSource.determineTargetDataSource();
                   /* if(isStatApp(appType)){
                        DataSourceContextHolder.setDB(DataSourceContextHolder.DEFAULT_DS);
                        dynamicDataSource.determineTargetDataSource();
                    }else{
                        DataSourceContextHolder.setDB(DataSourceContextHolder.DEFAULT_DS_GAME);
                        dynamicDataSource.determineTargetDataSource();
                    }*/
                } else {
                    //理论上有且仅有一条记录
                    SourceDatabaseBean sdb = sourceDatabaseBeans.get(0);
                    String dbName = sdb.getDbName();
                    if (DataSourceConfig.dsMap.get(dbName) == null) {
                        DataSourceConfig.dsMap.put(dbName, DataSourceConfig.createDataSource(
                                "jdbc:mysql://" + sdb.getDbIp() + ":" + sdb.getPort() + "/" + sdb.getDbName() + "?useUnicode=true&amp;characterEncoding=utf-8",
                                sdb.getDbUser(),
                                sdb.getDbPassword(),
                                driverClass
                        ));

                        //由于不存在一个数据库有多个Ip 的情况，所以DataSource的Key设置为dbName
                        DataSourceContextHolder.setDB(sdb.getDbName());
                        dynamicDataSource.determineTargetDataSource();
                    } else {
                        DataSourceContextHolder.setDB(sdb.getDbName());
                        dynamicDataSource.determineTargetDataSource();
                    }
                }
            }
        }
    }


    @After("@annotation(com.ald.bigdata.common.database.dynamic.DBSource)")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceContextHolder.clearDB();
    }


    /**
     * 判断是否为统计小程序
     * 是小程序，返回true；否则如果是小游戏，返回false
     * @param appType
     * @return
     */
//    public boolean isStatApp(String appType) {
//        boolean isStatApp = false;
//        String aldStatFlag = ConfigurationManager.getProperty(Constants.ALD_STAT_FLAG);
//        String aldGameFlag = ConfigurationManager.getProperty(Constants.ALD_GAME_FLAG);
//        if(appType.equals(aldStatFlag)) {
//            isStatApp = true;
//        } else if(appType.equals(aldGameFlag)) {
//            isStatApp = false;
//        }
//
//        return isStatApp;
//    }

}
