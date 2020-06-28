package com.aldwx.bigdata.modules.ad.entity;

import java.io.Serializable;
import java.util.Date;

/** 
 *
 * 
aldstat_daily_link_monitor
 *
 *  
 */
public class AldstatDailyLinkMonitor implements Serializable {
    /** 自增id **/
    private Long id;

    /** 小程序 Key **/
    private String appKey;

    /** 日期 **/
    private Date day;

    /** 小时区间,0-23,分别对应(0:00,0:59),(1:00,1:59)... **/
    private String hour;

    /** 外链的 key **/
    private String wsrQueryAldLinkKey;

    /** 媒体 ID **/
    private String wsrQueryAldMediaId;

    /** 位置 ID **/
    private String agAldPositionId;

    /** 访问人数 **/
    private Integer visitorCount;

    /** 新增授权人数 **/
    private Integer authuserCount;

    /** 打开次数 **/
    private Integer openCount;

    /** 访问次数 **/
    private Integer totalPageCount;

    /** 为小程序带来新增用户数 **/
    private Integer newComerCount;

    /** 总停留时长 **/
    private Integer totalStayTime;

    /** 次均停留时长 **/
    private Float secondaryAvgStayTime;

    /** 跳出页个数 **/
    private Integer onePageCount;

    /** 跳出率 **/
    private Float bounceRate;

    /** 新增用户次日留存 **/
    private Float newPeopleRatio;

    /** 新增授权次日留存 **/
    private Float authuserPeopleRatio;

    /** 更新时间 **/
    private Date updateAt;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getWsrQueryAldLinkKey() {
        return wsrQueryAldLinkKey;
    }

    public void setWsrQueryAldLinkKey(String wsrQueryAldLinkKey) {
        this.wsrQueryAldLinkKey = wsrQueryAldLinkKey;
    }

    public String getWsrQueryAldMediaId() {
        return wsrQueryAldMediaId;
    }

    public void setWsrQueryAldMediaId(String wsrQueryAldMediaId) {
        this.wsrQueryAldMediaId = wsrQueryAldMediaId;
    }

    public String getAgAldPositionId() {
        return agAldPositionId;
    }

    public void setAgAldPositionId(String agAldPositionId) {
        this.agAldPositionId = agAldPositionId;
    }

    public Integer getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(Integer visitorCount) {
        this.visitorCount = visitorCount;
    }

    public Integer getAuthuserCount() {
        return authuserCount;
    }

    public void setAuthuserCount(Integer authuserCount) {
        this.authuserCount = authuserCount;
    }

    public Integer getOpenCount() {
        return openCount;
    }

    public void setOpenCount(Integer openCount) {
        this.openCount = openCount;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Integer getNewComerCount() {
        return newComerCount;
    }

    public void setNewComerCount(Integer newComerCount) {
        this.newComerCount = newComerCount;
    }

    public Integer getTotalStayTime() {
        return totalStayTime;
    }

    public void setTotalStayTime(Integer totalStayTime) {
        this.totalStayTime = totalStayTime;
    }

    public Float getSecondaryAvgStayTime() {
        return secondaryAvgStayTime;
    }

    public void setSecondaryAvgStayTime(Float secondaryAvgStayTime) {
        this.secondaryAvgStayTime = secondaryAvgStayTime;
    }

    public Integer getOnePageCount() {
        return onePageCount;
    }

    public void setOnePageCount(Integer onePageCount) {
        this.onePageCount = onePageCount;
    }

    public Float getBounceRate() {
        return bounceRate;
    }

    public void setBounceRate(Float bounceRate) {
        this.bounceRate = bounceRate;
    }

    public Float getNewPeopleRatio() {
        return newPeopleRatio;
    }

    public void setNewPeopleRatio(Float newPeopleRatio) {
        this.newPeopleRatio = newPeopleRatio;
    }

    public Float getAuthuserPeopleRatio() {
        return authuserPeopleRatio;
    }

    public void setAuthuserPeopleRatio(Float authuserPeopleRatio) {
        this.authuserPeopleRatio = authuserPeopleRatio;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appKey=").append(appKey);
        sb.append(", day=").append(day);
        sb.append(", hour=").append(hour);
        sb.append(", wsrQueryAldLinkKey=").append(wsrQueryAldLinkKey);
        sb.append(", wsrQueryAldMediaId=").append(wsrQueryAldMediaId);
        sb.append(", agAldPositionId=").append(agAldPositionId);
        sb.append(", visitorCount=").append(visitorCount);
        sb.append(", authuserCount=").append(authuserCount);
        sb.append(", openCount=").append(openCount);
        sb.append(", totalPageCount=").append(totalPageCount);
        sb.append(", newComerCount=").append(newComerCount);
        sb.append(", totalStayTime=").append(totalStayTime);
        sb.append(", secondaryAvgStayTime=").append(secondaryAvgStayTime);
        sb.append(", onePageCount=").append(onePageCount);
        sb.append(", bounceRate=").append(bounceRate);
        sb.append(", newPeopleRatio=").append(newPeopleRatio);
        sb.append(", authuserPeopleRatio=").append(authuserPeopleRatio);
        sb.append(", updateAt=").append(updateAt);
        sb.append("]");
        return sb.toString();
    }
}