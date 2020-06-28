package com.aldwx.app.modules.share.dao.stat;

import com.aldwx.app.common.base.BaseDao;
import com.aldwx.app.modules.share.bean.Share;
import com.aldwx.app.modules.share.entity.ShareEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 分享
 */
@Mapper
public interface ShareStatDao extends BaseDao {


    /**
     * 行为 - 分享分析 -
     * @param share
     * @return
     */
    @Results(
            value = {
                    @Result(property = "share_count", column = "share_count"),
                    @Result(property = "share_user_count", column = "share_user_count"),
                    @Result(property = "new_count", column = "new_count"),
            })
    @Select(" SELECT  SUM(share_count) as share_count,SUM(share_user_count) as share_user_count," +
            "SUM(new_count) as new_count " +
            "from ${tableName} " +
            "WHERE day in ${dateSql} " +
            "AND app_key=#{appKey} ")
    ShareEntity queryShareView(Share share);


    @Results(
            value = {
                    @Result(property = "first_share_user_count", column = "first_share_user_count"),
                    @Result(property = "first_share_count", column = "first_share_count"),
                    @Result(property = "secondary_share_user_count", column = "secondary_share_user_count"),
                    @Result(property = "secondary_share_count", column = "secondary_share_count"),
                    @Result(property = "third_share_user_count", column = "third_share_user_count"),
                    @Result(property = "third_share_count", column = "third_share_count"),
            })
    @Select("SELECT " +
            "sum(first_share_user_count) as first_share_user_count," +
            "sum(first_share_count) as first_share_count," +
            "sum(secondary_share_user_count) as secondary_share_user_count," +
            "sum(secondary_share_count) as secondary_share_count, " +
            "sum(third_share_user_count) as third_share_user_count," +
            "sum(third_share_count) as third_share_count " +
            "from ${tableName} " +
            "WHERE day in ${dateSql} " +
            "AND app_key=#{appKey} group by app_key ")
    List<ShareEntity> querySharePie(Share share);


    @Results(
            value = {
                    @Result(property = "share_count", column = "share_count"),
                    @Result(property = "share_user_count", column = "share_user_count"),
                    @Result(property = "new_count", column = "new_count"),
                    @Result(property = "hour", column = "hour"),
                    @Result(property = "day", column = "day"),
            })
    @Select("SELECT share_user_count,share_count,new_count,day,hour " +
            "from ${tableName} " +
            "WHERE day in ${dateSql} " +
            "AND app_key=#{appKey} ")
    List<ShareEntity> queryShareHourChart(Share share);


    @Results(
            value = {
                    @Result(property = "share_count", column = "share_count"),
                    @Result(property = "share_user_count", column = "share_user_count"),
                    @Result(property = "new_count", column = "new_count"),
                    @Result(property = "day", column = "day")
            })
    @Select("SELECT share_user_count, share_count, new_count, day " +
            "from ${tableName} " +
            "WHERE day in ${dateSql} " +
            "AND app_key=#{appKey} ")
    List<ShareEntity> queryShareDayChart(Share share);

    @Results(
            value = {
                    @Result(property = "count", column = "count"),
                    @Result(property = "status", column = "status"),
                    @Result(property = "avatar_url", column = "avatar_url"),
                    @Result(property = "nickname", column = "nickname"),
            })
    @Select(" SELECT sum(a.count) as count, a.status,b.nickname, b.avatar_url  " +
            "from ${tableName} a left JOIN ald_wechat_user_bind b on a.uu=b.uuid " +
            "WHERE a.day=#{dateTime} " +
            "AND a.app_key=#{appKey} " +
            "AND a.status=#{type} " +
            " GROUP BY a.uu,a.status ORDER BY a.status asc,count desc ")
    List<ShareEntity> queryShareList(Share share);


    @Results(
            value = {
                    @Result(property = "share_user_count", column = "share_user_count"),
                    @Result(property = "gender", column = "gender"),
            })
    @Select("SELECT SUM(share_user_count) as share_user_count, gender " +
            "from ${tableName} " +
            "WHERE day in ${dateSql} " +
            "AND app_key=#{appKey}  GROUP BY gender ")
    List<ShareEntity> queryShareSexInfo(Share share);

    @Results(
            value = {
                    @Result(property = "share_user_count", column = "share_user_count"),
                    @Result(property = "source", column = "source"),
            })
    @Select("SELECT source,SUM(share_open_user_count) as share_user_count " +
            "from ${tableName} " +
            "WHERE day in ${dateSql} " +
            "AND app_key=#{appKey} GROUP BY source ")
    List<ShareEntity> queryShareSourceInfo(Share share);

    @Results(
            value = {
                    @Result(property = "share_user_count", column = "share_user_count"),
                    @Result(property = "city", column = "city"),
            })
    @Select("SELECT city,SUM(share_user_count) as share_user_count " +
            "from ${tableName} " +
            "WHERE day in ${dateSql} " +
            "AND app_key=#{appKey} GROUP BY city ")
    List<ShareEntity> queryShareCityInfo(Share share);





}
