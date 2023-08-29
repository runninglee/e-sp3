package com.julan.sp3.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julan.sp3.model.entity.User;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CreateDelayRoleJob {

    private static final String ID = "role";

    private static final String QUEUE = "delay." + ID;
    private static final String ROUTE_KEY = "delay.route." + ID;
    private static final String EXCHANGE = "delay.exchange";

    @Resource
    private AmqpTemplate amqpTemplate;

    public void dispatch(String message, int delayTime) {

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDelay(delayTime * 1000);
        Message msg = new Message(message.getBytes(), messageProperties);
        amqpTemplate.convertAndSend(EXCHANGE, ROUTE_KEY, msg);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(QUEUE), exchange = @Exchange(name = EXCHANGE, type = "x-delayed-message", arguments = {@Argument(name = "x-delayed-type", value = "direct")}, delayed = "true"), key = ROUTE_KEY))
    public void execute(String message) {
        log.info("RabbitMQ Role: {}", "延迟执行了" + message);
    }
}
