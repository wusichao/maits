package com.wusc.pay.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String sign(String str){
        MessageDigest md5;
        String sgin = "";
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(str.getBytes("UTF-8"));
            sgin = byteToStr(md5.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sgin;
    }
    
    /**
     * 将字节数组转换为十六进制字符串
     * 
     * @param byteArray
     * @return
     */
    public static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     * 
     * @return
     */
    public static String byteToHexStr(byte bytes) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(bytes >>> 4) & 0X0F];
        tempArr[1] = Digit[bytes & 0X0F];

        String s = new String(tempArr);
        return s;
    }
    
}