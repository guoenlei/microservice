package com.aldwx.app.modules.event.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.modules.event.bean.Event;
import com.aldwx.app.modules.event.entity.EventEntity;
import com.aldwx.app.modules.event.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 事件处理
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldstat/app/event")
public class EventController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;


    /**
     * 行为 - 事件分析 - 来源筛选
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/source", method = RequestMethod.POST)
    public String querySourceList(String app_key, String date, String app_type) {
        List<EventEntity> eventList = this.eventService.querySourceList(new Event(app_key, date, app_type));
        return resultJosn4(eventList);
    }


//    /**
//     * 行为 - 事件分析 - 列表
//     * @param app_key
//     * @param date
//     * @param app_type
//     * @param source
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value="/particular", method = RequestMethod.POST)
//    public String queryEventDataList(String app_key, String date, String app_type, String source) {
//        Event event = new Event(app_key, date, app_type, source);
//        List<EventEntity> eventEntityList = null;
//        try {
//            eventEntityList = eventService.queryEventDataList(event);
//        } catch (Exception e) {
//            LOG.error("处理异常：{}", e);
//            return jsonError();
//        }
//        return resultJosn4(eventEntityList);
//    }




    /**
     * 行为 - 事件分析 - 列表
     * @param app_key
     * @param date
     * @param app_type
     * @param source
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/particular", method = RequestMethod.POST)
    public String queryEventDataList(String app_key, String date, String app_type, String source, String currentPage) {
        Event event = new Event(app_key, date, app_type, source,Integer.parseInt(currentPage));
        Map<String,Object> eventEntityList = null;
        try {
            eventEntityList = eventService.queryEventDataList(event);
        } catch (Exception e) {
            LOG.error("处理异常：{}", e);
            return jsonError();
        }
        return resultJosn10(eventEntityList);
    }


}
