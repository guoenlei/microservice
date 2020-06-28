package com.aldwx.app.modules.dimension.service;


import com.aldwx.app.modules.dimension.bean.Qr;
import com.aldwx.app.modules.dimension.entity.QrEntity;

import java.util.List;
import java.util.Map;

/**
 * 二维码
 */
public interface QrService {


    /**
     * 来源 - 二维码数据 - 概览
     * @param qr
     * @return
     */
    List<QrEntity> queryQrView(Qr qr);


    /**
     * 来源 - 二维码数据 - 折线图
     * @param qr
     * @return
     */
    List<List<QrEntity>> queryQrChart(Qr qr);


    /**
     * 来源 - 二维码数据 - 列表
     * @param qr
     * @return
     */
    Map<String,Object> queryQrList(Qr qr);


}
