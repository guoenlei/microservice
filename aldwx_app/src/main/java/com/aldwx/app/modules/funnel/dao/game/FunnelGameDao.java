package com.aldwx.app.modules.funnel.dao.game;

import com.aldwx.app.common.base.BaseDao;
import com.aldwx.app.modules.funnel.bean.Funnel;
import com.aldwx.app.modules.funnel.entity.FunnelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 漏斗
 * @author
 * @description
 * @createTime
 **/
@Mapper
public interface FunnelGameDao extends BaseDao {


    /**
     * 行为 - 转换漏斗 - 来源
     * @param funnel
     * @return
     */
    List<FunnelEntity> querySourceView(Funnel funnel);

    /**
     * 行为 - 转换漏斗 - 列表
     * @param funnel
     * @return
     */
    @Results(
            value = {
                    @Result(property = "day", column = "day"),
                    @Result(property = "funnelKey", column = "funnel_key"),
                    @Result(property = "firstEventKey", column = "first_event_key"),
                    @Result(property = "firstEventAliasRes", column = "first_event_alias_res"),
                    @Result(property = "fuv", column = "fuv"),
                    @Result(property = "lastEventKey", column = "last_event_key"),
                    @Result(property = "lastEventAliasRes", column = "last_event_alias_res"),
                    @Result(property = "luv", column = "luv"),
                    @Result(property = "conv", column = "conv"),
            })
    @Select("select fr.app_key,fr.funnel_key,fr.first_event_key,fr.first_event_alias_res,fr.fuv " +
            ",last.last_event_key,last.last_event_alias_res,last.luv,last.luv/fr.fuv conv " +
            "from (" +
            "select b.app_key,a.funnel_key,a.first_event_key,a.first_event_alias_res,sum(b.user_view) fuv from ald_funnel a,aldstat_daily_funnel b where a.app_key =#{appKey} and a.first_event_key=b.event_key " +
            "and a.funnel_key=b.funnel_key and b.day in #{dateSql} and b.source_key='default' and b.source_value='default' group by b.app_key,a.funnel_key,a.first_event_key,a.first_event_alias_res) fr," +
            " (" +
            "select b.app_key,a.funnel_key,a.last_event_key,a.last_event_alias_res,sum(b.user_view) luv from ald_funnel a,aldstat_daily_funnel b where a.app_key =#{appKey} and a.last_event_key=b.event_key " +
            "and a.funnel_key=b.funnel_key and b.day in #{dateSql} and b.source_key='default' and b.source_value='default'" +
            " group by  b.app_key,a.funnel_key,a.last_event_key,a.last_event_alias_res)  last where fr.app_key=last.app_key and fr.funnel_key=last.funnel_key ")
    List<FunnelEntity> queryFunnelDataList(Funnel funnel);


    /**
     * 行为 - 转换漏斗 - 步骤明细
     * @param funnel
     * @return
     */
    List<FunnelEntity> queryFunnelDetail(Funnel funnel);




}
