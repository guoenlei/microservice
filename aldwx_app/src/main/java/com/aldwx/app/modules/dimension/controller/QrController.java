package com.aldwx.app.modules.dimension.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.common.util.CurrencyVo;
import com.aldwx.app.common.util.VoUtil;
import com.aldwx.app.modules.dimension.assist.QrAssist;
import com.aldwx.app.modules.dimension.bean.Qr;
import com.aldwx.app.modules.dimension.entity.QrEntity;
import com.aldwx.app.modules.dimension.service.QrService;
import com.aldwx.app.modules.scene.controller.SceneController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 二维码
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldstat/app/qr")
public class QrController extends BaseController {


    private static final Logger LOG = LoggerFactory.getLogger(QrController.class);


    @Autowired
    private QrService qrService;


    /**
     * 来源 - 二维码数据 - 概览
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/view", method = RequestMethod.POST)
    public String queryQrView(String app_key, String date, String app_type) {
        List<QrEntity> qrEntityList = this.qrService.queryQrView(new Qr(app_key, date, app_type));
        if (qrEntityList==null||qrEntityList.size()<=0){
            qrEntityList=new ArrayList<>();
            QrEntity qr=new QrEntity();
            qr.setAvgScanCountNew(0);
            qr.setAvgScanCountTotal(0);
            qr.setNewScanCount(0);
            qr.setNewScanUserCount(0);
            qr.setQrCount(0);
            qr.setQrNewComerForApp(0);
            qr.setTotalScanUserCount(0);
            qr.setTotalScanCount(0);
            qrEntityList.add(qr);
        }
        return resultJosn4(qrEntityList);
    }


    /**
     * 来源 - 二维码数据 - 折线图
     * @param app_key
     * @param date
     * @param app_type
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/trend", method = RequestMethod.POST)
    public String  queryQrChart(String app_key, String date, String app_type, String type) {
        CurrencyVo vo=VoUtil.getCurrencyVo(app_key,date);
        List<List<QrEntity>> qrEntityList = this.qrService.queryQrChart(new Qr(app_key, date, app_type, type));
        return resultJosn4(QrAssist.formatChart(qrEntityList, date , type,vo));
    }


    /**
     * 来源 - 二维码数据 - 列表
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/particular", method = RequestMethod.POST)
    public String queryQrList(String app_key, String date, String app_type, String currentPage) {
        Map<String,Object>  qrEntityList = this.qrService.queryQrList(new Qr(app_key, date, app_type,Integer.parseInt(currentPage)));
        return resultJosn10(qrEntityList);
    }








}
