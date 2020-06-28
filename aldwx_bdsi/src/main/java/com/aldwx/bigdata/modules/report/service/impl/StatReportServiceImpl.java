package com.aldwx.bigdata.modules.report.service.impl;

import com.aldwx.bigdata.modules.report.dao.stat.StatReportDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 报告接口
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class StatReportServiceImpl extends MainReportServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(StatReportServiceImpl.class);

    @Autowired
    @Qualifier("statReportDao")
    private void setReportDao(StatReportDao aldReportMasterDao){
        this.aldReportMasterDao = aldReportMasterDao;
    }

}
