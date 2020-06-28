package com.aldwx.bigdata.modules.share.controller;


import com.aldwx.bigdata.common.base.BaseController;
import com.aldwx.bigdata.modules.gameEvent.util.GameEventParamUtils;
import com.aldwx.bigdata.modules.share.service.AldUserShareService;
import com.aldwx.bigdata.modules.share.util.UserShareUtil;
import com.aldwx.bigdata.modules.share.vo.AldUserShareVo;
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
 * 小游戏 - 用户分享
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value="share/user")
public class AldUserShareController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AldUserShareController.class);

    @Autowired
    private AldUserShareService aldUserShareService;


    /**
     * 获取用户分享列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String queryUserShareList(String app_key, String date, String prop, String order, String type,
                                     String keyword, String currentPage, String total, String isDownload) {
        AldUserShareVo vo = new AldUserShareVo(app_key, date, order, prop, type, keyword);
        vo.setCurrentPage(currentPage);
        vo.setTotal(total);
        Map<String, Object> checkMap = UserShareUtil.checkParam(vo);
        if(null != checkMap && checkMap.size() > 0) {
            return returnJson(checkMap);
        }
        Map<String, Object> resultMap = null;
        try {
            AldUserShareVo v1 = UserShareUtil.requestParamFormat(vo);
            Map<String, Object> entityMap = aldUserShareService.queryPageDataList(v1);
            resultMap = UserShareUtil.responseDataListFormat(entityMap);
        } catch (Exception e) {
            LOG.error("程序处理异常: {}", e);
            return jsonFail("系统处理异常");
        }
        return resultJson2(resultMap, new Object[]{date});
    }

}
