package com.aldwx.app.modules.share.service;


import com.aldwx.app.modules.share.bean.Share;
import com.aldwx.app.modules.share.entity.ShareEntity;

import java.util.List;

/**
 * 分享
 */
public interface ShareService {



    ShareEntity queryShareView(Share share);



    List<ShareEntity> querySharePie(Share share);




    List<ShareEntity> queryShareChart(Share share);




    List<ShareEntity> queryShareList(Share share);




    List<ShareEntity> queryShareInfo(Share share);



}
