package com.aldwx.app.modules.general.assist;

import com.aldwx.app.common.base.BaseAssist;
import com.aldwx.app.common.util.CurrencyVo;
import com.aldwx.app.common.util.DateUtil;
import com.aldwx.app.common.util.StringUtil;
import com.aldwx.app.modules.trend.entity.TrendEntity;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 辅助类 格式化返回的数据
 *
 * @author
 * @description
 * @createTime
 **/
public class GeneralAssist extends BaseAssist {


    /**
     * 概况 - 首页 - 今日概况
     *
     * @param entityPair
     * @return
     */
    public static Map<String, Object> todayGeneralData(Pair<List<TrendEntity>, List<TrendEntity>> entityPair, CurrencyVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        Map<String, Object> resultYes = Maps.newConcurrentMap();
        Map<String, Object> resultTod = Maps.newConcurrentMap();
        if (null != entityPair.getLeft() && entityPair.getLeft().size() > 0) {
            TrendEntity entity = entityPair.getLeft().get(0);
            resultTod.put("newComerCount", entity.getNewComerCount());
            resultTod.put("visitorCount", entity.getVisitorCount());
            resultTod.put("totalPageCount", entity.getTotalPageCount());
            resultTod.put("openCount", entity.getOpenCount());
            resultTod.put("secondaryAvgStayTime", StringUtil.formatTime(entity.getSecondaryAvgStayTime()));
            resultTod.put("bounceRate", StringUtil.formatPercent2(entity.getBounceRate()));
        } else {
            resultTod.put("newComerCount", "0");
            resultTod.put("visitorCount", "0");
            resultTod.put("openCount", "0");
            resultTod.put("totalPageCount", "0");
            resultTod.put("secondaryAvgStayTime", "00:00:00");
            resultTod.put("bounceRate", "0%");
        }
        if (null != entityPair.getRight() && entityPair.getRight().size() > 0) {
            TrendEntity entity = entityPair.getRight().get(0);
            resultYes.put("newComerCount", entity.getNewComerCount());
            resultYes.put("visitorCount", entity.getVisitorCount());
            resultYes.put("openCount", entity.getOpenCount());
            resultYes.put("totalPageCount", entity.getTotalPageCount());
            resultYes.put("secondaryAvgStayTime", StringUtil.formatTime(entity.getSecondaryAvgStayTime()));
            resultYes.put("bounceRate", StringUtil.formatPercent2(entity.getBounceRate()));
        } else {
            resultYes.put("newComerCount", "0");
            resultYes.put("visitorCount", "0");
            resultYes.put("openCount", "0");
            resultYes.put("totalPageCount", "0");
            resultYes.put("secondaryAvgStayTime", "00:00:00");
            resultYes.put("bounceRate", "0%");
        }
        resultMap.put("today", resultTod);
        resultMap.put("yesterday", resultYes);
        return resultMap;
    }


