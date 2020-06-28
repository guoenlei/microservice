package com.aldwx.app.modules.general.service;

import com.aldwx.app.modules.general.bean.General;
import com.aldwx.app.modules.page.entity.PageEntity;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.aldwx.app.modules.smart.entity.SmartEntity;
import com.aldwx.app.modules.trend.entity.TrendEntity;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;


/**
 *
 * 概况service
 *
 */
public interface GeneralService /*extends BaseService*/ {


    /**
     * 概况 - 今日概况数据 - 概览
     * @param general
     * @return
     */
    Pair<List<TrendEntity>, List<TrendEntity>> queryBasicDataView(General general);


    /**
     * 概况 - 获取基础指标数据 - 折线图
     * @param general
     * @return
     */
    Map<String,List<TrendEntity>>  queryBasicDataChart(General general);


    /**
     * 概况 - 获取基础指标数据 - 折线图 - 改需求后
     * @param general
     * @return
     */
    Map<String, List<TrendEntity>> queryBasicDataChartNew(General general);


    /**
     * 概况 - 场景值趋势TOP5 - 列表
     * @param general
     * @return
     */
    List<SceneEntity> querySceneTrendTopList(General general);

    /**
     * 概况 - 场景值趋势TOP5 - 列表 - 改需求后
     * @param app_key
     * @param date
     * @param app_type
     * @param type
     * @return
     */
    Map<String,Object> querySceneTrendTopListNew(String app_key, String date, String app_type, String type);


    /**
     * 概况 - 页面趋势TOP5 - 列表
     * @param general
     * @return
     */
    List<PageEntity> queryPageTrendTopList(General general);


    /**
     * 概况 - 智能外链跟踪TOP5 - 列表
     * @param general
     * @return
     */
    List<SmartEntity> querySmartTrendTopList(General general);


}
