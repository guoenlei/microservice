package com.aldwx.app.modules.retain.dao.game;

import com.aldwx.app.modules.retain.entity.AldstatVisitDuration;
import com.aldwx.app.modules.retain.vo.RetainVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RetainGameDurationDAO {
    List<AldstatVisitDuration> selectGameTotalByDuration(RetainVo vo);
    List<AldstatVisitDuration> selectGameTotalByAk(RetainVo vo);
}