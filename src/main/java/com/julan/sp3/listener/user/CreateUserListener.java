package com.julan.sp3.listener.user;

import com.julan.sp3.event.SendEmailEvent;
import com.julan.sp3.event.SendMessageEvent;
import com.julan.sp3.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateUserListener {

    @Async
    @EventListener(SendEmailEvent.class)
    public void sendEmail(SendEmailEvent sendEmailEvent) {
        User user = sendEmailEvent.getUser();
        log.info("监听注册发送邮件事件,{}", user);
    }

    @Async
    @EventListener(SendMessageEvent.class)
    public void sendMessage(SendMessageEvent sendMessageEvent) {
        User user = sendMessageEvent.getUser();
        log.info("监听注册发送短信事件,{}", user);
    }
}
