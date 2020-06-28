package com.aldwx.app.modules.event.service;

import com.aldwx.app.modules.event.bean.Event;
import com.aldwx.app.modules.event.entity.EventEntity;

import java.util.List;
import java.util.Map;

/**
 * 事件
 * @author
 * @description
 * @createTime
 **/
public interface EventService {


    /**
     * 行为 - 事件分析 - 来源筛选
     * @param event
     * @return
     */
    List<EventEntity> querySourceList(Event event);

    /**
     * 行为 - 事件分析 - 列表
     * @param event
     * @return
     */
    Map<String,Object> queryEventDataList(Event event);

}
