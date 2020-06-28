package com.aldwx.bigdata.modules.city.service.impl;

import com.aldwx.bigdata.common.constants.Constants;
import com.aldwx.bigdata.common.page.PageUtil;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.modules.city.constants.CityConstants;
import com.aldwx.bigdata.modules.city.dao.cluster.AldCityClusterDao;
import com.aldwx.bigdata.modules.city.dao.master.AldCityMasterDao;
import com.aldwx.bigdata.modules.city.dao.presto.AldCityPrestoDao;
import com.aldwx.bigdata.modules.city.entity.AldCityEntity;
import com.aldwx.bigdata.modules.city.service.AldCityService;
import com.aldwx.bigdata.modules.city.util.CityUtils;
import com.aldwx.bigdata.modules.city.vo.AldCityVo;
import com.aldwx.bigdata.modules.dbinfo.dao.master.AldDbInfoDao;
import com.aldwx.bigdata.modules.gameEvent.entity.AldGameEventParamEntity;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 区域分析 - 城市
 */
@Service
@Transactional(readOnly = true)
public class AldCityServiceImpl implements AldCityService {

    private static final Logger LOG = LoggerFactory.getLogger(AldCityServiceImpl.class);

    @Autowired
    private AldCityMasterDao aldCityMasterDao;
    @Autowired
    private AldCityClusterDao aldCityClusterDao;
    @Autowired
    private AldCityPrestoDao aldCityPrestoDao;
    @Autowired
    private AldDbInfoDao aldDbInfoDao;


