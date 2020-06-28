package com.aldwx.app.modules.link.service;


import com.aldwx.app.modules.link.bean.Link;
import com.aldwx.app.modules.link.entity.LinkEntity;

import java.util.List;

/**
 * 智能外链
 */
public interface LinkService /*extends BaseService*/ {


    /**
     * 来源 - 智能外链 - 概览
     * @param link
     * @return
     */
    List<LinkEntity> queryLinkTrendTopList(Link link);


    /**
     * 来源 - 智能外链 来源渠道 - 折线图
     * @param link
     * @return
     */
    List<LinkEntity> queryLinkDataChart(Link link);



    /**
     * 来源 - 智能外链 渠道明细 - 列表
     * @param link
     * @return
     */
    List<LinkEntity> queryLinkDataList(Link link);



}
