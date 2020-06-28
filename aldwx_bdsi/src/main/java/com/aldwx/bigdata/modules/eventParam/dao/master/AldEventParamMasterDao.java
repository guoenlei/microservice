package com.aldwx.bigdata.modules.eventParam.dao.master;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.eventParam.entity.AldEventParamEntity;
import com.aldwx.bigdata.modules.eventParam.vo.AldEventParamVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AldEventParamMasterDao extends BaseDao<AldEventParamEntity, AldEventParamVo>  {



}
