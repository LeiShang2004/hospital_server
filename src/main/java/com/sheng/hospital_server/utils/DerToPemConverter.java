package com.sheng.hospital_server.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DerToPemConverter {

    /**
     * 读取.der格式公钥文件内容
     *
     * @param filePath 公钥文件路径
     * @return 公钥文件对应的字节数组
     * @throws IOException 文件读取异常时抛出
     */
    public static byte[] readDerPublicKeyFile(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

    /**
     * 将DER格式公钥数据转换为PEM格式字符串
     *
     * @param derPublicKeyBytes DER格式的公钥字节数组
     * @return PEM格式的公钥字符串
     * @throws NoSuchAlgorithmException 当指定的加密算法不存在时抛出
     * @throws InvalidKeySpecException  当公钥规范无效时抛出
     */
    public static String derToPem(byte[] derPublicKeyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 根据X509编码规范创建公钥规范对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(derPublicKeyBytes);
        // 获取KeyFactory实例，用于生成公钥
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        byte[] encoded = publicKey.getEncoded();
        String base64Encoded = Base64.getEncoder().encodeToString(encoded);

        StringBuilder pemBuilder = new StringBuilder();
        pemBuilder.append("-----BEGIN PUBLIC KEY-----\n");
        // 按照64个字符一行进行换行处理（这是PEM格式规范要求）
        int index = 0;
        while (index < base64Encoded.length()) {
            int endIndex = Math.min(index + 64, base64Encoded.length());
            pemBuilder.append(base64Encoded.substring(index, endIndex)).append("\n");
            index = endIndex;
        }
        pemBuilder.append("-----END PUBLIC KEY-----");

        return pemBuilder.toString();
    }

    public static void main(String[] args) {
        try {
            String derFilePath = "src/main/resources/rsa/public_key.der"; // 替换为实际的.der公钥文件路径
            byte[] derPublicKeyBytes = readDerPublicKeyFile(derFilePath);
            String pemPublicKeyStr = derToPem(derPublicKeyBytes);
            System.out.println(pemPublicKeyStr);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}