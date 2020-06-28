package com.aldwx.app.modules.retain.dao.stat;

import com.aldwx.app.modules.retain.entity.AldstatVisitDuration;
import com.aldwx.app.modules.retain.vo.RetainVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RetainDurationDAO {
    List<AldstatVisitDuration> selectTotalByDuration(RetainVo vo);
    List<AldstatVisitDuration> selectTotalByAk(RetainVo vo);
}