package com.aldwx.app.modules.index.controller;

import com.aldwx.app.common.base.BaseController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页小程序列表
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldstat/app/index")
public class IndexController extends BaseController {






    /**
     * 根据用户uid和时间
     * 获取小程序列表数据
     * @param uid
     * @param date
     * @return
     */
    public String queryAppList(int uid, String date) {



        return null;
    }

}
