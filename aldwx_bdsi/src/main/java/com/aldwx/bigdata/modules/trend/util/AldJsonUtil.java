package com.aldwx.bigdata.modules.trend.util;

import com.aldwx.bigdata.modules.trend.entity.Aldstat30daysTrendAnalysis;
import com.aldwx.bigdata.modules.trend.entity.Aldstat7daysTrendAnalysis;
import com.aldwx.bigdata.modules.trend.entity.AldstatHourlyTrendAnalysis;
import com.aldwx.bigdata.modules.trend.entity.AldstatTrendAnalysis;
import com.alibaba.fastjson.JSONObject;

public class AldJsonUtil {
    public static JSONObject getAldJson(Object object,String type){
        JSONObject jsonAld=new JSONObject();
        //1小时 2天 3七天 4三十天
        if (type.equals("1")){
            AldstatHourlyTrendAnalysis ald=(AldstatHourlyTrendAnalysis)object;
            if (ald.getNew_comer_count()!=null){
                jsonAld.put("new_comer_count",ald.getNew_comer_count());
            }else {
                jsonAld.put("new_comer_count","0");
            }
            if (ald.getVisitor_count()!=null){
                jsonAld.put("visitor_count",ald.getVisitor_count());
            }else {
                jsonAld.put("visitor_count","0");
            }
            if (ald.getTotal_page_count()!=null){
                jsonAld.put("total_page_count",ald.getTotal_page_count());
            }else {
                jsonAld.put("total_page_count","0");
            }
            if (ald.getOpen_count()!=null){
                jsonAld.put("open_count",ald.getOpen_count());
            }else {
                jsonAld.put("open_count","0");
            }
            if (ald.getSecondary_avg_stay_time()!=null){
                jsonAld.put("secondary_avg_stay_time",ald.getSecondary_avg_stay_time());
            }else {
                jsonAld.put("secondary_avg_stay_time","0");
            }
            if (ald.getBounce_rate()!=null){
                jsonAld.put("bounce_rate",ald.getBounce_rate());
            }else {
                jsonAld.put("bounce_rate","0");
            }
        }else if(type.equals("2")){
            AldstatTrendAnalysis ald=(AldstatTrendAnalysis)object;
            if (ald.getNew_comer_count()!=null){
                jsonAld.put("new_comer_count",ald.getNew_comer_count());
            }else {
                jsonAld.put("new_comer_count","0");
            }
            if (ald.getVisitor_count()!=null){
                jsonAld.put("visitor_count",ald.getVisitor_count());
            }else {
                jsonAld.put("visitor_count","0");
            }
            if (ald.getTotal_page_count()!=null){
                jsonAld.put("total_page_count",ald.getTotal_page_count());
            }else {
                jsonAld.put("total_page_count","0");
            }
            if (ald.getOpen_count()!=null){
                jsonAld.put("open_count",ald.getOpen_count());
            }else {
                jsonAld.put("open_count","0");
            }
            if (ald.getSecondary_avg_stay_time()!=null){
                jsonAld.put("secondary_avg_stay_time",ald.getSecondary_avg_stay_time());
            }else {
                jsonAld.put("secondary_avg_stay_time","0");
            }
            if (ald.getBounce_rate()!=null){
                jsonAld.put("bounce_rate",ald.getBounce_rate());
            }else {
                jsonAld.put("bounce_rate","0");
            }
        } else if(type.equals("3")){
            Aldstat7daysTrendAnalysis ald=(Aldstat7daysTrendAnalysis)object;
            if (ald.getNew_comer_count()!=null){
                jsonAld.put("new_comer_count",ald.getNew_comer_count());
            }else {
                jsonAld.put("new_comer_count","0");
            }
            if (ald.getVisitor_count()!=null){
                jsonAld.put("visitor_count",ald.getVisitor_count());
            }else {
                jsonAld.put("visitor_count","0");
            }
            if (ald.getTotal_page_count()!=null){
                jsonAld.put("total_page_count",ald.getTotal_page_count());
            }else {
                jsonAld.put("total_page_count","0");
            }
            if (ald.getOpen_count()!=null){
                jsonAld.put("open_count",ald.getOpen_count());
            }else {
                jsonAld.put("open_count","0");
            }
            if (ald.getSecondary_avg_stay_time()!=null){
                jsonAld.put("secondary_avg_stay_time",ald.getSecondary_avg_stay_time());
            }else {
                jsonAld.put("secondary_avg_stay_time","0");
            }
            if (ald.getBounce_rate()!=null){
                jsonAld.put("bounce_rate",ald.getBounce_rate());
            }else {
                jsonAld.put("bounce_rate","0");
            }
        }else if(type.equals("4")){
            Aldstat30daysTrendAnalysis ald=(Aldstat30daysTrendAnalysis)object;
            if (ald.getNew_comer_count()!=null){
                jsonAld.put("new_comer_count",ald.getNew_comer_count());
            }else {
                jsonAld.put("new_comer_count","0");
            }
            if (ald.getVisitor_count()!=null){
                jsonAld.put("visitor_count",ald.getVisitor_count());
            }else {
                jsonAld.put("visitor_count","0");
            }
            if (ald.getTotal_page_count()!=null){
                jsonAld.put("total_page_count",ald.getTotal_page_count());
            }else {
                jsonAld.put("total_page_count","0");
            }
            if (ald.getOpen_count()!=null){
                jsonAld.put("open_count",ald.getOpen_count());
            }else {
                jsonAld.put("open_count","0");
            }
            if (ald.getSecondary_avg_stay_time()!=null){
                jsonAld.put("secondary_avg_stay_time",ald.getSecondary_avg_stay_time());
            }else {
                jsonAld.put("secondary_avg_stay_time","0");
            }
            if (ald.getBounce_rate()!=null){
                jsonAld.put("bounce_rate",ald.getBounce_rate());
            }else {
                jsonAld.put("bounce_rate","0");
            }
        }
            return jsonAld;
    }
}
