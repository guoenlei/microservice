package com.aldwx.bigdata.modules.ad.assist;

import com.aldwx.bigdata.common.constants.Constants;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.modules.ad.entity.AldAdAnomalyDataEntity;
import com.aldwx.bigdata.modules.ad.vo.AldAdAnomalyDataVo;
import com.aldwx.bigdata.modules.ad.vo.AldAdDataVo;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class AdAssist {

    private static final String ALDSTAT_AD_ANOMALY_TIME_ANALYZE = "aldstat_ad_anomaly_time_analyze";
    private static final String ALDSTAT_AD_ANOMALY_IP_ANALYZE = "aldstat_ad_anomaly_ip_analyze";
    private static final String ALDSTAT_AD_ANOMALY_AUTH_ANALYZE = "aldstat_ad_anomaly_auth_analyze";

    private static final String ALDSTAT_7DAY_AD_ANOMALY_TIME_ANALYZE = "aldstat_7days_ad_anomaly_time_analyze";
    private static final String ALDSTAT_7DAY_AD_ANOMALY_IP_ANALYZE = "aldstat_7days_ad_anomaly_ip_analyze";
    private static final String ALDSTAT_7DAY_AD_ANOMALY_AUTH_ANALYZE = "aldstat_7days_ad_anomaly_auth_analyze";

    private static final String ALDSTAT_30DAY_AD_ANOMALY_TIME_ANALYZE = "aldstat_30days_ad_anomaly_time_analyze";
    private static final String ALDSTAT_30DAY_AD_ANOMALY_IP_ANALYZE = "aldstat_30days_ad_anomaly_ip_analyze";
    private static final String ALDSTAT_30DAY_AD_ANOMALY_AUTH_ANALYZE = "aldstat_30days_ad_anomaly_auth_analyze";

    //总览
    private static final String ALDSTAT_AD_ANOMALY_ALL_ANALYZE = "aldstat_ad_anomaly_all_analyze";
    private static final String ALDSTAT_7DAY_AD_ANOMALY_ALL_ANALYZE = "aldstat_7days_ad_anomaly_all_analyze";
    private static final String ALDSTAT_30DAY_AD_ANOMALY_ALL_ANALYZE = "aldstat_30days_ad_anomaly_all_analyze";


    private static final String ANOMALY_ALL_MODULE = "1";       //防护总览
    private static final String ANOMALY_TIME_MODULE = "2";      //异常时间差
    private static final String ANOMALY_IP_MODULE = "3";        //异常IP
    private static final String ANOMALY_AUTH_MODULE = "4";        //异常授权

    /**
     * 请求处理
     * @param aldAdAnomalyDataVo
     * @return
     */
    public static AldAdAnomalyDataVo requestHandler(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        if(null != aldAdAnomalyDataVo) {
            //用于统计总异常次数
            aldAdAnomalyDataVo = setTableName(aldAdAnomalyDataVo);
            aldAdAnomalyDataVo = formatDate(aldAdAnomalyDataVo);
            aldAdAnomalyDataVo = formatProp(aldAdAnomalyDataVo);
            String appKey = aldAdAnomalyDataVo.getAppKey();
            String module = aldAdAnomalyDataVo.getModule();
            String date = aldAdAnomalyDataVo.getDate();
            String order = aldAdAnomalyDataVo.getOrder();
            String prop = aldAdAnomalyDataVo.getProp();
            //今天
            if(StringUtil.isNotEmpty(date)) {
                if(StringUtils.isNotBlank(module)) {
                    if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                        //总览
                        if(module.equals(ANOMALY_ALL_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_AD_ANOMALY_ALL_ANALYZE);
                        } else if(module.equals(ANOMALY_TIME_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_AD_ANOMALY_TIME_ANALYZE);
                        } else if(module.equals(ANOMALY_IP_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_AD_ANOMALY_IP_ANALYZE);
                        } else if(module.equals(ANOMALY_AUTH_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_AD_ANOMALY_AUTH_ANALYZE);
                        }
                        //昨天
                    } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                        //总览
                        if(module.equals(ANOMALY_ALL_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_AD_ANOMALY_ALL_ANALYZE);
                        } else if(module.equals(ANOMALY_TIME_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_AD_ANOMALY_TIME_ANALYZE);
                        } else if(module.equals(ANOMALY_IP_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_AD_ANOMALY_IP_ANALYZE);
                        } else if(module.equals(ANOMALY_AUTH_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_AD_ANOMALY_AUTH_ANALYZE);
                        }
                        //最近7天
                    } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                        if(module.equals(ANOMALY_ALL_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_7DAY_AD_ANOMALY_ALL_ANALYZE);
                        } else if(module.equals(ANOMALY_TIME_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_7DAY_AD_ANOMALY_TIME_ANALYZE);
                        } else if(module.equals(ANOMALY_IP_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_7DAY_AD_ANOMALY_IP_ANALYZE);
                        } else if(module.equals(ANOMALY_AUTH_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_7DAY_AD_ANOMALY_AUTH_ANALYZE);
                        }
                        //最近30
                    } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                        if(module.equals(ANOMALY_ALL_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_30DAY_AD_ANOMALY_ALL_ANALYZE);
                        } else if(module.equals(ANOMALY_TIME_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_30DAY_AD_ANOMALY_TIME_ANALYZE);
                        } else if(module.equals(ANOMALY_IP_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_30DAY_AD_ANOMALY_IP_ANALYZE);
                        } else if(module.equals(ANOMALY_AUTH_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_30DAY_AD_ANOMALY_AUTH_ANALYZE);
                        }
                        //指定时间段
                    } else if(date.contains(Constants.FLAG_01)) {
//                    String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
//                    //日期转换为数组
//                    aldAdAnomalyDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
                        if(module.equals(ANOMALY_ALL_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_AD_ANOMALY_ALL_ANALYZE);
                        } else if(module.equals(ANOMALY_TIME_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_AD_ANOMALY_TIME_ANALYZE);
                        } else if(module.equals(ANOMALY_IP_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_AD_ANOMALY_IP_ANALYZE);
                        } else if(module.equals(ANOMALY_AUTH_MODULE)) {
                            aldAdAnomalyDataVo.setTableName1(ALDSTAT_AD_ANOMALY_AUTH_ANALYZE);
                        }
                    }
                }
            }
            if(StringUtils.isNotBlank(prop)) {
                if(prop.toUpperCase().contains("ASC")) {
                    prop = "ASC";
                } else {
                    prop = "DESC";
                }
            }
        }
        return aldAdAnomalyDataVo;
    }


    /**
     * 格式化图表数据
     * @param entityMap
     * @param aldAdAnomalyDataVo
     * @return
     */
    public static Map<String, Object> responseCheatFormat(Map<String, Object> entityMap, AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        Map<String, Object> resultEnity = Maps.newConcurrentMap();
        String date = aldAdAnomalyDataVo.getDate();
        if(date.contains(Constants.FLAG_01)) {
            date = date.replaceAll("\\s*", "");
            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
            if(dates[0].equals(dates[1])) {
                date = dates[0];
            }
            resultEnity = formatAnomalyDataByDay(entityMap, aldAdAnomalyDataVo);
        } else {
            if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME) || date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                resultEnity = formatAnomalyDataByDay(entityMap, aldAdAnomalyDataVo);
            } else {
                resultEnity = formatAnomalyDataByDay(entityMap, aldAdAnomalyDataVo);
            }
        }

        return resultEnity;
    }


