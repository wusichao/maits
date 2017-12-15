package com.wusc.redis.re;

import java.io.Serializable;

public class RedisResult implements Serializable{
    private String code;
    private Object data;

    public RedisResult() {
    }

    public RedisResult(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
}
