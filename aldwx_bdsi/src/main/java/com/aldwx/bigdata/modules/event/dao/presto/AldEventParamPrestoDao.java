//package com.aldwx.bigdata.modules.event.dao.presto;
//
//
//import com.aldwx.bigdata.common.base.BaseDao;
//import com.aldwx.bigdata.modules.eventParam.entity.AldEventParamEntity;
//import com.aldwx.bigdata.modules.eventParam.vo.AldEventParamVo;
//import com.facebook.presto.jdbc.internal.guava.annotations.Beta;
//import org.apache.ibatis.annotations.*;
//
//import java.util.List;
//
//@Mapper
//public interface AldEventParamPrestoDao extends BaseDao<AldEventParamEntity, AldEventParamVo> {
//
//
//
//    @Select("select app_key from aldstat_event_paras_partition where app_key=$0")
//    @Results({
//            @Result(property = "appKey", column = "app_key")
//    })
//    @Beta
//    List<AldEventParamEntity> selectOneBy(@Param("app_key") String appKey);
//
//
//    /**
//     * presto按日期查询
//     * @param vo
//     * @return
//     */
//    List<AldEventParamEntity> queryDaysDataByPresto(AldEventParamVo vo);
//
//
//
//}
