package com.aldwx.bigdata.modules.terminal.service.impl;

import com.aldwx.bigdata.common.constants.Constants;
import com.aldwx.bigdata.common.page.PageUtil;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.modules.dbinfo.dao.master.AldDbInfoDao;
import com.aldwx.bigdata.modules.terminal.dao.cluster.AldTerminalClusterDao;
import com.aldwx.bigdata.modules.terminal.dao.master.AldTerminalMasterDao;
import com.aldwx.bigdata.modules.terminal.dao.presto.AldTerminalPrestoDao;
import com.aldwx.bigdata.modules.terminal.entity.TerminalEntity;
import com.aldwx.bigdata.modules.terminal.service.AldTerminalService;
import com.aldwx.bigdata.modules.terminal.vo.AldTerminalVo;
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
public class AldTerminalServiceImpl implements AldTerminalService {

    private static final Logger LOG = LoggerFactory.getLogger(AldTerminalServiceImpl.class);

    @Autowired
    private AldDbInfoDao aldDbInfoDao;
    @Autowired
    private AldTerminalMasterDao aldTerminalMasterDao;
    @Autowired
    private AldTerminalClusterDao aldTerminalClusterDao;
    @Autowired
    private AldTerminalPrestoDao aldTerminalPrestoDao;


    /**
     * 分页处理
     * @param vo
     * @return
     */
    @Override
    public Map<String ,Object> queryPageDataList(AldTerminalVo vo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<TerminalEntity> terminalEntityList = Lists.newArrayList();
        if(null != vo) {
            String date = vo.getDate();
            if(date.contains(Constants.FLAG_01)) {
                String[] dates = StringUtils.splitPreserveAllTokens(date, Constants.FLAG_01);
                //判断日期是否包含今天
                boolean isContains = DateUtil.isContainsToday(dates[0], dates[1]);
                vo.setList(DateUtil.getBetweenDates3(dates[0],dates[1]));
//                if(isContains) {
                if(false) {
                    //关联查询
//                    List<TerminalEntity> terminalEntityList1 = queryDaysDataByPresto(vo);
//                    List<TerminalEntity> terminalEntityList2 = queryTodayDataBy(vo);
                    Map<String, Object> map1 = queryDaysDataBy(vo);
                    Map<String, Object> map2 = queryTodayDataBy(vo);
                    //前十页聚合处理
                } else {
                    //查询presto
                    resultMap = queryDaysDataBy(vo);
                }
            } else if(date.equals(Constants.ALDSTAT_EVENT_TODAY_TIME)) {
                resultMap = queryTodayDataBy(vo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_YESTERDAY_TIME)) {
                resultMap = queryYesterdayDataBy(vo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_WEEKDAY_TIME)) {
                resultMap = query7DayDataBy(vo);
            } else if(date.equals(Constants.ALDSTAT_EVENT_NEAR_MONTH_TIME)) {
                resultMap = query30DayDataBy(vo);
            }
        }

        return resultMap;
    }


    /**
     * 获取今天数据
     * @param terminalVo
     * @return
     */
    @Override
    public Map<String, Object> queryTodayDataBy(AldTerminalVo terminalVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<TerminalEntity> terminalEntityList = null;
        Page<?> page = null;
        if(isCluster(terminalVo)) {
            //从库
            page = PageUtil.startPage(terminalVo.getCurrentPage(), terminalVo.getTotal(),
                    terminalVo.getIsDownload());
            terminalEntityList = aldTerminalClusterDao.queryTodayDataBy(terminalVo);
        } else {
            //主库
            page = PageUtil.startPage(terminalVo.getCurrentPage(), terminalVo.getTotal(),
                    terminalVo.getIsDownload());
            terminalEntityList = aldTerminalMasterDao.queryTodayDataBy(terminalVo);
        }
        resultMap.put("data", terminalEntityList);
        resultMap.put("count", page.getTotal());
        return resultMap;
    }


