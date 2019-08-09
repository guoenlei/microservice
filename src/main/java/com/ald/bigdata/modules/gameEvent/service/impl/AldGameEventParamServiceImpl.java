package com.ald.bigdata.modules.gameEvent.service.impl;

import com.ald.bigdata.common.constants.Constants;
import com.ald.bigdata.common.util.Arith;
import com.ald.bigdata.common.util.DateUtil;
import com.ald.bigdata.modules.gameEvent.dao.cluster.AldGameEventParamClusterDao;
import com.ald.bigdata.modules.gameEvent.dao.master.AldGameEventMasterDao;
import com.ald.bigdata.modules.gameEvent.dao.master.AldGameEventParamMasterDao;
import com.ald.bigdata.modules.gameEvent.dao.presto.AldGameEventParamPrestoDao;
import com.ald.bigdata.modules.gameEvent.entity.AldGameEventParamEntity;
import com.ald.bigdata.modules.gameEvent.service.AldGameEventParamService;
import com.ald.bigdata.modules.gameEvent.util.GameEventDataUtil;
import com.ald.bigdata.modules.gameEvent.vo.AldGameEventParamVo;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
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
 * 小游戏 事件参数
 */
@Service
@Transactional(readOnly = true)
public class AldGameEventParamServiceImpl implements AldGameEventParamService {

    private static final Logger LOG = LoggerFactory.getLogger(AldGameEventParamServiceImpl.class);

    @Autowired
    private AldGameEventParamMasterDao aldGameEventParamMasterDao;
    @Autowired
    private AldGameEventParamClusterDao aldGameEventParamClusterDao;
    @Autowired
    private AldGameEventParamPrestoDao aldGameEventParamPrestoDao;

    @Autowired
    private AldGameEventMasterDao aldGameEventMasterDao;


    /**
     * 获取明细分页
     *
     * @param aldGameEventParamVo
     * @return
     */
    @Override
    public Map<String, Object> queryPageDataList(AldGameEventParamVo aldGameEventParamVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if (null != aldGameEventParamVo) {
            String date = aldGameEventParamVo.getDate();
            if (date.contains(Constants.FLAG_01)) {
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                //判断日期是否包含今天
                boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
                //日期转换为数组
                aldGameEventParamVo.setList(DateUtil.getBetweenDates3(dates[0], dates[1]));
//                if(isContains) {
                if (false) {
                    //重置分页开始和结束，用于合并数据和排序
                    aldGameEventParamVo = GameEventDataUtil.setPageRow(aldGameEventParamVo);
                    LOG.info("查询presto和mysql数据，重置startRow和endRow，startRow:{}, endRow:{}",
                            aldGameEventParamVo.getStartRow(), aldGameEventParamVo.getEndRow());

                    //关联查询
                    Map<String, Object> map1 = this.queryDaysDataBy(aldGameEventParamVo);
                    Map<String, Object> map2 = this.queryTodayDataBy(aldGameEventParamVo);
                    List<AldGameEventParamEntity> aldGameEventParamEntityList1 = (List<AldGameEventParamEntity>) map1.get("data");
                    List<AldGameEventParamEntity> aldGameEventParamEntityList2 = (List<AldGameEventParamEntity>) map2.get("data");
                    //前十页聚合处理
                    List<AldGameEventParamEntity> aldGameEventParamEntityList = GameEventDataUtil.mergePage(aldGameEventParamEntityList1,
                            aldGameEventParamEntityList2, aldGameEventParamVo);
                    long count1 = (long) map1.get("count");
                    long count2 = (long) map2.get("count");
                    long dataCount = (long) Arith.sub(Arith.add(count1, count2), aldGameEventParamEntityList.size());

                    resultMap.put("data", aldGameEventParamEntityList);
                    resultMap.put("count", dataCount);
                } else {
                    //查询presto
                    resultMap = this.queryDaysDataBy(aldGameEventParamVo);
                }
                //参数明细查询
            } else if (date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                aldGameEventParamVo.setDate(DateUtil.getTodayDate());
                resultMap = this.queryTodayDataBy(aldGameEventParamVo);
            } else if (date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                aldGameEventParamVo.setDate(DateUtil.getYesterday());
                resultMap = this.queryYesterdayDataBy(aldGameEventParamVo);
            } else if (date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                resultMap = this.query7DayDataBy(aldGameEventParamVo);
            } else if (date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                resultMap = query30DayDataBy(aldGameEventParamVo);
            }
        }

        return resultMap;
    }


    /**
     * 从presto获取今天事件明细
     *
     * @param aldGameEventParamVo
     * @return
     */
    @Override
    public Map<String, Object> queryTodayDataBy(AldGameEventParamVo aldGameEventParamVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldGameEventParamEntity> aldGameEventParamEntityList = Lists.newArrayList();
        Long dataCount = 0L;
        LOG.info("从presto获取今天事件明细");

        dataCount = this.aldGameEventParamPrestoDao.queryEventParamDetailCountTodayBy(aldGameEventParamVo);
        if (null != dataCount && dataCount > 0L) {
            aldGameEventParamEntityList = this.aldGameEventParamPrestoDao.queryEventParamDetailTodayBy(aldGameEventParamVo);
        }

        resultMap.put("data", aldGameEventParamEntityList);
        resultMap.put("count", NumberUtils.toLong(dataCount + ""));

        return resultMap;
    }


