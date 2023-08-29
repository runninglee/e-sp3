package com.julan.sp3.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julan.sp3.model.entity.User;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateRoleJob {

    private static final String KEY = "role";

    @Resource
    private AmqpTemplate amqpTemplate;

    public void dispatch(String message) {
        amqpTemplate.convertAndSend(KEY, message);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(KEY), exchange = @Exchange(name = "amq.direct"), key = KEY))
    public void execute() {

        log.info("RabbitMQ Role: {}", "332232323");
    }
}
