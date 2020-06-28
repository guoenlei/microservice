package com.aldwx.bigdata.modules.ad.dao.presto;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.ad.entity.AldAdAnomalyDataEntity;
import com.aldwx.bigdata.modules.ad.vo.AldAdAnomalyDataVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AldAdAnomalyDataPrestoDao extends BaseDao<AldAdAnomalyDataEntity, AldAdAnomalyDataVo> {



    ///////////异常IP////////////
    List<AldAdAnomalyDataEntity> queryAnomalyIPTodayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnomalyIPYesterdayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnomalyIP7DayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnomalyIP30DayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnomalyIPDaysDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);


    ///////////异常Time////////////
    List<AldAdAnomalyDataEntity> queryAnomalyTimeTodayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnomalyTimeYesterdayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnomalyTime7DayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnomalyTime30DayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnomalyTimeDaysDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);


    ///////////异常ALL////////////
    List<AldAdAnomalyDataEntity> queryAnomalyAllTodayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnomalyAllYesterdayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnomalyAll7DayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnomalyAll30DayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnomalyAllDaysDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
}
