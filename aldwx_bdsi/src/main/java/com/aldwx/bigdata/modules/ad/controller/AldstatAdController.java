package com.aldwx.bigdata.modules.ad.controller;

import com.aldwx.bigdata.common.util.DateTools;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.modules.ad.service.AldstatAdAnalysisService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.facebook.presto.jdbc.internal.spi.function.IsNull;
import com.github.pagehelper.PageInfo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Controller
@EnableAutoConfiguration
@RequestMapping("/aldstat/adMonitor2")
public class AldstatAdController {

    @Autowired
    private AldstatAdAnalysisService aldstatAdService;

    @ResponseBody
    @RequestMapping(value = "/openPageCount", method = RequestMethod.POST)
    public JSONObject openPageCount(@IsNull@NotBlank String app_key, @IsNull@NotBlank String date){ //时间只能是 1,2,3,4或者2018-10-11~2018-10-12
        JSONObject JSON = new JSONObject();
        try {
            List listDay = aldstatAdService.openPageCountService(app_key, date);
            if (listDay != null && listDay.size() > 0) {
                JSONObject jsonResutl= (JSONObject) JSONObject.toJSON(listDay.get(0));

                JSONObject jsonTitle=new JSONObject();
                if (jsonResutl.getString("naturalquantity")==null||Integer.parseInt(jsonResutl.getString("naturalquantity"))<0){
                    jsonTitle.put("naturalquantity","0");
                }else {
                    jsonTitle.put("naturalquantity",jsonResutl.getString("naturalquantity"));
                }
                if (jsonResutl.getString("total")==null||Integer.parseInt(jsonResutl.getString("total"))<0){
                    jsonTitle.put("total","0");
                }else {
                    jsonTitle.put("total",jsonResutl.getString("total"));
                }
                if (jsonResutl.getString("extend")==null||Integer.parseInt(jsonResutl.getString("extend"))<0){
                    jsonTitle.put("extend","0");
                }else {
                    jsonTitle.put("extend",jsonResutl.getString("extend"));
                }
                JSON.put("title",jsonTitle);
                JSON.put("code", 200);
                JSON.put("msg", "获取成功");
            } else {
                JSONObject jsonTitle=new JSONObject();
                jsonTitle.put("total","0");
                jsonTitle.put("extend","0");
                jsonTitle.put("naturalquantity","0");
                JSON.put("title", jsonTitle);
                JSON.put("code", 200);
                JSON.put("msg", "暂无数据");
            }
            if (date.equals("1") || date.equals("2")) {
                List listHour = aldstatAdService.openPageCountHourService(app_key, date);
                if (listHour != null && listHour.size() > 0) {
                    String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    JSONArray jsonArray = new JSONArray(listHour);
                    List<String> ListOpen = new ArrayList();
                    List<String> ListNatura = new ArrayList();
                    List<String> ListTotal = new ArrayList();
                    List<String> ListHour = new ArrayList();
                    for(int i = 1; i <= 24; i++) {
                        ListOpen.add("0");
                        ListNatura.add("0");
                        ListTotal.add("0");
                    }

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject job = jsonArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                        ListOpen.set(Integer.parseInt(job.getString("hour")),job.getString("extend"));
                        ListNatura.set(Integer.parseInt(job.getString("hour")),job.getString("naturalquantity"));
                        ListTotal.set(Integer.parseInt(job.getString("hour")),job.getString("total"));
                        //ListHour.add(getHour(job.getString("hour")));
                    }
                    JSON.put("xAxis",array );
                    JSONArray jsonArray1 = new JSONArray();
                    JSONObject json1 = new JSONObject();
                    json1.put("name", "推广量");
                    json1.put("data", ListOpen.toArray());
                    JSONObject json2 = new JSONObject();
                    json2.put("name", "自然量");
                    json2.put("data", ListNatura.toArray());
                    JSONObject json3 = new JSONObject();
                    json3.put("name", "总量");
                    json3.put("data", ListTotal.toArray());
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    JSON.put("series", jsonArray1);
                } else {
                    String[] array = new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    JSON.put("xAxis", array);
                    JSONArray jsonArray1 = new JSONArray();
                    JSONObject json1 = new JSONObject();
                    json1.put("name", "推广量");
                    String[] arrayhour = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
                    json1.put("data", arrayhour);
                    JSONObject json2 = new JSONObject();
                    json2.put("name", "自然量");
                    json2.put("data", arrayhour);
                    JSONObject json3 = new JSONObject();
                    json3.put("name", "总量");
                    json3.put("data", arrayhour);
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    JSON.put("series", jsonArray1);
                }
            } else {
                List listDays = aldstatAdService.openPageCountDayListService(app_key, date);
                if (listDays != null && listDays.size() > 0) {
                    //String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    JSONArray jsonArray = new JSONArray(listDays);
                    List<String> ListOpen = new ArrayList();
                    List<String> ListNatura = new ArrayList();
                    List<String> ListTotal = new ArrayList();
                    List<String> ListTime = new ArrayList();
                    if (date.equals("3")){
                        for (int i=1;i<=7;i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else if (date.equals("4")){
                        for (int i=1;i<=30;i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else{
                        String[] strArr = date.split("~");
                        String dateStart=strArr[0];
                        String dateEnd=strArr[1];
                        ListTime=DateUtil.getBetweenDates3(dateStart,dateEnd);
                        for (int i=0;i<ListTime.size();i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.set(i,ListTime.get(i).replaceAll("-", "/"));
                        }
                    }

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject job = jsonArray.getJSONObject(i);  //
                        String day=job.getString("day").replaceAll("-", "/");
                        int index=getIndex(ListTime,day);
                        if (index>=0){
                            ListOpen.set(index,job.getString("extend"));
                            ListNatura.set(index,job.getString("naturalquantity"));
                            ListTotal.set(index,job.getString("total"));
                        }
                    }
                    JSON.put("xAxis", ListTime.toArray());
                    JSONArray jsonArray1 = new JSONArray();
                    JSONObject json1 = new JSONObject();
                    json1.put("name", "推广量");
                    json1.put("data", ListOpen.toArray());
                    JSONObject json2 = new JSONObject();
                    json2.put("name", "自然量");
                    json2.put("data", ListNatura.toArray());
                    JSONObject json3 = new JSONObject();
                    json3.put("name", "总量");
                    json3.put("data", ListTotal.toArray());
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    JSON.put("series", jsonArray1);
                } else {
                    List<String> ListOpen = new ArrayList();
                    List<String> ListNatura = new ArrayList();
                    List<String> ListTotal = new ArrayList();
                    List<String> ListTime = new ArrayList();
                    if (date.equals("3")){
                        for (int i=1;i<=7;i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else if (date.equals("4")){
                        for (int i=1;i<=30;i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else{
                        String[] strArr = date.split("~");
                        String dateStart=strArr[0];
                        String dateEnd=strArr[1];
                        ListTime=DateUtil.getBetweenDates3(dateStart,dateEnd);
                        for (int i=0;i<ListTime.size();i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.set(i,ListTime.get(i).replaceAll("-", "/"));
                        }
                    }
                    JSON.put("xAxis",ListTime);
                    JSONArray jsonArray1=new JSONArray();
                    JSONObject json1=new JSONObject();
                    json1.put("name","推广量");
                    // String[] arrayhour= new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
                    json1.put("data",ListOpen);
                    JSONObject json2=new JSONObject();
                    json2.put("name","自然量");
                    json2.put("data",ListNatura);
                    JSONObject json3=new JSONObject();
                    json3.put("name","总量");
                    json3.put("data",ListTotal);
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    JSON.put("series",jsonArray1);
                }

            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            JSON.put("500","异常");
        }
        return  JSON;
    }

    @ResponseBody
    @RequestMapping(value = "/visitUserCount", method = RequestMethod.POST)
    public JSONObject visitUserCount(@IsNull@NotBlank String app_key, @IsNull@NotBlank String date){
        JSONObject JSON =new JSONObject();
        try {
            List listDay = aldstatAdService.openUserCountService(app_key, date);
            if (listDay != null && listDay.size() > 0) {
                JSONObject jsonResutl= (JSONObject) JSONObject.toJSON(listDay.get(0));

                JSONObject jsonTitle=new JSONObject();
                if (jsonResutl.getString("naturalquantity")==null||Integer.parseInt(jsonResutl.getString("naturalquantity"))<0){
                    jsonTitle.put("naturalquantity","0");
                }else {
                    jsonTitle.put("naturalquantity",jsonResutl.getString("naturalquantity"));
                }
                if (jsonResutl.getString("total")==null||Integer.parseInt(jsonResutl.getString("total"))<0){
                    jsonTitle.put("total","0");
                }else {
                    jsonTitle.put("total",jsonResutl.getString("total"));
                }
                if (jsonResutl.getString("extend")==null||Integer.parseInt(jsonResutl.getString("extend"))<0){
                    jsonTitle.put("extend","0");
                }else {
                    jsonTitle.put("extend",jsonResutl.getString("extend"));
                }
                JSON.put("title",jsonTitle);
                JSON.put("code", 200);
                JSON.put("msg", "获取成功");
            } else {
                JSONObject jsonTitle=new JSONObject();
                jsonTitle.put("total","0");
                jsonTitle.put("extend","0");
                jsonTitle.put("naturalquantity","0");
                JSON.put("title", jsonTitle);
                JSON.put("code", 200);
                JSON.put("msg", "暂无数据");
            }
            if (date.equals("1") || date.equals("2")) {
                List listHour = aldstatAdService.openUserCountHourService(app_key, date);
                if (listHour != null && listHour.size() > 0) {
                    String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    JSONArray jsonArray = new JSONArray(listHour);
                    List<String> ListOpen = new ArrayList();
                    List<String> ListNatura = new ArrayList();
                    List<String> ListTotal = new ArrayList();
                    List<String> ListHour = new ArrayList();
                    for(int i = 1; i <= 24; i++) {
                        ListOpen.add("0");
                        ListNatura.add("0");
                        ListTotal.add("0");
                    }

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject job = jsonArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                        ListOpen.set(Integer.parseInt(job.getString("hour")),job.getString("extend"));
                        ListNatura.set(Integer.parseInt(job.getString("hour")),job.getString("naturalquantity"));
                        ListTotal.set(Integer.parseInt(job.getString("hour")),job.getString("total"));
                        //ListHour.add(getHour(job.getString("hour")));
                    }
                    JSON.put("xAxis",array );
                    JSONArray jsonArray1 = new JSONArray();
                    JSONObject json1 = new JSONObject();
                    json1.put("name", "推广量");
                    json1.put("data", ListOpen.toArray());
                    JSONObject json2 = new JSONObject();
                    json2.put("name", "自然量");
                    json2.put("data", ListNatura.toArray());
                    JSONObject json3 = new JSONObject();
                    json3.put("name", "总量");
                    json3.put("data", ListTotal.toArray());
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    JSON.put("series", jsonArray1);
                } else {
                    String[] array = new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    JSON.put("xAxis", array);
                    JSONArray jsonArray1 = new JSONArray();
                    JSONObject json1 = new JSONObject();
                    json1.put("name", "推广量");
                    String[] arrayhour = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
                    json1.put("data", arrayhour);
                    JSONObject json2 = new JSONObject();
                    json2.put("name", "自然量");
                    json2.put("data", arrayhour);
                    JSONObject json3 = new JSONObject();
                    json3.put("name", "总量");
                    json3.put("data", arrayhour);
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    JSON.put("series", jsonArray1);
                }
            } else {
                List listDays = aldstatAdService.openUserCountDayListService(app_key, date);
                if (listDays != null && listDays.size() > 0) {
                    //String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    JSONArray jsonArray = new JSONArray(listDays);
                    List<String> ListOpen = new ArrayList();
                    List<String> ListNatura = new ArrayList();
                    List<String> ListTotal = new ArrayList();
                    List<String> ListTime = new ArrayList();
                    if (date.equals("3")){
                        for (int i=1;i<=7;i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else if (date.equals("4")){
                        for (int i=1;i<=30;i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else{
                        String[] strArr = date.split("~");
                        String dateStart=strArr[0];
                        String dateEnd=strArr[1];
                        ListTime=DateUtil.getBetweenDates3(dateStart,dateEnd);
                        for (int i=0;i<ListTime.size();i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.set(i,ListTime.get(i).replaceAll("-", "/"));
                        }
                    }

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject job = jsonArray.getJSONObject(i);  //
                        String day=job.getString("day").replaceAll("-", "/");
                        int index=getIndex(ListTime,day);
                        if (index>=0){
                            ListOpen.set(index,job.getString("extend"));
                            ListNatura.set(index,job.getString("naturalquantity"));
                            ListTotal.set(index,job.getString("total"));
                        }
                    }
                    JSON.put("xAxis", ListTime.toArray());
                    JSONArray jsonArray1 = new JSONArray();
                    JSONObject json1 = new JSONObject();
                    json1.put("name", "推广量");
                    json1.put("data", ListOpen.toArray());
                    JSONObject json2 = new JSONObject();
                    json2.put("name", "自然量");
                    json2.put("data", ListNatura.toArray());
                    JSONObject json3 = new JSONObject();
                    json3.put("name", "总量");
                    json3.put("data", ListTotal.toArray());
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    JSON.put("series", jsonArray1);
                } else {
                    List<String> ListOpen = new ArrayList();
                    List<String> ListNatura = new ArrayList();
                    List<String> ListTotal = new ArrayList();
                    List<String> ListTime = new ArrayList();
                    if (date.equals("3")){
                        for (int i=1;i<=7;i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else if (date.equals("4")){
                        for (int i=1;i<=30;i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else{
                        String[] strArr = date.split("~");
                        String dateStart=strArr[0];
                        String dateEnd=strArr[1];
                        ListTime=DateUtil.getBetweenDates3(dateStart,dateEnd);
                        for (int i=0;i<ListTime.size();i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.set(i,ListTime.get(i).replaceAll("-", "/"));
                        }
                    }
                    JSON.put("xAxis",ListTime);
                    JSONArray jsonArray1=new JSONArray();
                    JSONObject json1=new JSONObject();
                    json1.put("name","推广量");
                    // String[] arrayhour= new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
                    json1.put("data",ListOpen);
                    JSONObject json2=new JSONObject();
                    json2.put("name","自然量");
                    json2.put("data",ListNatura);
                    JSONObject json3=new JSONObject();
                    json3.put("name","总量");
                    json3.put("data",ListTotal);
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    JSON.put("series",jsonArray1);
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
            JSON.put("500","异常");
        }
        return  JSON;
    }
    @ResponseBody
    @RequestMapping(value = "/newUserCount", method = RequestMethod.POST)
    public JSONObject newUserCount(@IsNull@NotBlank String app_key, @IsNull@NotBlank String date){
        JSONObject JSON =new JSONObject();
        try {
            List listDay = aldstatAdService.newUserCountService(app_key, date);
            if (listDay != null && listDay.size() > 0) {
                JSONObject jsonResutl= (JSONObject) JSONObject.toJSON(listDay.get(0));

                JSONObject jsonTitle=new JSONObject();
                if (jsonResutl.getString("naturalquantity")==null||Integer.parseInt(jsonResutl.getString("naturalquantity"))<0){
                    jsonTitle.put("naturalquantity","0");
                }else {
                    jsonTitle.put("naturalquantity",jsonResutl.getString("naturalquantity"));
                }
                if (jsonResutl.getString("total")==null||Integer.parseInt(jsonResutl.getString("total"))<0){
                    jsonTitle.put("total","0");
                }else {
                    jsonTitle.put("total",jsonResutl.getString("total"));
                }
                if (jsonResutl.getString("extend")==null||Integer.parseInt(jsonResutl.getString("extend"))<0){
                    jsonTitle.put("extend","0");
                }else {
                    jsonTitle.put("extend",jsonResutl.getString("extend"));
                }
                JSON.put("title",jsonTitle);
                JSON.put("code", 200);
                JSON.put("msg", "获取成功");
            } else {
                JSONObject jsonTitle=new JSONObject();
                jsonTitle.put("total","0");
                jsonTitle.put("extend","0");
                jsonTitle.put("naturalquantity","0");
                JSON.put("title", jsonTitle);
                JSON.put("code", 200);
                JSON.put("msg", "暂无数据");
            }
            if (date.equals("1") || date.equals("2")) {
                List listHour = aldstatAdService.newUserCountHourService(app_key, date);
                if (listHour != null && listHour.size() > 0) {
                    String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    JSONArray jsonArray = new JSONArray(listHour);
                    List<String> ListOpen = new ArrayList();
                    List<String> ListNatura = new ArrayList();
                    List<String> ListTotal = new ArrayList();
                    List<String> ListHour = new ArrayList();
                    for(int i = 1; i <= 24; i++) {
                        ListOpen.add("0");
                        ListNatura.add("0");
                        ListTotal.add("0");
                    }

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject job = jsonArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                        ListOpen.set(Integer.parseInt(job.getString("hour")),job.getString("extend"));
                        ListNatura.set(Integer.parseInt(job.getString("hour")),job.getString("naturalquantity"));
                        ListTotal.set(Integer.parseInt(job.getString("hour")),job.getString("total"));
                        //ListHour.add(getHour(job.getString("hour")));
                    }
                    JSON.put("xAxis",array );
                    JSONArray jsonArray1 = new JSONArray();
                    JSONObject json1 = new JSONObject();
                    json1.put("name", "推广量");
                    json1.put("data", ListOpen.toArray());
                    JSONObject json2 = new JSONObject();
                    json2.put("name", "自然量");
                    json2.put("data", ListNatura.toArray());
                    JSONObject json3 = new JSONObject();
                    json3.put("name", "总量");
                    json3.put("data", ListTotal.toArray());
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    JSON.put("series", jsonArray1);
                } else {
                    String[] array = new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    JSON.put("xAxis", array);
                    JSONArray jsonArray1 = new JSONArray();
                    JSONObject json1 = new JSONObject();
                    json1.put("name", "推广量");
                    String[] arrayhour = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
                    json1.put("data", arrayhour);
                    JSONObject json2 = new JSONObject();
                    json2.put("name", "自然量");
                    json2.put("data", arrayhour);
                    JSONObject json3 = new JSONObject();
                    json3.put("name", "总量");
                    json3.put("data", arrayhour);
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    JSON.put("series", jsonArray1);
                }
            } else {
                List listDays = aldstatAdService.newUserCountDayListService(app_key, date);
                if (listDays != null && listDays.size() > 0) {
                    //String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    JSONArray jsonArray = new JSONArray(listDays);
                    List<String> ListOpen = new ArrayList();
                    List<String> ListNatura = new ArrayList();
                    List<String> ListTotal = new ArrayList();
                    List<String> ListTime = new ArrayList();
                    if (date.equals("3")){
                        for (int i=1;i<=7;i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else if (date.equals("4")){
                        for (int i=1;i<=30;i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else{
                        String[] strArr = date.split("~");
                        String dateStart=strArr[0];
                        String dateEnd=strArr[1];
                        ListTime=DateUtil.getBetweenDates3(dateStart,dateEnd);
                        for (int i=0;i<ListTime.size();i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.set(i,ListTime.get(i).replaceAll("-", "/"));
                        }
                    }

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject job = jsonArray.getJSONObject(i);  //
                        String day=job.getString("day").replaceAll("-", "/");
                        int index=getIndex(ListTime,day);
                        if (index>=0){
                            ListOpen.set(index,job.getString("extend"));
                            ListNatura.set(index,job.getString("naturalquantity"));
                            ListTotal.set(index,job.getString("total"));
                        }
                    }
                    JSON.put("xAxis", ListTime.toArray());
                    JSONArray jsonArray1 = new JSONArray();
                    JSONObject json1 = new JSONObject();
                    json1.put("name", "推广量");
                    json1.put("data", ListOpen.toArray());
                    JSONObject json2 = new JSONObject();
                    json2.put("name", "自然量");
                    json2.put("data", ListNatura.toArray());
                    JSONObject json3 = new JSONObject();
                    json3.put("name", "总量");
                    json3.put("data", ListTotal.toArray());
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    JSON.put("series", jsonArray1);
                } else {
                    List<String> ListOpen = new ArrayList();
                    List<String> ListNatura = new ArrayList();
                    List<String> ListTotal = new ArrayList();
                    List<String> ListTime = new ArrayList();
                    if (date.equals("3")){
                        for (int i=1;i<=7;i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else if (date.equals("4")){
                        for (int i=1;i<=30;i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else{
                        String[] strArr = date.split("~");
                        String dateStart=strArr[0];
                        String dateEnd=strArr[1];
                        ListTime=DateUtil.getBetweenDates3(dateStart,dateEnd);
                        for (int i=0;i<ListTime.size();i++){
                            ListOpen.add("0");
                            ListNatura.add("0");
                            ListTotal.add("0");
                            ListTime.set(i,ListTime.get(i).replaceAll("-", "/"));
                        }
                    }
                    JSON.put("xAxis",ListTime);
                    JSONArray jsonArray1=new JSONArray();
                    JSONObject json1=new JSONObject();
                    json1.put("name","推广量");
                    // String[] arrayhour= new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
                    json1.put("data",ListOpen);
                    JSONObject json2=new JSONObject();
                    json2.put("name","自然量");
                    json2.put("data",ListNatura);
                    JSONObject json3=new JSONObject();
                    json3.put("name","总量");
                    json3.put("data",ListTotal);
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    JSON.put("series",jsonArray1);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            JSON.put("500","异常");
        }
        return  JSON;
    }
  @ResponseBody
    @RequestMapping(value = "/newAuthUserCount", method = RequestMethod.POST)
    public JSONObject newAuthUserCount(@IsNull@NotBlank String app_key, @IsNull@NotBlank String date){
        JSONObject JSON =new JSONObject();
        try {
        List listDay= aldstatAdService.newAuthUserCountService(app_key,date);
      if (listDay!=null&&listDay.size()>0) {
          JSONObject jsonResutl= (JSONObject) JSONObject.toJSON(listDay.get(0));

          JSONObject jsonTitle=new JSONObject();
          if (jsonResutl.getString("naturalquantity")==null||Integer.parseInt(jsonResutl.getString("naturalquantity"))<0){
              jsonTitle.put("naturalquantity","0");
          }else {
              jsonTitle.put("naturalquantity",jsonResutl.getString("naturalquantity"));
          }
          if (jsonResutl.getString("total")==null||Integer.parseInt(jsonResutl.getString("total"))<0){
              jsonTitle.put("total","0");
          }else {
              jsonTitle.put("total",jsonResutl.getString("total"));
          }
          if (jsonResutl.getString("extend")==null||Integer.parseInt(jsonResutl.getString("extend"))<0){
              jsonTitle.put("extend","0");
          }else {
              jsonTitle.put("extend",jsonResutl.getString("extend"));
          }
          JSON.put("title",jsonTitle);
          JSON.put("code", 200);
          JSON.put("msg", "获取成功");
      }else {
          JSONObject jsonTitle=new JSONObject();
          jsonTitle.put("total","0");
          jsonTitle.put("extend","0");
          jsonTitle.put("naturalquantity","0");
          JSON.put("title", jsonTitle);
          JSON.put("code", 200);
          JSON.put("msg", "暂无数据");
      }
        if(date.equals("1")||date.equals("2")){
            List listHour=aldstatAdService.newAuthUserHourCountService(app_key,date);
            if (listHour!=null&&listHour.size()>0){
                //String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                JSONArray jsonArray = new JSONArray(listHour);
                List<String> ListOpen = new ArrayList();
                List<String> ListNatura = new ArrayList();
                List<String> ListTotal = new ArrayList();
                List<String> ListHour = new ArrayList();
                for(int i = 1; i <= 24; i++) {
                    ListOpen.add("0");
                    ListNatura.add("0");
                    ListTotal.add("0");
                }

                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject job = jsonArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    ListOpen.set(Integer.parseInt(job.getString("hour")),job.getString("extend"));
                    ListNatura.set(Integer.parseInt(job.getString("hour")),job.getString("naturalquantity"));
                    ListTotal.set(Integer.parseInt(job.getString("hour")),job.getString("total"));
                    //ListHour.add(getHour(job.getString("hour")));
                }
                JSON.put("xAxis",array );
                JSONArray jsonArray1=new JSONArray();
                JSONObject json1=new JSONObject();
                json1.put("name","推广量");
                json1.put("data",ListOpen.toArray());
                JSONObject json2=new JSONObject();
                json2.put("name","自然量");
                json2.put("data",ListNatura.toArray());
                JSONObject json3=new JSONObject();
                json3.put("name","总量");
                json3.put("data",ListTotal.toArray());
                jsonArray1.add(json1);
                jsonArray1.add(json2);
                jsonArray1.add(json3);
                JSON.put("series",jsonArray1);
            }else{
                String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                JSON.put("xAxis",array);
                JSONArray jsonArray1=new JSONArray();
                JSONObject json1=new JSONObject();
                json1.put("name","推广量");
                String[] arrayhour= new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
                json1.put("data",arrayhour);
                JSONObject json2=new JSONObject();
                json2.put("name","自然量");
                json2.put("data",arrayhour);
                JSONObject json3=new JSONObject();
                json3.put("name","总量");
                json3.put("data",arrayhour);
                jsonArray1.add(json1);
                jsonArray1.add(json2);
                jsonArray1.add(json3);
                JSON.put("series",jsonArray1);
            }
        }else{
            List listDays=aldstatAdService.newAuthUserCountDayListService(app_key,date);
            if (listDays!=null&&listDays.size()>0){
                //String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                JSONArray jsonArray=new JSONArray(listDays);
                List<String> ListOpen = new ArrayList();
                List<String> ListNatura = new ArrayList();
                List<String> ListTotal = new ArrayList();
                List<String> ListTime = new ArrayList();
                if (date.equals("3")){
                    for (int i=1;i<=7;i++){
                        ListOpen.add("0");
                        ListNatura.add("0");
                        ListTotal.add("0");
                        ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                    }
                }else if (date.equals("4")){
                    for (int i=1;i<=30;i++){
                        ListOpen.add("0");
                        ListNatura.add("0");
                        ListTotal.add("0");
                        ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                    }
                }else{
                    String[] strArr = date.split("~");
                    String dateStart=strArr[0];
                    String dateEnd=strArr[1];
                    ListTime=DateUtil.getBetweenDates3(dateStart,dateEnd);
                    for (int i=0;i<ListTime.size();i++){
                        ListOpen.add("0");
                        ListNatura.add("0");
                        ListTotal.add("0");
                        ListTime.set(i,ListTime.get(i).replaceAll("-", "/"));
                    }
                }

                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject job = jsonArray.getJSONObject(i);  //
                    String day=job.getString("day").replaceAll("-", "/");
                    int index=getIndex(ListTime,day);
                    if (index>=0){
                        ListOpen.set(index,job.getString("extend"));
                        ListNatura.set(index,job.getString("naturalquantity"));
                        ListTotal.set(index,job.getString("total"));
                    }
                }
                JSON.put("xAxis",ListTime.toArray());
                JSONArray jsonArray1=new JSONArray();
                JSONObject json1=new JSONObject();
                json1.put("name","推广量");
                json1.put("data",ListOpen.toArray());
                JSONObject json2=new JSONObject();
                json2.put("name","自然量");
                json2.put("data",ListNatura.toArray());
                JSONObject json3=new JSONObject();
                json3.put("name","总量");
                json3.put("data",ListTotal.toArray());
                jsonArray1.add(json1);
                jsonArray1.add(json2);
                jsonArray1.add(json3);
                JSON.put("series",jsonArray1);
            }else{
                List<String> ListOpen = new ArrayList();
                List<String> ListNatura = new ArrayList();
                List<String> ListTotal = new ArrayList();
                List<String> ListTime = new ArrayList();
                if (date.equals("3")){
                    for (int i=1;i<=7;i++){
                        ListOpen.add("0");
                        ListNatura.add("0");
                        ListTotal.add("0");
                        ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                    }
                }else if (date.equals("4")){
                    for (int i=1;i<=30;i++){
                        ListOpen.add("0");
                        ListNatura.add("0");
                        ListTotal.add("0");
                        ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                    }
                }else{
                    String[] strArr = date.split("~");
                    String dateStart=strArr[0];
                    String dateEnd=strArr[1];
                    ListTime=DateUtil.getBetweenDates3(dateStart,dateEnd);
                    for (int i=0;i<ListTime.size();i++){
                        ListOpen.add("0");
                        ListNatura.add("0");
                        ListTotal.add("0");
                        ListTime.set(i,ListTime.get(i).replaceAll("-", "/"));
                    }
                }
                JSON.put("xAxis",ListTime);
                JSONArray jsonArray1=new JSONArray();
                JSONObject json1=new JSONObject();
                json1.put("name","推广量");
               // String[] arrayhour= new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
                json1.put("data",ListOpen);
                JSONObject json2=new JSONObject();
                json2.put("name","自然量");
                json2.put("data",ListNatura);
                JSONObject json3=new JSONObject();
                json3.put("name","总量");
                json3.put("data",ListTotal);
                jsonArray1.add(json1);
                jsonArray1.add(json2);
                jsonArray1.add(json3);
                JSON.put("series",jsonArray1);
            }
        }
        }catch (Exception e) {
            e.printStackTrace();
            JSON.put("500","异常");
        }
        return  JSON;
    }
    //总列表
    @ResponseBody
    @RequestMapping(value = "/pupoAnalyzeList", method = RequestMethod.POST)
    public JSONObject pupoAnalyzeList(String app_key, @IsNull@NotBlank String date, @IsNull@NotBlank String source, String typeId, String keyword, @NotNull @NotBlank String currentPage, @NotNull@NotBlank String total, String prop, String order, String scenceName, String linkName, String channelName){
        PageInfo info= aldstatAdService.pupoAnalyzeListService(app_key,date,source,typeId,keyword,currentPage,total,prop,order,scenceName,linkName,channelName);
        List list=info.getList();
        JSONObject JSON =new JSONObject();
        if (list!=null&&list.size()>0) {
            JSON.put("title",JSONObject.toJSON(list));
            JSON.put("count",info.getTotal());
            JSON.put("code",200);
            JSON.put("msg", "获取成功");
        }else {
            JSON.put("code", 202);
            JSON.put("msg", "暂无数据");
        }
        //或者一段时间(公共方法)
        return JSON;
    }
    //详情列表
  /*  @ResponseBody
    @RequestMapping(value = "/pupoAnalyzeListDetail", method = RequestMethod.POST)
    public JSONObject pupoAnalyzeListDetail(@IsNull@NotBlank String app_key, @IsNull@NotBlank String date,@IsNull@NotBlank String source,String typeId,String keyword){
        List listDay= null;
        listDay= aldstatAdService.pupoAnalyzeListSomeDayService(app_key,date,source,typeId,keyword);
        JSONObject JSON =new JSONObject();
        if (listDay!=null&&listDay.size()>0) {
            JSON.put("title",JSONObject.toJSON(listDay));
            JSON.put("code",200);
            JSON.put("msg", "获取成功");
        }else {
            JSONObject jsonTitle=new JSONObject();
            JSON.put("code", 202);
            JSON.put("msg", "暂无数据");
        }
        return JSON;
    }*/
    //详情列表折线图
    @ResponseBody
    @RequestMapping(value = "/pupoAnalyzeListDetailLine", method = RequestMethod.POST)
    public JSONObject pupoAnalyzeListDetailLine(@IsNull@NotBlank String app_key, @IsNull@NotBlank String date,@IsNull@NotBlank String source,String scenceName,String linkName,String channelName){


        JSONObject JSON =new JSONObject();
        JSONObject jsonOneDay=null;
        JSONArray jsonArrayResult = new JSONArray();
        JSONArray jsonArrayListDay=null;
        try {
            List listDay= aldstatAdService.pupoAnalyzeListSomeDayService(app_key,date,source,scenceName,linkName,channelName);
            jsonArrayListDay = new JSONArray(listDay);
            // JSONObject JSON =new JSONObject();
            if (listDay!=null&&listDay.size()>0) {
                if (date.equals("1")||date.equals("2")){
                    String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};


                    for(int i = 0; i <= 23; i++) {
                        jsonOneDay=new JSONObject();
                        jsonOneDay.put("authuser_count",0);
                        jsonOneDay.put("visitor_count",0);
                        jsonOneDay.put("secondary_avg_stay_time",0);
                        jsonOneDay.put("new_comer_count",0);
                        jsonOneDay.put("total_page_count",0);
                        jsonOneDay.put("bounce_rate",0);
                        jsonOneDay.put("open_count",0);
                        jsonOneDay.put("hour",i+1);
                        jsonArrayResult.add(jsonOneDay);
                    }
                    for (int i = 0; i < jsonArrayListDay.size();i++){
                        JSONObject jsonObject = jsonArrayListDay.getJSONObject(i);
                        String hour =jsonObject.getString("hour");
                        jsonArrayResult.set(Integer.parseInt(hour),jsonObject);
                    }


                }else{
                    List<String> ListTime = new ArrayList();
                    if (date.equals("3")){
                        for (int i=1;i<=7;i++){
                            jsonOneDay=new JSONObject();
                            jsonOneDay.put("authuser_count",0);
                            jsonOneDay.put("visitor_count",0);
                            jsonOneDay.put("secondary_avg_stay_time",0);
                            jsonOneDay.put("new_comer_count",0);
                            jsonOneDay.put("total_page_count",0);
                            jsonOneDay.put("bounce_rate",0);
                            jsonOneDay.put("open_count",0);
                            jsonOneDay.put("day",DateTools.addDate(-i));
                            jsonArrayResult.add(jsonOneDay);
                            ListTime.add(DateTools.addDate(-i));
                        }
                    }else if (date.equals("4")){
                        for (int i=1;i<=30;i++){
                            jsonOneDay=new JSONObject();
                            jsonOneDay.put("authuser_count",0);
                            jsonOneDay.put("visitor_count",0);
                            jsonOneDay.put("secondary_avg_stay_time",0);
                            jsonOneDay.put("new_comer_count",0);
                            jsonOneDay.put("total_page_count",0);
                            jsonOneDay.put("bounce_rate",0);
                            jsonOneDay.put("open_count",0);
                            jsonOneDay.put("day",DateTools.addDate(-i));
                            jsonArrayResult.add(jsonOneDay);
                            ListTime.add(DateTools.addDate(-i));
                        }
                    }else{
                        String[] strArr = date.split("~");
                        String dateStart=strArr[0];
                        String dateEnd=strArr[1];
                        ListTime=DateUtil.getBetweenDates3(dateStart,dateEnd);
                        for (int i=0;i<ListTime.size();i++){
                            jsonOneDay=new JSONObject();
                            jsonOneDay.put("authuser_count",0);
                            jsonOneDay.put("visitor_count",0);
                            jsonOneDay.put("secondary_avg_stay_time",0);
                            jsonOneDay.put("new_comer_count",0);
                            jsonOneDay.put("total_page_count",0);
                            jsonOneDay.put("bounce_rate",0);
                            jsonOneDay.put("open_count",0);
                            jsonOneDay.put("day",DateTools.addDate(-i));
                            jsonArrayResult.add(jsonOneDay);
                        }
                    }
                    for (int i = 0; i < jsonArrayListDay.size(); i++) {
                        JSONObject job = jsonArrayListDay.getJSONObject(i);  //
                        String day=job.getString("day");
                        int index=getIndex(ListTime,day);
                        if (index>=0){
                            jsonArrayResult.set(index,job);
                        }
                    }

                }
                JSON.put("list",jsonArrayResult);
                JSON.put("code",200);
                JSON.put("msg", "获取成功");
            }else {
                JSONObject jsonTitle=new JSONObject();
                JSON.put("code", 202);
                JSON.put("list","");
                JSON.put("msg", "暂无数据");
            }

            if(date.equals("1")||date.equals("2")){

                if (listDay!=null&&listDay.size()>0){
                    //String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    JSONArray jsonArray = new JSONArray(listDay);
                    List<String> ListNewUser = new ArrayList();
                    List<String> ListVisiteTimes = new ArrayList();
                    List<String> ListVisitePeople= new ArrayList();
                    List<String> listOpenCount = new ArrayList();
                    for(int i = 1; i <= 24; i++) {
                        ListNewUser.add("0");
                        ListVisiteTimes.add("0");
                        ListVisitePeople.add("0");
                        listOpenCount.add("0");
                    }

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject job = jsonArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                        ListNewUser.set(Integer.parseInt(job.getString("hour")),job.getString("new_comer_count"));
                        ListVisiteTimes.set(Integer.parseInt(job.getString("hour")),job.getString("total_page_count"));
                        ListVisitePeople.set(Integer.parseInt(job.getString("hour")),job.getString("visitor_count"));
                        listOpenCount.set(Integer.parseInt(job.getString("hour")),job.getString("open_count"));
                        //ListHour.add(getHour(job.getString("hour")));
                    }
                    JSON.put("xAxis",array );
                    JSONArray jsonArray1=new JSONArray();
                    JSONObject json1=new JSONObject();
                    json1.put("新用户数",ListNewUser.toArray());
                    JSONObject json2=new JSONObject();
                    json2.put("访问人数",ListVisiteTimes.toArray());
                    JSONObject json3=new JSONObject();
                    json3.put("访问次数",ListVisitePeople.toArray());
                    JSONObject json4=new JSONObject();
                    json4.put("打开次数",listOpenCount.toArray());
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    jsonArray1.add(json4);
                    JSON.put("series",jsonArray1);
                }else{
                    String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    JSON.put("xAxis",array);
                    JSONArray jsonArray1=new JSONArray();
                    JSONObject json1=new JSONObject();
                    String[] arrayhour= new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
                    json1.put("新用户数",arrayhour);
                    JSONObject json2=new JSONObject();
                    json2.put("访问人数",arrayhour);
                    JSONObject json3=new JSONObject();
                    json3.put("访问次数",arrayhour);
                    JSONObject json4=new JSONObject();
                    json4.put("打开次数",arrayhour);
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    jsonArray1.add(json4);
                    JSON.put("series",jsonArray1);
                }
            }else{
                if (listDay!=null&&listDay.size()>0){
                    //String[] array= new String[]{"00:00 - 00:59", "01:00 - 01:59", "02:00 - 02:59", "03:00 - 03:59", "04:00 - 04:59", "05:00 - 05:59", "06:00 - 06:59", "07:00 - 07:59", "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59", "13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 17:59", "18:00 - 18:59", "19:00 - 19:59", "20:00 - 20:59", "21:00 - 21:59", "22:00 - 22:59", "23:00 - 23:59"};
                    JSONArray jsonArray=new JSONArray(listDay);
                    List<String> ListNewUser = new ArrayList();
                    List<String> ListVisiteTimes = new ArrayList();
                    List<String> ListVisitePeople= new ArrayList();
                    List<String> listOpenCount = new ArrayList();
                    List<String> ListTime = new ArrayList();
                    if (date.equals("3")){
                        for (int i=1;i<=7;i++){
                            ListNewUser.add("0");
                            ListVisiteTimes.add("0");
                            ListVisitePeople.add("0");
                            listOpenCount.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else if (date.equals("4")){
                        for (int i=1;i<=30;i++){
                            ListNewUser.add("0");
                            ListVisiteTimes.add("0");
                            ListVisitePeople.add("0");
                            listOpenCount.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else{
                        String[] strArr = date.split("~");
                        String dateStart=strArr[0];
                        String dateEnd=strArr[1];
                        ListTime=DateUtil.getBetweenDates3(dateStart,dateEnd);
                        for (int i=0;i<ListTime.size();i++){
                            ListNewUser.add("0");
                            ListVisiteTimes.add("0");
                            ListVisitePeople.add("0");
                            listOpenCount.add("0");
                            ListTime.set(i,ListTime.get(i).replaceAll("-", "/"));
                        }
                    }

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject job = jsonArray.getJSONObject(i);  //
                        String day=job.getString("day").replaceAll("-", "/");
                        int index=getIndex(ListTime,day);
                        if (index>=0){
                            ListNewUser.set(index,job.getString("new_comer_count"));
                            ListVisiteTimes.set(index,job.getString("total_page_count"));
                            ListVisitePeople.set(index,job.getString("visitor_count"));
                            listOpenCount.set(index,job.getString("open_count"));
                        }
                    }
                    JSON.put("xAxis",ListTime.toArray());
                    JSONArray jsonArray1=new JSONArray();
                    JSONObject json1=new JSONObject();
                    json1.put("新用户数",ListNewUser.toArray());
                    JSONObject json2=new JSONObject();
                    json2.put("访问人数",ListVisiteTimes.toArray());
                    JSONObject json3=new JSONObject();
                    json3.put("访问次数",ListVisitePeople.toArray());
                    JSONObject json4=new JSONObject();
                    json4.put("打开次数",listOpenCount.toArray());
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    jsonArray1.add(json4);
                    JSON.put("series",jsonArray1);
                }else{
                    List<String> ListNewUser = new ArrayList();
                    List<String> ListVisiteTimes = new ArrayList();
                    List<String> ListVisitePeople= new ArrayList();
                    List<String> listOpenCount = new ArrayList();
                    List<String> ListTime = new ArrayList();
                    if (date.equals("3")){
                        for (int i=1;i<=7;i++){
                            ListNewUser.add("0");
                            ListVisiteTimes.add("0");
                            ListVisitePeople.add("0");
                            listOpenCount.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else if (date.equals("4")){
                        for (int i=1;i<=30;i++){
                            ListNewUser.add("0");
                            ListVisiteTimes.add("0");
                            ListVisitePeople.add("0");
                            listOpenCount.add("0");
                            ListTime.add(DateTools.addDate(-i).replaceAll("-", "/"));
                        }
                    }else{
                        String[] strArr = date.split("~");
                        String dateStart=strArr[0];
                        String dateEnd=strArr[1];
                        ListTime=DateUtil.getBetweenDates3(dateStart,dateEnd);
                        for (int i=0;i<ListTime.size();i++){
                            ListNewUser.add("0");
                            ListVisiteTimes.add("0");
                            ListVisitePeople.add("0");
                            listOpenCount.add("0");
                            ListTime.set(i,ListTime.get(i).replaceAll("-", "/"));
                        }
                    }
                    JSON.put("xAxis",ListTime);
                    JSONArray jsonArray1=new JSONArray();
                    JSONObject json1=new JSONObject();
                    json1.put("新用户数",ListNewUser.toArray());
                    JSONObject json2=new JSONObject();
                    json2.put("访问人数",ListVisiteTimes.toArray());
                    JSONObject json3=new JSONObject();
                    json3.put("访问次数",ListVisitePeople.toArray());
                    JSONObject json4=new JSONObject();
                    json4.put("打开次数",listOpenCount.toArray());
                    jsonArray1.add(json1);
                    jsonArray1.add(json2);
                    jsonArray1.add(json3);
                    jsonArray1.add(json4);
                    JSON.put("series",jsonArray1);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            JSON.put("500","异常");
        }
        return  JSON;
    }
     /*@ResponseBody
    @RequestMapping(value = "/newUser-nextExistCount", method = RequestMethod.GET)
    public List<AldstatAdAnomalyIpAnalyze> newUserNextExistCount(@IsNull@NotBlank String app_key, @IsNull@NotBlank String date){
        return aldstatAdService.newUserNextExistCountService(app_key,date);
    }
    @ResponseBody
    @RequestMapping(value = "/newAuthUser-nextExistCount", method = RequestMethod.GET)
    public List<AldstatAdAnomalyIpAnalyze> newAuthUserNextExistCount(@IsNull@NotBlank String app_key, @IsNull@NotBlank String date){
        return aldstatAdService.newAuthUserNextExistCountCountService(app_key,date);
    } */
  /*  @ResponseBody
    @RequestMapping(value = "/cheatDataCount", method = RequestMethod.GET)
    public List<AldstatAdAnomalyIpAnalyze> cheatDataCount(@IsNull@NotBlank String app_key, @IsNull@NotBlank String date){
        return aldstatAdService.cheatDataCountService(app_key,date);
    }*/

   /* @ResponseBody
    @RequestMapping(value = "/cheatDataList", method = RequestMethod.GET)
    public List<AldstatAdAnomalyIpAnalyze> cheatDataList(@IsNull@NotBlank String app_key, @IsNull@NotBlank String date,@IsNull@NotBlank String modules,@IsNull@NotBlank String currentPage,@IsNull@NotBlank String total,String prop,String order){
        return aldstatAdService.cheatDataListService(app_key,date,modules,currentPage,total,prop,order);
    }*/
   public static int getIndex(List list, String value) {

       if (list.contains(value)) {
           int a = list.indexOf(value);

           return a;//如果未找到返回-1
       }else{
        return -1;
    }
   }
}
