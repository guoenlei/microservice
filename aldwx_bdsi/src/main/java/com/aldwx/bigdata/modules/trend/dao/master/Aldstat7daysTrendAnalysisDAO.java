package com.aldwx.bigdata.modules.trend.dao.master;

import com.aldwx.bigdata.modules.trend.entity.Aldstat7daysTrendAnalysis;
import com.aldwx.bigdata.modules.trend.vo.TrendQueryVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Aldstat7daysTrendAnalysisDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Aldstat7daysTrendAnalysis record);

    int insertSelective(Aldstat7daysTrendAnalysis record);

    Aldstat7daysTrendAnalysis selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Aldstat7daysTrendAnalysis record);

    int updateByPrimaryKey(Aldstat7daysTrendAnalysis record);
    Aldstat7daysTrendAnalysis selectTrendAnalysis7daysDayAndAk(TrendQueryVo vo);
}