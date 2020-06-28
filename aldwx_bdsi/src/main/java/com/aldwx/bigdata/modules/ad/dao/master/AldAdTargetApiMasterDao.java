package com.aldwx.bigdata.modules.ad.dao.master;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.ad.entity.AldAdTargetApiEntity;
import com.aldwx.bigdata.modules.ad.vo.AldAdTargetApiVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface AldAdTargetApiMasterDao extends BaseDao<AldAdTargetApiEntity, AldAdTargetApiVo> {


    List<AldAdTargetApiEntity> byAppKey(AldAdTargetApiVo aldAdTargetApiVo);


    List<AldAdTargetApiEntity> getAppKey(AldAdTargetApiVo aldAdTargetApiVo);
}
