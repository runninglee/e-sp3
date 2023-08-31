package com.julan.sp3.listener.user;

import com.julan.sp3.event.CreateUserEvent;
import com.julan.sp3.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SyncBusinessListener {

    @EventListener
    @Order(2)
    public void syncBusiness(CreateUserEvent event) {
        User user = event.getUser();
        //同步处理业务逻辑
        log.info("给用户[{}]发业务通知", user.getUsername());
    }
}
