package com.aldwx.app.modules.portrait.dao.game;

import com.aldwx.app.modules.portrait.entity.AldstatCityStatistics;
import com.aldwx.app.modules.portrait.vo.PortraitVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PortraitGameCityDao {
   List<AldstatCityStatistics> selectCityTotal(PortraitVo vo);
   List<AldstatCityStatistics> selectCityList(PortraitVo vo);
}