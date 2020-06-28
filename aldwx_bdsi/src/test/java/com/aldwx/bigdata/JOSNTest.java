package com.aldwx.bigdata;

import com.alibaba.fastjson.JSONObject;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;

import java.util.Map;

public class JOSNTest {


    public static void main(String[] args) {




    }



    public static String jsonPrase() {

        Map<String, String> map = Maps.newConcurrentMap();

        Object obj = JSONObject.toJSON(map);


        return null;
    }
}
