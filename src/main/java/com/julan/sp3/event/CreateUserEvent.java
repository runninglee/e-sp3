package com.julan.sp3.event;

import com.julan.sp3.model.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreateUserEvent extends ApplicationEvent {

    private final User user;

    public CreateUserEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