    /**
     * 从presto获取昨天事件明细
     *
     * @param aldGameEventParamVo
     * @return
     */
    @Override
    public Map<String, Object> queryYesterdayDataBy(AldGameEventParamVo aldGameEventParamVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldGameEventParamEntity> aldGameEventParamEntityList = Lists.newArrayList();
        Long dataCount = 0L;
        LOG.info("从presto获取昨天事件明细");
        dataCount = this.aldGameEventParamPrestoDao.queryEventParamDetailCountYesterdayBy(aldGameEventParamVo);
        if (null != dataCount && dataCount > 0L) {
            aldGameEventParamEntityList = this.aldGameEventParamPrestoDao.queryEventParamDetailYesterdayBy(aldGameEventParamVo);
        }


        resultMap.put("data", aldGameEventParamEntityList);
        resultMap.put("count", NumberUtils.toLong(dataCount + ""));

        return resultMap;
    }


    /**
     * 从presto获取最近7天事件列表明细
     *
     * @param aldGameEventParamVo
     * @return
     */
    @Override
    public Map<String, Object> query7DayDataBy(AldGameEventParamVo aldGameEventParamVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldGameEventParamEntity> aldGameEventParamEntityList = Lists.newArrayList();
        String prop = aldGameEventParamVo.getProp();
        LOG.info("最近7天事件列表明细总数");
        Long dateCount = this.aldGameEventParamPrestoDao.query7DayCountDataBy(aldGameEventParamVo);
        if (null != dateCount && dateCount > 0L) {
            //基于排序 ev_paras_uv  ev_paras_count
            LOG.info("最近7天事件列表明细, 排序" + prop);
            if ("ev_paras_uv".equals(prop)) {
                aldGameEventParamEntityList = this.aldGameEventParamPrestoDao.queryPresto7DaysDataOrderByUvCount(aldGameEventParamVo);
            } else if ("ev_paras_count".equals(prop)) {
                aldGameEventParamEntityList = this.aldGameEventParamPrestoDao.queryPresto7DaysDataOrderByParamCount(aldGameEventParamVo);
            } else {
                aldGameEventParamEntityList = this.aldGameEventParamPrestoDao.queryPresto7DaysDataOrderByDefault(aldGameEventParamVo);
            }
        }

        resultMap.put("data", aldGameEventParamEntityList);
        resultMap.put("count", NumberUtils.toLong(dateCount + ""));

        return resultMap;
    }


    /**
     * 从presto获取最近30天事件列表明细
     *
     * @param aldGameEventParamVo
     * @return
     */
    @Override
    public Map<String, Object> query30DayDataBy(AldGameEventParamVo aldGameEventParamVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldGameEventParamEntity> aldGameEventParamEntityList = Lists.newArrayList();
        String prop = aldGameEventParamVo.getProp();
        //count
        LOG.info("最近30天事件列表明细总数");
        Long dateCount = this.aldGameEventParamPrestoDao.query30DayCountDataBy(aldGameEventParamVo);
        if (null != dateCount && dateCount > 0L) {
            //基于排序 ev_paras_uv  ev_paras_count
            LOG.info("最近30天事件列表明细, 排序" + prop);
            if ("ev_paras_uv".equals(prop)) {
                aldGameEventParamEntityList = this.aldGameEventParamPrestoDao.queryPresto30DaysDataOrderByUvCount(aldGameEventParamVo);
            } else if ("ev_paras_count".equals(prop)) {
                aldGameEventParamEntityList = this.aldGameEventParamPrestoDao.queryPresto30DaysDataOrderByParamCount(aldGameEventParamVo);
            } else {
                aldGameEventParamEntityList = this.aldGameEventParamPrestoDao.queryPresto30DaysDataOrderByDefault(aldGameEventParamVo);
            }
        }

        resultMap.put("data", aldGameEventParamEntityList);
        resultMap.put("count", NumberUtils.toLong(dateCount + ""));

        return resultMap;
    }


    @Override
    public Long query30DayCountDataBy(AldGameEventParamVo vo) {
        return null;
    }

    @Override
    public Long query7DayCountDataBy(AldGameEventParamVo vo) {
        return null;
    }

    @Override
    public Long queryDaysCountDataBy(AldGameEventParamVo vo) {
        return null;
    }


