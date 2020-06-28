package com.aldwx.bigdata.modules.report.service;

import com.aldwx.bigdata.modules.report.vo.AldReportVo;
import com.alibaba.fastjson.JSONObject;

public interface ReportMainService {
    public JSONObject generateReport(String type, AldReportVo aldReportVo) ;
}
