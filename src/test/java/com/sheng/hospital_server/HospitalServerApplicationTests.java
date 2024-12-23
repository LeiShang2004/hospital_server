package com.sheng.hospital_server;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.sheng.hospital_server.api.GraphRagCaller;
import com.sheng.hospital_server.pojo.Doctor;
import com.sheng.hospital_server.pojo.DoctorES;
import com.sheng.hospital_server.pojo.ScheduleInfo;
import com.sheng.hospital_server.service.DoctorService;
import com.sheng.hospital_server.utils.RSAUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

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
        String encrypt = RSAUtil.encrypt("152601200488881188");
        System.out.println(encrypt);
//        String decrypt = RSAUtil.decrypt("TmDIsqXslamvZtEQ1Z6yNVPyffqqU+UKSSPP9+5nZ5cUZkGTM1jIXVGlzEOE1WmcUvZSNPvbcqP8CGjNnwRVn4dfzoyK7R8rhqQWYRA0Lz/Q1sJa2iZTID9qOWjaRIYli3HMEgwfje7ONJYCBzG7ECvAJjADtiEwWOLwsMJdpCM=");
//        System.out.println(decrypt);
    }


//    @Resource
//    private MyProducer myProducer;
//
//    @Test
//    void mqTest() {
//        String topic = "test-topic";
//        String message = "hello, rocketmq";
//        myProducer.sendMessage(message);
//    }

    @Test
    void apiTest() throws IOException {
        String response = GraphRagCaller.send("感冒了可以吃羊肉吗");
        System.out.println(response);
    }


    @Resource
    private DoctorService doctorService;

    @Test
    void esTest() throws IOException {
//        DoctorES doctorES = new DoctorES();
//        doctorES.setId(1);
//        doctorES.setIntroduction("我是大师医生无敌张栩嘉");
//        elasticsearchTemplate.save(doctorES);
        List<ScheduleInfo> 大师 = doctorService.getByIntroduction("大师");
        System.out.println(大师);
        System.out.println("===================================");
        List<ScheduleInfo> 臭嗨 = doctorService.getByIntroduction("臭嗨");
        System.out.println(臭嗨);

    }


    @Resource
    private ElasticsearchClient elasticClient;


    @Test
    void toES() {
        List<Doctor> all = doctorService.getAll();
        for (Doctor doctor : all) {
            try {
                DoctorES doctorES = new DoctorES();
                doctorES.setId(doctor.getDoctorId());
                doctorES.setIntroduction(doctor.getIntroduction());
                IndexRequest<DoctorES> indexRequest = IndexRequest.of(b -> b
                        .index("doctor")
                        .id(doctor.getDoctorId().toString())
                        .document(doctorES));
                IndexResponse index = elasticClient.index(indexRequest);
                System.out.println(index);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
