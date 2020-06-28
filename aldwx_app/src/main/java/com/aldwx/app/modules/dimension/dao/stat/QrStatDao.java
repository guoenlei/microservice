package com.aldwx.app.modules.dimension.dao.stat;

import com.aldwx.app.common.base.BaseDao;
import com.aldwx.app.modules.dimension.bean.Qr;
import com.aldwx.app.modules.dimension.entity.QrEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 二维码
 */
@Mapper
public interface QrStatDao extends BaseDao {





    /**
     * 来源 - 二维码数据 - 概览1
     * 今天  昨日
     * aldstat_qr_code_statistics
     * @param qr
     * @return
     */
    @Results(
            value = {
                    @Result(property = "totalScanUserCount", column = "total_scan_user_count"),
                    @Result(property = "newScanUserCount", column = "new_scan_user_count"),
                    @Result(property = "totalScanCount", column = "total_scan_count"),
                    @Result(property = "newScanCount", column = "new_scan_count"),
                    @Result(property = "qrNewComerForApp", column = "qr_new_comer_for_app"),
            })
    @Select("SELECT \n" +
            "SUM(total_scan_user_count) as total_scan_user_count, \n" +
            "SUM(new_scan_user_count) as new_scan_user_count, \n" +
            "SUM(total_scan_count) as total_scan_count, \n" +
            "SUM(new_scan_count) as new_scan_count, \n" +
            "SUM(qr_new_comer_for_app) as qr_new_comer_for_app \n" +
            "FROM aldstat_qr_code_statistics \n" +
            "WHERE 1=1 \n" +
            "AND day=#{dateTime}\n" +
            "AND app_key=#{appKey}")
    List<QrEntity> queryQrView1(Qr qr);




    /**
     * 来源 - 二维码数据 - 概览2
     * 7日 30日
     * aldstat_qr_code_statistics
     * @param qr
     * @return
     */
    @Results(
            value = {
                    @Result(property = "qrCount", column = "qr_count"),
                    @Result(property = "totalScanUserCount", column = "qr_visitor_count"),
                    @Result(property = "totalScanCount", column = "qr_scan_count"),
                    @Result(property = "qrNewComerForApp", column = "qr_newer_count"),
            })
    @Select("SELECT \n" +
            "SUM(qr_count) as qr_count, \n" +
            "SUM(qr_visitor_count) as qr_visitor_count, \n" +
            "SUM(qr_scan_count) as qr_scan_count, \n" +
            "SUM(qr_newer_count) as qr_newer_count \n" +
            "FROM ${tableName} \n" +
            "WHERE 1=1 \n" +
            "AND day=#{dateTime}\n" +
            "AND app_key=#{appKey}")
    List<QrEntity> queryQrView2(Qr qr);


    /**
     * 获取top3的二维码key
     * @param qr
     * @return
     */
    @Results(
             {
                    @Result(property = "qrKey", column = "qr_key")
            })
    @Select("SELECT \n" +
            "qr_key \n" +
            "FROM ${tableName} \n" +
            "WHERE 1=1\n" +
            "AND day IN ${dateSql}\n" +
            "AND app_key=#{appKey}\n" +
            "ORDER BY AVG(${type}) DESC\n" +
            "LIMIT ${limitNum}")
    List<QrEntity> queryTopQrKeyList(Qr qr);





    /**
     * 来源 - 二维码数据 小时- 折线图
     * aldstat_hourly_qr
     * @param qr
     * @return
     */
    @Results(
            value = {
                    @Result(property = "hour", column = "hour"),
                    @Result(property = "qrName", column = "sourcename"),
                    @Result(property = "totalScanUserCount", column = "qr_visitor_count"),
                    @Result(property = "newScanUserCount", column = "qr_newer_count"),
                    @Result(property = "totalScanCount", column = "qr_scan_count"),
            })
    @Select("SELECT b.sourcename, a.hour, a.qr_visitor_count, a.qr_scan_count, a.qr_newer_count  \n" +
            "FROM aldstat_hourly_qr a, ald_code b \n" +
            "WHERE 1=1 \n" +
            "AND day=#{dateTime} \n" +
            "AND a.app_key=#{appKey} \n" +
            "AND a.qr_key=b.qr_key \n" +
            "AND a.qr_key=#{qrKey}")
    List<QrEntity> queryQrHourChart(Qr qr);





