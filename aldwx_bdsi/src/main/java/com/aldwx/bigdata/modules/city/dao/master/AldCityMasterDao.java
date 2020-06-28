package com.aldwx.bigdata.modules.city.dao.master;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.city.entity.AldCityEntity;
import com.aldwx.bigdata.modules.city.vo.AldCityVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface AldCityMasterDao extends BaseDao<AldCityEntity, AldCityVo> {



    /*****************TopN********************/

    /**
     * 获取今天数据  城市Top
     * @param vo
     * @return
     */
    List<AldCityEntity> queryTodayDataTop(AldCityVo vo);


    /**
     * 获取今天数据  城市总数
     * @param vo
     * @return
     */
    Long queryTodayDataSumTop(AldCityVo vo);


    /**
     * 获取昨天数据  城市Top
     * @param vo
     * @return
     */
    List<AldCityEntity> queryYesterdayDataTop(AldCityVo vo);


    /**
     * 获取今天数据  城市总数
     * @param vo
     * @return
     */
    Long queryYesterdayDataSumTop(AldCityVo vo);



    /*****************Chart********************/

    /**
     * 获取城市今天数据 chart
     * @param vo
     * @return
     */
    List<AldCityEntity> queryTodayDataChart(AldCityVo vo);


    /**
     * 获取城市昨天数据 chart
     * @param vo
     * @return
     */
    List<AldCityEntity> queryYesterdayDataChart(AldCityVo vo);









}
