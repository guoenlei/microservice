package com.aldwx.app.modules.retain.dao.stat;

import com.aldwx.app.modules.retain.entity.AldstatUserActivity;
import com.aldwx.app.modules.retain.entity.AldstatUserActivityDetails;
import com.aldwx.app.modules.retain.vo.RetainVo;

import java.util.List;

public interface RetainActivityDetailsDAO {
    List<AldstatUserActivityDetails> selectActiveListDay(RetainVo vo);
    List<AldstatUserActivityDetails> selectActiveListWeek(RetainVo vo);
}