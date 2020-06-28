package com.aldwx.app.modules.funnel.entity;

import com.aldwx.app.common.base.BaseEntity;

/**
 * 漏斗
 * @author
 * @description
 * @createTime
 **/
public class FunnelEntity extends BaseEntity {
    /**
     * @Result(property = "day", column = "day"),
     *                     @Result(property = "funnelKey", column = "funnel_key"),
     *                     @Result(property = "firstEventKey", column = "first_event_key"),
     *                     @Result(property = "firstEventAliasRes", column = "first_event_alias_res"),
     *                     @Result(property = "fuv", column = "fuv"),
     *                     @Result(property = "lastEventKey", column = "last_event_key"),
     *                     @Result(property = "lastEventAliasRes", column = "last_event_alias_res"),
     *                     @Result(property = "luv", column = "luv"),
     *                     @Result(property = "conv", column = "conv"),
     */
    private String funnelKey;
    private String firstEventKey;
    private String firstEventAliasRes;
    private long fuv;
    private String lastEventKey;
    private String lastEventAliasRes;
    private long luv;
    private double conv;

    public String getFunnelKey() {
        return funnelKey;
    }

    public void setFunnelKey(String funnelKey) {
        this.funnelKey = funnelKey;
    }

    public String getFirstEventKey() {
        return firstEventKey;
    }

    public void setFirstEventKey(String firstEventKey) {
        this.firstEventKey = firstEventKey;
    }

    public String getFirstEventAliasRes() {
        return firstEventAliasRes;
    }

    public void setFirstEventAliasRes(String firstEventAliasRes) {
        this.firstEventAliasRes = firstEventAliasRes;
    }

    public long getFuv() {
        return fuv;
    }

    public void setFuv(long fuv) {
        this.fuv = fuv;
    }

    public String getLastEventKey() {
        return lastEventKey;
    }

    public void setLastEventKey(String lastEventKey) {
        this.lastEventKey = lastEventKey;
    }

    public String getLastEventAliasRes() {
        return lastEventAliasRes;
    }

    public void setLastEventAliasRes(String lastEventAliasRes) {
        this.lastEventAliasRes = lastEventAliasRes;
    }

    public long getLuv() {
        return luv;
    }

    public void setLuv(long luv) {
        this.luv = luv;
    }

    public double getConv() {
        return conv;
    }

    public void setConv(double conv) {
        this.conv = conv;
    }
}
