package com.search.www.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;


/**
 * Created by lujianing on 14-2-21.
 */
public class StringUtil {


    /**
     * 判断字符串是否不为空或者null
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    /**
     * 判断字符串是否为空或者null
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * BASE64加密
     * @param
     * @return
     * @throws Exception
     */
    public static byte[] encodeBase64(String str) throws Exception {
        byte[] b = Base64.encodeBase64(str.getBytes(), true);
        return b;
    }

    /**
     * BASE64加密
     * @param key
     * @return
     * @throws Exception
     */
    public static String decodeBase64(byte[] key) throws Exception {
        //解密
        byte[] b = Base64.decodeBase64(key);
        return new String(b);
    }

    // 若字符串为空，则取默认值
    public static String defaultIfEmpty(String str, String defaultValue) {
        return StringUtils.defaultIfEmpty(str, defaultValue);
    }

}
