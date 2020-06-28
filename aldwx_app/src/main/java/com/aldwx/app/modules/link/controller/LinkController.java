package com.aldwx.app.modules.link.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.modules.link.bean.Link;
import com.aldwx.app.modules.link.entity.LinkEntity;
import com.aldwx.app.modules.link.service.LinkService;
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
 * 推广活动
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldstat/app/link")
public class LinkController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(LinkController.class);

    @Autowired
    private LinkService linkService;


    /**
     * 来源 - 智能外链 - 概览
     * @param appKey
     * @param date
     * @param target
     * @param appType
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/view", method = RequestMethod.POST)
    public String queryLinkDataView(String appKey, String date, String target, String appType) {
//        List<LinkEntity> linkEntityList = linkService.queryLinkDataView(new Link(appKey, date, appType));
//        if(null != linkEntityList && linkEntityList.size() > 0) {
//            return resultJosn4(linkEntityList.get(0));
//        }
        return resultJosn4(null);
    }


    /**
     * 来源 - 智能外链 来源渠道 - 折线图
     * @param appKey
     * @param date
     * @param target
     * @param appType
     * @return
     */
    public String queryLinkDataChart(String appKey, String date, String target, String appType) {
        List<LinkEntity> linkEntityList = linkService.queryLinkDataChart(new Link(appKey, date, target, appType));
        return resultJosn4(linkEntityList);
    }


    /**
     * 来源 - 智能外链 渠道明细 - 列表
     * @param appKey
     * @param date
     * @param target
     * @param appType
     * @return
     */
    public String queryLinkDataList(String appKey, String date, String target, String appType) {
        List<LinkEntity> linkEntityList = linkService.queryLinkDataList(new Link(appKey, date, target, appType));
        return resultJosn4(linkEntityList);
    }



}
