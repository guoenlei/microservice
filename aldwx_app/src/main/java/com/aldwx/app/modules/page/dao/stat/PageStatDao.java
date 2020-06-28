package com.aldwx.app.modules.page.dao.stat;

import com.aldwx.app.common.base.BaseDao;
import com.aldwx.app.modules.page.bean.Page;
import com.aldwx.app.modules.page.entity.PageEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 受访页
 */
@Mapper
public interface PageStatDao extends BaseDao {



    /**
     *
     * 概况 - 页面趋势TOP5 - 列表
     * @param p
     * @return
     */
    @Results(
            value = {
                    @Result(property = "day", column = "day"),
                    @Result(property = "pagePath", column = "page_path"),
                    @Result(property = "pageCount", column = "page_count"),
                    @Result(property = "visitorCount", column = "visitor_count"),
                    @Result(property = "downstreamCount", column = "downstream_count"),
                    @Result(property = "totalStayTime", column = "total_stay_time"),
                    @Result(property = "abortPageCount", column = "abort_page_count"),
                    @Result(property = "abortRatio", column = "abort_ratio"),
                    @Result(property = "shareCount", column = "share_count"),
                    @Result(property = "openCount", column = "open_count"),
                    @Result(property = "avgStayTime", column = "avg_stay_time"),
                    @Result(property = "totalTime", column = "total_time"),
            })
    @Select("SELECT `day`, page_path, page_count, visitor_count, downstream_count, " +
            "total_stay_time, abort_page_count, abort_ratio, share_count, " +
            "open_count, avg_stay_time, total_time " +
            "FROM aldstat_page_view " +
            "WHERE `day` IN ${dateSql} " +
            "AND app_key=#{appKey} " +
            "ORDER BY visitor_count DESC "
            )
    List<PageEntity> queryPageDataTopList(Page p);




    /**
     * 行为 - 页面分析 - 受访页来源
     * @param page
     * @return
     */
    @Select("")
    List<PageEntity> querySourceList(Page page);




    /**
     * 行为 - 页面分析 - 受访页列表
     * @param page
     * @return
     */
    @Results(
            value = {
                    @Result(property = "pagePath", column = "page_path"),
                    @Result(property = "pageCount", column = "page_count"),
                    @Result(property = "visitorCount", column = "visitor_count"),
                    @Result(property = "abortPageCount", column = "abort_page_count"),
                    @Result(property = "abortRatio", column = "abort_ratio"),
            })
    @Select("select page_path, page_count, visitor_count, " +
            " abort_page_count, abort_ratio " +
            "from ${tableName} " +
            "where 1=1 " +
            "and day=#{dateTime} " +
            "and app_key=#{appKey} and  page_path !='null'")
    List<PageEntity> queryPageList(Page page);



    /**
     * 行为 - 页面分析 - 入口页列表
     * @param page
     * @return
     */
    @Select("")
    List<PageEntity> queryEntrancePageList(Page page);



}
