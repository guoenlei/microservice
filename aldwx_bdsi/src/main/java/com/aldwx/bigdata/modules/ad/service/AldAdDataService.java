package com.aldwx.bigdata.modules.ad.service;

import com.aldwx.bigdata.common.base.BaseService;
import com.aldwx.bigdata.modules.ad.entity.AldAdDataEntity;
import com.aldwx.bigdata.modules.ad.vo.AldAdDataVo;

import java.util.Map;

public interface AldAdDataService extends BaseService<AldAdDataEntity, AldAdDataVo> {




    Map<String, Object> openPageCount(AldAdDataVo aldAdDataVo);

    Map<String, Object> newUserCount(AldAdDataVo aldAdDataVo);

    Map<String, Object> visitUserCount(AldAdDataVo aldAdDataVo);




}

