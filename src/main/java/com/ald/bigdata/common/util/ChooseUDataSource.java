package com.ald.bigdata.common.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

import static com.ald.bigdata.common.constants.Constants.*;
import static com.ald.bigdata.common.database.mysql.TrendDataSourceConf.oldConnMessage;

/**
 * @author guoenlei
 * @date 2019-08-14
 * @version 1.0
 * 传入的ak不同，对应数据源不同。（从分库索引表查询）
 */
public class ChooseUDataSource {
    // 根据接口传入的PLATFORM和TYPE即可判断出默认数据库。te暂时用不到。保留te，保持一致性。
    public static JdbcTemplate chooseYourDataSource(String app_key, String te) {
        Logger logger = LoggerFactory.getLogger(ChooseUDataSource.class);
        JdbcTemplate defaultJdbcTemplate = oldConnMessage.get("default");
        // 1.在默认库索引表中查询ak，并赋值给result。要么是一条连接信息的map，要么是空。
        // TODO change table from ald_db_split_geltest to ald_db_split
        String dbInfoSQL = "select app_key,conn_name,dbname,dbip,port,dbuser,dbpassword,platform from ald_db_split " +
//        String dbInfoSQL = "select app_key,conn_name,dbname,dbip,port,dbuser,dbpassword,platform from ald_db_split_geltest " +
                " where app_key = '" + app_key + "';";
        Map<String, Object> result = null;
        try {
            result = defaultJdbcTemplate.queryForMap(dbInfoSQL);
        } catch (DataAccessException e) {
            // TODO delete sout
            logger.info("from dbSplitIndex table: {}", result);
        }

        // 2.查到连接信息，则判斷是否已經存在。dbname是唯一的标识。
        if (result != null) {
            String ip = result.get("dbip").toString();
            String port = result.get("port").toString();
            String dbname = result.get("dbname").toString();

            String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbname + "?useUnicode=true&amp;characterEncoding=utf-8";
            String user = result.get("dbuser").toString();
            String password = result.get("dbpassword").toString();
            String driverClass = "com.mysql.jdbc.Driver";

            // 3.判斷oldConnMessage中是否已經存在，存在則直接返回oldConnMessage中的
            for (String olddbname : oldConnMessage.keySet()) {
                if (StringUtils.equals(dbname, olddbname)) {
                    // TODO delete assist info
                    logger.info("Data source in use: {}", olddbname);
                    return oldConnMessage.get(olddbname);
                }
            }
            // 4.不存在，则新建DataSource和JdbcTemplate，並添加到oldConnMessage中。
            JdbcTemplate newJdbcTemplate = addJdbcTemplateToMap(dbname, url, user, password, driverClass);
            logger.info("use new dataSource: {}", dbname);
            return newJdbcTemplate;

        } else {
            // 5.索引表中没查出来，根据各接口二级路径传入的PLATFORM,TYPE判断是哪个数据源，并返回对应的jdbcTemplate。
            // qx爲例：q,w對應QQ和WX； x,g對應小程序和小游戲。
            if (StringUtils.equals(te, QQ_MINI)) {
                // TODO delete assist info
                logger.info("use qqMini default dataSource: {}", oldConnMessage.get("qqMini"));
                return oldConnMessage.get("qqMini");
            } else if(StringUtils.equals(te, QQ_GAME)){
                logger.info("use qqGame default dataSource: {}", oldConnMessage.get("qqGame"));
                return oldConnMessage.get("qqGame");
            } else if (StringUtils.equals(te, WX_GAME)) {
                logger.info("use wxGame default dataSource: {}", oldConnMessage.get("wxGame"));
                return oldConnMessage.get("wxGame");
            } else {
                logger.info("use wxMini default dataSource: {}", oldConnMessage.get("wxMini"));
                return oldConnMessage.get("wxMini");
            }
        }
    }

    /**
     * 新建DataSource和JdbcTemplate，並添加到oldConnMessage中。
     * @param dbname
     * @param url
     * @param user
     * @param password
     * @param driverClass
     * @return
     */
    private static JdbcTemplate addJdbcTemplateToMap(String dbname, String url, String user, String password, String driverClass) {
        JdbcTemplate newJdbcTemplate = new JdbcTemplate();
        DruidDataSource newDataSource = new DruidDataSource();
        newDataSource.setDriverClassName(driverClass);
        newDataSource.setUrl(url);
        newDataSource.setUsername(user);
        newDataSource.setPassword(password);
        newJdbcTemplate.setDataSource(newDataSource);
        oldConnMessage.put(dbname, newJdbcTemplate);
        return newJdbcTemplate;
    }
}

