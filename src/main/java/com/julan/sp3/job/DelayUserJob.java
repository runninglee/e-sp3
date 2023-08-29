package com.julan.sp3.job;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DelayUserJob {

    //第一步: 声明任务名称，标识要唯一
    private static final String JOB_NAME = "user";

    //第二步:首次手动创建延迟交换机 delay.exchange
    private static final String QUEUE = "delay." + JOB_NAME;
    private static final String ROUTE_KEY = "delay.route." + JOB_NAME;
    private static final String EXCHANGE = "delay.exchange";

    @Resource
    private AmqpTemplate amqpTemplate;

    //第三步: 传任务的ID和延迟时间,默认为秒
    public void dispatch(String jobId, int delayTime) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDelay(delayTime * 1000);
        Message message = new Message(jobId.getBytes(), messageProperties);
        amqpTemplate.convertAndSend(EXCHANGE, ROUTE_KEY, message);
    }

    //第四步: 绑定队列信息到延迟交换机上
    @RabbitListener(bindings = @QueueBinding(value = @Queue(QUEUE), exchange = @Exchange(name = EXCHANGE, type = "x-delayed-message", arguments = {@Argument(name = "x-delayed-type", value = "direct")}, delayed = "true"), key = ROUTE_KEY))
    public void execute(String message) {
        log.info("RabbitMQ USER: {}", "延迟执行了" + message);
    }
}
