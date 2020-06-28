package com.aldwx.bigdata.modules.ad.entity;

import com.aldwx.bigdata.common.base.BaseEntity;

public class AldAdDataEntity extends BaseEntity {


    private String appKey;
    private String day;
    private String hour;

    private long openPopuCount;         //推广量
    private long openNatuCount;         //自然量
    private long openTotalCount;        //总量
    private long newPopuCount;         //推广量
    private long newNatuCount;         //自然量
    private long newTotalCount;        //总量
    private long visitorPopuCount;         //推广量
    private long visitorNatuCount;         //自然量
    private long visitorTotalCount;        //总量

    private long popuCount;
    private long natuCount;
    private long totalCount;


    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public long getOpenPopuCount() {
        return openPopuCount;
    }

    public void setOpenPopuCount(long openPopuCount) {
        this.openPopuCount = openPopuCount;
    }

    public long getOpenNatuCount() {
        return openNatuCount;
    }

    public void setOpenNatuCount(long openNatuCount) {
        this.openNatuCount = openNatuCount;
    }

    public long getOpenTotalCount() {
        return openTotalCount;
    }

    public void setOpenTotalCount(long openTotalCount) {
        this.openTotalCount = openTotalCount;
    }

    public long getNewPopuCount() {
        return newPopuCount;
    }

    public void setNewPopuCount(long newPopuCount) {
        this.newPopuCount = newPopuCount;
    }

    public long getNewNatuCount() {
        return newNatuCount;
    }

    public void setNewNatuCount(long newNatuCount) {
        this.newNatuCount = newNatuCount;
    }

    public long getNewTotalCount() {
        return newTotalCount;
    }

    public void setNewTotalCount(long newTotalCount) {
        this.newTotalCount = newTotalCount;
    }

    public long getVisitorPopuCount() {
        return visitorPopuCount;
    }

    public void setVisitorPopuCount(long visitorPopuCount) {
        this.visitorPopuCount = visitorPopuCount;
    }

    public long getVisitorNatuCount() {
        return visitorNatuCount;
    }

    public void setVisitorNatuCount(long visitorNatuCount) {
        this.visitorNatuCount = visitorNatuCount;
    }

    public long getVisitorTotalCount() {
        return visitorTotalCount;
    }

    public void setVisitorTotalCount(long visitorTotalCount) {
        this.visitorTotalCount = visitorTotalCount;
    }

    public long getPopuCount() {
        return popuCount;
    }

    public void setPopuCount(long popuCount) {
        this.popuCount = popuCount;
    }

    public long getNatuCount() {
        return natuCount;
    }

    public void setNatuCount(long natuCount) {
        this.natuCount = natuCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
