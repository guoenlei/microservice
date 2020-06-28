package com.aldwx.app.common.base;

import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 统一数据返回格式
 * @author
 * @description
 * @createTime
 **/
public class BaseAssist {



    public static boolean checkParam() {

        return false;
    }


    /**
     * 格式化列表
     * @return
     */
    public static List<Object> formatList() {


        return null;
    }


    /**
     * 格式化折线图
     * @return
     */
    public static Map<String, Object> formatChart(Map<String, Object> entityMap) {





        return null;
    }



//    /**
//     * 格式化返回折线图数据
//     * @param lists
//     * @param date
//     * @param type
//     * @return
//     */
//    public static Map<String, Object> formatChart(List<List<SceneEntity>> lists, String date, String type) {
//        Map<String, Object> resultMap = Maps.newConcurrentMap();
//        List<Map<String, Object>> listMap = Lists.newArrayList();
//        if(null != lists && lists.size() > 0) {
//            for(List<SceneEntity> sceneEntityList : lists) {
//                if(null != sceneEntityList && sceneEntityList.size() > 0) {
//                    for(SceneEntity s : sceneEntityList) {
//                        Map<String, Object> map = Maps.newConcurrentMap();
//                        if(null != s) {
//                            map.put("name", s.getSceneName());
//                            String year = null;
//                            if(date.equals("1") || date.equals("2")) {
//                                year = s.getHour();
//                            } else {
//                                year = s.getDay();
//                            }
//                            map.put("year", year);
//                        }
//                        double value = 0.0;
//                        if(type.equals("1")) {
//                            value = s.getSceneNewerForApp();
//                        } else {
//                            value = s.getSceneVisitorCount();
//                        }
//                        map.put("value", value);
//                        listMap.add(map);
//                    }
//                }
//            }
//        }
//
//        resultMap.put("data", listMap);
//
//        return resultMap;
//    }



}
