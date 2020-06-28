package com.aldwx.bigdata.modules.report.service.impl;

import com.aldwx.bigdata.modules.report.dao.app.MiniReportDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 报告接口
 *
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class MiniReportServiceImpl extends MainReportServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(MiniReportServiceImpl.class);

    @Autowired
    @Qualifier("miniReportDao")
    private void setReportDao(MiniReportDao aldReportMasterDao) {

        this.aldReportMasterDao = aldReportMasterDao;
    }

}
