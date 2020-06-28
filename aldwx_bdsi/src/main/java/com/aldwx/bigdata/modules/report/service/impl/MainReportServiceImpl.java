package com.aldwx.bigdata.modules.report.service.impl;

import com.aldwx.bigdata.common.base.report.ReportDao;
import com.aldwx.bigdata.common.base.report.ReportService;
import com.aldwx.bigdata.modules.report.dao.bus.AldBusReportDao;
import com.aldwx.bigdata.modules.report.dao.master.AldReportMasterDao;
import com.aldwx.bigdata.modules.report.entity.AldReportEntity;
import com.aldwx.bigdata.modules.report.service.AldStatReportService;
import com.aldwx.bigdata.modules.report.vo.AldReportVo;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 报告接口
 * @author
 * @description
 * @createTime
 **/

public abstract class MainReportServiceImpl implements ReportService {

    private static final Logger LOG = LoggerFactory.getLogger(MainReportServiceImpl.class);

    /**
     * 小程序统计平台
     */
//    @Autowired
    protected ReportDao aldReportMasterDao;
    /**
     * service_bus
     */
    @Autowired
    private AldBusReportDao aldReportBusDao;


    @Override
    public List<String> registUser(AldReportVo aldReportVo) {
        return aldReportBusDao.registUser(aldReportVo);
    }

    @Override
    public Long registCount(AldReportVo aldReportVo) {
        aldReportVo.setPlatform("1");
        Long registCount = aldReportBusDao.registCount(aldReportVo);
        return null == registCount ? 0L : registCount;
    }

    @Override
    public Long appCount(AldReportVo aldReportVo) {
        aldReportVo.setPlatform("1");
        Long appCount = aldReportMasterDao.appCount(aldReportVo);
        return null == appCount ? 0L : appCount;
    }

    @Override
    public Long newUserCount(AldReportVo aldReportVo) {
        Long newUserCount = aldReportMasterDao.newUserCount(aldReportVo);
        return null == newUserCount ? 0L : newUserCount;
    }

    @Override
    public Long oldUserCount(AldReportVo aldReportVo) {
        Long oldUserCount = aldReportMasterDao.oldUserCount(aldReportVo);
        return null == oldUserCount ? 0L : oldUserCount;
    }

    @Override
    public Long appCountByNewUser(AldReportVo aldReportVo) {
        //获取所有注册用户信息
//        List<String> ids = aldReportBusDao.registUser(aldReportVo);
////        aldReportVo.setTime(aldReportVo, "4");
//        aldReportVo.setUserIds(ids);
        Long appCount = aldReportMasterDao.appCountByNewUser(aldReportVo);
        return null == appCount ? 0L : appCount;
    }

    @Override
    public Long appCountByOldUser(AldReportVo aldReportVo) {
        //获取注册用户信息
//        List<String> ids = aldReportBusDao.registUser(aldReportVo);
//        aldReportVo.setUserIds(ids);
        Long appCount = aldReportMasterDao.appCountByOldUser(aldReportVo);
        return null == appCount ? 0L : appCount;
    }

    @Override
    public Long reportSDKCount(AldReportVo aldReportVo) {
        Long reportSDKCount = aldReportMasterDao.reportSDKCount(aldReportVo);
        return null == reportSDKCount ? 0L : reportSDKCount;
    }

    @Override
    public Long reportSDKCountByNewUser(AldReportVo aldReportVo) {
        Long reportSDKCount = aldReportMasterDao.reportSDKCountByNewUser(aldReportVo);
        return null == reportSDKCount ? 0L : reportSDKCount;
    }

    @Override
    public Long reportSDKCountByOldUser(AldReportVo aldReportVo) {
        Long reportSDKCount = aldReportMasterDao.reportSDKCountByOldUser(aldReportVo);
        return null == reportSDKCount ? 0L : reportSDKCount;
    }


    @Override
//    平台：1-统计，2-指数，3-小神推，4-小程序，5-小码
    public Long loginCount(AldReportVo aldReportVo) {
        aldReportVo.setPlatform("1");
        Long loginCount = aldReportBusDao.loginCount(aldReportVo);
        return null == loginCount ? 0L : loginCount;
    }
    @Override
    public Long reportSDKCreatedCountByVOL(AldReportVo aldReportVo) {
        Long reportSDKCount = aldReportMasterDao.appCreatedSumByVOL(aldReportVo);
        return null == reportSDKCount ? 0L : reportSDKCount;
    }
    /**
     * 流失率
     * @param aldReportVo
     * @return
     */
    @Override
    public Double reportSDKDrain(AldReportVo aldReportVo) {
        Double reportSDKDrain = aldReportMasterDao.reportSDKDrain(aldReportVo);
        return null == reportSDKDrain ? 0L : reportSDKDrain;
    }

