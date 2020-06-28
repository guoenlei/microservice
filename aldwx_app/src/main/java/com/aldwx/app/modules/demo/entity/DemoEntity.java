package com.aldwx.app.modules.demo.entity;

/**
 * @author
 * @description
 * @createTime
 **/
public class DemoEntity {

    private String uname;       //型号
    private String name;        //机型
    private String brand;       //品牌
    private String update_at;   //更新时间

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name    = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}
