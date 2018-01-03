package com.wusc.campaign.dto;

public class RedisDTO {
    private String method;
    private Class<?>[] clazzs;
    private Object[] params;

    public RedisDTO() {
    }

    public RedisDTO(String method, Class<?>[] clazzs, Object[] params) {
        this.method = method;
        this.clazzs = clazzs;
        this.params = params;
    }

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
