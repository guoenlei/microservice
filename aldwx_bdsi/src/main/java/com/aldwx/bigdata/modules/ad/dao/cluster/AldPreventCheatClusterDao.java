package com.aldwx.bigdata.modules.ad.dao.cluster;


import com.aldwx.bigdata.modules.ad.entity.AldPreventCheat;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AldPreventCheatClusterDao {


    @Results(
            value = {
                    @Result(property = "appKey", column = "app_key"),
                    @Result(property = "diffTimeType", column = "diff_time_type"),
                    @Result(property = "ipclkCountType", column = "ipclk_count_type"),
            })
    @Select("select app_key, diff_time_type, ipclk_count_type from ald_prevent_cheat where app_key = #{appKey}")
    List<AldPreventCheat> seleteEntityBy(String appKey);
}