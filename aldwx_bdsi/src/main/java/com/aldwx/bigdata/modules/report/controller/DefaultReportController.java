package com.aldwx.bigdata.modules.report.controller;

import com.aldwx.bigdata.common.base.BaseController;
import com.aldwx.bigdata.modules.report.service.impl.GameReportServiceImpl;
import com.aldwx.bigdata.modules.report.service.impl.MiniReportServiceImpl;
import com.aldwx.bigdata.modules.report.service.impl.ReportMainServiceImpl;
import com.aldwx.bigdata.modules.report.service.impl.StatReportServiceImpl;
import com.aldwx.bigdata.modules.report.vo.AldReportVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 统计平台指标
 *
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "stat/reportTest")
public class DefaultReportController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultReportController.class);

    @Autowired
    private ApplicationContext context;

//    @Autowired
//    private  MiniReportServiceImpl miniReportService;
    /**
     * 获取所有指标，模板如下
     *{"data":[{"data":{"items":[{"name":"注册量为","rank":1,"value":540}],"table":{"items":[{"name":"事件","rank":1,"value":966},{"name":"广告监测","rank":2,"value":39},{"name":"用户分群","rank":2,"value":0},{"name":"二维码","rank":2,"value":6},{"name":"智能外链追踪","rank":2,"value":39}],"name:":"新上报用户主要功能使用频次"}},"typeName":"小程序移动端"}],"dateRange":"2019-01-21 00:00:00","name":"当日"}
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public String getAllBy(String type, @RequestParam(value = "partner",required = false) Boolean partner) {
        AldReportVo aldReportVo = new AldReportVo();
        aldReportVo.setTime(aldReportVo, type);
        aldReportVo.setDate(aldReportVo, type);
        aldReportVo.setDateList(aldReportVo, type);
        JSONObject jsonObject = new JSONObject();
        String strPartnerDesc = "(不包含第三方)";
//        if(partner!=null && partner) {
//            aldReportVo.setPartner(true);
//            strPartnerDesc="第三方";
//        }
        switch (type) {
            case "1":
                jsonObject.put("name", "昨日");
                jsonObject.put("dateRange",aldReportVo.getStartDate());
                break;
            case "2":
                jsonObject.put("name", "周");
                jsonObject.put("dateRange",aldReportVo.getStartDate());
                break;
            case "3":
                jsonObject.put("name", "月");
                jsonObject.put("dateRange",aldReportVo.getStartDate()+"至"+aldReportVo.getEndDate());
                break;
            case "4":
//                jsonObject.put("name", "周");
//                jsonObject.put("dateRange",aldReportVo.getStartDate()+"至"+aldReportVo.getEndDate());
                break;
        }
        //    平台：1-统计，2-指数，3-小神推，4-小程序，5-小码
//        Map<String, Object> resultMap = Maps.newConcurrentMap();
        JSONArray data= new JSONArray();
        {
            JSONObject jo = new JSONObject();
            jo.put("typeName","统计平台"+strPartnerDesc);
            aldReportVo.setPlatform("1");
            ReportMainServiceImpl reportMainService = (ReportMainServiceImpl) context.getBean("reportMainServiceImpl");
            StatReportServiceImpl reportService = (StatReportServiceImpl) context.getBean("statReportServiceImpl");
            reportMainService.setAldStatReportService(reportService);
            JSONObject jsonMini = reportMainService.generateReport(type, aldReportVo);
            jo.put("data",jsonMini);
            data.add(jo);
        }
        {
            JSONObject jo = new JSONObject();
            strPartnerDesc="第三方";
            aldReportVo.setPlatform("1");
            aldReportVo.setPartner(true);
            jo.put("typeName","统计平台"+strPartnerDesc);
            ReportMainServiceImpl reportMainService = (ReportMainServiceImpl) context.getBean("reportMainServiceImpl");
            StatReportServiceImpl reportService = (StatReportServiceImpl) context.getBean("statReportServiceImpl");
            reportMainService.setAldStatReportService(reportService);
            JSONObject jsonMini = reportMainService.generateReport(type, aldReportVo);
            jo.put("data",jsonMini);
            data.add(jo);
        }
        {
            aldReportVo.setPlatform("1");
            aldReportVo.setPartner(false);
            JSONObject jo = new JSONObject();
            jo.put("typeName","小游戏");
            aldReportVo.setPlatform("");
            ReportMainServiceImpl reportMainService = (ReportMainServiceImpl) context.getBean("reportMainServiceImpl");
            GameReportServiceImpl reportService = (GameReportServiceImpl) context.getBean("gameReportServiceImpl");
            reportMainService.setAldStatReportService(reportService);
            JSONObject jsonMini = reportMainService.generateReport(type, aldReportVo);
            jo.put("data",jsonMini);
            data.add(jo);
        }
        {
            aldReportVo.setPlatform("4");
            aldReportVo.setPartner(false);
            JSONObject jo = new JSONObject();
            jo.put("typeName","小程序移动端");
            ReportMainServiceImpl reportMainService = (ReportMainServiceImpl) context.getBean("reportMainServiceImpl");
            MiniReportServiceImpl reportService = (MiniReportServiceImpl) context.getBean("miniReportServiceImpl");
            reportMainService.setAldStatReportService(reportService);
            JSONObject jsonMini = reportMainService.generateReport(type, aldReportVo);
            jo.put("data",jsonMini);
            data.add(jo);
        }
        jsonObject.put("data",data);
        return jsonObject.toJSONString();
    }


}
