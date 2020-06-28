package com.aldwx.app.modules.page.service;

import com.aldwx.app.modules.page.bean.Page;
import com.aldwx.app.modules.page.entity.PageEntity;


import java.util.List;
import java.util.Map;


/**
 *
 * 受访页
 *
 */
public interface PageService /*extends BaseService<PageEntity, Page>*/ {




    /**
     * 概况 - 页面趋势TOP5 - 列表
     * @param page
     * @return
     */
    List<PageEntity> queryPageTrendTopList(Page page);





    /**
     * 行为 - 页面分析 - 受访页来源
     * @param page
     * @return
     */
    List<PageEntity> querySourceList(Page page);




    /**
     * 行为 - 页面分析 - 受访页列表
     * @param page
     * @return
     */
    Map<String,Object> queryPageList(Page page);



}
