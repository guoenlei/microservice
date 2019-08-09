package com.ald.bigdata.modules.eventParam.controller;


import com.ald.bigdata.common.base.BaseController;
import com.ald.bigdata.modules.eventParam.entity.AldEventEntity;
import com.ald.bigdata.modules.eventParam.service.AldEventParamService;
import com.ald.bigdata.modules.eventParam.util.ParamUtils;
import com.ald.bigdata.modules.eventParam.vo.AldEventParamVo;
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
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value="event/param")
public class AldEventParamController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AldEventParamController.class);

    @Autowired
    private AldEventParamService aldEventParamService;


    /**
     * 事件处理 -- 参数列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String queryEventParamDataList(String date, String currentPage, String total, String order, String prop,
                                          String app_key, String event_key, String ev_paras_name) {

        AldEventParamVo vo = new AldEventParamVo(date, prop, order, app_key, event_key, ev_paras_name);
        vo.setCurrentPage(currentPage);
        vo.setTotal(total);
//        vo.setType(EventParamConstants.EVENT_PARAM_LIST); //列表
        vo.setType("1"); //列表 对ev_paras_name分组
        Map<String, String> checkMap = ParamUtils.check(vo);
        if(null != checkMap && null != checkMap.get("code")) {
            return returnJson(checkMap);
        }

        AldEventParamVo v1 = ParamUtils.format(vo);
        List<AldEventEntity> aldEventEntityList = aldEventParamService.queryEventListDataBy(v1);

        return resultJson2(aldEventEntityList, new Object[]{date});
    }


    /**
     * 参数明细列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/detail-list", method = RequestMethod.POST)
    public String queryEventParamDetailsList(String date, String currentPage, String total, String order, String prop,
                                             String app_key, String event_key, String ev_paras_name) {

        AldEventParamVo vo = new AldEventParamVo(date, prop, order, app_key, event_key, ev_paras_name);
        vo.setCurrentPage(currentPage);
        vo.setTotal(total);
//        vo.setType(EventParamConstants.EVENT_PARAM_DETAIL); //明细
        vo.setType("2"); //明细 对ev_paras_value分组

        Map<String, String> checkMap = ParamUtils.check(vo);
        if(null != checkMap && null != checkMap.get("code")) {
            return returnJson(checkMap);
        }

        AldEventParamVo v1 = ParamUtils.format(vo);
        Map<String, Object> resultMap = aldEventParamService.queryPageDataList(v1);
//        List<AldEventParamEntity> aldEventParamEntityList = aldEventParamService.queryPageDataList(v1);
//        List<AldEventParamEntity> aldEventParamEntityList = null;

//        return returnJson(aldEventParamEntityList, new Object[]{date});
        return resultJson2(resultMap, new Object[]{date});
    }

}
