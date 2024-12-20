package com.sheng.hospital_server.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Bean
    public ElasticsearchClient esClient() {
        // ES服务器URL
        String serverUrl = "http://127.0.0.1:9200";
        // ES用户名和密码

        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();

        RestClient restClient = RestClient
                .builder(HttpHost.create(serverUrl))
                .setHttpClientConfigCallback(hc -> hc.setDefaultCredentialsProvider(credsProv))
                .build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }

}