package com.aldwx.app.common.database.mysql.dynamic;

/**
 * Created with IntelliJ IDEA.
 * User: @ziyu  freedomziyua@gmail.com
 * Date: 2019-08-06
 * Time: 11:48
 * Description:
 */

import com.aldwx.app.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: @ziyu  freedomziyua@gmail.com
 * Date: 2019-07-31
 * Time: 20:33
 * Description:
 */
@Mapper
public interface DataSourceDefaultDao extends BaseDao {

    @Results(
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "appKey", column = "app_key"),
                    @Result(property = "connName", column = "conn_name"),
                    @Result(property = "dbName", column = "dbname"),
                    @Result(property = "dbIp", column = "dbip"),
                    @Result(property = "port", column = "port"),
                    @Result(property = "dbUser", column = "dbuser"),
                    @Result(property = "dbPassword", column = "dbpassword"),
            })
    @Select("select * FROM ${tableName} WHERE 1=1 AND app_key=#{appKey}")
    List<SourceDatabaseBean> queryByAppKey(SourceDatabaseBean sourceDatabaseBean);
}
