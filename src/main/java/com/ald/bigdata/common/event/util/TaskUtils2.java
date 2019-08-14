//package com.ald.bigdata.common.event.util;
//
//import com.ald.bigdata.common.constants.Constants;
//import com.ald.bigdata.common.util.Arith;
//import com.ald.bigdata.common.util.DateUtil;
//import com.ald.bigdata.common.event.vo.EventVo;
//import com.facebook.presto.jdbc.internal.guava.collect.Lists;
//import com.facebook.presto.jdbc.internal.guava.collect.Maps;
//import org.apache.commons.lang3.StringUtils;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class TaskUtils2 {
//
//
//    /**
//     * 获取事件参数列表
//     * @param eventInfo
//     * @return
//     */
//    public static Map<String, Object> queryEventParamDataList(EventVo eventInfo) {
//        Map<String, Object> result = Maps.newConcurrentMap();
//        Map<String, Object> resultMap = parseParamAndQueryData(eventInfo);
//        if(null != resultMap) {
//            String code = resultMap.get("code").toString();
//            if(code == "200") {
//                String count = resultMap.get("count").toString();
//                String date = resultMap.get("date").toString();
//                Object list = resultMap.get("data");
//                List<Object> newList = Lists.newArrayList();
//                if(null != list && list instanceof List) {
//                    List li = (List<Map<String, String>>)list;
//                    for(int i = 0 ; i < li.size(); i++) {
//                        Map<String, String> m = Maps.newConcurrentMap();
//                        Map<String,String> map = (Map<String,String>)li.get(i);
//                        if(null != map) {
//                            m.put("ev_paras_name", map.get("ev_paras_name"));
//                            m.put("ev_paras_count", map.get("ev_paras_count"));
//                        }
//
//                        newList.add(m);
//                    }
//                }
//                //下载
//                String isDownload = eventInfo.getIsDownload();
//                if(StringUtils.isNotBlank(isDownload) && isDownload.equals("1")) {
//                    result.put("tableData", newList);
//                } else {
//                    result.put("code", code);
//                    result.put("date", date);
//                    result.put("count", count);
//                    result.put("tableData", newList);
//                }
//            }
//            return resultMap;
//        }
//
//        return result;
//    }
//
//
//    /**
//     * 获取事件明细列表
//     * @param eventInfo
//     * @return
//     */
//    public static Map<String, Object> queryEventParamDetailsList(EventVo eventInfo) {
//        Map<String, Object> result = Maps.newConcurrentMap();
//        Map<String, Object> resultMap = parseParamAndQueryData(eventInfo);
//        if(null != resultMap) {
//            String code = resultMap.get("code").toString();
//            if(code == "200") {
//                String count = resultMap.get("count").toString();
//                String date = resultMap.get("date").toString();
//                Object list = resultMap.get("data");
//                List<Object> newList = Lists.newArrayList();
//                if(null != list && list instanceof List) {
//                    List li = (List<Map<String, String>>)list;
//                    for(int i = 0 ; i < li.size(); i++) {
//                        Map<String, String> m = Maps.newConcurrentMap();
//                        Map<String,String> map = (Map<String,String>)li.get(i);
//                        if(null != map) {
//                            m.put("ev_paras_value", map.get("ev_paras_value"));
//                            m.put("ev_paras_count", map.get("ev_paras_count"));
//                            m.put("ev_paras_uv", map.get("ev_paras_uv"));
//                        }
//
//                        newList.add(m);
//                    }
//                }
//                //下载
//                String isDownload = eventInfo.getIsDownload();
//                if(StringUtils.isNotBlank(isDownload) && isDownload.equals("1")) {
//                    result.put("tableData", newList);
//                } else {
//                    result.put("code", code);
//                    result.put("date", date);
//                    result.put("count", count);
//                    result.put("tableData", newList);
//                }
//            }
//            return resultMap;
//        }
//
//        return result;
//    }
//
//
//    /**
//     * 解析参数 并获取数据信息
//     * @param eventInfo
//     * @return
//     */
//    public static Map<String, Object> parseParamAndQueryData(EventVo eventInfo) {
//        String perstoSql = null;
//        String mySql = null;
//        String perstoSqlCount = null;
//        String mySqlCount = null;
//        String date = eventInfo.getDate();
//        Map<String, Object> resultMap = Maps.newConcurrentMap();
//        if(StringUtils.isNotBlank(date) && date.contains(Constants.FLAG_01)) {
//            String[] dates = StringUtils.splitPreserveAllTokens(date.replaceAll("\\s*","") , Constants.FLAG_01);
//            boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
//            if(isContains) {
//                System.out.println("需要关联查询");
//                //获取persto查询条件
//                perstoSql = createPerstoSql4(eventInfo, false).toString();
//                perstoSqlCount = createPerstoSqlCount(eventInfo);
//                //获取mysql查询条件
//                mySql = createMySql(eventInfo);
//                mySqlCount = createMySqlCount(eventInfo);
//                System.out.println("拼接perstoSql=" + perstoSql);
//                System.out.println("拼接mySql=" + mySql);
//            } else {
//                System.out.println("不需要关联查询，只查询hive中数据");
//                perstoSql = createPerstoSql4(eventInfo, false).toString();
//                perstoSqlCount = createPerstoSqlCount(eventInfo);
//                System.out.println("perstoSql=" + perstoSql);
//            }
//        } else {
//            mySql = createMySql(eventInfo);
//            mySqlCount = createMySqlCount(eventInfo);
//        }
//        List<Map<String, String>> mysqlResultList = null;
//        List<Map<String, String>> perstoResultList = null;
//        List<Map<String, String>> joinResultList = null;
//        int mysqlCount = 0;
//        int prestoCount = 0;
//        if(StringUtils.isNotBlank(mySql)) {
//            System.out.println("开始查询mysql中数据========" + mySql);
//            System.out.println("开始查询mysql count数据========" + mySqlCount);
//            long startTime = System.currentTimeMillis();
//            mysqlResultList = queryMysqlEventResult(eventInfo, mySql);
//            mysqlCount = queryMysqlEventResultCount(mySqlCount,eventInfo);
//            long endTime = System.currentTimeMillis();
//            System.out.println("结束查询msyql中数据, 共耗时：" + (endTime-startTime)/1000 + "秒，查询到"+ mysqlResultList.size()+"条数据");
//        }
//        if(StringUtils.isNotBlank(perstoSql)) {
//            System.out.println("开始查询persto中数据========" + perstoSql);
//            System.out.println("开始查询persto count数据========" + perstoSqlCount);
//            long startTime = System.currentTimeMillis();
//            perstoResultList = queryPerstoEventResult(eventInfo, perstoSql);
//            prestoCount = queryPrestoEventResultCount(perstoSqlCount);
//            long endTime = System.currentTimeMillis();
//            System.out.println("结束查询persto中数据, 共耗时：" + (endTime-startTime)/1000 + "秒，查询到"+ perstoResultList.size()+"条数据");
//        }
//
//        if(null != mysqlResultList && mysqlResultList.size() > 0
//                && null != perstoResultList && perstoResultList.size() > 0) {
//            joinResultList = joinResult2(mysqlResultList, perstoResultList, eventInfo);
//        }
//
//        //未匹配到数据时 同时不返还data
//        if((null == joinResultList || joinResultList.size() == 0) && (null == mysqlResultList || mysqlResultList.size() == 0) && (mysqlCount == 0)
//                && (prestoCount == 0) && (null == perstoResultList || perstoResultList.size() == 0)) {
//            resultMap.put("code", "202");
//            resultMap.put("msg", "没有数据");
//        } else {
//            resultMap.put("code", "200");
////            resultMap.put("count", mysqlCount + prestoCount);
//            int count = mysqlCount + prestoCount;
//            if(null != joinResultList && joinResultList.size() > 0) {
//                resultMap.put("data", joinResultList);
//                count = count - (mysqlResultList.size() + perstoResultList.size() - joinResultList.size());
//            } else {
//                if(null != mysqlResultList && mysqlResultList.size() > 0) {
//                    resultMap.put("data", mysqlResultList);
//                } else if(null != perstoResultList && perstoResultList.size() > 0) {
//                    resultMap.put("data", perstoResultList);
//                }
//                //处理分页
//                List<Map<String, String>> addList = Lists.newArrayList();
//                if(null != resultMap && null != resultMap.get("data")) {
//                    String total = eventInfo.getTotal();
//                    String currentPage = eventInfo.getCurrentPage();
//
//                    int _total = Integer.parseInt(eventInfo.getTotal());
//                    int _currentPage = Integer.parseInt(eventInfo.getCurrentPage());
//                    int limitStart = (int)Arith.add(1 , Arith.mul(Arith.sub(_currentPage, 1), _total)); //5
//                    int limitEnd = (int)Arith.mul(_total, _currentPage);    //6
//
//                    List<Map<String, String>> listMap = (List)resultMap.get("data");
//                    if(Integer.parseInt(eventInfo.getCurrentPage()) <= 10) {
//                        System.out.println("第二次分页");
//                        if(null != resultMap && listMap.size() <= Integer.parseInt(total) && limitStart <= listMap.size()) { //排序并添加全部数据到结果集中
//                            System.out.println("第二次分页");
//                            resultMap.put("data", listMap);
//                        } else { //根据分页 取total值条数据
//                            System.out.println("开始分页：" + limitStart + " 结束分页：" + limitEnd);
//                            if(null != listMap && limitStart <= listMap.size()) {
//                                System.out.println("第二次分页---2");
//                                for(int a = (limitStart-1); (a < listMap.size() && a < limitEnd); a++) {
//                                    System.out.println("开始重组数据，索引为：" + a);
//                                    addList.add(listMap.get(a));
//                                }
//                                resultMap.put("data", addList);
//                            } else if(null != listMap && limitStart > listMap.size()){
//                                resultMap.put("data", addList);
//                            }
//                        }
//                        //大于十页以上的数据
//                    } else {
//                        for(int a = 0; (a < listMap.size() && a < limitEnd); a++) {
////                            System.out.println("开始重组数据，索引为：" + a);
//                            addList.add(listMap.get(a));
//                        }
//                        resultMap.put("data", addList);
//                    }
//                } else {
//                    resultMap.put("data", addList);
//                }
//            }
//            resultMap.put("count", count);
//        }
//
//        resultMap.put("date", eventInfo.getDate());
//        return resultMap;
//    }
//
//
//
//    /**
//     *
//     * @param eventInfo
//     * @param isCount
//     * @return
//     */
//    public static StringBuilder createPerstoSql4(EventVo eventInfo, boolean isCount) {
//
//        String selectField = null;
//
//        if(eventInfo.getType().equals("1")) {
//            selectField = "ev_paras_value";
//        } else {
//            selectField = "ev_paras_name";
//        }
//
//        System.out.println("开始拼接persto sql");
//        String date = eventInfo.getDate();
//        String[] dates = StringUtils.splitPreserveAllTokens(date.replaceAll("\\s*","") , Constants.FLAG_01);
//
//        StringBuilder perstoSql = new StringBuilder();
//
//        if(!isCount) {
//            perstoSql.append("select c.* from( select row_number() over (order by ");
//        } else {
//            perstoSql.append("select count(app_key) from( select row_number() over (order by ");
//        }
//        String order = eventInfo.getOrder();
//        String prop = eventInfo.getProp();
//        if(StringUtils.isNotBlank(order) && StringUtils.isNotBlank(prop)) {
//            if(prop.equals("ev_paras_count")) {
//                prop = "paras_count ";
//            } else if(prop.equals("ev_paras_uv")) {
//                prop = "paras_uv ";
//            }
//            if(order.toUpperCase().contains("DESC")) {
//                order = " DESC ";
//            } else if(order.toUpperCase().contains("ASC")) {
//                order = " ASC ";
//            }
//        } else { //没有排序是 默认row_num对指定字段排序
//            prop = selectField;
//            order = " ASC ";
//        }
//
//        perstoSql.append("b.").append(prop);
//        perstoSql.append(order);
//        perstoSql.append(") as sort_field, b.* " +
//                "from (" +
//                "select a.app_key, a.event_key, a.")
//                .append(selectField)
//                .append(", a.paras_uv, a.paras_count " +
//                "FROM  (select app_key, event_key, ")
//                .append(selectField)
//                .append(", sum(ev_paras_uv) as paras_uv, " +
//                "sum(ev_paras_count) as paras_count " +
//                "FROM ");
//
//        perstoSql.append(Constants.PERSTO_TABLENAME).append(" where 1=1 ");
//        perstoSql.append(" AND parti_day in ('");
//
//        List<Date> dateList = DateUtil.getBetweenDates2(dates[0], dates[1]);
//        for(Date d : dateList) {
//            String s = DateUtil.DATE_FORMAT.format(d);
//            perstoSql.append(s).append("','");
//        }
//        if(perstoSql.toString().endsWith(",'")) {
//            perstoSql = new StringBuilder(perstoSql.toString().substring(0, perstoSql.toString().lastIndexOf(",'")));
//        }
//        perstoSql.append(")");
//
//        String appKey = eventInfo.getAppKey();
//        String eventKey = eventInfo.getEventKey();
//        String eventName = eventInfo.getEventName();
//        if(StringUtils.isNotBlank(appKey)) {
//            perstoSql.append(" AND app_key='").append(appKey).append("'");
//        }
//        if(StringUtils.isNotBlank(eventKey)) {
//            perstoSql.append(" AND event_key='").append(eventKey).append("'");
//        }
//        if(StringUtils.isNotBlank(eventName)) {
//            perstoSql.append(" AND ev_paras_name like").append(" '%")
//                    .append(eventName)
//                    .append("%'");
//        }
//
//        perstoSql.append(" GROUP BY app_key, event_key, ")
//                .append(selectField)
//                .append(") a order by a.").append(prop).append(order);
//
//        if(!isCount) {
//            String isDownload = eventInfo.getIsDownload();
//            if(StringUtils.isBlank(isDownload) || !isDownload.equals("1")) {
//                perstoSql.append(") b where 1=1) c where c.sort_field between ");
//                perstoSql.append(calculateLimit(eventInfo, "2"));
//            } else {
//                perstoSql.append(") b where 1=1) c ");
//            }
//        } else {
//            perstoSql.append(") b where 1=1) c ");
//        }
//
//        return perstoSql;
//    }
//
//
//    /**
//     * 查询事件参数表
//     * @param eventInfo
//     * @param isCount
//     * @return
//     */
//    public static StringBuilder createMySql4(EventVo eventInfo, boolean isCount) {
//        final String FILTER_DATE_HEAR = " AND substr(day, 1, 10)= '";
//        StringBuilder mysql = new StringBuilder();
//        String date = eventInfo.getDate();
//

