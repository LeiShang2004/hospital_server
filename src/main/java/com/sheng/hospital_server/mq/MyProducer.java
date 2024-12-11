package com.sheng.hospital_server.mq;

import com.google.protobuf.InvalidProtocolBufferException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 消息队列生产者
 * 用于发送消息
 */
@Component
@Slf4j
public class MyProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    private static final String TOPIC = "release-available-number";

    private static final Long TIME_OUT = 10000L;

    /**
     * 延迟级别从1到18分别对应了1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    private static final Integer DELAY_LEVEL = 3;


    /**
     * 发送消息
     *
     * @param appointmentId 挂号id
     */
    public void sendMessage(Integer appointmentId) {
        log.info("消息队列：发送消息{}开始支付等待", appointmentId);

        try {
            rocketMQTemplate.syncSend(TOPIC, MessageBuilder.withPayload(appointmentId).build(), TIME_OUT, DELAY_LEVEL);
        } catch (Exception e) {
            log.error("消息队列：发送消息失败{}", e.getMessage());
        }


        log.info("消息队列：发送消息时间{}", LocalDateTime.now());
    }
}
