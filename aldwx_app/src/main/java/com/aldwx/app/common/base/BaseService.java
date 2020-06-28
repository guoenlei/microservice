package com.aldwx.app.common.base;

import java.util.List;

public interface BaseService<T, S>  {


    /**
     * 根据日期查询数据
     * @param s
     * @return
     */
    T queryDataByDate(S s);

    /**
     * 获取分页数据
     * @param s
     * @return
     */
    List<T> queryPageDataList(S s);

    /**
     * 查询今天数据
     * @param s
     * @return
     */
    T queryTodayDataBy(S s);

    /**
     * 查询昨天数据
     * @param s
     * @return
     */
    T queryYesterdayDataBy(S s);

    /**
     * 查询最近7天的数据
     * @param s
     * @return
     */
    T query7DayDataBy(S s);

    /**
     * 查询最近30天的数据
     * @param s
     * @return
     */
    T query30DayDataBy(S s);

    /**
     * 指定时间段区间查询数据
     * @param s
     * @return
     */
    T queryDaysDataBy(S s);


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


}
