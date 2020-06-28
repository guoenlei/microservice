package com.aldwx.app.modules.share.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.common.util.CurrencyVo;
import com.aldwx.app.common.util.StringUtil;
import com.aldwx.app.common.util.VoUtil;
import com.aldwx.app.modules.share.assist.ShareAssist;
import com.aldwx.app.modules.share.bean.Share;
import com.aldwx.app.modules.share.entity.ShareEntity;
import com.aldwx.app.modules.share.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 分享
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value="aldstat/app/share")
public class ShareController extends BaseController {


    @Autowired
    private ShareService shareService;



    /**
     * 行为 - 分享分析 - 概览
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/view", method = RequestMethod.POST)
    public String queryShareDataView(String app_key, String date, String app_type) {
        ShareEntity shareEntityList = this.shareService.queryShareView(new Share(app_key, date, app_type));
        return resultJosn4(shareEntityList);
    }





    /**
     * 行为 - 分享分析 层级明细 - 饼图
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/pie", method = RequestMethod.POST)
    public String queryShareDataDetail(String app_key, String date, String app_type, String type) {
        List<ShareEntity> shareEntityList =  this.shareService.querySharePie(new Share(app_key, date, app_type));
        return resultJosn4(ShareAssist.formatPie(shareEntityList, type));
    }





    /**
     * 行为 - 分享分析 分享趋势 - 折线图
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/trend", method = RequestMethod.POST)
    public String queryShareDataChart(String app_key, String date, String app_type) {
        CurrencyVo vo=VoUtil.getCurrencyVo(app_key,date);
        List<ShareEntity> shareEntityList =  this.shareService.queryShareChart(new Share(app_key, date, app_type));
        return resultJosn4(ShareAssist.formatChart(shareEntityList, date,vo));
    }


    /**
     * 行为 - 分享分析 TOP10关键用户 - 列表
     * @param app_key
     * @param date
     * @param app_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public String queryImplUserList(String app_key, String date, String app_type, String type) {
        List<ShareEntity> shareEntityList = this.shareService.queryShareList(new Share(app_key, date, app_type,type));
        return resultJosn4(ShareAssist.formatList(shareEntityList));
    }


    /**
     * 行为 - 分享者用户画像 - 画像
     * @param app_key
     * @param date
     * @param app_type
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/info", method = RequestMethod.POST)
    public String queryShareUserInfo(String app_key, String date, String app_type, String type) {
        List<ShareEntity> shareEntityList = this.shareService.queryShareInfo(new Share(app_key, date, app_type,type));
        List<ShareEntity> shareEntityList2=new ArrayList<>();
        if (type.equals("1")){
            ShareEntity shareEntity1=new ShareEntity();
            ShareEntity shareEntity2=new ShareEntity();
            ShareEntity shareEntity3=new ShareEntity();
            if (shareEntityList!=null&&shareEntityList.size()>0){
                int man=0;
                int women=0;
                int unknown=0;
                for (ShareEntity share: shareEntityList) {
                    if (share.getGender().equals("男")){
                        if (share.getShare_user_count()!=null){
                            man=share.getShare_user_count();
                        }
                    }
                    if (share.getGender().equals("女")){
                        if (share.getShare_user_count()!=null){
                            women=share.getShare_user_count();
                        }
                    }
                    if (share.getGender().equals("未知")){
                        if (share.getShare_user_count()!=null){
                            unknown=share.getShare_user_count();
                        }
                    }
                }

                if (man+women+unknown==0){
                    shareEntity1.setGender("男");
                    shareEntity1.setManrate("0%");
                    shareEntity2.setGender("女");
                    shareEntity2.setWomenrate("0%");
                    shareEntity3.setGender("未知");
                    shareEntity3.setUnknownrate("0%");
                }else {
                    shareEntity1.setGender("男");
                    shareEntity1.setManrate(StringUtil.formatPercent2(man*1.0/(man+women+unknown)));
                    shareEntity2.setGender("女");
                    shareEntity2.setWomenrate(StringUtil.formatPercent2(women*1.0/(man+women+unknown)));
                    shareEntity3.setGender("未知");
                    shareEntity3.setUnknownrate(StringUtil.formatPercent2(unknown*1.0/(man+women+unknown)));
                }
            }else {
                shareEntity1.setGender("男");
                shareEntity1.setManrate("0%");
                shareEntity2.setGender("女");
                shareEntity2.setWomenrate("0%");
                shareEntity3.setGender("未知");
                shareEntity3.setUnknownrate("0%");
            }
            shareEntityList2.add(shareEntity1);
            shareEntityList2.add(shareEntity2);
            shareEntityList2.add(shareEntity3);
            shareEntityList=shareEntityList2;
        }
        return resultJosn4(shareEntityList);
    }



}
