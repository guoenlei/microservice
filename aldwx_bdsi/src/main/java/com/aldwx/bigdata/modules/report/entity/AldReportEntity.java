package com.aldwx.bigdata.modules.report.entity;

import com.aldwx.bigdata.common.base.BaseEntity;

/**
 * @author
 * @description
 * @createTime
 **/
public class AldReportEntity extends BaseEntity {


    private long id;
    private long count;


    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
