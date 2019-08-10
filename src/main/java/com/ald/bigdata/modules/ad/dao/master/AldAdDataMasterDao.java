package com.ald.bigdata.modules.ad.dao.master;

import com.ald.bigdata.common.base.BaseDao;
import com.ald.bigdata.modules.ad.entity.AldAdDataEntity;
import com.ald.bigdata.modules.ad.vo.AldAdDataVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AldAdDataMasterDao extends BaseDao<AldAdDataEntity, AldAdDataVo> {



    Map<String, Object> openPageCount(AldAdDataVo aldAdDataVo);

    Map<String, Object> newUserCount(AldAdDataVo aldAdDataVo);

    Map<String, Object> visitUserCount(AldAdDataVo aldAdDataVo);


    List<AldAdDataEntity> queryAdDataListByHour(AldAdDataVo aldAdDataVo);
    List<AldAdDataEntity> queryAd7Or30DaysDataListBy(AldAdDataVo aldAdDataVo);
    List<AldAdDataEntity> queryAdDaysDataListBy(AldAdDataVo aldAdDataVo);

}
