package com.aldwx.bigdata.modules.terminal.dao.master;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.terminal.entity.TerminalEntity;
import com.aldwx.bigdata.modules.terminal.vo.AldTerminalVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AldTerminalMasterDao extends BaseDao<TerminalEntity, AldTerminalVo> {

//
//    List<TerminalEntity> queryList(AldTerminalVo vo);
//
////    @Select("SELECT * FROM aldstat_event_paras_partition")
////    @Results({
////            @Result(property = "appKey", column = "app_key"),
////            @Result(property = "day", column = "day"),
////            @Result(property = "id", column = "ev_id"),
////    })
//    /**
//     * 获取实体类
//     * @param vo
//     * @return
//     */
////    @Select("select from aldstat_event_paras_partition")
////    @Results({})
////    List<TerminalEntity> queryTerminalEntityList(AldTerminalVo vo);


}
