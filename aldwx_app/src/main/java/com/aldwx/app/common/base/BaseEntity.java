package com.aldwx.app.common.base;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

    private long id;
    private Date createAt;
    private Date updateAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
