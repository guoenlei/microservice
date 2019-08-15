package com.ald.bigdata.modules.trend.wxgame.controller;

import com.ald.bigdata.common.trend.vo.JsonResult;
import com.ald.bigdata.common.trend.vo.TrendQueryVo;
import com.ald.bigdata.common.util.DateUtil;
import com.ald.bigdata.modules.trend.wxgame.service.WXGameTrendService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import static com.ald.bigdata.common.util.ChooseUDataSource.chooseYourDataSource;

/**
 * WX小游戏趋势分析模块controller
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "wx/game/trend")
public class WXGameTrendAnalysisReportController {

    @Autowired
    WXGameTrendService trendService;

    /**
     * 趋势分析汇总
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "head", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult findAldstatTrendAnalysisTotal(@RequestBody JSONObject json) {
        // 根据传入的ak，类型等信息返回一个对应的jdbcTemplate。
        chooseDataSource(json);

        TrendQueryVo vo = constructQueryObject(json);
        Map result = trendService.getTotalData(vo);
        JsonResult jsonResult = new JsonResult(result);
        String date = vo.getDateStart() + " " + "~" + " " + vo.getDateEnd();
        String date2 = json.getString("date2");
        jsonResult.date = date;
        jsonResult.date_con = date2;
        return jsonResult;
    }

    /**
     * 趋势分析列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "table", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject findAldstatTrendAnalysisTable(@RequestBody JSONObject json) {
        chooseDataSource(json);

        TrendQueryVo vo = constructQueryObject(json);
        Pair<List, List> pair = trendService.tableData(vo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "200");
        if (pair.getLeft() == null || pair.getLeft().size() <= 0) {
            jsonObject.put("data", "null");
        } else {
            jsonObject.put("data", pair.getLeft());
        }
        if (pair.getRight() == null || pair.getRight().size() <= 0) {
            jsonObject.put("data_con", "null");
        } else {
            jsonObject.put("data_con", pair.getRight());
        }
        String date = vo.getDateStart() + " " + "~" + " " + vo.getDateEnd();
        String date2 = json.getString("date2");
        jsonObject.put("date", date);
        jsonObject.put("date_con", date2);
        return jsonObject;
    }

    /**
     * 趋势分析图表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "chart", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult findAldstatTrendAnalysisChart(@RequestBody JSONObject json) {
        chooseDataSource(json);

        TrendQueryVo vo = constructQueryObject(json);
        Pair<Map, Map> result = trendService.chartData(vo);
        JsonResult jsonResult = new JsonResult(result);
        String date = vo.getDateStart() + " " + "~" + " " + vo.getDateEnd();
        String date2 = json.getString("date2");
        jsonResult.date = date;
        jsonResult.date_con = date2;

        return jsonResult;
    }

    /**
     * 根据传入的ak，类型等信息返回一个对应的jdbcTemplate。
     * @param json
     */
    private void chooseDataSource(@RequestBody JSONObject json) {
        // 根据传入的ak，类型等信息返回一个对应的jdbcTemplate。
        String app_key = json.get("app_key").toString();
        String te = json.get("platform").toString();
        JdbcTemplate jdbcTemplate = chooseYourDataSource(app_key, te);
        trendService.setJdbcTemplate(jdbcTemplate);
    }

    /**
     *
     * @param json
     * @return
     */
    private TrendQueryVo constructQueryObject(JSONObject json) {

        TrendQueryVo vo = new TrendQueryVo();
        vo.setDataType(json.getString("data_type"));
        String date = json.getString("date");
        if (StringUtils.equals(date, "1")) { //今天
            vo.setDateStart(DateUtil.getTodayDate());
            vo.setDateEnd(vo.getDateStart());
        } else if (StringUtils.equals(date, "2")) { //昨天
            String yesterday = DateUtil.getYesterday();
            vo.setDateStart(yesterday);
            vo.setDateEnd(yesterday);
        } else if (StringUtils.equals(date, "3")) { //近7天
            String[] day = DateUtil.getNearly7Day();
            vo.setDateStart(day[1]);
            vo.setDateEnd(day[0]);
        } else if (StringUtils.equals(date, "4")) { //近30天
            String[] day = DateUtil.getNearly30Day();
            vo.setDateStart(day[1]);
            vo.setDateEnd(day[0]);
        } else {
            String[] dateRange = date.split("~");
            vo.setDateStart(dateRange[0].trim());
            vo.setDateEnd(dateRange[1].trim());
        }
        if (json.getString("is_compare") != null && json.getString("is_compare").equals("1")) {
            vo.setCompare(true);
        }
        if (vo.isCompare()) {
            String date2 = json.getString("date2");
            String dateRange2[] = date2.split("~");
            vo.setDate2Start(dateRange2[0].trim());
            vo.setDate2End(dateRange2[1].trim());
        }
        if (json.getString("date_type") != null) {
            vo.setDataType(json.getString("date_type"));
        }
        vo.setAk(json.getString("app_key"));

        if (json.getString("transdate") != null) {
            List<String> list1 = (List<String>) (json.get("transdate"));
            vo.setTransDate(list1);
        }
        if (json.getString("transdate2") != null) {
            List<String> list2 = (List<String>) (json.get("transdate2"));
            vo.setTransDate2(list2);
        }
        return vo;
    }

}