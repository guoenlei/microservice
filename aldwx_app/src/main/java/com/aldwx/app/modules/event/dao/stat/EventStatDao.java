package com.aldwx.app.modules.event.dao.stat;

import com.aldwx.app.common.base.BaseDao;
import com.aldwx.app.modules.event.bean.Event;
import com.aldwx.app.modules.event.entity.EventEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 事件分析
 */
@Mapper
public interface EventStatDao extends BaseDao {


    /**
     * 行为 - 事件分析 - 来源
     * @param event
     * @return
     */
    @Results(
            value = {
                    @Result(property = "evId", column = "ev_id"),
                    @Result(property = "eventKey", column = "event_key"),
                    @Result(property = "evName", column = "ev_name"),
            })
    @Select("SELECT ev_id, event_key, ev_name, ev_status " +
            "FROM ald_event " +
            "WHERE app_key=#{appKey} " +
            "AND ev_status = 1 LIMIT 10" )
    List<EventEntity> querySourceList(Event event);

//
//    /**
//     * 行为 - 事件分析 - 列表
//     * @param event
//     * @return
//     */
//    @Results(
//            value = {
//                    @Result(property = "evName", column = "ev_name"),
//                    @Result(property = "triggerUserCount", column = "trigger_user_count"),
//                    @Result(property = "triggerCount", column = "trigger_count"),
//                    @Result(property = "avgTriggerCount", column = "avg_trigger_count"),
//            })
//    @Select("select b.ev_name, \n" +
//            "SUM(a.trigger_user_count) as trigger_user_count, \n" +
//            "SUM(a.trigger_count) as trigger_count, \n" +
//            "SUM(a.avg_trigger_count) as avg_trigger_count \n" +
//            "FROM aldstat_daily_event a, ald_event b\n" +
//            "WHERE 1=1\n" +
//            "AND a.day IN ${dateSql}\n" +
//            "AND a.app_key=#{appKey}\n" +
//            "AND a.app_key=b.app_key\n" +
//            "AND a.ev_id=b.ev_id\n" +
//            "AND a.ev_id=#{type}\n" +
//            "GROUP BY b.ev_name limit 10")
//    List<EventEntity> queryEventDataList(Event event);





    /**
     * 行为 - 事件分析 - 列表
     * @param event
     * @return
     */
    @Results(
            value = {
                    @Result(property = "evName", column = "ev_name"),
                    @Result(property = "triggerUserCount", column = "trigger_user_count"),
                    @Result(property = "triggerCount", column = "trigger_count"),
                    @Result(property = "avgTriggerCount", column = "avg_trigger_count"),
            })
    @Select("select b.ev_name, \n" +
            "sum(a.trigger_user_count) trigger_user_count , \n" +
            "sum(a.trigger_count) trigger_count , \n" +
            "sum(a.trigger_count)/sum(a.trigger_user_count) avg_trigger_count \n" +
            "FROM ${tableName} a inner join (select * from ald_event where app_key =#{appKey} and ev_status=1 and ev_id !='ald_error_message'and ev_id !='ald_share_chain' and ev_id !='ald_share_click' and ev_id !='ald_share_status' and ev_id !='ald_share_user') b\n" +
            " on a.event_key=b.event_key\n "+
            "WHERE 1=1\n" +
            "AND a.day=#{dateTime}\n" +
            "AND a.app_key=#{appKey}\n" +
            "AND a.source_key='default'\n" +
            "AND a.source_value='default'\n" +
            "group by b.ev_name"
    )
    List<EventEntity> queryEventDataList(Event event);
}
