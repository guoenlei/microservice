package com.aldwx.app.modules.retain.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.common.util.DateUtil;
import com.aldwx.app.modules.retain.service.RetainService;
import com.aldwx.app.modules.retain.vo.RetainVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户画像模块
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="/aldstat/app/")
public class RetainController extends BaseController {

    @Autowired
    private RetainService retainService;
    //留存折线图
    @ResponseBody
    @RequestMapping(value = "keep/trend", method = RequestMethod.POST)
    public  Map<String,Object> findRetainLineChart(String app_key, String date, String app_type, String data_type){
        RetainVo vo = getPortraitVo(app_key,date,app_type,data_type);
        Map<String,Object> map=this.retainService.selectRetainLineChart(vo);
        return map;
    }
    //留存列表
    @ResponseBody
    @RequestMapping(value = "keep/particular", method = RequestMethod.POST)
    public  Map<String,Object> findRetainDetails(String app_key, String date, String app_type, String data_type){
        RetainVo vo = getPortraitVo(app_key,date,app_type,data_type);
        Map<String,Object> map=this.retainService.selectRetainDetail(vo);
        return map;
    }
    //活跃度趋势图
    @ResponseBody
    @RequestMapping(value = "active/trend", method = RequestMethod.POST)
    public  Map<String,Object> findActiveLinechart(String app_key, String date, String app_type, String data_type){
        RetainVo vo = getPortraitVo(app_key,date,app_type,data_type);
        Map<String,Object> map=this.retainService.selectActiveLineChart(vo);
        return map;
    }
    //活跃度列表
    @ResponseBody
    @RequestMapping(value = "active/particular", method = RequestMethod.POST)
    public  Map<String,Object> findActiveDetails(String app_key, String date, String app_type, String data_type){
        RetainVo vo = getPortraitVo(app_key,date,app_type,data_type);
        Map<String,Object> map=this.retainService.selectActiveDetail(vo);
        return map;
    }
    //忠诚度列表
    @ResponseBody
    @RequestMapping(value = "visit/chart", method = RequestMethod.POST)
    public  Map<String,Object> findDepthLinechart(String app_key, String date, String app_type, String data_type){
        RetainVo vo = getPortraitVo(app_key,date,app_type,data_type);
        Map<String,Object> map=this.retainService.selectDepthLineChart(vo);
        return map;
    }
    //活跃度列表
    @ResponseBody
    @RequestMapping(value = "visit/view", method = RequestMethod.POST)
    public  Map<String,Object> findDepthDetails(String app_key, String date, String app_type, String data_type){
        RetainVo vo = getPortraitVo(app_key,date,app_type,data_type);
        Map<String,Object> map=this.retainService.selectDepthDetail(vo);
        return map;
    }


    public static RetainVo getPortraitVo(String app_key, String date, String app_type, String data_type){
        RetainVo vo=new RetainVo();
        try{
            vo.setAppKey(app_key);
            vo.setAppType(app_type);
            vo.setDataType(data_type);
            if (date.equals("1")){
                vo.setDateStart(DateUtil.getTodayDate());
                vo.setDateEnd(vo.getDateStart());
                List<String> listTime =DateUtil.getBetweenDates3(vo.getDateStart(),vo.getDateStart());
                List<String> listWeek=DateUtil.getWeekList(listTime);
                vo.setListWeek(listWeek);
                vo.setListDate(listTime);
                vo.setDateType("1");
            }else if (date.equals("2")){
                String yesterday = DateUtil.getYesterday();
                vo.setDateStart(yesterday);
                vo.setDateEnd(yesterday);
                List<String> listTime =DateUtil.getBetweenDates3(yesterday,yesterday);
                List<String> listWeek=DateUtil.getWeekList(listTime);
                vo.setListWeek(listWeek);
                vo.setListDate(listTime);
                vo.setDateType("2");
            }else if (date.equals("3")){
                String[] day = DateUtil.getNearly7Day();
                vo.setDateStart(day[0]);
                vo.setDateEnd(day[1]);
                vo.setDateType("3");
                List<String> listTime =DateUtil.getBetweenDates3(day[0],day[1]);
                List<String> listWeek=DateUtil.getWeekList(listTime);
                vo.setListWeek(listWeek);
                vo.setListDate(listTime);
                List<String> listTemp=new ArrayList<>();
                for (int i=0;i<listWeek.size();i++){
                    String[] li=listWeek.get(i).split("~");
                    if (DateUtil.isSunDay(li[1])){
                        String dayTemp=DateUtil.getMonday(li[1]);
                        listTemp.add(dayTemp);
                    }
                }
                vo.setListDay(listTemp);
            }else if (date.equals("4")){
                String[] day = DateUtil.getNearly30Day();
                vo.setDateStart(day[0]);
                vo.setDateEnd(day[1]);
                vo.setDateType("4");
                List<String> listTime =DateUtil.getBetweenDates3(day[0],day[1]);
                List<String> listWeek=DateUtil.getWeekList(listTime);
                vo.setListWeek(listWeek);
                vo.setListDate(listTime);
                List<String> listTemp=new ArrayList<>();
                for (int i=0;i<listWeek.size();i++){
                    String[] li=listWeek.get(i).split("~");
                    if (DateUtil.isSunDay(li[1])){
                        String dayTemp=DateUtil.getMonday(li[1]);
                        listTemp.add(dayTemp);
                    }
                }
                vo.setListDay(listTemp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  vo;
    }

}
