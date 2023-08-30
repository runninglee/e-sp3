package com.julan.sp3.aspect;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.julan.sp3.annotation.DataPermission;
import com.julan.sp3.annotation.HandlePermission;
import com.julan.sp3.model.entity.BaseEntity;
import com.julan.sp3.model.entity.User;
import com.julan.sp3.service.impl.user.UserServiceImpl;
import com.julan.sp3.util.api.ResultJson;
import jakarta.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuthorizationAspect {


    @Resource
    private UserServiceImpl userService;

    @Around("@annotation(com.julan.sp3.annotation.HandlePermission)")
    public Object hasHandlePermission(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        HandlePermission annotation = signature.getMethod().getAnnotation(HandlePermission.class);
        if (!userService.hasHandlePermission(annotation.value())) {
            return ResultJson.forbidden(null);
        }
        return joinPoint.proceed();
    }

    @Around("@annotation(com.julan.sp3.annotation.DataPermission)")
    public Object hasDataPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DataPermission annotation = signature.getMethod().getAnnotation(DataPermission.class);
        if (!userService.hasDataPermission(annotation.value(), annotation.entity())) {
            return ResultJson.forbidden(null);
        }
        return joinPoint.proceed();
    }
}
