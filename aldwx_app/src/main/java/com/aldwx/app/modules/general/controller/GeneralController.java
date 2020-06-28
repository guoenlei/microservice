package com.aldwx.app.modules.general.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.common.util.CurrencyVo;
import com.aldwx.app.common.util.DateUtil;
import com.aldwx.app.common.util.VoUtil;
import com.aldwx.app.modules.general.assist.GeneralAssist;
import com.aldwx.app.modules.general.bean.General;
import com.aldwx.app.modules.general.service.GeneralService;
import com.aldwx.app.modules.general.vo.GeneralVo;
import com.aldwx.app.modules.page.entity.PageEntity;
import com.aldwx.app.modules.retain.vo.RetainVo;
import com.aldwx.app.modules.scene.entity.SceneEntity;
import com.aldwx.app.modules.smart.entity.SmartEntity;
import com.aldwx.app.modules.trend.entity.TrendEntity;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 数据概览
 * 对应菜单-模块下接口
 *
 * @author lx
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "aldstat/app/general")
public class GeneralController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(GeneralController.class);

    @Autowired
    private GeneralService generalService;


    /**
     * 概况 - 今日概况数据 - 概览
     * <p>
     * 新用户数 访问人数 打开次数
     *
     * @param app_key  小程序唯一标识
     * @param date     日期 1,2,3,4，日期区间 2019-04-01~2019-04-16
     * @param app_type 小程序类型  1小程序 2小游戏
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public String queryBasicDataView(String app_key, String date, String app_type) {
        Map<String, Object> resultMap = null;
        try {
            CurrencyVo vo = VoUtil.getCurrencyVo(app_key, date);
            Pair<List<TrendEntity>, List<TrendEntity>> entityPair = this.generalService.queryBasicDataView(new General(app_key, date, app_type));
            resultMap = GeneralAssist.todayGeneralData(entityPair, vo);
        } catch (Exception e) {
            LOG.error("异常:{}", e);
        }
        return resultJosn4(resultMap);
    }


    /**
     * 概况 - 获取基础指标数据 - 折线图 - 旧版（仍提供）
     * <p>
     * 新用户数 访问人数 打开次数
     *
     * @param app_key  小程序唯一标识
     * @param date     日期 1,2,3,4，日期区间 2019-04-01~2019-04-16
     * @param app_type 小程序类型  1小程序 2小游戏
     * @param type     1新用户，2访问人数，3.打开次数，4访问次数，5次均停留时长，6跳出率
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/chart", method = RequestMethod.POST)
//    public String queryBasicDataChart(String app_key, String date, String app_type, String type) {
//        Map<String, Object> resultMap = null;
//        try {
//            CurrencyVo vo = VoUtil.getCurrencyVo(app_key, date);
//            Map<String, List<TrendEntity>> entityPair = this.generalService.queryBasicDataChart(new General(app_key, date, app_type, type));
//
//            resultMap = GeneralAssist.basicChart(entityPair, date, type, vo);
//        } catch (Exception e) {
//            LOG.error("异常:{}", e);
//            return jsonError();
//        }
//        return resultJosn4(resultMap);
//    }

    /**
     * 概况 - 获取基础指标数据 - 折线图 - 改需求后（将type=1,2,3,4一并展示）
     * <p>
     * 新用户数 访问人数 打开次数
     *
     * @param app_key  小程序唯一标识
     * @param date     日期 1,2,3,4，日期区间 2019-04-01~2019-04-16
     * @param app_type 小程序类型  1小程序 2小游戏
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/charts", method = RequestMethod.POST)
    public String querBasicDataChartNew(String app_key, String date, String app_type, String type) {
        Map<String, Object> resultMap = null;
        try {
            CurrencyVo vo = VoUtil.getCurrencyVo(app_key, date);
            Map<String, List<TrendEntity>> entityPair = this.generalService.queryBasicDataChartNew(new General(app_key, date, app_type, type));

            resultMap = GeneralAssist.basicChartNew(entityPair, date, vo, type);
        } catch (Exception e) {
            LOG.error("异常:{}", e);
            return jsonError();
        }
        return resultJosn4(resultMap);
    }


    /**
     * 概况 - 场景值趋势TOP5 - 列表 - 旧版（仍提供）
     *
     * @param app_key  小程序唯一标识
     * @param date     日期 1,2,3,4，日期区间 2019-04-01~2019-04-16
     * @param app_type 小程序类型  1小程序 2小游戏
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/scene", method = RequestMethod.POST)
//    public String querySceneTrendTopList(String app_key, String date, String app_type) {
//        List<SceneEntity> sceneEntityList = generalService.querySceneTrendTopList(new General(app_key, date, app_type));
//        return resultJosn4(sceneEntityList);
//    }


    /**
     * 概况 - 场景值趋势TOP5 - 列表 - 改需求后
     *
     * @param app_key  小程序唯一标识
     * @param date     日期 1,2,3,4，日期区间 2019-04-01~2019-04-16
     * @param app_type 小程序类型  1小程序 2小游戏
     * @param type     访问人数,打开次数,页面访问次数，新用户数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/scenes", method = RequestMethod.POST)
    public String querySceneTrendTopList(@RequestParam(value = "app_key") String app_key,
                                         @RequestParam(value = "date") String date,
                                         @RequestParam(value = "app_type") String app_type,
                                         @RequestParam(value = "type") String type) {
        Map<String, Object> sceneEntityMap = generalService.querySceneTrendTopListNew(app_key, date, app_type, type);
        Map<String, Object> listMap = topSort(sceneEntityMap);//降序排序
        return resultJosn4(listMap);
    }

    /**
     * 概况 - 页面趋势TOP5 - 列表
     *
     * @param app_key  小程序唯一标识
     * @param date     日期 1,2,3,4，日期区间 2019-04-01~2019-04-16
     * @param app_type 小程序类型  1小程序 2小游戏
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public String queryPageTrendTopList(String app_key, String date, String app_type) {
        List<PageEntity> pageEntityList = generalService.queryPageTrendTopList(new General(app_key, date, app_type));
        return resultJosn4(pageEntityList);
    }


    /**
     * 概况 - 智能外链跟踪top5 - 列表
     *
     * @param app_key  小程序唯一标识
     * @param date     日期 1,2,3,4，日期区间 2019-04-01~2019-04-16
     * @param app_type 小程序类型  1小程序 2小游戏
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/smart", method = RequestMethod.POST)
    public String querySmartTrendTopList(String app_key, String date, String app_type) {
        List<SmartEntity> smartEntityList = generalService.querySmartTrendTopList(new General(app_key, date, app_type));
        return resultJosn4(smartEntityList);
    }

    public static Map<String, Object> topSort(Map<String, Object> map) {


        //TODO Int 转换 Long
        // 升序比较器
        Comparator<Map.Entry<String, Object>> valueComparator = new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> o1,
                               Map.Entry<String, Object> o2) {
                long oA = Long.valueOf(String.valueOf(o1.getValue()));;
                long oB = Long.valueOf(String.valueOf(o2.getValue()));;
                if (oA - oB > 0) {
                    return -1;
                } else if (oA - oB < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        // map转换成list进行排序
        List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(map.entrySet());

        // 排序
        Collections.sort(list, valueComparator);

        // 默认情况下，TreeMap对key进行升序排序
        Map<String, Object> listMap = new LinkedHashMap<>();
//        System.out.println("------------map按照value升序排序--------------------");
        for (Map.Entry<String, Object> entry : list) {
            listMap.put(entry.getKey(), entry.getValue());
        }
        Set<Map.Entry<String, Object>> set = listMap.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> next = it.next();
            System.out.println(next.getKey() + ":" + next.getValue());
        }
        return listMap;
    }


}
