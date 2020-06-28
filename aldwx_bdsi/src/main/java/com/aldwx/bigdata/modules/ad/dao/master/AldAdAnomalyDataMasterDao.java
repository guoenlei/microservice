package com.aldwx.bigdata.modules.ad.dao.master;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.ad.entity.AldAdAnomalyDataEntity;
import com.aldwx.bigdata.modules.ad.vo.AldAdAnomalyDataVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AldAdAnomalyDataMasterDao extends BaseDao<AldAdAnomalyDataEntity, AldAdAnomalyDataVo> {






    List<AldAdAnomalyDataEntity> queryAnyDayAnomalyTimeOrIpDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnyDayAnomalyTimeAndIpDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);


//
//    @Results(
//            value = {
//                    @Result(property = "appKey", column = "app_key"),
//                    @Result(property = "diffTimeType", column = "diff_time_type"),
//                    @Result(property = "diffTimeType", column = "ipclk_count_type"),
//            })
//    @Select("select app_key, diff_time_type, ipclk_count_type from ald_prevent_cheat where app_key = #{app_Key}")
    AldAdAnomalyDataEntity queryAnyDayAnomalyCountBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);



    List<AldAdAnomalyDataEntity> cheatDataCountByDay(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> cheatDataCountByHour(AldAdAnomalyDataVo aldAdAnomalyDataVo);



    /*******************/

    List<AldAdAnomalyDataEntity> queryDayAnomalyTimeOrIpDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryDayAnomalyAllDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnyDaysAnomalyTimeOrIpDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnyDaysAnomalyAllDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);


    //折线图
    List<AldAdAnomalyDataEntity> queryDayAnomalyChartDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnyDaysAnomalyChartDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);

}
