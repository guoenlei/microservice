package com.aldwx.bigdata.modules.eventParam.service.impl;

import com.aldwx.bigdata.common.config.ConfigurationManager;
import com.aldwx.bigdata.common.constants.Constants;
import com.aldwx.bigdata.common.page.PageUtil;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.modules.dbinfo.dao.master.AldDbInfoDao;
import com.aldwx.bigdata.modules.dbinfo.entity.AldDbInfoEntity;
import com.aldwx.bigdata.modules.eventParam.dao.cluster.AldEventParamClusterDao;
import com.aldwx.bigdata.modules.eventParam.dao.master.AldEventMasterDao;
import com.aldwx.bigdata.modules.eventParam.dao.master.AldEventParamMasterDao;
import com.aldwx.bigdata.modules.eventParam.dao.presto.AldEventParamPrestoDao;
import com.aldwx.bigdata.modules.eventParam.entity.AldEventEntity;
import com.aldwx.bigdata.modules.eventParam.entity.AldEventParamEntity;
import com.aldwx.bigdata.modules.eventParam.service.AldEventParamService;
import com.aldwx.bigdata.modules.eventParam.vo.AldEventParamVo;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
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


@Service
@Transactional(readOnly = true)
public class AldEventParamServiceImpl implements AldEventParamService {

    private static final Logger LOG = LoggerFactory.getLogger(AldEventParamServiceImpl.class);

    @Autowired
    private AldEventParamMasterDao aldEventParamMasterDao;

    @Autowired
    private AldEventParamClusterDao aldEventParamClusterDao;

    @Autowired
    private AldEventParamPrestoDao aldEventParamPrestoDao;

    @Autowired
    private AldDbInfoDao aldDbInfoDao;

    @Autowired
    private AldEventMasterDao aldEventMasterDao;

