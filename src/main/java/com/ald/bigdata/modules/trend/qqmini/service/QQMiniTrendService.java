package com.ald.bigdata.modules.trend.qqmini.service;

import com.ald.bigdata.common.trend.helper.TrendSQLHelper;
import com.ald.bigdata.common.trend.service.TrendService;
import com.ald.bigdata.common.trend.vo.MapResult;
import com.ald.bigdata.common.trend.vo.TrendQueryVo;
import com.ald.bigdata.common.util.StringUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * QQ小程序
 */
@Service
public class QQMiniTrendService extends TrendService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // *********以下三个接口该业务逻辑代码待优化*********

    /**
     * 获取总和
     *
     * @param trendQueryVo
     * @return
     */
    @Override
    public Map getTotalData(TrendQueryVo trendQueryVo) {
        String sql = TrendSQLHelper.totalSQL(trendQueryVo.getDateStart(), trendQueryVo.getDateEnd(), trendQueryVo.getAk());
        Map map = new HashMap();
        Map map2 = new HashMap();
        log.debug("sql:" + sql);
        Map result = null;
        try {
            result = jdbcTemplate.queryForMap(sql);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {

        }
        if (result == null) {
            map2.put("new_count", "0");
            map2.put("visit_count", "0");
            map2.put("total_page_count", "0");
            map2.put("open_count", "0");
            map2.put("secondary_avg_stay_time", "00:00:00");
            map2.put("bounce_rate", "0%");
        } else {
            map2.put("new_count", StringUtil.formatThousand((BigDecimal) result.get("new_count")));
            map2.put("visit_count", StringUtil.formatThousand((BigDecimal) result.get("visit_count")));
            map2.put("total_page_count", StringUtil.formatThousand((BigDecimal) result.get("total_page_count")));
            map2.put("open_count", StringUtil.formatThousand((BigDecimal) result.get("open_count")));
            map2.put("secondary_avg_stay_time", StringUtil.formatTime((Double.parseDouble(result.get("secondary_avg_stay_time").toString()))));
            map2.put("bounce_rate", StringUtil.formatPercent1(Float.parseFloat(result.get("bounce_rate").toString())));
        }
        map.put("data", map2);
        Map map1 = null;
        if (trendQueryVo.isCompare()) {
            String sql1 = TrendSQLHelper.totalSQL(trendQueryVo.getDate2Start(), trendQueryVo.getDate2End(), trendQueryVo.getAk());
            log.debug("sql1 compare:" + sql1);
            try {
                map1 = jdbcTemplate.queryForMap(sql1);
            } catch (org.springframework.dao.EmptyResultDataAccessException e) {

            } finally {
                // TODO closed database connection
                try {
                    Connection conn = jdbcTemplate.getDataSource().getConnection();
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (map1 != null) {
                map1.put("new_count", StringUtil.formatThousand((BigDecimal) map1.get("new_count")));
                map1.put("visit_count", StringUtil.formatThousand((BigDecimal) map1.get("visit_count")));
                map1.put("total_page_count", StringUtil.formatThousand((BigDecimal) map1.get("total_page_count")));
                map1.put("open_count", StringUtil.formatThousand((BigDecimal) map1.get("open_count")));
                map1.put("secondary_avg_stay_time", StringUtil.formatTime((Double.parseDouble(map1.get("secondary_avg_stay_time").toString()))));
                map1.put("bounce_rate", StringUtil.formatPercent1(Float.parseFloat(map1.get("bounce_rate").toString())));
            } else {
                map1 = new HashMap();
                map1.put("new_count", "0");
                map1.put("visit_count", "0");
                map1.put("total_page_count", "0");
                map1.put("open_count", "0");
                map1.put("secondary_avg_stay_time", "00:00:00");
                map1.put("bounce_rate", "0%");
            }
            map.put("data_con", map1);
        }
        return map;
    }

    /**
     * 列表对比
     *
     * @param trendQueryVo
     * @return
     */
    @Override
    public Pair<List, List> tableData(TrendQueryVo trendQueryVo) {
        //存放最终结果
        List<Map<String, String>> listRes1 = null;
        List<Map<String, String>> listRes2 = null;
        if (trendQueryVo.getDataType().equals("1")) {//hour
            Pair<List, List> pair = getHourData(trendQueryVo);
            List leftData = null;
            List rightData = null;
            if (pair.getRight() != null && pair.getRight().size() > 0) {
                rightData = pair.getRight();
            }
            if (pair.getLeft() != null && pair.getLeft().size() > 0) {
                leftData = pair.getLeft();
            }
            listRes1 = MapResult.GetTableMap(leftData, trendQueryVo, "1");
            if (trendQueryVo.isCompare()) {
                listRes2 = MapResult.GetTableMap(rightData, trendQueryVo, "2");
            }

        } else if (trendQueryVo.getDataType().equals("2")) {
            Pair<List, List> pair = getDayData(trendQueryVo);
            List leftData = null;
            List rightData = null;
            if (pair.getRight() != null && pair.getRight().size() > 0) {
                rightData = pair.getRight();
            }
            if (pair.getLeft() != null && pair.getLeft().size() > 0) {
                leftData = pair.getLeft();
            }
            listRes1 = MapResult.GetTableMap(leftData, trendQueryVo, "1");
            if (trendQueryVo.isCompare()) {
                listRes2 = MapResult.GetTableMap(rightData, trendQueryVo, "2");
            }

        } else if (trendQueryVo.getDataType().equals("3")) {
            Pair<List, List> pair = getWeekData(trendQueryVo);
            List leftData = null;
            List rightData = null;
            if (pair.getRight() != null && pair.getRight().size() > 0) {
                rightData = pair.getRight();
            }
            if (pair.getLeft() != null && pair.getLeft().size() > 0) {
                leftData = pair.getLeft();
            }
            listRes1 = MapResult.GetTableMap(leftData, trendQueryVo, "1");
            if (trendQueryVo.isCompare()) {
                listRes2 = MapResult.GetTableMap(rightData, trendQueryVo, "2");
            }
        } else if (trendQueryVo.getDataType().equals("4")) {
            Pair<List, List> pair = getMonthData(trendQueryVo);
            List leftData = null;
            List rightData = null;
            if (pair.getRight() != null && pair.getRight().size() > 0) {
                rightData = pair.getRight();
            }
            if (pair.getLeft() != null && pair.getLeft().size() > 0) {
                leftData = pair.getLeft();
            }
            listRes1 = MapResult.GetTableMap(leftData, trendQueryVo, "1");
            if (trendQueryVo.isCompare()) {
                listRes2 = MapResult.GetTableMap(rightData, trendQueryVo, "2");
            }
        }
        return Pair.of(listRes1, listRes2);
    }

    /**
     * 折线图
     *
     * @param trendQueryVo
     * @return
     */
    @Override
    public Pair<Map, Map> chartData(TrendQueryVo trendQueryVo) {
        //存放最终结果
        Map map1 = null;
        Map map2 = null;
        if (trendQueryVo.getDataType().equals("1")) {//hour
            Pair<List, List> pair = getHourData(trendQueryVo);
            List leftData = null;
            List rightData = null;
            if (pair.getRight() != null && pair.getRight().size() > 0) {
                rightData = pair.getRight();
            }
            if (pair.getLeft() != null && pair.getLeft().size() > 0) {
                leftData = pair.getLeft();
            }
            map1 = MapResult.GetChartMap(leftData, trendQueryVo, "1");
            if (trendQueryVo.isCompare()) {
                map2 = MapResult.GetChartMap(rightData, trendQueryVo, "2");
            }

        } else if (trendQueryVo.getDataType().equals("2")) {
            Pair<List, List> pair = getDayData(trendQueryVo);
            List leftData = null;
            List rightData = null;
            if (pair.getRight() != null && pair.getRight().size() > 0) {
                rightData = pair.getRight();
            }
            if (pair.getLeft() != null && pair.getLeft().size() > 0) {
                leftData = pair.getLeft();
            }
            map1 = MapResult.GetChartMap(leftData, trendQueryVo, "1");
            if (trendQueryVo.isCompare()) {
                map2 = MapResult.GetChartMap(rightData, trendQueryVo, "2");
            }
        } else if (trendQueryVo.getDataType().equals("3")) {
            Pair<List, List> pair = getWeekData(trendQueryVo);
            List leftData = null;
            List rightData = null;
            if (pair.getRight() != null && pair.getRight().size() > 0) {
                rightData = pair.getRight();
            }
            if (pair.getLeft() != null && pair.getLeft().size() > 0) {
                leftData = pair.getLeft();
            }
            map1 = MapResult.GetChartMap(leftData, trendQueryVo, "1");
            if (trendQueryVo.isCompare()) {
                map2 = MapResult.GetChartMap(rightData, trendQueryVo, "2");
            }
        } else if (trendQueryVo.getDataType().equals("4")) {
            Pair<List, List> pair = getMonthData(trendQueryVo);
            List leftData = null;
            List rightData = null;
            if (pair.getRight() != null && pair.getRight().size() > 0) {
                rightData = pair.getRight();
            }
            if (pair.getLeft() != null && pair.getLeft().size() > 0) {
                leftData = pair.getLeft();
            }
            map1 = MapResult.GetChartMap(leftData, trendQueryVo, "1");
            if (trendQueryVo.isCompare()) {
                map2 = MapResult.GetChartMap(rightData, trendQueryVo, "2");
            }
        }
        return Pair.of(map1, map2);
    }

    /**
     * 获取天的数据
     *
     * @param trendQueryVo
     * @return
     */
    @Override
    public Pair<List, List> getDayData(TrendQueryVo trendQueryVo) {
        Map map = new HashMap();
        String sql = TrendSQLHelper.daySQL(trendQueryVo.getDateStart(), trendQueryVo.getDateEnd(), trendQueryVo.getAk());
        log.debug("sql:" + sql);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map map1 = null;
        List<Map<String, Object>> result1 = null;
        if (trendQueryVo.isCompare()) {
            String sql1 = TrendSQLHelper.daySQL(trendQueryVo.getDate2Start(), trendQueryVo.getDate2End(), trendQueryVo.getAk());
            log.debug("sql1 compare:" + sql1);
            result1 = jdbcTemplate.queryForList(sql1);
            map1 = new HashMap();
        }
        return Pair.of(result, result1);
    }

    /**
     * 获取星期的数据
     *
     * @param trendQueryVo
     * @return
     */
    @Override
    public Pair<List, List> getWeekData(TrendQueryVo trendQueryVo) {
        Map map = new HashMap();
        String sql = TrendSQLHelper.weekSQL(trendQueryVo.getDateStart(), trendQueryVo.getDateEnd(), trendQueryVo.getAk());
        log.debug("sql:" + sql);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map map1 = null;
        List<Map<String, Object>> result1 = null;
        if (trendQueryVo.isCompare()) {
            String sql1 = TrendSQLHelper.weekSQL(trendQueryVo.getDate2Start(), trendQueryVo.getDate2End(), trendQueryVo.getAk());
            log.debug("sql1 compare:" + sql1);
            result1 = jdbcTemplate.queryForList(sql1);
            map1 = new HashMap();
        }
        return Pair.of(result, result1);
    }

    /**
     * 获取月的数据
     *
     * @param trendQueryVo
     * @return
     */
    @Override
    public Pair<List, List> getMonthData(TrendQueryVo trendQueryVo) {
        Map map = new HashMap();
        String sql = TrendSQLHelper.monthSQL(trendQueryVo.getDateStart(), trendQueryVo.getDateEnd(), trendQueryVo.getAk());
        log.debug("sql:" + sql);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map map1 = null;
        List<Map<String, Object>> result1 = null;
        if (trendQueryVo.isCompare()) {
            String sql1 = TrendSQLHelper.monthSQL(trendQueryVo.getDate2Start(), trendQueryVo.getDate2End(), trendQueryVo.getAk());
            log.debug("sql1 compare:" + sql1);
            result1 = jdbcTemplate.queryForList(sql1);
            map1 = new HashMap();
        }
        return Pair.of(result, result1);
    }

    /**
     * 获取小时数据
     *
     * @param trendQueryVo
     * @return
     */
    @Override
    public Pair<List, List> getHourData(TrendQueryVo trendQueryVo) {
        Map map = new HashMap();
        String sql = TrendSQLHelper.hourSQL(trendQueryVo.getDateStart(), trendQueryVo.getDateEnd(), trendQueryVo.getAk());
        log.debug("sql:" + sql);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Pair<List, List> pair = null;
        Map map1 = null;
        if (trendQueryVo.isCompare()) {
            String sql1 = TrendSQLHelper.hourSQL(trendQueryVo.getDate2Start(), trendQueryVo.getDate2End(), trendQueryVo.getAk());
            log.debug("sql1 compare:" + sql1);
            List<Map<String, Object>> result1 = jdbcTemplate.queryForList(sql1);
            pair = Pair.of(result, result1);
        } else {
            pair = Pair.of(result, null);
        }
        return pair;
    }

}
