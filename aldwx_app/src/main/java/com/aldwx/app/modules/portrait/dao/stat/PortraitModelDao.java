package com.aldwx.app.modules.portrait.dao.stat;

import com.aldwx.app.modules.portrait.entity.AldDeviceStatistics;
import com.aldwx.app.modules.portrait.vo.PortraitVo;

import java.util.List;

public interface PortraitModelDao {
    List<AldDeviceStatistics> selectModelTotal(PortraitVo vo);
    List<AldDeviceStatistics> selectModelList(PortraitVo vo);

}