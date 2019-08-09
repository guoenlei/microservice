package com.ald.bigdata.modules.event.util;

import com.ald.bigdata.common.constants.Constants;
import com.ald.bigdata.common.util.DateUtil;
import com.ald.bigdata.modules.event.vo.EventVo;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 事件参数处理
 */
public class ParamUtils {

    //分页默认从第一页
    private static final String CURRENT_PAGE_DEFAULT = "1";
    //默认每页100条
    private static final String TOTAL_DEFAULT = "100";

    /**
     * 参数校验
     *
     * @param vo
     * @return
     */
    public static Map<String, Object> check(EventVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        String date = vo.getDate();
        String currentPage = vo.getCurrentPage();
        String total = vo.getTotal();
        String ak = vo.getAppKey();
        String ek = vo.getEventKey();
        if (StringUtils.isEmpty(date) || (!date.contains(Constants.FLAG_01) && date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME) &&
                date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME) && date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)
                && date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME))) {
            resultMap.put("code", 202);
            resultMap.put("msg", "日期格式有误");
        } else if (StringUtils.isEmpty(currentPage) || StringUtils.isEmpty(total)
                || !StringUtils.isNumeric(currentPage) || !StringUtils.isNumeric(total)) {
            resultMap.put("code", 202);
            resultMap.put("msg", "请输入正确的分页信息");
        } else if (StringUtils.isEmpty(ak) || StringUtils.isEmpty(ek)) {
            resultMap.put("code", 202);
            resultMap.put("msg", "参数输入有误");
        }

        return resultMap;
    }


    /**
     * 参数格式化处理
     *
     * @param vo
     * @return
     */
    public static EventVo format(EventVo vo) {
        String date = vo.getDate();
        String eventName = vo.getEventName();
        String currentPage = vo.getCurrentPage();
        String total = vo.getTotal();
        //将分区时间仅为今天或者昨天的 替换为1或2 直接查询mysql库
        if (date.contains(Constants.FLAG_01)) {
            String[] dates = StringUtils.splitPreserveAllTokens(date.replaceAll("\\s*", ""), "~");
            //判断时间区间是否仅包含2天
            if (dates.length == 2 && dates[0].equals(dates[1])) {
                if (dates[0].equals(DateUtil.getTodayDate())) {
                    vo.setDate(Constants.ALDSTAT_EVENT_TODAY_TIME);
                } else if (dates[0].equals(DateUtil.getYesterday())) {
                    vo.setDate(Constants.ALDSTAT_EVENT_YESTERDAY_TIME);
                }
            }
        }

        if (StringUtils.isNotBlank(currentPage) && Integer.parseInt(currentPage) <= 0) {
            vo.setCurrentPage(CURRENT_PAGE_DEFAULT);
        }
        if (StringUtils.isNotBlank(total) && Integer.parseInt(total) <= 0) {
            vo.setTotal(TOTAL_DEFAULT);
        }
        if (StringUtils.isNotBlank(eventName)) {
            vo.setEventName(eventName.contains("选择") ? "" : eventName);
        }
        return vo;
    }

}