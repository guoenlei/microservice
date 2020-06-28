package com.aldwx.app.common.base;


import java.util.List;

public interface BaseDao<T, S> {


    /**
     * 查询最近7天的数据
     * @param s
     * @return
     */
    List<T> query7DayDataBy(S s);

    /**
     * 查询最近30天的数据
     * @param s
     * @return
     */
    List<T> query30DayDataBy(S s);

    /**
     * 指定时间段区间查询数据
     * @param s
     * @return
     */
    List<T> queryDaysDataBy(S s);


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
     * 判断是否为统计小程序app
     * 否时 小游戏
     * @param s
     * @return
     */
    boolean isStatApp(S s);


}
