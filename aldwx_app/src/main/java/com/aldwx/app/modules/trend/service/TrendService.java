package com.aldwx.app.modules.trend.service;


import com.aldwx.app.modules.trend.bean.Trend;
import com.aldwx.app.modules.trend.entity.TrendEntity;

import java.util.List;

/**
 *
 * 趋势
 *
 */
public interface TrendService /*extends BaseService<TrendEntity, Trend>*/ {



    /**
     * 概况 - 今日概况数据 - 概览
     * @param trend
     * @return
     */
    List<TrendEntity>  queryBasicDataView(Trend trend);



    /**
     * 概况 - 获取基础指标数据 - 折线图
     * @param trend
     * @return
     */
    List<List<TrendEntity>> queryBasicDataChart(Trend trend);


}
