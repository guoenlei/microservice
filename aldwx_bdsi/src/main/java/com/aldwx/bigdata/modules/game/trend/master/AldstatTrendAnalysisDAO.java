package com.aldwx.bigdata.modules.game.trend.master;

import com.aldwx.bigdata.modules.game.trend.entity.AldstatTrendAnalysis;
import com.aldwx.bigdata.modules.game.trend.vo.TrendQueryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AldstatTrendAnalysisDAO {
    int deleteByPrimaryKey(Long id);

    int insert(AldstatTrendAnalysis record);

    int insertSelective(AldstatTrendAnalysis record);

    AldstatTrendAnalysis selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AldstatTrendAnalysis record);

    int updateByPrimaryKey(AldstatTrendAnalysis record);

    AldstatTrendAnalysis selectTrendAnalysisByDayAndAk(TrendQueryVo vo);
    List<AldstatTrendAnalysis> selectTrendAnalysisByRangeAndAk(TrendQueryVo vo);
}