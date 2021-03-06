package com.aldwx.bigdata.common.base.report;

import com.aldwx.bigdata.modules.report.entity.AldReportEntity;
import com.aldwx.bigdata.modules.report.vo.AldReportVo;

import java.util.List;

public interface ReportService {


    /**
     * 注册用户
     * @param aldReportVo
     * @return
     */
    List<String> registUser(AldReportVo aldReportVo);


    /**
     * 注册量
     * @return
     */
    Long registCount(AldReportVo aldReportVo);


    /**
     * 小程序创建总量
     * @return
     */
    Long appCount(AldReportVo aldReportVo);


    /**
     * 新用户
     * @return
     */
    Long newUserCount(AldReportVo aldReportVo);


    /**
     * 老用户
     * @return
     */
    Long oldUserCount(AldReportVo aldReportVo);


    /**
     * 新用户创建小程序数
     * @param aldReportVo
     * @return
     */
    Long appCountByNewUser(AldReportVo aldReportVo);


    /**
     * 新用户创建小程序数
     * @param aldReportVo
     * @return
     */
    Long appCountByOldUser(AldReportVo aldReportVo);


    /**
     * 集成SDK总数
     * @param aldReportVo
     * @return
     */
    Long reportSDKCount(AldReportVo aldReportVo);


    /**
     * 新用户集成SDK
     * @param aldReportVo
     * @return
     */
    Long reportSDKCountByNewUser(AldReportVo aldReportVo);


    /**
     * 老用户集成SDK总数
     * @param aldReportVo
     * @return
     */
    Long reportSDKCountByOldUser(AldReportVo aldReportVo);

    /**
     * 同一用户创建的多于一个的APP数据
     * @param aldReportVo
     * @return
     */
     Long reportSDKCreatedCountByVOL(AldReportVo aldReportVo) ;
    /**
     * 登录数
     * @param aldReportVo
     * @return
     */
    Long loginCount(AldReportVo aldReportVo);


    /**
     * 集成sdk流失率
     * 流失（上报数据UV>1000，七日内UV<100的小程序,记为流失）
     * @param aldReportVo
     * @return
     */
    Double reportSDKDrain(AldReportVo aldReportVo);


    /**
     * 事件SDK上报占比
     * @param aldReportVo
     * @return
     */
    Double activeEventFunSDKProp(AldReportVo aldReportVo);

    /**
     * 广告SDK上报占比
     * @param aldReportVo
     * @return
     */
    Double activeAdFunSDKProp(AldReportVo aldReportVo);

    /**
     * 用户分群SDK上报占比
     * @param aldReportVo
     * @return
     */
    Double activeUserFunSDKProp(AldReportVo aldReportVo);

    /**
     * 智能外链SDK上报占比
     * @param aldReportVo
     * @return
     */
    Double activeLinkFunSDKProp(AldReportVo aldReportVo);

    /**
     * 二维码SDK上报占比
     * @param aldReportVo
     * @return
     */
    Double activeBarCodeFunSDKProp(AldReportVo aldReportVo);


    /**
     * UV在1万以上的小程序的行业信息
     * @param aldReportVo
     * @return
     */
    List<AldReportEntity> registBusTop(AldReportVo aldReportVo);


}
