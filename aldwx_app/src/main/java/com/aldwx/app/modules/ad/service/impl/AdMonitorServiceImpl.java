package com.aldwx.app.modules.ad.service.impl;

import com.aldwx.app.common.base.BaseMethod;
import com.aldwx.app.common.page.PageHandle;
import com.aldwx.app.common.util.StringUtil;
import com.aldwx.app.modules.ad.bean.Ad;
import com.aldwx.app.modules.ad.dao.game.AdMonitorGameDao;
import com.aldwx.app.modules.ad.dao.stat.AdMonitorStatDao;
import com.aldwx.app.modules.ad.entity.AdEntity;
import com.aldwx.app.modules.ad.service.AdMonitorService;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 广告监测
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class AdMonitorServiceImpl extends BaseMethod implements AdMonitorService {



    @Autowired
    private AdMonitorStatDao adMonitorStatDao;

    @Autowired
    private AdMonitorGameDao adMonitorGameDao;


    /**
     * 来源 - 广告监测数据 - 概览
     * @param ad
     * @return
     */
    @Override
    public List<AdEntity> queryAdView(Ad ad) {
        String appType = ad.getAppType();
        List<AdEntity> adEntityList = null;
        if(ad.getDate().equals("1") || ad.getDate().equals("2")) {
            ad.setTableName("aldstat_link_summary");
        } else if(ad.getDate().equals("3")){
            ad.setTableName("aldstat_7days_link_summary");
        }else if(ad.getDate().equals("4")){
            ad.setTableName("aldstat_30days_link_summary");
        }
        if(isStatApp(appType)) {
            adEntityList = this.adMonitorStatDao.queryAdView(ad);
        } else {
            adEntityList = this.adMonitorGameDao.queryAdView(ad);
        }
        if(null == adEntityList || adEntityList.size() == 0) {
            adEntityList = Lists.newArrayList();
            adEntityList.add(new AdEntity());
        }
        return adEntityList;
    }



    /**
     * 来源 - 广告监测数据 - 折线图
     * @param ad
     * @return
     */
    @Override
    public List<List<AdEntity>> queryAdChart(Ad ad) {
        String appType = ad.getAppType();
        List<List<AdEntity>> adEntityList = Lists.newArrayList();
        if(ad.getType().equals("1")) {
            ad.setType("link_newer_for_app");
        } else {
            ad.setType("link_visitor_count");
        }
        if(ad.getDate().equals("1") || ad.getDate().equals("2")) {
            ad.setTableName("aldstat_hourly_link");
        } else {
            ad.setTableName("aldstat_daily_link");
        }
        if(isStatApp(appType)) {
            List<AdEntity> adEntities = this.adMonitorStatDao.queryAdTopList(ad);
            for(AdEntity a : adEntities) {
                if(null != a && StringUtils.isNotBlank(a.getLinkKey())) {
                    ad.setLinkKey(a.getLinkKey());
                    List<AdEntity> entityList = null;
                    if(ad.getDate().equals("1") || ad.getDate().equals("2")) {
                        entityList = this.adMonitorStatDao.queryAdHourChart(ad);
                    } else {
                        entityList = this.adMonitorStatDao.queryAdDayChart(ad);
                    }
                    adEntityList.add(entityList);
                }
            }
        } else {
            List<AdEntity> adEntities = this.adMonitorGameDao.queryAdTopList(ad);
            for(AdEntity a : adEntities) {
                if(null != a && StringUtils.isNotBlank(a.getLinkKey())) {
                    ad.setLinkKey(a.getLinkKey());
                    List<AdEntity> entityList = null;
                    if(ad.getDate().equals("1") || ad.getDate().equals("2")) {
                        entityList = this.adMonitorGameDao.queryAdHourChart(ad);
                    } else {
                        entityList = this.adMonitorGameDao.queryAdDayChart(ad);
                    }
                    adEntityList.add(entityList);
                }
            }
        }
        return adEntityList;
    }



    /**
     * 来源 - 广告监测数据 - 列表
     * @param ad
     * @return
     */
    @Override
    public Map<String,Object> queryAdList(Ad ad) {
        String appType = ad.getAppType();
        List<AdEntity> adEntityList = null;
        List<Map> adEntityList2 = new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        PageHandle.startPage(ad.getCurrentPage(), ad.getPageSize());
        if(isStatApp(appType)) {
            adEntityList = this.adMonitorStatDao.queryAdList(ad);
        } else {
            adEntityList = this.adMonitorGameDao.queryAdList(ad);
        }
        PageInfo<AdEntity> pageInfo = new PageInfo<>(adEntityList);
        if (adEntityList!=null&&adEntityList.size()>0){
            for (AdEntity adEntity: adEntityList ){
                Map<String,String> mapEntity=new HashMap();
                mapEntity.put("bounceRate",String.valueOf(StringUtil.formatPercent2(adEntity.getBounceRate())));
                mapEntity.put("linkName",String.valueOf(adEntity.getLinkName()));
                mapEntity.put("linkOpenCount",String.valueOf(adEntity.getLinkOpenCount()));
                mapEntity.put("linkPageCount",String.valueOf(adEntity.getLinkPageCount()));
                mapEntity.put("linkVisitorCount",String.valueOf(adEntity.getLinkVisitorCount()));
                mapEntity.put("linkNewerForApp",String.valueOf(adEntity.getLinkNewerForApp()));
                mapEntity.put("linkAuthuserCount",String.valueOf(adEntity.getLinkAuthuserCount()));
                adEntityList2.add(mapEntity);
            }
        }
        map.put("data",adEntityList2);
        map.put("num", pageInfo.getTotal());
        return map;
    }



}
