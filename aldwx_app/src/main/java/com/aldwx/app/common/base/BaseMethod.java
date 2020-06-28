package com.aldwx.app.common.base;

import com.aldwx.app.common.config.ConfigurationManager;
import com.aldwx.app.common.constants.Constants;
import com.aldwx.app.modules.scene.bean.Scene;
import com.github.pagehelper.util.StringUtil;

/**
 * 基本方法
 * @author
 * @description
 * @createTime
 **/
public abstract class BaseMethod /*implements BaseService*/ {

    //默认排序字段
    public String DEFAULT_ORDER_BY_FIELD = ConfigurationManager.getProperty(Constants.DEFAULT_ORDER_BY_FIELD);
    //TopN limit 5
    public int SCENE_TREND_DATA_LIMIT = ConfigurationManager.getInteger(Constants.SCENE_TREND_DATA_LIMIT);

    /**
     * 判断是否为统计小程序
     * 是小程序，返回true；否则如果是小游戏，返回false
     * @param appType
     * @return
     */
    public boolean isStatApp(String appType) {
        boolean isStatApp = false;
        String aldStatFlag = ConfigurationManager.getProperty(Constants.ALD_STAT_FLAG);
        String aldGameFlag = ConfigurationManager.getProperty(Constants.ALD_GAME_FLAG);
        if(appType.equals(aldStatFlag)) {
            isStatApp = true;
        } else if(appType.equals(aldGameFlag)) {
            isStatApp = false;
        }

        return isStatApp;
    }


    /**
     * 获取目标排序字段
     * @param scene
     * @return
     */
    public Scene setTargetField(Scene scene) {
        String target = scene.getTarget();
        if(StringUtil.isEmpty(target)) {
            scene.setTarget(DEFAULT_ORDER_BY_FIELD);
        }
        return scene;
    }


}
