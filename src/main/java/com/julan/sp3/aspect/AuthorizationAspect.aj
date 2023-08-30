package com.julan.sp3.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public aspect AuthorizationAspect {

    @Before("@annotation(com.julan.sp3.annotation.BoolPermission)")
    public void checkBoolPermission(JoinPoint joinPoint) throws IllegalAccessException {
        boolean hasPermission = userPermissionCheck();
        System.out.println("注解到这里了");
        System.out.println(joinPoint);
        if (!hasPermission) {
            throw new IllegalAccessException("You don't have permission to access this resource.");
        }
    }

    @Before("@annotation(com.julan.sp3.annotation.DataPermission)")
    public void checkDataPermission(JoinPoint joinPoint) throws IllegalAccessException {
        boolean hasPermission = userPermissionCheck();

        if (!hasPermission) {
            throw new IllegalAccessException("You don't have permission to access this resource.");
        }
    }



    private boolean userPermissionCheck() {
        // 实际应用中，你需要根据用户角色或其他方式检查权限
        // 这里只是一个示例，实际实现可能需要查询数据库或其他方式验证权限
        return true;
    }
}
