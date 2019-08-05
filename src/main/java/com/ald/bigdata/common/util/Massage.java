package com.ald.bigdata.common.util;

public class Massage {

    private String massage;
    private Integer code;

    public Massage() {}

    public Massage(String massage, Integer code) {
        this.massage = massage;
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
