package com.aldwx.app.modules.page.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.modules.page.bean.Page;
import com.aldwx.app.modules.page.entity.PageEntity;
import com.aldwx.app.modules.page.service.PageService;
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
 * 受访页
 * @author lx
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldstat/app/page")
public class PageController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private PageService pageService;


    /**
     * 行为 - 页面分析 - 受访页来源/入口页
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/source", method = RequestMethod.POST)
    public String querySourceList(String app_key, String date, String app_type) {
        List<PageEntity> pageEntityList = this.pageService.querySourceList(new Page(app_key, date, app_type));
        return resultJosn4(pageEntityList);
    }


    /**
     * 行为 - 页面分析 - 受访页列表/入口页列表
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public String queryPageList(String app_key, String date, String app_type, String source,String currentPage) {
        Map<String,Object> pageEntityList = this.pageService.queryPageList(new Page(app_key, date, app_type, source,Integer.parseInt(currentPage)));
        return resultJosn10(pageEntityList);
    }





}
