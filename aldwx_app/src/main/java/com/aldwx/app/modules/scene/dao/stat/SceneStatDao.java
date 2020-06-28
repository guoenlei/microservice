package com.aldwx.app.modules.scene.dao.stat;


import com.aldwx.app.common.base.BaseDao;
import com.aldwx.app.modules.scene.bean.Scene;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 场景值-小程序
 */
@Component
@Mapper
public interface SceneStatDao extends BaseDao {



    /**
     * 概览 - 获取场景值topN - 列表
     * aldstat_scene_statistics
     * @param s
     * @return
     */
    @Results(
            value = {
                    @Result(property = "sceneName", column = "title"),
                    @Result(property = "sceneVisitorCount", column = "scene_visitor_count"), // 访问人数
                    @Result(property = "sceneOpenCount", column = "scene_open_count"), // 打开次数
                    @Result(property = "scenePageCount", column = "scene_page_count"), // 页面访问次数
                    @Result(property = "sceneNewerForApp", column = "scene_newer_for_app"), // 新用户数
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "secondaryStayTime", column = "total_stay_time"),
                    @Result(property = "onePageCount", column = "one_page_count"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
                    @Result(property = "sceneGroupId", column = "scene_group_id"),
            })
    @Select("SELECT a.scene_visitor_count, a.scene_open_count, a.scene_page_count, a.scene_newer_for_app, " +
            "a.total_stay_time, a.one_page_count, a.bounce_rate, b.title " +
            "FROM ${tableName} a, ald_cms_scene b " +
            "WHERE 1=1 " +
            "AND a.app_key=#{appKey} " +
            "AND a.day=#{dateTime} " +
            "AND a.scene_id=b.sid " +
            "ORDER BY a.${target} DESC limit 5 "
            )
    List<SceneEntity> querySceneTrendTopList(Scene s);


    /**
     * 概览 - 获取场景值topN - 列表 - 改需求后
     * 改需求后，查小程序TOPN走的此SQL语句，与querySceneTrendTopList区别在day处
     * @param s
     * @return
     */
    @Results(
            value = {
                    @Result(property = "sceneId", column = "sid"),
                    @Result(property = "sceneName", column = "title"),
                    @Result(property = "sceneVisitorCount", column = "scene_visitor_count"), // 访问人数
                    @Result(property = "sceneOpenCount", column = "scene_open_count"), // 打开次数
                    @Result(property = "scenePageCount", column = "scene_page_count"), // 页面访问次数
                    @Result(property = "sceneNewerForApp", column = "scene_newer_for_app"), // 新用户数
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "secondaryStayTime", column = "total_stay_time"),
                    @Result(property = "onePageCount", column = "one_page_count"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
                    @Result(property = "sceneGroupId", column = "scene_group_id"),
            })
    @Select("SELECT a.scene_visitor_count, a.scene_open_count, a.scene_page_count, a.scene_newer_for_app, " +
            "a.total_stay_time, a.one_page_count, a.bounce_rate, b.title " +
            "FROM ${tableName} a, ald_cms_scene b " +
            "WHERE 1=1 " +
            "AND a.scene_id=#{sceneId} " +
            "AND a.app_key=#{appKey} " +
            "AND a.day in ${dateSql} " +
            "AND a.scene_id=b.sid " +
            "ORDER BY a.${target} DESC limit 1 "
    )
    List<SceneEntity> querySceneTrendTopListNew(Scene s);


