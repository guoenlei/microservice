package com.aldwx.bigdata.modules.dbinfo.dao.master;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.dbinfo.entity.AldDbInfoEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AldDbInfoDao extends BaseDao {



    /**
     * 获取所有数据列表
     * @param appKey
     * @return
     */
    @Results(
            value = {
            @Result(property = "appKey", column = "app_key"),
            @Result(property = "connName", column = "conn_name"),
            @Result(property = "dbName", column = "dbName"),
            @Result(property = "dbIp", column = "dbIp"),
            @Result(property = "port", column = "port"),
            @Result(property = "dbUser", column = "dbUser"),
            @Result(property = "dbPassword", column = "dbPassword"),
    })
    @Select("select app_key, conn_name, dbName, dbIp, port, dbUser, dbpassword " +
            "from ald_db_split where app_key = #{app_Key}")
    List<AldDbInfoEntity> selectAll(@Param("app_Key") String appKey);



    /**
     * 获取一条数据
     * @param appKey
     * @return
     */
    @Results(
            value = {
                    @Result(property = "appKey", column = "app_key"),
                    @Result(property = "connName", column = "conn_name"),
                    @Result(property = "dbName", column = "dbName"),
                    @Result(property = "dbIp", column = "dbIp"),
                    @Result(property = "port", column = "port"),
                    @Result(property = "dbUser", column = "dbUser"),
                    @Result(property = "dbPassword", column = "dbPassword"),
            })
    @Select("select app_key, conn_name, dbName, dbIp, port, dbUser, dbpassword " +
            "from ald_db_split where app_key = #{app_Key}")
    AldDbInfoEntity selectOne(@Param("app_Key") String appKey);



//    @SelectProvider(type=Test.class, method="test")
//    String select();
//
//    class Test {
//        String test(){
//
//            return null;
//        }
//
//    }

}
