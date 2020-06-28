package com.aldwx.app.modules.portrait.service;


import com.aldwx.app.modules.portrait.entity.AldstatCityStatistics;
import com.aldwx.app.modules.portrait.vo.PortraitVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface PortraitService {

    Map<String, Object> selectCityTop(PortraitVo vo);
    Map<String, Object> selectCityDetail(PortraitVo vo);
    Map<String, Object> selectModelTop(PortraitVo vo);
    Map<String, Object> selectModelDetail(PortraitVo vo);
    Map<String, Object> selectTerminalTop(PortraitVo vo);
    Map<String, Object> selectTerminalDetail(PortraitVo vo);
    Map<String, Object> selectNetTop(PortraitVo vo);
    Map<String, Object> selectNetDetail(PortraitVo vo);
}