    /**
     * 获取昨天数据
     * @param terminalVo
     * @return
     */
    @Override
    public Map<String, Object> queryYesterdayDataBy(AldTerminalVo terminalVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<TerminalEntity> terminalEntityList = null;
        Page<?> page = null;
        if(isCluster(terminalVo)) {
            //从库
            page = PageUtil.startPage(terminalVo.getCurrentPage(), terminalVo.getTotal(),
                    terminalVo.getIsDownload());
            terminalEntityList = aldTerminalClusterDao.queryYesterdayDataBy(terminalVo);
        } else {
            //主库
            page = PageUtil.startPage(terminalVo.getCurrentPage(), terminalVo.getTotal(),
                    terminalVo.getIsDownload());
            terminalEntityList = aldTerminalMasterDao.queryYesterdayDataBy(terminalVo);
        }
        resultMap.put("data", terminalEntityList);
        resultMap.put("count", page.getTotal());
        LOG.info("总共有:"+page.getTotal()+"条数据,实际返回:"+terminalEntityList.size()+"条数据!");
        return resultMap;
    }


    /**
     * 获取最近7天数据
     * @param terminalVo
     * @return
     */
    @Override
    public Map<String, Object>  query7DayDataBy(AldTerminalVo terminalVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<TerminalEntity> terminalEntityList = null;
        Long dataCount = 0L;
        if(isCluster(terminalVo)) {
            //从库
            dataCount = query7DayCountDataBy(terminalVo);
            if(dataCount > 0L) {
                terminalEntityList = this.aldTerminalPrestoDao.query7DayDataBy(terminalVo);
            }
        } else {
            //主库
            dataCount = query7DayCountDataBy(terminalVo);
            if(dataCount > 0L) {
                terminalEntityList = this.aldTerminalPrestoDao.query7DayDataBy(terminalVo);
            }
        }
        resultMap.put("data", terminalEntityList);
        resultMap.put("count", dataCount);
        return resultMap;
    }


    /**
     * 获取最近30天数据
     * @param terminalVo
     * @return
     */
    @Override
    public Map<String, Object>  query30DayDataBy(AldTerminalVo terminalVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<TerminalEntity> terminalEntityList = null;
        Long dataCount = 0L;
        if(isCluster(terminalVo)) {
            //从库
            dataCount = query30DayCountDataBy(terminalVo);
            if(dataCount > 0L) {
                terminalEntityList = this.aldTerminalPrestoDao.query30DayDataBy(terminalVo);
            }
        } else {
            //主库
            dataCount = query30DayCountDataBy(terminalVo);
            if(dataCount > 0L) {
                terminalEntityList = this.aldTerminalPrestoDao.query30DayDataBy(terminalVo);
            }
        }
        resultMap.put("data", terminalEntityList);
        resultMap.put("count", dataCount);
        LOG.info("总共有:"+dataCount+"条数据,实际返回:"+terminalEntityList.size()+"条数据!");
        return resultMap;
    }

    /**
     * 获取presto中数据
     * @param terminalVo
     * @return
     */
    @Override
    public Map<String, Object> queryDaysDataBy(AldTerminalVo terminalVo) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<TerminalEntity> terminalEntityList = null;
        Long dataCount = 0L;
        if(isCluster(terminalVo)) {
            //从库
            dataCount = queryDaysCountDataBy(terminalVo);
            if(dataCount > 0L) {
                terminalEntityList = this.aldTerminalPrestoDao.queryDaysDataBy(terminalVo);
            }
        } else {
            //主库
            dataCount = queryDaysCountDataBy(terminalVo);
            if(dataCount > 0L) {
                terminalEntityList = this.aldTerminalPrestoDao.queryDaysDataBy(terminalVo);
            }
        }
        resultMap.put("data", terminalEntityList);
        resultMap.put("count", dataCount);
        return  resultMap;
    }

    @Override
    public Long query30DayCountDataBy(AldTerminalVo terminalVo) {
        return this.aldTerminalPrestoDao.query30DayCountDataBy(terminalVo);
    }

    @Override
    public Long query7DayCountDataBy(AldTerminalVo terminalVo) {
        return this.aldTerminalPrestoDao.query7DayCountDataBy(terminalVo);
    }

    @Override
    public Long queryDaysCountDataBy(AldTerminalVo terminalVo) {
        return this.aldTerminalPrestoDao.queryDaysCountDataBy(terminalVo);
    }



    /**
     * 判断ak是否在分库表
     * @param terminalVo
     * @return
     */
    public boolean isCluster(AldTerminalVo terminalVo) {
//        AldDbInfoEntity aldDbInfoEntity = aldDbInfoDao.selectOne(terminalVo.getAppKey());
//        if(null != aldDbInfoEntity && aldDbInfoEntity.getDbIp().equals
//                (ConfigurationManager.getProperty(Constants.CLUSTER_DATASOURCE_IP))) {
//            return true;
//        }
        return false;
    }
}
