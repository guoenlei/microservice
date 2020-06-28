package com.aldwx.app.common.util;

import com.aldwx.app.modules.general.vo.GeneralVo;

import java.util.ArrayList;
import java.util.List;

public class VoUtil {
    public static CurrencyVo getCurrencyVo(String app_key, String date){
        CurrencyVo vo=new CurrencyVo();
        try{
            vo.setAppKey(app_key);
          /*  vo.setType(app_type);
            vo.setDataType(data_type);*/
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
