package com.ald.bigdata.modules.ad.service.impl;

import com.ald.bigdata.common.page.PageUtil;
import com.ald.bigdata.modules.ad.dao.master.AldAdTargetApiMasterDao;
import com.ald.bigdata.modules.ad.entity.AldAdTargetApiEntity;
import com.ald.bigdata.modules.ad.service.AldAdTargetApiService;
import com.ald.bigdata.modules.ad.vo.AldAdTargetApiVo;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional(readOnly = true)
public class AldAdTargetApiServiceImpl implements AldAdTargetApiService {


    @Autowired
    private AldAdTargetApiMasterDao aldAdTargetApiMasterDao;


    @Override
    public Map<String, Object> queryPageDataList(AldAdTargetApiVo aldAdTargetApiVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdTargetApiEntity> aldAdTargetApiEntity = aldAdTargetApiMasterDao.getAppKey(aldAdTargetApiVo);
        if(null != aldAdTargetApiEntity && aldAdTargetApiEntity.size() > 0) {
            String appKey = aldAdTargetApiEntity.get(0).getAppKey();
            aldAdTargetApiVo.setAppKey(appKey);
            List<AldAdTargetApiEntity> aldAdTargetApiEntityList = aldAdTargetApiMasterDao.byAppKey(aldAdTargetApiVo);
            resultMap.put("data", aldAdTargetApiEntityList);
        }

        return resultMap;
    }

    @Override
    public Map<String, Object> queryTodayDataBy(AldAdTargetApiVo aldAdTargetApiVo) {
        return null;
    }

    @Override
    public Map<String, Object> queryYesterdayDataBy(AldAdTargetApiVo aldAdTargetApiVo) {
        return null;
    }

    @Override
    public Map<String, Object> query7DayDataBy(AldAdTargetApiVo aldAdTargetApiVo) {
        return null;
    }

    @Override
    public Map<String, Object> query30DayDataBy(AldAdTargetApiVo aldAdTargetApiVo) {
        return null;
    }

    @Override
    public Map<String, Object> queryDaysDataBy(AldAdTargetApiVo aldAdTargetApiVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        Page<?> page = null;
        page = PageUtil.startPage(NumberUtils.toInt(aldAdTargetApiVo.getCurrentPage()), NumberUtils.toInt(aldAdTargetApiVo.getTotal()));
        List<AldAdTargetApiEntity> aldAdTargetApiEntityList = this.aldAdTargetApiMasterDao.queryDaysDataBy(aldAdTargetApiVo);
        resultMap.put("data", aldAdTargetApiEntityList);
        resultMap.put("count", page.getTotal());

        return resultMap;
    }

    @Override
    public Long query30DayCountDataBy(AldAdTargetApiVo aldAdTargetApiVo) {
        return null;
    }

    @Override
    public Long query7DayCountDataBy(AldAdTargetApiVo aldAdTargetApiVo) {
        return null;
    }

    @Override
    public Long queryDaysCountDataBy(AldAdTargetApiVo aldAdTargetApiVo) {
        return null;
    }

    @Override
    public boolean isCluster(AldAdTargetApiVo aldAdTargetApiVo) {
        return false;
    }
}