    /**
     * 概况 - 首页 基础数据 - 折线图
     *
     * @param entityPair
     * @param date
     * @param type
     * @return
     */
    public static Map<String, Object> basicChart(Map<String, List<TrendEntity>> entityPair, String date, String type, CurrencyVo vo) {
        Map<String, Object> reulstMap = Maps.newConcurrentMap();
        List<Map<String, String>> mapList = null;
        List<Map<String, String>> mapList2 = null;
        List<Map<String, String>> mapAll = new ArrayList<>();
        List<TrendEntity> today = entityPair.get("today");
        List<TrendEntity> yesterday = entityPair.get("yesterday");
        List<TrendEntity> count = entityPair.get("count");
        //日期为今天时  需要返回2个折线图的数据
        if (date.equals("1")) {
            mapList = parseTypeMap(today, date, type, "今日", vo);

            mapList = mapList.subList(0, DateUtil.getNowHour());
            mapList2 = parseTypeMap(yesterday, date, type, "昨日", vo);
            mapAll.addAll(mapList2);
            mapAll.addAll(mapList);
            reulstMap.put("data", mapAll);
        } else {
            String name = "";
            if (date.equals("2")) {
                name = "昨日";
            } else if (date.equals("3")) {
                name = "7日";
            } else if (date.equals("4")) {
                name = "30日";
            }
            mapList = parseTypeMap(today, date, type, name, vo);
            reulstMap.put("data", mapList);
        }
        if (type.equals("1")) {
            if (isNull(count.get(0).getNewComerCount())) {
                reulstMap.put("count", "0");
            } else {
                reulstMap.put("count", count.get(0).getNewComerCount() + "");
            }
            //访问人数
        } else if (type.equals("2")) {
            if (isNull(count.get(0).getVisitorCount())) {
                reulstMap.put("count", "0");
            } else {
                reulstMap.put("count", count.get(0).getVisitorCount() + "");
            }
            //打开次数
        } else if (type.equals("3")) {
            if (isNull(count.get(0).getOpenCount())) {
                reulstMap.put("count", "0");
            } else {
                reulstMap.put("count", count.get(0).getOpenCount() + "");
            }
            //访问次数
        } else if (type.equals("4")) {
            if (isNull(count.get(0).getTotalPageCount())) {
                reulstMap.put("count", "0");
            } else {
                reulstMap.put("count", count.get(0).getTotalPageCount() + "");
            }
            //次均停留时长
        } else if (type.equals("5")) {
            if (isNull(count.get(0).getSecondaryAvgStayTime())) {
                reulstMap.put("count", "00:00:00");
            } else {
                reulstMap.put("count", StringUtil.formatTime(count.get(0).getSecondaryAvgStayTime()) + "");
            }
            //跳出率
        } else if (type.equals("6")) {

            if (isNull(count.get(0).getBounceRate())) {
                reulstMap.put("count", "0%");
            } else {
                reulstMap.put("count", StringUtil.formatPercent2(count.get(0).getBounceRate()) + "");
            }
        }
        return reulstMap;
    }


    /**
     * 概况 - 首页 基础数据 - 折线图 - 改需求后
     *
     * @param entityPair
     * @param date
     * @return
     */
    public static Map<String, Object> basicChartNew(Map<String, List<TrendEntity>> entityPair, String date, CurrencyVo vo, String type) {
        Map<String, Object> reulstMap = Maps.newConcurrentMap();
        List<Map<String, String>> mapList = null;
        List<Map<String, String>> mapList2 = null;
        List<Map<String, String>> mapAll = new ArrayList<>();
        List<TrendEntity> today = entityPair.get("today");
        List<TrendEntity> yesterday = entityPair.get("yesterday");
        List<TrendEntity> count = entityPair.get("count");
        //日期为今天时  需要返回2个折线图的数据
        if (date.equals("1")) {
            mapList = parseTypeMapNew(today, date, "今日", vo, type);

            mapList = mapList.subList(0, DateUtil.getNowHour());
            mapList2 = parseTypeMapNew(yesterday, date, "昨日", vo, type);
            mapAll.addAll(mapList2);
            mapAll.addAll(mapList);
            reulstMap.put("data", mapAll);
        } else {
            String name = "";
            if (date.equals("2")) {
                name = "昨日";
            } else if (date.equals("3")) {
                name = "7日";
            } else if (date.equals("4")) {
                name = "30日";
            }
            mapList = parseTypeMapNew(today, date, name, vo, type);
            reulstMap.put("data", mapList);
        }

        // 黨count列表不為空時再執行，防止count報IndexOutOfBounds
        if (count != null && count.size() > 0) {
            // 新用户数
            if (isNull(count.get(0).getNewComerCount())) {
                reulstMap.put("countNewUser", "0");
            } else {
                reulstMap.put("countNewUser", count.get(0).getNewComerCount() + "");
            }
            // 访问人数
            if (isNull(count.get(0).getVisitorCount())) {
                reulstMap.put("countUserVisitors", "0");
            } else {
                reulstMap.put("countUserVisitors", count.get(0).getVisitorCount() + "");
            }
            //打开次数
            if (isNull(count.get(0).getOpenCount())) {
                reulstMap.put("countOpen", "0");
            } else {
                reulstMap.put("countOpen", count.get(0).getOpenCount() + "");
            }
            //访问次数
            if (isNull(count.get(0).getTotalPageCount())) {
                reulstMap.put("countVisitors", "0");
            } else {
                reulstMap.put("countVisitors", count.get(0).getTotalPageCount() + "");
            }
        } else {
            reulstMap.put("countNewUser", "0");
            reulstMap.put("countUserVisitors", "0");
            reulstMap.put("countOpen", "0");
            reulstMap.put("countVisitors", "0");
        }
        return reulstMap;

        /*if(type.equals("1")) {
            if (isNull(count.get(0).getNewComerCount())){
                reulstMap.put("count", "0");
            }else {
                reulstMap.put("count", count.get(0).getNewComerCount()+"");
            }
            //访问人数
        } else if(type.equals("2")) {
            if (isNull(count.get(0).getVisitorCount())){
                reulstMap.put("count", "0");
            }else {
                reulstMap.put("count", count.get(0).getVisitorCount()+"");
            }
            //打开次数
        } else if(type.equals("3")) {
            if (isNull(count.get(0).getOpenCount())){
                reulstMap.put("count", "0");
            }else {
                reulstMap.put("count", count.get(0).getOpenCount()+"");
            }
            //访问次数
        } else if(type.equals("4")) {
            if (isNull(count.get(0).getTotalPageCount())){
                reulstMap.put("count", "0");
            }else {
                reulstMap.put("count", count.get(0).getTotalPageCount()+"");
            }
            //次均停留时长
        } else if(type.equals("5")) {
            if (isNull(count.get(0).getSecondaryAvgStayTime())){
                reulstMap.put("count", "00:00:00");
            }else {
                reulstMap.put("count", StringUtil.formatTime(count.get(0).getSecondaryAvgStayTime())+"");
            }
            //跳出率
        } else if(type.equals("6")) {

            if (isNull(count.get(0).getBounceRate())){
                reulstMap.put("count", "0%");
            }else {
                reulstMap.put("count", StringUtil.formatPercent2(count.get(0).getBounceRate())+"");
            }
        }*/
    }

