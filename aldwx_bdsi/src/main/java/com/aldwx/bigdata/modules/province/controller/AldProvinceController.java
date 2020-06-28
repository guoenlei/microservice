package com.aldwx.bigdata.modules.province.controller;

import com.aldwx.bigdata.common.base.BaseController;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.modules.city.util.CityUtils;
import com.aldwx.bigdata.modules.province.service.AldProvinceService;
import com.aldwx.bigdata.modules.province.util.ProvinceUtil;
import com.aldwx.bigdata.modules.province.vo.AldProvinceVo;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * 区域分析 -- 省份
 *
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value="area/province")
public class AldProvinceController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AldProvinceController.class);

    @Autowired
    private AldProvinceService aldProvinceService;


    /**
     * 获取省份top
     * @return
     */
    @ResponseBody
    @RequestMapping(value="top", method = RequestMethod.POST)
    public String queryProvinceTop(String date, String type, String app_key) {
        AldProvinceVo aldProvinceVo = new AldProvinceVo(date, type, app_key);
        Map<String, Object> checkMap = ProvinceUtil.topNParamCheck(aldProvinceVo);
        if(null != checkMap && checkMap.size() > 0) {
            return returnJson(checkMap);
        }

        Map<String, Object> resultMap = null;
        try {
//            AldProvinceVo v1 = ProvinceUtil.requestFormat(aldProvinceVo);
            Map<String, Object> entityMap = aldProvinceService.queryProvinceDataTop(aldProvinceVo);
            resultMap = ProvinceUtil.responseCityTopFormat(entityMap, aldProvinceVo);
        } catch (Exception e) {
            LOG.error("[" + this.getClass().getName() + "],数据处理异常：{}", e);
            return jsonError();
        }
        return resultJson2(resultMap, new Object[]{date});
    }


    /**
     * 获取省份图表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="chart", method = RequestMethod.POST)
    public String queryProvinceChart(String date, String type, String app_key) {


        return null;
    }


    /**
     * 获取区域统计列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="list", method = RequestMethod.POST)
    public String queryProvinceList(String date, String type, String app_key, String currentPage, String pageSize,
                                    String prop, String order) {


        return null;
    }



    public static void main(String[] args) {
        String today = DateUtil.getTodayDate() + " 00:00:00";
        Date todayDate = null;
        try {
            todayDate = DateUtil.TIME_FORMAT.parse(today);
            Date d1 = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(todayDate);
//            calendar.add(calendar.DATE,-1);
            long todayTime = calendar.getTimeInMillis()/1000L;
            System.out.println("todayTime:" + todayTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
