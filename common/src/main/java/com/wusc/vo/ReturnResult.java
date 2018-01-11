package com.wusc.vo;

import java.io.Serializable;

/**
 * create by wusc on 2017/12/20
 */
public class ReturnResult {
    private int code;
    private Object data;

    public ReturnResult(){

    }
    public ReturnResult(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
