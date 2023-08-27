package com.julan.sp3.listener.user;

import com.julan.sp3.event.CreateUserEvent;
import com.julan.sp3.model.entity.User;
import com.julan.sp3.service.impl.UserServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendEmailListener {

    @Async
    @EventListener(CreateUserEvent.class)
    @Order(0)
    @SneakyThrows
    public void sendEmail(CreateUserEvent event) {
        Thread.sleep(1000);    //延时2秒
        User user = event.getUser();
        //发邮件的逻辑
        log.info("给用户[{}]发邮件通知", user.getUsername());
    }
}
