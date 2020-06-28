package com.aldwx.bigdata.modules.game.trend.master;

import com.aldwx.bigdata.modules.game.trend.entity.AldstatHourlyTrendAnalysis;
import com.aldwx.bigdata.modules.game.trend.vo.TrendQueryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AldstatHourlyTrendAnalysisDAO {
    int deleteByPrimaryKey(Long id);

    int insert(AldstatHourlyTrendAnalysis record);

    int insertSelective(AldstatHourlyTrendAnalysis record);

    AldstatHourlyTrendAnalysis selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AldstatHourlyTrendAnalysis record);

    int updateByPrimaryKey(AldstatHourlyTrendAnalysis record);

    //查询今天小时的数据
    List<AldstatHourlyTrendAnalysis> selectTrendAnalysisHourByDayAndAk(TrendQueryVo vo);
}