package com.aldwx.app.modules.general.service.impl;

import com.aldwx.app.common.base.BaseMethod;
import com.aldwx.app.common.util.DateUtil;
import com.aldwx.app.modules.general.bean.General;
import com.aldwx.app.modules.general.service.GeneralService;
import com.aldwx.app.modules.page.bean.Page;
import com.aldwx.app.modules.page.entity.PageEntity;
import com.aldwx.app.modules.page.service.PageService;
import com.aldwx.app.modules.scene.bean.Scene;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.aldwx.app.modules.scene.service.SceneService;
import com.aldwx.app.modules.smart.bean.Smart;
import com.aldwx.app.modules.smart.entity.SmartEntity;
import com.aldwx.app.modules.smart.service.SmartService;
import com.aldwx.app.modules.trend.bean.Trend;
import com.aldwx.app.modules.trend.entity.TrendEntity;
import com.aldwx.app.modules.trend.service.TrendService;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据概览
 *
 * @author lx
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class GeneralServiceImpl extends BaseMethod implements GeneralService {

    private static final Logger LOG = LoggerFactory.getLogger(GeneralServiceImpl.class);

    //趋势
    @Autowired
    private TrendService trendService;
    //场景值
    @Autowired
    private SceneService sceneService;
    //页面趋势
    @Autowired
    private PageService pageService;
    //智能外链
    @Autowired
    private SmartService smartService;


    /**
     * 概况 - 今日概况数据 - 概览
     *
     * @param general
     * @return Pair<List   <   TrendEntity>, List<TrendEntity>>
     */
    @Override
    public Pair<List<TrendEntity>, List<TrendEntity>> queryBasicDataView(General general) {
        Trend trend = new Trend(general);
        //获取今天数据
        List<TrendEntity> today = trendService.queryBasicDataView(trend);
        //获取昨天数据
        trend.setDateTime(DateUtil.getYesterday());
        List<TrendEntity> yesterday = trendService.queryBasicDataView(trend);

        return Pair.of(today, yesterday);
    }


    /**
     * 概况 - 获取基础指标数据 - 折线图
     *
     * @param general
     * @return
     */
    @Override
    public Map<String, List<TrendEntity>> queryBasicDataChart(General general) {
        Trend trend = new Trend(general);
        List<TrendEntity> today = null;
        List<TrendEntity> yesterday = null;
        List<TrendEntity> count = null;
        Map<String, List<TrendEntity>> map = new HashMap<>();
        if (trend.getDate().equals("1")) {
            //获取今天数据
            today = trendService.queryBasicDataChart(trend).get(0);
            count = trendService.queryBasicDataChart(trend).get(1);
            //获取昨天数据
            trend.setDateTime(DateUtil.getYesterday());
            yesterday = trendService.queryBasicDataChart(trend).get(0);
            map.put("today", today);
            map.put("yesterday", yesterday);
            map.put("count", count);
        } else {
            today = trendService.queryBasicDataChart(trend).get(0);
            count = trendService.queryBasicDataChart(trend).get(1);
            map.put("today", today);
            map.put("count", count);
            map.put("yesterday", yesterday);
        }

        return map;
    }

    /**
     * 概况 - 获取基础指标数据 - 折线图 - 改需求后
     *
     * @param general
     * @return
     */
    @Override
    public Map<String, List<TrendEntity>> queryBasicDataChartNew(General general) {
        Trend trend = new Trend(general);
        List<TrendEntity> today = null;
        List<TrendEntity> yesterday = null;
        List<TrendEntity> count = null;
        Map<String, List<TrendEntity>> map = new HashMap<>();
        if (trend.getDate().equals("1")) {
            //获取今天数据
            today = trendService.queryBasicDataChart(trend).get(0);
            count = trendService.queryBasicDataChart(trend).get(1);
            //获取昨天数据
            trend.setDateTime(DateUtil.getYesterday());
            yesterday = trendService.queryBasicDataChart(trend).get(0);
            map.put("today", today);
            map.put("yesterday", yesterday);
            map.put("count", count);
        } else {
            today = trendService.queryBasicDataChart(trend).get(0);
            count = trendService.queryBasicDataChart(trend).get(1);
            map.put("today", today);
            map.put("count", count);
            map.put("yesterday", yesterday);
        }

        return map;
    }

    /**
     * 概况 - 场景值趋势TOP5 - 列表
     *
     * @param general
     * @return
     */
    @Override
    public List<SceneEntity> querySceneTrendTopList(General general) {
        return sceneService.querySceneTrendTopList(new Scene(general));
    }

    /**
     * 概况 - 场景值趋势TOP5 - 列表 - 改需求后
     * @param app_key
     * @param date
     * @param app_type
     * @param type
     * @return
     */
    @Override
    public Map<String,Object> querySceneTrendTopListNew(String app_key, String date, String app_type, String type) {
        return sceneService.querySceneTrendTopListNew(new Scene(app_key, date, app_type, type));
    }


    /**
     * 概况 - 页面趋势TOP5 - 列表
     *
     * @param general
     * @return
     */
    @Override
    public List<PageEntity> queryPageTrendTopList(General general) {
        return pageService.queryPageTrendTopList(new Page(general));
    }


    /**
     * 概况 - 智能外链跟踪top5 - 列表
     *
     * @param general
     * @return
     */
    @Override
    public List<SmartEntity> querySmartTrendTopList(General general) {
        return smartService.querySmartTrendTopList(new Smart(general));
    }


}