    /**
     * 处理返回数据格式 - 改需求后
     *
     * @param entityList
     * @param date
     * @return
     */
    private static List<Map<String, String>> parseTypeMapNew(List<TrendEntity> entityList, String date, String name, CurrencyVo vo, String type) {
        List<Map<String, String>> listTemp = new ArrayList<>();
        if (date.equals("1") || date.equals("2")) {
            for (int i = 0; i < 24; i++) {
                Map map = new HashMap();
                map.put("name", name);
                map.put("year", i + "");
                map.put("value", 0);
                if (type.equals("5")) {
                    map.put("value", "0");
                } else if (type.equals("6")) {
                    map.put("value", "0");
                } else {
                    map.put("value", "0");
                }
                listTemp.add(map);
            }
        } else {
            List<String> listTime = vo.getListDate();
            for (int i = 0; i < listTime.size(); i++) {
                Map map = new HashMap();
                map.put("name", name);
                map.put("year", listTime.get(i));
                map.put("value", "0");
                if (type.equals("5")) {
                    map.put("value", "0");
                } else if (type.equals("6")) {
                    map.put("value", "0");
                } else {
                    map.put("value", "0");
                }
                listTemp.add(map);
            }
        }
        if (null != entityList && entityList.size() > 0) {
            for (TrendEntity entity : entityList) {
                for (int i = 0; i < listTemp.size(); i++) {
                    Map<String, String> mapDay = listTemp.get(i);
                    if (date.equals("1") || date.equals("2")) {
                        //获取小时
                        String hour = entity.getHour() + "";
                        if (hour.equals(mapDay.get("year"))) {
                            Map<String, String> map = getMap(mapDay, type, entity);
                            listTemp.set(i, map);
                        }
                    } else {
                        String day = entity.getDay().substring(0, 10);
                        if (day.trim().equals(mapDay.get("year").trim())) {
                            Map<String, String> map = getMap(mapDay, type, entity);
                            listTemp.set(i, map);
                        }
                    }
                }
            }
        }
        return listTemp;
    }

