package com.aldwx.bigdata.modules.ad.dao.master;

import com.aldwx.bigdata.modules.ad.entity.Aldstat30daysLinkMonitor;
import com.aldwx.bigdata.modules.ad.vo.AldAdParamVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface Aldstat30daysLinkMonitorDao {

    int deleteByPrimaryKey(Long id);

    int insert(Aldstat30daysLinkMonitor record);

    int insertSelective(Aldstat30daysLinkMonitor record);

    Aldstat30daysLinkMonitor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Aldstat30daysLinkMonitor record);

    int updateByPrimaryKey(Aldstat30daysLinkMonitor record);
    //最近三十天的打开次数
    List  selectOpenPageCountBy_30_day(AldAdParamVo vo);
    //最近三十天的访问人数
    List  selectOpenUserCountBy_30_day(AldAdParamVo vo);
    //最近三十天的新用户数
    List  selectNewUserCountBy_30_day(AldAdParamVo vo);
    //最近三十天的新授权用户数
    List  selectNewAuthUserCountBy_30_day(AldAdParamVo vo);

    List  selectPupoAnalyzeListBy_30_dayL(AldAdParamVo vo);
    List  selectPupoAnalyzeListBy_30_dayC(AldAdParamVo vo);
    List  selectPupoAnalyzeListBy_30_dayS(AldAdParamVo vo);
    List  selectPupoAnalyzeListBy_30_dayLC(AldAdParamVo vo);
    List  selectPupoAnalyzeListBy_30_dayLS(AldAdParamVo vo);
    List  selectPupoAnalyzeListBy_30_dayCS(AldAdParamVo vo);
    List  selectPupoAnalyzeListBy_30_dayLCS(AldAdParamVo vo);
}