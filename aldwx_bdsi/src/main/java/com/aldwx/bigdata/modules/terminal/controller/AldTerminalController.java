package com.aldwx.bigdata.modules.terminal.controller;

import com.aldwx.bigdata.common.base.BaseController;
import com.aldwx.bigdata.common.exception.AldException;
import com.aldwx.bigdata.common.util.ParamUitl;
import com.aldwx.bigdata.modules.terminal.service.AldTerminalService;
import com.aldwx.bigdata.modules.terminal.util.TerminalModule;
import com.aldwx.bigdata.modules.terminal.util.TerminalParam;
import com.aldwx.bigdata.modules.terminal.util.TerminalUtils;
import com.aldwx.bigdata.modules.terminal.vo.AldTerminalVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 终端处理
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value="term")
public class AldTerminalController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AldTerminalController.class);

    @Autowired
    private AldTerminalService aldTerminalService;


    /**
     * 列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="list", method = RequestMethod.POST)
    public String queryTerminalDataList(String date, String type, String module, String app_key) {

        AldTerminalVo vo = new AldTerminalVo(date, type, module, app_key);

        Map<String, String> checkMap = TerminalParam.paramCheck(vo);
        if(null != checkMap && checkMap.size() > 0) {
            LOG.info("参数输入错误");
            return returnJson(checkMap);
        }
        String m = TerminalModule.getName(module);
        String d = ParamUitl.formatDate(date);
        vo.setModule(m);
        vo.setDate(d);
        Map<String, Object> resultMap = null;
        long count = 0;
        try {
            Map<String, Object> entityMap = aldTerminalService.queryPageDataList(vo);
            resultMap = TerminalUtils.responseTerminalListFormat(entityMap);
        } catch (Exception e) {
            LOG.error("系统处理异常: {}", e);
            return jsonFail("系统处理异常");
        }
        return resultJson2(resultMap, new Object[]{date});
    }


    /**
     * 柱状图
     * @returne
     */
    @ResponseBody
    @RequestMapping(value="line", method = RequestMethod.POST)
    public String queryTerminalDataLine(String date, String type, String module, String app_key) {

        AldTerminalVo vo = new AldTerminalVo(date, type, module, app_key);

        Map<String, String> checkMap = TerminalParam.paramCheck(vo);
        if(null != checkMap && null != checkMap.get(RESULT_CODE)) {
            LOG.info("参数输入错误");
            return returnJson(checkMap);
        }
        String m = TerminalModule.getName(module);
        String d = ParamUitl.formatDate(date);
        vo.setModule(m);
        vo.setDate(d);
        Map<String, Object> resultMap = null;
        long count = 0;
        try {
            Map<String, Object> entityMap = this.aldTerminalService.queryPageDataList(vo);
            resultMap = TerminalUtils.responseTerminalLineFormat(entityMap, vo);
        } catch (Exception e) {
            LOG.error("程序处理异常: {}", e);
            return jsonFail("系统处理异常");
        }
        return resultJson2(resultMap, new Object[]{date/*, count*/});
    }



}

