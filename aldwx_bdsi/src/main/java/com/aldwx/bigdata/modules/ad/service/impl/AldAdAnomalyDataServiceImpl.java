package com.aldwx.bigdata.modules.ad.service.impl;

import com.aldwx.bigdata.common.constants.Constants;
import com.aldwx.bigdata.common.page.PageUtil;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.modules.ad.dao.cluster.AldAdAnomalyDataClusterDao;
import com.aldwx.bigdata.modules.ad.dao.master.AldAdAnomalyDataMasterDao;
import com.aldwx.bigdata.modules.ad.dao.master.AldPreventCheatMaterDao;
import com.aldwx.bigdata.modules.ad.dao.presto.AldAdAnomalyDataPrestoDao;
import com.aldwx.bigdata.modules.ad.entity.AldAdAnomalyDataEntity;
import com.aldwx.bigdata.modules.ad.entity.AldPreventCheat;
import com.aldwx.bigdata.modules.ad.service.AldAdAnomalyDataService;
import com.aldwx.bigdata.modules.ad.vo.AldAdAnomalyDataVo;
import com.aldwx.bigdata.modules.dbinfo.dao.master.AldDbInfoDao;

import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional(readOnly = true)
public class AldAdAnomalyDataServiceImpl implements AldAdAnomalyDataService {

    private static final Logger LOG = LoggerFactory.getLogger(AldAdAnomalyDataServiceImpl.class);

    @Autowired
    private AldAdAnomalyDataClusterDao aldAdAnomalyDataClusterDao;
    @Autowired
    private AldAdAnomalyDataMasterDao aldAdAnomalyDataMasterDao;
    @Autowired
    private AldAdAnomalyDataPrestoDao aldAdAnomalyDataPrestoDao;
    @Autowired
    private AldDbInfoDao aldDbInfoDao;
    @Autowired
    private AldPreventCheatMaterDao aldPreventCheatMaterDao;


    /**
     * 获取异常检测数据列表
     * @param aldAdAnomalyDataVo
     * @return
     */
    @Override
    public Map<String, Object> queryPageDataList(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldPreventCheat> aldPreventCheatList = aldPreventCheatMaterDao.seleteEntityBy(aldAdAnomalyDataVo.getAppKey());
        if(null != aldPreventCheatList && aldPreventCheatList.size() > 0) {
            aldAdAnomalyDataVo.setDiffTimeType(aldPreventCheatList.get(0).getDiffTimeType());
            aldAdAnomalyDataVo.setAuthTimes(aldPreventCheatList.get(0).getOauthCountType());
            aldAdAnomalyDataVo.setIpclkCountType(aldPreventCheatList.get(0).getIpclkCountType());
        } else {
            return resultMap;
        }

//        AldAdAnomalyDataEntity aldAdAnomalyDataEntity = this.aldAdAnomalyDataMasterDao.queryAnyDayAnomalyCountBy(aldAdAnomalyDataVo);

        if(null != aldAdAnomalyDataVo) {
            String date = aldAdAnomalyDataVo.getDate();
            if(date.contains(Constants.FLAG_01)) {
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                //判断日期是否包含今天
                boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
                //日期转换为数组
                aldAdAnomalyDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
                if(false) {
//                    //重置分页开始和结束，用于合并数据和排序
//                    aldAdAnomalyDataVo = GameEventDataUtil.setPageRow(aldAdAnomalyDataVo);
//                    LOG.info("查询presto和mysql数据，重置startRow和endRow，startRow:{}, endRow:{}",
//                            aldGameEventParamVo.getStartRow(), aldGameEventParamVo.getEndRow());
//
//                    //关联查询
//                    Map<String, Object> map1 = this.queryDaysDataBy(aldGameEventParamVo);
//                    Map<String, Object> map2 = this.queryTodayDataBy(aldGameEventParamVo);
//                    List<AldGameEventParamEntity> aldGameEventParamEntityList1 = (List<AldGameEventParamEntity>)map1.get("data");
//                    List<AldGameEventParamEntity> aldGameEventParamEntityList2 = (List<AldGameEventParamEntity>)map2.get("data");
//                    //前十页聚合处理
//                    List<AldGameEventParamEntity> aldGameEventParamEntityList  = GameEventDataUtil.mergePage(aldGameEventParamEntityList1,
//                            aldGameEventParamEntityList2, aldGameEventParamVo);
//                    long count1 = (long)map1.get("count");
//                    long count2 = (long)map2.get("count");
//                    long dataCount = (long)Arith.sub(Arith.add(count1, count2), aldGameEventParamEntityList.size());
//
//                    resultMap.put("data", aldGameEventParamEntityList);
//                    resultMap.put("count", dataCount);
                } else {
                    //查询多天
                    resultMap = this.queryDaysDataBy(aldAdAnomalyDataVo);
                }
            }else if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                aldAdAnomalyDataVo.setDate(DateUtil.getTodayDate());
                resultMap = this.queryTodayDataBy(aldAdAnomalyDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                aldAdAnomalyDataVo.setDate(DateUtil.getYesterday());
                resultMap = this.queryYesterdayDataBy(aldAdAnomalyDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                resultMap = this.query7DayDataBy(aldAdAnomalyDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                resultMap = query30DayDataBy(aldAdAnomalyDataVo);
            }
        }
        return resultMap;
    }


    /**
     * 获取今天的数据列表
     * @param aldAdAnomalyDataVo
     * @return
     */
    @Override
    public Map<String, Object> queryTodayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        return this.queryDayAnomalyDataBy(aldAdAnomalyDataVo);
    }


    /**
     * 获取昨天的数据列表
     * @param aldAdAnomalyDataVo
     * @return
     */
    @Override
    public Map<String, Object> queryYesterdayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        return this.queryDayAnomalyDataBy(aldAdAnomalyDataVo);
    }


