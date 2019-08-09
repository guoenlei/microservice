package com.ald.bigdata.modules.gameEvent.dao.presto;

import com.ald.bigdata.common.base.BaseDao;
import com.ald.bigdata.modules.gameEvent.entity.AldGameEventParamEntity;
import com.ald.bigdata.modules.gameEvent.vo.AldGameEventParamVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AldGameEventParamPrestoDao extends BaseDao<AldGameEventParamEntity, AldGameEventParamVo> {




    /**
     * 查询今天事件明细
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryEventParamDetailTodayBy(AldGameEventParamVo vo);
    Long queryEventParamDetailCountTodayBy(AldGameEventParamVo vo);

    /**
     * 查询昨天事件明细
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryEventParamDetailYesterdayBy(AldGameEventParamVo vo);
    Long queryEventParamDetailCountYesterdayBy(AldGameEventParamVo vo);



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




    /**
     * 获取数据并排序
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryPrestoDataOrderBy(AldGameEventParamVo vo);


    /**
     * 基于7天表消息数排序
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryPresto7DaysDataOrderByParamCount(AldGameEventParamVo vo);


    /**
     * 基于7天表用户排序
     * @return
     */
    List<AldGameEventParamEntity> queryPresto7DaysDataOrderByUvCount(AldGameEventParamVo vo);

    /**
     * 基于7天表默认排序
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryPresto7DaysDataOrderByDefault(AldGameEventParamVo vo);


    /**
     * 基于30天表消息数排序
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryPresto30DaysDataOrderByParamCount(AldGameEventParamVo vo);


    /**
     * 基于30天表用户排序
     * @return
     */
    List<AldGameEventParamEntity> queryPresto30DaysDataOrderByUvCount(AldGameEventParamVo vo);


    /**
     * 基于30天表默认排序
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryPresto30DaysDataOrderByDefault(AldGameEventParamVo vo);


    /**
     * 指定时间区间查询 基于消息总数排序
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryPrestoDaysDataOrderByParamCount(AldGameEventParamVo vo);


    /**
     * 指定时间区间查询 基于UV总数排序
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryPrestoDaysDataOrderByUvCount(AldGameEventParamVo vo);


    /**
     * 指定时间区间查询 基于默认排序
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryPrestoDaysDataOrderByDefault(AldGameEventParamVo vo);

//
//    /**
//     * 获取总数
//     * @param vo
//     * @return
//     */
//    Long query30DayCountDataBy(AldGameEventParamVo vo);
//    Long query7DayCountDataBy(AldGameEventParamVo vo);
//    Long queryDaysCountDataBy(AldGameEventParamVo vo);





/************************事件参数列表****************************

    /**
     * 从presto中获取事件列表最近7天的数据
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryEventParamList7DaysDataBy(AldGameEventParamVo vo);


    /**
     * 从presto中获取事件列表最近30天的数据
     * @return
     */

    List<AldGameEventParamEntity> queryEventParamList30DaysDataBy(AldGameEventParamVo vo);


    /**
     * 从presto中获取事件列表指定日期范围的数据
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryEventParamListDaysDataBy(AldGameEventParamVo vo);




}
