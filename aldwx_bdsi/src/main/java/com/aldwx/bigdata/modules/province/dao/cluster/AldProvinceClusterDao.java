package com.aldwx.bigdata.modules.province.dao.cluster;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.city.entity.AldCityEntity;
import com.aldwx.bigdata.modules.city.vo.AldCityVo;
import com.aldwx.bigdata.modules.province.entity.AldProvinceEntity;
import com.aldwx.bigdata.modules.province.vo.AldProvinceVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AldProvinceClusterDao extends BaseDao<AldProvinceEntity, AldProvinceVo> {

        /*****************TopN********************/

    /**
     * 获取今天数据  省份Top
     * @param vo
     * @return
     */
    List<AldProvinceEntity> queryTodayDataTop(AldProvinceVo vo);


    /**
     * 获取今天数据  省份总数
     * @param vo
     * @return
     */
    Long queryTodayDataSumTop(AldProvinceVo vo);


    /**
     * 获取昨天数据  省份Top
     * @param vo
     * @return
     */
    List<AldProvinceEntity> queryYesterdayDataTop(AldProvinceVo vo);


    /**
     * 获取今天数据  省份总数
     * @param vo
     * @return
     */
    Long queryYesterdayDataSumTop(AldProvinceVo vo);



    /*****************Chart********************/

    /**
     * 获取省份今天数据 chart
     * @param vo
     * @return
     */
    List<AldProvinceEntity> queryTodayDataChart(AldProvinceVo vo);


    /**
     * 获取省份昨天数据 chart
     * @param vo
     * @return
     */
    List<AldProvinceEntity> queryYesterdayDataChart(AldProvinceVo vo);


}
