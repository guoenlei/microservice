package com.aldwx.app.modules.smart.dao.game;


import com.aldwx.app.modules.smart.bean.Smart;
import com.aldwx.app.modules.smart.entity.SmartEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 智能外链
 */
@Mapper
public interface SmartGameDao {

    /**
     * 概况 - 智能外链跟踪top5 - 列表
     * @param smart
     * @return
     */
    @Results(
            value = {
                    @Result(property = "linkType", column = "link_type_group"),
                    @Result(property = "linkName", column = "link_name_zhishu"),
                    @Result(property = "newComerCount", column = "new_comer_count"),
                    @Result(property = "authNewComerCount", column = "auth_new_comer_count"),
                    @Result(property = "visitorCount", column = "visitor_count"),
                    @Result(property = "openCount", column = "open_count"),
                    @Result(property = "totalPageCount", column = "total_page_count"),
                    @Result(property = "avgStayTime", column = "avg_stay_time"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
                    @Result(property = "onePageCount", column = "one_page_count"),
            })
    @Select("select if(c.link_name_tongji != '', c.link_name_tongji, b.link_name_zhishu) link_name_zhishu,\n" +
            "  a.app_id, a.link_type_group, a.os_type, \n" +
            "  sum(a.new_comer_count) new_comer_count, \n" +
            "  sum(a.auth_new_comer_count) auth_new_comer_count, \n" +
            "  sum(a.visitor_count) visitor_count, \n" +
            "  sum(a.total_page_count) total_page_count, \n" +
            "  sum(a.open_count) open_count, \n" +
            "  sum(a.total_stay_time) totaltime, \n" +
            "  sum(a.one_page_count) one_page_count ,\n" +
            "  sum(a.one_page_count)/sum(a.open_count) bounce_rate\n" +
            "  from aldstat_smartLink_day_analysis  a\n" +
            "  left join ald_smartLink_dict  b\n" +
            "  on a.app_id = b.app_id \n" +
            "  left join ald_smartLink_dict_name  c \n" +
            "  on a.app_id = c.app_id and a.app_key = c.app_key \n" +
            "  where (day IN ${dateSql} and a.app_key =#{appKey} and link_type = '') group by a.app_id order by a.visitor_count desc")
    List<SmartEntity> querySmartTrendTopList(Smart smart);




    /**
     * 来源 - 智能外链跟踪 - 概览
     * @param smart
     * @return
     */
    @Results(
            value = {
                    @Result(property = "day", column = "day"),
                    @Result(property = "newComerCount", column = "new_comer_count"),
                    @Result(property = "authNewComerCount", column = "auth_new_comer_count"),
                    @Result(property = "visitorCount", column = "visitor_count"),
                    @Result(property = "openCount", column = "open_count"),
                    @Result(property = "totalPageCount", column = "total_page_count"),
                    @Result(property = "avgStayTime", column = "avg_stay_time"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
                    @Result(property = "onePageCount", column = "one_page_count"),
            })
    @Select("SELECT \n" +
            "SUM(new_comer_count) as new_comer_count, \n" +
            "SUM(auth_new_comer_count) as auth_new_comer_count, \n" +
            "SUM(visitor_count) as visitor_count, \n" +
            "SUM(open_count) as open_count, \n" +
            "SUM(total_page_count) as total_page_count, \n" +
            "SUM(avg_stay_time) as avg_stay_time, \n" +
            "SUM(total_stay_time) as total_stay_time, \n" +
            "SUM(one_page_count)/SUM(open_count) as bounce_rate, \n" +
            "SUM(one_page_count) as one_page_count\n" +
            "FROM ${tableName}\n" +
            "WHERE day IN ${dateSql}\n" +
            "AND app_key = #{appKey}")
    List<SmartEntity> querySmartDataView(Smart smart);


    /**
     * 获取top3的appid和linkName
     * @param smart
     * @return
     */
    @Results(
            value = {
                    @Result(property = "appId", column = "app_id"),
                    @Result(property = "linkName", column = "link_name_zhishu"),
            })
    @Select("SELECT a.app_id, b.link_name_zhishu, sum(a.${type}) as ${type}\n" +
            "FROM ${tableName} a, ald_smartLink_dict b\n" +
            "WHERE 1=1 " +
            "AND a.day IN ${dateSql}\n" +
            "AND a.app_key=#{appKey}\n" +
            "AND a.link_type_group != ''\n" +
            "AND a.app_id=b.app_id\n" +
            "GROUP BY a.app_id, b.link_name_zhishu\n" +
            "ORDER BY ${type} DESC LIMIT ${limitNum}")
    List<SmartEntity> queryTopAppIdList(Smart smart);


    /**
     * 来源 - 智能外链来源渠道 - 小时 - 折线图
     * @param smart
     * @return
     */
    @Results(
            value = {
                    @Result(property = "hour", column = "hour"),
                    @Result(property = "linkName", column = "link_name_zhishu"),
                    @Result(property = "newComerCount", column = "new_comer_count"),
                    @Result(property = "authNewComerCount", column = "auth_new_comer_count"),
                    @Result(property = "visitorCount", column = "visitor_count"),
                    @Result(property = "openCount", column = "open_count"),
                    @Result(property = "totalPageCount", column = "total_page_count"),
                    @Result(property = "avgStayTime", column = "avg_stay_time"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
                    @Result(property = "onePageCount", column = "one_page_count"),
            })
    @Select("select b.link_name_zhishu,a.app_id app_id,a.hour hour," +
            "a.new_comer_count,a.visitor_count,a.open_count,a.total_page_count " +
            "from aldstat_smartLink_hour_analysis a join \n" +
            "(select aa.app_id,sum(aa.new_comer_count) cc,min(aa.day) day," +
            "min(aa.app_key) app_key,ald_smartLink_dict.link_name_zhishu link_name_zhishu " +
            "from aldstat_smartLink_hour_analysis aa join ald_smartLink_dict " +
            "on aa.app_id=ald_smartLink_dict.app_id \n" +
            "where aa.app_key=#{appKey} " +
            "and aa.day IN ${dateSql} and link_type_group!='' group by aa.app_id order by cc desc limit 3) b \n" +
            "on a.app_key=b.app_key and a.app_id=b.app_id and a.day=b.day\n" +
            "where a.link_type_group!='' order by app_id,hour")
    List<SmartEntity> querySmartDataHourChart(Smart smart);



    /**
     * 来源 - 智能外链来源渠道 - 天 - 折线图
     * @param smart
     * @return
     */
    @Results(
            value = {
                    @Result(property = "day", column = "day"),
                    @Result(property = "linkName", column = "link_name_zhishu"),
                    @Result(property = "newComerCount", column = "new_comer_count"),
                    @Result(property = "authNewComerCount", column = "auth_new_comer_count"),
                    @Result(property = "visitorCount", column = "visitor_count"),
                    @Result(property = "openCount", column = "open_count"),
                    @Result(property = "totalPageCount", column = "total_page_count"),
                    @Result(property = "avgStayTime", column = "avg_stay_time"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
                    @Result(property = "onePageCount", column = "one_page_count"),
            })
    @Select("select b.link_name_zhishu,a.day,sum(a.new_comer_count) new_comer_count,sum(a.visitor_count) visitor_count,sum(a.open_count) open_count,sum(a.total_page_count) total_page_count from aldstat_smartLink_day_analysis a join \n" +
            "(select aa.app_id,sum(aa.new_comer_count) cc,min(aa.app_key) app_key,ald_smartLink_dict.link_name_zhishu link_name_zhishu from aldstat_smartLink_day_analysis aa join ald_smartLink_dict on aa.app_id=ald_smartLink_dict.app_id \n" +
            " where aa.app_key=#{appKey} and aa.day IN ${dateSql} and link_type_group!='' group by aa.app_id order by cc desc limit 3) b\n" +
            "  on a.app_id=b.app_id where a.link_type_group!='' and a.day IN ${dateSql} group by b.link_name_zhishu,a.day\n" +
            "  order by b.link_name_zhishu,a.day ")
    List<SmartEntity> querySmartDataDayChart(Smart smart);



    /**
     * 来源 - 智能外链来源渠道明细 - 列表
     * @return
     */
    @Results(
            value = {
                    @Result(property = "linkName", column = "link_name_zhishu"),
                    @Result(property = "linkTypeGroup", column = "link_type_group"),
                    @Result(property = "newComerCount", column = "new_comer_count"),
                    @Result(property = "authNewComerCount", column = "auth_new_comer_count"),
                    @Result(property = "visitorCount", column = "visitor_count"),
                    @Result(property = "openCount", column = "open_count"),
                    @Result(property = "totalPageCount", column = "total_page_count"),
                    @Result(property = "avgStayTime", column = "avg_stay_time"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
                    @Result(property = "onePageCount", column = "one_page_count"),
            })
    @Select("select case when b.link_name_zhishu is null then '未知' else b.link_name_zhishu end as link_name_zhishu,a.link_type_group,a.app_id,sum(a.new_comer_count) new_comer_count," +
            "sum(a.auth_new_comer_count) auth_new_comer_count," +
            "sum(a.visitor_count) visitor_count," +
            "sum(a.open_count) open_count,sum(a.total_page_count) total_page_count" +
            " from aldstat_smartLink_day_analysis a " +
            "left join ald_smartLink_dict b " +
            "on a.app_id=b.app_id where  a.app_key=#{appKey} " +
            "and link_type_group !='' and a.day IN ${dateSql} " +
            "group by b.link_name_zhishu,a.app_id,a.link_type_group order by visitor_count desc  ")
    List<SmartEntity> querySmartDataList(Smart smart);









}
