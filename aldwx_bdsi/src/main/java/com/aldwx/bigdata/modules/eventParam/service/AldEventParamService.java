package com.aldwx.bigdata.modules.eventParam.service;

import com.aldwx.bigdata.common.base.BaseService;
import com.aldwx.bigdata.modules.eventParam.entity.AldEventEntity;
import com.aldwx.bigdata.modules.eventParam.entity.AldEventParamEntity;
import com.aldwx.bigdata.modules.eventParam.vo.AldEventParamVo;

import java.util.List;


public interface AldEventParamService extends BaseService<AldEventParamEntity, AldEventParamVo> {




    /**
     * 获取事件明细 -- 列表信息
     * @return
     */
    List<AldEventEntity> queryEventListDataBy(AldEventParamVo vo);
}
