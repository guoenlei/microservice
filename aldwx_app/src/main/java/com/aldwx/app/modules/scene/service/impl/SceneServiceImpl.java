package com.aldwx.app.modules.scene.service.impl;

import com.aldwx.app.common.base.BaseMethod;
import com.aldwx.app.common.page.PageHandle;
import com.aldwx.app.common.util.StringUtil;
import com.aldwx.app.modules.scene.bean.Scene;
import com.aldwx.app.modules.scene.dao.game.SceneGameDao;
import com.aldwx.app.modules.scene.dao.stat.SceneStatDao;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.aldwx.app.modules.scene.service.SceneService;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.*;


/**
 * 场景值
 *
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class SceneServiceImpl extends BaseMethod implements SceneService {

    private static final Logger LOG = LoggerFactory.getLogger(SceneServiceImpl.class);

    @Autowired
    private SceneStatDao sceneStatDao;

    @Autowired
    private SceneGameDao sceneGameDao;

    @Autowired
    @Qualifier("dbMaster")
    private DataSource dataSource;


    /**
     * 概览 - 获取场景值topN - 列表
     *
     * @param scene
     * @return
     */
    @Override
    public List<SceneEntity> querySceneTrendTopList(Scene scene) {
        String appType = scene.getAppType();
        scene = this.setTargetField(scene);
        scene.setTarget("scene_visitor_count");
        List<SceneEntity> sceneEntityList = null;
        if (isStatApp(appType)) {
            sceneEntityList = sceneStatDao.querySceneTrendTopList(scene);
        } else {
            sceneEntityList = sceneGameDao.querySceneTrendTopList(scene);
        }
        return sceneEntityList;
    }


    /**
     * 概览 - 获取场景值topN - 列表 - 改需求后
     *
     * @param scene
     * @return
     */
    @Override
