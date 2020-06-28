package com.aldwx.bigdata.modules.city.dao.presto;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.city.entity.AldCityEntity;
import com.aldwx.bigdata.modules.city.vo.AldCityVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AldCityPrestoDao extends BaseDao<AldCityEntity, AldCityVo> {


    /**
     * topN
     * @param vo
     * @return
     */
    List<AldCityEntity> query7DayDataTop(AldCityVo vo);
    Long query7DayDataSumTop(AldCityVo vo);

    List<AldCityEntity> query30DayDataTop(AldCityVo vo);
    Long query30DayDataSumTop(AldCityVo vo);

    List<AldCityEntity> queryDaysDataTop(AldCityVo vo);
    Long queryDaysDataSumTop(AldCityVo vo);


    /**
     * chart
     * @param vo
     * @return
     */
    List<AldCityEntity> query7DayDataChart(AldCityVo vo);
    List<AldCityEntity> query30DayDataChart(AldCityVo vo);
    List<AldCityEntity> queryDaysDataChart(AldCityVo vo);




}
