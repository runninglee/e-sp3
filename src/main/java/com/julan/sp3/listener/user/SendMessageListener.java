package com.julan.sp3.listener.user;

import com.julan.sp3.event.CreateUserEvent;
import com.julan.sp3.model.entity.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendMessageListener {
    
    @Async
    @EventListener
    @Order(1)
    public void sendMessage(CreateUserEvent event) throws InterruptedException {
        Thread.sleep(3000);
        User user = event.getUser();
        //发短信的逻辑
        log.info("给用户[{}]发短信通知", user.getUsername());
    }
}
