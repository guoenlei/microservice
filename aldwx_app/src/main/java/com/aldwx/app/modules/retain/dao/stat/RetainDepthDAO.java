package com.aldwx.app.modules.retain.dao.stat;

import com.aldwx.app.modules.retain.entity.AldstatVisitDepth;
import com.aldwx.app.modules.retain.vo.RetainVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RetainDepthDAO {
    List<AldstatVisitDepth> selectTotalByDepth(RetainVo vo);
    List<AldstatVisitDepth> selectTotalByAk(RetainVo vo);
}