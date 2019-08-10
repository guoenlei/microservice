package com.ald.bigdata.modules.ad.dao.master;

import com.ald.bigdata.modules.ad.entity.Aldstat7daysLinkMonitor;
import com.ald.bigdata.modules.ad.vo.AldAdParamVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Aldstat7daysLinkMonitorDao {

    int deleteByPrimaryKey(Long id);

    int insert(Aldstat7daysLinkMonitor record);

    int insertSelective(Aldstat7daysLinkMonitor record);


    Aldstat7daysLinkMonitor selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(Aldstat7daysLinkMonitor record);

    int updateByPrimaryKey(Aldstat7daysLinkMonitor record);
    //最近7天的打开次数
    List  selectOpenPageCountBy_7_day(AldAdParamVo vo);
    //最近7天的访问人数
    List  selectOpenUserCountBy_7_day(AldAdParamVo vo);
    //最近7天的新用户数
    List  selectNewUserCountBy_7_day(AldAdParamVo vo);
    //最近7天的新授权用户数
    List  selectNewAuthUserCountBy_7_day(AldAdParamVo vo);


    List  selectPupoAnalyzeListBy_7_dayL(AldAdParamVo vo);
    List  selectPupoAnalyzeListBy_7_dayC(AldAdParamVo vo);
    List  selectPupoAnalyzeListBy_7_dayS(AldAdParamVo vo);
    List  selectPupoAnalyzeListBy_7_dayLC(AldAdParamVo vo);
    List  selectPupoAnalyzeListBy_7_dayLS(AldAdParamVo vo);
    List  selectPupoAnalyzeListBy_7_dayCS(AldAdParamVo vo);
    List  selectPupoAnalyzeListBy_7_dayLCS(AldAdParamVo vo);
}