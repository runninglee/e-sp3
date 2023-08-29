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

import java.util.Optional;

@Component
@Slf4j
public class CreateUserJob {

    private static final String KEY = "user";

    @Resource
    private AmqpTemplate amqpTemplate;


    public void dispatch(User user) throws JsonProcessingException {

        amqpTemplate.convertAndSend(KEY, new ObjectMapper().writeValueAsString(user));
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(KEY), exchange = @Exchange(name = "amq.direct"), key = KEY))
    public void execute(String object) throws JsonProcessingException {


        log.info("RabbitMQ User: {}", object);
        if (object != null) {
            User user = new ObjectMapper().readValue(object, User.class);
            log.info("RabbitMQ Role: {}", user);
        }
    }
}
