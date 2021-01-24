package com.four.messageconvert;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: AESUtils
 * @Author: sanwu
 * @Date: 2021/1/24 11:16
 */
public class AESUtils {

    /**
     * 加解密密钥, 外部可以
     */
    public static final String AES_DATA_SECURITY_KEY = "4%YkW!@g5LGcf9Ut";
    /**
     * 算法/加密模式/填充方式
     */
    private static final String AES_PKCS5P = "AES/ECB/PKCS5Padding";

    private static final String AES_PERSON_KEY_SECURITY_KEY = "pisnyMyZYXuCNcRd";

    /**
     * 加密
     *
     * @param str 需要加密的字符串
     * @param key 密钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String str, String key) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("key不能为空");
        }
        try {
            if (str == null) {
                return null;
            }
            // 判断Key是否为16位
            if (key.length() != 16) {
                return null;
            }
            byte[] raw = key.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            // "算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance(AES_PKCS5P);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
            // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
            return new BASE64Encoder().encode(encrypted);
        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * 解密
     *
     * @param str 需要解密的字符串
     * @param key 密钥
     * @return
     */
    public static String decrypt(String str, String key) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("key不能为空");
        }
        try {
            if (str == null) {
                return null;
            }
            // 判断Key是否为16位
            if (key.length() != 16) {
                return null;
            }
            byte[] raw = key.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(AES_PKCS5P);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            // 先用base64解密
            byte[] encrypted = new BASE64Decoder().decodeBuffer(str);
            try {
                byte[] original = cipher.doFinal(encrypted);
                String originalString = new String(original, "UTF-8");
                return originalString;
            } catch (Exception e) {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 加密
     *
     * @param str 需要加密的字符串
     * @return
     * @throws Exception
     */
    public static String encrypt(String str) {
        return encrypt(str, AES_DATA_SECURITY_KEY);
    }

    /**
     * 解密
     *
     * @param str 需要解密的字符串
     * @return
     */
    public static String decrypt(String str) {
        return decrypt(str, AES_DATA_SECURITY_KEY);
    }

    /**
     * 查询的时候对某些字段解密
     *
     * @param str
     * @return
     */
    public static String aesDecrypt(String str) {
        if (StringUtils.isBlank(str)) {
            return " ";
        }
        String sql = " AES_DECRYPT(from_base64(" + str + ")," + "'" + AES_DATA_SECURITY_KEY + "')";
        return sql;
    }

    /**
     * 对personKey加密
     *
     * @param personKey
     * @return
     */
    public static String encryptPersonKey(String personKey) {
        return AESUtils.encrypt(personKey, AES_PERSON_KEY_SECURITY_KEY);
    }

    /**
     * 对personKey解密
     *
     * @param personKey
     * @return
     */
    public static String decryptPersonKey(String personKey) {
        return AESUtils.decrypt(personKey, AES_PERSON_KEY_SECURITY_KEY);
    }
}
