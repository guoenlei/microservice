package com.aldwx.bigdata.modules.ad.dao.master;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.ad.entity.AldAdDataEntity;
import com.aldwx.bigdata.modules.ad.vo.AldAdDataVo;
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
