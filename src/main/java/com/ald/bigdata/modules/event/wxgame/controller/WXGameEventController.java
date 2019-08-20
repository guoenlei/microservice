package com.ald.bigdata.modules.event.wxgame.controller;

import com.ald.bigdata.common.base.BaseController;
import com.ald.bigdata.common.event.util.ParamUtils;
import com.ald.bigdata.common.event.util.TaskUtils;
import com.ald.bigdata.common.event.vo.EventVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 小游戏事件
 */
@Controller
@RequestMapping(value="wx/game/event")
@EnableAutoConfiguration
public class WXGameEventController extends BaseController {
    private static Logger LOG = LoggerFactory.getLogger(WXGameEventController.class);

    /**
     * 事件处理 -- 参数列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/param-list", method = RequestMethod.POST)
    public String queryEventParamDataList(String date, String currentPage, String total,
                                          String order, String prop, String app_key, String event_key, String ev_paras_name,
                                          String isDownload, String platform) {

        EventVo e = new EventVo(date, prop, order, app_key, event_key, ev_paras_name, isDownload, platform);
        e.setCurrentPage(currentPage);
        e.setTotal(total);
        e.setType("2"); //列表
        Map<String, Object> checkMap = ParamUtils.check(e);
        if(null != checkMap.get("code")) {

            checkMap = formatData(checkMap);

            return returnJson(checkMap);
        }

        EventVo vo = ParamUtils.format(e);
        Map<String, Object>  resultMap = TaskUtils.queryEventParamDataList(vo);

        resultMap = formatData(resultMap);

        // 更新map中的date为日期类型
        resultMap.put("date", formatReturnDate(resultMap.get("date").toString()));

        return returnJson(resultMap);
    }


    /**
     * 参数明细列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/param-detail-list", method = RequestMethod.POST)
    public String queryEventParamDetailsList(String date, String currentPage, String total,
                                             String order, String prop, String app_key, String event_key, String ev_paras_name,
                                             String isDownload, String platform) {

        EventVo e = new EventVo(date, prop, order, app_key, event_key, ev_paras_name, isDownload, platform);
        e.setCurrentPage(currentPage);
        e.setTotal(total);
        e.setType("1"); //明细

        Map<String, Object> checkMap = ParamUtils.check(e);
        if(null != checkMap.get("code")) {

            checkMap = formatData(checkMap);

            return returnJson(checkMap);
        }

        EventVo vo = ParamUtils.format(e);
        Map<String, Object>  resultMap = TaskUtils.queryEventParamDetailsList(vo);

        resultMap = formatData(resultMap);

        // 更新map中的date为日期类型
        resultMap.put("date", formatReturnDate(resultMap.get("date").toString()));

        return returnJson(resultMap);
    }


    /**
     * 有数据或没数据
     * @param map
     * @return
     */
    private Map<String, Object> formatData(Map<String, Object> map) {
        if(map.get("code").equals("200")) {
            map.put("code", 200);
        } else {
            map.put("code", 202);
        }
        return map;
    }

}
