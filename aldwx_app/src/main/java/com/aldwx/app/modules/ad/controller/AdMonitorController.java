package com.aldwx.app.modules.ad.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.common.util.CurrencyVo;
import com.aldwx.app.common.util.VoUtil;
import com.aldwx.app.modules.ad.assist.AdAssist;
import com.aldwx.app.modules.ad.bean.Ad;
import com.aldwx.app.modules.ad.entity.AdEntity;
import com.aldwx.app.modules.ad.service.AdMonitorService;
import com.aldwx.app.modules.dimension.bean.Qr;
import com.aldwx.app.modules.dimension.entity.QrEntity;
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
 * 广告监测Controller
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldstat/app/ad")
public class AdMonitorController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AdMonitorController.class);

    @Autowired
    private AdMonitorService adMonitorService;


    /**
     * 来源 - 广告监测数据 - 概览
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/view", method = RequestMethod.POST)
    public String queryQrView(String app_key, String date, String app_type) {
        List<AdEntity> adEntityList = this.adMonitorService.queryAdView(new Ad(app_key, date, app_type));
        return resultJosn4(adEntityList.get(0));
    }


    /**
     * 来源 - 广告监测数据 - 折线图
     * @param app_key
     * @param date
     * @param app_type
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/trend", method = RequestMethod.POST)
    public String  queryQrChart(String app_key, String date, String app_type, String type) {
        CurrencyVo vo=VoUtil.getCurrencyVo(app_key,date);
        List<List<AdEntity>> adEntityList = this.adMonitorService.queryAdChart(new Ad(app_key, date, app_type, type));
        return resultJosn4(AdAssist.formatChart(adEntityList, date, type,vo));
    }


    /**
     * 来源 - 广告监测数据 - 列表
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/particular", method = RequestMethod.POST)
    public String queryQrList(String app_key, String date, String app_type, String currentPage) {
        Map<String,Object>  adEntityList = this.adMonitorService.queryAdList(new Ad(app_key, date, app_type,Integer.parseInt(currentPage)));
        return resultJosn10(adEntityList);
    }






}
