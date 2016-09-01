package com.search.www.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

public class CodecUtil {

    private static final Logger logger = Logger.getLogger(CodecUtil.class);

    // 将 URL 编码
    public static String urlEncode(String str) {
        String target;
        try {
            target = URLEncoder.encode(str, Constance.UTF_8);
        } catch (Exception e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将 URL 解码
    public static String urlDecode(String str) {
        String target;
        try {
            target = URLDecoder.decode(str, Constance.UTF_8);
        } catch (Exception e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 Base64 编码
    public static String encodeBase64(byte[] bt) {
        String target;
        try {
            target = Base64.encodeBase64String(bt);
        } catch (Exception e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 Base64 编码
    public static String encodeBase64(String str) {
        String target;
        try {
            target = Base64.encodeBase64URLSafeString(str.getBytes(Constance.UTF_8));
        } catch (UnsupportedEncodingException e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }


    // 将字符串 Base64 解码
    public static String decodeBase64(String str) {
        String target;
        try {
            target = new String(Base64.decodeBase64(str), Constance.UTF_8);
        } catch (UnsupportedEncodingException e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 Base64 解码
    public static String decodeBase64(byte[] bt) {
        String target;
        try {
            target = new String(Base64.decodeBase64(bt));
        } catch (Exception e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 Base64 解码
    public static byte[] decodeBase64Byte(byte[] bt) {
        byte[] target;
        try {
            target = Base64.decodeBase64(bt);
        } catch (Exception e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 Base64 解码
    public static byte[] decodeBase64Byte(String str) {
        byte[] target;
        try {
            target = Base64.decodeBase64(str);
        } catch (Exception e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }



    // 将字符串 MD5 加密
    public static String encryptMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    // 将字符串 SHA 加密
    public static String encryptSHA(String str) {
        return DigestUtils.sha1Hex(str);
    }

    // 创建随机数
    public static String createRandom(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    // 获取UUID（32位）
    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
