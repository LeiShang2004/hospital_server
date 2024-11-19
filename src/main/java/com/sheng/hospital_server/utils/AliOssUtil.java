package com.sheng.hospital_server.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;

import java.net.URL;
import java.util.Date;

public class AliOssUtil {

    private AliOssUtil() throws com.aliyuncs.exceptions.ClientException {
    }

    // 以华东1（杭州）的外网Endpoint为例，其它Region请按实际情况填写。
    private static final String ENDPOINT = "https://oss-cn-heyuan.aliyuncs.com";
    // 填写Bucket名称，例如examplebucket。
    private static final String BUCKET_NAME = "programming-hospital";

    // 填写Endpoint对应的Region信息，例如cn-hangzhou。
    private static final String REGION = "cn-heyuan";

    // 从环境变量中获取访问凭证。运行本代码示例之前，请先配置环境变量。
    private static final EnvironmentVariableCredentialsProvider CREDENTIALSPROVIDER;

    // 创建OSSClient实例。
    private static ClientBuilderConfiguration CLIENTBUILDERCONFIGURATION = new ClientBuilderConfiguration();
    private static final OSS OSS_CLIENT;

    static {
        try {
            CREDENTIALSPROVIDER = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
            CLIENTBUILDERCONFIGURATION.setSignatureVersion(SignVersion.V4);
            CLIENTBUILDERCONFIGURATION.setSignatureVersion(SignVersion.V4);
            OSS_CLIENT = OSSClientBuilder.create()
                    .endpoint(ENDPOINT)
                    .credentialsProvider(CREDENTIALSPROVIDER)
                    .clientConfiguration(CLIENTBUILDERCONFIGURATION)
                    .region(REGION)
                    .build();
        } catch (com.aliyuncs.exceptions.ClientException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUrl(String objectName) {
        URL signedUrl = null;
        try {
            // 指定生成的签名URL过期时间，单位为毫秒。本示例以设置过期时间为1小时为例。
            Date expiration = new Date(new Date().getTime() + 3600 * 1000L);

            // 生成签名URL。
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(BUCKET_NAME, objectName, HttpMethod.GET);
            // 设置过期时间。
            request.setExpiration(expiration);

            // 通过HTTP GET请求生成签名URL。
            signedUrl = OSS_CLIENT.generatePresignedUrl(request);

        } catch (
                OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (
                ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        }

        // 返回签名URL。
        if (signedUrl != null) {
            return signedUrl.toString();
        } else {
            return "error";
        }
    }

}
