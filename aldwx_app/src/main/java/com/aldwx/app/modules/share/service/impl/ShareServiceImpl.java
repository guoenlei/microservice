package com.aldwx.app.modules.share.service.impl;

import com.aldwx.app.common.base.BaseMethod;
import com.aldwx.app.common.page.PageHandle;
import com.aldwx.app.modules.share.bean.Share;
import com.aldwx.app.modules.share.dao.game.ShareGameDao;
import com.aldwx.app.modules.share.dao.stat.ShareStatDao;
import com.aldwx.app.modules.share.entity.ShareEntity;
import com.aldwx.app.modules.share.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 分享
 * @author
 * @description
 * @createTime
 **/
@Service
@Transactional(readOnly = true)
public class ShareServiceImpl extends BaseMethod implements ShareService {

    @Autowired
    private ShareStatDao shareStatDao;
    @Autowired
    private ShareGameDao shareGameDao;


    @Override
    public ShareEntity queryShareView(Share share) {
        ShareEntity shareEntity=null;
        String appType = share.getAppType();
        if (isStatApp(appType)){
            shareEntity=this.shareStatDao.queryShareView(share);
        }else {
            shareEntity=this.shareGameDao.queryShareView(share);
        }
        return shareEntity;
    }

    /**
     * ald_30days_hierarchy_share
     * ald_7days_hierarchy_share
     * @param share
     * @return
     */
    @Override
    public List<ShareEntity> querySharePie(Share share) {
        List<ShareEntity> list=null;
        String appType=share.getAppType();
        share.setTableName("ald_hierarchy_share");
        if (isStatApp(appType)){
            list =this.shareStatDao.querySharePie(share);
        }else {
            list=this.shareGameDao.querySharePie(share);
        }
        return list;
    }

    @Override
    public List<ShareEntity> queryShareChart(Share share) {
        List<ShareEntity> list=null;
        String appType=share.getAppType();
        if (isStatApp(appType)){
            if (share.getDate().equals("1") || share.getDate().equals("2")){
                list=this.shareStatDao.queryShareHourChart(share);
            }else {
                list=this.shareStatDao.queryShareDayChart(share);
            }
        }else {
            if (share.getDate().equals("1") || share.getDate().equals("2")){
                list=this.shareGameDao.queryShareHourChart(share);
            }else {
                list=this.shareGameDao.queryShareDayChart(share);
            }
        }
        return list;
    }

    @Override
    public List<ShareEntity> queryShareList(Share share) {
        List<ShareEntity> list=null;
        String appType=share.getAppType();
        if (share.getDate().equals("1")||share.getDate().equals("2")){
            share.setTableName("aldstat_dailyshare_top10");
        }else if (share.getDate().equals("3")){
            share.setTableName("aldstat_7days_userShare_top10");
        }else if (share.getDate().equals("4")){
            share.setTableName("aldstat_30days_userShare_top10");
        }
        PageHandle.startPage(share.getCurrentPage(), share.getPageSize());
        if (isStatApp(appType)){
           list=this.shareStatDao.queryShareList(share);
        }else {
           list=this.shareGameDao.queryShareList(share);
        }
        return list;
    }

    @Override
    public List<ShareEntity> queryShareInfo(Share share) {
        List<ShareEntity> list=null;
        String appType=share.getAppType();
        if (isStatApp(appType)){
            if (share.getType().equals("1")){
                share.setTableName("aldstat_dailyshare_gender");
                list=this.shareStatDao.queryShareSexInfo(share);
            }else if (share.getType().equals("2")){
                share.setTableName("aldstat_dailyshare_source");
                list=this.shareStatDao.queryShareSourceInfo(share);
            }else if (share.getType().equals("3")){
                share.setTableName("aldstat_dailyshare_city");
                list=this.shareStatDao.queryShareCityInfo(share);
            }
        }else {
            if (share.getType().equals("1")){
                share.setTableName("aldstat_dailyshare_gender");
                list=this.shareGameDao.queryShareSexInfo(share);
            }else if (share.getType().equals("2")){
                share.setTableName("aldstat_dailyshare_source");
                list=this.shareGameDao.queryShareSourceInfo(share);
            }else if (share.getType().equals("3")){
                share.setTableName("aldstat_dailyshare_city");
                list=this.shareGameDao.queryShareCityInfo(share);
            }
        }
        return list;
    }
}
