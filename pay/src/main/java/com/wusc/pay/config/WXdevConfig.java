package com.wusc.pay.config;

import com.objcoding.wxpay.sdk.WXPay;
import com.wusc.pay.pojo.WXdev;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * create by wusc on 2018/1/24
 */
@Configuration
@ConfigurationProperties(prefix = "pay.wx")
public class WXdevConfig {
    private String appId;
    private String MchId;
    private String key;
    private int httpConnectTimeoutMs;
    private int httpReadTimeoutMs;
    private String certPath;

    @Bean
    public WXdev wxdev(){

        return new WXdev(appId,MchId,key,httpConnectTimeoutMs,httpReadTimeoutMs,certPath);
    }

    @Bean
    public WXPay wxPay(){

        return new WXPay(wxdev());
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return MchId;
    }

    public void setMchId(String mchId) {
        MchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getHttpConnectTimeoutMs() {
        return httpConnectTimeoutMs;
    }

    public void setHttpConnectTimeoutMs(int httpConnectTimeoutMs) {
        this.httpConnectTimeoutMs = httpConnectTimeoutMs;
    }

    public int getHttpReadTimeoutMs() {
        return httpReadTimeoutMs;
    }

    public void setHttpReadTimeoutMs(int httpReadTimeoutMs) {
        this.httpReadTimeoutMs = httpReadTimeoutMs;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }
}
