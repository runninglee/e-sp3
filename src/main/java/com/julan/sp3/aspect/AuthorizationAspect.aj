package com.julan.sp3.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public aspect AuthorizationAspect {

    @Pointcut(value = "@annotation(com.julan.sp3.annotation.BoolPermission)")
    public void boolPermission() {

    }

    @Pointcut(value = "@annotation(com.julan.sp3.annotation.DataPermission)")
    public void dataPermission() {

    }

    @Before("boolPermission()")
    public void checkBoolPermission(JoinPoint joinPoint) {
        System.out.println("布尔");
        System.out.println(joinPoint);

    }

    @Before("dataPermission()")
    public void checkDataPermission(JoinPoint joinPoint) {
        System.out.println("数据");
        System.out.println(joinPoint);
    }
}
