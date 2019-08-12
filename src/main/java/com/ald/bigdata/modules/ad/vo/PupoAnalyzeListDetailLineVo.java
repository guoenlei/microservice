package com.ald.bigdata.modules.ad.vo;

import com.ald.bigdata.common.base.BaseVo;

/**
 * Created with IntelliJ IDEA.
 * User: @ziyu  freedomziyua@gmail.com
 * Date: 2019-08-12
 * Time: 17:01
 * Description:PupoAnalyzeListDetailLine 接口的Vo类,用于参数转换
 */
public class PupoAnalyzeListDetailLineVo extends BaseVo{

    private String date;
    private String source;
    private String scenceName;
    private String linkName;
    private String channelName;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getScenceName() {
        return scenceName;
    }

    public void setScenceName(String scenceName) {
        this.scenceName = scenceName;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