    /**
     * 获取城市数据 list
     * @param vo
     * @return
     */
    @Override
    public Map<String, Object> queryPageDataList(AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        String date = vo.getDate();
        if(date.contains(CityConstants.FLAG_01)) {
            //时间区间数据处理
            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
            boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
            vo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
//            if(isContains) {
            if(false) {
                //TODO
            } else {
                resultMap = this.queryDaysDataBy(vo);
            }
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_TODAY_TIME)) {
            resultMap = this.queryTodayDataBy(vo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
            resultMap = this.queryYesterdayDataBy(vo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
            resultMap = this.query7DayDataBy(vo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
            resultMap = this.query30DayDataBy(vo);
        }

        return resultMap;
    }

    /**
     * 获取城市TopN
     * @param vo
     * @return
     */
    @Override
    public Map<String, Object> queryCityDataTop(AldCityVo vo) {
        Map<String, Object> resutMap = Maps.newConcurrentMap();
        String date = vo.getDate();
        if(date.contains(CityConstants.FLAG_01)) {
            //时间区间数据处理
            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
            boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
            vo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
//            if(isContains) {
            if(false) {
                Map<String, Object> map1 = this.queryDaysDataTop(vo);
                Map<String, Object> map2 = this.queryTodayDataTop(vo);
                List<AldCityEntity> aldCityEntityList1 = (List<AldCityEntity>)map1.get(CityConstants.TOP_N);
                Long otherCount1 = (Long)map1.get(CityConstants.TOP_OTHER);
                List<AldCityEntity> aldCityEntityList2 = (List<AldCityEntity>)map2.get(CityConstants.TOP_N);
                Long otherCount2 = (Long)map2.get(CityConstants.TOP_OTHER);
                //合并数据
                resutMap = CityUtils.mergeTopData(aldCityEntityList1, aldCityEntityList2, otherCount1, otherCount2, vo);
            } else {
                resutMap = this.queryDaysDataTop(vo);
            }
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_TODAY_TIME)) {
            resutMap = this.queryTodayDataTop(vo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
            resutMap = this.queryYesterdayDataTop(vo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
            resutMap = this.query7DayDataTop(vo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
            resutMap = this.query30DayDataTop(vo);
        }
        return resutMap;
    }



    /**
     * 获取城市City chart
     * @param vo
     * @return
     */
    @Override
    public Map<String, Object> queryCityDataChart(AldCityVo vo) {
        Map<String, Object> resutMap = Maps.newConcurrentMap();
        String date = vo.getDate();
        if(date.contains(CityConstants.FLAG_01)) {
            //时间区间数据处理
            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
            boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
            vo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
//            if(isContains) {
            if(false) {
//                Map<String, Object> map1 = this.queryDaysDataChart(vo);
//                Map<String, Object> map2 = this.queryTodayDataChart(vo);
                Map<String, Object> map1 = this.queryTodayDataChart(vo);
                Map<String, Object> map2 = this.queryYesterdayDataChart(vo);
                List<AldCityEntity> aldCityEntityList1 = (List<AldCityEntity>)map1.get("data");
                List<AldCityEntity> aldCityEntityList2 = (List<AldCityEntity>)map2.get("data");
                //合并数据
                resutMap = CityUtils.mergeChartData(aldCityEntityList1, aldCityEntityList2, vo);
            } else {
                resutMap = this.queryDaysDataChart(vo);
            }
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_TODAY_TIME)) {
            resutMap = this.queryTodayDataChart(vo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
            resutMap = this.queryYesterdayDataChart(vo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
            resutMap = this.query7DayDataChart(vo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
            resutMap = this.query30DayDataChart(vo);
        }
        return resutMap;
    }


    /**
     * 获取今天列表数据 list
     * @param aldCityVo
     * @return
     */
    @Override
    public Map<String, Object> queryTodayDataBy(AldCityVo aldCityVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldCityEntity> aldCityEntityList = null;
        Page<?> page = null;
        if(isCluster(aldCityVo)) {
            //从库
            page = PageUtil.startPage(aldCityVo.getCurrentPage(), aldCityVo.getTotal(), aldCityVo.getIsDownload());
            aldCityEntityList = this.aldCityClusterDao.queryTodayDataBy(aldCityVo);
        } else {
            //主库
            page = PageUtil.startPage(aldCityVo.getCurrentPage(), aldCityVo.getTotal(), aldCityVo.getIsDownload());
            aldCityEntityList = this.aldCityMasterDao.queryTodayDataBy(aldCityVo);
        }
        resultMap.put("data", aldCityEntityList);
        resultMap.put("count", page.getTotal());
        return resultMap;
    }


    /**
     * 获取昨天列表数据 list
     * @param aldCityVo
     * @return
     */
    @Override
    public Map<String, Object> queryYesterdayDataBy(AldCityVo aldCityVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldCityEntity> aldCityEntityList = null;
        Page<?> page = null;
        if(isCluster(aldCityVo)) {
            //从库
            page = PageUtil.startPage(aldCityVo.getCurrentPage(), aldCityVo.getTotal(), aldCityVo.getIsDownload());
            aldCityEntityList = this.aldCityClusterDao.queryYesterdayDataBy(aldCityVo);
        } else {
            //主库
            page = PageUtil.startPage(aldCityVo.getCurrentPage(), aldCityVo.getTotal(), aldCityVo.getIsDownload());
            aldCityEntityList = this.aldCityMasterDao.queryYesterdayDataBy(aldCityVo);
        }
        resultMap.put("data", aldCityEntityList);
        resultMap.put("count", page.getTotal());
        return resultMap;
    }


    /**
     * 获取最近7天列表数据 list
     * @param aldCityVo
     * @return
     */
    @Override
    public Map<String, Object> query7DayDataBy(AldCityVo aldCityVo) {
        List<AldCityEntity> aldCityEntityList = Lists.newArrayList();
        Map<String, Object> resultMap = null;
        Long dataCount = 0L;
        if(isCluster(aldCityVo)) {
            dataCount = this.aldCityPrestoDao.query7DayCountDataBy(aldCityVo);
            if(dataCount > 0L) {
                aldCityEntityList = this.aldCityPrestoDao.query7DayDataBy(aldCityVo);
            }
        } else {
            if(dataCount > 0L) {
                dataCount = this.aldCityPrestoDao.query7DayCountDataBy(aldCityVo);
            }
            aldCityEntityList = this.aldCityPrestoDao.query7DayDataBy(aldCityVo);
        }
        resultMap.put("count", dataCount);
        resultMap.put("data", aldCityEntityList);

        return resultMap;
    }

    /**
     * 获取最近30天列表数据 list
     * @param aldCityVo
     * @return
     */
    @Override
    public Map<String, Object> query30DayDataBy(AldCityVo aldCityVo) {
        List<AldCityEntity> aldCityEntityList = Lists.newArrayList();
        Map<String, Object> resultMap = null;
        Long dataCount = 0L;
        if(isCluster(aldCityVo)) {
            dataCount = this.aldCityPrestoDao.query30DayCountDataBy(aldCityVo);
            if(dataCount > 0L) {
                aldCityEntityList = this.aldCityPrestoDao.query30DayDataBy(aldCityVo);
            }
        } else {
            if(dataCount > 0L) {
                dataCount = this.aldCityPrestoDao.query30DayCountDataBy(aldCityVo);
            }
            aldCityEntityList = this.aldCityPrestoDao.query30DayDataBy(aldCityVo);
        }
        resultMap.put("count", dataCount);
        resultMap.put("data", aldCityEntityList);

        return resultMap;
    }


    /**
     * 获取指定日期的列表数据
     * @param aldCityVo
     * @return
     */
    @Override
    public Map<String, Object> queryDaysDataBy(AldCityVo aldCityVo) {
        List<AldCityEntity> aldCityEntityList = Lists.newArrayList();
        Map<String, Object> resultMap = null;
        Long dataCount = 0L;
        if(isCluster(aldCityVo)) {
            dataCount = this.aldCityPrestoDao.queryDaysCountDataBy(aldCityVo);
            if(dataCount > 0L) {
                aldCityEntityList = this.aldCityPrestoDao.queryDaysDataBy(aldCityVo);
            }
        } else {
            if(dataCount > 0L) {
                dataCount = this.aldCityPrestoDao.queryDaysCountDataBy(aldCityVo);
            }
            aldCityEntityList = this.aldCityPrestoDao.queryDaysDataBy(aldCityVo);
        }
        resultMap.put("count", dataCount);
        resultMap.put("data", aldCityEntityList);

        return resultMap;
    }

    @Override
    public Long query30DayCountDataBy(AldCityVo aldCityVo) {
        return null;
    }

    @Override
    public Long query7DayCountDataBy(AldCityVo aldCityVo) {
        return null;
    }

    @Override
    public Long queryDaysCountDataBy(AldCityVo aldCityVo) {
        return null;
    }



    /**
     * 获取今天城市top
     * @param vo
     * @return
     */
    @Override
    public Map<String, Object> queryTodayDataTop(AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldCityEntity> aldCityEntityList = null;
        Long sumData = 0L;
        if(isCluster(vo)) {
            //从库
        } else {
            aldCityEntityList = this.aldCityMasterDao.queryTodayDataTop(vo);
            sumData = this.aldCityMasterDao.queryTodayDataSumTop(vo);
        }
        if(null != aldCityEntityList && aldCityEntityList.size() > 0) {
            int sumTopN = CityUtils.calculateTopN(aldCityEntityList, vo);
            resultMap.put(CityConstants.TOP_N, aldCityEntityList);
            resultMap.put(CityConstants.TOP_OTHER, (NumberUtils.toLong(sumData+"") - sumTopN));
        }
        return resultMap;
    }



    /**
     * 获取昨天城市top
     * @param vo
     * @return
     */
    @Override
    public Map<String, Object> queryYesterdayDataTop(AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldCityEntity> aldCityEntityList = null;
        Long sumData = 0L;
        if(isCluster(vo)) {
            //从库
        } else {
            aldCityEntityList = this.aldCityMasterDao.queryYesterdayDataTop(vo);
            sumData = this.aldCityMasterDao.queryYesterdayDataSumTop(vo);
        }
        if(null != aldCityEntityList && aldCityEntityList.size() > 0) {
            int sumTopN = CityUtils.calculateTopN(aldCityEntityList, vo);
            resultMap.put(CityConstants.TOP_N, aldCityEntityList);
            resultMap.put(CityConstants.TOP_OTHER, (NumberUtils.toLong(sumData+"") - sumTopN));
        }
        return resultMap;
    }



    /**
     * 最近7天 TopN
     * @param vo
     * @return
     */
    public Map<String, Object> query7DayDataTop(AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldCityEntity> aldCityEntityList = null;
        Long sumData = 0L;
        if(isCluster(vo)) {
            //从库
        } else {
            aldCityEntityList = this.aldCityPrestoDao.query7DayDataTop(vo);
            sumData = this.aldCityPrestoDao.query7DayDataSumTop(vo);
        }
        if(null != aldCityEntityList && aldCityEntityList.size() > 0) {
            int sumTopN = CityUtils.calculateTopN(aldCityEntityList, vo);
            resultMap.put(CityConstants.TOP_N, aldCityEntityList);
            resultMap.put(CityConstants.TOP_OTHER, (NumberUtils.toLong(sumData+"") - sumTopN));
        }
        return resultMap;
    }


    /**
     * 最近30天 TopN
     * @param vo
     * @return
     */
    public Map<String, Object> query30DayDataTop(AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldCityEntity> aldCityEntityList = null;
        Long sumData = 0L;
        if(isCluster(vo)) {
            //从库
        } else {
            aldCityEntityList = this.aldCityPrestoDao.query30DayDataTop(vo);
            sumData = this.aldCityPrestoDao.query30DayDataSumTop(vo);
        }
        if(null != aldCityEntityList && aldCityEntityList.size() > 0) {
            int sumTopN = CityUtils.calculateTopN(aldCityEntityList, vo);
            resultMap.put(CityConstants.TOP_N, aldCityEntityList);
            resultMap.put(CityConstants.TOP_OTHER, (NumberUtils.toLong(sumData+"") - sumTopN));
        }
        return resultMap;
    }

    /**
     * 指定日期 TopN
     * @param vo
     * @return
     */
    public Map<String, Object> queryDaysDataTop(AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldCityEntity> aldCityEntityList = null;
        Long sumData = 0L;
        if(isCluster(vo)) {
            //从库
        } else {
            aldCityEntityList = this.aldCityPrestoDao.queryDaysDataTop(vo);
            sumData = this.aldCityPrestoDao.queryDaysDataSumTop(vo);
        }
        if(null != aldCityEntityList && aldCityEntityList.size() > 0) {
            int sumTopN = CityUtils.calculateTopN(aldCityEntityList, vo);
            resultMap.put(CityConstants.TOP_N, aldCityEntityList);
            resultMap.put(CityConstants.TOP_OTHER, (NumberUtils.toLong(sumData+"") - sumTopN));
        }
        return resultMap;
    }


    /**
     * 获取城市city今天数据 chart
     * @param vo
     * @return
     */
    @Override
    public Map<String, Object> queryTodayDataChart(AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldCityEntity> aldCityEntityList = null;
        if(isCluster(vo)) {
            //从库
        } else {
            aldCityEntityList = this.aldCityMasterDao.queryTodayDataChart(vo);
        }
        resultMap.put("data", aldCityEntityList);
        return resultMap;
    }


    /**
     * 获取城市city昨天数据 chart
     * @param vo
     * @return
     */
    @Override
    public Map<String, Object> queryYesterdayDataChart(AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldCityEntity> aldCityEntityList = null;
        if(isCluster(vo)) {
            //从库
        } else {
            aldCityEntityList = this.aldCityMasterDao.queryYesterdayDataChart(vo);
        }
        resultMap.put("data", aldCityEntityList);
        return resultMap;
    }


    /**
     * 获取城市City最近7天数据 chart
     * @param vo
     * @return
     */
    public Map<String, Object> query7DayDataChart(AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldCityEntity> aldCityEntityList = null;
        if(isCluster(vo)) {
            //从库
        } else {
            aldCityEntityList = this.aldCityPrestoDao.query7DayDataChart(vo);
        }
        resultMap.put("data", aldCityEntityList);
        return resultMap;
    }

    /**
     * 获取城市City最近30天数据 chart
     * @param vo
     * @return
     */
    public Map<String, Object> query30DayDataChart(AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldCityEntity> aldCityEntityList = null;
        if(isCluster(vo)) {
            //从库
        } else {
            aldCityEntityList = this.aldCityPrestoDao.query30DayDataChart(vo);
        }
        resultMap.put("data", aldCityEntityList);
        return resultMap;
    }


    /**
     * 获取城市City指定日期数据 chart
     * @param vo
     * @return
     */
    public Map<String, Object> queryDaysDataChart(AldCityVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldCityEntity> aldCityEntityList = null;
        if(isCluster(vo)) {
            //从库
            aldCityEntityList = this.aldCityPrestoDao.queryDaysDataChart(vo);
        } else {
            aldCityEntityList = this.aldCityPrestoDao.queryDaysDataChart(vo);
        }
        resultMap.put("data", aldCityEntityList);
        return resultMap;
    }








    @Override
    public boolean isCluster(AldCityVo aldCityVo) {
        return false;
    }



}
