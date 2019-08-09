package com.ald.bigdata.modules.gameEvent.service;


import com.ald.bigdata.common.base.BaseService;
import com.ald.bigdata.modules.gameEvent.entity.AldGameEventParamEntity;
import com.ald.bigdata.modules.gameEvent.vo.AldGameEventParamVo;

import java.util.List;


public interface AldGameEventParamService extends BaseService<AldGameEventParamEntity, AldGameEventParamVo> {




    /**
     * 获取事件列表数据
     * @param vo
     * @return
     */
    List<AldGameEventParamEntity> queryEventParamListDataBy(AldGameEventParamVo vo);





}
