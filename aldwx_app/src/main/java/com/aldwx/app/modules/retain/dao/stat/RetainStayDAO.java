package com.aldwx.app.modules.retain.dao.stat;

import com.aldwx.app.modules.retain.entity.AldStayLogs;
import com.aldwx.app.modules.retain.vo.RetainVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RetainStayDAO {
    List<AldStayLogs> selectStayList(RetainVo vo);
    List<AldStayLogs> selectStayDetails(RetainVo vo);
}