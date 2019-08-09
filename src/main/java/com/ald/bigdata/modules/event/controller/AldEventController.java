package com.ald.bigdata.modules.event.controller;

import com.ald.bigdata.common.base.BaseController;
import com.ald.bigdata.modules.event.util.ParamUtils;
import com.ald.bigdata.modules.event.util.TaskUtils;
import com.ald.bigdata.modules.event.vo.EventVo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 事件
 */
@Controller
@RequestMapping(value="event")
@EnableAutoConfiguration
public class AldEventController extends BaseController {

//
//    @Autowired
//    private AldEventService aldEventService;



    /**
     * 事件处理 -- 参数列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/param-list", method = RequestMethod.POST)
    public String queryEventParamDataList(String date, String typeId, String keyword, String currentPage, String total,
                                          String order, String prop, String app_key, String event_key, String ev_paras_name,
                                          String isDownload) {

        EventVo e = new EventVo(date, typeId, keyword, prop, order, app_key, event_key, ev_paras_name, isDownload);
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

        return returnJson(resultMap);
    }


    /**
     * 参数明细列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/param-detail-list", method = RequestMethod.POST)
    public String queryEventParamDetailsList(String date, String typeId, String keyword, String currentPage, String total,
                                             String order, String prop, String app_key, String event_key, String ev_paras_name,
                                             String isDownload) {

        EventVo e = new EventVo(date, typeId, keyword, prop, order, app_key, event_key, ev_paras_name, isDownload);
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

        return returnJson(resultMap);
    }


    private Map<String, Object> formatData(Map<String, Object> map) {
        if(map.get("code").equals("200")) {
            map.put("code", 200);
        } else {
            map.put("code", 202);
        }
        return map;
    }

}
