package com.ald.bigdata.modules.QQMini.controller;

import com.ald.bigdata.modules.QQGame.controller.QQGameTrendAnalysisReportController;
import com.ald.bigdata.common.util.DateUtil;
import com.ald.bigdata.common.trend.vo.JsonResult;
import com.ald.bigdata.common.trend.vo.TrendQueryVo;
import com.ald.bigdata.modules.QQMini.service.QQMiniTrendService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * QQ小程序趋势分析模块controller
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "qq/mini/trend")
public class QQMiniTrendAnalysisReportController {

    @Autowired
    QQMiniTrendService trendService;

    private static final String PLATFORM = "qq";
    private static final String TYPE = "mini";
    /**
     * 趋势分析汇总
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "head", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult findAldstatTrendAnalysisTotal(@RequestBody JSONObject json) {
        // 根据传入的ak，类型等信息返回一个对应的jdbcTemplate。
        String app_key = json.get("app_key").toString();
        String te = json.get("te").toString();
        JdbcTemplate jdbcTemplate = chooseYourDataSource(app_key, te, PLATFORM, TYPE);
        trendService.setJdbcTemplate(jdbcTemplate);

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
        // 根据传入的ak，类型等信息返回一个对应的jdbcTemplate。
        String app_key = json.get("app_key").toString();
        String te = json.get("te").toString();
        JdbcTemplate jdbcTemplate = chooseYourDataSource(app_key, te, PLATFORM, TYPE);
        trendService.setJdbcTemplate(jdbcTemplate);

        TrendQueryVo vo = constructQueryObject(json);
        Pair<List, List> pair = trendService.tableData(vo);
        //JsonResult jsonResult = new JsonResult(pair);
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
        // 根据传入的ak，类型等信息返回一个对应的jdbcTemplate。
        String app_key = json.get("app_key").toString();
        String te = json.get("te").toString();
        JdbcTemplate jdbcTemplate = chooseYourDataSource(app_key, te, PLATFORM, TYPE);
        trendService.setJdbcTemplate(jdbcTemplate);

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
     * {
     * "type_name": "1",
     * "date": "2018-03-01 ~ 2019-04-06",
     * "date2": "2018-03-01 ~ 2019-04-06",
     * "data_type": "3",
     * "is_compare" : "1",
     * "token": "LlKyXHCPC7g876uMjPIG%2Ff5chon8g5xKYTTi9bWz0zGQsfv3WOdAt0%2BdrWKLSbGKlLXj22MPv%2FIiwLHpPKaJCwDR8AelznRjV9JCNRbzuVHTiKec5U3w16Xq6H61tJ8HFrJNx5ECvypkX0qR6ZR5%2BuMCddyywmlAK7U7kJzot%2FyHKmT5BFIOazcGHldJmsmwjrPq234qND5%2Br5v3cs2Drg%3D%3D",
     * "app_key": "1eda86f738edd4884efc3733173192db",
     * "/trend/data": "",
     * "transdate": [
     * "2019-03-01 ~ 2019-03-03",
     * "2019-03-04 ~ 2019-03-10",
     * "2019-03-11 ~ 2019-03-17",
     * "2019-03-18 ~ 2019-03-24",
     * "2019-03-25 ~ 2019-03-31",
     * "2019-04-01 ~ 2019-04-06"
     * ],
     * "transdate2": [
     * "2019-03-01 ~ 2019-03-03",
     * "2019-03-04 ~ 2019-03-10",
     * "2019-03-11 ~ 2019-03-17",
     * "2019-03-18 ~ 2019-03-24",
     * "2019-03-25 ~ 2019-03-31",
     * "2019-04-01 ~ 2019-04-06"
     * ]
     * }
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
