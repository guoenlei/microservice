package com.aldwx.app.modules.index.service.impl;

import com.aldwx.app.common.base.BaseMethod;
import com.aldwx.app.modules.index.service.IndexService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class IndexServiceImpl extends BaseMethod implements IndexService {


    @Override
    public Object queryDataByDate(Object object) {
        return null;
    }

    @Override
    public List queryPageDataList(Object object) {
        return null;
    }

    @Override
    public Object queryTodayDataBy(Object object) {
        return null;
    }

    @Override
    public Object queryYesterdayDataBy(Object object) {
        return null;
    }

    @Override
    public Object query7DayDataBy(Object object) {
        return null;
    }

    @Override
    public Object query30DayDataBy(Object object) {
        return null;
    }

    @Override
    public Object queryDaysDataBy(Object object) {
        return null;
    }

    @Override
    public Long query30DayCountDataBy(Object object) {
        return null;
    }

    @Override
    public Long query7DayCountDataBy(Object object) {
        return null;
    }

    @Override
    public Long queryDaysCountDataBy(Object object) {
        return null;
    }
}
