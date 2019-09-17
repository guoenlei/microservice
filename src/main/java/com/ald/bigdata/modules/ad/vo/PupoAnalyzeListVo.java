package com.ald.bigdata.modules.ad.vo;

import com.ald.bigdata.common.base.BaseVo;

/**
 * Created with IntelliJ IDEA.
 * User: @ziyu  freedomziyua@gmail.com
 * Date: 2019-08-12
 * Time: 17:13
 * Description:
 */
public class PupoAnalyzeListVo extends BaseVo {
    private String date;
    private String source;
    private String scenceName;
    private String keyword;
    private String channelName;
    private String typeId; // 1活动名称 2.渠道名称 3场景值
    private String currentPage;
    private String total;
    private String prop;
    private String order;
    private String linkName;

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public String getCurrentPage() {
        return currentPage;
    }

    @Override
    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String getTotal() {
        return total;
    }

    @Override
    public void setTotal(String total) {
        this.total = total;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

}
