package com.aldwx.bigdata.modules.ad.service;

import com.aldwx.bigdata.common.base.BaseService;
import com.aldwx.bigdata.modules.ad.entity.AldAdAnomalyDataEntity;
import com.aldwx.bigdata.modules.ad.vo.AldAdAnomalyDataVo;

import java.util.List;
import java.util.Map;


public interface AldAdAnomalyDataService extends BaseService<AldAdAnomalyDataEntity, AldAdAnomalyDataVo> {


    Map<String, Object> cheatDataCount(AldAdAnomalyDataVo aldAdAnomalyDataVo);



//    List<AldAdAnomalyDataEntity> queryDayAnomalyTimeOrIpDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
//    List<AldAdAnomalyDataEntity> queryDayAnomalyAllDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
//    List<AldAdAnomalyDataEntity> queryAnyDaysAnomalyTimeOrIpDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
//    List<AldAdAnomalyDataEntity> queryAnyDaysAnomalyAllDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);

    //折线图
    List<AldAdAnomalyDataEntity> queryDayAnomalyChartDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnyDaysAnomalyChartDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);

}
