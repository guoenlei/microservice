package com.aldwx.app.modules.funnel.service;

import com.aldwx.app.modules.funnel.bean.Funnel;
import com.aldwx.app.modules.funnel.entity.FunnelEntity;

import java.util.List;

/**
 * 漏斗
 */
public interface FunnelService {


    /**
     * 行为 - 转换漏斗 - 来源
     * @param funnel
     * @return
     */
    List<FunnelEntity> querySourceView(Funnel funnel);

    /**
     * 行为 - 转换漏斗 - 列表
     * @param funnel
     * @return
     */
    List<FunnelEntity> queryFunnelDataList(Funnel funnel);


    /**
     * 行为 - 转换漏斗 - 步骤明细
     * @param funnel
     * @return
     */
    List<FunnelEntity> queryFunnelDetail(Funnel funnel);


}
