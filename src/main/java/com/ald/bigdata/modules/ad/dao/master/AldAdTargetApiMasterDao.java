package com.ald.bigdata.modules.ad.dao.master;
import com.ald.bigdata.common.base.BaseDao;
import com.ald.bigdata.modules.ad.entity.AldAdTargetApiEntity;
import com.ald.bigdata.modules.ad.vo.AldAdTargetApiVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AldAdTargetApiMasterDao extends BaseDao<AldAdTargetApiEntity, AldAdTargetApiVo> {


    List<AldAdTargetApiEntity> byAppKey(AldAdTargetApiVo aldAdTargetApiVo);


    List<AldAdTargetApiEntity> getAppKey(AldAdTargetApiVo aldAdTargetApiVo);
}
