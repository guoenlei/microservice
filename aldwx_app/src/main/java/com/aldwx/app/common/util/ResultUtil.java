package com.aldwx.app.common.util;

import java.util.Map;

public class ResultUtil {
    private static final String success="成功";
    private static final String nodata="无数据";
    private static final String error="错误";
    private static final int su=200;
    private static final int no=202;
    private static final int er=500;
    public static Map<String,Object> success(Map map){
        map.put("code",su);
        map.put("msg",success);
        return map;
    }
    public static Map<String,Object> nodata(Map map){
        map.put("code",no);
        map.put("msg",nodata);
        return map;
    }
    public static Map<String,Object> error(Map map){
        map.put("code",er);
        map.put("msg",error);
        return map;
    }
}
