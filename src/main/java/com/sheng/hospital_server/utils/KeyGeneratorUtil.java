package com.sheng.hospital_server.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;


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

    /**
     * 生成RSA公钥私钥对 保存到der文件
     */
    public static void main(String[] args) throws IOException {
        try {
            // 创建RSA密钥对生成器
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            // 初始化密钥对生成器，这里设置密钥长度
            keyPairGenerator.initialize(1024);

            // 生成密钥对
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // 获取公钥
            PublicKey publicKey = keyPair.getPublic();

            // 将公钥保存为public_key.der文件
            FileOutputStream publicKeyOutputStream = new FileOutputStream("public_key.der");
            publicKeyOutputStream.write(publicKey.getEncoded());
            publicKeyOutputStream.close();

            // 获取私钥
            PrivateKey privateKey = keyPair.getPrivate();

            // 将私钥保存为private_key.der文件
            FileOutputStream privateKeyOutputStream = new FileOutputStream("private_key.der");
            privateKeyOutputStream.write(privateKey.getEncoded());
            privateKeyOutputStream.close();

            System.out.println("RSA公钥私钥对已成功生成并保存");
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}