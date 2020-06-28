package com.aldwx.bigdata.modules.terminal.util;

import com.aldwx.bigdata.common.util.StringUtil;
import com.aldwx.bigdata.modules.terminal.entity.TerminalEntity;
import com.aldwx.bigdata.modules.terminal.vo.AldTerminalVo;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Map;


public class TerminalUtils {


    /**
     * 格式化返回数据
     * @return
     */
    public static Map<String, Object> responseTerminalListFormat(Map<String, Object> entityMap ) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<Map<String, Object>> resultList = Lists.newArrayList();
        if(null != entityMap) {
            List<TerminalEntity> terminalEntityList = (List<TerminalEntity>)entityMap.get("data");
            Long dataCount = (Long)entityMap.get("count");
            if(null != terminalEntityList && terminalEntityList.size() > 0) {

                Map<String, String> poolMap = Maps.newConcurrentMap();
                int newComerCountSum = 0;
                int totalPageCountSum = 0;
                int visitorCountSum = 0;
                int openCountSum = 0;
                double avgStayTimeSum = 0;
                float bounceRateSum = 0;

                for(TerminalEntity terminalEntity : terminalEntityList) {
                    Map<String, Object> dataMap = Maps.newConcurrentMap();

                    String typeValue = terminalEntity.getTypeValue();
                    int newComerCount = terminalEntity.getNewComerCount();
                    int totalPageCount = terminalEntity.getTotalPageCount();
                    int visitorCount = terminalEntity.getVisitorCount();
                    int openCount = terminalEntity.getOpenCount();
                    double avgStayTime = terminalEntity.getAvgStayTime();
                    float bounceRate = terminalEntity.getBounceRate();

                    dataMap.put("type_value", typeValue);
                    dataMap.put("new_comer_count", newComerCount + "");
                    dataMap.put("total_page_count", totalPageCount + "");
                    dataMap.put("visitor_count", visitorCount + "");
                    dataMap.put("open_count", openCount + "");
                    dataMap.put("avg_stay_time", StringUtil.formatTime(avgStayTime));
                    dataMap.put("bounce_rate", StringUtil.formatPercent(bounceRate));

                    newComerCountSum += newComerCount;
                    totalPageCountSum += totalPageCount;
                    visitorCountSum += visitorCount;
                    openCountSum += openCount;
                    avgStayTimeSum += avgStayTime;
                    bounceRateSum += bounceRate;

                    resultList.add(dataMap);
                }

                poolMap.put("new_comer_count", newComerCountSum + "");
                poolMap.put("total_page_count", totalPageCountSum + "");
                poolMap.put("visitor_count", visitorCountSum + "");
                poolMap.put("open_count", openCountSum + "");
                poolMap.put("avg_stay_time", StringUtil.formatTime(avgStayTimeSum));
                poolMap.put("bounce_rate", StringUtil.formatPercent(bounceRateSum));

                resultMap.put("terminalCount", dataCount);
                resultMap.put("data", resultList);
                resultMap.put("pool", poolMap);
            }
        }

        return resultMap;
    }



    /**
     * 数据格式化
     * @return
     */
    public static Map<String, Object> responseTerminalLineFormat(Map<String, Object> entityMap, AldTerminalVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != entityMap) {
            List<TerminalEntity> terminalEntityList = (List<TerminalEntity>)entityMap.get("data");
            if(null != terminalEntityList && terminalEntityList.size() > 0) {
                Map<String, Object> xAxisMap = Maps.newConcurrentMap();
                Map<String, Object> seriesMap = Maps.newConcurrentMap();

                List<Map<String, Object>> seriesMapList = Lists.newArrayList();

                List<String> xAxisList = Lists.newArrayList();
                List<String> seriesList = Lists.newArrayList();

                for(TerminalEntity terminalEntity : terminalEntityList) {
                    xAxisList.add(terminalEntity.getTypeValue());
                    if(vo.getType().equals("1")) {  //新增用户
                        seriesMap.put("name", "新增用户");
                        seriesList.add(terminalEntity.getNewComerCount() + "");
                    } else if(vo.getType().equals("2")) {               //访问人数
                        seriesMap.put("name", "访问人数");
                        seriesList.add(terminalEntity.getVisitorCount() + "");
                    } else if(vo.getType().equals("3")) {               //访问次数
                        seriesMap.put("name", "访问次数");
                        seriesList.add(terminalEntity.getTotalPageCount() + "");
                    }
                }

                xAxisMap.put("data", xAxisList);
                seriesMap.put("data", seriesList);

                seriesMapList.add(seriesMap);

                //xAxis
                resultMap.put("xAxis", xAxisMap);
                //series
                resultMap.put("series", seriesMapList);
            }
        }

        return resultMap;
    }




}