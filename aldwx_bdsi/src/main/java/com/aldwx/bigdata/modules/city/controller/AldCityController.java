package com.aldwx.bigdata.modules.city.controller;

import com.aldwx.bigdata.common.base.BaseController;
import com.aldwx.bigdata.modules.city.service.AldCityService;
import com.aldwx.bigdata.modules.city.util.CityUtils;
import com.aldwx.bigdata.modules.city.vo.AldCityVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 地域分析 -- City
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value="area/city")
public class AldCityController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AldCityController.class);

    @Autowired
    private AldCityService aldCityService;



    /**
     * 获取城市top
     * @return
     */
    @ResponseBody
    @RequestMapping(value="top", method = RequestMethod.POST)
    public String queryCityTop(String date, String type, String app_key) {
        AldCityVo vo = new AldCityVo(date, type, app_key);
        Map<String, String> checkMap = CityUtils.requestTopNParamCheck(vo);
        if(null != checkMap && checkMap.size() > 0) {
            return returnJson(checkMap);
        }
        Map<String, Object> resultMap = null;
        try {
            Map<String, Object> entityMap = aldCityService.queryCityDataTop(vo);
            resultMap = CityUtils.responseCityTopFormat(entityMap, vo);
        } catch (Exception e) {
            LOG.error("[" + this.getClass().getName() + "],数据处理异常：{}", e);
            return jsonFail("系统处理异常");
        }
        return resultJson2(resultMap, new Object[]{date});
    }


    /**
     * 获取城市图表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="chart", method = RequestMethod.POST)
    public String queryCityChart(String date, String type, String app_key) {
        AldCityVo vo = new AldCityVo(date, type, app_key);
        Map<String, String> checkMap = CityUtils.requestTopNParamCheck(vo);
        if(null != checkMap && checkMap.size() > 0) {
            return returnJson(checkMap);
        }
        Map<String, Object> resultMap = null;
        try {
            Map<String, Object> entityMap = aldCityService.queryCityDataChart(vo);
            resultMap = CityUtils.responseCityChartFormat(entityMap, vo);
        } catch (Exception e) {
            LOG.error("[" + this.getClass().getName() + "],数据处理异常{}", e);
            return jsonFail("系统处理异常");
        }
        return resultJson2(resultMap, new Object[]{date});
    }


    /**
     * 获取城市统计列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="list", method = RequestMethod.POST)
    public String queryCityList(String date, String type, String app_key, String currentPage, String pageSize,
                            String prop, String order) {
        AldCityVo vo = new AldCityVo(date, type, app_key, prop, order);
        vo.setCurrentPage(currentPage);
        vo.setTotal(pageSize);
        Map<String, String> checkMap = CityUtils.requestListParamCheck(vo);
        if(null != checkMap && checkMap.size() > 0) {
            return returnJson(checkMap);
        }
        Map<String, Object> resultMap = null;
        try{
            AldCityVo v1 = CityUtils.requestParamFormat(vo);
            Map<String, Object> entityMap = aldCityService.queryPageDataList(v1);
            resultMap = CityUtils.responseCityListFormat(entityMap, vo);
        } catch (Exception e) {
            LOG.error("[" + this.getClass().getName() + "],数据处理异常{}", e);
            return jsonFail("系统处理异常");
        }
        return resultJson2(resultMap, new Object[]{date});
    }




}
