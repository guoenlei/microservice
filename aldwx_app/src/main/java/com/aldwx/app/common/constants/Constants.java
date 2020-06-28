package com.aldwx.app.common.constants;


/**
 * 常量
 */
public interface Constants {

    //返回状态码
    String RESULT_OK_STATUS = "result.ok.status";
    String RESULT_FAIL_STATUS = "result.fail.status";
    String RESULT_ERROR_STATUS = "result.error.status";

    //返回字段说明
    String RESULT_DATA_FIELD = "result.data.field";
    String RESULT_MSG_FIELD = "result.msg.field";
    String RESULT_CODE_FIELD = "result.code.field";
    String RESULT_DATE_FIELD = "result.date.field";
    String RESULT_COUNT_FIELD = "result.count.field";

    //未查询到数据
    String RESULT_OK_DATA_MSG = "result.ok.data.msg";
    String RESULT_NULL_DATA_MSG = "result.null.data.msg";

    //日期
    String PARAM_TODAY_DATE_FLAG = "param.today.date.flag";
    String PARAM_YESTERDAY_DATE_FLAG = "param.yesterday.date.flag";
    String PARAM_WEEKDAY_DATE_FLAG = "param.weekday.date.flag";
    String PARAM_MONTH_DATE_FLAG = "param.month.date.flag";


    //默认排序字段
    String DEFAULT_ORDER_BY_FIELD = "default.order.by.field";

    //数据概览 指标TopN
    String SCENE_TREND_DATA_LIMIT = "scene.trend.data.limit";
    String PAGE_TREND_DATA_LIMIT = "page.trend.data.limit";
    String INTELLIGENT_TREND_DATA_LIMIT = "intelligent.trend.data.limit";



    //小程序标识
    String ALD_STAT_FLAG = "ald.stat.flag";
    //小游戏标识
    String ALD_GAME_FLAG = "ald.game.flag";


    //日期
//    String PARAM_DATE_TODAY = "param.date.today";
//    String PARAM_DATE_YESTERDAY = "param.date.yesterday";
//    String PARAM_DATE_WORKDAY = "param.date.workday";
//    String PARAM_DATE_MONTH = "param.date.month";


}
