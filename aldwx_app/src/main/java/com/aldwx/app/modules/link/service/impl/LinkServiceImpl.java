package com.aldwx.app.modules.link.service.impl;

import com.aldwx.app.common.base.BaseMethod;
import com.aldwx.app.modules.link.bean.Link;
import com.aldwx.app.modules.link.dao.game.LinkGameDao;
import com.aldwx.app.modules.link.dao.stat.LinkStatDao;
import com.aldwx.app.modules.link.entity.LinkEntity;
import com.aldwx.app.modules.link.service.LinkService;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 智能外链
 * @author lx
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class LinkServiceImpl extends BaseMethod implements LinkService {

    private static final Logger LOG = LoggerFactory.getLogger(LinkServiceImpl.class);

    @Autowired
    private LinkStatDao linkStatDao;
    @Autowired
    private LinkGameDao linkGameDao;



    /**
     * 概况 - 智能外链跟踪top5 - 列表
     * @param link
     * @return
     */
    @Override
    public List<LinkEntity> queryLinkTrendTopList(Link link) {
        List<LinkEntity> linkEntityList = null;
//        String appType = link.getAppType();
//        link.setLimitNum(SCENE_TREND_DATA_LIMIT);
//        link.setTarget("scene_visitor_count");
//        link.setTableName("aldstat_scene_statistics");
//        if(isStatApp(appType)) {
//            linkEntityList = linkStatDao.queryLinkTrendTopList(link);
//        } else {
//            linkEntityList = linkGameDao.queryLinkTrendTopList(link);
//        }

        return linkEntityList;
    }



    @Override
    public List<LinkEntity> queryLinkDataChart(Link link) {
        return null;
    }



    @Override
    public List<LinkEntity> queryLinkDataList(Link link) {
        return null;
    }



}
