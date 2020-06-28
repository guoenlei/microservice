package com.aldwx.bigdata.modules.share.dao.cluster;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.share.entity.AldUserShareEntity;
import com.aldwx.bigdata.modules.share.vo.AldUserShareVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AldUserShareClusterDao extends BaseDao<AldUserShareEntity, AldUserShareVo> {
}
