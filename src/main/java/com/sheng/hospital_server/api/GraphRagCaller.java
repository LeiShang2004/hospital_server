package com.sheng.hospital_server.api;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


@Slf4j
@Component
public class GraphRagCaller {

    @Value("${graphrag.url}")
    private String URL_ADDRESS;

    private static URL URL = null;

    @PostConstruct
    public void init() throws MalformedURLException {
        log.info("**GRAPHRAG：加载服务地址{}", URL_ADDRESS);
        URL = new URL(URL_ADDRESS);
    }

    /**
     * 发送请求
     *
     * @throws IOException IO异常
     */
    public static String send(String message) throws IOException {
        log.info("**GRAPHRAG：发送请求：{}", message);
        // 打开一个到URL的连接
        HttpURLConnection conn = (HttpURLConnection) URL.openConnection();
        // 设置HTTP方法为POST
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        // 设置连接和读取超时时间为5分钟
        conn.setConnectTimeout(300000);
        conn.setReadTimeout(300000);


        // 发送请求体
        String jsonInputString = "{\"query\":\"" + message + "\"}";
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // 读取响应
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            log.info("**GRAPHRAG：请求成功：{}", response.toString());
            return response.toString();
        }
    }

}
