package com.ald.bigdata.common.trend.vo;

import com.ald.bigdata.common.base.BaseVo;

import java.util.List;

/**
 *
 */
public class TrendQueryVo {

//    /**
//     * 平台类型
//     * wx-微信小程序
//     * wg-微信小游戏
//     */
//    private String platform;

    /**
     * 日期类型
     * 1-小时，2-天，3-周，4-月
     */
    private String dataType;
    /**
     * 开始日期
     */
    private String dateStart;
    /**
     * 结束日期
     */
    private String dateEnd;
    /**
     * 比较的开始日期
     */
    private String date2Start;
    /**
     * 比较的结束日期
     */
    private String date2End;
    /**
     * 是否比较，
     * 1比较，0不比较
     */
    private boolean isCompare = false;

    private List<String> transDate;
    private List<String> transDate2;
    /**
     * 客户ak(app-key)
     */
    private String ak;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public String getDate2Start() {
        return date2Start;
    }

    public void setDate2Start(String date2Start) {
        this.date2Start = date2Start;
    }

    public String getDate2End() {
        return date2End;
    }

    public void setDate2End(String date2End) {
        this.date2End = date2End;
    }

    public boolean isCompare() {
        return isCompare;
    }

    public void setCompare(boolean compare) {
        isCompare = compare;
    }


    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public List<String> getTransDate() {
        return transDate;
    }

    public void setTransDate(List<String> transDate) {
        this.transDate = transDate;
    }

    public List<String> getTransDate2() {
        return transDate2;
    }

    public void setTransDate2(List<String> transDate2) {
        this.transDate2 = transDate2;
    }
}
