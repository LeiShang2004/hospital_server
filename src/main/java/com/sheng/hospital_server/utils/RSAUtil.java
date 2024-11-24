package com.sheng.hospital_server.utils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Component
public class RSAUtil {

    @Value("${rsa.public-Key-filepath}")
    private String publicKeyFilePath;

    @Value("${rsa.private-key-filepath}")
    private String privateKeyFilePath;

    private static PublicKey publicKey;

    private static PrivateKey privateKey;


    @PostConstruct
    public void init() {
        log.info("**RSA：加载RSA公钥私钥");
        publicKey = getPublicKeyFromFile(publicKeyFilePath);
        privateKey = getPrivateKeyFromFile(privateKeyFilePath);
    }

    public static String encrypt(String data) {
        try {
            // 创建Cipher实例用于加密，使用RSA
            Cipher cipher = Cipher.getInstance("RSA");
            // 初始化Cipher为加密模式，传入公钥
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 加密
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            // 字节数组转换为Base64编码的字符串
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            log.error("加密失败", e);
            return null;
        }
    }

    public static PublicKey getPublicKeyFromFile(String publicKeyFilePath) {
        try {
            ClassPathResource resource = new ClassPathResource(publicKeyFilePath);
            // 从文件中读取公钥字节数组
            byte[] publicKeyBytes = Files.readAllBytes(resource.getFile().toPath());
            // 根据X509编码规范创建公钥规范对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            // 获取KeyFactory实例，用于生成公钥
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            log.error("获取公钥失败", e);
            return null;
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            // 创建Cipher实例用于解密，使用RSA
            Cipher cipher = Cipher.getInstance("RSA");
            // 初始化Cipher为解密模式，传入私钥
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // 对Base64编码的加密数据进行解码，然后进行解密
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedData);
        } catch (Exception e) {
            log.error("解密失败", e);
            return null;
        }
    }

    public static PrivateKey getPrivateKeyFromFile(String privateKeyFilePath) {
        try {
            ClassPathResource resource = new ClassPathResource(privateKeyFilePath);
            // 从文件中读取私钥字节数组
            byte[] privateKeyBytes = Files.readAllBytes(resource.getFile().toPath());
            // 根据PKCS8编码规范创建私钥规范对象
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            // 获取KeyFactory实例，用于生成私钥
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            log.error("获取私钥失败", e);
            return null;
        }
    }
}