package com.aldwx.app.modules.retain.service.impl;


import com.aldwx.app.common.util.DateUtil;
import com.aldwx.app.common.util.ResultUtil;
import com.aldwx.app.common.util.StringUtil;
import com.aldwx.app.modules.retain.dao.game.*;
import com.aldwx.app.modules.retain.dao.stat.*;
import com.aldwx.app.modules.retain.entity.*;
import com.aldwx.app.modules.retain.service.RetainService;
import com.aldwx.app.modules.retain.vo.RetainVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class RetainServiceImpl implements RetainService {

    @Autowired
    private RetainStayDAO retainStayDAO;
    @Autowired
    private RetainGameStayDAO retainGameStayDAO;
    @Autowired
    private RetainActivityDAO retainActivityDAO;
    @Autowired
    private RetainGameActivityDAO retainGameActivityDAO;
    @Autowired
    private RetainDepthDAO retainDepthDAO;
    @Autowired
    private RetainGameDepthDAO retainGameDepthDAO;
    @Autowired
    private RetainDurationDAO retainDurationDAO;
    @Autowired
    private RetainGameDurationDAO retainGameDurationDAO;
    @Autowired
    private RetainActivityDetailsDAO retainActivityDetailsDAO;
    @Autowired
    private RetainGameActivityDetailsDAO retainGameActivityDetailsDAO;
    //留存折线图
    public Map<String, Object> selectRetainLineChart(RetainVo vo){
        Map<String, Object> map=new HashMap<>();
        List<Map<String,String>> mapEmpty=stayLineEmpty(vo);
        try{
            List<AldStayLogs> list=null;
            if (vo.getAppType().equals("1")){
                list=this.retainStayDAO.selectStayList(vo);
            }else {
                list=this.retainGameStayDAO.selectGameStayList(vo);
            }
            //1新增 2活跃
            if (list!=null&&list.size()>0){
                    Double days1=0.0;Double days2=0.0;Double days3=0.0;Double days4=0.0;Double days5=0.0;Double days6=0.0;Double days7=0.0;Double days14=0.0;Double days30=0.0;
                    int count1=0;int count2=0;int count3=0;int count4=0;int count5=0;int count6=0;int count7=0;int count14=0;int count30=0;
                    for (int i=0;i<list.size();i++){
                        if (list.get(i).getDay_after()==1){
                            if (vo.getDataType().equals("1")) {
                                days1+=list.get(i).getNew_people_ratio();
                            }
                            if (vo.getDataType().equals("2")) {
                                days1+=list.get(i).getActive_people_ratio();
                            }
                            count1++;
                        }
                        if (list.get(i).getDay_after()==2){
                            if (vo.getDataType().equals("1")) {
                                days2+=list.get(i).getNew_people_ratio();
                            }
                            if (vo.getDataType().equals("2")) {
                                days2+=list.get(i).getActive_people_ratio();
                            }
                            count2++;
                        }
                        if (list.get(i).getDay_after()==3){
                            if (vo.getDataType().equals("1")) {
                                days3+=list.get(i).getNew_people_ratio();
                            }
                            if (vo.getDataType().equals("2")) {
                                days3+=list.get(i).getActive_people_ratio();
                            }
                            count3++;
                        }
                        if (list.get(i).getDay_after()==4){
                            if (vo.getDataType().equals("1")) {
                                days4+=list.get(i).getNew_people_ratio();
                            }
                            if (vo.getDataType().equals("2")) {
                                days4+=list.get(i).getActive_people_ratio();
                            }
                            count4++;
                        }
                        if (list.get(i).getDay_after()==5){
                            if (vo.getDataType().equals("1")) {
                                days5+=list.get(i).getNew_people_ratio();
                            }
                            if (vo.getDataType().equals("2")) {
                                days5+=list.get(i).getActive_people_ratio();
                            }
                            count5++;
                        }
                        if (list.get(i).getDay_after()==6){
                            if (vo.getDataType().equals("1")) {
                                days6+=list.get(i).getNew_people_ratio();
                            }
                            if (vo.getDataType().equals("2")) {
                                days6+=list.get(i).getActive_people_ratio();
                            }
                            count6++;
                        }
                        if (list.get(i).getDay_after()==7){
                            if (vo.getDataType().equals("1")) {
                                days7+=list.get(i).getNew_people_ratio();
                            }
                            if (vo.getDataType().equals("2")) {
                                days7+=list.get(i).getActive_people_ratio();
                            }
                            count7++;
                        }
                        if (list.get(i).getDay_after()==14){
                            if (vo.getDataType().equals("1")) {
                                days14+=list.get(i).getNew_people_ratio();
                            }
                            if (vo.getDataType().equals("2")) {
                                days14+=list.get(i).getActive_people_ratio();
                            }
                            count14++;
                        }
                        if (list.get(i).getDay_after()==30){
                            if (vo.getDataType().equals("1")) {
                                days30+=list.get(i).getNew_people_ratio();
                            }
                            if (vo.getDataType().equals("2")) {
                                days30+=list.get(i).getActive_people_ratio();
                            }
                            count30++;
                        }
                    }
                    for (int z=0;z<mapEmpty.size();z++){
                        Map mapTemp=mapEmpty.get(z);
                        if (mapEmpty.get(z).get("year").equals("1")){
                            try{
                                mapTemp.put("value",StringUtil.formatPercentNum(days1*100/count1));
                            }catch (Exception e){
                                mapTemp.put("value","0");
                            }
                        }
                        if (mapEmpty.get(z).get("year").equals("2")){
                            try{
                                mapTemp.put("value",StringUtil.formatPercentNum(days2*100/count2));
                            }catch (Exception e){
                                mapTemp.put("value","0");
                            }
                        }
                        if (mapEmpty.get(z).get("year").equals("3")){
                            try{
                                mapTemp.put("value",StringUtil.formatPercentNum(days3*100/count3));
                            }catch (Exception e){
                                mapTemp.put("value","0");
                            }
                        }
                        if (mapEmpty.get(z).get("year").equals("4")){
                            try{
                                mapTemp.put("value",StringUtil.formatPercentNum(days4*100/count4));
                            }catch (Exception e){
                                mapTemp.put("value","0");
                            }
                        }
                        if (mapEmpty.get(z).get("year").equals("5")){
                            try{
                                mapTemp.put("value",StringUtil.formatPercentNum(days5*100/count5));
                            }catch (Exception e){
                                mapTemp.put("value","0");
                            }
                        }
                        if (mapEmpty.get(z).get("year").equals("6")){
                            try{
                                mapTemp.put("value",StringUtil.formatPercentNum(days6*100/count6));
                            }catch (Exception e){
                                mapTemp.put("value","0");
                            }
                        }
                        if (mapEmpty.get(z).get("year").equals("7")){
                            try{
                                mapTemp.put("value",StringUtil.formatPercentNum(days7*100/count7));
                            }catch (Exception e){
                                mapTemp.put("value","0");
                            }
                        }
                        if (mapEmpty.get(z).get("year").equals("14")){
                            try{
                                mapTemp.put("value",StringUtil.formatPercentNum(days14*100/count14));
                            }catch (Exception e){
                                mapTemp.put("value","0");
                            }
                        }
                        if (mapEmpty.get(z).get("year").equals("30")){
                            try{
                                mapTemp.put("value",StringUtil.formatPercentNum(days30*100/count30));
                            }catch (Exception e){
                                mapTemp.put("value","0");
                            }
                        }
                        mapEmpty.set(z,mapTemp);
                    }

            }
            map.put("data",mapEmpty);
            map=ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //留存列表详情
    public Map<String, Object> selectRetainDetail(RetainVo vo){
        Map<String, Object> map=new HashMap<>();
        List<Map<String,Object>> listEmpty=stayDetailsEmpty(vo);
        try{
            List<AldStayLogs> list=null;
            if (vo.getAppType().equals("1")){
                list=this.retainStayDAO.selectStayDetails(vo);
            }else {
                list=this.retainGameStayDAO.selectGameStayDetails(vo);
            }
            if (list!=null&&list.size()>0){
                for (int z=0;z<listEmpty.size();z++){
                    Map<String, Object> mapTemp=listEmpty.get(z);
                    List<Object> listDay2 = new ArrayList<>();

                    for (String s : mapTemp.keySet()) {
                        if (StringUtils.equals(s, "days")) {
                            Object o = mapTemp.get(s);
//                            for (Object value : (ArrayList<Double>)o) {
                            for (Object value : (Double[]) o) {
                                listDay2.add(value);
                            }
                        }
                    }
                    List<String> listDay=(List<String>)mapTemp.get("days");
                    for (int i=0;i<list.size();i++){
                        AldStayLogs ald=list.get(i);
                        DateUtil.dateTranStr(ald.getDay());
                        if (mapTemp.get("time").toString().equals(DateUtil.dateTranStr(ald.getDay()))){
                            if (ald.getDay_after()==0){
                                if (vo.getDataType().equals("1")){
                                    mapTemp.put("count",ald.getNew_people_left());
                                }else {
                                    mapTemp.put("count",ald.getPeople_left());
                                }
                            }else {
                                int a=ald.getDay_after();
                                int s=0;
                                if (a<14){
                                    s=a-1;
                                }else if (a==14){
                                    s=7;
                                }else if (a==30){
                                    s=8;
                                }
                                if (vo.getDataType().equals("1")){
                                    listDay.set(s,StringUtil.formatPercent2(ald.getNew_people_ratio()));
                                }else if (vo.getDataType().equals("2")){
                                    listDay.set(s,StringUtil.formatPercent2(ald.getActive_people_ratio()));
                                }
                            }
                        }
                    }
                    mapTemp.put("days",listDay);
                    listEmpty.set(z,mapTemp);
                }

            }
            map.put("data",listEmpty);
            map=ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //用户活跃度折线图
    public Map<String, Object> selectActiveLineChart(RetainVo vo){
        Map<String, Object> map=new HashMap<>();
        try{
            List<AldstatUserActivity> list=null;
            if (vo.getAppType().equals("1")){
                list=this.retainActivityDAO.selectActiveList(vo);
            }else {
                list=this.retainGameActivityDAO.selectGameActiveList(vo);
            }
            List<Map<String,String>> dataList=(List<Map<String,String>>)activeLineEmpty(vo).get("data");
            if (list!=null&&list.size()>0){
                for (int i=0;i<list.size();i++){
                    AldstatUserActivity ald=list.get(i);
                   for (int z=0;z<dataList.size();z++){
                       if (DateUtil.dateTranStr(ald.getDay()).equals(dataList.get(z).get("year"))) {
                           Map mapTEPM = dataList.get(z);
                           if (mapTEPM.get("name").equals("日活")){
                               mapTEPM.put("value", ald.getDau().toString());
                           }else if (mapTEPM.get("name").equals("周活")){
                               mapTEPM.put("value", ald.getWau().toString());
                           }
                           dataList.set(z, mapTEPM);
                       }
                   }
                }
            }
            map.put("data",dataList);
            map=ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //用户活跃度详情列表
    public Map<String, Object> selectActiveDetail(RetainVo vo){
        Map<String, Object> map=new HashMap<>();
        try{
            List<AldstatUserActivity> list=null;
            if (vo.getAppType().equals("1")){
                list=this.retainActivityDAO.selectActiveList(vo);
            }else {
                list=this.retainGameActivityDAO.selectGameActiveList(vo);
            }
            List<Map<String,Object>> listEmpty=activeDetailsEmpty(vo);
            if (list!=null&&list.size()>0){
                for (int i=0;i<listEmpty.size();i++){
                    Map<String,Object> mapTemp=listEmpty.get(i);
                    String listDay=mapTemp.get("time").toString();
                    for (int s=0;s<list.size();s++){
                        AldstatUserActivity ald=list.get(s);
                       if (listDay.equals(DateUtil.dateTranStr(ald.getDay()))){
                           mapTemp.put("dau",ald.getDau().toString());
                           mapTemp.put("wau",ald.getWau().toString());
                           mapTemp.put("dau_wau_ratio",StringUtil.formatPercent2(ald.getDau_wau_ratio()));
                           mapTemp.put("mau",ald.getMau().toString());
                           mapTemp.put("dau_mau_ratio",StringUtil.formatPercent2(ald.getDau_mau_ratio()));
                           listEmpty.set(i,mapTemp);
                       }
                    }
                }
            }
            map.put("data",listEmpty);
            map=ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    //用户活跃度折线图(改版)
    public Map<String, Object> selectActiveLineChart1(RetainVo vo){
        Map<String, Object> map=new HashMap<>();
        try{
            List<AldstatUserActivityDetails> dayData=null;
            List<AldstatUserActivityDetails> weekData=null;
            if (vo.getAppType().equals("1")){
                dayData=this.retainActivityDetailsDAO.selectActiveListDay(vo);
            }else {
                dayData=this.retainGameActivityDetailsDAO.selectActiveListDay(vo);
            }
            if (vo.getDateType().equals("1")||vo.getDateType().equals("2")){
                if (vo.getAppType().equals("1")){
                    weekData=this.retainActivityDetailsDAO.selectActiveListWeek(vo);
                }else {
                    weekData=this.retainGameActivityDetailsDAO.selectActiveListWeek(vo);
                }
            }
            List<Map<String,String>> dayList=(List<Map<String,String>>)activeLineEmpty1(vo).get("dayData");
            List<Map<String,String>> weekList=(List<Map<String,String>>)activeLineEmpty1(vo).get("weekData");
            if (dayData!=null&&dayData.size()>0){
                for (int i=0;i<dayData.size();i++){
                    AldstatUserActivityDetails ald=dayData.get(i);
                    for (int z=0;z<dayList.size();z++){
                        if (DateUtil.dateTranStr(ald.getDay()).equals(dayList.get(z).get("year"))) {
                            Map mapTEPM = dayList.get(z);
                            mapTEPM.put("value", ald.getUcount().toString());
                            dayList.set(z, mapTEPM);
                        }
                    }
                }
            }
            if (weekData!=null&&weekData.size()>0){
                for (int i=0;i<weekData.size();i++){
                    AldstatUserActivityDetails ald=dayData.get(i);
                    for (int z=0;z<weekList.size();z++){
                        Map<String,String> mapTemp=weekList.get(z);
                        String[] str=mapTemp.get("year").split("~");
                        String timeTemp=null;
                        if (DateUtil.isSunDay(str[1].trim())){
                            timeTemp=DateUtil.getMonday(str[1].trim());
                        }
                        if (ald.getDay().equals(timeTemp)){
                            mapTemp.put("value",ald.getUcount().toString());
                        }
                        weekList.set(z,mapTemp);
                    }
                }
            }
            map.put("dayData",dayList);
            map.put("weekData",weekList);
            map=ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //用户活跃度详情列表（改版）
    public Map<String, Object> selectActiveDetail1(RetainVo vo){
        Map<String, Object> map=new HashMap<>();
        try{
            List<AldstatUserActivityDetails> dayData=null;
            List<AldstatUserActivityDetails> weekData=null;
            if (vo.getAppType().equals("1")){
                dayData=this.retainActivityDetailsDAO.selectActiveListDay(vo);
            }else {
                dayData=this.retainGameActivityDetailsDAO.selectActiveListDay(vo);
            }
            if (vo.getDateType().equals("1")||vo.getDateType().equals("2")){
                if (vo.getAppType().equals("1")){
                    weekData=this.retainActivityDetailsDAO.selectActiveListWeek(vo);
                }else {
                    weekData=this.retainGameActivityDetailsDAO.selectActiveListWeek(vo);
                }
            }
            List<Map<String,Object>> listEmpty=activeDetailsEmpty1(vo);
            List<String> listweek=vo.getListWeek();
            if (dayData!=null&&dayData.size()>0){
                for (int i=0;i<listEmpty.size();i++){
                    Map<String,Object> mapTemp=listEmpty.get(i);
                    String listDay=mapTemp.get("time").toString();
                    for (int s=0;s<dayData.size();s++){
                        AldstatUserActivityDetails ald=dayData.get(s);
                        if (listDay.equals(DateUtil.dateTranStr(ald.getDay()))){
                            mapTemp.put("dau",ald.getUcount().toString());
                            for (int m=0;m<listweek.size();m++){
                                String[] weekArry=listweek.get(m).split("~");
                                if (DateUtil.strToLong(DateUtil.dateTranStr(ald.getDay()))>=DateUtil.strToLong(weekArry[0].trim())&&DateUtil.strToLong(DateUtil.dateTranStr(ald.getDay()))<=DateUtil.strToLong(weekArry[1].trim())){
                                    String timeTemp=null;
                                    if (DateUtil.isSunDay(weekArry[1].trim())){
                                        timeTemp=DateUtil.getMonday(weekArry[1].trim());
                                        for (int w=0;w<weekData.size();w++){
                                            if (DateUtil.dateTranStr(weekData.get(w).getDay()).equals(timeTemp)){
                                                mapTemp.put("wau",weekData.get(w).getUcount().toString());
                                                Double radio=0d;
                                                if (weekData.get(w).getUcount()!=0){
                                                    radio=ald.getUcount()*1.0d/weekData.get(w).getUcount();
                                                }
                                                mapTemp.put("dau_wau_ratio",StringUtil.formatPercent2(radio));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    listEmpty.set(i,mapTemp);
                }
            }
            map.put("data",listEmpty);
            map=ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //用户忠诚度折线图
    public Map<String, Object> selectDepthLineChart(RetainVo vo){
        Map<String, Object> map=new HashMap<>();
        Map<String,List<Map<String,String>>> mapEmpty=depthLineEmpty(vo);
        List<Map<String,String>> listEmpty=mapEmpty.get("data");
        try{
            List list=null;
            if (vo.getAppType().equals("1")){
                if (vo.getDataType().equals("1")){
                    list=this.retainDepthDAO.selectTotalByDepth(vo);
                }else if (vo.getDataType().equals("2")){
                    list=this.retainDurationDAO.selectTotalByDuration(vo);
                }
            }else if (vo.getAppType().equals("2")){
                if (vo.getDataType().equals("1")){
                    list=this.retainGameDepthDAO.selectGameTotalByDepth(vo);
                }else if (vo.getDataType().equals("2")){
                    list=this.retainGameDurationDAO.selectGameTotalByDuration(vo);
                }
            }
            if (list!=null&&list.size()>0){
                for (int i=0;i<list.size();i++){
                    if (vo.getDataType().equals("1")){
                        AldstatVisitDepth ald=(AldstatVisitDepth)list.get(i);
                        for (int z=0;z<listEmpty.size();z++){
                            Map<String,String> maptemp=listEmpty.get(z);
                            if (maptemp.get("year").equals(ald.getVisit_depth().toString())&&maptemp.get("name").equals("访问人数")){
                                maptemp.put("value",ald.getVisitor_count().toString());
                            }else if (maptemp.get("year").equals(ald.getVisit_depth().toString())&&maptemp.get("name").equals("打开次数")){
                                maptemp.put("value",ald.getOpen_count().toString());
                            }
                            listEmpty.set(z,maptemp);
                        }
                    }else if (vo.getDataType().equals("2")){
                        AldstatVisitDuration ald=(AldstatVisitDuration)list.get(i);
                        for (int z=0;z<listEmpty.size();z++){
                            Map<String,String> maptemp=listEmpty.get(z);
                            if (maptemp.get("year").equals(ald.getVisit_duration().toString())&&maptemp.get("name").equals("访问人数")){
                                maptemp.put("value",ald.getVisitor_count().toString());
                            }else if (maptemp.get("year").equals(ald.getVisit_duration().toString())&&maptemp.get("name").equals("打开次数")){
                                maptemp.put("value",ald.getOpen_count().toString());
                            }
                            listEmpty.set(z,maptemp);
                        }

                    }
                }
            }
            map.put("data",listEmpty);
            map=ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //用户忠诚度列表
    public Map<String, Object> selectDepthDetail(RetainVo vo){
        Map<String, Object> map=new HashMap<>();
        List<Map<String,String>> mapEmpty=depthDetailsEmpty(vo);
        try{
            List list=null;
            List list2=null;
            if (vo.getAppType().equals("1")){
                if (vo.getDataType().equals("1")){
                    list=this.retainDepthDAO.selectTotalByDepth(vo);
                    list2=this.retainDepthDAO.selectTotalByAk(vo);
                }else if (vo.getDataType().equals("2")){
                    list=this.retainDurationDAO.selectTotalByDuration(vo);
                    list2=this.retainDurationDAO.selectTotalByAk(vo);
                }
            }else if (vo.getAppType().equals("2")){
                if (vo.getDataType().equals("1")){
                    list=this.retainGameDepthDAO.selectGameTotalByDepth(vo);
                    list2=this.retainGameDepthDAO.selectGameTotalByAk(vo);
                }else if (vo.getDataType().equals("2")){
                    list=this.retainGameDurationDAO.selectGameTotalByDuration(vo);
                    list2=this.retainGameDurationDAO.selectGameTotalByAk(vo);
                }
            }
            if (list!=null&&list.size()>0&&list2!=null&&list2.size()>0){
                for (int z=0;z<mapEmpty.size();z++){
                    Map<String,String> maptemp=mapEmpty.get(z);
                    for (int i=0;i<list.size();i++){
                        if (vo.getDataType().equals("1")){
                            AldstatVisitDepth ald=(AldstatVisitDepth)list.get(i);
                            AldstatVisitDepth ald2=(AldstatVisitDepth)list2.get(0);
                            if (ald.getVisit_depth()-1==z){
                                maptemp.put("visitor_count",ald.getVisitor_count().toString());
                                maptemp.put("visitor_ratio",StringUtil.formatPercent2(ald.getVisitor_count()*1.0d/ald2.getVisitor_count()));
                                maptemp.put("open_count",ald.getOpen_count().toString());
                                maptemp.put("open_ratio",StringUtil.formatPercent2(ald.getOpen_count()*1.0d/ald2.getOpen_count()));
                            }

                        }else if (vo.getDataType().equals("2")){
                            AldstatVisitDuration ald=(AldstatVisitDuration)list.get(i);
                            AldstatVisitDuration ald2=(AldstatVisitDuration)list2.get(0);
                            if (ald.getVisit_duration()-1==z){
                                maptemp.put("visitor_count",ald.getVisitor_count().toString());
                                maptemp.put("visitor_ratio",StringUtil.formatPercent2(ald.getVisitor_count()*1.0d/ald2.getVisitor_count()));
                                maptemp.put("open_count",ald.getOpen_count().toString());
                                maptemp.put("open_ratio",StringUtil.formatPercent2(ald.getOpen_count()*1.0d/ald2.getOpen_count()));
                            }
                        }
                        mapEmpty.set(z,maptemp);
                    }
                }
            }
            map.put("data",mapEmpty);
            map=ResultUtil.success(map);

        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    //忠诚度折线图空格子
    public static Map<String,List<Map<String,String>>> depthLineEmpty(RetainVo vo){
        Map<String,List<Map<String,String>>> maps=new HashMap<>();
        List<Map<String,String>> list= new ArrayList<>();
        if(vo.getDataType().equals("1")){
            for (int i=1;i<7;i++){
                Map<String,String> map=new HashMap<>();
                map.put("name","访问人数");
                map.put("value","0");
                map.put("year",i+"");
                list.add(map);

            }
            for (int i=1;i<7;i++){
                Map<String,String> map1=new HashMap<>();
                map1.put("name","打开次数");
                map1.put("value","0");
                map1.put("year",i+"");
                list.add(map1);
            }
        }else if (vo.getDataType().equals("2")){
            for (int i=1;i<9;i++){
                Map<String,String> map=new HashMap<>();
                map.put("name","访问人数");
                map.put("value","0");
                map.put("year",i+"");
                list.add(map);
            }
            for (int i=1;i<9;i++){
                Map<String,String> map1=new HashMap<>();
                map1.put("name","打开次数");
                map1.put("value","0");
                map1.put("year",i+"");
                list.add(map1);
            }
        }
        maps.put("data",list);
        return maps;
    }
    //忠诚度详情空格子
    public static List<Map<String,String>> depthDetailsEmpty(RetainVo vo){
        List<Map<String,String>> list=new ArrayList<>();
        int z=0;
        if(vo.getDataType().equals("1")){
            z=6;
        }else if (vo.getDataType().equals("2")){
            z=8;
        }
        for (int i=0;i<z;i++){
            Map<String,String> map = new HashMap<>();
            map.put("time",i+1+"");
            map.put("visitor_count","0");
            map.put("visitor_ratio","0%");
            map.put("open_count","0");
            map.put("open_ratio","0%");
            list.add(map);
        }
        return list;
    }
    //活跃度折线图空格子
    public static Map<String,Object>  activeLineEmpty(RetainVo vo){
        Map<String, Object> map = new HashMap<>();
        List<String> listDay=vo.getListDate();
        List<Map<String,String>> listCount=new ArrayList<>();
        for (int i=0;i<listDay.size();i++){
            Map mapTEPM=new HashMap();
            mapTEPM.put("value","0");
            mapTEPM.put("year",listDay.get(i));
            mapTEPM.put("name","日活");
            listCount.add(mapTEPM);
        }
        for (int i=0;i<listDay.size();i++){
            Map mapTEPM1=new HashMap();
            mapTEPM1.put("value","0");
            mapTEPM1.put("year",listDay.get(i));
            mapTEPM1.put("name","周活");
            listCount.add(mapTEPM1);
        }
        map.put("data",listCount);
        return map;
    }
    //活跃度详情列表空格子
    public static List<Map<String,Object>> activeDetailsEmpty(RetainVo vo) {
        List<Map<String,Object>> listResult=new ArrayList<>();
        List<String> listDay=vo.getListDate();
        for (int i=0;i<listDay.size();i++){
            Map map=new HashMap();
            List<String> list=new ArrayList();
            map.put("time",listDay.get(i));
            map.put("dau","0");
            map.put("wau","0");
            map.put("dau_wau_ratio","0%");
            map.put("mau","0");
            map.put("dau_mau_ratio","0%");
            listResult.add(map);
        }
        return listResult;
    }
    //活跃度折线图空格子new
    public static Map<String,Object>  activeLineEmpty1(RetainVo vo){
        Map<String, Object> map = new HashMap<>();
        List<String> listDay=vo.getListDate();
        List<String> listWeek=vo.getListWeek();
        List<Map<String,String>> listDayData=new ArrayList<>();
        List<Map<String,String>> listWeekData=new ArrayList<>();
        for (int i=0;i<listDay.size();i++){
            Map mapTEPM=new HashMap();
            mapTEPM.put("value","0");
            mapTEPM.put("year",listDay.get(i));
            mapTEPM.put("name","日活");
            listDayData.add(mapTEPM);
        }
        for (int i=0;i<listWeek.size();i++){
            String week=listWeek.get(i);
            String[] weekArry=week.split("~");
            if (DateUtil.isSunDay(weekArry[1].trim())){
                Map mapTEPM1=new HashMap();
                mapTEPM1.put("value","0");
                mapTEPM1.put("year",week);
                mapTEPM1.put("name","周活");
                listWeekData.add(mapTEPM1);
            }
        }
        map.put("dayData",listDayData);
        map.put("weekData",listWeekData);
        return map;
    }
    //活跃度详情列表空格子new
    public static List<Map<String,Object>> activeDetailsEmpty1(RetainVo vo) {
        List<Map<String,Object>> listResult=new ArrayList<>();
        List<String> listDay=vo.getListDate();
        List<String> weekDay=vo.getListDate();
        for (int i=0;i<listDay.size();i++){
            Map map=new HashMap();
            List<String> list=new ArrayList();
            map.put("time",listDay.get(i));
            map.put("dau","0");
            for (int z=0;z<weekDay.size();z++){
                String week=weekDay.get(z);
                String[] weekArry=week.split("~");
                if (DateUtil.strToLong(listDay.get(i))>=DateUtil.strToLong(weekArry[0])&&DateUtil.strToLong(listDay.get(i))<=DateUtil.strToLong(weekArry[1])){
                    if (DateUtil.isSunDay(weekArry[1])){
                        map.put("wau","0");
                        map.put("dau_wau_ratio","0%");
                    }else {
                        map.put("wau","--");
                        map.put("dau_wau_ratio","--");
                    }
                }
            }
            map.put("dau_wau_ratio","0%");

            listResult.add(map);
        }
        return listResult;
    }
    //留存详情列表空格子
    public static List<Map<String,Object>>  stayDetailsEmpty(RetainVo vo){
        List<Map<String,Object>> listResult=new ArrayList<>();
        List<String> listTime=vo.getListDate();
        for (int i=0;i<listTime.size();i++){
            Map mp=new HashMap();
            mp.put("time",listTime.get(i));
            mp.put("count","0");
            List<String> listTemp=DateUtil.getBetweenDates3(listTime.get(i),vo.getDateEnd());
            List<String> listCount=new ArrayList<>();
            int countNum=0;
            if (listTemp.size()>=2&&listTemp.size()<=8){
                countNum=listTemp.size()-1;
            }else if (listTemp.size()>8&&listTemp.size()<=15){
                countNum=8;
            }else if (listTemp.size()>15){
                countNum=9;
            }
            if (countNum>0){
                for (int a=0;a<countNum;a++){
                    listCount.add("0%");
                }
                mp.put("days",listCount);
            }else {
                mp.put("days",null);
            }
            listResult.add(mp);
        }
        return listResult;
    }
    //留存折线图空格子
    public static List<Map<String,String>> stayLineEmpty(RetainVo vo){
        List<Map<String,String>> list=new ArrayList<>();
        for (int i=1;i<10;i++){
            Map map=new HashMap();
            if (i==8){
                map.put("year","14");
            }else if (i==9){
                map.put("year","30");
            }else {
                map.put("year", i + "");
            }
            if (vo.getDataType().equals("1")){
                map.put("name","新增用户留存");
            }else if (vo.getDataType().equals("2")){
                map.put("name","活跃用户留存");
            }
            map.put("value","0%");
            list.add(map);
        }
        return list;
    }
    //获取元素的下标
    public static int ifListContainValue(List<String> list,String a){
        int s=-1;
        if (list.contains(a)){
            s=list.indexOf(a);
        }
        return s;
    }

    public  static void main(String[]args){
        RetainVo vo=new RetainVo();
        String[] day = DateUtil.getNearly7Day();
        vo.setDateStart(day[0]);
        vo.setDateEnd(day[1]);
        vo.setDateType("3");
        List<String> listTime =DateUtil.getBetweenDates3(day[0],day[1]);
        vo.setListDate(listTime);
        stayDetailsEmpty(vo);
    }
}
