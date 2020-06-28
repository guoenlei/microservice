package com.aldwx.bigdata.modules.terminal.util;

import com.aldwx.bigdata.common.util.ParamUitl;
import com.aldwx.bigdata.modules.terminal.vo.AldTerminalVo;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class TerminalParam {


    /**
     * 参数校验
     * @param vo
     * @return
     */
    public static Map<String, String> paramCheck(AldTerminalVo vo) {
        Map<String, String> checkMap = Maps.newConcurrentMap();
        if(null != vo) {
            String appKey = vo.getAppKey();
            String date = vo.getDate();
            String type = vo.getType();
            String module = vo.getModule();
            if(StringUtils.isEmpty(appKey) || StringUtils.isEmpty(module)) {
                checkMap.put("code", "202");
                checkMap.put("msg", "参数信息有误");
            } else if(ParamUitl.checkDate(date)) {
                checkMap.put("code", "202");
                checkMap.put("msg", "日期格式有误");
            }
        }

        return checkMap;
    }







}
