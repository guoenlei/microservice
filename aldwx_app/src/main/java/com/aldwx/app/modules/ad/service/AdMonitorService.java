package com.aldwx.app.modules.ad.service;


import com.aldwx.app.modules.ad.bean.Ad;
import com.aldwx.app.modules.ad.entity.AdEntity;

import java.util.List;
import java.util.Map;

/**
 * 广告监测Service
 */
public interface AdMonitorService /*extends BaseService */{


    /**
     * 来源 - 广告监测 - 概览
     * @param ad
     * @return
     */
    List<AdEntity> queryAdView(Ad ad);


    /**
     * 来源 - 广告监测数据 - 折线图
     * @param ad
     * @return
     */
    List<List<AdEntity>> queryAdChart(Ad ad);


    /**
     * 来源 - 广告监测数据 - 列表
     * @param ad
     * @return
     */
    Map<String,Object> queryAdList(Ad ad);



}
