package com.aldwx.app.modules.dimension.service.impl;

import com.aldwx.app.common.base.BaseMethod;
import com.aldwx.app.common.page.PageHandle;
import com.aldwx.app.modules.dimension.bean.Qr;
import com.aldwx.app.modules.dimension.dao.game.QrGameDao;
import com.aldwx.app.modules.dimension.dao.stat.QrStatDao;
import com.aldwx.app.modules.dimension.entity.QrEntity;
import com.aldwx.app.modules.dimension.service.QrService;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二维码
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class QrServiceImpl extends BaseMethod implements QrService {


    private static final Logger LOG = LoggerFactory.getLogger(QrServiceImpl.class);


    @Autowired
    private QrStatDao qrStatDao;
    @Autowired
    private QrGameDao qrGameDao;


    /**
     * 来源 - 二维码数据 - 概览
     * @param qr
     * @return
     */
    @Override
    public List<QrEntity> queryQrView(Qr qr) {
        String appType = qr.getAppType();
        List<QrEntity> qrEntityList = null;
        if(isStatApp(appType)) {
            if(qr.getDate().equals("1") || qr.getDate().equals("2")) {
                qrEntityList = this.qrStatDao.queryQrView1(qr);
            } else {
                if(qr.getDate().equals("3")) {
                    qr.setTableName("aldstat_7days_all_qr");
                } else if(qr.getDate().equals("4")) {
                    qr.setTableName("aldstat_30days_all_qr");
                }
                qrEntityList = this.qrStatDao.queryQrView2(qr);
            }
        } else {
            if(qr.getDate().equals("1") || qr.getDate().equals("2") ) {
                qrEntityList = this.qrGameDao.queryQrView1(qr);
            } else {
                if(qr.getDate().equals("3")) {
                    qr.setTableName("aldstat_7days_all_qr");
                } else if(qr.getDate().equals("4")) {
                    qr.setTableName("aldstat_30days_all_qr");
                }
                qrEntityList = this.qrGameDao.queryQrView2(qr);
            }
        }
        if(null == qrEntityList || qrEntityList.size() == 0 || null == qrEntityList.get(0)) {
            qrEntityList = Lists.newArrayList();
        }
        return qrEntityList;
    }


    /**
     * 来源 - 二维码数据 - 折线图
     * @param qr
     * @return
     */
    @Override
    public List<List<QrEntity>> queryQrChart(Qr qr) {
        String appType = qr.getAppType();
        List<List<QrEntity>> qrEntityList = Lists.newArrayList();
        qr.setLimitNum(3);
        if(qr.getType().equals("1")) {
            if(qr.getDate().equals("1") || qr.getDate().equals("2")) {
                qr.setTableName("aldstat_hourly_qr");
                qr.setType("qr_newer_count");
            } else {
                qr.setTableName("aldstat_qr_code_statistics");
                qr.setType("new_scan_user_count");
            }
        } else {
            if(qr.getDate().equals("1") || qr.getDate().equals("2")) {
                qr.setTableName("aldstat_hourly_qr");
                qr.setType("qr_visitor_count");
            } else {
                qr.setTableName("aldstat_qr_code_statistics");
                qr.setType("total_scan_user_count");
            }
        }
        if(isStatApp(appType)) {
            List<QrEntity> entities = qrStatDao.queryTopQrKeyList(qr);
            List<QrEntity> entityList = null;
            for(QrEntity q : entities) {

                if(null != q) {
                    String qrKey = q.getQrKey();
                    qr.setQrKey(qrKey);
                    if(qr.getDate().equals("1") || qr.getDate().equals("2")) {
                        entityList = this.qrStatDao.queryQrHourChart(qr);
                    } else {
                        entityList = this.qrStatDao.queryQrDayChart(qr);
                    }
                }
            }
            qrEntityList.add(entityList);
        } else {
            List<QrEntity> entityList = null;
            List<QrEntity> entities = qrGameDao.queryTopQrKeyList(qr);
            for(QrEntity q : entities) {
                if(null != q) {
                    String qrKey = q.getQrKey();
                    qr.setQrKey(qrKey);
                    if (qr.getDate().equals("1") || qr.getDate().equals("2")) {
                        entityList = this.qrGameDao.queryQrHourChart(qr);
                    } else {
                        entityList = this.qrGameDao.queryQrDayChart(qr);
                    }
                }
            }
            qrEntityList.add(entityList);
        }
        return qrEntityList;
    }


    /**
     * 来源 - 二维码数据 - 列表
     * @param qr
     * @return
     */
    @Override
    public Map<String,Object>  queryQrList(Qr qr) {
        String appType = qr.getAppType();
        Map<String,Object> map=new HashMap<>();
        List<QrEntity> entityList = null;
        PageHandle.startPage(qr.getCurrentPage(), qr.getPageSize());
        if(isStatApp(appType)) {
            if(qr.getDate().equals("1") || qr.getDate().equals("2")) {
                entityList = this.qrStatDao.queryQrList1(qr);
            } else {
                if(qr.getDate().equals("3")) {
                    qr.setTableName("aldstat_7days_single_qr");
                } else {
                    qr.setTableName("aldstat_30days_single_qr");
                }
                entityList = this.qrStatDao.queryQrList2(qr);
            }
        } else {
            if(qr.getDate().equals("1") || qr.getDate().equals("2")) {
                entityList = this.qrGameDao.queryQrList1(qr);
            } else {
                if(qr.getDate().equals("3")) {
                    qr.setTableName("aldstat_7days_single_qr");
                } else {
                    qr.setTableName("aldstat_30days_single_qr");
                }
                entityList = this.qrGameDao.queryQrList2(qr);
            }
        }
        PageInfo<QrEntity> pageInfo = new PageInfo<>(entityList);
        map.put("data",entityList);
        map.put("num", pageInfo.getTotal());
        return map;
    }
}
