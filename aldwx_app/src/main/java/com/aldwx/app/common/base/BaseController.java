package com.aldwx.app.common.base;

import com.aldwx.app.common.config.ConfigurationManager;
import com.aldwx.app.common.constants.Constants;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.alibaba.fastjson.JSONObject;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseController {

    private static final int STATUS_OK = ConfigurationManager.getInteger(Constants.RESULT_OK_STATUS);
    private static final int STATUS_FAIL = ConfigurationManager.getInteger(Constants.RESULT_FAIL_STATUS);
    private static final int STATUS_ERROR = ConfigurationManager.getInteger(Constants.RESULT_ERROR_STATUS);

    public static final String RESULT_DATA = ConfigurationManager.getProperty(Constants.RESULT_DATA_FIELD);
    public static final String RESULT_MSG = ConfigurationManager.getProperty(Constants.RESULT_MSG_FIELD);
    public static final String RESULT_CODE = ConfigurationManager.getProperty(Constants.RESULT_CODE_FIELD);
    public static final String RESULT_DATE = ConfigurationManager.getProperty(Constants.RESULT_DATE_FIELD);
    public static final String RESULT_COUNT = ConfigurationManager.getProperty(Constants.RESULT_COUNT_FIELD);

    public static final String RESULT_OK_DATA_MSG = ConfigurationManager.getProperty(Constants.RESULT_OK_DATA_MSG);
    public static final String RESULT_NULL_DATA_MSG = ConfigurationManager.getProperty(Constants.RESULT_NULL_DATA_MSG);


    /**
     * 转json string返回
     * @param obj
     * @return
     */
    public static String returnJson(Object obj) {
        return JSONObject.toJSONString(obj);
    }



    public static String jsonError() {
        String msg = "内部程序异常";
        Map<String, Object> result = new HashMap<>();
        result.put(RESULT_CODE, STATUS_ERROR);
        result.put(RESULT_MSG, msg);
        return returnJson(result);
    }


    /**
     * 转json
     * @param entityList
     * @param objects
     * @return
     */
    public static String returnJson(List<? extends BaseEntity> entityList, Object[] objects) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != entityList && entityList.size() > 0) {
            resultMap.put(RESULT_CODE, STATUS_OK);                  //状态
            resultMap.put(RESULT_DATA, entityList);                 //数据
        } else {
            resultMap.put(RESULT_CODE, STATUS_FAIL);                //状态
            resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);        //无数据
        }

        resultMap.put(RESULT_DATE, objects[0]);                     //时间

        return returnJson(resultMap);
    }


    /**
     * 格式化数据
     * @param object
     * @param objects
     * @return
     */
    public static String resultJson2(Object object, Object[] objects) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(object instanceof List) {
            List<?> lists = (List<?>) object;
            if(null != lists && lists.size() > 0) {
                resultMap.put(RESULT_CODE, STATUS_OK);
                resultMap.put(RESULT_DATA, lists);
            } else {
                resultMap.put(RESULT_CODE, STATUS_FAIL);
                resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
            }
        } else if(object instanceof Map) {
            Map<String, Object> map = (Map<String, Object>)object;
            if(null != map && map.size() > 0) {
                resultMap.put(RESULT_CODE, STATUS_OK);
                for(Map.Entry<String, Object> entry : map.entrySet()) {
                    resultMap.put(entry.getKey(), entry.getValue());
                }
            } else {
                resultMap.put(RESULT_CODE, STATUS_FAIL);
                resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
            }
        }
        resultMap.put(RESULT_DATE, objects[0]);     //时间
        if(objects.length > 1) {
            resultMap.put(RESULT_COUNT, objects[1]);     //总数
        }

        return returnJson(resultMap);
    }


    /**
     * 折线图数据格式化
     * @param object
     * @return
     */
    public static String resultJson3(Object object/*, Object[] objects*/) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(object instanceof List) {
            List<?> lists = (List<?>) object;
            if(null != lists && lists.size() > 0) {
                resultMap.put(RESULT_CODE, STATUS_OK);
                resultMap.put(RESULT_DATA, lists);
            } else {
                resultMap.put(RESULT_CODE, STATUS_FAIL);
                resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
            }
        } else if(object instanceof Map) {
            Map<String, Object> map = (Map<String, Object>)object;
            if(null != map && map.size() > 0) {
                resultMap.put(RESULT_CODE, STATUS_OK);
                for(Map.Entry<String, Object> entry : map.entrySet()) {
                    resultMap.put(entry.getKey(), entry.getValue());
                }
            } else {
                resultMap.put(RESULT_CODE, STATUS_FAIL);
                resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
            }
        }
        return returnJson(resultMap);
    }


    public static String resultJosn4(Object object) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null == object) {
            resultMap.put(RESULT_CODE, STATUS_FAIL);
            resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
        } else {
            resultMap.put(RESULT_CODE, STATUS_OK);
            resultMap.put(RESULT_MSG, RESULT_OK_DATA_MSG);
            resultMap.put(RESULT_DATA, object);
        }
        if(object instanceof List) {
            List<?> lists = (List<?>) object;
            if(null != lists && lists.size() > 0) {
                resultMap.put(RESULT_CODE, STATUS_OK);
                resultMap.put(RESULT_MSG, RESULT_OK_DATA_MSG);
                resultMap.put(RESULT_DATA, object);
            } else {
                resultMap.put(RESULT_CODE, STATUS_FAIL);
                resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
            }
        }
        if(object instanceof Map) {
            Map<String, Object> map = (Map<String, Object>)object;
            if(null != map && map.size() > 0) {
                resultMap.put(RESULT_CODE, STATUS_OK);
                resultMap.put(RESULT_MSG, RESULT_OK_DATA_MSG);
                resultMap.put(RESULT_DATA, object);
            } else {
                resultMap.put(RESULT_CODE, STATUS_FAIL);
                resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
            }
        }

        return returnJson(resultMap);
    }


    public static String resultJosn5(Map<String, Object> entityMap) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != entityMap && entityMap.size() > 0) {
            Object data = entityMap.get("data");
            Object count = entityMap.get("count");
            resultMap.put(RESULT_CODE, STATUS_OK);
            resultMap.put(RESULT_DATA, null == data ? "" : data);
            if(null != count) {
                resultMap.put(RESULT_COUNT, count);
            }
        } else {
            resultMap.put(RESULT_CODE, STATUS_FAIL);
            resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
        }
        return returnJson(resultMap);
    }

    public static String resultJosn10(Object object) {
        Map<String, Object> resultMap = new HashMap<>();
        if(null == object) {
            resultMap.put(RESULT_CODE, STATUS_FAIL);
            resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
        } else {
            Map result=(Map)object;
            resultMap.put(RESULT_CODE, STATUS_OK);
            resultMap.put(RESULT_MSG, RESULT_OK_DATA_MSG);
            resultMap.put(RESULT_DATA, result.get("data"));
            resultMap.put("count", result.get("num"));
        }
        if(object instanceof List) {
            List<?> lists = (List<?>) object;
            if(null != lists && lists.size() > 0) {
                resultMap.put(RESULT_CODE, STATUS_OK);
                resultMap.put(RESULT_MSG, RESULT_OK_DATA_MSG);
                resultMap.put(RESULT_DATA, object);
            } else {
                resultMap.put(RESULT_CODE, STATUS_FAIL);
                resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
            }
        }
        if(object instanceof Map) {
            Map<String, Object> map = (Map<String, Object>)object;
            if(null != map && map.size() > 0) {
                resultMap.put(RESULT_CODE, STATUS_OK);
                resultMap.put(RESULT_MSG, RESULT_OK_DATA_MSG);
                resultMap.put(RESULT_DATA, object);
            } else {
                resultMap.put(RESULT_CODE, STATUS_FAIL);
                resultMap.put(RESULT_MSG, RESULT_NULL_DATA_MSG);
            }
        }

        return returnJson(resultMap);
    }

    /**
     * 场景值Top(N)
     * 用于转换成柱状图的数据格式方法
     * @param object
     * @return
     */
    public static String resultJosnBarGraph(Object object) {


        return null;

    }


}