//    /**
//     * 格式化日期
//     * @param aldAdAnomalyDataVo
//     * @return
//     */
//    private static AldAdAnomalyDataVo formatDate(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
//        String date = aldAdAnomalyDataVo.getDate();
//        List<String> dateList = Lists.newArrayList();
//        //昨天 7天 30天 转化为昨天的日期
//        if(aldAdAnomalyDataVo.getDate().equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME) ||
//                aldAdAnomalyDataVo.getDate().equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
//            dateList.add(DateUtil.getYesterday());
//            aldAdAnomalyDataVo.setList(dateList);
//            //昨天
//        } else if(aldAdAnomalyDataVo.getDate().equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
//            dateList.add(DateUtil.getTodayDate());
//            aldAdAnomalyDataVo.setList(dateList);
//            //转化为今天的日期
//        } else if(aldAdAnomalyDataVo.getDate().equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
//            dateList.add(DateUtil.getTodayDate());
//            aldAdAnomalyDataVo.setList(dateList);
//        } else if(date.contains(Constants.FLAG_01)) {
//            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
//            //日期转换为数组
//            aldAdAnomalyDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
//        }
//
//        return aldAdAnomalyDataVo;
//    }


    /**
     * 格式化日期
     * @param aldAdDataVo
     * @return
     */
    public static AldAdAnomalyDataVo formatDate(AldAdAnomalyDataVo aldAdDataVo) {
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

    private static AldAdAnomalyDataVo setTableName(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        if(aldAdAnomalyDataVo.getDate().equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
            aldAdAnomalyDataVo.setTableName3(ALDSTAT_30DAY_AD_ANOMALY_TIME_ANALYZE);
            aldAdAnomalyDataVo.setTableName4(ALDSTAT_30DAY_AD_ANOMALY_IP_ANALYZE);
        } else if(aldAdAnomalyDataVo.getDate().equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
            aldAdAnomalyDataVo.setTableName3(ALDSTAT_7DAY_AD_ANOMALY_TIME_ANALYZE);
            aldAdAnomalyDataVo.setTableName4(ALDSTAT_7DAY_AD_ANOMALY_IP_ANALYZE);
        } else {
            aldAdAnomalyDataVo.setTableName3(ALDSTAT_AD_ANOMALY_TIME_ANALYZE);
            aldAdAnomalyDataVo.setTableName4(ALDSTAT_AD_ANOMALY_IP_ANALYZE);
        }

        return aldAdAnomalyDataVo;
    }


    private static AldAdAnomalyDataVo formatProp(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        String prop = aldAdAnomalyDataVo.getProp();
        if(StringUtil.isEmpty(prop)) {
            return aldAdAnomalyDataVo;
        }
        if(prop.equals("openCount")) {
            aldAdAnomalyDataVo.setProp("open_Count");
        } else if(prop.equals("anomalyClickCount")) {
            aldAdAnomalyDataVo.setProp("anomaly_click_count");
        } else if(prop.equals("allClickCount")) {
            aldAdAnomalyDataVo.setProp("all_click_count");
        } else if(prop.equals("anomalyClickProp")) {
            aldAdAnomalyDataVo.setProp("anomaly_click_prop");
        } else if(prop.equals("newAuthUser")) {
            aldAdAnomalyDataVo.setProp("new_auth_user");
        } else if(prop.equals("anomalyNewAuthUser")) {
            aldAdAnomalyDataVo.setProp("anomaly_new_auth_user");
        } else if(prop.equals("anomalyAuthUserProp")) {
            aldAdAnomalyDataVo.setProp("anomaly_auth_user_prop");
        }
        return aldAdAnomalyDataVo;
    }


    private static Map<String, Object> formatAnomalyDataByHour(Map<String, Object> entityMap,
                                                               AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdAnomalyDataEntity> aldAdAnomalyDataEntityList = (List<AldAdAnomalyDataEntity>)entityMap.get("data");
        resultMap.put("title", titleMap(entityMap));
        resultMap.put("xAxis", formatHour());
        resultMap.put("series", setxAxisDate(aldAdAnomalyDataEntityList, aldAdAnomalyDataVo));
        return resultMap;
    }

    private static Map<String, Object> formatAnomalyDataByDay(Map<String, Object> entityMap,
                                                              AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdAnomalyDataEntity> aldAdAnomalyDataEntityList = (List<AldAdAnomalyDataEntity>)entityMap.get("data");
        resultMap.put("title", titleMap(entityMap));
        resultMap.put("xAxis", formatDay(aldAdAnomalyDataVo));
        resultMap.put("series", setxAxisDate(aldAdAnomalyDataEntityList, aldAdAnomalyDataVo));

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
//            Object key1 = entityMap.get("key1");
//            Object key2 = entityMap.get("key2");
//            titleMap.put("异常新增授权用户", null == key1 ? 0 : key1);
//            titleMap.put("异常打开次数", null == key2 ? 0 : key2);
            int keyVal1 = 0;
            int keyVal2 = 0;
            List<AldAdAnomalyDataEntity> aldAdAnomalyDataEntityList = (List<AldAdAnomalyDataEntity>)entityMap.get("data");
            if(null != aldAdAnomalyDataEntityList && aldAdAnomalyDataEntityList.size() > 0) {
                for(AldAdAnomalyDataEntity aldAdAnomalyDataEntity : aldAdAnomalyDataEntityList) {
                    keyVal1 = keyVal1 + NumberUtils.toInt(aldAdAnomalyDataEntity.getAnomalyNewAuthUser());
                    keyVal2 = keyVal2 + NumberUtils.toInt(aldAdAnomalyDataEntity.getAnomalyClickCount());
                }
            }
            titleMap.put("anomalyAuth", keyVal1);
            titleMap.put("anomalyClick", keyVal2);
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
     * @param aldAdAnomalyDataVo
     * @return
     */
    private static List<String> formatDay(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        List<String> xAxisMapList = Lists.newArrayList();
        String date = aldAdAnomalyDataVo.getDate();
        List<String> dateList = null;
        if(date.contains(Constants.FLAG_01)) {
            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
            if(dates[0].equals(dates[1])) {
                dateList = Lists.newArrayList();
                dateList.add(dates[0]);
            } else {
                //日期转换为数组
                dateList = DateUtil.getBetweenDates3(dates[0],dates[1]);
            }
        } else {
            if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                String yesterday = DateUtil.getYesterday();
                String beferday = DateUtil.getPastDate(7);
                date = String.format("%s~%s", beferday, yesterday);
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
//                aldAdAnomalyDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
                dateList = DateUtil.getBetweenDates3(dates[0],dates[1]);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                String yesterday = DateUtil.getYesterday();
                String beferday = DateUtil.getPastDate(30);
                date = String.format("%s~%s", beferday, yesterday);
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
//                aldAdAnomalyDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
                dateList = DateUtil.getBetweenDates3(dates[0],dates[1]);
            } else if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                dateList = Lists.newArrayList();
                dateList.add(DateUtil.getTodayDate());
            } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                dateList = Lists.newArrayList();
                dateList.add(DateUtil.getYesterday());
            }
        }
