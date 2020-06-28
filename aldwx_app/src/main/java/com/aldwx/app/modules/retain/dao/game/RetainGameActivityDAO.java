package com.aldwx.app.modules.retain.dao.game;

import com.aldwx.app.modules.retain.entity.AldStayLogs;
import com.aldwx.app.modules.retain.entity.AldstatUserActivity;
import com.aldwx.app.modules.retain.vo.RetainVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RetainGameActivityDAO {

    List<AldstatUserActivity> selectGameActiveList(RetainVo vo);
}