    /**
     * 分页处理
     * @param aldEventParamVo
     * @return
     */
    @Override
    public Map<String, Object> queryPageDataList(AldEventParamVo aldEventParamVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldEventParamEntity> aldEventParamEntityList = Lists.newArrayList();
        if(null != aldEventParamVo) {
            String date = aldEventParamVo.getDate();
            if(date.contains(Constants.FLAG_01)) {
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                //判断日期是否包含今天
                boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
                if(isContains) {
                    //关联查询
//                    List<AldEventParamEntity> aldEventParamEntityList1 = queryDaysDataByPresto(aldEventParamVo);
//                    List<AldEventParamEntity> aldEventParamEntityList2 = queryTodayDataBy(aldEventParamVo);
                    Map<String, Object> map1 = queryDaysDataBy(aldEventParamVo);
                    Map<String, Object> map2 = queryTodayDataBy(aldEventParamVo);
                    //前十页聚合处理
                    //TODO
                } else {
                    //查询presto
                    resultMap = queryDaysDataBy(aldEventParamVo);
                }
            } else if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                resultMap = queryTodayDataBy(aldEventParamVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                resultMap = queryYesterdayDataBy(aldEventParamVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                resultMap = query7DayDataBy(aldEventParamVo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                resultMap = query30DayDataBy(aldEventParamVo);
            }
        }

        return resultMap;
    }

    /**
     * 今天
     * @param aldEventParamVo
     * @return
     */
    @Override
    public Map<String, Object> queryTodayDataBy(AldEventParamVo aldEventParamVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldEventParamEntity> aldEventParamEntityList = null;
        Page<?> page = null;
        if(isCluster(aldEventParamVo)) {
            //从库
            page = PageUtil.startPage(aldEventParamVo.getCurrentPage(), aldEventParamVo.getTotal(),
                    aldEventParamVo.getIsDownload());
            aldEventParamEntityList = aldEventParamClusterDao.queryTodayDataBy(aldEventParamVo);
        } else {
            //主库
            page = PageUtil.startPage(aldEventParamVo.getCurrentPage(), aldEventParamVo.getTotal(),
                    aldEventParamVo.getIsDownload());
            aldEventParamEntityList = aldEventParamMasterDao.queryTodayDataBy(aldEventParamVo);
        }

        resultMap.put("data", aldEventParamEntityList);
        resultMap.put("count", page.getTotal());

        LOG.info("总共有:"+page.getTotal()+"条数据,实际返回:"+aldEventParamEntityList.size()+"条数据!");
        return resultMap;
    }

    /**
     * 昨天
     * @param aldEventParamVo
     * @return
     */
    @Override
    public Map<String, Object> queryYesterdayDataBy(AldEventParamVo aldEventParamVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldEventParamEntity> aldEventParamEntityList = null;
        Page<?> page = null;
        if(isCluster(aldEventParamVo)) {
            //从库
            page = startPage(aldEventParamVo);
            aldEventParamEntityList = aldEventParamClusterDao.queryYesterdayDataBy(aldEventParamVo);
        } else {
            //主库
            page = startPage(aldEventParamVo);
            aldEventParamEntityList = aldEventParamMasterDao.queryYesterdayDataBy(aldEventParamVo);
        }

        resultMap.put("data", aldEventParamEntityList);
        resultMap.put("count", page.getTotal());
        return resultMap;
    }


    /**
     * 最近7天
     * @param aldEventParamVo
     * @return
     */
    @Override
    public Map<String, Object> query7DayDataBy(AldEventParamVo aldEventParamVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldEventParamEntity> aldEventParamEntityList = null;
        Page<?> page = null;
        if(isCluster(aldEventParamVo)) {
            //从库
            page = startPage(aldEventParamVo);
            aldEventParamEntityList = aldEventParamClusterDao.query7DayDataBy(aldEventParamVo);
        } else {
            //主库
            page = startPage(aldEventParamVo);
            aldEventParamEntityList = aldEventParamMasterDao.query7DayDataBy(aldEventParamVo);
        }
        resultMap.put("data", aldEventParamEntityList);
        resultMap.put("count", page.getTotal());
        return resultMap;
    }


    /**
     * 最近30天
     * @param aldEventParamVo
     * @return
     */
    @Override
    public Map<String, Object> query30DayDataBy(AldEventParamVo aldEventParamVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldEventParamEntity> aldEventParamEntityList = null;
        Page<?> page = null;
        if(isCluster(aldEventParamVo)) {
            //从库
            page = startPage(aldEventParamVo);
            aldEventParamEntityList = aldEventParamClusterDao.query30DayDataBy(aldEventParamVo);
        } else {
            //主库
            page = startPage(aldEventParamVo);
            aldEventParamEntityList = aldEventParamMasterDao.query30DayDataBy(aldEventParamVo);
        }
        resultMap.put("data", aldEventParamEntityList);
        resultMap.put("count", page.getTotal());
        return resultMap;
    }


    @Override
    public Long query30DayCountDataBy(AldEventParamVo aldEventParamVo) {
        return null;
    }

    @Override
    public Long query7DayCountDataBy(AldEventParamVo aldEventParamVo) {
        return null;
    }

    @Override
    public Long queryDaysCountDataBy(AldEventParamVo aldEventParamVo) {
        return aldEventParamPrestoDao.queryDaysCountDataBy(aldEventParamVo);
    }


    /**
     * 按时间区间查询
     * @param aldEventParamVo
     * @return
     */
    @Override
    public Map<String, Object> queryDaysDataBy(AldEventParamVo aldEventParamVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<AldEventParamEntity> aldEventParamEntityList = null;
        String[] dates = StringUtils.splitPreserveAllTokens(aldEventParamVo.getDate(), Constants.FLAG_01);
        aldEventParamVo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
//        List<AldEventParamEntity> aldEventParamEntityList = aldEventParamPrestoDao.queryByAk(aldEventParamVo);
        aldEventParamEntityList = aldEventParamPrestoDao.queryDaysDataByPresto(aldEventParamVo);
//        return  aldEventParamEntityList;

        long count = queryDaysCountDataBy(aldEventParamVo);
        resultMap.put("data", aldEventParamEntityList);
        resultMap.put("count", count);
        return resultMap;
    }




    /**
     * 获取事件列表
     * @return
     */
    @Override
    public List<AldEventEntity> queryEventListDataBy(AldEventParamVo vo) {
        PageUtil.defaultPage();
        return aldEventMasterDao.queryEventListDataBy(vo.getAppKey(), vo.getEventKey());
    }


    /**
     * 判断ak是否在分库表
     * @param aldEventParamVo
     * @return
     */
    public boolean isCluster(AldEventParamVo aldEventParamVo) {
        AldDbInfoEntity aldDbInfoEntity = aldDbInfoDao.selectOne(aldEventParamVo.getAppKey());
        if(null != aldDbInfoEntity && aldDbInfoEntity.getDbIp().equals
                (ConfigurationManager.getProperty(Constants.CLUSTER_DATASOURCE_IP))) {
            return true;
        }
        return false;
    }


    private Page<?> startPage(AldEventParamVo vo) {
        return PageHelper.startPage(Integer.parseInt(vo.getCurrentPage()),
                Integer.parseInt(vo.getTotal()));
    }

}
