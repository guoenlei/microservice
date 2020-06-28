package com.aldwx.app.modules.portrait.service.impl;

import com.aldwx.app.common.util.ResultUtil;
import com.aldwx.app.common.util.StringUtil;
import com.aldwx.app.modules.portrait.dao.game.PortraitGameCityDao;
import com.aldwx.app.modules.portrait.dao.game.PortraitGameModelDao;
import com.aldwx.app.modules.portrait.dao.game.PortraitGameTerminalDAO;
import com.aldwx.app.modules.portrait.dao.stat.PortraitCityDao;
import com.aldwx.app.modules.portrait.dao.stat.PortraitModelDao;
import com.aldwx.app.modules.portrait.dao.stat.PortraitTerminalDAO;
import com.aldwx.app.modules.portrait.entity.AldDeviceStatistics;
import com.aldwx.app.modules.portrait.entity.AldstatCityStatistics;
import com.aldwx.app.modules.portrait.entity.AldstatTerminalAnalysis;
import com.aldwx.app.modules.portrait.service.PortraitService;
import com.aldwx.app.modules.portrait.vo.PortraitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional(readOnly = true)
public class PortraitServiceImpl implements PortraitService {
    @Autowired
    private PortraitCityDao portraitCityDao;
    @Autowired
    private PortraitModelDao portraitModelDao;
    @Autowired
    private PortraitTerminalDAO portraitTerminalDAO;