////        date = "1";
//
//        String selectField = null;
//
//        if(eventInfo.getType().equals("1")) {
//            selectField = "ev_paras_value";
//        } else {
//            selectField = "ev_paras_name";
//        }
//
//        if(isCount) {
//            mysql.append("select count(b.event_key) from (");
//        }
//
//        mysql.append("select a.* from (" +
//                "select app_key, event_key, " + selectField +
//                " , sum(ev_paras_uv) as paras_uv, sum(ev_paras_count) as paras_count " +
//                "from ");
//        String tableName = null;
//        String filterDate = null;
//        if(StringUtils.isNotBlank(date)) {
//            if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {                   //今天
//                tableName = Constants.ALDSTAT_DAILY_EVENT_USER_GROUP;

//                filterDate = FILTER_DATE_HEAR + DateUtil.getTodayDate()+ "' ";
////                filterDate = FILTER_DATE_HEAR + "2018-09-04' ";
//
//            } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {        //昨天
//                tableName = Constants.ALDSTAT_DAILY_EVENT_USER_GROUP ;
//                filterDate = FILTER_DATE_HEAR + DateUtil.getYesterday()+ "' ";
//            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {     //最近7天
//                tableName = Constants.ALDSTAT_7DAYS_EVENT_USER_GROUP;
////                filterDate = FILTER_DATE_HEAR + DateUtil.getTodayDate()+ "' ";
//                filterDate = FILTER_DATE_HEAR + DateUtil.getYesterday()+ "' ";
//            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {       //最近30天
//                tableName = Constants.ALDSTAT_30DAYS_EVENT_USER_GROUP;
////                filterDate = FILTER_DATE_HEAR + DateUtil.getTodayDate()+ "' ";
//                filterDate = FILTER_DATE_HEAR + DateUtil.getYesterday()+ "' ";
//            } else { //分时间区间取数据
//                tableName = Constants.ALDSTAT_DAILY_EVENT_USER_GROUP;
//                filterDate = FILTER_DATE_HEAR + DateUtil.getTodayDate()+ "' ";
//            }
//            mysql.append(tableName);
//            mysql.append(" where 1=1 ");
//            mysql.append(filterDate);
//
//            String appKey = eventInfo.getAppKey();
//            String eventKey = eventInfo.getEventKey();
//            String eventName = eventInfo.getEventName();
//
//            if(StringUtils.isNotBlank(appKey)) {
//                mysql.append(" AND app_key='").append(appKey).append("'");
//            }
//            if(StringUtils.isNotBlank(eventKey)) {
//                mysql.append(" AND event_key='").append(eventKey).append("'");
//            }
//            if(StringUtils.isNotBlank(eventName)) {
//                mysql.append(" AND ev_paras_name like")
//                        .append(" '%")
//                        .append(eventName)
//                        .append("%'");
//            }
//
//            mysql.append(" group by app_key, event_key, ").append(selectField).append(")a where 1=1 ");
//
//            if(!isCount) {
//                String order = eventInfo.getOrder();
//                String prop = eventInfo.getProp();
//                if(StringUtils.isNotBlank(order) && StringUtils.isNotBlank(prop)) {
//                    if(prop.equals("ev_paras_count")) {
//                        prop = "a.paras_count";
//                    } else if(prop.equals("ev_paras_uv")) {
//                        prop = "a.paras_uv";
//                    }
//                    mysql.append("order by ").append(prop);
//                    if(order.toUpperCase().contains("DESC")) {
//                        mysql.append(" DESC");
//                    } else if(order.toUpperCase().contains("ASC")) {
//                        mysql.append(" ASC");
//                    }
//                }
////                else { //没有排序是 默认row_num对ev_paras_name排序
////                    mysql.append(" order by a.ev_paras_name asc");
////                }
//                String isDownload = eventInfo.getIsDownload();
//                if(StringUtils.isBlank(isDownload) || !isDownload.equals("1")) {
//                    mysql.append("  LIMIT ");
//                    mysql.append(calculateLimit(eventInfo, "1"));
//                } /*else {
//                    mysql.append(") b");
//                }*/
//            } else {
//                mysql.append(") b");
//            }
//        }
//
//        return mysql;
//    }
//
//
//
//
//    /**
//     * 获取persto数据
//     * @param perstoSql
//     * @return
//     */
//    private static List<Map<String, String>> queryPerstoEventResult(EventVo e, String perstoSql) {
//        List<Map<String, String>> resultList = Lists.newArrayList();
//        Statement statement = null;
//        try {
//            Connection PrestoConnection  = TaskUtils.getPrestoConnection();//创建presto连接对象
//            statement = PrestoConnection.createStatement();// 创建Statement对象
//            ResultSet rs = statement.executeQuery(perstoSql);
//            if(null != rs) {
//                while (rs.next()) {
//                    Map<String, String> map = Maps.newConcurrentMap();
//
//                    if(e.getType().equals("1")) {
//                        String app_key = rs.getString(2);
//                        String event_key = rs.getString(3);
//                        String ev_paras_value = rs.getString(4);
//                        String paras_uv = rs.getString(5);
//                        String paras_count = rs.getString(6);
//
//                        map.put("app_key", app_key);
//                        map.put("event_key", event_key);
//                        map.put("ev_paras_value", ev_paras_value);
//                        map.put("ev_paras_uv", paras_uv);
//                        map.put("ev_paras_count", paras_count);
//                    } else {
////                    String sortField = rs.getString(1);
//                        String app_key = rs.getString(2);
//                        String event_key = rs.getString(3);
//                        String ev_paras_name = rs.getString(4);
//                        String paras_uv = rs.getString(5);
//                        String paras_count = rs.getString(6);
//
//                        map.put("app_key", app_key);
//                        map.put("event_key", event_key);
//                        map.put("ev_paras_name", ev_paras_name);
//                        map.put("ev_paras_uv", paras_uv);
//                        map.put("ev_paras_count", paras_count);
//                    }
//
//                    resultList.add(map);
//                }
//            }
//        } catch (SQLException e1) {
//            e1.printStackTrace();
//        } finally {
//            try {
//                if(null != statement) {
//                    statement.close();
//                }
//            } catch (SQLException e2) {
//                e2.printStackTrace();
//            }
//        }
//
//        return resultList;
//    }
//
//
//    /**
//     * 获取mysql 数据
//     * @param mysql
//     * @return
//     */
//    private static List<Map<String, String>> queryMysqlEventResult(EventVo e, String mysql) {
//        List<Map<String, String>> resultList = Lists.newArrayList();
//        Statement stmt = null;
//        try {
//            Connection connection = TaskUtils.getSplitDBConnection(e);//建立mysql连接
//            stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(mysql);
//            if(null != rs) {
//                while(rs.next()) {
//                    Map<String, String> map = Maps.newConcurrentMap();
//                    if(e.getType().equals("1")) {
//                        String app_key = rs.getString(1);
//                        String event_key = rs.getString(2);
//                        String ev_paras_value = rs.getString(3);
//                        String paras_uv = rs.getString(4);
//                        String paras_count = rs.getString(5);
//
//                        map.put("app_key", app_key);
//                        map.put("event_key", event_key);
//                        map.put("ev_paras_value", ev_paras_value);
//                        map.put("ev_paras_uv", paras_uv);
//                        map.put("ev_paras_count", paras_count);
//                    } else {
//                        String app_key = rs.getString(1);
//                        String event_key = rs.getString(2);
//                        String ev_paras_name = rs.getString(3);
//                        String paras_uv = rs.getString(4);
//                        String paras_count = rs.getString(5);
//
//                        map.put("app_key", app_key);
//                        map.put("event_key", event_key);
//                        map.put("ev_paras_name", ev_paras_name);
//                        map.put("ev_paras_uv", paras_uv);
//                        map.put("ev_paras_count", paras_count);
//                    }
//
//                    resultList.add(map);
//                }
//            }
//        } catch (SQLException e1) {
//            e1.printStackTrace();
//        } finally {
//            if(null != stmt) {
//                try {
//                    stmt.close();
//                } catch (SQLException e2) {
//                    e2.printStackTrace();
//                }
//            }
//        }
//        return resultList;
//    }
//
//
//    public static int queryMysqlEventResultCount(String mysql, EventVo baseVO) {
//        Statement stmt = null;
//        int result = 0;
//        Connection connection = TaskUtils.getSplitDBConnection(baseVO);//建立mysql连接
//        try {
//            stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(mysql);
//            if(null != rs) {
//                while(rs.next()) {
//                    result = rs.getInt(1);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if(null != stmt) {
//                try {
//                    stmt.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return result;
//    }
//
//
//    public static int queryPrestoEventResultCount(String prestoSql) {
//        Statement stmt = null;
//        int result = 0;
//        try {
//            Connection PrestoConnection  = TaskUtils.getPrestoConnection();//创建presto连接对象
//            stmt = PrestoConnection.createStatement();// 创建Statement对象
//            ResultSet rs = stmt.executeQuery(prestoSql);
//            if(null != rs) {
//                while(rs.next()) {
//                    result = rs.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if(null != stmt) {
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return result;
//    }
//
//
//
//
//
//    /**
//     * 合并统计
//     * @return
//     */
//    private static List<Map<String, String>> joinResult2(List<Map<String, String>> mysqlResultList,
//                                                         List<Map<String, String>> prestoResultList, EventVo eventInfo) {
//
//        List<Map<String, String>> sumCountList = Lists.newArrayList();
//        Map<String, Map<String, String>> sumCountMap = Maps.newConcurrentMap();
//        for(Map<String , String> map : mysqlResultList) {
//            String appKey = map.get("app_key");
//            String eventKey = map.get("event_key");
//            String evParasUv = map.get("ev_paras_uv");
//            String evParasCount = map.get("ev_paras_count");
//            String sumCountKey = null;
//
//            if(eventInfo.getType().equals("1")) {
//                sumCountKey = map.get("ev_paras_value");
//            } else {
//                sumCountKey = map.get("ev_paras_name");
//            }
//
////            String sumCountKey = appKey + "_" + eventKey;
//            Object sumCountVal = sumCountMap.get(sumCountKey);
//            if(null == sumCountVal) {
//                System.out.println(" mysqlResultList 新的数据放入到treemap - " + sumCountKey);
//                sumCountMap.put(sumCountKey, map);
//            } else {
//                //累加计算访问人数和消息数
//                if(sumCountVal instanceof Map) {
//                    Map<String, String> m = (Map)sumCountVal;
//
//                    String _evParasUv = m.get("ev_paras_uv");
//                    String _evParasCount = m.get("ev_paras_count");
//
//                    map.put("ev_paras_uv", Integer.parseInt(_evParasUv) + Integer.parseInt(evParasUv) + "");
//                    map.put("ev_paras_count", Integer.parseInt(_evParasCount) + Integer.parseInt(evParasCount) + "");
//
//                    sumCountMap.put(sumCountKey, map);
//                }
//            }
//        }
//
//        for(Map<String , String> map : prestoResultList) {
//            String appKey = map.get("app_key");
//            String eventKey = map.get("event_key");
//            String evParasUv = map.get("ev_paras_uv");
//            String evParasCount = map.get("ev_paras_count");
//            String sumCountKey = null;
//
//            if(eventInfo.getType().equals("1")) {
//                sumCountKey = map.get("ev_paras_value");
//            } else {
//                sumCountKey = map.get("ev_paras_name");
//            }
//
////            String sumCountKey = appKey + "_" + eventKey;
//            Object sumCountVal = sumCountMap.get(sumCountKey);
//            if(null == sumCountVal) {
//                //sout
//                System.out.println(" prestoResultList 新的数据放入到treemap - " + sumCountKey);
//                sumCountMap.put(sumCountKey, map);
//            } else {
//                //累加计算访问人数和消息数
//                if(sumCountVal instanceof Map) {
//                    Map<String, String> m = (Map)sumCountVal;
//
//                    String _evParasUv = m.get("ev_paras_uv");
//                    String _evParasCount = m.get("ev_paras_count");
//
//                    map.put("ev_paras_uv", Integer.parseInt(_evParasUv) + Integer.parseInt(evParasUv) + "");
//                    map.put("ev_paras_count", Integer.parseInt(_evParasCount) + Integer.parseInt(evParasCount) + "");
//
//                    sumCountMap.put(sumCountKey, map);
//                }
//            }
//        }
//
//        return sortAndLimit(sumCountMap, eventInfo);
//    }
//
//
//    /**
//     * 排序并分页
//     * @return
//     */
//    public static List<Map<String, String>> sortAndLimit(Map<String, Map<String, String>> sumCountMap,
//                                                         EventVo eventInfo) {
//
//        String order = eventInfo.getOrder();
//        String prop = eventInfo.getProp();
//        String total = eventInfo.getTotal();
//        String currentPage = eventInfo.getCurrentPage();
//        //做排序处理
//        List<Map<String, String>> joinResultList = Lists.newArrayList();
//        List<Map<String, String>> listMap = Lists.newArrayList();
//        if(null != sumCountMap) {
//            Set<String> keys = sumCountMap.keySet();
//            if(null != keys && keys.size() > 0) {
//                for(String key : keys) {
//                    System.out.println("当前key为：" + key);
//                    if(StringUtils.isNotBlank(key)) {
//                        Map<String,String> mapStr = (Map<String,String>)sumCountMap.get(key);
//                        if(null != mapStr) {
//                            listMap.add(mapStr);
//                        }
//                    }
//                }
//
//                //合并排序
//                if(StringUtils.isNotBlank(order) && StringUtils.isNotBlank(prop)) {
//                    boolean isSort = false;
//                    if(order.toUpperCase().contains("ASC")) {
//                        isSort = true; //正序
//                    }
//                    System.out.println("开始排序操作....listMap长度为：" + listMap.size());
//                    for(int i = 0; i < listMap.size(); i++) {
//                        for(int j = 1; j < keys.size(); j++) {
//                            Object o = null;
//                            Map<String,String> mapStr1 = listMap.get(i);
//                            String val1 = mapStr1.get(prop);
//                            Map<String,String> mapStr2 = listMap.get(j);
//                            String val2 = mapStr2.get(prop);
//                            if(isSort) {
//                                if(Double.parseDouble(val1) >= Double.parseDouble(val2)) {
//                                    o = mapStr1;
//                                    listMap.set(i, mapStr2);
//                                    listMap.set(j, (Map<String, String>) o);
//                                }
//                            } else {
//                                if(Double.parseDouble(val1) <= Double.parseDouble(val2)) {
//                                    o = mapStr1;
//                                    listMap.set(i, mapStr2);
//                                    listMap.set(j, (Map<String, String>) o);
//                                }
//                            }
//                        }
//                    }
//                }
//
//                System.out.println("count size : " + listMap.size());
//
//                String isDownload = eventInfo.getIsDownload();
//                if(StringUtils.isNotBlank(isDownload) && isDownload.equals("1")) {
//                    joinResultList = listMap;
//                } else {
//
//                    int sum = 0;
//                    int _total = Integer.parseInt(total);
//                    int _currentPage = Integer.parseInt(currentPage);
//                    int limitStart = (int)Arith.add(1 , Arith.mul(Arith.sub(_currentPage, 1), _total)); //5
//                    int limitEnd = (int)Arith.mul(_total, _currentPage);    //6
//
//                    System.out.println("list size 为：" + listMap.size() + " total 长度为 ： " + total);
//                    if(null != listMap && listMap.size() <= Integer.parseInt(total) && limitStart <= listMap.size()) { //排序并添加全部数据到结果集中
//                        joinResultList = listMap;
//                    } else { //根据分页 取total值条数据
//                        System.out.println("开始分页：" + limitStart + " 结束分页：" + limitEnd);
//                        if(null != listMap && limitStart <= listMap.size()) {
//                            for(int a = (limitStart-1); (a < listMap.size() && a < limitEnd); a++) {
//                                System.out.println("开始重组数据，索引为：" + a);
//                                joinResultList.add(listMap.get(a));
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println("结果数据长度为：" + joinResultList.size());
//        return joinResultList;
//    }
//
//
//
//
//
//    /**
//     * 计算数据开始行和结束行
//     * @param eventInfo
//     * @return
//     */
//    private static String calculateLimit(EventVo eventInfo, String type) {
//        String limitStart = null;
//        String limitEnd = null;
//        if(null != eventInfo) {
//            String currentPage = eventInfo.getCurrentPage();
//            String total = eventInfo.getTotal();
//            if(StringUtils.isNumeric(total) && StringUtils.isNumeric(currentPage)) {
//                int _total = Integer.parseInt(total);
//                int _currentPage = Integer.parseInt(currentPage);
//
//                if(_currentPage >= 1 && _currentPage <= 10) {
//                    if(type.equals("1")) {
//                        limitStart = "0";   //mysql
//                    } else {
////                        limitStart = (int)Arith.add(1 , Arith.mul(Arith.sub(_currentPage, 1), _total)) + "";
//                        limitStart = "1";   //presto
//                    }
//                    limitEnd = (int)Arith.mul(_total, _currentPage) + "";
//                } else {
//                    //mysql
//                    if(type.equals("1")) {
//                        limitStart = (int)Arith.add(0 , Arith.mul(Arith.sub(_currentPage, 1), _total)) + "";
//                        limitEnd = total;
//                        //presto
//                    } else {
//                        limitStart = (int)Arith.add(1 , Arith.mul(Arith.sub(_currentPage, 1), _total)) + "";
//                        limitEnd = (int)Arith.mul(_total, _currentPage) + "";
//                    }
//                }
//            }
//        }
//        System.out.println("start = " +limitStart + " end = " + limitEnd);
//        return limitStart + (type.equals("1") ? "," : " and ") + limitEnd;
//    }
//
//    public static String createMySql(EventVo e) {
//        StringBuilder sql = createMySql4(e, Constants.IS_COUNT);
//        return null == sql ? null : sql.toString();
//    }
//
//
//    /**
//     * 获取mysql count值
//     * @return
//     */
//    public static String createMySqlCount(EventVo e) {
//        StringBuilder sql = createMySql4(e, !Constants.IS_COUNT);
//        return null == sql ? null : sql.toString();
//    }
//
//    public static String createPerstroSql(EventVo e) {
//        StringBuilder sql = createPerstoSql4(e, Constants.IS_COUNT);
//        return null == sql ? null : sql.toString();
//    }
//
//    /**
//     * 获取presto count值
//     * @return
//     */
//    public static String createPerstoSqlCount(EventVo e) {
//        StringBuilder sql = createPerstoSql4(e, !Constants.IS_COUNT);
//        return null == sql ? null : sql.toString();
//    }
//
//
//    public static void main(String[] args) {
//
////        int _total = 10;
////        int _currentPage = 3;
////
////        int limitStart = (int)Arith.add(1 , Arith.mul(Arith.sub(_currentPage, 1), _total)); //5
////        int limitEnd = (int)Arith.mul(_total, _currentPage);    //6
////        System.out.println("开始分页：" + limitStart + " 结束分页：" + limitEnd);
////
////        System.out.println("开始取值,索引为:" + (limitStart-1));
//
//
////        if(null != "100" && limitStart <= "100".size()) {
////            for(int a = (limitStart-1); (a < "100".size() && a < limitEnd); a++) {
////                joinResultList.add(listMap.get(a));
////            }
////        }
//
//
//        EventVo e = new EventVo();
////        e.setDate("2018-09-04");
//        e.setDate("2018-10-01~2018-10-14");
////        e.setDate("1");
////        e.setTypeId("1");
////        e.setKeyword("a");
//
//        e.setType("1");
//
//        e.setAppKey("f2468cafe1ac77ba12aca1778e67b6b8");
//        e.setEventKey("50e2e82b492fae492e268cae43373730");
//        e.setEventName("");
//
////        e.setIsDownload("1");
//
//        e.setCurrentPage("7958");
//        e.setTotal("10");
//        e.setOrder("desc");
//        e.setProp("ev_paras_count");
//        System.out.println("测试开始....");
//
////        queryEventParamDataList(e);
//        queryEventParamDetailsList(e);
//    }
//
//
//}