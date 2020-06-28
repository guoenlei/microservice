package com.aldwx.bigdata.modules.ad.controller;

import com.aldwx.bigdata.common.base.BaseController;
import com.aldwx.bigdata.common.constants.Constants;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.common.util.StringUtil;
import com.aldwx.bigdata.modules.ad.service.AldAdTargetApiService;
import com.aldwx.bigdata.modules.ad.vo.AldAdTargetApiVo;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static com.alibaba.druid.util.DruidWebUtils.getRemoteAddr;

/**
 * 给小盟广告提供的接口
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "xmadx/ad-target/api")
public class AldAdTargetApiController  extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AldAdTargetApiController.class);


    @Autowired
    private AldAdTargetApiService aldAdTargetApiService;


    /**
     * 广告监测接口2 - 按分页查询
     * @param date
     * @param total
     * @param currentPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryBy", method = RequestMethod.GET)
    public String byDate(/*HttpServletRequest request, */String date, String total, String currentPage) {
        AldAdTargetApiVo aldAdTargetApiVo = new AldAdTargetApiVo();
        Map<String, Object> result = null;
        //total 在100~10000范围内 分页默认从1开始
        if(StringUtils.isBlank(total)) {
            total = "100";
        }
        if(StringUtils.isBlank(currentPage)) {
            currentPage = "1";
        }
        if((Integer.valueOf(total)*Integer.valueOf(currentPage)) > 1000*1000) {
            return jsonFail("分页参数过大，单批次查询建议不要超过10W以上");
        }
        if(StringUtils.isNotBlank(date)) {
            date = date.replaceAll("\\s*", "");
            if(date.contains("~")) {
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                try {
                    //对日期校验
                    DateUtil.DATE_FORMAT.parse(dates[0]);
                    DateUtil.DATE_FORMAT.parse(dates[1]);
                } catch (Exception e) {
                    return jsonFail("日期格式输入错误");
                }
            }
            List<String> dateList = Lists.newArrayList();
            if(StringUtils.isNotBlank(date) && date.contains("~")) {
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                aldAdTargetApiVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
            }
        } else {
            //不输入日期默认查询全部数据
            aldAdTargetApiVo.setList(null);
        }
        aldAdTargetApiVo.setTotal(total);
        aldAdTargetApiVo.setCurrentPage(currentPage);
        try {
            result = aldAdTargetApiService.queryDaysDataBy(aldAdTargetApiVo);
        }catch ( Exception e) {
            e.printStackTrace();
            return jsonFail("接口内部异常，请联系开发人员");
        }
        return resultJson2(result, new Object[]{null != date ? date : "All"});
    }


    /**
     * 广告监测接口
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String byAppKey(/*HttpServletRequest request,*/ String appKey, String appId, String appName, String date) {
        Map<String, Object> result = null;

        if(StringUtils.isBlank(appId) && StringUtils.isBlank(appName) ) {
            return jsonFail("appId和appName不能同时为空");
        }

        if(StringUtils.isBlank(date)) {
//            String date1 = "2018-12-01";
//            String date2 = DateUtil.getTodayDate();
//            date = String.format("%s~%s", date1, date2);
            date = DateUtil.getTodayDate();
        }

        if(StringUtils.isBlank(date)) {
            return jsonFail("日期不能为空");
        } else {
            date = date.replaceAll("\\s*", "");
            if(date.contains("~")) {
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                try {
                    DateUtil.DATE_FORMAT.parse(dates[0]);
                    DateUtil.DATE_FORMAT.parse(dates[1]);
                } catch ( Exception e) {
                    return jsonFail("日期格式输入错误");
                }
            } else {
                try {
                    DateUtil.DATE_FORMAT.parse(date);
                } catch (Exception e) {
                    return jsonFail("日期格式输入错误");
                }
            }
        }
//        String requestIp = getRemoteAddr(request);
//        if(validAddress(request)) {
        if(true) {
            AldAdTargetApiVo aldAdTargetApiVo = new AldAdTargetApiVo();
            aldAdTargetApiVo.setAppKey(appKey);
            aldAdTargetApiVo.setAppId(appId);
            aldAdTargetApiVo.setAppName(appName);
            List<String> dateList = Lists.newArrayList();
            if(StringUtils.isNotBlank(date) && date.contains("~")) {
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                aldAdTargetApiVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
            } else {
                dateList.add(date);
                aldAdTargetApiVo.setList(dateList);
            }
            try {
                result = aldAdTargetApiService.queryPageDataList(aldAdTargetApiVo);
            } catch (Exception e) {
                return jsonError();
            }
        } else {
            return jsonFail("权限不足");
        }
        return resultJson2(result, new Object[]{date});
    }

//
//    /**
//     *
//     * @param appKey
//     * @param date
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "auth")
//    public String auth(String appKey, String date) {
//
//        return null;
//    }



    private boolean validAddress(HttpServletRequest request) {
        String requestIp = getRemoteAddr(request);
//        if(requestIp.equals("")) {
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request){
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }
}

