package com.aldwx.app.common.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 分页处理
 * @author
 * @description
 * @createTime
 **/
public class PageHandle  extends PageInfo {


    private static final Integer CURRENT_PAGE = 1;
    private static final Integer TOTAL_DATA = 1000;

    //默认下载总条数
    private static final Integer DOWNLOAD_DATA = 1000*1000*10;



    /**
     * 默认分页
     * @return
     */
    public static Page<?> defaultPage() {
        return PageHelper.startPage(CURRENT_PAGE, TOTAL_DATA);
    }



    public static Page<?> startPage(int currentPage, int total) {
        return PageHelper.startPage(currentPage, total);
    }


    /**
     * 下载时分页
     * @param currentPage
     * @param total
     * @param isDownload
     * @return
     */
    public static Page<?> startPage(int currentPage, int total, String isDownload) {
        if(StringUtils.isNotBlank(isDownload) && isDownload.equals("1")) {
            return startPage(CURRENT_PAGE, DOWNLOAD_DATA);
        }
        return startPage(currentPage, total);
    }



    public static Page<?> startPage(String currentPage, String total, String isDownload) {
        return startPage(NumberUtils.toInt(currentPage), NumberUtils.toInt(total), isDownload);
    }


    /**
     * presto开始分页行
     * @return
     */
    public static int startRow(int currentPage, int total) {
        int startRow = 1; //默认从1
        if(StringUtils.isNumeric(currentPage +"") && StringUtils.isNumeric(total +"")) {
            startRow = (currentPage - 1) * total + 1;
        }
        return startRow;
    }


    /**
     * presto 分页结束
     * @return
     */
    public static int endRow(int currentPage, int total) {
        int endRow = 20;  //默认结束为20
        if(StringUtils.isNumeric(currentPage +"") && StringUtils.isNumeric(total +"")) {
            endRow = currentPage * total;
        }
        return endRow;
    }


    /**
     * 开始数缩小10倍
     * @param currentPage
     * @param total
     * @return
     */
    public static int reStartRow(int currentPage, int total) {
        int startRow = startRow(currentPage, total)/10;
        return  startRow <= 1 ? 1 : startRow;
    }


    /**
     * 结束条数扩大10倍
     * @param currentPage
     * @param total
     * @return
     */
    public static int reEndRow(int currentPage, int total) {
        return endRow(currentPage, total)*10;
    }

//    @Bean
//    public PageHelper pageHelper() {
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        //数据库
//        properties.setProperty("helperDialect", "mysql");
//        //是否将参数offset作为PageNum使用
//        properties.setProperty("offsetAsPageNum", "true");
//        //是否进行count查询
//        properties.setProperty("rowBoundsWithCount", "true");
//        //是否分页合理化
//        properties.setProperty("reasonable", "false");
//        pageHelper.setProperties(properties);
//
//        return pageHelper;
//    }
}
