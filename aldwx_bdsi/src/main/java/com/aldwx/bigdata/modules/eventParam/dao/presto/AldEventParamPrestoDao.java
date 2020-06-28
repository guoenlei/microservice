package com.aldwx.bigdata.modules.eventParam.dao.presto;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.eventParam.entity.AldEventParamEntity;
import com.aldwx.bigdata.modules.eventParam.vo.AldEventParamVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AldEventParamPrestoDao extends BaseDao<AldEventParamEntity, AldEventParamVo>  {



    /**
     * presto按日期查询
     * @param vo
     * @return
     */
    List<AldEventParamEntity> queryDaysDataByPresto(AldEventParamVo vo);


    /**
     * 获取prsto中总数
     * @param vo
     * @return
     */
    long queryCountDataByPresto(AldEventParamVo vo);



    List<AldEventParamEntity> queryByAk(AldEventParamVo vo);



}
