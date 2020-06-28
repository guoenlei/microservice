package com.aldwx.app.modules.ad.assist;

import com.aldwx.app.common.base.BaseAssist;
import com.aldwx.app.common.util.CurrencyVo;
import com.aldwx.app.modules.ad.entity.AdEntity;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;

import java.util.*;

/**
 * 广告监测辅助类
 * @author
 * @description
 * @createTime
 **/
public class AdAssist extends BaseAssist {



    /**
     * 格式化返回折线图数据
     * @param lists
     * @param date
     * @param type
     * @return
     */
    public static Map<String, Object> formatChart(List<List<AdEntity>> lists, String date, String type, CurrencyVo vo) {
       /* Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<Map<String, Object>> listMap = Lists.newArrayList();
        if(null != lists && lists.size() > 0) {
            for(List<AdEntity> adEntityList : lists) {
                if(null != adEntityList && adEntityList.size() > 0) {
                    for(AdEntity a : adEntityList) {
                        Map<String, Object> map = Maps.newConcurrentMap();
                        if(null != a) {
                            map.put("name", a.getLinkName());
                            String year = null;
                            if(date.equals("1") || date.equals("2")) {
                                year = a.getHour();
                            } else {
                                year = a.getDay();
                            }
                            map.put("year", year);
                        }
                        double value = 0.0;
                        if(type.equals("1")) {
                            value = a.getLinkNewerForApp();
                        } else {
                            value = a.getLinkVisitorCount();
                        }
                        map.put("value", value);
                        listMap.add(map);
                    }
                }
            }
        }

        resultMap.put("data", listMap);

        return resultMap;*/

        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<Map<String, Object>> listTmp = Lists.newArrayList();
        Set<String> set=new HashSet<>();
        if (lists!=null&&lists.size()>0){
            for(List<AdEntity> adEntityList : lists) {
                if (null != adEntityList && adEntityList.size() > 0){
                    for(AdEntity s : adEntityList) {
                        set.add(s.getLinkName());
                    }
                }
            }
        }
        if (set.size()>0){
            for (String str : set) {
                if (date.equals("1")||date.equals("2")){
                    for (int i=0;i<24;i++){
                        Map map=new HashMap();
                        map.put("name", str);
                        map.put("year", i+"");
                        map.put("value", "0");
                        listTmp.add(map);
                    }
                }else {
                    List<String> listTime=vo.getListDate();
                    for (int i=0;i<listTime.size();i++){
                        Map map=new HashMap();
                        map.put("name", str);
                        map.put("year", listTime.get(i));
                        map.put("value", "0");
                        listTmp.add(map);
                    }
                }
            }
        }
        if(null != lists && lists.size() > 0) {
            for(List<AdEntity> adEntityList : lists) {
                if(null != adEntityList && adEntityList.size() > 0) {
                    for(AdEntity s : adEntityList) {
                        for (int z=0;z<listTmp.size();z++){
                            Map<String,Object> map=listTmp.get(z);
                            String year = null;
                            Long a=0L;
                            try{
                                if(date.equals("1") || date.equals("2")) {
                                    year = s.getHour();
                                } else {
                                    year = s.getDay().trim();
                                }
                                if(type.equals("1")) {
                                    a = s.getLinkNewerForApp();
                                } else {
                                    a = s.getLinkVisitorCount();
                                }
                            }catch (Exception e){
                                year="nodata";
                            }
                            if(date.equals("1") || date.equals("2")) {
                                if (map.get("name").equals(s.getLinkName())&&map.get("year").equals(year)){
                                    map.put("value",a+"");
                                    listTmp.set(z,map);
                                }
                            }else if (map.get("name").equals(s.getLinkName())&&map.get("year").equals(year)){
                                map.put("value",a+"");
                                listTmp.set(z,map);
                            }
                        }
                    }
                }
            }
        }
        resultMap.put("data", listTmp);
        return resultMap;
    }


    /**
     * 格式化折线图
     * @param lists
     * @param date
     * @param type
     * @return
     *
     *
     *
     * {series:}
     *
     *
     *
     *
     *
     *
     *
     *
     */
    public static Map<String, Object> formatCahrt2(List<List<AdEntity>> lists, String date, String type) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();

        List<Object> objectList = Lists.newArrayList();
        Map<String, Object> map1 = Maps.newConcurrentMap();
        Map<String, Object> map2 = Maps.newConcurrentMap();
        List<AdEntity> todayList = Lists.newArrayList();
        List<AdEntity> yesterdayList = Lists.newArrayList();

        map1.put("name", "今日");
        map1.put("data", todayList);

        map2.put("name", "昨日");
        map2.put("data", yesterdayList);

        objectList.add(map1);
        objectList.add(map2);

        resultMap.put("series", objectList);

        List<String> dateList = Lists.newArrayList();
        for(int i = 0; i < 24; i++) {
            dateList.add(String.format("%时", i));
        }

        resultMap.put("xAxis", dateList);

        if(null != lists && lists.size() > 0) {
            for(List<AdEntity> adEntityList : lists) {
                if(null != adEntityList && adEntityList.size() > 0) {
                    for(AdEntity a : adEntityList) {
                        Map<String, Object> map = Maps.newConcurrentMap();
                        String linkName = a.getLinkName();



                    }
                }
            }
        }


        return null;
    }









}
