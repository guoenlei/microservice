package com.ald.bigdata.modules.ad.assist;

import com.ald.bigdata.common.constants.Constants;
import com.ald.bigdata.common.util.DateUtil;
import com.ald.bigdata.modules.ad.entity.AldAdDataEntity;
import com.ald.bigdata.modules.ad.vo.AldAdAnomalyDataVo;
import com.ald.bigdata.modules.ad.vo.AldAdDataVo;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Map;


public class AdAssist2 {


    public static AldAdDataVo requestHandler(AldAdDataVo aldAdDataVo) {
        String appKey = aldAdDataVo.getAppKey();
        String date = aldAdDataVo.getDate();
        if(StringUtils.isNotBlank(date) && !date.contains(Constants.FLAG_01)) {
//            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
            if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                aldAdDataVo.setTableName1("aldstat_7days_link_summary");
                aldAdDataVo.setTableName2("aldstat_7days_trend_analysis");
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                aldAdDataVo.setTableName1("aldstat_30days_link_summary");
                aldAdDataVo.setTableName2("aldstat_30days_trend_analysis");
            }
        }

        aldAdDataVo = formatDate(aldAdDataVo);
        return aldAdDataVo;
    }


    /**
     * 格式化日期
     * @param aldAdDataVo
     * @return
     */
    public static AldAdDataVo formatDate(AldAdDataVo aldAdDataVo) {
        String date = aldAdDataVo.getDate();
        if(StringUtils.isNotBlank(date) && !date.contains(Constants.FLAG_01)) {
            if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                String yesterday = DateUtil.getYesterday();
                String beferday = DateUtil.getPastDate(7);
                aldAdDataVo.setDate(String.format("%s~%s", beferday, yesterday));
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                String yesterday = DateUtil.getYesterday();
                String beferday = DateUtil.getPastDate(30);
                aldAdDataVo.setDate(String.format("%s~%s", beferday, yesterday));
            }
        }

        return aldAdDataVo;
    }




    /**
     * 格式化图表数据
     * @param entityMap
     * @param aldAdDataVo
     * @return
     */
    public static Map<String, Object> responseAdCountFormat(Map<String, Object> entityMap, AldAdDataVo aldAdDataVo) {
        Map<String, Object> resultEnity = Maps.newConcurrentMap();
        String date = aldAdDataVo.getDate();
        if(date.equals(DateUtil.getYesterday()) || date.equals(DateUtil.getTodayDate()) ||
                date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME) || date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
            resultEnity = formatDataByHour(entityMap, aldAdDataVo);
        } else {
            resultEnity = formatDataByDay(entityMap, aldAdDataVo);
        }
        return resultEnity;
    }


    /**
     * 格式化日期
     * @param aldAdAnomalyDataVo
     * @return
     */
    private static AldAdAnomalyDataVo formatDate(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        String date = aldAdAnomalyDataVo.getDate();
        List<String> dateList = Lists.newArrayList();
        //昨天 7天 30天 转化为昨天的日期
        if(aldAdAnomalyDataVo.getDate().equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME) ||
                aldAdAnomalyDataVo.getDate().equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME) ||
                aldAdAnomalyDataVo.getDate().equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
            dateList.add(DateUtil.getYesterday());
            aldAdAnomalyDataVo.setList(dateList);
            //转化为今天的日期
        } else if(aldAdAnomalyDataVo.getDate().equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
            dateList.add(DateUtil.getTodayDate());
            aldAdAnomalyDataVo.setList(dateList);
        } else if(date.contains(Constants.FLAG_01)) {
            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
            //日期转换为数组
            aldAdAnomalyDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
        }

        return aldAdAnomalyDataVo;
    }

