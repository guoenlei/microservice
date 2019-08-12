package com.ald.bigdata.modules.ad.service;


import com.ald.bigdata.common.util.DateTools;
import com.ald.bigdata.modules.ad.dao.master.Aldstat30daysLinkMonitorDao;
import com.ald.bigdata.modules.ad.dao.master.Aldstat7daysLinkMonitorDao;
import com.ald.bigdata.modules.ad.dao.master.AldstatDailyLinkMonitorDao;
import com.ald.bigdata.modules.ad.vo.AldAdParamVo;
import com.ald.bigdata.modules.ad.vo.PupoAnalyzeListDetailLineVo;
import com.ald.bigdata.modules.ad.vo.PupoAnalyzeListVo;
import com.facebook.presto.jdbc.internal.spi.function.IsNull;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class    AldstatAdAnalysisService {
    @Autowired
    private AldstatDailyLinkMonitorDao aldstatDailyLinkMonitorDao;
    @Autowired
    private Aldstat7daysLinkMonitorDao aldstat7daysLinkMonitorDao;
    @Autowired
    private Aldstat30daysLinkMonitorDao aldstat30daysLinkMonitorDao;
//打开次数（天）
    public List openPageCountService(String app_key,String date){
        List list=null;
        if(date.equals("1")){//今天
            String time= DateTools.getDay();
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstatDailyLinkMonitorDao.selectOpenPageCountByTodayAndYesterday(vo);
        }else if(date.equals("2")){//昨天
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstatDailyLinkMonitorDao.selectOpenPageCountByTodayAndYesterday(vo);
        }else if(date.equals("3")){//7天
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstat7daysLinkMonitorDao.selectOpenPageCountBy_7_day(vo);
        }else if(date.equals("4")){//30天
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstat30daysLinkMonitorDao.selectOpenPageCountBy_30_day(vo);
        }else{
            String[] strArr = date.split("~");
            String dateStart=strArr[0];
            String dateEnd=strArr[1];
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDateStart(dateStart);
            vo.setDateEnd(dateEnd);
            list =aldstatDailyLinkMonitorDao.selectOpenPageCountByTimeInterval(vo);
        }
        return  list;
    }
    //打开次数（小时）
    public List openPageCountHourService(String app_key,String date){
        List list=null;
        if(date.equals("1")){
            String time=DateTools.getDay();
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstatDailyLinkMonitorDao.selectOpenPageCountByTodayAndYesterdayHour(vo);
        }else if(date.equals("2")){
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstatDailyLinkMonitorDao.selectOpenPageCountByTodayAndYesterdayHour(vo);
        }
        return  list;
    }
    //打开次数 （天列表）
    public List openPageCountDayListService(String app_key,String date){
        List list=null;
        if(date.equals("3")){
            String timeStart=DateTools.addDate(-7);
            String timeEnd=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDateStart(timeStart);
            vo.setDateEnd(timeEnd);
            list = aldstatDailyLinkMonitorDao.selectOpenPageCountDayList(vo);
        }else if(date.equals("4")){
            String timeEnd=DateTools.addDate(-1);
            String timeStart=DateTools.addDate(-30);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDateStart(timeStart);
            vo.setDateEnd(timeEnd);
            list = aldstatDailyLinkMonitorDao.selectOpenPageCountDayList(vo);
        }else{
            String[] strArr = date.split("~");
            String dateStart=strArr[0];
            String dateEnd=strArr[1];
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDateStart(dateStart);
            vo.setDateEnd(dateEnd);
            list =aldstatDailyLinkMonitorDao.selectOpenPageCountDayList(vo);
        }
        return  list;
    }
    //
    //访问人数（天）
   public List openUserCountService(String app_key,String date){
       List list=null;
       if(date.equals("1")){
           String time=DateTools.getDay();
           AldAdParamVo vo= new AldAdParamVo();
           vo.setAppkey(app_key);
           vo.setDate(time);
           list = aldstatDailyLinkMonitorDao.selectOpenUserCountByTodayAndYesterday(vo);
       }else if(date.equals("2")){
           String time=DateTools.addDate(-1);
           AldAdParamVo vo= new AldAdParamVo();
           vo.setAppkey(app_key);
           vo.setDate(time);
           list = aldstatDailyLinkMonitorDao.selectOpenUserCountByTodayAndYesterday(vo);
       }else if(date.equals("3")){
           String time=DateTools.addDate(-1);
           AldAdParamVo vo= new AldAdParamVo();
           vo.setAppkey(app_key);
           vo.setDate(time);
           list = aldstat7daysLinkMonitorDao.selectOpenUserCountBy_7_day(vo);
       }else if(date.equals("4")){
           String time=DateTools.addDate(-1);
           AldAdParamVo vo= new AldAdParamVo();
           vo.setAppkey(app_key);
           vo.setDate(time);
           list = aldstat30daysLinkMonitorDao.selectOpenUserCountBy_30_day(vo);
       }else{
           String[] strArr = date.split("~");
           String dateStart=strArr[0];
           String dateEnd=strArr[1];
           AldAdParamVo vo= new AldAdParamVo();
           vo.setAppkey(app_key);
           vo.setDateStart(dateStart);
           vo.setDateEnd(dateEnd);
           list =aldstatDailyLinkMonitorDao.selectOpenPageCountByTimeInterval(vo);
       }
       return  list;
    }
    //访问人数（小时）
    public List openUserCountHourService(String app_key,String date){
       List list=null;
       if(date.equals("1")){
           String time=DateTools.getDay();
           AldAdParamVo vo= new AldAdParamVo();
           vo.setAppkey(app_key);
           vo.setDate(time);
           list = aldstatDailyLinkMonitorDao.selectOpenUserCountByTodayAndYesterdayHour(vo);
       }else if(date.equals("2")){
           String time=DateTools.addDate(-1);
           AldAdParamVo vo= new AldAdParamVo();
           vo.setAppkey(app_key);
           vo.setDate(time);
           list = aldstatDailyLinkMonitorDao.selectOpenUserCountByTodayAndYesterdayHour(vo);
       }
       return  list;
    }
    //访问人数（天列表）
    public List openUserCountDayListService(String app_key,String date){
        List list=null;
        if(date.equals("3")){
            String timeStart=DateTools.addDate(-7);
            String timeEnd=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDateStart(timeStart);
            vo.setDateEnd(timeEnd);
           list = aldstatDailyLinkMonitorDao.selectOpenUserCountDayList(vo);
       }else if(date.equals("4")){
            String timeStart=DateTools.addDate(-30);
            String timeEnd=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDateStart(timeStart);
            vo.setDateEnd(timeEnd);
            list = aldstatDailyLinkMonitorDao.selectOpenUserCountDayList(vo);
       }else{
           String[] strArr = date.split("~");
           String dateStart=strArr[0];
           String dateEnd=strArr[1];
           AldAdParamVo vo= new AldAdParamVo();
           vo.setAppkey(app_key);
           vo.setDateStart(dateStart);
           vo.setDateEnd(dateEnd);
            list = aldstatDailyLinkMonitorDao.selectOpenUserCountDayList(vo);
       }
        return  list;
    }
    //新用户数（天）
    public List newUserCountService(String app_key,String date){
        List list=null;
        if(date.equals("1")){
            String time=DateTools.getDay();
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstatDailyLinkMonitorDao.selectNewUserCountByTodayAndYesterday(vo);
        }else if(date.equals("2")){
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstatDailyLinkMonitorDao.selectNewUserCountByTodayAndYesterday(vo);
        }else if(date.equals("3")){
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstat7daysLinkMonitorDao.selectNewUserCountBy_7_day(vo);
        }else if(date.equals("4")){
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstat30daysLinkMonitorDao.selectNewUserCountBy_30_day(vo);
        }else{
            String[] strArr = date.split("~");
            String dateStart=strArr[0];
            String dateEnd=strArr[1];
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDateStart(dateStart);
            vo.setDateEnd(dateEnd);
                list =aldstatDailyLinkMonitorDao.selectNewUserCountByTimeInterval(vo);
        }
        return  list;
    }
    //新用户数（小时）
    public List newUserCountHourService(String app_key,String date){
        List list=null;
        if(date.equals("1")){
            String time=DateTools.getDay();
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstatDailyLinkMonitorDao.selectNewUserCountByTodayAndYesterdayHour(vo);
        }else if(date.equals("2")){
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstatDailyLinkMonitorDao.selectNewUserCountByTodayAndYesterdayHour(vo);
        }
        return  list;
    }
    //新用户数（天列表）
    public List newUserCountDayListService(String app_key,String date){
        List list=null;

       if(date.equals("3")){
           String timeStart=DateTools.addDate(-7);
           String timeEnd=DateTools.addDate(-1);
           AldAdParamVo vo= new AldAdParamVo();
           vo.setAppkey(app_key);
           vo.setDateStart(timeStart);
           vo.setDateEnd(timeEnd);
            list = aldstatDailyLinkMonitorDao.selectNewUserCountDayList(vo);
        }else if(date.equals("4")){
           String timeStart=DateTools.addDate(-30);
           String timeEnd=DateTools.addDate(-1);
           AldAdParamVo vo= new AldAdParamVo();
           vo.setAppkey(app_key);
           vo.setDateStart(timeStart);
           vo.setDateEnd(timeEnd);
           list = aldstatDailyLinkMonitorDao.selectNewUserCountDayList(vo);
        }else{
            String[] strArr = date.split("~");
            String dateStart=strArr[0];
            String dateEnd=strArr[1];
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDateStart(dateStart);
            vo.setDateEnd(dateEnd);
            list = aldstatDailyLinkMonitorDao.selectNewUserCountDayList(vo);
        }
        return  list;
    }
    //新增授权数(天)
    public List newAuthUserCountService(String app_key,String date){
        List list=null;
        if(date.equals("1")){
            String time=DateTools.getDay();
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstatDailyLinkMonitorDao.selectNewAuthUserCountByTodayAndYesterday(vo);
        }else if(date.equals("2")){
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstatDailyLinkMonitorDao.selectNewAuthUserCountByTodayAndYesterday(vo);
        }else if(date.equals("3")){
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstat7daysLinkMonitorDao.selectNewAuthUserCountBy_7_day(vo);
        }else if(date.equals("4")){
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstat30daysLinkMonitorDao.selectNewAuthUserCountBy_30_day(vo);
        }else{
            String[] strArr = date.split("~");
            String dateStart=strArr[0];
            String dateEnd=strArr[1];
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDateStart(dateStart);
            vo.setDateEnd(dateEnd);
                list =aldstatDailyLinkMonitorDao.selectNewAuthUserCountByTimeInterval(vo);
        }
        return  list;
    }
    //新增授权数(小时)
    public List newAuthUserHourCountService(String app_key,String date){
        List list=null;
        if(date.equals("1")){
            String time=DateTools.getDay();
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstatDailyLinkMonitorDao.selectNewAuthUserCountByTodayAndYesterdayHour(vo);
        }else if(date.equals("2")){
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDate(time);
            list = aldstatDailyLinkMonitorDao.selectNewAuthUserCountByTodayAndYesterdayHour(vo);
        }
        return  list;
    }
    //新增授权数(天列表)
    public List newAuthUserCountDayListService(String app_key,String date){
        List list=null;
        if(date.equals("3")){
            String timeStart=DateTools.addDate(-7);
            String timeEnd=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDateStart(timeStart);
            vo.setDateEnd(timeEnd);
            list = aldstatDailyLinkMonitorDao.selectNewAuthUserCountDayList(vo);
        }else if(date.equals("4")){
            String timeStart=DateTools.addDate(-30);
            String timeEnd=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDateStart(timeStart);
            vo.setDateEnd(timeEnd);
            list = aldstatDailyLinkMonitorDao.selectNewAuthUserCountDayList(vo);
        }else{
            String[] strArr = date.split("~");
            String dateStart=strArr[0];
            String dateEnd=strArr[1];
            AldAdParamVo vo= new AldAdParamVo();
            vo.setAppkey(app_key);
            vo.setDateStart(dateStart);
            vo.setDateEnd(dateEnd);
            list = aldstatDailyLinkMonitorDao.selectNewAuthUserCountDayList(vo);
        }
        return  list;
    }

    /**
     * 之前传递的参数,修改为对象传递,为了aop切割方便
     * @param pupoVo
     * @return
     */
    //推广分析列表
    public PageInfo pupoAnalyzeListService(PupoAnalyzeListVo pupoVo){
        String date = pupoVo.getDate();
        String app_key = pupoVo.getAppKey();
        String total = pupoVo.getTotal();
        String prop = pupoVo.getProp();
        String typeId = pupoVo.getTypeId();
        String keyword = pupoVo.getKeyword();
        String order = pupoVo.getOrder();
        String currentPage = pupoVo.getCurrentPage();
        String channelName = pupoVo.getChannelName();
        String scenceName = pupoVo.getScenceName();
        String linkName = pupoVo.getLinkName();
        String source = pupoVo.getSource();

        List list=null;
        //Page<?> page = PageUtil.startPage(currentPage, total, "-1");
        Page page =PageHelper.startPage(Integer.parseInt(currentPage),Integer.parseInt(total));
        if(date.equals("1")){
            String time=DateTools.getDay();
            AldAdParamVo vo= new AldAdParamVo();
            if (app_key!=null&&!app_key.equals("")){
                vo.setAppkey(app_key);
            }
            vo.setDate(time);
            vo.setCurrentPage(currentPage);
            vo.setTotal(total);
            if(prop!=null&&!prop.equals("")){
                vo.setProp(prop);
            }
            if(order!=null&&!order.equals("")){
                vo.setOrder(order);
            }
            if (typeId!=null&&typeId.equals("1")){//1活动名称
                if (keyword!=null&&!keyword.equals("")){
                    vo.setActivitiesName(keyword);
                }
            }else if (typeId!=null&&typeId.equals("2")){//2渠道名称
                if (keyword!=null&&!keyword.equals("")){
                    vo.setChannel(keyword);
                }
            }else if (typeId!=null&&typeId.equals("3")){//场景值
                if (keyword!=null&&keyword.equals("公众号文章")){
                    vo.setScene("1058");
                }else  if (keyword!=null&&keyword.equals("从另一个小程序返回")){
                    vo.setScene("1038");
                }else   if (keyword!=null&&keyword.equals("公众号自定义菜单")){
                    vo.setScene("1035");
                }else   if (keyword!=null&&keyword.equals("小程序模版消息")){
                    vo.setScene("1014");
                }else   if (keyword!=null&&keyword.equals("其他")){
                    vo.setScene("其他");
                }
            }
            if (linkName!=null&&!linkName.equals("")){//1活动名称
                vo.setActivitiesName(linkName);

            }else if (channelName!=null&&!channelName.equals("")){//2渠道名称
                vo.setChannel(channelName);
            }else if (scenceName!=null&&scenceName.equals("公众号文章")){//渠道名称
                vo.setScene("1058");
            }else  if (scenceName!=null&&scenceName.equals("从另一个小程序返回")){
                vo.setScene("1038");
            }else   if (scenceName!=null&&scenceName.equals("公众号自定义菜单")){
                vo.setScene("1035");
            }else   if (scenceName!=null&&scenceName.equals("小程序模版消息")){
                vo.setScene("1014");
            }else   if (scenceName!=null&&scenceName.equals("其他")){
                vo.setScene("其他");
            }

            if (source!=null&&!source.equals("")&&source.equals("1")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayL(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayC(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("3")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayLC(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,3")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayLS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2,3")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayCS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2,3")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayLCS(vo);
            }
        }else if(date.equals("2")){
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            if (app_key!=null&&!app_key.equals("")){
                vo.setAppkey(app_key);
            }
            vo.setDate(time);
            vo.setCurrentPage(currentPage);
            vo.setTotal(total);
            if(prop!=null&&!prop.equals("")){
                vo.setProp(prop);
            }
            if(order!=null&&!order.equals("")){
                vo.setOrder(order);
            }
            if (typeId!=null&&typeId.equals("1")){//1活动名称
                if (keyword!=null&&!keyword.equals("")){
                    vo.setActivitiesName(keyword);
                }
            }else if (typeId!=null&&typeId.equals("2")){//2渠道名称
                if (keyword!=null&&!keyword.equals("")){
                    vo.setChannel(keyword);
                }
            }
            if (source!=null&&!source.equals("")&&source.equals("1")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayL(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayC(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("3")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayLC(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,3")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayLS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2,3")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayCS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2,3")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTodayAndYesterdayLCS(vo);
            }
        }else if(date.equals("3")){
            AldAdParamVo vo= new AldAdParamVo();
            if (app_key!=null&&!app_key.equals("")){
                vo.setAppkey(app_key);
            }
            String time=DateTools.addDate(-1);
            vo.setDate(time);
            vo.setCurrentPage(currentPage);
            vo.setTotal(total);
            if(prop!=null&&!prop.equals("")){
                vo.setProp(prop);
            }
            if(order!=null&&!order.equals("")){
                vo.setOrder(order);
            }
            if (typeId!=null&&typeId.equals("1")){//1活动名称
                if (keyword!=null&&!keyword.equals("")){
                    vo.setActivitiesName(keyword);
                }
            }else if (typeId!=null&&typeId.equals("2")){//2渠道名称
                if (keyword!=null&&!keyword.equals("")){
                    vo.setChannel(keyword);
                }
            }
            if (source!=null&&!source.equals("")&&source.equals("1")){
                list = aldstat7daysLinkMonitorDao.selectPupoAnalyzeListBy_7_dayL(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2")){
                list = aldstat7daysLinkMonitorDao.selectPupoAnalyzeListBy_7_dayC(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("3")){
                list = aldstat7daysLinkMonitorDao.selectPupoAnalyzeListBy_7_dayS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2")){
                list = aldstat7daysLinkMonitorDao.selectPupoAnalyzeListBy_7_dayLC(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,3")){
                list = aldstat7daysLinkMonitorDao.selectPupoAnalyzeListBy_7_dayLS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2,3")){
                list = aldstat7daysLinkMonitorDao.selectPupoAnalyzeListBy_7_dayCS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2,3")){
                list = aldstat7daysLinkMonitorDao.selectPupoAnalyzeListBy_7_dayLCS(vo);
            }
        }else if(date.equals("4")){
            String time=DateTools.addDate(-1);
            AldAdParamVo vo= new AldAdParamVo();
            if (app_key!=null&&!app_key.equals("")){
                vo.setAppkey(app_key);
            }

            vo.setDate(time);
            vo.setCurrentPage(currentPage);
            vo.setTotal(total);
            if(prop!=null&&!prop.equals("")){
                vo.setProp(prop);
            }
            if(order!=null&&!order.equals("")){
                vo.setOrder(order);
            }
            if (typeId!=null&&typeId.equals("1")){//1活动名称
                if (keyword!=null&&!keyword.equals("")){
                    vo.setActivitiesName(keyword);
                }
            }else if (typeId!=null&&typeId.equals("2")){//2渠道名称
                if (keyword!=null&&!keyword.equals("")){
                    vo.setChannel(keyword);
                }
            }
            if (source!=null&&!source.equals("")&&source.equals("1")){
                list = aldstat30daysLinkMonitorDao.selectPupoAnalyzeListBy_30_dayL(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2")){
                list = aldstat30daysLinkMonitorDao.selectPupoAnalyzeListBy_30_dayC(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("3")){
                list = aldstat30daysLinkMonitorDao.selectPupoAnalyzeListBy_30_dayS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2")){
                list = aldstat30daysLinkMonitorDao.selectPupoAnalyzeListBy_30_dayLC(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,3")){
                list = aldstat30daysLinkMonitorDao.selectPupoAnalyzeListBy_30_dayLS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2,3")){
                list = aldstat30daysLinkMonitorDao.selectPupoAnalyzeListBy_30_dayCS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2,3")){
                list = aldstat30daysLinkMonitorDao.selectPupoAnalyzeListBy_30_dayLCS(vo);
            }
        }else{
            String[] strArr = date.split("~");
            String dateStart=strArr[0];
            String dateEnd=strArr[1];
            AldAdParamVo vo= new AldAdParamVo();
            if (app_key!=null&&!app_key.equals("")){
                vo.setAppkey(app_key);
            }
            vo.setDateStart(dateStart);
            vo.setDateEnd(dateEnd);
            vo.setAppkey(app_key);
            vo.setCurrentPage(currentPage);
            vo.setTotal(total);
            if(prop!=null&&!prop.equals("")){
                vo.setProp(prop);
            }
            if(order!=null&&!order.equals("")){
                vo.setOrder(order);
            }
            if (typeId!=null&&typeId.equals("1")){//1活动名称
                if (keyword!=null&&!keyword.equals("")){
                    vo.setActivitiesName(keyword);
                }
            }else if (typeId!=null&&typeId.equals("2")){//2渠道名称
                if (keyword!=null&&!keyword.equals("")){
                    vo.setChannel(keyword);
                }
            }
            if (source!=null&&!source.equals("")&&source.equals("1")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTimeIntervalL(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTimeIntervalC(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("3")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTimeIntervalS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTimeIntervalLC(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,3")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTimeIntervalLS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2,3")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTimeIntervalCS(vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2,3")){
                list = aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByTimeIntervalLCS(vo);
            }
        }
        PageInfo info = new PageInfo<>(page.getResult());
        //List temp = page.getResult();
        return  info;
    }

    /**
     * 之前的是变量传递参数,现在封装成对象传递,为了aop切 方便
     * @param pupoVo
     * @return
     */
    public List pupoAnalyzeListSomeDayService(PupoAnalyzeListDetailLineVo pupoVo){
        List list=null;
        String app_key = pupoVo.getAppKey();
        String linkName = pupoVo.getLinkName();
        String channelName = pupoVo.getChannelName();
        String scenceName = pupoVo.getScenceName();
        String date = pupoVo.getDate();
        String source = pupoVo.getSource();

        AldAdParamVo vo= new AldAdParamVo();
        if (app_key!=null&&!app_key.equals("")){
            vo.setAppkey(app_key);
        }
        if (linkName!=null&&!linkName.equals("")){//1活动名称
                vo.setActivitiesName(linkName);

        }else if (channelName!=null&&!channelName.equals("")){//2渠道名称
                vo.setChannel(channelName);
        }else if (scenceName!=null&&scenceName.equals("公众号文章")){//渠道名称
                vo.setScene("1058");
        }else  if (scenceName!=null&&scenceName.equals("从另一个小程序返回")){
                vo.setScene("1038");
        }else   if (scenceName!=null&&scenceName.equals("公众号自定义菜单")){
                vo.setScene("1035");
        }else   if (scenceName!=null&&scenceName.equals("小程序模版消息")){
                vo.setScene("1014");
        }else   if (scenceName!=null&&scenceName.equals("其他")){
                vo.setScene("其他");
        }

        if (date.equals("1")){
            String time=DateTools.getDay();
            vo.setDate(time);
            if (source!=null&&!source.equals("")&&source.equals("1")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourL( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourC( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2")){
                list= aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourLC( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,3")){
                list= aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourLS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2,3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourCS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2,3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourLCS( vo);
            }

        } else if (date.equals("2")) {
            String time=DateTools.addDate(-1);
            vo.setDate(time);
            if (source!=null&&!source.equals("")&&source.equals("1")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourL( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourC( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2")){
                list= aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourLC( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,3")){
                list= aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourLS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2,3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourCS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2,3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByHourLCS( vo);
            }
        }else if (date.equals("3")){
            String timeStart=DateTools.addDate(-7);
            String timeEnd=DateTools.addDate(-1);
            vo.setDateStart(timeStart);
            vo.setDateEnd(timeEnd);
            if (source!=null&&!source.equals("")&&source.equals("1")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayL( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayC( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2")){
                list= aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayLC( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,3")){
                list= aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayLS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2,3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayCS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2,3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayLCS( vo);
            }
        }else if (date.equals("4")){
            String timeEnd=DateTools.addDate(-1);
            String timeStart=DateTools.addDate(-30);
            vo.setDateStart(timeStart);
            vo.setDateEnd(timeEnd);
            if (source!=null&&!source.equals("")&&source.equals("1")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayL( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayC( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2")){
                list= aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayLC( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,3")){
                list= aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayLS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2,3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayCS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2,3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayLCS( vo);
            }
        }else {
            String[] strArr = date.split("~");
            String dateStart=strArr[0];
            String dateEnd=strArr[1];
            vo.setDateStart(dateStart);
            vo.setDateEnd(dateEnd);
            if (source!=null&&!source.equals("")&&source.equals("1")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayL( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayC( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2")){
                list= aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayLC( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,3")){
                list= aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayLS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("2,3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayCS( vo);
            }else if (source!=null&&!source.equals("")&&source.equals("1,2,3")){
                list =aldstatDailyLinkMonitorDao.selectPupoAnalyzeListByDayLCS( vo);
            }
        }
        return  list;

    }

}
