package com.julan.sp3.aspect;

import com.julan.sp3.annotation.BoolPermission;
import com.julan.sp3.exception.GraceException;
import com.julan.sp3.service.impl.user.UserServiceImpl;
import com.julan.sp3.util.api.ResultJson;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.lang.reflect.Parameter;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Component
@Aspect
public class AuthorizationAspect {


    @Resource
    private UserServiceImpl userService;

    @Around("@annotation(com.julan.sp3.annotation.BoolPermission)")
    public Object checkBoolPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        BoolPermission annotation = signature.getMethod().getAnnotation(BoolPermission.class);
        if (!userService.hasBoolPermission(annotation.value())) {
            return ResultJson.forbidden(null);
        }
        return joinPoint.proceed();
    }

    @Before("@annotation(com.julan.sp3.annotation.DataPermission)")
    public void checkDataPermission(JoinPoint joinPoint) {
        System.out.println("数据");
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }
}
