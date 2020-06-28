package com.aldwx.app.modules.retain.dao.game;

import com.aldwx.app.modules.retain.entity.AldStayLogs;
import com.aldwx.app.modules.retain.vo.RetainVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RetainGameStayDAO {
    List<AldStayLogs> selectGameStayList(RetainVo vo);
    List<AldStayLogs> selectGameStayDetails(RetainVo vo);
}