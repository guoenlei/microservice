package com.aldwx.app.modules.demo.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.modules.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 调用测试Demo类
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldstat/app/demo")
public class DemoController extends BaseController {


    @Autowired
    private DemoService demoService;


    @ResponseBody
    @RequestMapping(value="/data", method = RequestMethod.POST)
    public String queryDemoData() {

        return null;
    }




}