    /**
     * 获取指定时间区间的 事件明细 presto
     *
     * @param aldGameEventParamVo
     * @return
     */
    @Override
    public Map<String, Object> queryDaysDataBy(AldGameEventParamVo aldGameEventParamVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldGameEventParamEntity> aldGameEventParamEntityList = Lists.newArrayList();
        String prop = aldGameEventParamVo.getProp();
        //COUNT
        Long dateCount = this.aldGameEventParamPrestoDao.queryDaysCountDataBy(aldGameEventParamVo);
        if (null != dateCount && dateCount > 0L) {
            //基于排序 ev_paras_uv  ev_paras_count
            if ("ev_paras_uv".equals(prop)) {
                aldGameEventParamEntityList = this.aldGameEventParamPrestoDao.queryPrestoDaysDataOrderByUvCount(aldGameEventParamVo);
            } else if ("ev_paras_count".equals(prop)) {
                aldGameEventParamEntityList = this.aldGameEventParamPrestoDao.queryPrestoDaysDataOrderByParamCount(aldGameEventParamVo);
            } else {
                aldGameEventParamEntityList = this.aldGameEventParamPrestoDao.queryPrestoDaysDataOrderByDefault(aldGameEventParamVo);
            }
        }

        resultMap.put("data", aldGameEventParamEntityList);
        resultMap.put("count", NumberUtils.toLong(dateCount + ""));

        return resultMap;
    }


    /**
     * presto总数
     *
     * @param aldGameEventParamVo
     * @return
     */
    private long queryDaysDataCount(AldGameEventParamVo aldGameEventParamVo) {
        return this.aldGameEventParamPrestoDao.queryDaysCountDataBy(aldGameEventParamVo);
    }


    /**
     * 处理参数列表
     * <p>
     * 今天，昨天 查询presto
     * 最近7天 30天 查询presto中数据
     * 时间段查询 presto
     *
     * @param vo
     * @return
     */
    @Override
    public List<AldGameEventParamEntity> queryEventParamListDataBy(AldGameEventParamVo vo) {
        List<AldGameEventParamEntity> aldGameEventParamEntityList = Lists.newArrayList();
        if (null != vo) {
            String date = vo.getDate();
            if (date.contains(Constants.FLAG_01)) {
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                //判断日期是否包含今天
                boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
                //日期转换为数组
                vo.setList(DateUtil.getBetweenDates3(dates[0], dates[1]));
//                if(isContains) {
                if (false) {
                    //关联查询
                    List<AldGameEventParamEntity> aldGameEventParamEntityList1 = aldGameEventParamPrestoDao.queryEventParamListDaysDataBy(vo);
                    aldGameEventParamEntityList = aldGameEventParamEntityList1;
//                    List<AldGameEventParamEntity> aldGameEventParamEntityList2 = this.queryEventParamListTodayBy(vo);
                    //前十页聚合处理
//                    aldGameEventParamEntityList = GameEventDataUtil.merge(aldGameEventParamEntityList1, aldGameEventParamEntityList2, vo);
                } else {
                    //查询presto事件列表
                    aldGameEventParamEntityList = aldGameEventParamPrestoDao.queryEventParamListDaysDataBy(vo);
                }
                //处理 今天 昨天 最近7天和30天的数据列表
            } else if (date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                vo.setDate(DateUtil.getTodayDate());
                aldGameEventParamEntityList = this.queryEventParamListTodayBy(vo);
            } else if (date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                vo.setDate(DateUtil.getYesterday());
                aldGameEventParamEntityList = this.queryEventParamListYesterdayBy(vo);
            } else if (date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                aldGameEventParamEntityList = this.queryEventParamList7DaysDataBy(vo);
            } else if (date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                aldGameEventParamEntityList = this.queryEventParamList30DaysDataBy(vo);
            }
        }

        return aldGameEventParamEntityList;
    }


    /**
     * 获取presto今天列表数据
     *
     * @return
     */
    private List<AldGameEventParamEntity> queryEventParamListTodayBy(AldGameEventParamVo vo) {
        return this.aldGameEventParamPrestoDao.queryEventParamListTodayBy(vo);
    }


    /**
     * 获取presto昨天列表数据
     *
     * @param vo
     * @return
     */
    private List<AldGameEventParamEntity> queryEventParamListYesterdayBy(AldGameEventParamVo vo) {
        return this.aldGameEventParamPrestoDao.queryEventParamListYesterdayBy(vo);
    }


    /**
     * 获取presto中最近7天事件列表
     *
     * @param vo
     * @return
     */
    private List<AldGameEventParamEntity> queryEventParamList7DaysDataBy(AldGameEventParamVo vo) {
        return aldGameEventParamPrestoDao.queryEventParamList7DaysDataBy(vo);
    }


    /**
     * 获取presto中最近30天事件列表
     *
     * @param vo
     * @return
     */
    private List<AldGameEventParamEntity> queryEventParamList30DaysDataBy(AldGameEventParamVo vo) {
        return aldGameEventParamPrestoDao.queryEventParamList30DaysDataBy(vo);
    }


    /**
     * 判断是否需要读从库
     * @param aldGameEventParamVo
     * @return
     */


}
