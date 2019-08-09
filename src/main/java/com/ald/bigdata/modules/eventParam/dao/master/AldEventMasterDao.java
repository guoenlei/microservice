package com.ald.bigdata.modules.eventParam.dao.master;


import com.ald.bigdata.modules.eventParam.entity.AldEventEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface AldEventMasterDao {


    /**
     * 获取事件列表
     * @param appKey
     * @param eventKey
     * @return
     */
    @Results(
            value = {
                    @Result(property = "appKey", column = "app_key"),
                    @Result(property = "eventKey", column = "event_key"),
                    @Result(property = "evId", column = "ev_id"),
                    @Result(property = "evName", column = "ev_name"),
            })
    @Select("select app_key, event_key, ev_name, ev_id from ald_event where ev_status=1 " +
            "and app_key=#{app_key} and event_key=#{event_key}")
    List<AldEventEntity> queryEventListDataBy(@Param("app_key") String appKey, @Param("event_key") String eventKey);


}
