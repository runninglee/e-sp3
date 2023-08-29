package com.julan.sp3.job;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class CreateUserJob {

    private static final String QueueRoutingKey = "user";

    @Resource
    private AmqpTemplate amqpTemplate;

    public void dispatch(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        amqpTemplate.convertAndSend(QueueRoutingKey, hashMap);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(QueueRoutingKey), exchange = @Exchange(name = "amq.direct"), key = QueueRoutingKey))
    public void execute(HashMap<?, ?> map) {
        //在这里处理业务即可
        log.info("RabbitMQ User: {}", map.get("id"));
    }
}
