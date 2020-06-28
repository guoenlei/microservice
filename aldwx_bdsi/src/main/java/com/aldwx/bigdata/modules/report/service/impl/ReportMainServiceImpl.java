package com.aldwx.bigdata.modules.report.service.impl;

import com.aldwx.bigdata.modules.report.service.ReportMainService;
import com.aldwx.bigdata.modules.report.vo.AldReportVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ReportMainServiceImpl implements ReportMainService {
    private com.aldwx.bigdata.common.base.report.ReportService aldStatReportService;

    public void setAldStatReportService(com.aldwx.bigdata.common.base.report.ReportService aldStatReportService) {
        this.aldStatReportService = aldStatReportService;
    }

    /**
     * 生成报表对象，格式
     * {name:"日",dateRange:"2019-01-20至2019-01-31",items:[{name:"SDK上报数",value："51"...}],talbe:[{name:"广告监测",value:""}...]}
     *
     * @param type        日，周，月
     * @param aldReportVo 查询对象
     * @return json，标准格式
     */
    public JSONObject generateReport(String type, AldReportVo aldReportVo) {
        JSONObject reportObject = new JSONObject();

        reportObject.put("items",mergeDataItems(type,aldReportVo));

        JSONObject talbleObject = new JSONObject();
        talbleObject.put("name","新上报用户主要功能使用频次");
        talbleObject.put("items",mergedataTable(type,aldReportVo));
        reportObject.put("table",talbleObject);
        return reportObject;
    }
    public JSONArray mergeDataItems(String type, AldReportVo aldReportVo){
        JSONArray jsonArray = new JSONArray();
        //注册量
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "注册量为");
            jsonObject.put("value", aldStatReportService.registCount(aldReportVo));
            jsonObject.put("rank", 1);
            jsonArray.add(jsonObject);
        }
        //登陆量
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "后台登录数");
            jsonObject.put("value", aldStatReportService.loginCount(aldReportVo));
            jsonObject.put("rank", 2);
            jsonArray.add(jsonObject);
        }
        //创建小程序数
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "总创建小程序数为");
            jsonObject.put("value", aldStatReportService.appCount(aldReportVo));
            jsonObject.put("rank", 3);
            jsonArray.add(jsonObject);
        }
        //新用户小程序数
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "新用户创建小程序数");
            jsonObject.put("value", aldStatReportService.appCountByNewUser(aldReportVo));
            jsonObject.put("rank", 4);
            jsonArray.add(jsonObject);
        }
        //老用户小程序数
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "老用户创建小程序数");
            jsonObject.put("value", aldStatReportService.appCountByOldUser(aldReportVo));
            jsonObject.put("rank", 4);
            jsonArray.add(jsonObject);
        }
        //SDK上报数
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "SDK上报数为");
            jsonObject.put("value", aldStatReportService.reportSDKCount(aldReportVo));
            jsonObject.put("rank", 5);
            jsonArray.add(jsonObject);
        }
        //新用户创建SDK上报数
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "新用户创建SDK上报数");
            jsonObject.put("value", aldStatReportService.reportSDKCountByNewUser(aldReportVo));
            jsonObject.put("rank", 6);
            jsonArray.add(jsonObject);
        }
        //老用户创建SDK上报数
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "老用户创建SDK上报数");
            jsonObject.put("value", aldStatReportService.reportSDKCountByOldUser(aldReportVo));
            jsonObject.put("rank", 7);
            jsonArray.add(jsonObject);
        }
        //大客户创建SDK上报数
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "大客户创建SDK上报数");
            jsonObject.put("value", aldStatReportService.reportSDKCreatedCountByVOL(aldReportVo));
            jsonObject.put("rank", 8);
            jsonArray.add(jsonObject);
        }
        //
//        1.注册量为：532，后台登录数：1937；
//        2.总创建小程序数为：1438，其中新用户创建了：433，老用户创建了：1005；
//        3.SDK上报数为：957，其中新用户SDK上报为：83，老用户SDK上报数为：874；
//        4.主要功能上报SDK数如下：
        return jsonArray;
    }
    public JSONArray mergedataTable(String type, AldReportVo aldReportVo){
        JSONArray jsonArray = new JSONArray();
        /**
         * 事件	869
         * 广告监测	39
         * 用户分群	0
         * 智能外链追踪	39
         * 二维码	7
         */
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "事件");
            jsonObject.put("value", aldStatReportService.activeEventFunSDKProp(aldReportVo));
            jsonObject.put("rank", 1);
            jsonArray.add(jsonObject);
        }
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "广告监测");
            jsonObject.put("value", aldStatReportService.activeAdFunSDKProp(aldReportVo));
            jsonObject.put("rank", 2);
            jsonArray.add(jsonObject);
        }
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "用户分群");
            jsonObject.put("value", aldStatReportService.activeUserFunSDKProp(aldReportVo));
            jsonObject.put("rank", 2);
            jsonArray.add(jsonObject);
        }
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "二维码");
            jsonObject.put("value", aldStatReportService.activeBarCodeFunSDKProp(aldReportVo));
            jsonObject.put("rank", 2);
            jsonArray.add(jsonObject);
        }
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "智能外链追踪");
            jsonObject.put("value", aldStatReportService.activeLinkFunSDKProp(aldReportVo));
            jsonObject.put("rank", 2);
            jsonArray.add(jsonObject);
        }
        return  jsonArray;
    }

}
