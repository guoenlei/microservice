package com.aldwx.bigdata.modules.terminal.dao.presto;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.terminal.entity.TerminalEntity;
import com.aldwx.bigdata.modules.terminal.vo.AldTerminalVo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AldTerminalPrestoDao extends BaseDao<TerminalEntity, AldTerminalVo>  {


//    /**
//     * 获取presto数据
//     * @param vo
//     * @return
//     */
//    @Select("SELECT * FROM aldstat_event_paras_partition")
//    @Results({
//            @Result(property = "appKey", column = "app_key"),
//            @Result(property = "day", column = "day"),
//            @Result(property = "id", column = "ev_id"),
//    })
//    List<TerminalEntity> queryPrestoList(AldTerminalVo vo);
//
//
//
//    long queryDataCount(AldTerminalVo vo);
//
//
//    /**
//     *
//     * @param vo
//     * @return
//     */
//    List<String> queryByAk(AldTerminalVo vo);
//
//
//    /**
//     *
//     * @return
//     */
//    @Results({
//            @Result(property = "appKey", column = "app_key")
//    })
//    @Select("select app_key from aldstat_event_paras_partition where 1=1 " +
//            "AND parti_day in ('2018-10-12') ")
//    List<String> queryId();

}
