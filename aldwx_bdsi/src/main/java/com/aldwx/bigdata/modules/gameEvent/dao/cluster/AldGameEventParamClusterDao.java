package com.aldwx.bigdata.modules.gameEvent.dao.cluster;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.gameEvent.entity.AldGameEventParamEntity;
import com.aldwx.bigdata.modules.gameEvent.vo.AldGameEventParamVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AldGameEventParamClusterDao extends BaseDao<AldGameEventParamEntity, AldGameEventParamVo> {


    /**
     * 查询今天事件明细
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryEventParamDetailTodayBy(AldGameEventParamVo vo);


    /**
     * 查询昨天事件明细
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryEventParamDetailYesterdayBy(AldGameEventParamVo vo);





    /**
     * 查询今天列表
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryEventParamListTodayBy(AldGameEventParamVo vo);


    /**
     * 查询昨天列表
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryEventParamListYesterdayBy(AldGameEventParamVo vo);





}
