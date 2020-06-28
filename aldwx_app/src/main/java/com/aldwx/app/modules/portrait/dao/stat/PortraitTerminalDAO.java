package com.aldwx.app.modules.portrait.dao.stat;

import com.aldwx.app.modules.portrait.entity.AldstatTerminalAnalysis;
import com.aldwx.app.modules.portrait.vo.PortraitVo;

import java.util.List;

public interface PortraitTerminalDAO {
    List<AldstatTerminalAnalysis> selectTerminalTotal(PortraitVo vo);
    List<AldstatTerminalAnalysis> selectTerminalList(PortraitVo vo);
    List<AldstatTerminalAnalysis> selectNetTotal(PortraitVo vo);
    List<AldstatTerminalAnalysis> selectNetList(PortraitVo vo);
}