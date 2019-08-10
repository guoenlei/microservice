package com.ald.bigdata.modules.ad.dao.cluster;

import com.ald.bigdata.common.base.BaseDao;
import com.ald.bigdata.modules.ad.entity.AldAdAnomalyDataEntity;
import com.ald.bigdata.modules.ad.vo.AldAdAnomalyDataVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AldAdAnomalyDataClusterDao extends BaseDao<AldAdAnomalyDataEntity, AldAdAnomalyDataVo> {


    List<AldAdAnomalyDataEntity> queryDayAnomalyTimeOrIpDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryDayAnomalyAllDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnyDaysAnomalyTimeOrIpDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnyDaysAnomalyAllDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);



    List<AldAdAnomalyDataEntity> queryAnyDayAnomalyTimeOrIpDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);
    List<AldAdAnomalyDataEntity> queryAnyDayAnomalyTimeAndIpDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo);


}
