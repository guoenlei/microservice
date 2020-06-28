package com.aldwx.app.common.base;


import com.aldwx.app.common.config.ConfigurationManager;
import com.aldwx.app.common.constants.Constants;
import com.aldwx.app.common.util.DateUtil;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseBean implements Serializable {

    //默认从第一个开始
    private int currentPage = 1;
    //每页显示20条数据
    private int pageSize = 10;
    //是否导出数据 为1时  导出数据  不需要分页处理
    private String isDownload;
    //分页开始
    private int startRow;
    //分页结束
    private int endRow;
    //初始化list 默认添加昨天日期
    private List<String> dateList = new ArrayList<String>() {{add(DateUtil.getYesterday());}};
    //根据日期创建dateSql
    private String dateSql;

    //小程序唯一标识
    private String appKey;
    //日期
    private String date;
    //日期转换
    private String dateTime;
    //指标类型（新用户，访客，打开次数...）
    private String target;
    //小程序类型（小程序，小游戏）
    private String appType;

    //TOPN
    private int limitNum;
    //表名
    private String tableName;

    public BaseBean(){
    }

    public BaseBean(String date){
        this.formatDate(date);
        this.dateSql(this.getDateList());
    }

    public BaseBean(String appKey, String date, String type){
        this.setAppKey(appKey);
        this.setDate(date);
        this.setTarget(type);
    }

    public BaseBean(String appKey, String date, String target, String type){
        this.setAppKey(appKey);
        this.setDate(date);
        this.setTarget(target);
        this.setTarget(type);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public String getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(String isDownload) {
        this.isDownload = isDownload;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getDateSql() {
        return dateSql;
    }

    public void setDateSql(String dateSql) {
        this.dateSql = dateSql;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 日期转换后 放入到List中
     * @param date
     * @return
     */
    private void formatDate(String date) {
        String today = ConfigurationManager.getProperty(Constants.PARAM_TODAY_DATE_FLAG);
        String yesterday = ConfigurationManager.getProperty(Constants.PARAM_YESTERDAY_DATE_FLAG);
        String weekday = ConfigurationManager.getProperty(Constants.PARAM_WEEKDAY_DATE_FLAG);
        String month = ConfigurationManager.getProperty(Constants.PARAM_MONTH_DATE_FLAG);

        if(date.equals(today)) {
            this.setDateTime(DateUtil.getTodayDate());
            this.setDateList(new ArrayList<String>() {{add(DateUtil.getTodayDate());}});
        } else if(date.equals(yesterday)) {
            this.setDateTime(DateUtil.getYesterday());
            this.setDateList(new ArrayList<String>() {{add(DateUtil.getYesterday());}});
        } else if(date.equals(weekday)) {
            this.setDateTime(DateUtil.getYesterday());
            this.setDateList(DateUtil.getBeforeDate(7));
        } else if(date.equals(month)) {
            this.setDateTime(DateUtil.getYesterday());
            this.setDateList(DateUtil.getBeforeDate(30));
        }
    }


    /**
     * dateSql
     * @param dateList
     */
    private void dateSql(List<String> dateList) {
        StringBuffer sb = new StringBuffer();
        if(null != dateList && dateList.size() > 0) {
            sb.append("(");
            for(String date : dateList) {
                sb.append("'")
                        .append(date)
                        .append("',");
            }
            String s = sb.substring(0, sb.lastIndexOf(","));
//            System.out.println(s+")");
            if(null != sb) {
                this.setDateSql(s+")");
            }
            System.out.println(this.getDateSql());
        }
    }


    public static void main(String[] args) {
        BaseBean baseBean = new BaseBean();
        List<String> dateList = Lists.newArrayList();
        dateList.add("2019-01-01");
        dateList.add("2019-01-01");
        dateList.add("2019-01-01");
        dateList.add("2019-01-01");
        baseBean.dateSql(dateList);
    }

}
