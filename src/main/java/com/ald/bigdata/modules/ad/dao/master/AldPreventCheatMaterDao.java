package com.ald.bigdata.modules.ad.dao.master;

import com.ald.bigdata.modules.ad.entity.AldPreventCheat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AldPreventCheatMaterDao {

    @Results(
            value = {
                    @Result(property = "appKey", column = "app_key"),
                    @Result(property = "diffTimeType", column = "diff_time_type"),
                    @Result(property = "oauthCountType", column = "oauth_count_type"),
                    @Result(property = "ipclkCountType", column = "ipclk_count_type"),
            })
    @Select("select app_key, diff_time_type, oauth_count_type, ipclk_count_type from ald_prevent_cheat " +
            "where app_key = #{appKey}")
    List<AldPreventCheat> seleteEntityBy(String appKey);
}