package com.aldwx.bigdata.common.base;

import com.aldwx.bigdata.common.util.DateUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseVo implements Serializable {

    //默认从第一个开始
    private String currentPage = "1";
    //每页显示20条数据
    private String total = "20";
    //是否导出数据 为1时  导出数据  不需要分页处理
    private String isDownload;
    //分页开始
    private int startRow;
    //分页结束
    private int endRow;
    //初始化list 默认添加昨天日期
    private List<String> list = new ArrayList<String>() {{add(DateUtil.getYesterday());}};

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
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

}
