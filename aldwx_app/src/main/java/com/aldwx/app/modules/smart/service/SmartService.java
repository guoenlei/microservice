package com.aldwx.app.modules.smart.service;

import com.aldwx.app.modules.smart.bean.Smart;
import com.aldwx.app.modules.smart.entity.SmartEntity;

import java.util.List;
import java.util.Map;

/**
 * 智能外链
 */


public interface SmartService {



    /**
     * 概况 - 智能外链跟踪top5 - 列表
     * @param smart
     * @return
     */
    List<SmartEntity> querySmartTrendTopList(Smart smart);

    /**
     * 来源 - 智能外链跟踪 - 概览
     * @param smart
     * @return
     */
    List<SmartEntity> querySmartDataView(Smart smart);


    /**
     * 来源 - 智能外链来源渠道 - 折线图
     * @param smart
     * @return
     */
    List<SmartEntity> querySmartDataChart(Smart smart);


    /**
     * 来源 - 智能外链来源渠道明细 - 列表
     * @return
     */
    Map<String,Object> querySmartDataList(Smart smart);

}
