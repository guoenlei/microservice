package com.aldwx.app.modules.smart.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.common.util.CurrencyVo;
import com.aldwx.app.common.util.VoUtil;
import com.aldwx.app.modules.scene.controller.SceneController;
import com.aldwx.app.modules.smart.assist.SmartAssist;
import com.aldwx.app.modules.smart.bean.Smart;
import com.aldwx.app.modules.smart.entity.SmartEntity;
import com.aldwx.app.modules.smart.service.SmartService;
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
 * 智能外链
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldstat/app/smart")
public class SmartController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(SceneController.class);

    @Autowired
    private SmartService smartService;


    /**
     * 来源 - 智能外链跟踪 - 概览
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/view", method = RequestMethod.POST)
    public String querySmartDataView(String app_key, String date, String app_type) {
        List<SmartEntity> smartEntityList = smartService.querySmartDataView(new Smart(app_key, date, app_type));
        return resultJosn4(smartEntityList.get(0));
    }


    /**
     * 来源 - 智能外链 来源渠道 - 折线图
     * @param app_key
     * @param date
     * @param app_type
     * @param type
     *          1新增 2活跃
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/source", method = RequestMethod.POST)
    public String querySmartDataChart(String app_key, String date, String app_type, String type) {
        CurrencyVo vo=VoUtil.getCurrencyVo(app_key,date);
        List<SmartEntity> smartEntityList = smartService.querySmartDataChart(new Smart(app_key, date, app_type, type));
        return resultJosn4(SmartAssist.formatChart(smartEntityList, date, type,vo));
    }


    /**
     * 来源 - 智能外链 来源渠道明细 - 列表
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/particular", method = RequestMethod.POST)
    public String querySmartDataList(String app_key, String date, String app_type,String currentPage) {
        Map<String,Object> smartEntityList = smartService.querySmartDataList(new Smart(app_key, date, app_type,Integer.parseInt(currentPage)));
        return resultJosn10(smartEntityList);
    }





}
