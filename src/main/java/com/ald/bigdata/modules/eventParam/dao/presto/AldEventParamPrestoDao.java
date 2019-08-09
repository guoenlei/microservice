package com.ald.bigdata.modules.eventParam.dao.presto;


import com.ald.bigdata.common.base.BaseDao;
import com.ald.bigdata.modules.eventParam.entity.AldEventParamEntity;
import com.ald.bigdata.modules.eventParam.vo.AldEventParamVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AldEventParamPrestoDao extends BaseDao<AldEventParamEntity, AldEventParamVo> {



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
