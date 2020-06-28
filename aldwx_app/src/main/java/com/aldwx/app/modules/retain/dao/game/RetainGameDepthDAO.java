package com.aldwx.app.modules.retain.dao.game;

import com.aldwx.app.modules.retain.entity.AldstatVisitDepth;
import com.aldwx.app.modules.retain.vo.RetainVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RetainGameDepthDAO {
    List<AldstatVisitDepth> selectGameTotalByDepth(RetainVo vo);
    List<AldstatVisitDepth> selectGameTotalByAk(RetainVo vo);
}