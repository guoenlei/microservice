package com.ald.bigdata.modules.gameEvent.controller;

import com.ald.bigdata.common.base.BaseController;
import com.ald.bigdata.modules.gameEvent.entity.AldGameEventParamEntity;
import com.ald.bigdata.modules.gameEvent.service.AldGameEventParamService;
import com.ald.bigdata.modules.gameEvent.util.GameEventParamUtils;
import com.ald.bigdata.modules.gameEvent.vo.AldGameEventParamVo;
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
 * 小游戏事件参数
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldwx/game/event")
public class AldGameEventParamController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AldGameEventParamController.class);

    @Autowired
    private AldGameEventParamService aldGameEventParamService;


        /**
         * 事件处理 -- 参数列表
         * @return
         */
        @ResponseBody
        @RequestMapping(value = "/param-list", method = RequestMethod.POST)
        public String queryEventParamDataList(String date, String currentPage, String total, String order, String prop,
                                              String app_key, String event_key, String ev_paras_name) {

            AldGameEventParamVo vo = new AldGameEventParamVo(date, prop, order, app_key, event_key, ev_paras_name);
            Map<String, String> checkMap = GameEventParamUtils.paramCheck(vo);
            if(null != checkMap && checkMap.size() > 0) {
                return returnJson(checkMap);
            }
            List<Map<String, Object>> resultMap = null;
            try {
            AldGameEventParamVo v1 = GameEventParamUtils.requestParamFormat(vo);
            List<AldGameEventParamEntity> aldGameEventEntityList = aldGameEventParamService.queryEventParamListDataBy(v1);
            resultMap = GameEventParamUtils.responseParamListFormat(aldGameEventEntityList);
            } catch (Exception e) {
                LOG.error("程序处理异常: {}", e);
                return jsonError();
            }

            return resultJson2(resultMap, new Object[]{date});
        }


        /**
         * 参数明细列表
         * @return
         */
        @ResponseBody
        @RequestMapping(value="/param-detail-list", method = RequestMethod.POST)
        public String queryEventParamDetailsList(String date, String currentPage, String total, String order, String prop,
                                                 String app_key, String event_key, String ev_paras_name, String isDownload) {

            AldGameEventParamVo vo = new AldGameEventParamVo(date, prop, order, app_key, event_key, ev_paras_name);
            vo.setCurrentPage(currentPage);
            vo.setTotal(total);
            vo.setIsDownload(isDownload);
            Map<String, String> checkMap = GameEventParamUtils.paramCheck(vo);
            if(null != checkMap && checkMap.size() > 0) {
                return returnJson(checkMap);
            }
            List<Map<String, Object>> resultMap = null;
            long count = 0;
            try {
                AldGameEventParamVo v1 = GameEventParamUtils.requestParamFormat(vo);
                Map<String, Object> entityMap = aldGameEventParamService.queryPageDataList(v1);
                if(null != entityMap && entityMap.size() > 0) {
                    count = (long)entityMap.get("count");
                    resultMap = GameEventParamUtils.responseParamDetailFormat(entityMap);
                }
            } catch (Exception e) {
                LOG.error("程序处理异常: {}", e);
                return jsonError();
            }

            return resultJson2(resultMap, new Object[]{date, count});
        }
}
