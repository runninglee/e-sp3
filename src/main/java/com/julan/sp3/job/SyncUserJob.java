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
public class SyncUserJob {

    //第一步: 声明队列名称
    private static final String JOB_NAME = "user";
    //定义交换机、队列、路由
    private static final String EXCHANGE = "amq.direct";
    private static final String QUEUE = JOB_NAME;
    private static final String ROUTE_KEY = JOB_NAME;

    @Resource
    private AmqpTemplate amqpTemplate;

    //第二步: 触发任务
    public void dispatch(String jobId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("jobId", jobId);
        amqpTemplate.convertAndSend(ROUTE_KEY, hashMap);
    }

    //第三步: 监听任务
    @RabbitListener(bindings = @QueueBinding(value = @Queue(QUEUE), exchange = @Exchange(name = EXCHANGE), key = ROUTE_KEY))
    public void execute(HashMap<?, ?> map) {
        //在这里处理业务即可
        log.info("RabbitMQ User: {}", map.get("jobId"));
    }
}
