package com.aldwx.bigdata.modules.game.trend.vo;

import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.common.util.StringUtil;

import java.math.BigDecimal;
import java.util.*;

public class MapResult {
    //对比列表
    public static List<Map<String,String>> GetTableMap(List list, TrendQueryVo vo, String a){
        List<Map<String,String>> listRes =null;
        if (vo.getDataType().equals("1")) {
            listRes = MapResult.getEmptyHour(vo,a);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map mapresult = (Map) list.get(i);
                    Map map = new LinkedHashMap();
                    map.put("hour", DateUtil.getHourRange(mapresult.get("hour").toString()));
                    if (a.equals("1")) {
                        if (vo.isCompare()){
                            map.put("time", vo.getDateStart() + " " + "~" + " " + vo.getDateEnd());
                        }else {
                            map.put("time", "-");
                        }
                    } else if (a.equals("2")) {
                        if (vo.isCompare()){
                            map.put("time", vo.getDate2Start() + " " + "~" + " " + vo.getDate2End());
                        }else {
                            map.put("time", "-");
                        }
                    }
                    map.put("new_count", StringUtil.formatThousand((BigDecimal) mapresult.get("new_count")));
                    map.put("visit_count", StringUtil.formatThousand((BigDecimal) mapresult.get("visit_count")));
                    map.put("open_count", StringUtil.formatThousand((BigDecimal) mapresult.get("open_count")));
                    map.put("secondary_avg_stay_time", StringUtil.formatTime((Double.parseDouble(mapresult.get("secondary_avg_stay_time").toString())) ));
                    listRes.set(Integer.parseInt(mapresult.get("hour").toString()), map);
                }
            }
        }
        if (vo.getDataType().equals("2")) {
            listRes = MapResult.getEmptyDay(vo, a);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map mapresult = (Map) list.get(i);
                    Map map = new LinkedHashMap();
                    map.put("hour", mapresult.get("day").toString());
                    if (a.equals("1")) {
                        if (vo.isCompare()){
                            map.put("time", vo.getDateStart() + " " + "~" + " " + vo.getDateEnd());
                        }else {
                            map.put("time", "-");
                        }
                    } else if (a.equals("2")) {
                        if (vo.isCompare()){
                            map.put("time", vo.getDate2Start() + " " + "~" + " " + vo.getDate2End());
                        }else {
                            map.put("time", "-");
                        }
                    }
                    map.put("new_count", StringUtil.formatThousand((BigDecimal) mapresult.get("new_count")));
                    map.put("visit_count", StringUtil.formatThousand((BigDecimal) mapresult.get("visit_count")));
                    map.put("open_count", StringUtil.formatThousand((BigDecimal) mapresult.get("open_count")));
                    map.put("secondary_avg_stay_time", StringUtil.formatTime((Double.parseDouble(mapresult.get("secondary_avg_stay_time").toString())) ));
                    //遍历
                    for (int z = 0; z < listRes.size(); z++) {
                        if (listRes.get(z).get("hour").equals(map.get("hour"))) {
                            listRes.set(z, map);
                        }
                    }
                }
            }
        }
        if (vo.getDataType().equals("3")){
            listRes= MapResult.getEmptyWeek(vo,a);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map mapresult = (Map) list.get(i);
                    Map map = new LinkedHashMap();
                    if (a.equals("1")){
                        map.put("hour", vo.getTransDate().get(i));
                    }else if (a.equals("2")){
                        map.put("hour", vo.getTransDate2().get(i));
                    }
                    if (a.equals("1")) {
                        if (vo.isCompare()){
                            map.put("time", vo.getDateStart() + " " + "~" + " " + vo.getDateEnd());
                        }else {
                            map.put("time", "-");
                        }
                    } else if (a.equals("2")) {
                        if (vo.isCompare()){
                            map.put("time", vo.getDate2Start() + " " + "~" + " " + vo.getDate2End());
                        }else {
                            map.put("time", "-");
                        }
                    }
                    map.put("new_count", StringUtil.formatThousand((BigDecimal) mapresult.get("new_count")));
                    map.put("visit_count", StringUtil.formatThousand((BigDecimal) mapresult.get("visit_count")));
                    map.put("open_count", StringUtil.formatThousand((BigDecimal) mapresult.get("open_count")));
                    map.put("secondary_avg_stay_time", StringUtil.formatTime((Double.parseDouble(mapresult.get("secondary_avg_stay_time").toString())) ));
                    for (int z = 0; z < listRes.size(); z++) {
                        if (listRes.get(z).get("hour").equals(map.get("hour"))) {
                            listRes.set(z, map);
                        }
                    }
                }
            }
        }
        if (vo.getDataType().equals("4")){
            listRes= MapResult.getEmptyMonth(vo,a);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map mapresult = (Map) list.get(i);
                    Map map = new LinkedHashMap();
                    if (a.equals("1")){
                        map.put("hour", vo.getTransDate().get(i));
                    }else if (a.equals("2")){
                        map.put("hour", vo.getTransDate2().get(i));
                    }
                    if (a.equals("1")) {
                        if (vo.isCompare()){
                            map.put("time", vo.getDateStart() + " " + "~" + " " + vo.getDateEnd());
                        }else {
                            map.put("time", "-");
                        }
                    } else if (a.equals("2")) {
                        if (vo.isCompare()){
                            map.put("time", vo.getDate2Start() + " " + "~" + " " + vo.getDate2End());
                        }else {
                            map.put("time", "-");
                        }
                    }
                    map.put("new_count", StringUtil.formatThousand((BigDecimal) mapresult.get("new_count")));
                    map.put("visit_count", StringUtil.formatThousand((BigDecimal) mapresult.get("visit_count")));
                    map.put("open_count", StringUtil.formatThousand((BigDecimal) mapresult.get("open_count")));
                    map.put("secondary_avg_stay_time", StringUtil.formatTime((Double.parseDouble(mapresult.get("secondary_avg_stay_time").toString())) ));
                    for (int z = 0; z < listRes.size(); z++) {
                        if (listRes.get(z).get("hour").equals(map.get("hour"))) {
                            listRes.set(z, map);
                        }
                    }
                }
            }
        }
        return listRes;
    }
    public static Map GetChartMap(List list, TrendQueryVo vo, String a){
        List time1= new ArrayList();
        Map mapresult =new HashMap();
        List<Map<String,String>> listRes1 =new ArrayList<>();
        if (vo.getDataType().equals("1")){
            for (int s=0;s<24;s++){
                time1.add(DateUtil.getHourRange(s+""));
            }
        }
        if (vo.getDataType().equals("2")){
            if (a.equals("2")){
                time1 = DateUtil.getBetweenDates3(vo.getDate2Start(),vo.getDate2End());
            }else if(a.equals("1")){
                time1 = DateUtil.getBetweenDates3(vo.getDateStart(),vo.getDateEnd());
            }
        }
        if (vo.getDataType().equals("3")){
            List weekList= null;
            if (a.equals("2")){
                weekList = DateUtil.getBetweenDates3(vo.getDate2Start(),vo.getDate2End());
            }else if (a.equals("1")){
                weekList = DateUtil.getBetweenDates3(vo.getDateStart(),vo.getDateEnd());
            }
            for (int i=0;i<weekList.size();i++){
                try{
                    String time= DateUtil.getWeekNum(weekList.get(i).toString());
                    if (!time1.contains(time)){
                        time1.add(time);
                    }
                }catch  (Exception E){
                    E.printStackTrace();
                }
            }
        }
        if (vo.getDataType().equals("4")){
            List monthList= null;
            if (a.equals("2")){
                monthList = DateUtil.getBetweenDates3(vo.getDate2Start(),vo.getDate2End());
            }else if (a.equals("1")){
                monthList = DateUtil.getBetweenDates3(vo.getDateStart(),vo.getDateEnd());
            }
            for (int i=0;i<monthList.size();i++){
                try{
                    String time=monthList.get(i).toString().substring(0,7);
                    if (!time1.contains(time)){
                        time1.add(time);
                    }
                }catch  (Exception E){
                    E.printStackTrace();
                }
            }
        }
        List tema= MapResult.getMapEmpty(vo,a);
        List temb= MapResult.getMapEmpty(vo,a);
        List temd= MapResult.getMapEmpty(vo,a);
        List teme= MapResult.getMapEmpty(vo,a);
        Map mapa=new HashMap();
        Map mapb=new HashMap();
        Map mapd=new HashMap();
        Map mape=new HashMap();
        if (list!=null&&list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = (Map) list.get(i);
                for (String key : map.keySet()) {
                    if (key.equals("new_count")) {
                        if (vo.getDataType().equals("1")) {
                            tema.set(Integer.parseInt(map.get("hour").toString()), map.get("new_count"));
                            mapa.put("name", "新用户数");
                            mapa.put("data", tema);
                        }
                        if (vo.getDataType().equals("2")) {
                            if (time1.contains(map.get("day").toString())) {
                                int m = time1.indexOf(map.get("day").toString());
                                if (m >= 0) {
                                    tema.set(m, map.get("new_count"));
                                }
                            }
                            mapa.put("name", "新用户数");
                            mapa.put("data", tema);
                        }
                        if (vo.getDataType().equals("3")) {
                            if (time1.contains(map.get("week").toString())) {
                                int m = time1.indexOf(map.get("week").toString());
                                if (m >= 0) {
                                    tema.set(m, map.get("new_count"));
                                }
                            }
                            mapa.put("name", "新用户数");
                            mapa.put("data", tema);
                        }
                        if (vo.getDataType().equals("4")) {
                            if (time1.contains(map.get("mon").toString())) {
                                int m = time1.indexOf(map.get("mon").toString());
                                if (m >= 0) {
                                    tema.set(m, map.get("new_count"));
                                }
                            }
                            mapa.put("name", "新用户数");
                            mapa.put("data", tema);
                        }
                    } else if (key.equals("visit_count")) {
                        if (vo.getDataType().equals("1")) {
                            temb.set(Integer.parseInt(map.get("hour").toString()), map.get("visit_count"));
                            mapb.put("name", "访问人数");
                            mapb.put("data", temb);
                        }
                        if (vo.getDataType().equals("2")) {
                            if (time1.contains(map.get("day").toString())) {
                                int m = time1.indexOf(map.get("day").toString());
                                if (m >= 0) {
                                    temb.set(m, map.get("visit_count"));
                                }
                            }
                            mapb.put("name", "访问人数");
                            mapb.put("data", temb);
                        }
                        if (vo.getDataType().equals("3")) {
                            if (time1.contains(map.get("week").toString())) {
                                int m = time1.indexOf(map.get("week").toString());
                                if (m >= 0) {
                                    temb.set(m, map.get("visit_count"));
                                }
                            }
                            mapb.put("name", "访问人数");
                            mapb.put("data", temb);
                        }
                        if (vo.getDataType().equals("4")) {
                            if (time1.contains(map.get("mon").toString())) {
                                int m = time1.indexOf(map.get("mon").toString());
                                if (m >= 0) {
                                    temb.set(m, map.get("visit_count"));
                                }
                            }
                            mapb.put("name", "访问人数");
                            mapb.put("data", temb);
                        }
                    } else if (key.equals("open_count")) {

                        if (vo.getDataType().equals("1")) {
                            temd.set(Integer.parseInt(map.get("hour").toString()), map.get("open_count"));
                            mapd.put("name", "打开次数");
                            mapd.put("data", temd);
                        }
                        if (vo.getDataType().equals("2")) {
                            if (time1.contains(map.get("day").toString())) {
                                int m = time1.indexOf(map.get("day").toString());
                                if (m >= 0) {
                                    temd.set(m, map.get("open_count"));
                                }
                            }
                            mapd.put("name", "打开次数");
                            mapd.put("data", temd);
                        }
                        if (vo.getDataType().equals("3")) {
                            if (time1.contains(map.get("week").toString())) {
                                int m = time1.indexOf(map.get("week").toString());
                                if (m >= 0) {
                                    temd.set(m, map.get("open_count"));
                                }
                            }
                            mapd.put("name", "打开次数");
                            mapd.put("data", temd);
                        }
                        if (vo.getDataType().equals("4")) {
                            if (time1.contains(map.get("mon").toString())) {
                                int m = time1.indexOf(map.get("mon").toString());
                                if (m >= 0) {
                                    temd.set(m, map.get("open_count"));
                                }
                            }
                            mapd.put("name", "打开次数");
                            mapd.put("data", temd);
                        }
                    } else if (key.equals("secondary_avg_stay_time")) {

                        if (vo.getDataType().equals("1")) {
                            teme.set(Integer.parseInt(map.get("hour").toString()), StringUtil.formatPercent3(Double.parseDouble(map.get("secondary_avg_stay_time").toString())));
                            mape.put("name", "次均停留时长");
                            mape.put("data", teme);
                        }
                        if (vo.getDataType().equals("2")) {
                            if (time1.contains(map.get("day").toString())) {
                                int m = time1.indexOf(map.get("day").toString());
                                if (m >= 0) {
                                    teme.set(m, StringUtil.formatPercent3(Double.parseDouble(map.get("secondary_avg_stay_time").toString())));
                                }
                            }
                            mape.put("name", "次均停留时长");
                            mape.put("data", teme);
                        }
                        if (vo.getDataType().equals("3")) {
                            if (time1.contains(map.get("week").toString())) {
                                int m = time1.indexOf(map.get("week").toString());
                                if (m >= 0) {
                                    teme.set(m, StringUtil.formatPercent3(Double.parseDouble(map.get("secondary_avg_stay_time").toString())));
                                }
                            }
                            mape.put("name", "次均停留时长");
                            mape.put("data", teme);
                        }
                        if (vo.getDataType().equals("4")) {
                            if (time1.contains(map.get("mon").toString())) {
                                int m = time1.indexOf(map.get("mon").toString());
                                if (m >= 0) {
                                    teme.set(m, StringUtil.formatPercent3(Double.parseDouble(map.get("secondary_avg_stay_time").toString())));
                                }
                            }
                            mape.put("name", "次均停留时长");
                            mape.put("data", teme);
                        }
                    }
                }
            }
            listRes1.add(mapa);
            listRes1.add(mapb);
            listRes1.add(mapd);
            listRes1.add(mape);
        }else {
            mapa.put("name", "新用户数");
            mapa.put("data", tema);
            mapb.put("name", "访问人数");
            mapb.put("data", temb);

            mapd.put("name", "打开次数");
            mapd.put("data", temd);
            mape.put("name", "次均停留时长");
            mape.put("data", teme);

            listRes1.add(mapa);
            listRes1.add(mapb);
            listRes1.add(mapd);
            listRes1.add(mape);

        }
        mapresult.put("series",listRes1);
        if (vo.getDataType().equals("3")||vo.getDataType().equals("4")){
            if (a.equals("1")){
                mapresult.put("xAxis",vo.getTransDate());
            }else if (a.equals("2")){
                mapresult.put("xAxis",vo.getTransDate2());
            }
        } else {
            mapresult.put("xAxis",time1);
        }
        return  mapresult;
    }
    //表格 小时初始化
    public static List<Map<String,String>> getEmptyHour(TrendQueryVo vo, String b){
        List<Map<String,String>> a=new ArrayList<>();
        for (int i=0;i<24;i++){
            Map map=new LinkedHashMap();
            map.put("hour", DateUtil.getHourRange(i+""));
            if (b.equals("1")) {
                if (vo.isCompare()){
                    map.put("time", vo.getDateStart() + " " + "~" + " " + vo.getDateEnd());
                }else {
                    map.put("time", "-");
                }
            } else if (b.equals("2")) {
                if (vo.isCompare()){
                    map.put("time", vo.getDate2Start() + " " + "~" + " " + vo.getDate2End());
                }else {
                    map.put("time", "-");
                }
            }
            map.put("new_count", "0");
            map.put("visit_count", "0");
            map.put("open_count", "0");
            map.put("secondary_avg_stay_time", "00:00:00");
            a.add(map);
        }
        return a;
    }
    //表格 天初始化
    public static List<Map<String,String>> getEmptyDay(TrendQueryVo vo, String b){

        List<Map<String,String>> a=new ArrayList<>();
        List<String> ListTime=null;
        if (b.equals("2")){
            ListTime = DateUtil.getBetweenDates3(vo.getDate2Start(),vo.getDate2End());
        }else if (b.equals("1")){
            ListTime = DateUtil.getBetweenDates3(vo.getDateStart(),vo.getDateEnd());
        }
        for (int i=0;i<ListTime.size();i++){
            Map map=new LinkedHashMap();
            map.put("hour", ListTime.get(i));
            if(b.equals("1")){
                if (vo.isCompare()){
                    map.put("time", vo.getDateStart() + " " + "~" + " " + vo.getDateEnd());
                }else {
                    map.put("time", "-");
                }
            }else if (b.equals("2")){
                if (vo.isCompare()){
                    map.put("time", vo.getDate2Start() + " " + "~" + " " + vo.getDate2End());
                }else {
                    map.put("time", "-");
                }
            }
            map.put("new_count", "0");
            map.put("visit_count", "0");
            map.put("open_count", "0");
            map.put("secondary_avg_stay_time", "00:00:00");

            a.add(map);
        }
        return a;
    }
    //表格 周初始化
    public static List<Map<String,String>> getEmptyWeek(TrendQueryVo vo, String b){
        List<Map<String,String>> a=new ArrayList<>();
        List<String> ListTime=null;
        if (b.equals("2")){
            ListTime = DateUtil.getBetweenDates3(vo.getDate2Start(),vo.getDate2End());
        }else if (b.equals("1")){
            ListTime = DateUtil.getBetweenDates3(vo.getDateStart(),vo.getDateEnd());
        }
        try{
            List<String> listtmp=new ArrayList<>();
            for (int i=0;i<ListTime.size();i++){
                String time= DateUtil.getWeekNum(ListTime.get(i));
                if (!listtmp.contains(time)){
                    listtmp.add(time);
                }
            }

            for (int i=0;i<listtmp.size();i++) {
                Map map=new LinkedHashMap();
                String mon = listtmp.get(i);
                if (b.equals("1")){
                    map.put("hour", vo.getTransDate().get(i));
                }else if (b.equals("2")){
                    map.put("hour", vo.getTransDate2().get(i));
                }
                if(b.equals("1")){
                    if (vo.isCompare()){
                        map.put("time", vo.getDateStart() + " " + "~" + " " + vo.getDateEnd());
                    }else {
                        map.put("time", "-");
                    }
                }else if (b.equals("2")){
                    if (vo.isCompare()){
                        map.put("time", vo.getDate2Start() + " " + "~" + " " + vo.getDate2End());
                    }else {
                        map.put("time", "-");
                    }
                }
                map.put("new_count", "0");
                map.put("visit_count", "0");
                map.put("open_count", "0");
                map.put("secondary_avg_stay_time", "00:00:00");
                a.add(map);
            }
        }catch (Exception E){
            E.printStackTrace();
        }

        return a;
    }
    //表格 月初始化
    public static List<Map<String,String>> getEmptyMonth(TrendQueryVo vo, String b){
        List<Map<String,String>> a=new ArrayList<>();
        List<String> ListTime=null;
        if (b.equals("2")){
            ListTime = DateUtil.getBetweenDates3(vo.getDate2Start(),vo.getDate2End());
        }else if (b.equals("1")){
            ListTime = DateUtil.getBetweenDates3(vo.getDateStart(),vo.getDateEnd());
        }
        List<String> listtmp=new ArrayList<>();
        for (int i=0;i<ListTime.size();i++){
            String time=ListTime.get(i).substring(0,7);
            if (!listtmp.contains(time)){
                listtmp.add(time);
            }
        }
        for (int i=0;i<listtmp.size();i++){
            Map map=new LinkedHashMap();
            if (b.equals("1")){
                map.put("hour", vo.getTransDate().get(i));
            }else if (b.equals("2")){
                map.put("hour", vo.getTransDate2().get(i));
            }
            if(b.equals("1")){
                if (vo.isCompare()){
                    map.put("time", vo.getDateStart() + " " + "~" + " " + vo.getDateEnd());
                }else {
                    map.put("time", "-");
                }
            }else if (b.equals("2")){
                if (vo.isCompare()){
                    map.put("time", vo.getDate2Start() + " " + "~" + " " + vo.getDate2End());
                }else {
                    map.put("time", "-");
                }
            }
            map.put("new_count", "0");
            map.put("visit_count", "0");
            map.put("open_count", "0");
            map.put("secondary_avg_stay_time", "00:00:00");
            a.add(map);
        }
        return a;

    }
    //折线图 初始化
    public static List getMapEmpty(TrendQueryVo vo, String a){
        List time1=new ArrayList();
        List temp=new ArrayList();
        if (vo.getDataType().equals("1")){
            for (int s=0;s<24;s++){
                time1.add(0);
            }
        }
        if (vo.getDataType().equals("2")){
            if (a.equals("1")){
                temp = DateUtil.getBetweenDates3(vo.getDateStart(),vo.getDateEnd());
            }else if(a.equals("2")){
                temp = DateUtil.getBetweenDates3(vo.getDate2Start(),vo.getDate2End());
            }
            for (int s=0;s<temp.size();s++){
                time1.add(0);
            }

        }
        if (vo.getDataType().equals("3")){
            if (a.equals("2")){
                temp = DateUtil.getBetweenDates3(vo.getDate2Start(),vo.getDate2End());
            }else if (a.equals("1")){
                temp = DateUtil.getBetweenDates3(vo.getDateStart(),vo.getDateEnd());
            }
            List temp2=new ArrayList();
            try{
                for (int i=0;i<temp.size();i++){
                    String time= DateUtil.getWeekNum(temp.get(i).toString());
                    if (!temp2.contains(time)) {
                        temp2.add(time);
                    }
                }
                for (int i=0;i<temp2.size();i++){
                    time1.add(0);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (vo.getDataType().equals("4")){
            if (a.equals("2")){
                temp = DateUtil.getBetweenDates3(vo.getDate2Start(),vo.getDate2End());
            }else if (a.equals("1")){
                temp = DateUtil.getBetweenDates3(vo.getDateStart(),vo.getDateEnd());
            }
            List temp2=new ArrayList();
            for (int i=0;i<temp.size();i++){
                String time=temp.get(i).toString().substring(0,7);
                if (!temp2.contains(time)) {
                    temp2.add(time);
                }
            }
            for (int i=0;i<temp2.size();i++){
                time1.add(0);
            }
        }
        return  time1;
    }
    // 折线图 天初始化
    //折线图 周初始化
    //折线图 月初始化




}
