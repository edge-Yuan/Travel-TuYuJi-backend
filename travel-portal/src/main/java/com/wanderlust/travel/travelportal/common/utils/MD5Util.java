package com.wanderlust.travel.travelportal.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 简化的MD5加密工具类（无盐值）
 */
public class MD5Util {

    /**
     * 对字符串进行MD5加密（无盐值）
     * @param plainText 明文密码
     * @return 加密后的32位小写字符串
     */
    public static String encrypt(String plainText) {
        try {
            // 获取MD5实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 对明文进行加密处理
            byte[] digest = md.digest(plainText.getBytes());

            // 转换为16进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                int val = ((int) b) & 0xff;
                if (val < 16) {
                    sb.append("0"); // 不足两位补0
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // MD5是标准算法，不会抛出此异常
            throw new RuntimeException("MD5加密失败", e);
        }
    }
}

