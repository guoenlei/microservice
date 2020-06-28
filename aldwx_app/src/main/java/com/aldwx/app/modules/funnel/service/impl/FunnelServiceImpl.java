package com.aldwx.app.modules.funnel.service.impl;

import com.aldwx.app.common.base.BaseMethod;
import com.aldwx.app.modules.funnel.bean.Funnel;
import com.aldwx.app.modules.funnel.dao.game.FunnelGameDao;
import com.aldwx.app.modules.funnel.dao.stat.FunnelStatDao;
import com.aldwx.app.modules.funnel.entity.FunnelEntity;
import com.aldwx.app.modules.funnel.service.FunnelService;
import com.aldwx.app.modules.trend.service.impl.TrendServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class FunnelServiceImpl extends BaseMethod implements FunnelService {

    private static final Logger LOG = LoggerFactory.getLogger(FunnelServiceImpl.class);

    @Autowired
    private FunnelStatDao funnelStatDao;
    @Autowired
    private FunnelGameDao funnelGameDao;


    @Override
    public List<FunnelEntity> querySourceView(Funnel funnel) {
        return null;
    }

    @Override
    public List<FunnelEntity> queryFunnelDataList(Funnel funnel) {
        String appType = funnel.getAppType();
//        scene = this.setTargetField(scene);
//        scene.setLimitNum(SCENE_TREND_DATA_LIMIT);
//        scene.setTarget("scene_visitor_count");
        List<FunnelEntity> funnelEntities = null;
        if(isStatApp(appType)) {
            funnelEntities = funnelStatDao.queryFunnelDataList(funnel);
        } else {
            funnelEntities = funnelGameDao.queryFunnelDataList(funnel);
        }
        return funnelEntities;
    }

    @Override
    public List<FunnelEntity> queryFunnelDetail(Funnel funnel) {
        return null;
    }
}
