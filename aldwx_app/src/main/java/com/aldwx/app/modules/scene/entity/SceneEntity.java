package com.aldwx.app.modules.scene.entity;

import com.aldwx.app.common.base.BaseEntity;

/**
 * 场景值
 * @author
 * @description
 * @createTime
 **/
public class SceneEntity extends BaseEntity {

    private String appKey;                          //小程序标识
    private String hour;                               //小时
    private String day;                               //日期
    private int sceneId;                            //场景值id
    private long sceneVisitorCount;                 //访问用户数
    private long sceneOpenCount;                    //打开次数
    private long scenePageCount;                    //访问次数
    private long sceneNewerForApp;                  //新用户数
    private long totalStayTime;                     //停留时长
    private float secondaryStayTime;                //次挺留时长
    private long onePageCount;                      //跳失率
    private float bounceRate;                       //
    private String sceneName;                       //场景值名称
    private int sceneGroupId;                       //分组id

    public SceneEntity() {
    }

    public SceneEntity(String appKey) {
        this.appKey = appKey;
    }

    public SceneEntity(String appKey, String day, int sceneId, long sceneVisitorCount, long sceneOpenCount, long scenePageCount, long sceneNewerForApp, long totalStayTime, float secondaryStayTime, long onePageCount, float bounceRate, String sceneName, int sceneGroupId) {
        this.appKey = appKey;
        this.day = day;
        this.sceneId = sceneId;
        this.sceneVisitorCount = sceneVisitorCount;
        this.sceneOpenCount = sceneOpenCount;
        this.scenePageCount = scenePageCount;
        this.sceneNewerForApp = sceneNewerForApp;
        this.totalStayTime = totalStayTime;
        this.secondaryStayTime = secondaryStayTime;
        this.onePageCount = onePageCount;
        this.bounceRate = bounceRate;
        this.sceneName = sceneName;
        this.sceneGroupId = sceneGroupId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public long getSceneVisitorCount() {
        return sceneVisitorCount;
    }

    public void setSceneVisitorCount(long sceneVisitorCount) {
        this.sceneVisitorCount = sceneVisitorCount;
    }

    public long getSceneOpenCount() {
        return sceneOpenCount;
    }

    public void setSceneOpenCount(long sceneOpenCount) {
        this.sceneOpenCount = sceneOpenCount;
    }

    public long getScenePageCount() {
        return scenePageCount;
    }

    public void setScenePageCount(long scenePageCount) {
        this.scenePageCount = scenePageCount;
    }

    public long getSceneNewerForApp() {
        return sceneNewerForApp;
    }

    public void setSceneNewerForApp(long sceneNewerForApp) {
        this.sceneNewerForApp = sceneNewerForApp;
    }

    public long getTotalStayTime() {
        return totalStayTime;
    }

    public void setTotalStayTime(long totalStayTime) {
        this.totalStayTime = totalStayTime;
    }

    public float getSecondaryStayTime() {
        return secondaryStayTime;
    }

    public void setSecondaryStayTime(float secondaryStayTime) {
        this.secondaryStayTime = secondaryStayTime;
    }

    public long getOnePageCount() {
        return onePageCount;
    }

    public void setOnePageCount(long onePageCount) {
        this.onePageCount = onePageCount;
    }

    public float getBounceRate() {
        return bounceRate;
    }

    public void setBounceRate(float bounceRate) {
        this.bounceRate = bounceRate;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public int getSceneGroupId() {
        return sceneGroupId;
    }

    public void setSceneGroupId(int sceneGroupId) {
        this.sceneGroupId = sceneGroupId;
    }
}
