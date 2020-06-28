package com.aldwx.bigdata.modules.province.util;

import com.aldwx.bigdata.common.constants.Constants;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.modules.city.constants.CityConstants;
import com.aldwx.bigdata.modules.province.entity.AldProvinceEntity;
import com.aldwx.bigdata.modules.province.vo.AldProvinceVo;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

public class ProvinceUtil {



    /**
     * TopN参数校验
     * @return
     */
    public static Map<String, Object> topNParamCheck(AldProvinceVo vo) {
        Map<String, Object> checkMap = Maps.newConcurrentMap();
        String date = vo.getDate();
        String ak = vo.getAppKey();
        String type = vo.getType();
        if(StringUtils.isNotBlank(date)) {
            if(!date.contains(Constants.FLAG_01)) {
                boolean flag = false;
                switch (NumberUtils.toInt(date)) {
                    case 1:
                        flag = true;
                        break;
                    case 2:
                        flag = true;
                        break;
                    case 3:
                        flag = true;
                        break;
                    case 4:
                        flag = true;
                        break;
                    default:
                        break;
                }
                if(!flag) {
                    checkMap.put("code", "202");
                    checkMap.put("msg", Constants.ALERT_DATE_ERROR_MSG);
                }
            }
        } else {
            checkMap.put("code", "202");
            checkMap.put("msg", Constants.ALERT_DATE_ERROR_MSG);
        }
       if(StringUtils.isEmpty(ak) || StringUtils.isEmpty(type)) {
            checkMap.put("code", "202");
            checkMap.put("msg", "参数输入有误");
        }
        return checkMap;
    }

    /**
     * 计算topN其他省份总数
     * @return
     */
    public static int calculateTopN(List<AldProvinceEntity> aldProvinceEntityList, AldProvinceVo vo) {
        int sumTopN = 0;
        if(null != aldProvinceEntityList && aldProvinceEntityList.size() > 0) {
            for(AldProvinceEntity aldProvinceEntity : aldProvinceEntityList) {
                if(vo.getType().equals(CityConstants.NEW_USER_COUNT)) {
                    sumTopN += NumberUtils.toInt(aldProvinceEntity.getNewUserCount());
                } else if(vo.getType().equals(CityConstants.VISITOR_COUNT)) {
                    sumTopN += NumberUtils.toInt(aldProvinceEntity.getVisitorCount());
                } else if(vo.getType().equals(CityConstants.PAGE_COUNT)) {
                    sumTopN += NumberUtils.toInt(aldProvinceEntity.getPageCount());
                }
            }
        }
        return sumTopN;
    }


    /**
     * 格式化请求参数
     * @param vo
     * @return
     */
    public static AldProvinceVo requestFormat(AldProvinceVo vo) {
        String date = vo.getDate();
        String formatDate = null;
        //今天
        String today = DateUtil.getTodayDate() + " 00:00:00";
        long todayTime = 0L;
        try {
            Date todayDate = DateUtil.TIME_FORMAT.parse(today);
            Date d1 = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(todayDate);
            todayTime = calendar.getTimeInMillis()/1000L;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //昨天
        String yesterday = DateUtil.getYesterday() + " 00:00:00";
        long yesterdayTime = 0L;
        try {
            Date yesterdayDate = DateUtil.TIME_FORMAT.parse(yesterday);
            Date d1 = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(yesterdayDate);
            yesterdayTime = calendar.getTimeInMillis()/1000L;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(StringUtils.isNotBlank(date)) {
            if(!date.contains(Constants.FLAG_01)) {
                switch (NumberUtils.toInt(date)) {
                    case 1:
                        formatDate = todayTime + "";
                        break;
                    case 2:
                        formatDate = yesterdayTime + "";
                        break;
                    case 3:
                        formatDate = yesterdayTime + "";
                        break;
                    case 4:
                        formatDate = yesterdayTime + "";
                        break;
                    default:
                        formatDate = date;
                        break;
                }
            }
        }

        vo.setDate(formatDate);
        return vo;
    }


    public static String formatTodayTime() {
        //今天
        String today = DateUtil.getTodayDate() + " 00:00:00";
        long todayTime = 0L;
        try {
            Date todayDate = DateUtil.TIME_FORMAT.parse(today);
            Date d1 = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(todayDate);
            todayTime = calendar.getTimeInMillis()/1000L;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todayTime+"";
    }


    public static String formatYesterdayTime() {
        //昨天
        String yesterday = DateUtil.getYesterday() + " 00:00:00";
        long yesterdayTime = 0L;
        try {
            Date yesterdayDate = DateUtil.TIME_FORMAT.parse(yesterday);
            Date d1 = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(yesterdayDate);
            yesterdayTime = calendar.getTimeInMillis()/1000L;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return yesterdayTime + "";
    }

    /**
     * 格式化TopN返回值
     * @param entityMap
     * @param vo
     * @return
     */
    public static Map<String, Object> responseCityTopFormat(Map<String, Object> entityMap, AldProvinceVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != entityMap && entityMap.size() > 0) {
            if(entityMap instanceof Map) {
                List<AldProvinceEntity> aldProvinceEntityList = (List<AldProvinceEntity>)entityMap.get(CityConstants.TOP_N);
                Long otherSum = (Long)entityMap.get(CityConstants.TOP_OTHER);
                List<String> title = Lists.newArrayList();
                if(null != aldProvinceEntityList && aldProvinceEntityList.size() > 0) {
                    String name = null;
                    List<Map<String, Object>> mapList = Lists.newArrayList();
                    for(AldProvinceEntity aldProvinceEntity : aldProvinceEntityList) {
                        Map<String, Object> map = Maps.newConcurrentMap();
                        title.add(aldProvinceEntity.getProvince());
                        if(vo.getType().equals(CityConstants.NEW_USER_COUNT)) {
                            name = CityConstants.NEW_USER_COUNT_NAME;
                            map.put("name", aldProvinceEntity.getProvince());
                            map.put("value", aldProvinceEntity.getNewUserCount());
                        } else if(vo.getType().equals(CityConstants.VISITOR_COUNT)) {
                            name = CityConstants.VISITOR_COUNT_NAME;
                            map.put("name", aldProvinceEntity.getProvince());
                            map.put("value", aldProvinceEntity.getVisitorCount());
                        } else if(vo.getType().equals(CityConstants.PAGE_COUNT)) {
                            name = CityConstants.PAGE_COUNT_NAME;
                            map.put("name", aldProvinceEntity.getProvince());
                            map.put("value", aldProvinceEntity.getPageCount());
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



}
