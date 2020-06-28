package com.aldwx.bigdata.modules.province.service.impl;

import com.aldwx.bigdata.common.constants.Constants;
import com.aldwx.bigdata.modules.city.constants.CityConstants;
import com.aldwx.bigdata.modules.dbinfo.dao.master.AldDbInfoDao;
import com.aldwx.bigdata.modules.province.dao.cluster.AldProvinceClusterDao;
import com.aldwx.bigdata.modules.province.dao.master.AldProvinceMasterDao;
import com.aldwx.bigdata.modules.province.dao.presto.AldProvincePrestoDao;
import com.aldwx.bigdata.modules.province.entity.AldProvinceEntity;
import com.aldwx.bigdata.modules.province.service.AldProvinceService;
import com.aldwx.bigdata.modules.province.util.ProvinceUtil;
import com.aldwx.bigdata.modules.province.vo.AldProvinceVo;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 地域分析 - 省份province
 */
@Service
@Transactional(readOnly = true)
public class AldProvinceServiceImpl implements AldProvinceService {

    private static final Logger LOG = LoggerFactory.getLogger(AldProvinceServiceImpl.class);

    @Autowired
    private AldDbInfoDao aldDbInfoDao;
    @Autowired
    private AldProvinceMasterDao aldProvinceMasterDao;
    @Autowired
    private AldProvinceClusterDao aldProvinceClusterDao;
    @Autowired
    private AldProvincePrestoDao aldProvincePrestoDao;


