package com.aldwx.bigdata.modules.trend.vo;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * {
 *     "type_name": "1",
 *     "date": "2018-03-01 ~ 2019-04-06",
 *     "date2": "2018-03-01 ~ 2019-04-06",
 *     "data_type": "3",
 *     "is_compare" : "1",
 *     "token": "LlKyXHCPC7g876uMjPIG%2Ff5chon8g5xKYTTi9bWz0zGQsfv3WOdAt0%2BdrWKLSbGKlLXj22MPv%2FIiwLHpPKaJCwDR8AelznRjV9JCNRbzuVHTiKec5U3w16Xq6H61tJ8HFrJNx5ECvypkX0qR6ZR5%2BuMCddyywmlAK7U7kJzot%2FyHKmT5BFIOazcGHldJmsmwjrPq234qND5%2Br5v3cs2Drg%3D%3D",
 *     "app_key": "1eda86f738edd4884efc3733173192db",
 *     "/trend/data": "",
 *     "transdate": [
 *         "2019-03-01 ~ 2019-03-03",
 *         "2019-03-04 ~ 2019-03-10",
 *         "2019-03-11 ~ 2019-03-17",
 *         "2019-03-18 ~ 2019-03-24",
 *         "2019-03-25 ~ 2019-03-31",
 *         "2019-04-01 ~ 2019-04-06"
 *     ],
 *     "transdate2": [
 *         "2019-03-01 ~ 2019-03-03",
 *         "2019-03-04 ~ 2019-03-10",
 *         "2019-03-11 ~ 2019-03-17",
 *         "2019-03-18 ~ 2019-03-24",
 *         "2019-03-25 ~ 2019-03-31",
 *         "2019-04-01 ~ 2019-04-06"
 *     ]
 * }
 * @param json
 * @return
 */
public class TrendQueryVo {

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
     * 是否比较，true-比较，false不比较
     */
    private boolean isCompare=false;

    private List<String> transDate;
    private List<String> transDate2;
    /**
     * 客户ak
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