//    private static AldAdAnomalyDataVo setTableName(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
//        if(aldAdAnomalyDataVo.getDate().equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
//            aldAdAnomalyDataVo.setTableName3(ALDSTAT_30DAY_AD_ANOMALY_TIME_ANALYZE);
//            aldAdAnomalyDataVo.setTableName4(ALDSTAT_30DAY_AD_ANOMALY_IP_ANALYZE);
//        } else if(aldAdAnomalyDataVo.getDate().equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
//            aldAdAnomalyDataVo.setTableName3(ALDSTAT_7DAY_AD_ANOMALY_TIME_ANALYZE);
//            aldAdAnomalyDataVo.setTableName4(ALDSTAT_7DAY_AD_ANOMALY_IP_ANALYZE);
//        } else {
//            aldAdAnomalyDataVo.setTableName3(ALDSTAT_AD_ANOMALY_TIME_ANALYZE);
//            aldAdAnomalyDataVo.setTableName4(ALDSTAT_AD_ANOMALY_IP_ANALYZE);
//        }
//
//        return aldAdAnomalyDataVo;
//    }


    private static AldAdDataVo formatProp(AldAdDataVo aldAdDataVo) {
//        String prop = aldAdDataVo.getProp();
//        if(StringUtil.isEmpty(prop)) {
//            return aldAdDataVo;
//        }
//        if(prop.equals("openCount")) {
//            aldAdAnomalyDataVo.setProp("open_Count");
//        } else if(prop.equals("anomalyClickCount")) {
//            aldAdAnomalyDataVo.setProp("anomaly_click_count");
//        } else if(prop.equals("allClickCount")) {
//            aldAdAnomalyDataVo.setProp("all_click_count");
//        } else if(prop.equals("anomalyClickProp")) {
//            aldAdAnomalyDataVo.setProp("anomaly_click_prop");
//        } else if(prop.equals("newAuthUser")) {
//            aldAdAnomalyDataVo.setProp("new_auth_user");
//        } else if(prop.equals("anomalyNewAuthUser")) {
//            aldAdAnomalyDataVo.setProp("anomaly_new_auth_user");
//        } else if(prop.equals("anomalyAuthUserProp")) {
//            aldAdAnomalyDataVo.setProp("anomaly_auth_user_prop");
//        }
//        return aldAdAnomalyDataVo;
        return null;
    }


    private static Map<String, Object> formatDataByHour(Map<String, Object> entityMap, AldAdDataVo aldAdDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdDataEntity> aldAdDataEntityList = (List<AldAdDataEntity>)entityMap.get("data");
        resultMap.put("title", titleMap(entityMap));
        resultMap.put("xAxis", formatHour());
        resultMap.put("series", setxAxisDate(aldAdDataEntityList, aldAdDataVo));
        return resultMap;
    }



    private static Map<String, Object> formatDataByDay(Map<String, Object> entityMap, AldAdDataVo aldAdDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdDataEntity> aldAdDataEntityList = (List<AldAdDataEntity>)entityMap.get("data");
        resultMap.put("title", titleMap(entityMap));
        resultMap.put("xAxis", formatDay(aldAdDataVo));
        resultMap.put("series", setxAxisDate(aldAdDataEntityList, aldAdDataVo));
        return resultMap;
    }



    /**
     * 获取title
     * @param entityMap
     * @return
     */
    private static Map<String, Object> titleMap(Map<String, Object> entityMap) {
        Map<String, Object> titleMap = Maps.newConcurrentMap();
        if(null != entityMap) {
            int keyVal1 = 0;
            int keyVal2 = 0;
            int keyVal3 = 0;
            List<AldAdDataEntity> aldAdDataEntityList = (List<AldAdDataEntity>)entityMap.get("data");
            if(null != aldAdDataEntityList && aldAdDataEntityList.size() > 0) {
                for(AldAdDataEntity aldAdDataEntity : aldAdDataEntityList) {
                    keyVal1 = keyVal1 + NumberUtils.toInt(aldAdDataEntity.getPopuCount() + "");
                    keyVal2 = keyVal2 + NumberUtils.toInt(aldAdDataEntity.getNatuCount() + "");
                    keyVal3 = keyVal3 + NumberUtils.toInt(aldAdDataEntity.getTotalCount() + "");
                }
            }
//            titleMap.put("popuCount", keyVal1);extend
//            titleMap.put("natuCount", keyVal2);naturalquantity
//            titleMap.put("totalCount", keyVal3);total
            titleMap.put("extend", keyVal1);
            titleMap.put("naturalquantity", keyVal2);
            titleMap.put("total", keyVal3);
        }

        return titleMap;
    }



    /**
     * 格式化小时
     * @return
     */
    private static List<String> formatHour() {
        List<String> xAxisMapList = Lists.newArrayList();
        for(int i = 0; i <= 23; i++) {
            String hour = "";
            if(i < 10) {
                hour = String.format("0%s:00 - 0%s:59", i, i);
            } else {
                hour = String.format("%s:00 - %s:59", i, i);
            }
            xAxisMapList.add(hour);
        }

        return xAxisMapList;
    }



    /**
     * 转化天
     * @param aldAdDataVo
     * @return
     */
    private static List<String> formatDay(AldAdDataVo aldAdDataVo) {
        List<String> xAxisMapList = Lists.newArrayList();
        String date = aldAdDataVo.getDate();
        List<String> dateList = null;
        if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
            dateList = DateUtil.getBeforeDate(7);
        } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
            dateList = DateUtil.getBeforeDate(30);
        } else if(date.contains(Constants.FLAG_01)) {
            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
            //日期转换为数组
            dateList = DateUtil.getBetweenDates3(dates[0],dates[1]);
        }
        if(null != dateList && dateList.size() > 0) {
            for(String dateFormat : dateList) {
                String d = dateFormat.replaceAll("-", "/");
                xAxisMapList.add(d);
            }
        }

        return xAxisMapList;
    }


    /**
     * 数据格式化
     * @param aldAdDataEntityList
     * @param aldAdDataVo
     * @return
     */
    private static List<?> setxAxisDate(List<AldAdDataEntity> aldAdDataEntityList, AldAdDataVo aldAdDataVo) {
        String date = aldAdDataVo.getDate();
        //格式化数据
        Map<String, Object> dataFormatMap = dataFormatMap(aldAdDataEntityList, aldAdDataVo);

        List<Map<String, Object>> seriesMapList = Lists.newArrayList();
        Map<String, Object> seriesMap1 = Maps.newConcurrentMap();
        Map<String, Object> seriesMap2 = Maps.newConcurrentMap();
        Map<String, Object> seriesMap3 = Maps.newConcurrentMap();
        seriesMap1.put("name", "推广量");
        seriesMap2.put("name", "自然量");
        seriesMap3.put("name", "总量");
        List<Double> dataList1 = Lists.newArrayList();
        List<Double> dataList2 = Lists.newArrayList();
        List<Double> dataList3 = Lists.newArrayList();

        //今天 昨天 按小时处理
        if(date.equals(DateUtil.getYesterday()) || date.equals(DateUtil.getTodayDate()) ||
                date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME) || date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
            //小时处理
            for(int i = 0; i <= 23; i++) {
                String hour = "";
//                if(i < 10) {
//                    hour = String.format("0%s", i);
//                } else {
                    hour = i+"";
//                }
                Object pupoVal = dataFormatMap.get(hour+"_popu");
                Object natuVal = dataFormatMap.get(hour+"_natu");
                Object totalVal = dataFormatMap.get(hour+"_total");
                dataList1.add(NumberUtils.toDouble(null == pupoVal ? "" : pupoVal+""));
                dataList2.add(NumberUtils.toDouble(null == natuVal ? "" : natuVal+""));
                dataList3.add(NumberUtils.toDouble(null == totalVal ? "" : totalVal+""));
            }
            //最近7天
        } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
            List<String> dateList = DateUtil.getBeforeDate(7);
            for(String d1 :  dateList) {
                Object pupoVal = dataFormatMap.get(d1+"_popu");
                Object natuVal = dataFormatMap.get(d1+"_natu");
                Object totalVal = dataFormatMap.get(d1+"_total");
                dataList1.add(NumberUtils.toDouble(null == pupoVal ? "" : pupoVal+""));
                dataList2.add(NumberUtils.toDouble(null == natuVal ? "" : natuVal+""));
                dataList3.add(NumberUtils.toDouble(null == totalVal ? "" : totalVal+""));
            }
            //最近30天
        } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
            List<String> dateList = DateUtil.getBeforeDate(30);
            for(String d1 :  dateList) {
                Object pupoVal = dataFormatMap.get(d1+"_popu");
                Object natuVal = dataFormatMap.get(d1+"_natu");
                Object totalVal = dataFormatMap.get(d1+"_total");
                dataList1.add(NumberUtils.toDouble(null == pupoVal ? "" : pupoVal+""));
                dataList2.add(NumberUtils.toDouble(null == natuVal ? "" : natuVal+""));
                dataList3.add(NumberUtils.toDouble(null == totalVal ? "" : totalVal+""));
            }
        } else if(date.contains(Constants.FLAG_01)) {
            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
//            //判断日期是否为今天
//            boolean isContains = DateUtil.isContainsToday(dates[0], dates[0]);
//            //区间日期为今天时  转为小时格式
//            if(dates[0].equals(dates[1]) && dates[0].equals(DateUtil.getTodayDate())) {
//                //小时处理
//                for(int i = 0; i <= 23; i++) {
//                    String hour = "";
////                    if(i < 10) {
////                        hour = String.format("0%s", i);
////                    } else {
//                        hour = i+"";
////                    }
//                    Object pupoVal = dataFormatMap.get(hour+"_popu");
//                    Object natuVal = dataFormatMap.get(hour+"_natu");
//                    Object totalVal = dataFormatMap.get(hour+"_total");
//                    dataList1.add(NumberUtils.toDouble(null == pupoVal ? "" : pupoVal+""));
//                    dataList2.add(NumberUtils.toDouble(null == natuVal ? "" : natuVal+""));
//                    dataList3.add(NumberUtils.toDouble(null == totalVal ? "" : totalVal+""));
//                }
//            } else {
                List<String> dateList = DateUtil.getBetweenDates3(dates[0], dates[1]);
                for(String d1 :  dateList) {
                    Object pupoVal = dataFormatMap.get(d1+"_popu");
                    Object natuVal = dataFormatMap.get(d1+"_natu");
                    Object totalVal = dataFormatMap.get(d1+"_total");
                    dataList1.add(NumberUtils.toDouble(null == pupoVal ? "" : pupoVal+""));
                    dataList2.add(NumberUtils.toDouble(null == natuVal ? "" : natuVal+""));
                    dataList3.add(NumberUtils.toDouble(null == totalVal ? "" : totalVal+""));
                }
//            }
        }

        seriesMap1.put("data", dataList1);
        seriesMap2.put("data", dataList2);
        seriesMap3.put("data", dataList3);

        seriesMapList.add(seriesMap1);
        seriesMapList.add(seriesMap2);
        seriesMapList.add(seriesMap3);

        return seriesMapList;
    }


    /**
     * 日期数据格式化
     * @param aldAdDataEntityList
     * @return
     */
    private static Map<String, Object> dataFormatMap(List<AldAdDataEntity> aldAdDataEntityList, AldAdDataVo aldAdDataVo) {
        Map<String, Object> data = Maps.newConcurrentMap();
        String date = aldAdDataVo.getDate();
        if(null != aldAdDataEntityList && aldAdDataEntityList.size() > 0) {
            for(AldAdDataEntity aldAdDataEntity : aldAdDataEntityList) {
                if(null != aldAdDataEntity) {
//                String time = aldAdAnomalyDataEntity.getHour();
                    //今天昨天 计算小时
                    if(date.equals(DateUtil.getYesterday()) || date.equals(DateUtil.getTodayDate()) ||
                            date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME) || date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                        data.put(aldAdDataEntity.getHour() + "_popu", aldAdDataEntity.getPopuCount());
                        data.put(aldAdDataEntity.getHour() + "_natu", aldAdDataEntity.getNatuCount());
                        data.put(aldAdDataEntity.getHour() + "_total", aldAdDataEntity.getTotalCount());
                        //7天 30天
                    } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME) || date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                        data.put(aldAdDataEntity.getDay() + "_popu", aldAdDataEntity.getPopuCount());
                        data.put(aldAdDataEntity.getDay() + "_natu", aldAdDataEntity.getNatuCount());
                        data.put(aldAdDataEntity.getDay() + "_total", aldAdDataEntity.getTotalCount());
                        //指定日期
                    } else if(date.contains(Constants.FLAG_01)) {
                        String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                        //判断日期是否为今天
                        boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
                        if(isContains && dates[0].equals(dates[1])) {
                            //计算小时
                            data.put(aldAdDataEntity.getHour() + "_popu", aldAdDataEntity.getPopuCount());
                            data.put(aldAdDataEntity.getHour() + "_natu", aldAdDataEntity.getNatuCount());
                            data.put(aldAdDataEntity.getHour() + "_total", aldAdDataEntity.getTotalCount());
                        } else {
                            data.put(aldAdDataEntity.getDay() + "_popu", aldAdDataEntity.getPopuCount());
                            data.put(aldAdDataEntity.getDay() + "_natu", aldAdDataEntity.getNatuCount());
                            data.put(aldAdDataEntity.getDay() + "_total", aldAdDataEntity.getTotalCount());
                        }
                    }
                }
            }
        }

        return data;
    }



    public static void main(String[] args) {
        String[] dates = "2018-12-12~2018-12-16".split("~");

        List<?> ds = DateUtil.getBetweenDates3(dates[0], dates[1]);



        System.out.println(formatHour());
    }

}