    @Override
    public Double activeEventFunSDKProp(AldReportVo aldReportVo) {
//        Long reportSDKCount = aldReportMasterDao.reportSDKCount(aldReportVo);
        Long activeEventFunSDKCount = aldReportMasterDao.activeEventFunSDKProp(aldReportVo);
//        LOG.info("总SDK上报：{}", reportSDKCount);
        LOG.info("事件SDK上报：{}", activeEventFunSDKCount);
//        if(reportSDKCount > 0) {
//            return Arith.div(NumberUtils.toDouble(null == activeEventFunSDKCount ? "" : activeEventFunSDKCount+""),
//                    NumberUtils.toDouble(reportSDKCount+""));
//        } else {
//            return 0D;
//        }
        return NumberUtils.toDouble(null == activeEventFunSDKCount ? "" : activeEventFunSDKCount+"");
    }

    @Override
    public Double activeAdFunSDKProp(AldReportVo aldReportVo) {
//        Long reportSDKCount = aldReportMasterDao.reportSDKCount(aldReportVo);
        Long activeAdFunSDKCount = aldReportMasterDao.activeAdFunSDKProp(aldReportVo);
//        LOG.info("总SDK上报：{}", reportSDKCount);
        LOG.info("广告SDK上报：{}", activeAdFunSDKCount);
//        if(reportSDKCount > 0) {
//            return Arith.div(NumberUtils.toDouble(null == activeAdFunSDKCount ? "" : activeAdFunSDKCount+""),
//                    NumberUtils.toDouble(reportSDKCount+""));
//        } else {
//            return 0D;
//        }
        return NumberUtils.toDouble(null == activeAdFunSDKCount ? "" : activeAdFunSDKCount+"");
    }

    @Override
    public Double activeUserFunSDKProp(AldReportVo aldReportVo) {
//        Long reportSDKCount = aldReportMasterDao.reportSDKCount(aldReportVo);
        Long activeUserFunSDKCount = aldReportMasterDao.activeUserFunSDKProp(aldReportVo);
//        LOG.info("总SDK上报：{}", reportSDKCount);
        LOG.info("用户分群SDK上报：{}", activeUserFunSDKCount);
//        if(reportSDKCount > 0) {
//            return Arith.div(NumberUtils.toDouble(null == activeUserFunSDKCount ? "" : activeUserFunSDKCount+""),
//                    NumberUtils.toDouble(reportSDKCount+""));
//        } else {
//            return 0D;
//        }
        return NumberUtils.toDouble(null == activeUserFunSDKCount ? "" : activeUserFunSDKCount+"");
    }

    @Override
    public Double activeLinkFunSDKProp(AldReportVo aldReportVo) {
//        Long reportSDKCount = aldReportMasterDao.reportSDKCount(aldReportVo);
        Long activeLinkFunSDKCount = aldReportMasterDao.activeLinkFunSDKProp(aldReportVo);
//        LOG.info("总SDK上报：{}", reportSDKCount);
        LOG.info("智能外链SDK上报：{}", activeLinkFunSDKCount);
//        if(reportSDKCount > 0) {
//            return Arith.div(NumberUtils.toDouble(null == activeLinkFunSDKCount ? "" : activeLinkFunSDKCount+""),
//                    NumberUtils.toDouble(reportSDKCount+""));
//        } else {
//            return 0D;
//        }
        return NumberUtils.toDouble(null == activeLinkFunSDKCount ? "" : activeLinkFunSDKCount+"");
    }


    @Override
    public Double activeBarCodeFunSDKProp(AldReportVo aldReportVo) {
//        Long reportSDKCount = aldReportMasterDao.reportSDKCount(aldReportVo);
        Long activeBarCodeFunSDKCount = aldReportMasterDao.activeBarCodeFunSDKProp(aldReportVo);
//        LOG.info("总SDK上报：{}", reportSDKCount);
        LOG.info("二维码SDK上报：{}", activeBarCodeFunSDKCount);
//        if(reportSDKCount > 0) {
//            return Arith.div(NumberUtils.toDouble(null == activeBarCodeFunSDKCount ? "" : activeBarCodeFunSDKCount+""),
//                    NumberUtils.toDouble(reportSDKCount+""));
//        } else {
//            return 0D;
//        }
        return NumberUtils.toDouble(null == activeBarCodeFunSDKCount ? "" : activeBarCodeFunSDKCount+"");
    }

    @Override
    public List<AldReportEntity> registBusTop(AldReportVo aldReportVo) {
        return null;
    }

}
