package com.wusc.redis.param;

import java.io.Serializable;

public class ParamWapper implements Serializable{
    private String method;
    private Class<?>[] clazzs;
    private Object[] params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Class<?>[] getClazzs() {
        return clazzs;
    }

    public void setClazzs(Class<?>[] clazzs) {
        this.clazzs = clazzs;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
