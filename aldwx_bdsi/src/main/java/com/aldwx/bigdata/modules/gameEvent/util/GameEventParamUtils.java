package com.aldwx.bigdata.modules.gameEvent.util;

import com.aldwx.bigdata.common.constants.Constants;
import com.aldwx.bigdata.common.page.PageUtil;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.modules.eventParam.vo.AldEventParamVo;
import com.aldwx.bigdata.modules.gameEvent.entity.AldGameEventEntity;
import com.aldwx.bigdata.modules.gameEvent.entity.AldGameEventParamEntity;
import com.aldwx.bigdata.modules.gameEvent.vo.AldGameEventParamVo;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Map;

/**
 * 事件参数处理
 */
public class GameEventParamUtils {

    //分页默认从第一页
    private static final String CURRENT_PAGE_DEFAULT = "1";
    //默认每页100条
    private static final String TOTAL_DEFAULT = "100";

    /**
     * 参数校验
     * @param vo
     * @return
     */
    public static Map<String, String> paramCheck(AldGameEventParamVo vo) {
        Map<String, String> checkMap = Maps.newConcurrentMap();
        String date = vo.getDate();
        String currentPage = vo.getCurrentPage();
        String total = vo.getTotal();
        String ak = vo.getAppKey();
        String ek = vo.getEventKey();
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
        if (StringUtils.isEmpty(currentPage) || StringUtils.isEmpty(total)
                || !StringUtils.isNumeric(currentPage) || !StringUtils.isNumeric(total)) {
            checkMap.put("code", "202");
            checkMap.put("msg", Constants.ALERT_PAGE_ERROR_MSG);
        } else if (StringUtils.isEmpty(ak) || StringUtils.isEmpty(ek)) {
            checkMap.put("code", "202");
            checkMap.put("msg", "参数输入有误");
        }

        return checkMap;
    }


    /**
     * 参数格式化处理
     * @param vo
     * @return
     */
    public static AldGameEventParamVo requestParamFormat(AldGameEventParamVo vo) {
        String date = vo.getDate();
        String eventName = vo.getEventName();
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
                } else if(dates[0].equals(DateUtil.getYesterday())){
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
        if(StringUtils.isNotBlank(eventName)) {
            vo.setEventName(eventName.contains("选择") ? "" : eventName);
        }
        if(StringUtils.isNotBlank(order)) {
            if(order.contains("asc")) {
                vo.setOrder("ASC");
            } else if(order.contains("desc")){
                vo.setOrder("DESC");
            }
        }

        //格式化开始页 和 结束页
        vo = pageFormat(vo);

        return vo;
    }


    public static AldGameEventParamVo pageFormat(AldGameEventParamVo vo) {
        String isDownload = vo.getIsDownload();
        if(StringUtils.isNotBlank(isDownload) && isDownload.equals("1")) {
            vo.setStartRow(1);
            vo.setEndRow(1000*1000*100);
        } else {
            String currentPage = vo.getCurrentPage();
            String total = vo.getTotal();
            vo.setStartRow(PageUtil.startRow(Integer.parseInt(currentPage), Integer.parseInt(total)));
            vo.setEndRow(PageUtil.endRow(Integer.parseInt(currentPage), Integer.parseInt(total)));
        }
        return vo;
    }

    /**
     * 参数列表结果数据格式化
     * @param aldGameEventEntityList
     * @return
     */
    public static List<Map<String, Object>> responseParamListFormat(List<AldGameEventParamEntity> aldGameEventEntityList) {
        List<Map<String, Object>> resultList = Lists.newArrayList();
        if(null != aldGameEventEntityList && aldGameEventEntityList.size() > 0) {
            for(AldGameEventParamEntity aldGameEventParamEntity : aldGameEventEntityList) {
                Map<String, Object> resultMap = Maps.newConcurrentMap();
                String appKey = aldGameEventParamEntity.getAppKey();
                String eventKey = aldGameEventParamEntity.getEventKey();
                String evName = aldGameEventParamEntity.getEvParasName();
                String evParasCount = aldGameEventParamEntity.getEvParasCount();

                resultMap.put("app_key", appKey);
                resultMap.put("event_key", eventKey);
                resultMap.put("ev_paras_name", evName);
                resultMap.put("ev_paras_count", evParasCount);

                resultList.add(resultMap);
            }
        }

        return resultList;
    }


    /**
     * 参数明细结果数据格式化
     * @param entityMap
     * @return
     */
    public static List<Map<String, Object>> responseParamDetailFormat(Map<String, Object> entityMap) {
        List<Map<String, Object>> resultList = Lists.newArrayList();
        if(null != entityMap) {
            List<AldGameEventParamEntity> aldEventParamEntityList = (List<AldGameEventParamEntity>)entityMap.get("data");
            if(null != aldEventParamEntityList && aldEventParamEntityList.size() > 0) {
                for(AldGameEventParamEntity aldGameEventParamEntity : aldEventParamEntityList) {
                    Map<String, Object> resultMap = Maps.newConcurrentMap();
                    String appKey = aldGameEventParamEntity.getAppKey();
                    String eventKey = aldGameEventParamEntity.getEventKey();
                    String evParasCount = aldGameEventParamEntity.getEvParasCount();
                    String evParasUv = aldGameEventParamEntity.getEvParasUv();
                    String evParasValue = aldGameEventParamEntity.getEvParasValue();

                    resultMap.put("app_key", appKey);
                    resultMap.put("event_key", eventKey);
                    resultMap.put("ev_paras_count", evParasCount);
                    resultMap.put("ev_paras_uv", evParasUv);
                    resultMap.put("ev_paras_value", evParasValue);

                    resultList.add(resultMap);
                }
            }
        }

        return resultList;
    }




}