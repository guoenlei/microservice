//package com.aldwx.bigdata.modules.trend.service.impl;
//
//import com.aldwx.bigdata.common.util.DateUtil;
//import com.aldwx.bigdata.modules.trend.dao.master.Aldstat30daysTrendAnalysisDAO;
//import com.aldwx.bigdata.modules.trend.dao.master.Aldstat7daysTrendAnalysisDAO;
//import com.aldwx.bigdata.modules.trend.dao.master.AldstatHourlyTrendAnalysisDAO;
//import com.aldwx.bigdata.modules.trend.dao.master.AldstatTrendAnalysisDAO;
//import com.aldwx.bigdata.modules.trend.entity.Aldstat30daysTrendAnalysis;
//import com.aldwx.bigdata.modules.trend.entity.Aldstat7daysTrendAnalysis;
//import com.aldwx.bigdata.modules.trend.entity.AldstatHourlyTrendAnalysis;
//import com.aldwx.bigdata.modules.trend.entity.AldstatTrendAnalysis;
//import com.aldwx.bigdata.modules.trend.service.AldstatTrendAnalysisService;
//import com.aldwx.bigdata.modules.trend.util.AldJsonUtil;
//import com.aldwx.bigdata.modules.trend.vo.TrendQueryVo;
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AldstatTrendAnalysisServiceImpl implements AldstatTrendAnalysisService {
//
//    @Autowired
//    private AldstatTrendAnalysisDAO aldstatTrendAnalysisDAO;
//    @Autowired
//    private AldstatHourlyTrendAnalysisDAO aldstatHourlyTrendAnalysisDAO;
//    @Autowired
//    private Aldstat30daysTrendAnalysisDAO aldstat30daysTrendAnalysisDAO;
//    @Autowired
//    private Aldstat7daysTrendAnalysisDAO aldstat7daysTrendAnalysisDAO;
//
//    public  JSONObject getAldstatTrendAnalysisTotal(JSONObject json){
//        //对比数据
//        JSONObject jsonResult =new JSONObject();
//        try{
//            //第一个时间控件
//            String date=json.getString("date");
//            //第二个时间控件
//            String date2=json.getString("date2");
//
//            String day="";
//            String ak=json.getString("app_key");
//            List<AldstatTrendAnalysis> listSecond=null;
//            TrendQueryVo vo =new TrendQueryVo();
//            if (date.equals("1")){
//                //今天的小时数据sum 小时表
//                day=DateUtil.getFetureDate(-1);
//                vo.setDay(day);
//                vo.setAk(ak);
//                List<AldstatHourlyTrendAnalysis> listFirst =this.aldstatHourlyTrendAnalysisDAO.selectTrendAnalysisHourByDayAndAk(vo);
//                if (listFirst!=null&&listFirst.size()>0){
//                    AldstatHourlyTrendAnalysis ald=listFirst.get(0);
//                    JSONObject jsonObject= AldJsonUtil.getAldJson(ald,"1");
//                    if (jsonObject!=null&&jsonObject.size()>0){
//                        jsonResult.put("data1",jsonObject);
//                    }else {
//                        jsonResult.put("data1","null");
//                    }
//                }
//            }else if (date.equals("2")){
//                //昨天的数据 天表
//                day=DateUtil.getFetureDate(0);
//                vo.setDay(day);
//                vo.setAk(ak);
//                AldstatTrendAnalysis aldstat =this.aldstatTrendAnalysisDAO.selectTrendAnalysisByDayAndAk(vo);
//                if (aldstat!=null){
//                    JSONObject jsonObject= AldJsonUtil.getAldJson(aldstat,"2");
//                    if (jsonObject!=null&&jsonObject.size()>0){
//                        jsonResult.put("data1",jsonObject);
//                    }else {
//                        jsonResult.put("data1","null");
//                    }
//                }
//            }else if (date.equals("3")){
//                //7天的数据 7天表
//                day=DateUtil.getFetureDate(0);
//                vo.setDay(day);
//                vo.setAk(ak);
//                Aldstat7daysTrendAnalysis aldstat=this.aldstat7daysTrendAnalysisDAO.selectTrendAnalysis7daysDayAndAk(vo);
//                if (aldstat!=null){
//                    JSONObject jsonObject= AldJsonUtil.getAldJson(aldstat,"3");
//                    if (jsonObject!=null&&jsonObject.size()>0){
//                        jsonResult.put("data1",jsonObject);
//                    }else {
//                        jsonResult.put("data1","null");
//                    }
//                }
//            }else if (date.equals("4")){
//                //30天的数据 30天表
//                day=DateUtil.getFetureDate(0);
//                vo.setDay(day);
//                vo.setAk(ak);
//                Aldstat30daysTrendAnalysis aldstat=this.aldstat30daysTrendAnalysisDAO.selectTrendAnalysis30daysByDayAndAk(vo);
//                if (aldstat!=null){
//                    JSONObject jsonObject= AldJsonUtil.getAldJson(aldstat,"4");
//                    if (jsonObject!=null&&jsonObject.size()>0){
//                        jsonResult.put("data1",jsonObject);
//                    }else {
//                        jsonResult.put("data1","null");
//                    }
//                }
//            }else {
//                //时间区间
//                String[] strArr = date.split("~");
//                String dayStart=strArr[0];
//                String dayEnd=strArr[1];
//                vo.setAk(ak);
//                vo.setDayStart(dayStart);
//                vo.setDayEnd(dayEnd);
//                List<AldstatTrendAnalysis> listFirst=this.aldstatTrendAnalysisDAO.selectTrendAnalysisByRangeAndAk(vo);
//                if (listFirst!=null&&listFirst.size()>0){
//                    AldstatTrendAnalysis ald=listFirst.get(0);
//                    JSONObject jsonObject= AldJsonUtil.getAldJson(ald,"2");
//                    if (jsonObject!=null&&jsonObject.size()>0){
//                        jsonResult.put("data1",jsonObject);
//                    }else {
//                        jsonResult.put("data1","null");
//                    }
//                }
//            }
//            if (date2!=null){
//                String[] strArr = date2.split("~");
//                String dayStart=strArr[0];
//                String dayEnd=strArr[1];
//                vo.setAk(ak);
//                vo.setDayStart(dayStart);
//                vo.setDayEnd(dayEnd);
//                listSecond=this.aldstatTrendAnalysisDAO.selectTrendAnalysisByRangeAndAk(vo);
//                if (listSecond!=null&&listSecond.size()>0){
//                    AldstatTrendAnalysis ald=listSecond.get(0);
//                    JSONObject jsonObject= AldJsonUtil.getAldJson(ald,"2");
//                    if (jsonObject!=null&&jsonObject.size()>0){
//                        jsonResult.put("data2",jsonObject);
//                    }else {
//                        jsonResult.put("data2","null");
//                    }
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//        return  jsonResult;
//    }
//
//}
