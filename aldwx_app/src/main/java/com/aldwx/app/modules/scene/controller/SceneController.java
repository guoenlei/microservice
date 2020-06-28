package com.aldwx.app.modules.scene.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.common.util.CurrencyVo;
import com.aldwx.app.common.util.VoUtil;
import com.aldwx.app.modules.scene.assist.SceneAssist;
import com.aldwx.app.modules.scene.bean.Scene;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.aldwx.app.modules.scene.service.SceneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 场景值
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldstat/app/scene")
public class SceneController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(SceneController.class);

    @Autowired
    private SceneService sceneService;



    /**
     * 来源 - 场景值趋势 - 概览
     * @param app_key
     * @param date
     * @param app_type
     *          1新增 2活跃
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/view", method = RequestMethod.POST)
    public String querySceneView(String app_key, String date, String app_type) {
        List<SceneEntity> sceneEntityList = sceneService.querySceneView(new Scene(app_key, date, app_type));
        return resultJosn4(sceneEntityList.get(0));
    }


    /**
     * 来源 - 场景值趋势 - 折线图
     * @param app_key
     * @param date
     * @param app_type
     * @param type
     *          1新增 2活跃
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/trend", method = RequestMethod.POST)
    public String querySceneDataTopChart(String app_key, String date, String app_type, String type) {
        CurrencyVo vo=VoUtil.getCurrencyVo(app_key,date);
        List<List<SceneEntity>> sceneEntityList = sceneService.querySceneDataTopChart(new Scene(app_key, date, app_type, type));
        return resultJosn4(SceneAssist.formatChart(sceneEntityList, date, type,vo));
    }


    /**
     * 来源 - 场景值明细 - 列表
     * @param app_key
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/particular", method = RequestMethod.POST)
    public String querySceneDataList(String app_key, String date, String app_type,String currentPage) {
        Map<String,Object> sceneEntityMap = sceneService.querySceneDataList(new Scene(app_key, date, app_type,Integer.parseInt(currentPage)));
        return resultJosn10(sceneEntityMap);
    }






}