    /**
     * 处理返回数据格式
     *
     * @param entityList
     * @param date
     * @param type
     * @return
     */
    private static List<Map<String, String>> parseTypeMap(List<TrendEntity> entityList, String date, String name, String type, CurrencyVo vo) {
        List<Map<String, String>> listTemp = new ArrayList<>();
        if (date.equals("1") || date.equals("2")) {
            for (int i = 0; i < 24; i++) {
                Map map = new HashMap();
                map.put("name", name);
                map.put("year", i + "");
                if (type.equals("5")) {
                    map.put("value", "0");
                } else if (type.equals("6")) {
                    map.put("value", "0");
                } else {
                    map.put("value", "0");
                }
                listTemp.add(map);
            }
        } else {
            List<String> listTime = vo.getListDate();
            for (int i = 0; i < listTime.size(); i++) {
                Map map = new HashMap();
                map.put("name", name);
                map.put("year", listTime.get(i));
                if (type.equals("5")) {
                    map.put("value", "0");
                } else if (type.equals("6")) {
                    map.put("value", "0");
                } else {
                    map.put("value", "0");
                }
                listTemp.add(map);
            }
        }
        if (null != entityList && entityList.size() > 0) {
            for (TrendEntity entity : entityList) {
                for (int i = 0; i < listTemp.size(); i++) {
                    Map<String, String> mapDay = listTemp.get(i);
                    if (date.equals("1") || date.equals("2")) {
                        //获取小时
                        String hour = entity.getHour() + "";
                        if (hour.equals(mapDay.get("year"))) {
                            Map<String, String> map = getMap(mapDay, type, entity);
                            listTemp.set(i, map);
                        }
                    } else {
                        String day = entity.getDay().substring(0, 10);
                        if (day.trim().equals(mapDay.get("year").trim())) {
                            Map<String, String> map = getMap(mapDay, type, entity);
                            listTemp.set(i, map);
                        }
                    }
                }
            }
        }
        return listTemp;
    }

    public static Map<String, String> getMapNew(Map<String, String> mapDay, TrendEntity entity) {
//        if (type.equals("1")) {
        mapDay.put("value", entity.getNewComerCount() + "");
        //访问人数
//        } else if (type.equals("2")) {
        mapDay.put("value", entity.getVisitorCount() + "");
        //打开次数
//        } else if (type.equals("3")) {
        mapDay.put("value", entity.getOpenCount() + "");
        //访问次数
//        } else if (type.equals("4")) {
        mapDay.put("value", entity.getTotalPageCount() + "");
        //次均停留时长
//        } else if (type.equals("5")) {
//            mapDay.put("value", StringUtil.formatPercent3(entity.getSecondaryAvgStayTime()) + "");
        //跳出率
//        } else if (type.equals("6")) {
//            mapDay.put("value", StringUtil.formatPercent3(entity.getBounceRate()) + "");
//        }
        return mapDay;
    }

    public static Map<String, String> getMap(Map<String, String> mapDay, String type, TrendEntity entity) {
        if (type.equals("1")) {
            mapDay.put("value", entity.getNewComerCount() + "");
            //访问人数
        } else if (type.equals("2")) {
            mapDay.put("value", entity.getVisitorCount() + "");
            //打开次数
        } else if (type.equals("3")) {
            mapDay.put("value", entity.getOpenCount() + "");
            //访问次数
        } else if (type.equals("4")) {
            mapDay.put("value", entity.getTotalPageCount() + "");
            //次均停留时长
        } else if (type.equals("5")) {
            mapDay.put("value", StringUtil.formatPercent3(entity.getSecondaryAvgStayTime()) + "");
            //跳出率
        } else if (type.equals("6")) {
            mapDay.put("value", StringUtil.formatPercent3(entity.getBounceRate()) + "");
        }
        return mapDay;
    }

    public static Boolean isNull(Object object) {
        Boolean a = true;
        if (object == null) {
            a = true;
        } else {
            a = false;
        }
        return a;
    }


}
