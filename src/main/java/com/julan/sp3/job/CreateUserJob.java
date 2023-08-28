package com.julan.sp3.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julan.sp3.model.vo.user.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateUserJob {

    @RabbitListener(bindings = @QueueBinding(value = @Queue("topic"), exchange = @Exchange("amq.direct")))
    public void execute(String object)  {


        log.info("RabbitMQ: {}", object);
    }
}
