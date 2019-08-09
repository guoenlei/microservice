package com.ald.bigdata.modules.eventParam.service;


import com.ald.bigdata.common.base.BaseService;
import com.ald.bigdata.modules.eventParam.entity.AldEventEntity;
import com.ald.bigdata.modules.eventParam.entity.AldEventParamEntity;
import com.ald.bigdata.modules.eventParam.vo.AldEventParamVo;

import java.util.List;


public interface AldEventParamService extends BaseService<AldEventParamEntity, AldEventParamVo> {




    /**
     * 获取事件明细 -- 列表信息
     * @return
     */
    List<AldEventEntity> queryEventListDataBy(AldEventParamVo vo);
}
