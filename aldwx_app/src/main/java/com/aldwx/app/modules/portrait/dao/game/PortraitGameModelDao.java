package com.aldwx.app.modules.portrait.dao.game;

import com.aldwx.app.modules.portrait.entity.AldDeviceStatistics;
import com.aldwx.app.modules.portrait.vo.PortraitVo;

import java.util.List;

public interface PortraitGameModelDao {
    List<AldDeviceStatistics> selectModelTotal(PortraitVo vo);
    List<AldDeviceStatistics> selectModelList(PortraitVo vo);

}