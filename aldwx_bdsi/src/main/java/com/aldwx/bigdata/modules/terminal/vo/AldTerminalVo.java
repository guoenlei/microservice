package com.aldwx.bigdata.modules.terminal.vo;

import com.aldwx.bigdata.common.base.BaseVo;

import java.util.List;


public class AldTerminalVo extends BaseVo {

    private String date;
    private String type;
    private String module;
    private String appKey;

    private List<String> list;

    public AldTerminalVo(String date, String type, String module, String appKey) {
        this.date = date;
        this.type = type;
        this.module = module;
        this.appKey = appKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}