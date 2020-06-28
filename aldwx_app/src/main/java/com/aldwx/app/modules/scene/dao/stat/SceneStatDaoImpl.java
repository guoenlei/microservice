package com.aldwx.app.modules.scene.dao.stat;

import com.aldwx.app.modules.scene.bean.Scene;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class SceneStatDaoImpl implements SceneStatDao {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Override
    public List<SceneEntity> querySceneTrendTopList(Scene s) {
        return null;
    }

    @Override
    public List<SceneEntity> querySceneTrendTopListNew(Scene s) {
        return null;
    }

    @Override
    public List<SceneEntity> querySceneView(Scene scene) {
        return null;
    }

    @Override
    public List<SceneEntity> querySceneDataTopList(Scene scene) {
        return null;
    }

    @Override
    public List<SceneEntity> query(Scene scene) {
        return null;
    }

    @Override
    public List<SceneEntity> querySceneHourDataTopChart(Scene s) {
        return null;
    }

    @Override
    public List<SceneEntity> querySceneDayDataTopChart(Scene s) {
        return null;
    }

    @Override
    public List<SceneEntity> querySceneHourDataList(Scene s) {
        return null;
    }

    @Override
    public List<SceneEntity> querySceneDayDataList(Scene s) {
        return null;
    }

    @Override
    public List query7DayDataBy(Object o) {
        return null;
    }

    @Override
    public List query30DayDataBy(Object o) {
        return null;
    }

    @Override
    public List queryDaysDataBy(Object o) {
        return null;
    }

    @Override
    public Long query30DayCountDataBy(Object o) {
        return null;
    }

    @Override
    public Long query7DayCountDataBy(Object o) {
        return null;
    }

    @Override
    public Long queryDaysCountDataBy(Object o) {
        return null;
    }

    @Override
    public boolean isStatApp(Object o) {
        return false;
    }
}
