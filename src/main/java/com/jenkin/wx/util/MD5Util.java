package com.jenkin.wx.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: jenkinwang
 * @date: 2018/9/11 17:36
 * @description:：MD5加密类 --- 16位
 */
public class MD5Util {

    public static String convertToMD5(String message) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(message.getBytes());
        return new BigInteger(1, md5.digest()).toString(16);
    }
}