//        if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
//            dateList = DateUtil.getBeforeDate(7);
//        } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
//            dateList = DateUtil.getBeforeDate(30);
//        } else if(date.contains(Constants.FLAG_01)) {
//            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
//            //日期转换为数组
//            dateList = DateUtil.getBetweenDates3(dates[0],dates[1]);
//        }
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
     * @param aldAdAnomalyDataEntityList
     * @param aldAdAnomalyDataVo
     * @return
     */
    private static List<?> setxAxisDate(List<AldAdAnomalyDataEntity> aldAdAnomalyDataEntityList, AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        String date = aldAdAnomalyDataVo.getDate();
        //格式化数据
        Map<String, Object> dataFormatMap = dataFormatMap(aldAdAnomalyDataEntityList, aldAdAnomalyDataVo);

        List<Map<String, Object>> seriesMapList = Lists.newArrayList();
        Map<String, Object> seriesMap1 = Maps.newConcurrentMap();
        Map<String, Object> seriesMap2 = Maps.newConcurrentMap();
        seriesMap1.put("name", "异常新增授权用户");
        seriesMap2.put("name", "异常点击次数");
        List<Double> dataList1 = Lists.newArrayList();
        List<Double> dataList2 = Lists.newArrayList();

        if(date.contains(Constants.FLAG_01)) {
            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
            //区间日期为今天时  转为小时格式
            if(dates[0].equals(dates[1]) && dates[0].equals(DateUtil.getTodayDate())) {
                //小时处理
                for(int i = 0; i <= 23; i++) {
                    String hour = "";
                    if(i < 10) {
                        hour = String.format("0%s", i);
                    }
                    Object authVal = dataFormatMap.get(hour+"_auth");
                    Object clickVal = dataFormatMap.get(hour+"_click");
                    dataList1.add(NumberUtils.toDouble(null == authVal ? "" : authVal+""));
                    dataList2.add(NumberUtils.toDouble(null == authVal ? "" : clickVal+""));
                }
            } else {
                List<String> dateList = DateUtil.getBetweenDates3(dates[0], dates[1]);
                for(String d1 :  dateList) {
                    Object authVal = dataFormatMap.get(d1+"_auth");
                    Object clickVal = dataFormatMap.get(d1+"_click");
                    dataList1.add(NumberUtils.toDouble(null == authVal ? "" : authVal+""));
                    dataList2.add(NumberUtils.toDouble(null == authVal ? "" : clickVal+""));
                }
            }
        } else {
            //今天 昨天 按小时处理
            if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                //小时处理
                for(int i = 0; i <= 23; i++) {
                    String hour = "";
                    if(i < 10) {
                        hour = String.format("0%s", i);
                    }  else {
                        hour = i+"";
                    }
                    Object authVal = dataFormatMap.get(hour+"_auth");
                    Object clickVal = dataFormatMap.get(hour+"_click");
                    dataList1.add(NumberUtils.toDouble(null == authVal ? "" : authVal+""));
                    dataList2.add(NumberUtils.toDouble(null == authVal ? "" : clickVal+""));
                }
                //昨天
            } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                Object authVal = dataFormatMap.get(DateUtil.getYesterday()+"_auth");
                Object clickVal = dataFormatMap.get(DateUtil.getYesterday()+"_click");
                dataList1.add(NumberUtils.toDouble(null == authVal ? "" : authVal+""));
                dataList2.add(NumberUtils.toDouble(null == authVal ? "" : clickVal+""));
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                List<String> dateList = DateUtil.getBeforeDate(7);
                for(String d1 :  dateList) {
                    Object authVal = dataFormatMap.get(d1+"_auth");
                    Object clickVal = dataFormatMap.get(d1+"_click");
                    dataList1.add(NumberUtils.toDouble(null == authVal ? "" : authVal+""));
                    dataList2.add(NumberUtils.toDouble(null == authVal ? "" : clickVal+""));
                }
                //最近30天
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                List<String> dateList = DateUtil.getBeforeDate(30);
                for(String d1 :  dateList) {
                    Object authVal = dataFormatMap.get(d1+"_auth");
                    Object clickVal = dataFormatMap.get(d1+"_click");
                    dataList1.add(NumberUtils.toDouble(null == authVal ? "" : authVal+""));
                    dataList2.add(NumberUtils.toDouble(null == authVal ? "" : clickVal+""));
                }
            }
        }

        if(date.contains(Constants.FLAG_01)) {
            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
            if((dates[0].equals(dates[1]) && dates[0].equals(DateUtil.getTodayDate()))) {
                List<Double> dataList11 = Lists.newArrayList();
                List<Double> dataList21 = Lists.newArrayList();
                seriesMap1.put("data", dataList11);
                seriesMap2.put("data", dataList21);
            } else {
                seriesMap1.put("data", dataList1);
                seriesMap2.put("data", dataList2);
            }
        } else {
            if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                List<Double> dataList11 = Lists.newArrayList();
                List<Double> dataList21 = Lists.newArrayList();
                seriesMap1.put("data", dataList11);
                seriesMap2.put("data", dataList21);
            } else {
                seriesMap1.put("data", dataList1);
                seriesMap2.put("data", dataList2);
            }
        }

        seriesMapList.add(seriesMap1);
        seriesMapList.add(seriesMap2);

        return seriesMapList;
    }


    /**
     * 日期数据格式化
     * @param aldAdAnomalyDataEntityList
     * @return
     */
    private static Map<String, Object> dataFormatMap(List<AldAdAnomalyDataEntity> aldAdAnomalyDataEntityList,
                                              AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        Map<String, Object> data = Maps.newConcurrentMap();
        String date = aldAdAnomalyDataVo.getDate();
        for(AldAdAnomalyDataEntity aldAdAnomalyDataEntity : aldAdAnomalyDataEntityList) {
            if(null != aldAdAnomalyDataEntity) {
//                String time = aldAdAnomalyDataEntity.getHour();
                //今天昨天 计算小时
                if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                    data.put(aldAdAnomalyDataEntity.getHour() + "_auth", aldAdAnomalyDataEntity.getAnomalyNewAuthUser());
                    data.put(aldAdAnomalyDataEntity.getHour() + "_click", aldAdAnomalyDataEntity.getAnomalyClickCount());
                    //7天 30天
                } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME) || date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)
                        || date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                    data.put(aldAdAnomalyDataEntity.getDay() + "_auth", aldAdAnomalyDataEntity.getAnomalyNewAuthUser());
                    data.put(aldAdAnomalyDataEntity.getDay() + "_click", aldAdAnomalyDataEntity.getAnomalyClickCount());
                    //指定日期
                } else if(date.contains(Constants.FLAG_01)) {
                    String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                    //判断日期是否为今天
                    boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
                    if(isContains && dates[0].equals(dates[1])) {
                        //计算小时
                        data.put(aldAdAnomalyDataEntity.getHour() + "_auth", aldAdAnomalyDataEntity.getAnomalyNewAuthUser());
                        data.put(aldAdAnomalyDataEntity.getHour() + "_click", aldAdAnomalyDataEntity.getAnomalyClickCount());
                    } else {
                        data.put(aldAdAnomalyDataEntity.getDay() + "_auth", aldAdAnomalyDataEntity.getAnomalyNewAuthUser());
                        data.put(aldAdAnomalyDataEntity.getDay() + "_click", aldAdAnomalyDataEntity.getAnomalyClickCount());
                    }
                }
            }
        }

        return data;
    }


    public static void main(String[] args) {
        System.out.println(formatHour());
    }

}
