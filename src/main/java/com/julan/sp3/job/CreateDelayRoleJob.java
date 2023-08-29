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

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class CreateDelayRoleJob {

    private static final String KEY = "delayed-role";

    private static final String DELAYED_EXCHANGE = "delayed-exchange";

    @Resource
    private AmqpTemplate amqpTemplate;

    public void dispatch(String message) {

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDelay(5000);
        Message msg = new Message(message.getBytes(), messageProperties);
        amqpTemplate.convertAndSend(DELAYED_EXCHANGE, KEY, msg);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = KEY,arguments = {@Argument(name = "x-dead-letter-exchange", value = DELAYED_EXCHANGE), @Argument(name = "x-dead-letter-routing-key", value = KEY)}),
            exchange = @Exchange(name = DELAYED_EXCHANGE, type = "x-delayed-message", arguments = {@Argument(name = "x-delayed-type", value = "direct")}, delayed = "true")))
    public void execute(String message) {
        log.info("RabbitMQ Role: {}", "延迟执行了" + message);
    }
}
