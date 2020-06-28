package com.aldwx.app.modules.share.assist;

import com.aldwx.app.common.util.CurrencyVo;
import com.aldwx.app.common.util.DateUtil;
import com.aldwx.app.common.util.StringUtil;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.aldwx.app.modules.share.entity.ShareEntity;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;

import java.util.*;

/**
 *
 * @author
 * @description
 * @createTime
 **/
public class ShareAssist {


    public static List<Map<String, Object>> formatPie(List<ShareEntity> shareEntityList, String type) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        if(null != shareEntityList && shareEntityList.size() > 0 && null != shareEntityList.get(0)) {
            ShareEntity entity = shareEntityList.get(0);
            if(null != entity) {
                //人数
                Map<String, Object> map1 = Maps.newConcurrentMap();
                Map<String, Object> map2 = Maps.newConcurrentMap();
                Map<String, Object> map3 = Maps.newConcurrentMap();
                int first_count = 0;
                int secondary_count = 0;
                int third_count = 0;
                Double count = 0.0;
                if(type.equals("1")) {
                    first_count = entity.getFirst_share_user_count();
                    secondary_count = entity.getSecondary_share_user_count();
                    third_count = entity.getThird_share_user_count();
                    count = (first_count+secondary_count+third_count)*1.0;
                    //次数
                } else {
                    first_count = entity.getFirst_share_count();
                    secondary_count = entity.getSecondary_share_count();
                    third_count = entity.getThird_share_count();
                    count =(first_count+secondary_count+third_count)*1.0;
                }
                map1.put("name", "一度");
                map1.put("percent", StringUtil.formatPercentNum(first_count/count));
                map2.put("name", "二度");
                map2.put("percent", StringUtil.formatPercentNum(secondary_count/count));
                map3.put("name", "三度");
                map3.put("percent",StringUtil.formatPercentNum( third_count/count));

                mapList.add(map1);
                mapList.add(map2);
                mapList.add(map3);
            }
        }

        return mapList;
    }




    public static Map<String, Object> formatChart(List<ShareEntity> shareEntityList, String date, CurrencyVo vo) {
        /*List<Map<String, Object>> mapList = Lists.newArrayList();
        if(null != shareEntityList && shareEntityList.size() > 0 && null != shareEntityList.get(0)) {
            for(ShareEntity entity : shareEntityList) {
                Map<String, Object> map1 = Maps.newConcurrentMap();
                Map<String, Object> map2 = Maps.newConcurrentMap();
                Map<String, Object> map3 = Maps.newConcurrentMap();
                if(null != entity) {
                    double shareUserCount = entity.getShare_user_count();
                    double shareCount = entity.getShare_count();
                    double newCount = entity.getNew_count();
                    if(date.equals("1") || date.equals("2")) {
                        Integer hour = (null == entity.getHour() ? 0 : entity.getHour());
                        map1.put("year", hour);
                        map2.put("year", hour);
                        map3.put("year", hour);
                    } else {
                        String year = DateUtil.DATE_FORMAT.format(entity.getDay());
                        map1.put("year", year);
                        map2.put("year", year);
                        map3.put("year", year);
                    }
                    map1.put("name", "分享人数");
                    map2.put("name", "分享次数");
                    map3.put("name", "分享新增");

                    map1.put("value", shareUserCount);
                    map2.put("value", shareCount);
                    map3.put("value", newCount);
                }

                mapList.add(map1);
                mapList.add(map2);
                mapList.add(map3);
            }
        }

        return mapList;*/

        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<Map<String, Object>> listTmp = Lists.newArrayList();
        Set<String> set=new HashSet<>();
        set.add("分享人数");
        set.add("分享次数");
        set.add("分享新增");
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
        if(null != shareEntityList && shareEntityList.size() > 0) {
                for(ShareEntity s : shareEntityList) {
                    for (int z=0;z<listTmp.size();z++){
                        Map<String,Object> map=listTmp.get(z);
                        String year = null;
                        if(date.equals("1") || date.equals("2")) {
                            Integer hour = (null == s.getHour() ? 0 : s.getHour());
                            year = hour+"";
                        } else {
                            year = DateUtil.dateTranStr(s.getDay());
                        }
                        double shareUserCount = s.getShare_user_count();
                        double shareCount = s.getShare_count();
                        double newCount = s.getNew_count();

                        if (map.get("name").equals("分享人数")&&map.get("year").equals(year)){
                            map.put("value",shareUserCount);
                            listTmp.set(z,map);
                        }
                        if (map.get("name").equals("分享次数")&&map.get("year").equals(year)){
                            map.put("value",shareCount);
                            listTmp.set(z,map);
                        }
                        if (map.get("name").equals("分享新增")&&map.get("year").equals(year)){
                            map.put("value",newCount);
                            listTmp.set(z,map);
                        }
                    }

            }
        }
        resultMap.put("data", listTmp);
        return resultMap;

    }





    public static List<Map<String, Object>> formatList(List<ShareEntity> shareEntityList) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        if(null != shareEntityList && shareEntityList.size() > 0) {
            for(int i = 0; i < shareEntityList.size(); i++) {
                ShareEntity entity = shareEntityList.get(i);
                String url=null;
                Map<String, Object> map =new LinkedHashMap<>();
                if ( entity.getAvatar_url()==null){
                    url = "";
                }else {
                    url=entity.getAvatar_url();
                }
                String nickName = null;
                if ( entity.getNickname()==null){
                    nickName = "未知";
                }else {
                    nickName=entity.getNickname();
                }
                //次数
               /* if(type.equals("1")) {
                    if (null==entity.getShare_count()){
                        map.put("value", 0);
                    }else {
                        map.put("value", entity.getShare_count());
                    }
                    //回流量
                } else if(type.equals("2")) {
                    if (entity.getStatus()==1){

                    }
                    if (null==entity.getShare_reflux_ratio()){
                        map.put("value", 0);
                    }else {
                        map.put("value", entity.getShare_reflux_ratio());
                    }
                    //分享新增
                } else {
                    if (null==entity.getNew_count()){
                        map.put("value", 0);
                    }else {
                        map.put("value", entity.getNew_count());
                    }
                }*/
                map.put("value", entity.getCount());
                map.put("index", i+1);
                map.put("url", url);
                map.put("nickName", nickName);
                mapList.add(map);
            }
        }

        return mapList;
    }




    public static Map<String, Object> formatInfo(List<ShareEntity> shareEntityList) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != shareEntityList && shareEntityList.size() > 0) {
            for(ShareEntity shareEntity : shareEntityList) {
                String key = shareEntity.getGender();
                if(key.equals("男")) {
                    key = "man";
                } else if(key.equals("女")) {
                    key = "woman";
                } else if(key.equals("未知")) {
                    key = "xx";
                }
                resultMap.put(key, shareEntity.getCount());
            }
        } else {
            resultMap.put("man", 0);
            resultMap.put("woman", 0);
            resultMap.put("xx", 0);
        }
        return resultMap;
    }



}
