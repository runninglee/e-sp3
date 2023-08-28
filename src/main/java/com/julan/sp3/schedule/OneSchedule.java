package com.julan.sp3.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
//@EnableScheduling
@Async
@Slf4j
class OneSchedule {

    @Scheduled(cron = "0/5 * * * * ?")
    public void execute() {
        log.info("One Schedule ...");
    }
}
