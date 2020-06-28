package com.aldwx.app.modules.retain.vo;

import com.aldwx.app.common.base.BaseBean;

import java.util.List;

public class RetainVo extends BaseBean{

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
     * apptype 小程序 小游戏
     */
    private String appType;
    /**
     * apptype 1新增 2活跃
     */
    private String dataType;
    /**
     * 日期列表
     */
    private List listDate;
    /**
     * 日期类型 1 2 3 4
     */
    private String dateType;

    /**
     * 周列表(区间)
     */
    private List listWeek;

    private List listDay;

    public List getListDay() {
        return listDay;
    }

    public void setListDay(List listDay) {
        this.listDay = listDay;
    }

    public List getListWeek() {
        return listWeek;
    }

    public void setListWeek(List listWeek) {
        this.listWeek = listWeek;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public List getListDate() {
        return listDate;
    }

    public void setListDate(List listDate) {
        this.listDate = listDate;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String getAppType() {
        return appType;
    }

    @Override
    public void setAppType(String appType) {
        this.appType = appType;
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
