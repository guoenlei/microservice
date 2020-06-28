package com.aldwx.app.modules.funnel.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.modules.funnel.bean.Funnel;
import com.aldwx.app.modules.funnel.entity.FunnelEntity;
import com.aldwx.app.modules.funnel.service.FunnelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 漏斗
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldstat/app/funnel")
public class FunnelController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(FunnelController.class);

    @Autowired
    private FunnelService funnelService;


    /**
     * 行为 - 转换漏斗 - 来源
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/view", method = RequestMethod.POST)
    public String querySourceView(String app_key, String date, String app_type) {
        List<FunnelEntity> funnelEntityList = funnelService.queryFunnelDataList(new Funnel(app_key, date, app_type));
        return resultJosn4(funnelEntityList);
    }



    /**
     * 行为 - 转化漏斗 - 列表
     * @param app_key
     * @param date
     * @param app_type
     * @param source
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public String queryFunnelDataList(String app_key, String date, String app_type, String source) {
        List<FunnelEntity> funnelEntityList = funnelService.queryFunnelDataList(new Funnel(app_key, date, app_type, source));
        return resultJosn4(funnelEntityList);
    }


    /**
     * 行为 - 转化漏斗 - 步骤明细
     * @param app_key
     * @param date
     * @param app_type
     * @param source
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/step", method = RequestMethod.POST)
    public String queryFunnelDetail(String app_key, String date, String app_type, String source) {
        List<FunnelEntity> funnelEntityList = funnelService.queryFunnelDetail(new Funnel(app_key, date, app_type, source));
        return resultJosn4(funnelEntityList);
    }





}
