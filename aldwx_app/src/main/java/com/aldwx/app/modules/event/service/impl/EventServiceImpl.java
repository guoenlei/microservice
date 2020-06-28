package com.aldwx.app.modules.event.service.impl;

import com.aldwx.app.common.base.BaseMethod;
import com.aldwx.app.common.page.PageHandle;
import com.aldwx.app.common.util.StringUtil;
import com.aldwx.app.modules.event.bean.Event;
import com.aldwx.app.modules.event.dao.game.EventGameDao;
import com.aldwx.app.modules.event.dao.stat.EventStatDao;
import com.aldwx.app.modules.event.entity.EventEntity;
import com.aldwx.app.modules.event.service.EventService;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件处理
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class EventServiceImpl extends BaseMethod implements EventService {

    private static final Logger LOG = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventStatDao eventStatDao;
    @Autowired
    private EventGameDao eventGameDao;


    /**
     * 行为 - 行为分析 - 来源筛选
     * @param event
     * @return
     */
    @Override
    public List<EventEntity> querySourceList(Event event) {
        String appType = event.getAppType();
        List<EventEntity> eventEntityList = null;
        if(isStatApp(appType)) {
            eventEntityList = this.eventStatDao.querySourceList(event);
        } else {
            eventEntityList = this.eventGameDao.querySourceList(event);
        }
        return eventEntityList;
    }

    /**
     * 行为 - 事件分析 - 列表
     * @param event
     * @return
     */
    @Override
    public Map<String,Object> queryEventDataList(Event event) {
        String appType = event.getAppType();
        Map<String,Object> map=new HashMap<>();
        List<EventEntity> eventEntityList = null;
        List<Map<String,Object>> mapList = new ArrayList<>();
        PageHandle.startPage(event.getCurrentPage(), event.getPageSize());
        if(isStatApp(appType)) {
            eventEntityList = this.eventStatDao.queryEventDataList(event);
        } else {
            eventEntityList = this.eventGameDao.queryEventDataList(event);
        }
        if (eventEntityList!=null&&eventEntityList.size()>0){
            for (EventEntity eventEntity: eventEntityList) {
                Map<String,Object> mapEv=new HashMap<>();
                mapEv.put("ev_name",eventEntity.getEvName());
                mapEv.put("trigger_count",eventEntity.getTriggerCount());
                mapEv.put("trigger_user_count",eventEntity.getTriggerUserCount());
                mapEv.put("avg_trigger_count",StringUtil.formatPercentNum(eventEntity.getAvgTriggerCount()));
                mapList.add(mapEv);
            }
        }
        PageInfo<EventEntity> pageInfo = new PageInfo<>(eventEntityList);
        map.put("data",mapList);
        map.put("num", pageInfo.getTotal());
        return map;
    }





}
