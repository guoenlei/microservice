package com.aldwx.bigdata.modules.share.dao.master;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.share.entity.AldUserShareEntity;
import com.aldwx.bigdata.modules.share.vo.AldUserShareVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AldUserShareMasterDao extends BaseDao<AldUserShareEntity, AldUserShareVo> {

}
