package com.aldwx.app.modules.demo.service.impl;

import com.aldwx.app.modules.demo.dao.stat.DemoStatDao;
import com.aldwx.app.modules.demo.service.DemoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 *
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class DemoServiceImpl implements DemoService {


    private DemoStatDao demoStatDao;


    public Map<String, Object> queryData() {

        return null;
    }
}