    /**
     * 获取列表
     * @param vo
     * @return
     */
    @Override
    public Map<String, Object> queryPageDataList(AldProvinceVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        String date = vo.getDate();
        if(date.contains(CityConstants.FLAG_01)) {
            //时间区间数据处理
//            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
//            boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
//            vo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
//            if(isContains) {
            if(false) {
                //TODO
            } else {
                resultMap = this.queryDaysDataBy(vo);
            }
        } else if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
            resultMap = this.queryTodayDataBy(vo);
        } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
            resultMap = this.queryYesterdayDataBy(vo);
        } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
            resultMap = this.query7DayDataBy(vo);
        } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
            resultMap = this.query30DayDataBy(vo);
        }

        return resultMap;
    }


    @Override
    public Map<String, Object> queryProvinceDataChart(AldProvinceVo aldProvinceVo) {
        Map<String, Object> resutMap = Maps.newConcurrentMap();
        String date = aldProvinceVo.getDate();
        if(date.contains(CityConstants.FLAG_01)) {
//            //时间区间数据s) {
            if(false) {
                //
            } else {
                resutMap = this.queryDaysDataChart(aldProvinceVo);
            }
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_TODAY_TIME)) {
            resutMap = this.queryTodayDataChart(aldProvinceVo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
            resutMap = this.queryYesterdayDataChart(aldProvinceVo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
            resutMap = this.query7DayDataChart(aldProvinceVo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
            resutMap = this.query30DayDataTop(aldProvinceVo);
        }
        return resutMap;
    }


    /**
     * 取TopN
     * @param aldProvinceVo
     * @return
     */
    @Override
    public Map<String, Object> queryProvinceDataTop(AldProvinceVo aldProvinceVo) {
        Map<String, Object> resutMap = Maps.newConcurrentMap();
        String date = aldProvinceVo.getDate();
        if(date.contains(CityConstants.FLAG_01)) {
//            //时间区间数据处理
//            String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
//            boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
//            aldProvinceVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
//            if(isContains) {
            if(false) {
               //
            } else {
                resutMap = this.queryDaysDataTop(aldProvinceVo);
            }
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_TODAY_TIME)) {
            aldProvinceVo.setDate(ProvinceUtil.formatTodayTime());
            resutMap = this.queryTodayDataTop(aldProvinceVo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
            aldProvinceVo.setDate(ProvinceUtil.formatYesterdayTime());
            resutMap = this.queryYesterdayDataTop(aldProvinceVo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
            resutMap = this.query7DayDataTop(aldProvinceVo);
        } else if(date.equals(CityConstants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
            resutMap = this.query30DayDataTop(aldProvinceVo);
        }
        return resutMap;
    }

    @Override
    public Map<String, Object> queryTodayDataBy(AldProvinceVo aldProvinceVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldProvinceEntity> aldProvinceEntityList = null;
        Long sumData = 0L;
        if(isCluster(aldProvinceVo)) {
            //从库
        } else {
            aldProvinceEntityList = this.aldProvinceMasterDao.queryTodayDataTop(aldProvinceVo);
            sumData = this.aldProvinceMasterDao.queryTodayDataSumTop(aldProvinceVo);
        }
        if(null != aldProvinceEntityList && aldProvinceEntityList.size() > 0) {
            int sumTopN = ProvinceUtil.calculateTopN(aldProvinceEntityList, aldProvinceVo);
            resultMap.put(CityConstants.TOP_N, aldProvinceEntityList);
            resultMap.put(CityConstants.TOP_OTHER, (NumberUtils.toLong(sumData+"") - sumTopN));
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> queryYesterdayDataBy(AldProvinceVo aldProvinceVo) {
        return null;
    }

    @Override
    public Map<String, Object> query7DayDataBy(AldProvinceVo aldProvinceVo) {
        return null;
    }

    @Override
    public Map<String, Object> query30DayDataBy(AldProvinceVo aldProvinceVo) {
        return null;
    }

    @Override
    public Map<String, Object> queryDaysDataBy(AldProvinceVo aldProvinceVo) {
        return null;
    }

    @Override
    public Long query30DayCountDataBy(AldProvinceVo aldProvinceVo) {
        return null;
    }

    @Override
    public Long query7DayCountDataBy(AldProvinceVo aldProvinceVo) {
        return null;
    }

    @Override
    public Long queryDaysCountDataBy(AldProvinceVo aldProvinceVo) {
        return null;
    }

    @Override
    public Map<String, Object> queryTodayDataTop(AldProvinceVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldProvinceEntity> aldProvinceEntityList = null;
        Long dataCount = 0L;
        if(isCluster(vo)) {
            dataCount = this.aldProvinceClusterDao.queryTodayDataSumTop(vo);
            if(null != dataCount && dataCount > 0L) {
                aldProvinceEntityList = this.aldProvinceClusterDao.queryTodayDataTop(vo);
            }
        } else {
            dataCount = this.aldProvinceMasterDao.queryTodayDataSumTop(vo);
            if(null != dataCount && dataCount > 0L) {
                aldProvinceEntityList = this.aldProvinceMasterDao.queryTodayDataTop(vo);
            }
        }
        if(null != aldProvinceEntityList && aldProvinceEntityList.size() > 0) {
            int sumTopN = ProvinceUtil.calculateTopN(aldProvinceEntityList, vo);
            resultMap.put(CityConstants.TOP_N, aldProvinceEntityList);
            resultMap.put(CityConstants.TOP_OTHER, (NumberUtils.toLong(dataCount+"") - sumTopN));
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> queryYesterdayDataTop(AldProvinceVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldProvinceEntity> aldProvinceEntityList = null;
        Long dataCount = 0L;
        if(isCluster(vo)) {
            dataCount = this.aldProvinceClusterDao.queryYesterdayDataSumTop(vo);
            if(null != dataCount && dataCount > 0L) {
                aldProvinceEntityList = this.aldProvinceClusterDao.queryYesterdayDataTop(vo);
            }
        } else {
            dataCount = this.aldProvinceMasterDao.queryYesterdayDataSumTop(vo);
            if(null != dataCount && dataCount > 0L) {
                aldProvinceEntityList = this.aldProvinceMasterDao.queryYesterdayDataTop(vo);
            }
        }
        if(null != aldProvinceEntityList && aldProvinceEntityList.size() > 0) {
            int sumTopN = ProvinceUtil.calculateTopN(aldProvinceEntityList, vo);
            resultMap.put(CityConstants.TOP_N, aldProvinceEntityList);
            resultMap.put(CityConstants.TOP_OTHER, (NumberUtils.toLong(dataCount+"") - sumTopN));
        }
        return resultMap;
    }


    private Map<String, Object> query7DayDataTop(AldProvinceVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldProvinceEntity> aldProvinceEntityList = null;
        Long dataCount = 0L;
        if(isCluster(vo)) {
            //从库
            dataCount = this.aldProvincePrestoDao.query7DayDataSumTop(vo);
            if(null != dataCount && dataCount > 0L) {
                aldProvinceEntityList = this.aldProvincePrestoDao.queryDaysDataTop(vo);
            }
        } else {
            dataCount = this.aldProvincePrestoDao.query7DayDataSumTop(vo);
            if(null != dataCount && dataCount > 0L) {
                aldProvinceEntityList = this.aldProvincePrestoDao.queryDaysDataTop(vo);
            }
        }
        if(null != aldProvinceEntityList && aldProvinceEntityList.size() > 0) {
            int sumTopN = ProvinceUtil.calculateTopN(aldProvinceEntityList, vo);
            resultMap.put(CityConstants.TOP_N, aldProvinceEntityList);
            resultMap.put(CityConstants.TOP_OTHER, (NumberUtils.toLong(dataCount+"") - sumTopN));
        }
        return resultMap;
    }


    /**
     * 最近30天
     * @param vo
     * @return
     */
    private Map<String, Object> query30DayDataTop(AldProvinceVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldProvinceEntity> aldProvinceEntityList = null;
        Long dataCount = 0L;
        if(isCluster(vo)) {
            //从库
            dataCount = this.aldProvincePrestoDao.query7DayDataSumTop(vo);
            if(null != dataCount && dataCount > 0L) {
                aldProvinceEntityList = this.aldProvincePrestoDao.queryDaysDataTop(vo);
            }
        } else {
            dataCount = this.aldProvincePrestoDao.query7DayDataSumTop(vo);
            if(null != dataCount && dataCount > 0L) {
                aldProvinceEntityList = this.aldProvincePrestoDao.queryDaysDataTop(vo);
            }
        }
        if(null != aldProvinceEntityList && aldProvinceEntityList.size() > 0) {
            int sumTopN = ProvinceUtil.calculateTopN(aldProvinceEntityList, vo);
            resultMap.put(CityConstants.TOP_N, aldProvinceEntityList);
            resultMap.put(CityConstants.TOP_OTHER, (NumberUtils.toLong(dataCount+"") - sumTopN));
        }
        return resultMap;
    }


    /**
     * 指定日期 TopN
     * @param vo
     * @return
     */
    private Map<String, Object> queryDaysDataTop(AldProvinceVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldProvinceEntity> aldProvinceEntityList = null;
        Long dataCount = 0L;
        if(isCluster(vo)) {
            //从库
            dataCount = this.aldProvincePrestoDao.query7DayDataSumTop(vo);
            if(null != dataCount && dataCount > 0L) {
                aldProvinceEntityList = this.aldProvincePrestoDao.queryDaysDataTop(vo);
            }
        } else {
            dataCount = this.aldProvincePrestoDao.query7DayDataSumTop(vo);
            if(null != dataCount && dataCount > 0L) {
                aldProvinceEntityList = this.aldProvincePrestoDao.queryDaysDataTop(vo);
            }
        }
        if(null != aldProvinceEntityList && aldProvinceEntityList.size() > 0) {
            int sumTopN = ProvinceUtil.calculateTopN(aldProvinceEntityList, vo);
            resultMap.put(CityConstants.TOP_N, aldProvinceEntityList);
            resultMap.put(CityConstants.TOP_OTHER, (NumberUtils.toLong(dataCount+"") - sumTopN));
        }
        return resultMap;
    }



    @Override
    public Map<String, Object> queryTodayDataChart(AldProvinceVo vo) {
        return null;
    }

    @Override
    public Map<String, Object> queryYesterdayDataChart(AldProvinceVo vo) {
        return null;
    }

    private Map<String, Object> query7DayDataChart(AldProvinceVo vo) {
        return null;
    }

    private Map<String, Object> query30DayDataChart(AldProvinceVo vo) {
        return null;
    }


    private Map<String, Object> queryDaysDataChart(AldProvinceVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldProvinceEntity> aldProvinceEntityList = null;
        if(isCluster(vo)) {
            aldProvinceEntityList = this.aldProvincePrestoDao.queryDaysDataChart(vo);
        } else {
            aldProvinceEntityList = this.aldProvincePrestoDao.queryDaysDataChart(vo);
        }

        return resultMap;
    }



    @Override
    public boolean isCluster(AldProvinceVo aldProvinceVo) {
        return false;
    }
}
