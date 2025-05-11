package com.example.spring_boot_core_final.account.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AccountAop {

    @Pointcut("execution(* com.example.spring_boot_core_final.account.service.AuthenticationService.login(..)) ||" +
              "execution(* com.example.spring_boot_core_final.account.service.AuthenticationService.logout())")
    public void cut() {}

    @Before("cut()")
    public void logLoginAndLogout(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        log.info("{}({})", methodName, args);
    }
}