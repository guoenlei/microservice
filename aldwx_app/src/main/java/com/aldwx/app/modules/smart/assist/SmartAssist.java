package com.aldwx.app.modules.smart.assist;

import com.aldwx.app.common.base.BaseAssist;
import com.aldwx.app.common.util.CurrencyVo;
import com.aldwx.app.modules.ad.entity.AdEntity;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.aldwx.app.modules.smart.entity.SmartEntity;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;

import java.util.*;

/**
 * @author
 * @description
 * @createTime
 **/
public class SmartAssist /*extends BaseAssist*/ {



    /**
     * 格式化返回折线图数据
     * @param lists
     * @param date
     * @param type
     * @return
     */
    public static Map<String, Object> formatChart(List<SmartEntity> lists, String date, String type, CurrencyVo vo) {
       /* Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<Map<String, Object>> listMap = Lists.newArrayList();
        if(null != lists && lists.size() > 0) {
            for(List<SmartEntity> smartEntities : lists) {
                if(null != smartEntities && smartEntities.size() > 0) {
                    for(SmartEntity s : smartEntities) {
                        Map<String, Object> map = Maps.newConcurrentMap();
                        if(null != s) {
                            map.put("name", s.getLinkName());
                            String year = null;
                            if(date.equals("1") || date.equals("2")) {
                                year = s.getHour();
                            } else {
                                year = s.getDay();
                            }
                            map.put("year", year);
                        }
                        double value = 0.0;
                        if(type.equals("1")) {
                            value = s.getNewComerCount();
                        } else {
                            value = s.getVisitorCount();
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
            for(SmartEntity s : lists) {
                set.add(s.getLinkName());
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
                    for(SmartEntity s : lists) {
                        for (int z=0;z<listTmp.size();z++){
                            Map<String,Object> map=listTmp.get(z);
                            String year = null;
                            if(date.equals("1") || date.equals("2")) {
                                year = s.getHour();
                            } else {
                                year = s.getDay().trim();
                            }
                            Long a=0L;
                            if(type.equals("1")) {
                                a = s.getNewComerCount();
                            } else {
                                a = s.getVisitorCount();
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
        resultMap.put("data", listTmp);
        return resultMap;
    }


}
