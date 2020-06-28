package com.aldwx.app.modules.trend.dao.stat;


import com.aldwx.app.common.base.BaseDao;
import com.aldwx.app.modules.trend.bean.Trend;
import com.aldwx.app.modules.trend.entity.TrendEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 趋势
 */
@Mapper
public interface TrendStatDao extends BaseDao<TrendEntity, Trend> {


    /**
     * 概览 - 首页 今日概况
     * 按日期查询数据
     * 今日概览
     * @param t
     * @return
     */
    @Results(
            value = {
                    @Result(property = "day", column = "day"),
                    @Result(property = "newComerCount", column = "new_comer_count"),
                    @Result(property = "visitorCount", column = "visitor_count"),
                    @Result(property = "openCount", column = "open_count"),
                    @Result(property = "onePageCount", column = "one_page_count"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "totalPageCount", column = "total_page_count"),
                    @Result(property = "secondaryAvgStayTime", column = "secondary_avg_stay_time"),
                    @Result(property = "bounceRate", column = "bounce_rate"),

            })
    @Select("SELECT  app_key,sum(new_comer_count) new_comer_count, sum(visitor_count) visitor_count,sum(total_page_count) total_page_count, sum(open_count) open_count,sum(total_stay_time)/sum(open_count) secondary_avg_stay_time,sum(one_page_count)/sum(open_count) bounce_rate " +
            "FROM aldstat_trend_analysis " +
            "WHERE `day`=#{dateTime} " +
            "AND app_key=#{appKey} group by app_key")
    List<TrendEntity> queryDataByDate(Trend t);

//7天 30天
    @Results(
            value = {
                    @Result(property = "day", column = "day"),
                    @Result(property = "newComerCount", column = "new_comer_count"),
                    @Result(property = "visitorCount", column = "visitor_count"),
                    @Result(property = "openCount", column = "open_count"),
                    @Result(property = "onePageCount", column = "one_page_count"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "totalPageCount", column = "total_page_count"),
                    @Result(property = "secondaryAvgStayTime", column = "secondary_avg_stay_time"),
                    @Result(property = "bounceRate", column = "bounce_rate"),

            })
    @Select("SELECT  app_key,sum(new_comer_count) new_comer_count, sum(visitor_count) visitor_count,sum(total_page_count) total_page_count, sum(open_count) open_count,sum(total_stay_time)/sum(open_count) secondary_avg_stay_time,sum(one_page_count)/sum(open_count) bounce_rate " +
            "FROM aldstat_trend_analysis " +
            "WHERE `day` IN ${dateSql} " +
            "AND app_key=#{appKey} group by app_key")
    List<TrendEntity> queryDataByDateRange(Trend t);

    @Results(
            value = {
                    @Result(property = "day", column = "day"),
                    @Result(property = "newComerCount", column = "new_comer_count"),
                    @Result(property = "visitorCount", column = "visitor_count"),
                    @Result(property = "openCount", column = "open_count"),
                    @Result(property = "onePageCount", column = "one_page_count"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "totalPageCount", column = "total_page_count"),
                    @Result(property = "secondaryAvgStayTime", column = "secondary_avg_stay_time"),
                    @Result(property = "bounceRate", column = "bounce_rate"),

            })
    @Select("SELECT `day`, sum(new_comer_count) new_comer_count, sum(visitor_count) visitor_count, sum(open_count) open_count, sum(total_page_count) total_page_count,sum(total_stay_time)/sum(open_count) secondary_avg_stay_time,sum(one_page_count)/sum(open_count) bounce_rate " +
            "FROM aldstat_hourly_trend_analysis " +
            "WHERE `day` = #{dateTime} " +
            "AND app_key=#{appKey}"+
            " group by app_key")
    List<TrendEntity> queryDataByHour(Trend t);
    /**
     * 按日期查询数据 - 小时
     * 数据概览 - 今天，昨天 - 折线图
     * @param t
     * @return
     */
    @Results(
            value = {
                    @Result(property = "day", column = "day"),
                    @Result(property = "hour", column = "hour"),
                    @Result(property = "newComerCount", column = "new_comer_count"),
                    @Result(property = "visitorCount", column = "visitor_count"),
                    @Result(property = "openCount", column = "open_count"),
                    @Result(property = "totalPageCount", column = "total_page_count"),
                    @Result(property = "secondaryAvgStayTime", column = "secondary_avg_stay_time"),
                    @Result(property = "bounceRate", column = "bounce_rate"),
            })
    @Select("SELECT `day`, hour, new_comer_count, visitor_count, open_count, total_page_count ,secondary_avg_stay_time ,bounce_rate " +
            "FROM aldstat_hourly_trend_analysis " +
            "WHERE `day` = #{dateTime} " +
            "AND app_key=#{appKey} " +
            "GROUP BY hour " +
            "ORDER BY hour DESC ")
    List<TrendEntity> queryHourDataByDate(Trend t);



    /**
     * 按日期查询数据 - 天
     * 数据概览 - 7日，30日 - 折线图
     * @param t
     * @return
     */
    @Results(
            value = {
                    @Result(property = "day", column = "day"),
                    @Result(property = "newComerCount", column = "new_comer_count"),
                    @Result(property = "visitorCount", column = "visitor_count"),
                    @Result(property = "openCount", column = "open_count"),
                    @Result(property = "totalPageCount", column = "total_page_count"),
                    @Result(property = "secondaryAvgStayTime", column = "secondary_avg_stay_time"),
                    @Result(property = "bounceRate", column = "bounce_rate"),

            })
    @Select("SELECT `day`, new_comer_count, visitor_count, open_count, total_page_count,secondary_avg_stay_time,bounce_rate " +
            "FROM aldstat_trend_analysis " +
            "WHERE `day` IN ${dateSql} " +
            "AND app_key=#{appKey} " +
            "GROUP BY `day` " +
            "ORDER BY `day` DESC ")
    List<TrendEntity> queryDataByDates(Trend t);






}
