package com.aldwx.bigdata.modules.province.dao.presto;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.city.entity.AldCityEntity;
import com.aldwx.bigdata.modules.city.vo.AldCityVo;
import com.aldwx.bigdata.modules.province.entity.AldProvinceEntity;
import com.aldwx.bigdata.modules.province.vo.AldProvinceVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AldProvincePrestoDao extends BaseDao<AldProvinceEntity, AldProvinceVo> {


    /**
     * topN
     * @param vo
     * @return
     */
    List<AldProvinceEntity> query7DayDataTop(AldProvinceVo vo);
    Long query7DayDataSumTop(AldProvinceVo vo);

    List<AldProvinceEntity> query30DayDataTop(AldProvinceVo vo);
    Long query30DayDataSumTop(AldProvinceVo vo);

    List<AldProvinceEntity> queryDaysDataTop(AldProvinceVo vo);
    Long queryDaysDataSumTop(AldProvinceVo vo);


    /**
     * chart
     * @param vo
     * @return
     */
    List<AldProvinceEntity> query7DayDataChart(AldProvinceVo vo);
    List<AldProvinceEntity> query30DayDataChart(AldProvinceVo vo);
    List<AldProvinceEntity> queryDaysDataChart(AldProvinceVo vo);



}