//    @DBSource
    public Map<String, Object> querySceneTrendTopListNew(Scene scene) {
//        try {
//            Connection connection = dataSource.getConnection();
//            Properties clientInfo = connection.getClientInfo();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        String appType = scene.getAppType();
        LOG.info("Into SceneServiceImpl,the scene appType is [{}]", appType);
        scene = this.setTargetField(scene);
        // 查詢出TOP N
        scene.setLimitNum(SCENE_TREND_DATA_LIMIT);
        // 設置排序字段和設置類型    // 1新用户，2访问人数，3.打开次数，4访问次数
        if ("1".equals(scene.getType())) {
            scene.setTarget("scene_newer_for_app");
            scene.setType("scene_newer_for_app");
        } else if ("2".equals(scene.getType())) {
            scene.setTarget("scene_visitor_count");
            scene.setType("scene_visitor_count");
        } else if ("3".equals(scene.getType())) {
            scene.setTarget("scene_open_count");
            scene.setType("scene_open_count");
        } else if ("4".equals(scene.getType())) {
            scene.setTarget("scene_page_count");
            scene.setType("scene_page_count");
        }
        List<SceneEntity> sceneEntitiesTopList = null;
        if (isStatApp(appType)) {
            LOG.info("Into app TopList,The type is [{}]",appType);
            sceneEntitiesTopList = sceneStatDao.querySceneDataTopList(scene);// 1新用户，2访问人数，3.打开次数，4访问次数
            //  sceneEntityList = sceneStatDao.querySceneTrendTopList(scene);
        } else {
            LOG.info("Into game TopList,The type is [{}]",appType);
            sceneEntitiesTopList = sceneGameDao.querySceneDataTopList(scene);// 1新用户，2访问人数，3.打开次数，4访问次数

        }

        // 可能爲空

        List<SceneEntity> sceneEntityList = null;

        Map<String, Object> sceneEntityMap = new HashMap<>();

        if (sceneEntitiesTopList.size() == 0 || sceneEntitiesTopList == null) {
            LOG.error("sceneEntities is null,Please check database have date");
        } else {

            // 場景值IDTop5,遍历出来每一个，然后取出来每一个的Top1
            for (SceneEntity sceneEntity : sceneEntitiesTopList) {

                scene.setSceneId(sceneEntity.getSceneId());
                if (isStatApp(appType)) {
                    LOG.info("AppType is App :By SceneId find TopOne");
                    //  sceneEntityList = sceneStatDao.querySceneTrendTopList(scene);
                    sceneEntityList = sceneStatDao.querySceneTrendTopListNew(scene);
                } else {
                    LOG.info("AppType is Game :By SceneId find TopOne");
                    sceneEntityList = sceneGameDao.querySceneTrendTopListNew(scene);
                }

                // 設置排序字段和設置類型    // 1新用户，2访问人数，3.打开次数，4访问次数
                for (SceneEntity s : sceneEntityList) {
                    if ("scene_newer_for_app".equals(scene.getType())) {
                        sceneEntityMap.put(s.getSceneName(), s.getSceneNewerForApp());
                    } else if ("scene_visitor_count".equals(scene.getType())) {
                        sceneEntityMap.put(s.getSceneName(), s.getSceneVisitorCount());

                    } else if ("scene_open_count".equals(scene.getType())) {
                        sceneEntityMap.put(s.getSceneName(), s.getSceneOpenCount());

                    } else if ("scene_page_count".equals(scene.getType())) {
                        sceneEntityMap.put(s.getSceneName(), s.getScenePageCount());
                    }
                }
            }
        }
        return sceneEntityMap;
    }


    /**
     * 来源 - 场景值 - 概览
     *
     * @param scene
     * @return
     */
    @Override
    public List<SceneEntity> querySceneView(Scene scene) {
        String appType = scene.getAppType();
        List<SceneEntity> sceneEntityList = null;
        if (isStatApp(appType)) {
            sceneEntityList = this.sceneStatDao.querySceneView(scene);
        } else {
            sceneEntityList = this.sceneGameDao.querySceneView(scene);
        }
        if (null == sceneEntityList || sceneEntityList.size() == 0) {
            sceneEntityList = Lists.newArrayList();
            sceneEntityList.add(new SceneEntity());
        }

        return sceneEntityList;
    }


    /**
     * 来源 - 场景值趋势Top - 折线图
     *
     * @param scene
     * @return
     */
    @Override
    public List<List<SceneEntity>> querySceneDataTopChart(Scene scene) {
        String appType = scene.getAppType();
        //通过type的值判断是查询新增还是活跃用户指标
        if (scene.getType().equals("1")) {
            if (scene.getDate().equals("1") || scene.getDate().equals("2")) {
                scene.setTableName("aldstat_hourly_scene");
                scene.setType("new_comer_count");
            } else {
                scene.setTableName("aldstat_scene_statistics");
                scene.setType("scene_newer_for_app");
            }
        } else {
            if (scene.getDate().equals("1") || scene.getDate().equals("2")) {
                scene.setTableName("aldstat_hourly_scene");
                scene.setType("visitor_count");
            } else {
                scene.setTableName("aldstat_scene_statistics");
                scene.setType("scene_visitor_count");
            }
        }
        //取top3
        scene.setLimitNum(3);
        List<List<SceneEntity>> lists = Lists.newArrayList();
        List<SceneEntity> sceneEntityList = null;
        if (isStatApp(appType)) {
            List<SceneEntity> sceneEntitys = sceneStatDao.querySceneDataTopList(scene);
            for (SceneEntity entity : sceneEntitys) {
                scene.setSceneId(entity.getSceneId());
                if (scene.getDate().equals("1") || scene.getDate().equals("2")) {
                    sceneEntityList = sceneStatDao.querySceneHourDataTopChart(scene);
                } else {
                    sceneEntityList = sceneStatDao.querySceneDayDataTopChart(scene);
                }
                lists.add(sceneEntityList);
            }
        } else {
            List<SceneEntity> sceneEntitys = sceneGameDao.querySceneDataTopList(scene);
            for (SceneEntity entity : sceneEntitys) {
                scene.setSceneId(entity.getSceneId());
                if (scene.getDate().equals("1") || scene.getDate().equals("2")) {
                    sceneEntityList = sceneGameDao.querySceneHourDataTopChart(scene);
                } else {
                    sceneEntityList = sceneGameDao.querySceneDayDataTopChart(scene);
                }
                lists.add(sceneEntityList);
            }
        }
        return lists;
    }


    /**
     * 来源 - 场景值明细 - 列表
     *
     * @param scene
     * @return
     */
    @Override
    public Map<String, Object> querySceneDataList(Scene scene) {
        String appType = scene.getAppType();
        Map<String, Object> map = new HashMap<>();
        List<Map<String, String>> list = new ArrayList<>();
//        if(scene.getDate().equals("1")) {
//            scene.setTableName("aldstat_hourly_scene");
//        } else {
        scene.setTableName("aldstat_scene_statistics");
//        }
        //取top10
        //日期获取表名
        List<SceneEntity> sceneEntityList = null;
        PageHandle.startPage(scene.getCurrentPage(), scene.getPageSize());
        if (isStatApp(appType)) {
//            if(scene.getDate().equals("1")) {
//                sceneEntityList = sceneStatDao.querySceneHourDataList(scene);
//            } else {
            sceneEntityList = sceneStatDao.querySceneDayDataList(scene);
//            }
        } else {
//            if(scene.getDate().equals("1") ) {
//                sceneEntityList = sceneGameDao.querySceneHourDataList(scene);
//            } else {
            sceneEntityList = sceneGameDao.querySceneDayDataList(scene);
//            }
        }
        PageInfo<SceneEntity> pageInfo = new PageInfo<>(sceneEntityList);

        if (sceneEntityList != null && sceneEntityList.size() > 0) {
            for (SceneEntity sceneEntity : sceneEntityList) {
                Map<String, String> map1 = new HashMap<>();
                map1.put("sceneNewerForApp", String.valueOf(sceneEntity.getSceneNewerForApp()));
                map1.put("sceneVisitorCount", String.valueOf(sceneEntity.getSceneVisitorCount()));
                map1.put("scenePageCount", String.valueOf(sceneEntity.getScenePageCount()));
                map1.put("sceneOpenCount", String.valueOf(sceneEntity.getSceneOpenCount()));
                map1.put("bounceRate", StringUtil.formatPercent2(sceneEntity.getBounceRate()));
                map1.put("sceneName", sceneEntity.getSceneName());
                list.add(map1);
            }
        }
        map.put("data", list);
        map.put("num", pageInfo.getTotal());
        return map;
    }


}