    /**
     * 获取最近7天的数据
     * @param aldAdAnomalyDataVo
     * @return
     */
    @Override
    public Map<String, Object> query7DayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        return this.queryDayAnomalyDataBy(aldAdAnomalyDataVo);
    }


    /**
     * 获取最近30天的数据
     * @param aldAdAnomalyDataVo
     * @return
     */
    @Override
    public Map<String, Object> query30DayDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        return this.queryDayAnomalyDataBy(aldAdAnomalyDataVo);
    }


    /**
     * 获取任意时间段的数据
     * @param aldAdAnomalyDataVo
     * @return
     */
    @Override
    public Map<String, Object> queryDaysDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        return this.queryAnyDaysAnomalyDataBy(aldAdAnomalyDataVo);
    }


    /**
     * 反作弊图表
     * 折线图和总异常统计
     * @param aldAdAnomalyDataVo
     * @return
     */
    @Override
    public Map<String, Object> cheatDataCount(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdAnomalyDataEntity> aldAdAnomalyDataEntityList = Lists.newArrayList();
        List<AldPreventCheat> aldPreventCheatList = aldPreventCheatMaterDao.seleteEntityBy(aldAdAnomalyDataVo.getAppKey());
        //图表
        if(null != aldPreventCheatList && aldPreventCheatList.size() > 0) {
            aldAdAnomalyDataVo.setDiffTimeType(aldPreventCheatList.get(0).getDiffTimeType());
            aldAdAnomalyDataVo.setIpclkCountType(aldPreventCheatList.get(0).getIpclkCountType());
            aldAdAnomalyDataVo.setAuthTimes(aldPreventCheatList.get(0).getOauthCountType());
        } else {
            resultMap.put("data", aldAdAnomalyDataEntityList);
            return resultMap;
        }
        //折线图信息
        if(null != aldAdAnomalyDataVo) {
            String date = aldAdAnomalyDataVo.getDate();
            if(date.contains(Constants.FLAG_01)) {
                date = date.replaceAll("\\s*", "");
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                //日期转换为数组
                aldAdAnomalyDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
                if(dates[0].equals(dates[1])) {
                    //不对今天数据做处理
                    if(dates[0].equals(DateUtil.getTodayDate())) {
                        resultMap.put("data", aldAdAnomalyDataEntityList);
                        return resultMap;
                    } else {
                        List<String> dateList = Lists.newArrayList();
                        dateList.add(dates[0]);
                        aldAdAnomalyDataVo.setList(dateList);
                        aldAdAnomalyDataEntityList = this.queryAnyDaysAnomalyChartDataBy(aldAdAnomalyDataVo);
                    }
                } else {
                    aldAdAnomalyDataEntityList = this.queryAnyDaysAnomalyChartDataBy(aldAdAnomalyDataVo);
                }
            }else if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                resultMap.put("data", aldAdAnomalyDataEntityList);
                return resultMap;
            } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                List<String> dateList = Lists.newArrayList();
                dateList.add(DateUtil.getYesterday());
                aldAdAnomalyDataVo.setList(dateList);
                aldAdAnomalyDataEntityList = this.queryAnyDaysAnomalyChartDataBy(aldAdAnomalyDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                aldAdAnomalyDataEntityList = this.queryAnyDaysAnomalyChartDataBy(aldAdAnomalyDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                aldAdAnomalyDataEntityList = queryAnyDaysAnomalyChartDataBy(aldAdAnomalyDataVo);
            }
        }
        resultMap.put("data", aldAdAnomalyDataEntityList);
        return resultMap;
    }

    @Override
    public List<AldAdAnomalyDataEntity> queryDayAnomalyChartDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        return this.aldAdAnomalyDataMasterDao.queryDayAnomalyChartDataBy(aldAdAnomalyDataVo);
    }

    @Override
    public List<AldAdAnomalyDataEntity> queryAnyDaysAnomalyChartDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        return this.aldAdAnomalyDataMasterDao.queryAnyDaysAnomalyChartDataBy(aldAdAnomalyDataVo);
    }


    /**
     * 获取天 7天 30天
     * @param aldAdAnomalyDataVo
     * @return
     */
    public Map<String, Object> queryDayAnomalyDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdAnomalyDataEntity> aldAdAnomalyDataEntityList = Lists.newArrayList();
        Page<?> page = null;
        if(isCluster(aldAdAnomalyDataVo)) {
            page = PageUtil.startPage(aldAdAnomalyDataVo.getCurrentPage(), aldAdAnomalyDataVo.getTotal(),
                    aldAdAnomalyDataVo.getIsDownload());
            if(aldAdAnomalyDataVo.getModule().equals("1")) {
                aldAdAnomalyDataEntityList = this.aldAdAnomalyDataClusterDao.queryDayAnomalyAllDataBy(aldAdAnomalyDataVo);
            } else {
                aldAdAnomalyDataEntityList = this.aldAdAnomalyDataClusterDao.queryDayAnomalyTimeOrIpDataBy(aldAdAnomalyDataVo);
            }
        } else {
            page = PageUtil.startPage(aldAdAnomalyDataVo.getCurrentPage(), aldAdAnomalyDataVo.getTotal(),
                    aldAdAnomalyDataVo.getIsDownload());
            if(aldAdAnomalyDataVo.getModule().equals("1")) {
                //总览列表
                aldAdAnomalyDataEntityList = this.aldAdAnomalyDataMasterDao.queryDayAnomalyAllDataBy(aldAdAnomalyDataVo);
            } else {
                //不是总览的列表
                aldAdAnomalyDataEntityList = this.aldAdAnomalyDataMasterDao.queryDayAnomalyTimeOrIpDataBy(aldAdAnomalyDataVo);
            }
        }

