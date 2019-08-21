package com.ald.bigdata.common.base;

import com.ald.bigdata.common.util.DateUtil;
import com.ald.bigdata.modules.event.wxmini.controller.WXMiniEventController;
import com.alibaba.fastjson.JSONObject;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static com.ald.bigdata.common.constants.Constants.*;
import static com.ald.bigdata.common.constants.Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME;

public class BaseController {
    private static Logger LOG = LoggerFactory.getLogger(BaseController.class);

    private static final Integer STATUS_OK = 200;
    private static final Integer STATUS_ERROR = 202;


    public static final String RESULT_DATA = "data";
    public static final String RESULT_MSG = "msg";
    public static final String RESULT_CODE = "code";
    public static final String RESULT_DATE = "date";
    public static final String RESULT_COUNT = "count";

    public static final String RESULT_NULL_DATA_MSG = "没有数据";
    /**
     * 日期格式化方法：
     * 1,2,3,4或时间段的参数 => "2019-08-20 ~ 2019-08-20", ...
     *
     * @param date
     * @return
     */
    public static String formatReturnDate(String date) {
        if (StringUtils.equals(date, ALDSTAT_EVENT_TODAY_TIME)) {
            String todayDate = DateUtil.getTodayDate();
            return todayDate + " ~ " + todayDate;
        } else if (StringUtils.equals(date, ALDSTAT_EVENT_YESTERDAY_TIME)) {
            String yesterday = DateUtil.getYesterday();
            return yesterday + " ~ " + yesterday;
        } else if (StringUtils.equals(date, ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
            String[] nearly7Day = DateUtil.getNearly7Day();
            String weekFirstDay = nearly7Day[1];
            String weekLastDay = nearly7Day[0];
            return weekFirstDay + " ~ " + weekLastDay;
        } else if (StringUtils.equals(date, ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
            String[] nearly30Day = DateUtil.getNearly30Day();
            String MonthFirstDay = nearly30Day[1];
            String MonthLastDay = nearly30Day[0];
            return MonthFirstDay + " ~ " + MonthLastDay;
        } else if (date.contains(FLAG_01)) {
            return date;
        } else {
            LOG.error("Incoming date format error: [{}]", date);
            return date;
        }
    }


    /**
     * 转json string返回
     *
     * @param obj
     * @return
     */
    public static String returnJson(Object obj) {
        return JSONObject.toJSONString(obj);
    }


    /**
     * 转json
     *
     * @param entityList
     * @param objects
     * @return
     */
    public static String returnJson(List<? extends BaseEntity> entityList, Object[] objects) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if (null != entityList && entityList.size() > 0) {
            resultMap.put(RESULT_CODE, STATUS_OK);      //状态
            resultMap.put(RESULT_DATA, entityList);     //数据
        } else {
            resultMap.put(RESULT_CODE, STATUS_ERROR);   //状态
            resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);       //无数据
        }

        resultMap.put(RESULT_DATE, objects[0]);     //时间

        return returnJson(resultMap);
    }


    /**
     * 格式化数据
     *
     * @param object
     * @param objects
     * @return
     */
    public static String resultJson2(Object object, Object[] objects) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if (object instanceof List) {
            List<?> lists = (List<?>) object;
            if (null != lists && lists.size() > 0) {
                resultMap.put(RESULT_CODE, STATUS_OK);
                resultMap.put(RESULT_DATA, lists);
            } else {
                resultMap.put(RESULT_CODE, STATUS_ERROR);
                resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
            }
        } else if (object instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) object;
            if (null != map && map.size() > 0) {
                List dataList = (List<?>) map.get("data");
                if (null != dataList && dataList.size() > 0) {
                    resultMap.put(RESULT_CODE, STATUS_OK);
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        resultMap.put(entry.getKey(), entry.getValue());
                    }
                } else {
                    resultMap.put(RESULT_CODE, STATUS_ERROR);
                    resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
                }
            } else {
                resultMap.put(RESULT_CODE, STATUS_ERROR);
                resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
            }
        }
        resultMap.put(RESULT_DATE, objects[0]);     //时间
        if (objects.length > 1) {
            resultMap.put(RESULT_COUNT, objects[1]);     //总数
        }

        return returnJson(resultMap);
    }


    /**
     * 折线图数据格式化
     *
     * @param object
     * @param objects
     * @return
     */
    public static String resultJson3(Object object, Object[] objects) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if (object instanceof List) {
            List<?> lists = (List<?>) object;
            if (null != lists && lists.size() > 0) {
                resultMap.put(RESULT_CODE, STATUS_OK);
                resultMap.put(RESULT_DATA, lists);
            } else {
                resultMap.put(RESULT_CODE, STATUS_ERROR);
                resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
            }
        } else if (object instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) object;
            if (null != map && map.size() > 0) {
                resultMap.put(RESULT_CODE, STATUS_OK);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    resultMap.put(entry.getKey(), entry.getValue());
                }
            } else {
                resultMap.put(RESULT_CODE, STATUS_ERROR);
                resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
            }
        }
        resultMap.put(RESULT_DATE, objects[0]);     //时间
        if (objects.length > 1) {
            resultMap.put(RESULT_COUNT, objects[1]);     //总数
        }

        return returnJson(resultMap);
    }


    /**
     * 返回消息
     *
     * @param messager
     * @return
     */
    public static String resultJson(Messager messager) {
        return returnJson(messager);
    }


    /**
     * 返回消息
     *
     * @param status
     * @return
     */
    public static Messager resultJson(Integer status, String massage) {
        Messager m = new Messager();
        m.setCode(status);
        m.setMessage(massage);
        return m;
    }


    /**
     * 返回成功
     *
     * @param massage
     * @return
     */
    public static String jsonOk(String massage) {
        return resultJson(resultJson(STATUS_OK, massage));
    }


    /**
     * 返回失败
     *
     * @param massage
     * @return
     */
    public static String jsonFail(String massage) {
        return resultJson(resultJson(STATUS_ERROR, massage));
    }


    /**
     * 系统处理异常
     *
     * @return
     */
    public static String jsonError() {
        return returnJson(resultJson(STATUS_ERROR, "系统处理异常"));
    }

}
