package com.aldwx.app.modules.portrait.vo;

import com.aldwx.app.common.base.BaseBean;

public class PortraitVo extends BaseBean {

    /**
     * 开始日期
     */
    private String dateStart;
    /**
     * 结束日期
     */
    private String dateEnd;
    /**
     * 客户ak
     */
    private String appKey;
    /**
     * topn yes or no
     */
    private String isTop;
    /**
     * type 小程序 小游戏
     */
    private String appType;
    /**
     * datatype 1新增 2活跃
     */
    private String dataType;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
