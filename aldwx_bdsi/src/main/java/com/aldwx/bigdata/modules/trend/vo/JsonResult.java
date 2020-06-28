package com.aldwx.bigdata.modules.trend.vo;

import com.aldwx.bigdata.common.util.DateUtil;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

public class JsonResult {
    /**
     * 返回标志
     */
    public String code = "200";
    /**
     * 数据
     */
    public Map data = null;
    /**
     * 比较数据
     */
    public Map data_con = null;
    /**
     * 当前查询日期
     */
    public String date = null;
    /**
     * 比较日期
     */
    public String date_con = null;

    public JsonResult() {
    }

    public JsonResult(String code, Map data, Map data_con, String date, String date_con) {
        this.code = code;
        this.data = data;
        this.data_con = data_con;
        this.date = date;
        this.date_con = date_con;
    }
  /*  public JsonResult( Map data,String date, String date_con) {
        this.data = (Map)data.get("data");
        this.data_con = (Map)data.get("data_con");
        if (date.equals("1")){
            this.date=DateUtil.getTodayDate();
        }else if (date.equals("2")){
            this.date=DateUtil.getYesterday();
        }else if (date.equals("3")){
            String[] day = DateUtil.getNearly7Day();
            this.date=day[0]+"~"+day[2];
        }else if (date.equals("4")){
            String[] day = DateUtil.getNearly30Day();
            this.date=day[0]+"~"+day[2];
        }else {
            this.date = date;
        }
        this.date_con = date_con;
    }*/
    public JsonResult( Pair<Map, Map> data) {
        this.data = data.getLeft();
        this.data_con = data.getRight();
    }
    public JsonResult( Map data) {
        this.data = (Map)data.get("data");
        this.data_con = (Map)data.get("data_con");
    }
}
