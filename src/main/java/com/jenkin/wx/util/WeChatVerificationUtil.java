package com.jenkin.wx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author: jenkinwang
 * @date: 2018/9/26 17:57
 * @description:
 */
public class WeChatVerificationUtil {
    private static final String TOKEN = "jenkin_wx";

    public static String verificateWX(String timestamp, String nonce) throws NoSuchAlgorithmException {

        String[] array = new String[]{TOKEN, timestamp, nonce};
        StringBuffer sb = new StringBuffer();
        // 排序
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        String str = sb.toString();
        // sha1加密
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(str.getBytes());
        byte[] digest = md.digest();

        StringBuffer hexstr = new StringBuffer();
        String shaHex = "";
        for (int i = 0; i < digest.length; i++) {
            shaHex = Integer.toHexString(digest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }

        return hexstr.toString();
    }
}
