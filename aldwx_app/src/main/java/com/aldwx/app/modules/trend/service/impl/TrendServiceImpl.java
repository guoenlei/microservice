package com.aldwx.app.modules.trend.service.impl;

import com.aldwx.app.common.base.BaseMethod;
import com.aldwx.app.common.config.ConfigurationManager;
import com.aldwx.app.common.constants.Constants;
import com.aldwx.app.common.exception.AldException;
import com.aldwx.app.common.util.DateUtil;
import com.aldwx.app.modules.trend.bean.Trend;
import com.aldwx.app.modules.trend.dao.game.TrendGameDao;
import com.aldwx.app.modules.trend.dao.stat.TrendStatDao;
import com.aldwx.app.modules.trend.entity.TrendEntity;
import com.aldwx.app.modules.trend.service.TrendService;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 趋势分析
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class TrendServiceImpl extends BaseMethod implements TrendService {

    private static final Logger LOG = LoggerFactory.getLogger(TrendServiceImpl.class);

    @Autowired
    private TrendStatDao trendStatDao;
    @Autowired
    private TrendGameDao trendGameDao;



    /**
     * 概况 - 今日概况数据 - 概览
     * @param trend
     * @return
     */
    @Override
    public List<TrendEntity> queryBasicDataView(Trend trend) {
        String appType = trend.getAppType();
        List<TrendEntity> trendEntityList = null;
        if(isStatApp(appType)) {
            if (trend.getDate().equals("1")||trend.getDate().equals("2")){
                trendEntityList = trendStatDao.queryDataByDate(trend);
            }else {
                trendEntityList = trendStatDao.queryDataByDate(trend);
            }
        } else {
            if (trend.getDate().equals("1")||trend.getDate().equals("2")){
                trendEntityList = trendGameDao.queryDataByDate(trend);
            }else {
                trendEntityList = trendGameDao.queryDataByDate(trend);
            }
        }
        if(null == trendEntityList && trendEntityList.size() == 0) {
            trendEntityList = Lists.newArrayList();
            trendEntityList.add(new TrendEntity());
        }

        return trendEntityList;
    }


    /**
     * 概况 - 获取基础指标数据 - 折线图
     * @param trend
     * @return
     */
    @Override
    public List<List<TrendEntity>>  queryBasicDataChart(Trend trend) {
        String appType = trend.getAppType();
        List<List<TrendEntity>> total=new ArrayList<>();
        List<TrendEntity> trendEntityList = null;
        List<TrendEntity> trendEntityList1 = null;
        List<TrendEntity> trendEntityList2 = null;
        // 如果是小程序，则isStatApp为true
        if(isStatApp(appType)) {
            //今日查询时 获取今日和昨日的数据
            if(trend.getDate().equals("1")||trend.getDate().equals("2") ) {
                //获取今日 昨日数据
                trendEntityList = trendStatDao.queryHourDataByDate(trend);
                trendEntityList1 = trendStatDao.queryDataByDate(trend);
                total.add(trendEntityList);
                total.add(trendEntityList1);
            } else {
                //获取7日 30日数据
                trendEntityList = trendStatDao.queryDataByDates(trend);
                trendEntityList2 = trendStatDao.queryDataByDateRange(trend);
                total.add(trendEntityList);
                total.add(trendEntityList2);
            }
        } else {
            if(trend.getDate().equals("1")||trend.getDate().equals("2") ) {
                trendEntityList = trendGameDao.queryHourDataByDate(trend);
                trendEntityList1 = trendGameDao.queryDataByDate(trend);
                total.add(trendEntityList);
                total.add(trendEntityList1);
            } else {
                trendEntityList = trendGameDao.queryDataByDates(trend);
                trendEntityList2 = trendGameDao.queryDataByDateRange(trend);
                total.add(trendEntityList);
                total.add(trendEntityList2);
            }
        }
        return total;
    }




}
