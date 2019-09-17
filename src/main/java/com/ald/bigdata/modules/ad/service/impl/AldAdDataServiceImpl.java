package com.ald.bigdata.modules.ad.service.impl;

import com.ald.bigdata.common.constants.Constants;
import com.ald.bigdata.common.util.DateUtil;
import com.ald.bigdata.modules.ad.dao.master.AldAdDataMasterDao;
import com.ald.bigdata.modules.ad.entity.AldAdDataEntity;
import com.ald.bigdata.modules.ad.service.AldAdDataService;
import com.ald.bigdata.modules.ad.vo.AldAdDataVo;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class AldAdDataServiceImpl implements AldAdDataService {


    @Autowired
    private AldAdDataMasterDao aldAdDataMasterDao;


    /**
     * 打开次数
     * @param aldAdDataVo
     * @return
     */
    @Override
    public Map<String, Object> openPageCount(AldAdDataVo aldAdDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != aldAdDataVo) {
            String date = aldAdDataVo.getDate();
            if(date.contains(Constants.FLAG_01)) {
                date = date.replaceAll("\\s*", "");
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                if(dates[0].equals(dates[1])) {
                    List<String> dateList = Lists.newArrayList();
                    dateList.add(dates[0]);
                    aldAdDataVo.setList(dateList);
                    resultMap = this.queryDaysDataBy(aldAdDataVo);
                } else {
                    aldAdDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
                    resultMap = this.queryDaysDataBy(aldAdDataVo);
                }
//                //判断日期是否包含今天
////                boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
//                aldAdDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
//                if(false) {
//                    resultMap = this.queryDaysDataBy(aldAdDataVo);
//                } else {
//                    resultMap = this.queryDaysDataBy(aldAdDataVo);
//                }
            }else if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                aldAdDataVo.setDate(DateUtil.getTodayDate());
                resultMap = this.queryTodayDataBy(aldAdDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                aldAdDataVo.setDate(DateUtil.getYesterday());
                resultMap = this.queryYesterdayDataBy(aldAdDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                resultMap = this.query7DayDataBy(aldAdDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                resultMap = query30DayDataBy(aldAdDataVo);
            }
        }

        //
        if(null != resultMap && resultMap.size() > 0) {
            List<AldAdDataEntity> aldAdDataEntityList = (List<AldAdDataEntity>)resultMap.get("data");
            List<AldAdDataEntity> aldAdDataEntityList1 = Lists.newArrayList();
            if(null != aldAdDataEntityList && aldAdDataEntityList.size() > 0) {
                for(AldAdDataEntity aldAdDataEntity : aldAdDataEntityList) {
                    AldAdDataEntity aldAdDataEntity2 = new AldAdDataEntity();
                    aldAdDataEntity2.setPopuCount(aldAdDataEntity.getOpenPopuCount());
                    aldAdDataEntity2.setNatuCount(aldAdDataEntity.getOpenNatuCount());
                    aldAdDataEntity2.setTotalCount(aldAdDataEntity.getOpenTotalCount());
                    aldAdDataEntity2.setDay(aldAdDataEntity.getDay());
                    aldAdDataEntity2.setHour(aldAdDataEntity.getHour());

                    aldAdDataEntityList1.add(aldAdDataEntity2);
                }
                resultMap.put("data", aldAdDataEntityList1);
            }
        }

        return resultMap;
    }


    /**
     * 新用户数
     * @param aldAdDataVo
     * @return
     */
    @Override
    public Map<String, Object> newUserCount(AldAdDataVo aldAdDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != aldAdDataVo) {
            String date = aldAdDataVo.getDate();
            if(date.contains(Constants.FLAG_01)) {
                date = date.replaceAll("\\s*", "");
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                if(dates[0].equals(dates[1])) {
                    List<String> dateList = Lists.newArrayList();
                    dateList.add(dates[0]);
                    aldAdDataVo.setList(dateList);
                    resultMap = this.queryDaysDataBy(aldAdDataVo);
                } else {
                    aldAdDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
                    resultMap = this.queryDaysDataBy(aldAdDataVo);
                }
//                //判断日期是否包含今天
//                boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
//                //日期转换为数组
//                aldAdDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
//                if(false) {
////                    //重置分页开始和结束，用于合并数据和排序
//                    resultMap = this.queryDaysDataBy(aldAdDataVo);
//                } else {
//                    //重置分页开始和结束，用于合并数据和排序
//                    resultMap = this.queryDaysDataBy(aldAdDataVo);
//                }
            }else if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                aldAdDataVo.setDate(DateUtil.getTodayDate());
                resultMap = this.queryTodayDataBy(aldAdDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                aldAdDataVo.setDate(DateUtil.getYesterday());
                resultMap = this.queryYesterdayDataBy(aldAdDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                resultMap = this.query7DayDataBy(aldAdDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                resultMap = query30DayDataBy(aldAdDataVo);
            }
        }

        //
        if(null != resultMap && resultMap.size() > 0) {
            List<AldAdDataEntity> aldAdDataEntityList = (List<AldAdDataEntity>)resultMap.get("data");
            List<AldAdDataEntity> aldAdDataEntityList1 = Lists.newArrayList();
            if(null != aldAdDataEntityList && aldAdDataEntityList.size() > 0) {
                for(AldAdDataEntity aldAdDataEntity : aldAdDataEntityList) {
                    AldAdDataEntity aldAdDataEntity2 = new AldAdDataEntity();
                    aldAdDataEntity2.setPopuCount(aldAdDataEntity.getNewPopuCount());
                    aldAdDataEntity2.setNatuCount(aldAdDataEntity.getNewNatuCount());
                    aldAdDataEntity2.setTotalCount(aldAdDataEntity.getNewTotalCount());
                    aldAdDataEntity2.setDay(aldAdDataEntity.getDay());
                    aldAdDataEntity2.setHour(aldAdDataEntity.getHour());

                    aldAdDataEntityList1.add(aldAdDataEntity2);
                }
                resultMap.put("data", aldAdDataEntityList1);
            }
        }

        return resultMap;
    }


    /**
     * 访问人数
     * @param aldAdDataVo
     * @return
     */
    @Override
    public Map<String, Object> visitUserCount(AldAdDataVo aldAdDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != aldAdDataVo) {
            String date = aldAdDataVo.getDate();
            if(date.contains(Constants.FLAG_01)) {
                date = date.replaceAll("\\s*", "");
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                if(dates[0].equals(dates[1])) {
                    List<String> dateList = Lists.newArrayList();
                    dateList.add(dates[0]);
                    aldAdDataVo.setList(dateList);
                    resultMap = this.queryDaysDataBy(aldAdDataVo);
                } else {
                    aldAdDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
                    resultMap = this.queryDaysDataBy(aldAdDataVo);
                }
//                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
//                //判断日期是否包含今天
//                boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
//                //日期转换为数组
//                aldAdDataVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
//                if(false) {
////                    //重置分页开始和结束，用于合并数据和排序
//                    resultMap = this.queryDaysDataBy(aldAdDataVo);
//                } else {
//                    resultMap = this.queryDaysDataBy(aldAdDataVo);
//                }
            }else if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                aldAdDataVo.setDate(DateUtil.getTodayDate());
                resultMap = this.queryTodayDataBy(aldAdDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                aldAdDataVo.setDate(DateUtil.getYesterday());
                resultMap = this.queryYesterdayDataBy(aldAdDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                resultMap = this.query7DayDataBy(aldAdDataVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                resultMap = query30DayDataBy(aldAdDataVo);
            }
        }
        //
        if(null != resultMap && resultMap.size() > 0) {
            List<AldAdDataEntity> aldAdDataEntityList = (List<AldAdDataEntity>)resultMap.get("data");
            List<AldAdDataEntity> aldAdDataEntityList1 = Lists.newArrayList();
            if(null != aldAdDataEntityList && aldAdDataEntityList.size() > 0) {
                for(AldAdDataEntity aldAdDataEntity : aldAdDataEntityList) {
                    AldAdDataEntity aldAdDataEntity2 = new AldAdDataEntity();
                    aldAdDataEntity2.setPopuCount(aldAdDataEntity.getVisitorPopuCount());
                    aldAdDataEntity2.setNatuCount(aldAdDataEntity.getVisitorNatuCount());
                    aldAdDataEntity2.setTotalCount(aldAdDataEntity.getVisitorTotalCount());
                    aldAdDataEntity2.setDay(aldAdDataEntity.getDay());
                    aldAdDataEntity2.setHour(aldAdDataEntity.getHour());

                    aldAdDataEntityList1.add(aldAdDataEntity2);
                }
                resultMap.put("data", aldAdDataEntityList1);
            }
        }

        return resultMap;
    }


//    --------------------------------------------华丽分隔线 - -，


    @Override
    public Map<String, Object> queryPageDataList(AldAdDataVo aldAdDataVo) {
        return null;
    }

    @Override
    public Map<String, Object> queryTodayDataBy(AldAdDataVo aldAdDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdDataEntity> aldAdDataEntityList = this.aldAdDataMasterDao.queryAdDataListByHour(aldAdDataVo);
        resultMap.put("data", aldAdDataEntityList);
        return resultMap;
    }

    @Override
    public Map<String, Object> queryYesterdayDataBy(AldAdDataVo aldAdDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdDataEntity> aldAdDataEntityList = this.aldAdDataMasterDao.queryAdDataListByHour(aldAdDataVo);
        resultMap.put("data", aldAdDataEntityList);
        return resultMap;
    }

    @Override
    public Map<String, Object> query7DayDataBy(AldAdDataVo aldAdDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdDataEntity> aldAdDataEntityList = this.aldAdDataMasterDao.queryAd7Or30DaysDataListBy(aldAdDataVo);
        resultMap.put("data", aldAdDataEntityList);
        return resultMap;
    }

    @Override
    public Map<String, Object> query30DayDataBy(AldAdDataVo aldAdDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdDataEntity> aldAdDataEntityList = this.aldAdDataMasterDao.queryAd7Or30DaysDataListBy(aldAdDataVo);
        resultMap.put("data", aldAdDataEntityList);
        return resultMap;
    }

    @Override
    public Map<String, Object> queryDaysDataBy(AldAdDataVo aldAdDataVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldAdDataEntity> aldAdDataEntityList = this.aldAdDataMasterDao.queryAdDaysDataListBy(aldAdDataVo);
        resultMap.put("data", aldAdDataEntityList);
        return resultMap;
    }

    @Override
    public Long query30DayCountDataBy(AldAdDataVo aldAdDataVo) {
        return null;
    }

    @Override
    public Long query7DayCountDataBy(AldAdDataVo aldAdDataVo) {
        return null;
    }

    @Override
    public Long queryDaysCountDataBy(AldAdDataVo aldAdDataVo) {
        return null;
    }

    @Override
    public boolean isCluster(AldAdDataVo aldAdDataVo) {
        return false;
    }


}
