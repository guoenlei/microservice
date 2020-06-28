package com.aldwx.bigdata.modules.province.service;

import com.aldwx.bigdata.common.base.BaseService;
import com.aldwx.bigdata.modules.province.entity.AldProvinceEntity;
import com.aldwx.bigdata.modules.province.vo.AldProvinceVo;

import java.util.Map;

public interface AldProvinceService extends BaseService<AldProvinceEntity, AldProvinceVo> {


    /****************获取城市TopN*******************/
    /**
     * 获取城市Top N
     * @return
     */
    Map<String, Object> queryProvinceDataTop(AldProvinceVo vo);

    /**
     * 获取今天城市Top
     * @param vo
     * @return
     */
    Map<String, Object> queryTodayDataTop(AldProvinceVo vo);


    /**
     * 获取昨天城市Top
     * @param vo
     * @return
     */
    Map<String, Object> queryYesterdayDataTop(AldProvinceVo vo);



    /***************获取城市 chart**************/

    /**
     * 获取城市city chart
     * @param vo
     * @return
     */
    Map<String, Object> queryProvinceDataChart(AldProvinceVo vo);

    /**
     * 获取城市今天数据 chart
     * @param vo
     * @return
     */
    Map<String, Object> queryTodayDataChart(AldProvinceVo vo);


    /**
     * 获取城市昨天数据 chart
     * @param vo
     * @return
     */
    Map<String, Object> queryYesterdayDataChart(AldProvinceVo vo);


}
