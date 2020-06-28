package com.aldwx.bigdata.modules.city.util;

import com.aldwx.bigdata.common.constants.Constants;
import com.aldwx.bigdata.common.page.PageUtil;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.common.util.StringUtil;
import com.aldwx.bigdata.modules.city.constants.CityConstants;
import com.aldwx.bigdata.modules.city.entity.AldCityEntity;
import com.aldwx.bigdata.modules.city.vo.AldCityVo;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;


public class CityUtils {


    //分页默认从第一页
    private static final String CURRENT_PAGE_DEFAULT = "1";
    //默认每页100条
    private static final String TOTAL_DEFAULT = "100";

    /**
     * TopN参数校验
     * @param vo
     * @return
     */
    public static Map<String, String> requestTopNParamCheck(AldCityVo vo) {
        Map<String, String> checkMap = Maps.newConcurrentMap();
        String date = vo.getDate();
        String ak = vo.getAppKey();
        String type = vo.getType();
        if (StringUtils.isEmpty(date) || (!date.contains(Constants.FLAG_01) && date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME) &&
                date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME) && date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)
                && date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME))) {
            checkMap.put("code", "202");
            checkMap.put("msg", "日期格式有误");
        } else if(StringUtils.isEmpty(ak) || StringUtils.isEmpty(type)) {
            checkMap.put("code", "202");
            checkMap.put("msg", "参数输入有误");
        }

        return checkMap;
    }


    /**
     * list列表参数格式化
     * @param vo
     * @return
     */
    public static Map<String, String> requestListParamCheck(AldCityVo vo) {
        Map<String, String> checkMap = Maps.newConcurrentMap();
        String date = vo.getDate();
        String ak = vo.getAppKey();
        String type = vo.getType();
        String currentPage = vo.getCurrentPage();
        String total = vo.getTotal();

        if (StringUtils.isEmpty(date) || (!date.contains(Constants.FLAG_01) && date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME) &&
                date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME) && date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)
                && date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME))) {
            checkMap.put("code", "202");
            checkMap.put("msg", "日期格式有误");
        } else if (StringUtils.isEmpty(currentPage) || StringUtils.isEmpty(total)
                || !StringUtils.isNumeric(currentPage) || !StringUtils.isNumeric(total)) {
            checkMap.put("code", "202");
            checkMap.put("msg", "请输入正确的分页信息");
        } else if (StringUtils.isEmpty(ak) || StringUtils.isEmpty(type)) {
            checkMap.put("code", "202");
            checkMap.put("msg", "参数输入有误");
        }

        return checkMap;
    }



    /**
     * 格式化TopN返回值
     * @param entityMap
     * @param vo
     * @return
     */
    public static Map<String, Object> responseCityTopFormat(Map<String, Object> entityMap, AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != entityMap && entityMap.size() > 0) {
            if(entityMap instanceof Map) {
                List<AldCityEntity> aldCityEntityList = (List<AldCityEntity>)entityMap.get(CityConstants.TOP_N);
                Long otherSum = (Long)entityMap.get(CityConstants.TOP_OTHER);
                List<String> title = Lists.newArrayList();
                if(null != aldCityEntityList && aldCityEntityList.size() > 0) {
                    String name = null;
                    List<Map<String, Object>> mapList = Lists.newArrayList();
                    for(AldCityEntity aldCityEntity : aldCityEntityList) {
                        Map<String, Object> map = Maps.newConcurrentMap();
                        title.add(aldCityEntity.getCity());
                        if(vo.getType().equals(CityConstants.NEW_USER_COUNT)) {
                            name = CityConstants.NEW_USER_COUNT_NAME;
                            map.put("name", aldCityEntity.getCity());
                            map.put("value", aldCityEntity.getNewUserCount());
                        } else if(vo.getType().equals(CityConstants.VISITOR_COUNT)) {
                            name = CityConstants.VISITOR_COUNT_NAME;
                            map.put("name", aldCityEntity.getCity());
                            map.put("value", aldCityEntity.getVisitorCount());
                        } else if(vo.getType().equals(CityConstants.PAGE_COUNT)) {
                            name = CityConstants.PAGE_COUNT_NAME;
                            map.put("name", aldCityEntity.getCity());
                            map.put("value", aldCityEntity.getPageCount());
                        }
                        mapList.add(map);
                    }
                    //添加其他区域信息
                    title.add(CityConstants.TOP_OTHER);

                    Map<String, Object> otherAreaMap = Maps.newConcurrentMap();
                    otherAreaMap.put("name", CityConstants.TOP_OTHER);
                    otherAreaMap.put("value", NumberUtils.toInt(otherSum+""));

                    mapList.add(otherAreaMap);

                    resultMap.put("title", title);
                    resultMap.put("name", name);
                    resultMap.put("data", mapList);
                }
            }
        }
        return resultMap;
    }


    /**
     * 合并chart数据 合并数据
     * @param list1
     * @param list2
     * @param vo
     * @return
     */
    public static Map<String, Object> mergeChartData(List<AldCityEntity> list1, List<AldCityEntity> list2, AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != list1 && list1.size() > 0 && null != list2 && list2.size() > 0) {
            List<AldCityEntity> mapList = Lists.newArrayList();
            Map<String, AldCityEntity> cityEntityMap = Maps.newConcurrentMap();
            for(AldCityEntity aldCityEntity : list1) {
                cityEntityMap.put(aldCityEntity.getCity(), aldCityEntity);
            }
            for(AldCityEntity aldCityEntity : list2) {
                AldCityEntity aldCityEntity1 = cityEntityMap.get(aldCityEntity.getCity());
                if(null == aldCityEntity1) {
                    cityEntityMap.put(aldCityEntity.getCity(), aldCityEntity);
                } else {
                    int mergeCount = 0;
                    String type = vo.getType();
                    if(type.equals(CityConstants.NEW_USER_COUNT)) {
                        mergeCount = NumberUtils.toInt(aldCityEntity.getNewUserCount()) +
                                NumberUtils.toInt(aldCityEntity1.getNewUserCount());
                        aldCityEntity.setNewUserCount(mergeCount+"");
                    } else if(type.equals(CityConstants.VISITOR_COUNT)) {
                        mergeCount = NumberUtils.toInt(aldCityEntity.getVisitorCount()) +
                                NumberUtils.toInt(aldCityEntity1.getVisitorCount());
                        aldCityEntity.setVisitorCount(mergeCount+"");
                    } else if(type.equals(CityConstants.PAGE_COUNT)) {
                        mergeCount = NumberUtils.toInt(aldCityEntity.getPageCount()) +
                                NumberUtils.toInt(aldCityEntity1.getPageCount());
                        aldCityEntity.setPageCount(mergeCount+"");
                    }
                    cityEntityMap.put(aldCityEntity.getCity(), aldCityEntity);
                }
            }
            for(Map.Entry<String, AldCityEntity> entry : cityEntityMap.entrySet()) {
                mapList.add(entry.getValue());
            }
            resultMap.put("data", mapList);
        } else {
            if(null != list1 && list1.size() > 0) {
                resultMap.put("data", list1);
            } else if(null != list2 && list2.size() > 0){
                resultMap.put("data", list2);
            }
        }

        return resultMap;
    }



    /**
     * 合并TopN数据
     * @param list1
     * @param list2
     * @return
     */
    public static Map<String, Object> mergeTopData(List<AldCityEntity> list1, List<AldCityEntity> list2,
                                                     Long otherCount1, Long otherCount2, AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != list1 && list1.size() > 0 && null != list2 && list2.size() > 0) {
            List<AldCityEntity> mapList = Lists.newArrayList();
            Map<String, AldCityEntity> cityEntityMap = Maps.newConcurrentMap();
            for(AldCityEntity aldCityEntity : list1) {
                cityEntityMap.put(aldCityEntity.getCity(), aldCityEntity);
            }
            for(AldCityEntity aldCityEntity : list2) {
                AldCityEntity aldCityEntity1 = cityEntityMap.get(aldCityEntity.getCity());
                if(null == aldCityEntity1) {
                    cityEntityMap.put(aldCityEntity.getCity(), aldCityEntity);
                } else {
                    int mergeCount = 0;
                    String type = vo.getType();
                    if(type.equals(CityConstants.NEW_USER_COUNT)) {
                        mergeCount = NumberUtils.toInt(aldCityEntity.getNewUserCount()) +
                                NumberUtils.toInt(aldCityEntity1.getNewUserCount());
                        aldCityEntity.setNewUserCount(mergeCount+"");
                    } else if(type.equals(CityConstants.VISITOR_COUNT)) {
                        mergeCount = NumberUtils.toInt(aldCityEntity.getVisitorCount()) +
                                NumberUtils.toInt(aldCityEntity1.getVisitorCount());
                        aldCityEntity.setVisitorCount(mergeCount+"");
                    } else if(type.equals(CityConstants.PAGE_COUNT)) {
                        mergeCount = NumberUtils.toInt(aldCityEntity.getPageCount()) +
                                NumberUtils.toInt(aldCityEntity1.getPageCount());
                        aldCityEntity.setPageCount(mergeCount+"");
                    }
                    cityEntityMap.put(aldCityEntity.getCity(), aldCityEntity);
                }
            }
            for(Map.Entry<String, AldCityEntity> entry : cityEntityMap.entrySet()) {
                mapList.add(entry.getValue());
            }
            //排序取TOP5 并计算其他城市的总数
            //TODO
            resultMap.put(CityConstants.TOP_N, mapList);
            resultMap.put(CityConstants.TOP_OTHER, 100L);
        } else {
            if(null != list1 && list1.size() > 0) {
                resultMap.put(CityConstants.TOP_N, list1);
                resultMap.put(CityConstants.TOP_OTHER, otherCount1);
            } else if(null != list2 && list2.size() > 0){
                resultMap.put(CityConstants.TOP_N, list2);
                resultMap.put(CityConstants.TOP_OTHER, otherCount2);
            }
        }

        return resultMap;
    }


    /**
     * 参数格式化
     * @return
     */
    public static AldCityVo requestParamFormat(AldCityVo vo) {
        String date = vo.getDate();
        String currentPage = vo.getCurrentPage();
        String total = vo.getTotal();
        String order = vo.getOrder();
        //将分区时间仅为今天或者昨天的 替换为1或2 直接查询mysql库
        if(date.contains(Constants.FLAG_01)) {
            String[] dates = StringUtils.splitPreserveAllTokens(date.replaceAll("\\s*",""), Constants.FLAG_01);
            //判断时间区间是否仅包含2天
            if(dates.length == 2 && dates[0].equals(dates[1])) {
                if(dates[0].equals(DateUtil.getTodayDate())) {
                    vo.setDate(Constants.ALDSTAT_EVENT_TODAY_TIME);
                } else if(dates[0].equals(DateUtil.getYesterday())) {
                    vo.setDate(Constants.ALDSTAT_EVENT_YESTERDAY_TIME);
                }
            }
        }
        if(StringUtils.isNotBlank(currentPage) && Integer.parseInt(currentPage) <= 0) {
            vo.setCurrentPage(CURRENT_PAGE_DEFAULT);
        }
        if(StringUtils.isNotBlank(total) && Integer.parseInt(total) <= 0) {
            vo.setTotal(TOTAL_DEFAULT);
        }
        if(StringUtils.isNotBlank(order)) {
            if(order.contains("asc")) {
                vo.setOrder("ASC");
            } else if(order.contains("desc")) {
                vo.setOrder("DESC");
            }
        }
        //格式化开始页 和 结束页
        vo = pageFormat(vo);

        return vo;
    }



    public static AldCityVo pageFormat(AldCityVo vo) {
        String currentPage = vo.getCurrentPage();
        String total = vo.getTotal();
        vo.setStartRow(PageUtil.startRow(Integer.parseInt(currentPage), Integer.parseInt(total)));
        vo.setEndRow(PageUtil.endRow(Integer.parseInt(currentPage), Integer.parseInt(total)));
        return vo;
    }



    /**
     * 格式化城市Chart返回数据
     * @param entityMap
     * @param vo
     * @return
     */
    public static Map<String, Object> responseCityChartFormat(Map<String, Object> entityMap, AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != entityMap && entityMap.size() > 0) {
            if(entityMap instanceof Map) {
                List<AldCityEntity> aldCityEntityList = (List<AldCityEntity>)entityMap.get("data");
                if(null != aldCityEntityList && aldCityEntityList.size() > 0) {
                    String typeName = null;
                    List<Map<String, Object>> mapList = Lists.newArrayList();
                    for(AldCityEntity aldCityEntity : aldCityEntityList) {
                        Map<String, Object> map = Maps.newConcurrentMap();
                        if(vo.getType().equals(CityConstants.NEW_USER_COUNT)) {
                            typeName = CityConstants.NEW_USER_COUNT_NAME;
                            map.put("name", aldCityEntity.getCity());
                            map.put("value", aldCityEntity.getNewUserCount());
                        } else if(vo.getType().equals(CityConstants.VISITOR_COUNT)) {
                            typeName = CityConstants.VISITOR_COUNT_NAME;
                            map.put("name", aldCityEntity.getCity());
                            map.put("value", aldCityEntity.getVisitorCount());
                        } else if(vo.getType().equals(CityConstants.PAGE_COUNT)) {
                            typeName = CityConstants.PAGE_COUNT_NAME;
                            map.put("name", aldCityEntity.getCity());
                            map.put("value", aldCityEntity.getPageCount());
                        }

                        mapList.add(map);
                    }

                    resultMap.put("typeName", typeName);
                    resultMap.put("data", mapList);
                }
            }
        }

        return resultMap;
    }



    /**
     * 格式化返回list列表数据
     * @param entityMap
     * @param vo
     * @return
     */
    public static Map<String, Object> responseCityListFormat(Map<String, Object> entityMap, AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != entityMap && entityMap.size() > 0) {
            List<AldCityEntity> aldCityEntityList = (List<AldCityEntity>)entityMap.get("data");
            if(null != aldCityEntityList && aldCityEntityList.size() > 0) {

                int newUserCount = 0;
                int visitorCount = 0;
                int openCount = 0;
                int pageCount = 0;
                double secondaryStayTime = 0d;
                float bounceRate = 0f;

                List<Map<String, Object>> mapList = Lists.newArrayList();
                for(AldCityEntity aldCityEntity : aldCityEntityList) {
                    Map<String, Object> map = Maps.newConcurrentMap();
                    map.put("city", aldCityEntity.getCity());
                    map.put("new_user_count", aldCityEntity.getNewUserCount());
                    map.put("visitor_count", aldCityEntity.getVisitorCount());
                    map.put("open_count", aldCityEntity.getOpenCount());
                    map.put("page_count", aldCityEntity.getPageCount());
                    map.put("secondary_stay_time", StringUtil.formatTime(NumberUtils.toDouble(aldCityEntity.getSecondaryStayTime())));
                    map.put("bounce_rate", StringUtil.formatPercent(NumberUtils.toFloat(aldCityEntity.getBounceRate())));
                    mapList.add(map);

                    newUserCount += NumberUtils.toInt(aldCityEntity.getNewUserCount());
                    visitorCount += NumberUtils.toInt(aldCityEntity.getVisitorCount());
                    openCount += NumberUtils.toInt(aldCityEntity.getOpenCount());
                    pageCount += NumberUtils.toInt(aldCityEntity.getPageCount());
                    secondaryStayTime += NumberUtils.toDouble(aldCityEntity.getSecondaryStayTime());
                    bounceRate += NumberUtils.toFloat(aldCityEntity.getBounceRate());
                }

                Map<String, Object> summaryDataMap = Maps.newConcurrentMap();
                summaryDataMap.put("new_user_count", newUserCount);
                summaryDataMap.put("visitor_count", visitorCount);
                summaryDataMap.put("open_count", openCount);
                summaryDataMap.put("page_count", pageCount);
                summaryDataMap.put("secondary_stay_time", StringUtil.formatTime(secondaryStayTime));
                summaryDataMap.put("bounce_rate", StringUtil.formatPercent(bounceRate));

                resultMap.put("data", mapList);
                resultMap.put("summaryData", summaryDataMap);
                resultMap.put("count", entityMap.get("count"));
            }
        }

        return resultMap;
    }



    /**
     * 计算topN其他城市总数
     * @return
     */
    public static int calculateTopN(List<AldCityEntity> aldCityEntityList, AldCityVo vo) {
        int sumTopN = 0;
        if(null != aldCityEntityList && aldCityEntityList.size() > 0) {
            for(AldCityEntity aldCityEntity : aldCityEntityList) {
                if(vo.getType().equals(CityConstants.NEW_USER_COUNT)) {
                    sumTopN += NumberUtils.toInt(aldCityEntity.getNewUserCount());
                } else if(vo.getType().equals(CityConstants.VISITOR_COUNT)) {
                    sumTopN += NumberUtils.toInt(aldCityEntity.getVisitorCount());
                } else if(vo.getType().equals(CityConstants.PAGE_COUNT)) {
                    sumTopN += NumberUtils.toInt(aldCityEntity.getPageCount());
                }
            }
        }
        return sumTopN;
    }


//    /**
//     * 格式化停留时长
//     * @param stayTime
//     * @return
//     */
//    public static String formatStayTime(double stayTime) {
//        final int A = 60;
//        String result = null;
//        if(stayTime >= 60) {
//            int minuter = (int)stayTime / A;
//            int min = (int)stayTime % A;
//            if(minuter >= 60) {
//                int hour = minuter / A;
//                int h = minuter % A;
//                return String.format("%s:%s:%s", (hour >= 10 ? hour : "0"+hour),
//                        (h >= 10 ? h : "0"+h), (min >= 10 ? min : "0"+min));
//            } else {
//                result = String.format("00:%s:%s", (minuter >= 10 ? minuter : "0"+minuter),
//                        (min >= 10 ? min : "0"+min));
//            }
//        } else {
//            result = String.format("00:00:%s", (stayTime >= 10 ? (int)stayTime : "0"+(int)stayTime));
//        }
//        return result;
//    }


//    /**
//     * 格式化占有率
//     * @return
//     */
//    public static String formatBounceRate(float bounceRate) {
//        if(bounceRate > 0) {
//            DecimalFormat fnum = new DecimalFormat("##0.00");
//            return fnum.format(bounceRate) + "%";
//        }
//        return "0.00%";
//    }



}
