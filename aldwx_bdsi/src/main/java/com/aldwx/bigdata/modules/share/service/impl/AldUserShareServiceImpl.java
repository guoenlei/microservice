package com.aldwx.bigdata.modules.share.service.impl;

import com.aldwx.bigdata.common.constants.Constants;
import com.aldwx.bigdata.common.page.PageUtil;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.modules.dbinfo.dao.master.AldDbInfoDao;
import com.aldwx.bigdata.modules.share.dao.cluster.AldUserShareClusterDao;
import com.aldwx.bigdata.modules.share.dao.master.AldUserShareMasterDao;
import com.aldwx.bigdata.modules.share.dao.presto.AldUserSharePrestoDao;
import com.aldwx.bigdata.modules.share.entity.AldUserShareEntity;
import com.aldwx.bigdata.modules.share.service.AldUserShareService;
import com.aldwx.bigdata.modules.share.vo.AldUserShareVo;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 用户分享
 */
@Service
@Transactional(readOnly = true)
public class AldUserShareServiceImpl implements AldUserShareService {

    private static final Logger LOG = LoggerFactory.getLogger(AldUserShareServiceImpl.class);

    @Autowired
    private AldUserShareMasterDao aldUserShareMasterDao;
    @Autowired
    private AldUserShareClusterDao aldUserShareClusterDao;
    @Autowired
    private AldUserSharePrestoDao aldUserSharePrestoDao;
    @Autowired
    private AldDbInfoDao aldDbInfoDao;


    /**
     * 获取分享用户列表
     * @param
     * @return
     */
    @Override
    public Map<String, Object> queryPageDataList(AldUserShareVo aldUserShareVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != aldUserShareVo) {
            String date = aldUserShareVo.getDate();
            if(date.contains(Constants.FLAG_01)) {
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                //判断日期是否包含今天
                boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
                aldUserShareVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
//                if(isContains) {
                if(false) {
                    resultMap = this.queryDaysDataBy(aldUserShareVo);
                } else {
                    resultMap = this.queryDaysDataBy(aldUserShareVo);
                }
            } else {
                if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                    resultMap = this.queryTodayDataBy(aldUserShareVo);
                } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                    resultMap = this.queryYesterdayDataBy(aldUserShareVo);
                } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                    aldUserShareVo.setList(DateUtil.getBeforeDate(7));
                    resultMap = this.query7DayDataBy(aldUserShareVo);
                } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                    aldUserShareVo.setList(DateUtil.getBeforeDate(30));
                    resultMap = this.query30DayDataBy(aldUserShareVo);
                }
            }
        }
        return resultMap;
    }


    @Override
    public Map<String, Object> queryTodayDataBy(AldUserShareVo aldUserShareVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldUserShareEntity> aldUserShareEntityList = null;
        Page<?> page = null;
        if(isCluster(aldUserShareVo)) {
            page = startPage(aldUserShareVo);
            aldUserShareEntityList = this.aldUserShareClusterDao.queryTodayDataBy(aldUserShareVo);
        } else {
            page = startPage(aldUserShareVo);
            aldUserShareEntityList = this.aldUserShareMasterDao.queryTodayDataBy(aldUserShareVo);
        }
        LOG.info("查询今天分享数据, 共查询出{}条数据", page.getTotal());
        resultMap.put("count", page.getTotal());
        resultMap.put("data", aldUserShareEntityList);
        return resultMap;
    }


    @Override
    public Map<String, Object> queryYesterdayDataBy(AldUserShareVo aldUserShareVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldUserShareEntity> aldUserShareEntityList = null;
        Page<?> page = null;
        if(isCluster(aldUserShareVo)) {
            page = startPage(aldUserShareVo);
            aldUserShareEntityList = this.aldUserShareClusterDao.queryYesterdayDataBy(aldUserShareVo);
        } else {
            page = startPage(aldUserShareVo);
            aldUserShareEntityList = this.aldUserShareMasterDao.queryYesterdayDataBy(aldUserShareVo);
        }
        LOG.info("查询昨天分享数据, 共查询出{}条数据", page.getTotal());
        resultMap.put("count", page.getTotal());
        resultMap.put("data", aldUserShareEntityList);
        return resultMap;
    }

    @Override
    public Map<String, Object> query7DayDataBy(AldUserShareVo aldUserShareVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldUserShareEntity> aldUserShareEntityList = null;
        Page<?> page = null;
        if(isCluster(aldUserShareVo)) {
            page = startPage(aldUserShareVo);
            aldUserShareEntityList = this.aldUserShareClusterDao.queryDaysDataBy(aldUserShareVo);
        } else {
            page = startPage(aldUserShareVo);
            aldUserShareEntityList = this.aldUserShareMasterDao.queryDaysDataBy(aldUserShareVo);
        }
        LOG.info("查询最近7天分享数据, 共查询出{}条数据", page.getTotal());
        resultMap.put("count", page.getTotal());
        resultMap.put("data", aldUserShareEntityList);
        return resultMap;
    }

    @Override
    public Map<String, Object> query30DayDataBy(AldUserShareVo aldUserShareVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldUserShareEntity> aldUserShareEntityList = null;
        Page<?> page = null;
        if(isCluster(aldUserShareVo)) {
            page = startPage(aldUserShareVo);
            aldUserShareEntityList = this.aldUserShareClusterDao.queryDaysDataBy(aldUserShareVo);
        } else {
            page = startPage(aldUserShareVo);
            aldUserShareEntityList = this.aldUserShareMasterDao.queryDaysDataBy(aldUserShareVo);
        }
        LOG.info("查询最近30天分享数据, 共查询出{}条数据", page.getTotal());
        resultMap.put("count", page.getTotal());
        resultMap.put("data", aldUserShareEntityList);
        return resultMap;
    }


    @Override
    public Map<String, Object> queryDaysDataBy(AldUserShareVo aldUserShareVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldUserShareEntity> aldUserShareEntityList = null;
        Page<?> page = null;
        if(isCluster(aldUserShareVo)) {
            page = startPage(aldUserShareVo);
            aldUserShareEntityList = this.aldUserShareClusterDao.queryDaysDataBy(aldUserShareVo);
        } else {
            page = startPage(aldUserShareVo);
            aldUserShareEntityList = this.aldUserShareMasterDao.queryDaysDataBy(aldUserShareVo);
        }
        LOG.info("查询指定日期分享数据, 共查询出{}条数据", page.getTotal());
        resultMap.put("count", page.getTotal());
        resultMap.put("data", aldUserShareEntityList);
        return resultMap;
    }

    @Override
    public Long query30DayCountDataBy(AldUserShareVo aldUserShareVo) {
        return null;
    }

    @Override
    public Long query7DayCountDataBy(AldUserShareVo aldUserShareVo) {
        return null;
    }

    @Override
    public Long queryDaysCountDataBy(AldUserShareVo aldUserShareVo) {
        return null;
    }

    @Override
    public boolean isCluster(AldUserShareVo aldUserShareVo) {
        return false;
    }


    private Page<?> startPage(AldUserShareVo aldUserShareVo) {
        return PageUtil.startPage(Integer.parseInt(aldUserShareVo.getCurrentPage()),
                Integer.parseInt(aldUserShareVo.getTotal()), aldUserShareVo.getIsDownload());
    }
}