    @Autowired
    private PortraitGameCityDao portraitGameCityDao;
    @Autowired
    private PortraitGameModelDao portraitGameModelDao;
    @Autowired
    private PortraitGameTerminalDAO portraitGameTerminalDAO;
    //地域分布top 10列表
    public Map<String, Object> selectCityTop(PortraitVo vo){
        Map<String,Object> map=new HashMap<>();
        try{
            Map<String,String> map1=null;
            //按城市列表
            List<AldstatCityStatistics> list2=null;
            //汇总
            List<AldstatCityStatistics> list=null;

            if (vo.getAppType().equals("1")){
                list2=this.portraitCityDao.selectCityList(vo);
                list=this.portraitCityDao.selectCityTotal(vo);
            }else if (vo.getAppType().equals("2")){
                list2=this.portraitGameCityDao.selectCityList(vo);
                list=this.portraitGameCityDao.selectCityTotal(vo);

            }
            List listResult1 = null;
            if (list2!=null&&list2.size()>0&&list!=null&&list.size()>0){
                listResult1 = new ArrayList();
                for (int i=0;i<list2.size();i++){
                    map1=new HashMap<>();
                    AldstatCityStatistics ald= list2.get(i);
                    ald.getNew_user_count();
                    if (vo.getDataType().equals("1")){

                        map1.put("userCount",ald.getNew_user_count().toString());
                        map1.put("city",ald.getCity());
                        if (list.get(0).getNew_user_count()!=0){
                            Double a= ald.getNew_user_count()*1.0d/list.get(0).getNew_user_count();
                            map1.put("rate",StringUtil.formatPercent2(a));
                        }else {
                            map1.put("rate","0%");
                        }
                    }else if (vo.getDataType().equals("2")){

                        map1.put("visitorCount",ald.getVisitor_count().toString());
                        map1.put("city",ald.getCity());
                        if (list.get(0).getVisitor_count()!=0){
                            Double a= ald.getVisitor_count()*1.0d/list.get(0).getVisitor_count();
                            map1.put("rate",StringUtil.formatPercent2(a));
                        }else {
                            map1.put("rate","0%");
                        }
                    }
                    listResult1.add(map1);
                }
                map.put("data",listResult1);
                map=ResultUtil.success(map);
            }else {
                map.put("data",null);
                map=ResultUtil.nodata(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //地域分布明细
    public Map<String, Object> selectCityDetail(PortraitVo vo){
        Map<String,Object> map=new HashMap<>();
        try{
            //按城市列表
            List<AldstatCityStatistics> list2=null;
            //汇总
            List<AldstatCityStatistics> list=null;
            if (vo.getAppType().equals("1")){
                list2=this.portraitCityDao.selectCityList(vo);
                list=this.portraitCityDao.selectCityTotal(vo);
            }else if (vo.getAppType().equals("2")){
                list2=this.portraitGameCityDao.selectCityList(vo);
                list=this.portraitGameCityDao.selectCityTotal(vo);
            }
            List listResult1 = null;
            Map<String,String> map1=null;
            if (list2!=null&&list2.size()>0&&list!=null&&list.size()>0){
                listResult1 = new ArrayList();
                for (int i=0;i<list2.size();i++){
                    map1=new HashMap<>();
                    AldstatCityStatistics ald= list2.get(i);
                    ald.getNew_user_count();
                    map1.put("city",ald.getCity());
                    map1.put("userCount",ald.getNew_user_count().toString());
                    if (list.get(0).getNew_user_count()!=0){
                        // 线上新用户占比计算方法（selectCityList的新访问用户数 / selectCityTotal的新访问用户数）
                        // 小程序：按城市分组，找出新用户总数最多的城市对应的新用户总数 / 所有城市新用户总数
                        // 小游戏：访问总人数排第一的
                        Double a= ald.getNew_user_count()*1.0d/list.get(0).getNew_user_count();
                        map1.put("rate",StringUtil.formatPercent2(a));
                    }else {
                        map1.put("rate","0%");
                    }
                    map1.put("visitorCount",ald.getVisitor_count().toString());
                    listResult1.add(map1);
                }
                map.put("data",listResult1);
                map=ResultUtil.success(map);
            }else {
                map.put("data",listResult1);
                map=ResultUtil.nodata(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //机型分布top10
    public Map<String, Object> selectModelTop(PortraitVo vo){
        Map<String,Object> map=new HashMap<>();
        try{
            //列表by机型
            List<AldDeviceStatistics> list2=null;
            //汇总
            List<AldDeviceStatistics> list=null;
            if (vo.getAppType().equals("1")){
                list2=this.portraitModelDao.selectModelList(vo);
                list=this.portraitModelDao.selectModelTotal(vo);
            }else if (vo.getAppType().equals("2")){
                list2=this.portraitGameModelDao.selectModelList(vo);
                list=this.portraitGameModelDao.selectModelTotal(vo);
            }
            Map<String,String> map1=null;
            List listResult1 = null;
            if (list2!=null&&list2.size()>0&&list!=null&&list.size()>0){
                listResult1 = new ArrayList();
                for (int i=0;i<list2.size();i++){
                    map1=new HashMap<>();
                    AldDeviceStatistics ald= list2.get(i);
                    if (vo.getDataType().equals("1")){
                        map1.put("userCount",ald.getNew_user_count().toString());
                        map1.put("phoneModel",ald.getPhone_model());
                        if (list.get(0).getNew_user_count()!=0){
                            Double a= ald.getNew_user_count()*1.0d/list.get(0).getNew_user_count();
                            map1.put("rate",StringUtil.formatPercent2(a));
                        }else {
                            map1.put("rate","0%");
                        }
                    }else if (vo.getDataType().equals("2")){
                        map1.put("visitorCount",ald.getVisitor_count().toString());
                        map1.put("phoneModel",ald.getPhone_model());
                        if (list.get(0).getVisitor_count()!=0){
                            Double a= ald.getVisitor_count()*1.0d/list.get(0).getVisitor_count();
                            map1.put("rate",StringUtil.formatPercent2(a));
                        }else {
                            map1.put("rate","0%");
                        }
                    }
                    listResult1.add(map1);

                }
                map.put("data",listResult1);
                map=ResultUtil.success(map);
            }else {
                map.put("data",listResult1);
                map=ResultUtil.nodata(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  map;
    }
    //机型分布设备明细
    public Map<String, Object> selectModelDetail(PortraitVo vo){
        Map<String,Object> map=new HashMap<>();
        try{
            //列表by机型
            List<AldDeviceStatistics> list2=null;
            //汇总
            List<AldDeviceStatistics> list=null;
            if (vo.getAppType().equals("1")){
                list2=this.portraitModelDao.selectModelList(vo);
                list=this.portraitModelDao.selectModelTotal(vo);
            }else if (vo.getAppType().equals("2")){
                list2=this.portraitGameModelDao.selectModelList(vo);
                list=this.portraitGameModelDao.selectModelTotal(vo);
            }
            Map<String,String> map1=null;
            List listResult1 = null;
            if (list2!=null&&list2.size()>0&&list!=null&&list.size()>0){
                listResult1 = new ArrayList();

                for (int i=0;i<list2.size();i++){
                    map1=new HashMap<>();
                    AldDeviceStatistics ald= list2.get(i);
                    ald.getNew_user_count();
                    map1.put("phoneModel",ald.getPhone_model());
                    map1.put("userCount",ald.getNew_user_count().toString());
                    if (list.get(0).getNew_user_count()!=0){
                        Double a= ald.getNew_user_count()*1.0d/list.get(0).getNew_user_count();
                        map1.put("rate",StringUtil.formatPercent2(a));
                    }else {
                        map1.put("rate","0%");
                    }
                    map1.put("visitorCount",ald.getVisitor_count().toString());
                    listResult1.add(map1);

                }
                map.put("data",listResult1);
                map=ResultUtil.success(map);
            }else {
                map.put("data",listResult1);
                map=ResultUtil.nodata(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  map;
    }
    //终端分布top10
    public Map<String, Object> selectTerminalTop(PortraitVo vo){
        Map<String,Object> map=new HashMap<>();
        try{
            //列表by机型
            List<AldstatTerminalAnalysis> list2=null;
            //汇总
            List<AldstatTerminalAnalysis> list=null;
            if (vo.getAppType().equals("1")){
                list2=this.portraitTerminalDAO.selectTerminalList(vo);
                //汇总
                list=this.portraitTerminalDAO.selectTerminalTotal(vo);
            }else if (vo.getAppType().equals("2")){
                list2=this.portraitGameTerminalDAO.selectTerminalList(vo);
                //汇总
                list=this.portraitGameTerminalDAO.selectTerminalTotal(vo);
            }
            Map<String,String> map1=null;
            List listResult1 = null;
            if (list2!=null&&list2.size()>0&&list!=null&&list.size()>0){
                listResult1 = new ArrayList();
                for (int i=0;i<list2.size();i++){
                    map1=new HashMap<>();
                    AldstatTerminalAnalysis ald= list2.get(i);
                    if (vo.getDataType().equals("1")){

                        map1.put("userCount",ald.getNew_comer_count().toString());
                        map1.put("type",ald.getType_values());
                        if (list.get(0).getNew_comer_count()!=0){
                            Double a= ald.getNew_comer_count()*1.0d/list.get(0).getNew_comer_count();
                            map1.put("rate",StringUtil.formatPercent2(a));
                        }else {
                            map1.put("rate","0%");
                        }
                    }else if (vo.getDataType().equals("2")){

                        map1.put("visitorCount",ald.getVisitor_count().toString());
                        map1.put("type",ald.getType_values());
                        if (list.get(0).getVisitor_count()!=0){
                            Double a= ald.getVisitor_count()*1.0d/list.get(0).getVisitor_count();
                            map1.put("rate",StringUtil.formatPercent2(a));
                        }else {
                            map1.put("rate","0%");
                        }
                    }
                    listResult1.add(map1);
                }
                map.put("data",listResult1);
                map=ResultUtil.success(map);
            }else {
                map.put("data",listResult1);
                map=ResultUtil.nodata(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  map;
    }
    //终端分布设备明细
    public Map<String, Object> selectTerminalDetail(PortraitVo vo){
        Map<String,Object> map=new HashMap<>();
        try{
            //列表by机型
            List<AldstatTerminalAnalysis> list2=null;
            //汇总
            List<AldstatTerminalAnalysis> list=null;
            if (vo.getAppType().equals("1")){
                list2=this.portraitTerminalDAO.selectTerminalList(vo);
                //汇总
                list=this.portraitTerminalDAO.selectTerminalTotal(vo);
            }else if (vo.getAppType().equals("2")){
                list2=this.portraitGameTerminalDAO.selectTerminalList(vo);
                //汇总
                list=this.portraitGameTerminalDAO.selectTerminalTotal(vo);
            }
            Map<String,String> map1=null;
            List listResult1 = null;
            if (list2!=null&&list2.size()>0&&list!=null&&list.size()>0){
                listResult1 = new ArrayList();
                for (int i=0;i<list2.size();i++){
                    map1=new HashMap<>();
                    AldstatTerminalAnalysis ald= list2.get(i);
                    map1.put("type",ald.getType_values());
                    map1.put("userCount",ald.getNew_comer_count().toString());
                    if (list.get(0).getNew_comer_count()!=0){
                        Double a= ald.getNew_comer_count()*1.0d/list.get(0).getNew_comer_count();
                        map1.put("rate",StringUtil.formatPercent2(a));
                    }else {
                        map1.put("rate","0%");
                    }
                    map1.put("visitorCount",ald.getVisitor_count().toString());
                    listResult1.add(map1);
                }
                map.put("data",listResult1);
                map=ResultUtil.success(map);
            }else {
                map.put("data",listResult1);
                map=ResultUtil.nodata(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  map;
    }
    //网络分布top10
    public Map<String, Object> selectNetTop(PortraitVo vo){
        Map<String,Object> map=new HashMap<>();
        try{
            //列表by机型
            List<AldstatTerminalAnalysis> list2=null;
            //汇总
            List<AldstatTerminalAnalysis> list=null;
            if (vo.getAppType().equals("1")){
                list2=this.portraitTerminalDAO.selectNetList(vo);
                //汇总
                list=this.portraitTerminalDAO.selectNetTotal(vo);
            }else if (vo.getAppType().equals("2")){
                list2=this.portraitGameTerminalDAO.selectNetList(vo);
                //汇总
                list=this.portraitGameTerminalDAO.selectNetTotal(vo);
            }
            Map<String,String> map1=null;
            List listResult1 = null;
            if (list2!=null&&list2.size()>0&&list!=null&&list.size()>0){
                listResult1 = new ArrayList();
                for (int i=0;i<list2.size();i++){
                    map1=new HashMap<>();
                    AldstatTerminalAnalysis ald= list2.get(i);
                    map1.put("type",ald.getType_values());
                    if (vo.getDataType().equals("1")){
                        map1.put("userCount",ald.getNew_comer_count().toString());
                        if (list.get(0).getNew_comer_count()!=0){
                            Double a= ald.getNew_comer_count()*1.0d/list.get(0).getNew_comer_count();
                            map1.put("rate",StringUtil.formatPercent2(a));
                        }else {
                            map1.put("rate","0%");
                        }
                    }else if (vo.getDataType().equals("2")){
                        map1.put("visitorCount",ald.getVisitor_count().toString());
                        if (list.get(0).getVisitor_count()!=0){
                            Double a= ald.getVisitor_count()*1.0d/list.get(0).getVisitor_count();
                            map1.put("rate",StringUtil.formatPercent2(a));
                        }else {
                            map1.put("rate","0%");
                        }
                    }
                    listResult1.add(map1);
                }
                map.put("data",listResult1);
                map=ResultUtil.success(map);
            }else {
                map.put("data",listResult1);
                map=ResultUtil.nodata(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  map;
    }
    //网络分布设备明细
    public Map<String, Object> selectNetDetail(PortraitVo vo){
        Map<String,Object> map=new HashMap<>();
        try{
            //列表by机型
            List<AldstatTerminalAnalysis> list2=null;
            //汇总
            List<AldstatTerminalAnalysis> list=null;
            if (vo.getAppType().equals("1")){
                list2=this.portraitTerminalDAO.selectNetList(vo);
                //汇总
                list=this.portraitTerminalDAO.selectNetTotal(vo);
            }else if (vo.getAppType().equals("2")){
                list2=this.portraitGameTerminalDAO.selectNetList(vo);
                //汇总
                list=this.portraitGameTerminalDAO.selectNetTotal(vo);
            }
            Map<String,String> map1=null;
            List listResult1 = null;
            if (list2!=null&&list2.size()>0&&list!=null&&list.size()>0){
                listResult1 = new ArrayList();
                for (int i=0;i<list2.size();i++){
                    map1=new HashMap<>();
                    AldstatTerminalAnalysis ald= list2.get(i);
                    map1.put("type",ald.getType_values());
                    map1.put("userCount",ald.getNew_comer_count().toString());
                    if (list.get(0).getNew_comer_count()!=0){
                        Double a= ald.getNew_comer_count()*1.0d/list.get(0).getNew_comer_count();
                        map1.put("rate",StringUtil.formatPercent2(a));
                    }else {
                        map1.put("rate","0%");
                    }
                    map1.put("visitorCount",ald.getVisitor_count().toString());
                    listResult1.add(map1);
                }
                map.put("data",listResult1);
                map=ResultUtil.success(map);
            }else {
                map.put("data",listResult1);
                map=ResultUtil.nodata(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  map;
    }
}
