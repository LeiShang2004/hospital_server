package com.sheng.hospital_server.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 密钥生成工具类
 */
@Slf4j
public class KeyGeneratorUtil {
    /**
     * 生成256位的密钥
     *
     * @return 生成的256位密钥字节数组
     */
    public static byte[] generate256BitKey() {
        try {
            // 使用AES算法生成密钥
            javax.crypto.KeyGenerator keyGenerator = javax.crypto.KeyGenerator.getInstance("AES");

            // 设置密钥长度为256位
            keyGenerator.init(256, new SecureRandom());

            // 生成密钥
            javax.crypto.SecretKey secretKey = keyGenerator.generateKey();

            // 返回密钥的字节数组形式
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            log.error("生成密钥失败", e);
            return null;
        }
    }
}