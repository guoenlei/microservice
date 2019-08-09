package com.ald.bigdata.modules.eventParam.dao.master;

import com.ald.bigdata.common.base.BaseDao;
import com.ald.bigdata.modules.eventParam.entity.AldEventParamEntity;
import com.ald.bigdata.modules.eventParam.vo.AldEventParamVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AldEventParamMasterDao extends BaseDao<AldEventParamEntity, AldEventParamVo> {



}
