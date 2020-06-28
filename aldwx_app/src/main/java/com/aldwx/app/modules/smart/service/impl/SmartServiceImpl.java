package com.aldwx.app.modules.smart.service.impl;

import com.aldwx.app.common.base.BaseMethod;
import com.aldwx.app.common.page.PageHandle;
import com.aldwx.app.modules.link.entity.LinkEntity;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.aldwx.app.modules.smart.bean.Smart;
import com.aldwx.app.modules.smart.dao.game.SmartGameDao;
import com.aldwx.app.modules.smart.dao.stat.SmartStatDao;
import com.aldwx.app.modules.smart.entity.SmartEntity;
import com.aldwx.app.modules.smart.service.SmartService;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.github.pagehelper.Page;
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
 * 智能外链
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class SmartServiceImpl extends BaseMethod implements SmartService {

    private static final Logger LOG = LoggerFactory.getLogger(SmartServiceImpl.class);

    @Autowired
    private SmartStatDao smartStatDao;
    @Autowired
    private SmartGameDao smartGameDao;


    /**
     * 概况 - 智能外链跟踪top5 - 列表
     * @param smart
     * @return
     */
    @Override
    public List<SmartEntity> querySmartTrendTopList(Smart smart) {
        String appType = smart.getAppType();
        List<SmartEntity> smartEntityList = null;
        PageHandle.startPage(smart.getCurrentPage(), smart.getPageSize());
        if(isStatApp(appType)) {
            smartEntityList = smartStatDao.querySmartTrendTopList(smart);
        } else {
            smartEntityList = smartGameDao.querySmartTrendTopList(smart);
        }
        return smartEntityList;
    }

    /**
     * 来源 - 智能外链跟踪 - 概览
     * @param smart
     * @return
     */
    @Override
    public List<SmartEntity> querySmartDataView(Smart smart) {
        String appType = smart.getAppType();
        List<SmartEntity> smartEntityList = null;
        //概览汇总
        smart.setTableName("aldstat_smartLink_summary_analysis");
        if(isStatApp(appType)) {
            smartEntityList = smartStatDao.querySmartDataView(smart);
        } else {
            smartEntityList = smartGameDao.querySmartDataView(smart);
        }
        if(null == smartEntityList && smartEntityList.size() == 0) {
            smartEntityList = Lists.newArrayList();
            smartEntityList.add(new SmartEntity());
        }

        return smartEntityList;
    }

    /**
     * 来源 - 智能外链来源渠道 - 折线图
     * @param smart
     * @return
     */
    @Override
    public List<SmartEntity> querySmartDataChart(Smart smart) {
        String appType = smart.getAppType();
        List<SmartEntity> entityList = null;
        if(smart.getType().equals("1")) {
            smart.setType("new_comer_count");
        } else {
            smart.setType("visitor_count");
        }
        List<List<SmartEntity>> smartEntityList = Lists.newArrayList();
        if(isStatApp(appType)) {
                if(smart.getDate().equals("1") || smart.getDate().equals("2")) {
                    entityList = smartStatDao.querySmartDataHourChart(smart);
                } else {
                    entityList = smartStatDao.querySmartDataDayChart(smart);
                }
        } else {
                if(smart.getDate().equals("1") || smart.getDate().equals("2")) {
                    entityList = smartGameDao.querySmartDataHourChart(smart);
                } else {
                    entityList = smartGameDao.querySmartDataDayChart(smart);
                }
        }
        return entityList;
    }

    /**
     * 来源 - 智能外链来源渠道明细 - 列表
     * @param smart
     * @return
     */
    @Override
    public Map<String,Object> querySmartDataList(Smart smart) {
        String appType = smart.getAppType();
        Map<String,Object> map=new HashMap<>();
        List<SmartEntity> smartEntityList = null;
        Page<?> page=PageHandle.startPage(smart.getCurrentPage(), smart.getPageSize());
        if(isStatApp(appType)) {
            smartEntityList = smartStatDao.querySmartDataList(smart);
        } else {
            smartEntityList = smartGameDao.querySmartDataList(smart);
        }

        map.put("data",smartEntityList);
        map.put("num", page.getTotal());
        return map;
    }


}
