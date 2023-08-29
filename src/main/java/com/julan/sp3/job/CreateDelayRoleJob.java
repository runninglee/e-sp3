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


    private static final String QUEUE_ROUTE_KEY = "delay.role";
    private static final String DELAYED_EXCHANGE = "delay.exchange";

    @Resource
    private AmqpTemplate amqpTemplate;

    public void dispatch(String message) {

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDelay(5000);
        Message msg = new Message(message.getBytes(), messageProperties);
        amqpTemplate.convertAndSend(DELAYED_EXCHANGE, QUEUE_ROUTE_KEY, msg);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(QUEUE_ROUTE_KEY), exchange = @Exchange(name = DELAYED_EXCHANGE, type = "x-delayed-message", arguments = {
            @Argument(name = "x-delayed-type", value = "direct")
    }, delayed = "true"), key = QUEUE_ROUTE_KEY))
    public void execute(String message) {
        log.info("RabbitMQ Role: {}", "延迟执行了" + message);
    }
}