    /**
     * 来源 - 场景值 - 概览
     * @param scene
     * @return
     */
    @Results(
            value = {
                    @Result(property = "sceneName", column = "title"),
                    @Result(property = "sceneVisitorCount", column = "scene_visitor_count"),
                    @Result(property = "sceneOpenCount", column = "scene_open_count"),
                    @Result(property = "scenePageCount", column = "scene_page_count"),
                    @Result(property = "sceneNewerForApp", column = "scene_newer_for_app"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "onePageCount", column = "one_page_count"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
            })
    @Select("SELECT a.scene_visitor_count, a.scene_open_count, a.scene_page_count, " +
            "a.scene_newer_for_app, a.total_stay_time, a.one_page_count, a.bounce_rate " +
            "FROM ${tableName} a " +
            "WHERE 1=1 " +
            "AND a.app_key=#{appKey} " +
            "AND a.day in ${dateSql} ")
    List<SceneEntity> querySceneView(Scene scene);


    /**
     * 获取top的列表
     * @param scene
     * @return
     */
    @Results(
            value = {
                    @Result(property = "sceneId", column = "scene_id"),
            })
    @Select("SELECT c.scene_id from (SELECT scene_id, avg(${type}) as ${type} FROM ${tableName} " +
            "WHERE 1=1 \n" +
            "AND day IN ${dateSql} \n" +
            "AND app_key=#{appKey} \n" +
            "GROUP BY scene_id \n" +
            "ORDER BY ${type} desc \n" +
            "LIMIT ${limitNum}) c \n" )
    List<SceneEntity> querySceneDataTopList(Scene scene);
    @Results(
            value = {
                    @Result(property = "sceneId", column = "scene_id"),
            })
    @Select("SELECT sceneId FROM ${tableName}" )
    List<SceneEntity> query(Scene scene);



    /**
     * 来源 - 场景值趋势Top 小时- 折线图
     * aldstat_hourly_scene
     * @param s
     * @return
     */
    @Results(
            value = {
                    @Result(property = "hour", column = "hour"),
                    @Result(property = "sceneId", column = "scene_id"),
                    @Result(property = "sceneName", column = "title"),
                    @Result(property = "sceneVisitorCount", column = "visitor_count"),
                    @Result(property = "sceneNewerForApp", column = "new_comer_count"),
            })
    @Select("SELECT a.hour, a.scene_id, b.title, a.visitor_count, a.new_comer_count \n" +
            "FROM aldstat_hourly_scene a, ald_cms_scene b \n" +
            "WHERE 1=1 \n" +
            "AND a.day in ${dateSql} \n" +
            "AND a.app_key=#{appKey} \n" +
            "AND a.scene_id=b.sid \n" +
            "AND a.scene_id=#{sceneId} " +
            "GROUP BY a.scene_id, a.day, a.hour \n" +
            "ORDER BY a.day, ${type} ")
    List<SceneEntity> querySceneHourDataTopChart(Scene s);



    /**
     * 来源 - 场景值趋势Top 天- 折线图
     * @param s
     * @return
     */
    @Results(
            value = {
                    @Result(property = "day", column = "day"),
                    @Result(property = "sceneId", column = "scene_id"),
                    @Result(property = "sceneName", column = "title"),
                    @Result(property = "sceneVisitorCount", column = "scene_visitor_count"),
                    @Result(property = "sceneNewerForApp", column = "scene_newer_for_app"),
            })
    @Select("SELECT date_format(a.day, '%Y-%m-%d') as day, a.scene_id, b.title, a.scene_visitor_count, a.scene_newer_for_app \n" +
            "FROM ${tableName} a, ald_cms_scene b \n" +
            "WHERE 1=1 \n" +
            "AND a.day in ${dateSql} \n" +
            "AND a.app_key=#{appKey} \n" +
            "AND a.scene_id=b.sid \n" +
            "AND a.scene_id=#{sceneId} " +
            "GROUP BY a.scene_id, a.day \n" +
            "ORDER BY a.day, ${type} ")
    List<SceneEntity> querySceneDayDataTopChart(Scene s);




    /**
     * 来源 - 场景值明细 h - 列表
     * @param s
     * @return
     */
    @Results(
            value = {
                    @Result(property = "sceneName", column = "title"),
                    @Result(property = "sceneVisitorCount", column = "scene_visitor_count"),
                    @Result(property = "sceneOpenCount", column = "scene_open_count"),
                    @Result(property = "scenePageCount", column = "scene_page_count"),
                    @Result(property = "sceneNewerForApp", column = "scene_newer_for_app"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "secondaryStayTime", column = "total_stay_time"),
                    @Result(property = "onePageCount", column = "one_page_count"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
                    @Result(property = "sceneGroupId", column = "scene_group_id"),
            })
    @Select("SELECT \n" +
            "SUM(a.visitor_count) AS scene_visitor_count, \n" +
            "SUM(a.open_count) AS scene_open_count, \n" +
            "SUM(a.page_count) AS scene_page_count, \n" +
            "SUM(a.new_comer_count) AS scene_newer_for_app, \n" +
            "SUM(a.total_stay_time) AS total_stay_time,\n" +
            "SUM(a.secondary_stay_time) AS secondary_stay_time,\n" +
            "SUM(a.one_page_count) AS one_page_count,\n" +
            "SUM(a.one_page_count)/SUM(a.open_count) AS bounce_rate,\n" +
            "b.title\n" +
            "FROM ${tableName} a, ald_cms_scene b\n" +
            "WHERE 1=1\n" +
            "AND a.`day` IN ${dateSql}\n" +
            "AND a.app_key=#{appKey}\n" +
            "AND a.scene_id=b.sid\n" +
            "GROUP BY a.scene_id\n" +
            "ORDER BY a.visitor_count DESC\n"
    )
    List<SceneEntity> querySceneHourDataList(Scene s);




    /**
     * 来源 - 场景值明细 day - 列表
     * @param s
     * @return
     */
    @Results(
            value = {
                    @Result(property = "sceneName", column = "title"),
                    @Result(property = "sceneVisitorCount", column = "scene_visitor_count"),
                    @Result(property = "sceneOpenCount", column = "scene_open_count"),
                    @Result(property = "scenePageCount", column = "scene_page_count"),
                    @Result(property = "sceneNewerForApp", column = "scene_newer_for_app"),
//                    @Result(property = "totalStayTime", column = "total_stay_time"),
//                    @Result(property = "secondaryStayTime", column = "total_stay_time"),
//                    @Result(property = "onePageCount", column = "one_page_count"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
                    @Result(property = "sceneGroupId", column = "scene_group_id"),
            })
    @Select("select atable.scene_group_id as scene_group_id," +
            "atable.title as title," +
            "sum(btable.scene_newer_for_app) as scene_newer_for_app," +
            "sum(btable.scene_open_count) as scene_open_count," +
            "sum(btable.scene_page_count) as scene_page_count," +
            "sum(btable.scene_visitor_count) as scene_visitor_count," +
            "ROUND(sum(btable.total_stay_time)/sum(btable.scene_open_count)) as secondary_stay_time," +
            "sum(btable.one_page_count)/sum(btable.scene_open_count) as bounce_rate," +
            "btable.scene_id as scene_id " +
            "from ald_cms_scene as atable left join aldstat_scene_statistics as btable " +
            "on atable.sid = btable.scene_id " +
            "where btable.app_key = #{appKey}" +
            " and btable.day in ${dateSql} group by atable.sid order by scene_visitor_count desc")
    List<SceneEntity> querySceneDayDataList(Scene s);



}
