package com.wusc.pay.utils;

import java.util.UUID;

/**
 * create by wusc on 2018/1/24
 */
public class KeyGenerator {
    public static String createId(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
