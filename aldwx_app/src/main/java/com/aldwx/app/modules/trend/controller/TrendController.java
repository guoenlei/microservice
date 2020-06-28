package com.aldwx.app.modules.trend.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.modules.ad.service.AdMonitorService;
import com.aldwx.app.modules.trend.service.TrendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 趋势分析
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldstat/app/trend")
public class TrendController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(TrendController.class);

    @Autowired
    private TrendService trendService;




}
