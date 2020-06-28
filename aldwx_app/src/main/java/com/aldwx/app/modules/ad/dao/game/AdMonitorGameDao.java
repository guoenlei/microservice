package com.aldwx.app.modules.ad.dao.game;

import com.aldwx.app.common.base.BaseDao;
import com.aldwx.app.modules.ad.bean.Ad;
import com.aldwx.app.modules.ad.entity.AdEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdMonitorGameDao extends BaseDao {






    /**
     * 来源 - 广告监测 - 概览
     * aldstat_link_summary
     * aldstat_daily_link_monitor
     * ald_link_trace
     * ald_media
     * @param ad
     * @return
     */
    @Results(
            value = {
                    @Result(property = "linkVisitorCount", column = "link_visitor_count"),
                    @Result(property = "linkAuthuserCount", column = "link_authuser_count"),
                    @Result(property = "linkOpenCount", column = "link_open_count"),
                    @Result(property = "linkPageCount", column = "link_page_count"),
                    @Result(property = "linkClickCount", column = "link_click_count"),
                    @Result(property = "linkNewerForApp", column = "link_newer_for_app"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "secondaryStayTime", column = "secondary_stay_time"),
                    @Result(property = "onePageCount", column = "one_page_count"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
            })
    @Select("select                          \n" +
            "SUM(a.link_visitor_count) as link_visitor_count, \n" +
            "SUM(a.link_authuser_count) as link_authuser_count,\n" +
            "SUM(a.link_open_count) as link_open_count,    \n" +
            "SUM(a.link_page_count) as link_page_count,    \n" +
            "SUM(a.link_click_count) as link_click_count,   \n" +
            "SUM(a.link_newer_for_app) as link_newer_for_app, \n" +
            "SUM(a.total_stay_time) as total_stay_time,    \n" +
            "SUM(a.secondary_stay_time) as secondary_stay_time,\n" +
            "SUM(a.one_page_count) as one_page_count,     \n" +
            "SUM(a.one_page_count)/SUM(a.link_open_count) as bounce_rate     \n" +
            "FROM ${tableName} a\n" +
            "WHERE 1=1\n" +
            "AND day IN ${dateSql}\n" +
            "AND app_key=#{appKey}\n"
    )
    List<AdEntity> queryAdView(Ad ad);


    /**
     * 获取广告监测Top3场景值
     * @param ad
     * @return
     */
    @Results(
            value = {
                    @Result(property = "linkKey", column = "link_key"),
            })
    @Select("select a.app_key, a.link_key,a.link_name,${type} from ald_link_trace a \n" +
            "left JOIN (select app_key, link_key, SUM(${type}) ${type} \n" +
            "from aldstat_daily_link where day IN ${dateSql} \n" +
            "            and app_key=#{appKey}\n" +
            "GROUP BY link_key,app_key ORDER BY ${type} DESC) b  on a.app_key=b.app_key and a.link_key = b.link_key\n" +
            " where a.app_key =#{appKey} limit 3")
    List<AdEntity> queryAdTopList(Ad ad);


    /**
     * 来源 - 广告监测数据 小时 - 折线图
     * @param ad
     * @return
     */
    @Results(
            value = {
                    @Result(property = "hour", column = "hour"),
                    @Result(property = "linkName", column = "link_name"),
                    @Result(property = "linkVisitorCount", column = "link_visitor_count"),
                    @Result(property = "linkAuthuserCount", column = "link_authuser_count"),
                    @Result(property = "linkOpenCount", column = "link_open_count"),
                    @Result(property = "linkPageCount", column = "link_page_count"),
                    @Result(property = "linkClickCount", column = "link_click_count"),
                    @Result(property = "linkNewerForApp", column = "link_newer_for_app"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "secondaryStayTime", column = "secondary_stay_time"),
                    @Result(property = "onePageCount", column = "one_page_count"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
            })
    @Select("SELECT \n" +
            "                        b.hour, a.link_name,\n" +
            "                        b.link_visitor_count,\n" +
            "                        b.link_authuser_count,\n" +
            "                        b.link_open_count,  \n" +
            "                        b.link_page_count,   \n" +
            "                        b.link_click_count, \n" +
            "                        b.link_newer_for_app,\n" +
            "                        b.total_stay_time,   \n" +
            "                        b.secondary_stay_time,\n" +
            "                        b.one_page_count,   \n" +
            "                        b.bounce_rate     \n" +
            "                        FROM ald_link_trace a LEFT JOIN (SELECT * from aldstat_hourly_link      WHERE \n" +
            "                         day IN ${dateSql} \n" +
            "                        AND app_key=#{appKey}\n" +
            "                        GROUP BY hour, link_key ) b  \n" +
            "                       ON   a.app_key=b.app_key and a.link_key=b.link_key\n" +
            "                       where a.app_key=#{appKey}\n" +
            "                       AND  a.link_key=#{linkKey}\n" +
            "                        ORDER BY  ${type} desc\n" )
    List<AdEntity> queryAdHourChart(Ad ad);




    /**
     * 来源 - 广告监测数据 天 - 折线图
     * @param ad
     * @return
     */
    @Results(
            value = {
                    @Result(property = "day", column = "day"),
                    @Result(property = "linkName", column = "link_name"),
                    @Result(property = "linkVisitorCount", column = "link_visitor_count"),
                    @Result(property = "linkAuthuserCount", column = "link_authuser_count"),
                    @Result(property = "linkOpenCount", column = "link_open_count"),
                    @Result(property = "linkPageCount", column = "link_page_count"),
                    @Result(property = "linkClickCount", column = "link_click_count"),
                    @Result(property = "linkNewerForApp", column = "link_newer_for_app"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "secondaryStayTime", column = "secondary_stay_time"),
                    @Result(property = "onePageCount", column = "one_page_count"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
            })
    @Select("SELECT \n" +
            "                        b.day, a.link_name,\n" +
            "                        b.link_visitor_count,\n" +
            "                        b.link_authuser_count,\n" +
            "                        b.link_open_count,  \n" +
            "                        b.link_page_count,   \n" +
            "                        b.link_click_count, \n" +
            "                        b.link_newer_for_app,\n" +
            "                        b.total_stay_time,   \n" +
            "                        b.secondary_stay_time,\n" +
            "                        b.one_page_count,   \n" +
            "                        b.bounce_rate     \n" +
            "                        FROM ald_link_trace a LEFT JOIN (SELECT * from aldstat_daily_link      WHERE \n" +
            "                         day IN ${dateSql} \n" +
            "                        AND app_key=#{appKey}\n" +
            "                        GROUP BY day, link_key ) b  \n" +
            "                       ON   a.app_key=b.app_key and a.link_key=b.link_key\n" +
            "                       where a.app_key=#{appKey}\n" +
            "                       AND  a.link_key=#{linkKey}\n" +
            "                        ORDER BY  ${type} desc\n" )
    List<AdEntity> queryAdDayChart(Ad ad);




    /**
     * 来源 - 广告监测数据 - 列表
     * @param ad
     * @return
     */
    @Results(
            value = {
                    @Result(property = "linkName", column = "link_name"),
                    @Result(property = "linkVisitorCount", column = "link_visitor_count"),
                    @Result(property = "linkAuthuserCount", column = "link_authuser_count"),
                    @Result(property = "linkOpenCount", column = "link_open_count"),
                    @Result(property = "linkPageCount", column = "link_page_count"),
                    @Result(property = "linkClickCount", column = "link_click_count"),
                    @Result(property = "linkNewerForApp", column = "link_newer_for_app"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "secondaryStayTime", column = "secondary_stay_time"),
                    @Result(property = "onePageCount", column = "one_page_count"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
            })
    @Select("SELECT\n" +
            "            a.link_name,b.*                        \n" +
            "            FROM ald_link_trace   a LEFT JOIN ( SELECT link_key,app_key,link_visitor_count, \n" +
            "            link_authuser_count,\n" +
            "            link_open_count,   \n" +
            "            link_page_count,    \n" +
            "            link_click_count,   \n" +
            "            link_newer_for_app, \n" +
            "            total_stay_time,    \n" +
            "            total_stay_time/link_open_count as secondary_stay_time,\n" +
            "            one_page_count,     \n" +
            "            one_page_count/link_open_count bounce_rate from  aldstat_daily_link\n" +
            "            where app_key=#{appKey} and day=#{dateTime} )  b  \n" +
            "            on a.link_key=b.link_key and a.app_key=b.app_key\n" +
            "            where a.app_key=#{appKey}")
    List<AdEntity> queryAdList(Ad ad);





}
