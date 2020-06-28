package com.aldwx.bigdata.common.base;

import java.util.Map;

public interface BaseService<T, S> {


    Map<String, Object> queryPageDataList(S s);

    /**
     * 查询今天数据
     * @param s
     * @return
     */
    Map<String, Object> queryTodayDataBy(S s);

    /**
     * 查询昨天数据
     * @param s
     * @return
     */
    Map<String, Object> queryYesterdayDataBy(S s);

    /**
     * 查询最近7天的数据
     * @param s
     * @return
     */
    Map<String, Object> query7DayDataBy(S s);

    /**
     * 查询最近30天的数据
     * @param s
     * @return
     */
    Map<String, Object> query30DayDataBy(S s);

    /**
     * 指定时间段区间查询数据
     * @param s
     * @return
     */
    Map<String, Object> queryDaysDataBy(S s);


    /**
     * 获取最近30天总数
     * @param s
     * @return
     */
    Long query30DayCountDataBy(S s);

    /**
     * 获取最近7天
     * @param s
     * @return
     */
    Long query7DayCountDataBy(S s);

    /**
     * 获取指定时间区间查询数据
     * @param s
     * @return
     */
    Long queryDaysCountDataBy(S s);


    /**
     * 判断是否为从库
     * @param s
     * @return
     */
    boolean isCluster(S s);

}
