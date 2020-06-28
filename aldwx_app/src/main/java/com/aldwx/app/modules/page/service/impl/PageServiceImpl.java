package com.aldwx.app.modules.page.service.impl;

import com.aldwx.app.common.base.BaseMethod;
import com.aldwx.app.common.page.PageHandle;
import com.aldwx.app.modules.page.bean.Page;
import com.aldwx.app.modules.page.dao.game.PageGameDao;
import com.aldwx.app.modules.page.dao.stat.PageStatDao;
import com.aldwx.app.modules.page.entity.PageEntity;
import com.aldwx.app.modules.page.service.PageService;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 受访页
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class PageServiceImpl extends BaseMethod implements PageService {

    private static final Logger LOG = LoggerFactory.getLogger(PageServiceImpl.class);

    @Autowired
    private PageStatDao pageStatDao;
    @Autowired
    private PageGameDao pageGameDao;



    /**
     * 概况 - 页面趋势TOP5 - 列表
     * @param page
     * @return
     */
    @Override
    public List<PageEntity> queryPageTrendTopList(Page page) {
        String appType = page.getAppType();
        List<PageEntity> pageEntityList = null;
        page.setLimitNum(SCENE_TREND_DATA_LIMIT);
        PageHandle.startPage(page.getCurrentPage(), page.getPageSize());
        if(isStatApp(appType)) {
            pageEntityList = pageStatDao.queryPageDataTopList(page);
        } else {
            pageEntityList = pageGameDao.queryPageDataTopList(page);
        }

        return pageEntityList;
    }


    /**
     * 行为 - 页面分析 - 受访页来源
     * @param page
     * @return
     */
    @Override
    public List<PageEntity> querySourceList(Page page) {
        String appType = page.getAppType();
        List<PageEntity> pageEntityList = null;
        if(isStatApp(appType)) {
            pageEntityList = this.pageStatDao.querySourceList(page);
        } else {
            pageEntityList = this.pageStatDao.querySourceList(page);
        }
        if(null == pageEntityList && pageEntityList.size() == 0) {
            pageEntityList = Lists.newArrayList();
            pageEntityList.add(new PageEntity());
        }
        return pageEntityList;
    }


    /**
     * 行为 - 页面分析 - 受访页列表
     * @param page
     * @return
     */
    @Override
    public Map<String,Object> queryPageList(Page page) {
        String appType = page.getAppType();
        String pageType = page.getType();
        Map<String,Object> map=new HashMap<>();
        List<PageEntity> pageEntityList = null;
        PageHandle.startPage(page.getCurrentPage(), page.getPageSize());
        if(isStatApp(appType)) {
            //受访页
            if(pageType.equals("1")) {
                pageEntityList = this.pageStatDao.queryPageList(page);
                //入口页
            } else {

            }
        } else {
            pageEntityList = this.pageGameDao.queryPageList(page);
        }
        if(null == pageEntityList || pageEntityList.size() == 0 || null == pageEntityList.get(0)) {
            pageEntityList = Lists.newArrayList();
        }
        PageInfo<PageEntity> pageInfo = new PageInfo<>(pageEntityList);
        map.put("data",pageEntityList);
        map.put("num", pageInfo.getTotal());
        return map;
    }


}
