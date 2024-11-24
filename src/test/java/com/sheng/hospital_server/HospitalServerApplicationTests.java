package com.sheng.hospital_server;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.sheng.hospital_server.utils.RSAUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.URL;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;

import java.net.URL;
import java.util.Date;

@SpringBootTest
class HospitalServerApplicationTests {

    @Test
    void contextLoads() throws com.aliyuncs.exceptions.ClientException {


//        // 以华东1（杭州）的外网Endpoint为例，其它Region请按实际情况填写。
//        String endpoint = "https://oss-cn-heyuan.aliyuncs.com";
//        // 填写Bucket名称，例如examplebucket。
//        String bucketName = "programming-hospital";
//        // 填写Object完整路径，例如exampleobject.txt。Object完整路径中不能包含Bucket名称。
//        String objectName = "前端资源/1.txt";
//
//        // 填写Endpoint对应的Region信息，例如cn-hangzhou。
//        String region = "cn-heyuan";
//
//        // 从环境变量中获取访问凭证。运行本代码示例之前，请先配置环境变量。
//        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
//
//        // 创建OSSClient实例。
//        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
//        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
//        OSS ossClient = OSSClientBuilder.create()
//                .endpoint(endpoint)
//                .credentialsProvider(credentialsProvider)
//                .clientConfiguration(clientBuilderConfiguration)
//                .region(region)
//                .build();
//
//        URL signedUrl = null;
//        try {
//            // 指定生成的签名URL过期时间，单位为毫秒。本示例以设置过期时间为1小时为例。
//            Date expiration = new Date(new Date().getTime() + 3600 * 1000L);
//
//            // 生成签名URL。
//            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
//            // 设置过期时间。
//            request.setExpiration(expiration);
//
//            // 通过HTTP GET请求生成签名URL。
//            signedUrl = ossClient.generatePresignedUrl(request);
//            // 打印签名URL。
//            System.out.println("signed url for getObject: " + signedUrl);
//        } catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                    + "but was rejected with an error response for some reason.");
//            System.out.println("Error Message:" + oe.getErrorMessage());
//            System.out.println("Error Code:" + oe.getErrorCode());
//            System.out.println("Request ID:" + oe.getRequestId());
//            System.out.println("Host ID:" + oe.getHostId());
//        } catch (ClientException ce) {
//            System.out.println("Caught an ClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with OSS, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message:" + ce.getMessage());
//        }
    }

    @Test
    void rsaTest() {
        String encrypt = RSAUtil.encrypt("152555202411248888");
        System.out.println(encrypt);
        String decrypt = RSAUtil.decrypt(encrypt);
        System.out.println(decrypt);
    }

}