    /**
     * 来源 - 二维码数据 天- 折线图
     * @param qr
     * @return
     */
    @Results(
            value = {
                    @Result(property = "day", column = "day"),
                    @Result(property = "qrName", column = "sourcename"),
                    @Result(property = "totalScanUserCount", column = "total_scan_user_count"),
                    @Result(property = "newScanUserCount", column = "new_scan_user_count"),
                    @Result(property = "totalScanCount", column = "total_scan_count"),
                    @Result(property = "newScanCount", column = "new_scan_count"),
                    @Result(property = "qrNewComerForApp", column = "qr_new_comer_for_app"),
                    @Result(property = "avgScanCountTotal", column = "avg_scan_count_total"),
                    @Result(property = "avgScanCountNew", column = "avg_scan_count_new"),
            })
    @Select("SELECT \n" +
            "a.day, " +
            "b.sourcename,\n" +
            "a.total_scan_user_count, \n" +
            "a.new_scan_user_count, \n" +
            "a.total_scan_count, \n" +
            "a.new_scan_count, \n" +
            "a.qr_new_comer_for_app,\n" +
            "a.avg_scan_count_total, \n" +
            "a.avg_scan_count_new \n" +
            "FROM aldstat_qr_code_statistics a, ald_code b\n" +
            "WHERE 1=1 \n" +
            "AND a.day IN ${dateSql}\n" +
            "AND a.qr_key=b.qr_key \n" +
            "AND a.qr_key=#{qrKey}")
    List<QrEntity> queryQrDayChart(Qr qr);




    /**
     * 来源 - 二维码数据 天 - 列表
     * 今日 昨日
     * @param qr
     * @return
     */
    @Results(
            value = {
                    @Result(property = "qrName", column = "sourcename"),
                    @Result(property = "totalScanUserCount", column = "total_scan_user_count"),
                    @Result(property = "newScanUserCount", column = "new_scan_user_count"),
                    @Result(property = "totalScanCount", column = "total_scan_count"),
                    @Result(property = "newScanCount", column = "new_scan_count"),
                    @Result(property = "qrNewComerForApp", column = "qr_new_comer_for_app"),
                    @Result(property = "avgScanCountTotal", column = "avg_scan_count_total"),
                    @Result(property = "avgScanCountNew", column = "avg_scan_count_new"),
            })
    @Select("SELECT \n" +
            "b.sourcename,\n" +
            "a.total_scan_user_count, \n" +
            "a.new_scan_user_count, \n" +
            "a.total_scan_count, \n" +
            "a.new_scan_count, \n" +
            "a.qr_new_comer_for_app,\n" +
            "a.avg_scan_count_total, \n" +
            "a.avg_scan_count_new \n" +
            "FROM aldstat_qr_code_statistics a, ald_code b\n" +
            "WHERE 1=1 \n" +
            "AND a.day=#{dateTime}\n" +
            "AND a.app_key=#{appKey}\n" +
            "AND b.status=0\n" +
            "AND a.qr_key=b.qr_key\n" )
    List<QrEntity> queryQrList1(Qr qr);




    /**
     * 来源 - 二维码数据 7.30天 - 列表
     * @param qr
     * @return
     */
    @Results(
            value = {
                    @Result(property = "qrName", column = "sourcename"),
                    @Result(property = "totalScanUserCount", column = "qr_visitor_count"),
                    @Result(property = "totalScanCount", column = "qr_scan_count"),
                    @Result(property = "qrNewComerForApp", column = "qr_newer_count"),

            })
    @Select("SELECT \n" +
            "b.sourcename,\n" +
            "a.qr_visitor_count,\n" +
            "a.qr_scan_count,\n" +
            "a.qr_newer_count \n" +
            "FROM ${tableName} a, ald_code b\n" +
            "WHERE 1=1 \n" +
            "AND a.day=#{dateTime}\n" +
            "AND a.app_key=#{appKey}\n" +
            "AND b.status=0\n" +
            "AND a.qr_key=b.qr_key\n" )
    List<QrEntity> queryQrList2(Qr qr);




}