//        AldAdAnomalyDataEntity aldAdAnomalyDataEntity = this.aldAdAnomalyDataMasterDao.queryAnyDayAnomalyCountBy(aldAdAnomalyDataVo);

        resultMap.put("data",  aldAdAnomalyDataEntityList);
        resultMap.put("count", page.getTotal());
        LOG.info("总共有:"+page.getTotal()+"条数据,实际返回:"+aldAdAnomalyDataEntityList.size()+"条数据!");
        return resultMap;
    }


    /**
     * 获取指定天
     * @param aldAdAnomalyDataVo
     * @return
     */
    public Map<String, Object> queryAnyDaysAnomalyDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdAnomalyDataEntity> aldAdAnomalyDataEntityList = Lists.newArrayList();
        Page<?> page = null;
        if(isCluster(aldAdAnomalyDataVo)) {
            page = PageUtil.startPage(aldAdAnomalyDataVo.getCurrentPage(), aldAdAnomalyDataVo.getTotal(),
                    aldAdAnomalyDataVo.getIsDownload());
            if(aldAdAnomalyDataVo.getModule().equals("1")) {
                aldAdAnomalyDataEntityList = this.aldAdAnomalyDataClusterDao.queryAnyDaysAnomalyAllDataBy(aldAdAnomalyDataVo);
            } else {
                aldAdAnomalyDataEntityList = this.aldAdAnomalyDataClusterDao.queryAnyDaysAnomalyTimeOrIpDataBy(aldAdAnomalyDataVo);
            }
        } else {
            page = PageUtil.startPage(aldAdAnomalyDataVo.getCurrentPage(), aldAdAnomalyDataVo.getTotal(),
                    aldAdAnomalyDataVo.getIsDownload());
            if(aldAdAnomalyDataVo.getModule().equals("1")) {
                //总览列表
                aldAdAnomalyDataEntityList = this.aldAdAnomalyDataMasterDao.queryAnyDaysAnomalyAllDataBy(aldAdAnomalyDataVo);
            } else {
                //不是总览的列表
                aldAdAnomalyDataEntityList = this.aldAdAnomalyDataMasterDao.queryAnyDaysAnomalyTimeOrIpDataBy(aldAdAnomalyDataVo);
            }
        }

//        AldAdAnomalyDataEntity aldAdAnomalyDataEntity = this.aldAdAnomalyDataMasterDao.queryAnyDayAnomalyCountBy(aldAdAnomalyDataVo);

        resultMap.put("data",  aldAdAnomalyDataEntityList);
        resultMap.put("count", page.getTotal());
        LOG.info("总共有:"+page.getTotal()+"条数据,实际返回:"+aldAdAnomalyDataEntityList.size()+"条数据!");
        return resultMap;
    }


    @Override
    public Long query30DayCountDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        return null;
    }

    @Override
    public Long query7DayCountDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        return null;
    }

    @Override
    public Long queryDaysCountDataBy(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        return null;
    }

    @Override
    public boolean isCluster(AldAdAnomalyDataVo aldAdAnomalyDataVo) {
        return false;
    }

}
