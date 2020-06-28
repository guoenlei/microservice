package com.aldwx.bigdata.modules.city.service;

import com.aldwx.bigdata.common.base.BaseService;
import com.aldwx.bigdata.modules.city.entity.AldCityEntity;
import com.aldwx.bigdata.modules.city.vo.AldCityVo;

import java.util.Map;


public interface AldCityService extends BaseService<AldCityEntity, AldCityVo> {


    /****************获取城市TopN*******************/
    /**
     * 获取城市Top N
     * @return
     */
    Map<String, Object> queryCityDataTop(AldCityVo vo);

    /**
     * 获取今天城市Top
     * @param vo
     * @return
     */
    Map<String, Object> queryTodayDataTop(AldCityVo vo);



    /**
     * 获取昨天城市Top
     * @param vo
     * @return
     */
    Map<String, Object> queryYesterdayDataTop(AldCityVo vo);



    /***************获取城市 chart**************/

    /**
     * 获取城市city chart
     * @param vo
     * @return
     */
    Map<String, Object> queryCityDataChart(AldCityVo vo);

    /**
     * 获取城市今天数据 chart
     * @param vo
     * @return
     */
    Map<String, Object> queryTodayDataChart(AldCityVo vo);


    /**
     * 获取城市昨天数据 chart
     * @param vo
     * @return
     */
    Map<String, Object> queryYesterdayDataChart(AldCityVo vo);



}
