package com.aldwx.bigdata.modules.trend.dao.master;

import com.aldwx.bigdata.modules.trend.entity.Aldstat30daysTrendAnalysis;
import com.aldwx.bigdata.modules.trend.vo.TrendQueryVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Aldstat30daysTrendAnalysisDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Aldstat30daysTrendAnalysis record);

    int insertSelective(Aldstat30daysTrendAnalysis record);

    Aldstat30daysTrendAnalysis selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Aldstat30daysTrendAnalysis record);

    int updateByPrimaryKey(Aldstat30daysTrendAnalysis record);
    Aldstat30daysTrendAnalysis selectTrendAnalysis30daysByDayAndAk(TrendQueryVo vo);
}