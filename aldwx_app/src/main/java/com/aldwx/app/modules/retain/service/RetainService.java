package com.aldwx.app.modules.retain.service;

import com.aldwx.app.modules.retain.vo.RetainVo;

import java.util.Map;

public interface RetainService {
    Map<String, Object> selectRetainLineChart(RetainVo vo);
    Map<String, Object> selectRetainDetail(RetainVo vo);
    Map<String, Object> selectActiveLineChart(RetainVo vo);
    Map<String, Object> selectActiveDetail(RetainVo vo);
    Map<String, Object> selectDepthLineChart(RetainVo vo);
    Map<String, Object> selectDepthDetail(RetainVo vo);
}
