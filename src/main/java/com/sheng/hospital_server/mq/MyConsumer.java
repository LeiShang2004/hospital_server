package com.sheng.hospital_server.mq;

import com.sheng.hospital_server.pojo.Appointment;
import com.sheng.hospital_server.service.AppointmentService;
import com.sheng.hospital_server.service.ScheduleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 消息队列消费者
 * 接受消息
 */
@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "my-boot-producer-group", topic = "release-available-number")
public class MyConsumer implements RocketMQListener<String> {
    @Resource
    private AppointmentService appointmentService;

    @Resource
    private ScheduleService scheduleService;

    @Override
    public void onMessage(String message) {
        log.info("消息队列：接收到{}支付等待到期", message);
        log.info("消息队列：收到消息时间{}", LocalDateTime.now());
        // 支付等待超时

        // 获取挂号信息
        Integer appointmentId = Integer.valueOf(message);
        Appointment appointment = appointmentService.getById(appointmentId);
        // 未支付才释放资源
        if (Objects.equals(appointment.getStatus(), AppointmentService.STATUS_CONFIRMED)) {
            return;
        } else {
            // 释放资源
            scheduleService.incrementAvailableNumber(appointment.getScheduleId());
            // 取消挂号
            appointmentService.cancel(appointmentId);
        }
    }
}
