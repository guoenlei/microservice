package com.aldwx.app.modules.scene.bean;

import com.aldwx.app.common.base.BaseBean;
import com.aldwx.app.modules.general.bean.General;
import org.apache.commons.lang3.StringUtils;


/**
 * 场景值
 * @author
 * @description
 * @createTime
 **/
public class Scene extends BaseBean {

//    private String type;        //类型 1新增  2活跃
    private String type;        //访问人数,打开次数,页面访问次数，新用户数

    private int sceneId;

    private static final String TABLE_NAME_HOUR = "aldstat_hourly_scene";
    private static final String TABLE_NAME_DAY = "aldstat_scene_statistics";
    private static final String TABLE_NAME_7DAY = "aldstat_7days_single_scene";
    private static final String TABLE_NAME_30DAY = "aldstat_30days_single_scene";

    public Scene(){}

    public Scene(String appKey, String date, String appType) {
        super(date);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
    }
    public Scene(String appKey, String date, String appType,int currentPage) {
        super(date);
        super.setCurrentPage(currentPage);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
    }

    public Scene(String appKey, String date, String appType, String type) {
        super(date);
        this.setTName(date);
        this.setAppKey(appKey);
        this.setDate(date);
        this.setAppType(appType);
        this.setType(type);
    }

    public Scene(General general) {
        super(general.getDate());
        this.setTName(general.getDate());
        this.setAppKey(general.getAppKey());
        this.setDate(general.getDate());
        this.setTarget(general.getTarget());
        this.setAppType(general.getAppType());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    //判断表名
    public void setTName(String date) {
        String tableName = null;
        if(date.equals("1")) {
            tableName = TABLE_NAME_DAY;
        } else if(date.equals("2")) {
            tableName = TABLE_NAME_DAY;
        } else if(date.equals("3")) {
            tableName = TABLE_NAME_7DAY;
        } else if(date.equals("4")) {
            tableName = TABLE_NAME_30DAY;
        }

        if(StringUtils.isNotBlank(tableName)) {
            this.setTableName(tableName);
        }
    }


}
