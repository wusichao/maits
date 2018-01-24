package com.wusc.pay.pojo;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.util.StringUtils;

import java.io.*;

public class WXdev implements WXPayConfig {
    private String appId;
    private String MchId;
    private String key;
    private int httpConnectTimeoutMs;
    private int httpReadTimeoutMs;
    private byte[] certData;

    public WXdev(String appId, String MchId, String key, int httpConnectTimeoutMs, int httpReadTimeoutMs, String certPath) {
        this.appId=appId;
        this.MchId=MchId;
        this.key=key;
        this.httpConnectTimeoutMs=httpConnectTimeoutMs;
        this.httpReadTimeoutMs=httpReadTimeoutMs;
        if(!StringUtils.isEmpty(certPath)){
            File file = new File(certPath);
            InputStream certStream = null;
            try {
                certStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            this.certData = new byte[(int) file.length()];
            try {
                certStream.read(this.certData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                certStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getAppID() {
        return appId;
    }

    public String getMchID() {
        return MchId;
    }

    public String getKey() {
        return key;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return httpConnectTimeoutMs;
    }

    public int getHttpReadTimeoutMs() {
        return httpReadTimeoutMs;
    }
}
