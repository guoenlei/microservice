package com.aldwx.app.modules.scene.service;


import com.aldwx.app.common.util.CurrencyVo;
import com.aldwx.app.modules.scene.bean.Scene;
import com.aldwx.app.modules.scene.entity.SceneEntity;

import java.util.List;
import java.util.Map;

/**
 * 场景值service
 */
public interface SceneService /*extends BaseService */{


    /**
     * 概览 - 获取场景值topN - 列表
     * @param scene
     * @return
     */
    List<SceneEntity> querySceneTrendTopList(Scene scene);


    Map<String,Object> querySceneTrendTopListNew(Scene scene);

    /**
     * 来源 - 场景值 - 概览
     * @param scene
     * @return
     */
    List<SceneEntity> querySceneView(Scene scene);


    /**
     * 来源 - 场景值TopN - 折线图
     * @param scene
     * @return
     */
    List<List<SceneEntity>> querySceneDataTopChart(Scene scene);


    /**
     * 来源 - 场景值明细 - 列表
     * @param scene
     * @return
     */
    Map<String,Object> querySceneDataList(Scene scene);

}
