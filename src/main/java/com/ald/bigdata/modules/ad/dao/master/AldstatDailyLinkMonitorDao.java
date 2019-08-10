package com.ald.bigdata.modules.ad.dao.master;

import com.ald.bigdata.modules.ad.entity.AldstatDailyLinkMonitor;
import com.ald.bigdata.modules.ad.vo.AldAdParamVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AldstatDailyLinkMonitorDao {

    int deleteByPrimaryKey(Long id);

    int insert(AldstatDailyLinkMonitor record);

    int insertSelective(AldstatDailyLinkMonitor record);

    AldstatDailyLinkMonitor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AldstatDailyLinkMonitor record);

    int updateByPrimaryKey(AldstatDailyLinkMonitor record);

    //今天和昨天的打开次数
    List selectOpenPageCountByTodayAndYesterday(AldAdParamVo vo);
    List selectOpenPageCountByTodayAndYesterdayHour(AldAdParamVo vo);
    //一段时间的打开次数
    List selectOpenPageCountDayList(AldAdParamVo vo);//列表
    List selectOpenPageCountByTimeInterval(AldAdParamVo vo);//总量

    //今天和昨天的访问人数
    List selectOpenUserCountByTodayAndYesterday(AldAdParamVo vo);
    List selectOpenUserCountByTodayAndYesterdayHour(AldAdParamVo vo);
    //一段时间的访问人数
    List selectOpenUserCountByTimeInterval(AldAdParamVo vo);
    List selectOpenUserCountDayList(AldAdParamVo vo);

    //今天和昨天的访问新用户
    List selectNewUserCountByTodayAndYesterday(AldAdParamVo vo);
    List selectNewUserCountByTodayAndYesterdayHour(AldAdParamVo vo);
    //一段时间的访问新用户
    List selectNewUserCountByTimeInterval(AldAdParamVo vo);
    List selectNewUserCountDayList(AldAdParamVo vo);

    //今天和昨天的访问新授权用户
    List selectNewAuthUserCountByTodayAndYesterday(AldAdParamVo vo);
    List selectNewAuthUserCountByTodayAndYesterdayHour(AldAdParamVo vo);
    //一段时间的访问新授权用户
    List selectNewAuthUserCountByTimeInterval(AldAdParamVo vo);
    List selectNewAuthUserCountDayList(AldAdParamVo vo);


    //活动推广今天昨天
    List selectPupoAnalyzeListByTodayAndYesterdayL(AldAdParamVo vo);
    List selectPupoAnalyzeListByTodayAndYesterdayC(AldAdParamVo vo);
    List selectPupoAnalyzeListByTodayAndYesterdayS(AldAdParamVo vo);
    List selectPupoAnalyzeListByTodayAndYesterdayLC(AldAdParamVo vo);
    List selectPupoAnalyzeListByTodayAndYesterdayLS(AldAdParamVo vo);
    List selectPupoAnalyzeListByTodayAndYesterdayCS(AldAdParamVo vo);
    List selectPupoAnalyzeListByTodayAndYesterdayLCS(AldAdParamVo vo);
    //活动推广今天昨天
    List selectPupoAnalyzeListByTimeIntervalL(AldAdParamVo vo);
    List selectPupoAnalyzeListByTimeIntervalC(AldAdParamVo vo);
    List selectPupoAnalyzeListByTimeIntervalS(AldAdParamVo vo);
    List selectPupoAnalyzeListByTimeIntervalLC(AldAdParamVo vo);
    List selectPupoAnalyzeListByTimeIntervalLS(AldAdParamVo vo);
    List selectPupoAnalyzeListByTimeIntervalCS(AldAdParamVo vo);
    List selectPupoAnalyzeListByTimeIntervalLCS(AldAdParamVo vo);
    //小时
    List selectPupoAnalyzeListByHourL(AldAdParamVo vo);
    List selectPupoAnalyzeListByHourC(AldAdParamVo vo);
    List selectPupoAnalyzeListByHourS(AldAdParamVo vo);
    List selectPupoAnalyzeListByHourLC(AldAdParamVo vo);
    List selectPupoAnalyzeListByHourLS(AldAdParamVo vo);
    List selectPupoAnalyzeListByHourCS(AldAdParamVo vo);
    List selectPupoAnalyzeListByHourLCS(AldAdParamVo vo);
    //天列表

    List selectPupoAnalyzeListByDayL(AldAdParamVo vo);
    List selectPupoAnalyzeListByDayC(AldAdParamVo vo);
    List selectPupoAnalyzeListByDayS(AldAdParamVo vo);
    List selectPupoAnalyzeListByDayLC(AldAdParamVo vo);
    List selectPupoAnalyzeListByDayLS(AldAdParamVo vo);
    List selectPupoAnalyzeListByDayCS(AldAdParamVo vo);
    List selectPupoAnalyzeListByDayLCS(AldAdParamVo vo);
}