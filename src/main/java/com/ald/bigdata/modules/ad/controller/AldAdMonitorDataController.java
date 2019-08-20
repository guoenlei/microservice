package com.ald.bigdata.modules.ad.controller;

import com.ald.bigdata.common.base.BaseController;
import com.ald.bigdata.common.base.Messager;
import com.ald.bigdata.modules.ad.assist.AdAssist;
import com.ald.bigdata.modules.ad.assist.AdAssist2;
import com.ald.bigdata.modules.ad.service.AldAdAnomalyDataService;
import com.ald.bigdata.modules.ad.service.AldAdDataService;
import com.ald.bigdata.modules.ad.vo.AldAdAnomalyDataVo;
import com.ald.bigdata.modules.ad.vo.AldAdDataVo;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

//import com.aldwx.bigdata.modules.ad.service.AldAdDataService;

/**
 * 广告监测
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value="/wx/mini/adMonitor")
public class AldAdMonitorDataController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AldAdMonitorDataController.class);

    @Autowired
    private AldAdAnomalyDataService aldAdAnomalyDataService;

    @Autowired
    private AldAdDataService aldDataService;


    /**
     * 反作弊处理报告列表
     * @param app_key
     * @param date
     * @param module
     *          1（防护总览）,2（异常时差保护）,3（异常ip保护）, 4 异常授权
     * @param currentPage
     * @param total
     * @param prop
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cheatDataList", method = RequestMethod.POST)
    public String cheatDataList(@NotEmpty String app_key, String date, String module, String currentPage,
                                String total, String prop, String order,String platform) {
        date = date.replaceAll("\\s*", "");
        AldAdAnomalyDataVo aldAdAnomalyDataVo = new AldAdAnomalyDataVo(app_key, date, module, prop, order);
        aldAdAnomalyDataVo.setCurrentPage(currentPage);
        aldAdAnomalyDataVo.setTotal(total);
        if(platform !=null){
            aldAdAnomalyDataVo.setPlatform(platform);
        }
        Map<String, Object> mapEntity = null;
        try {
            aldAdAnomalyDataVo = AdAssist.requestHandler(aldAdAnomalyDataVo);
            mapEntity = aldAdAnomalyDataService.queryPageDataList(aldAdAnomalyDataVo);
        } catch (Exception e) {
            LOG.error("处理异常：{}", e);
            return resultJson(new Messager("系统处理异常", 202));
        }
        return resultJson2(mapEntity, new Object[]{date});
    }


    /**
     * 反作弊
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cheatDataCount", method = RequestMethod.POST)
    public String cheatDataCount(@NotEmpty String app_key, String date,String platform) {
        date = date.replaceAll("\\s*", "");
        AldAdAnomalyDataVo aldAdAnomalyDataVo = new AldAdAnomalyDataVo();
        aldAdAnomalyDataVo.setAppKey(app_key);
        aldAdAnomalyDataVo.setDate(date);
        if(platform !=null){
            aldAdAnomalyDataVo.setPlatform(platform);
        }
        Map<String, Object> mapEntity = null;
        try {
            aldAdAnomalyDataVo = AdAssist.requestHandler(aldAdAnomalyDataVo);
            mapEntity = aldAdAnomalyDataService.cheatDataCount(aldAdAnomalyDataVo);
//            aldAdAnomalyDataVo.setDate(date);
            mapEntity = AdAssist.responseCheatFormat(mapEntity, aldAdAnomalyDataVo);
        } catch (Exception e) {
            LOG.error("处理异常：{}", e);
            return resultJson(new Messager("系统处理异常", 202));
        }
        return resultJson3(mapEntity, new Object[]{date});
    }


    /**
     * 打开次数接口
     * @param app_key
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "openPageCount", method = RequestMethod.POST)
    public String openPageCount(String app_key, String date,String platform) {
        date = date.replaceAll("\\s*", "");
        AldAdDataVo aldAdDataVo = new AldAdDataVo(app_key);
        aldAdDataVo.setDate(date);
        if(platform !=null){
            aldAdDataVo.setPlatform(platform);
        }
        Map<String, Object> mapEntity = null;
        try {
            aldAdDataVo = AdAssist2.requestHandler(aldAdDataVo);
            mapEntity = aldDataService.openPageCount(aldAdDataVo);
//            aldAdDataVo.setDate(date);
            mapEntity = AdAssist2.responseAdCountFormat(mapEntity, aldAdDataVo);
        } catch (Exception e) {
            LOG.error("处理异常：{}", e);
            return resultJson(new Messager("系统处理异常", 202));
        }
        return resultJson3(mapEntity, new Object[]{date});
    }





    /**
     * 新增用户接口
     * @param app_key
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "newUserCount")
    public String newUserCount(String app_key, String date,String platform) {
        date = date.replaceAll("\\s*", "");
        AldAdDataVo aldAdDataVo = new AldAdDataVo(app_key);
        aldAdDataVo.setDate(date);
        if(platform !=null){
            aldAdDataVo.setPlatform(platform);
        }
        Map<String, Object> mapEntity = null;
        try {
            // 设置7天30天表。
            aldAdDataVo = AdAssist2.requestHandler(aldAdDataVo);
            mapEntity = aldDataService.newUserCount(aldAdDataVo);
//            aldAdDataVo.setDate(date);
            mapEntity = AdAssist2.responseAdCountFormat(mapEntity, aldAdDataVo);
        } catch (Exception e) {
            LOG.error("处理异常：{}", e);
            return resultJson(new Messager("系统处理异常", 202));
        }
        return resultJson3(mapEntity, new Object[]{date});
    }





    /**
     * 访问人数接口
     * @param app_key
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "visitUserCount")
    public String visitUserCount(String app_key, String date,String platform) {
        date = date.replaceAll("\\s*", "");
        AldAdDataVo aldAdDataVo = new AldAdDataVo(app_key);
        aldAdDataVo.setDate(date);
        if(platform !=null){
            aldAdDataVo.setPlatform(platform);
        }
        Map<String, Object> mapEntity = null;
        try {
            aldAdDataVo = AdAssist2.requestHandler(aldAdDataVo);
            mapEntity = aldDataService.visitUserCount(aldAdDataVo);
            mapEntity = AdAssist2.responseAdCountFormat(mapEntity, aldAdDataVo);
        } catch (Exception e) {
            LOG.error("处理异常：{}", e);
            return resultJson(new Messager("系统处理异常", 202));
        }
        return resultJson3(mapEntity, new Object[]{date});
    }

}
