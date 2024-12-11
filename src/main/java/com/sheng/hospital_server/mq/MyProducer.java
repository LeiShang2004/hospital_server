package com.sheng.hospital_server.mq;

import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息队列生产者
 * 用于发送消息
 */
@Component
public class MyProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息
     *
     * @param topic   主题
     * @param message 消息
     */
    public void sendMessage(String topic, String message) {
        rocketMQTemplate.convertAndSend(topic, message);
    }
}
