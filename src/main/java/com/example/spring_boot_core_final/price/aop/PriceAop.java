package com.example.spring_boot_core_final.price.aop;


import com.example.spring_boot_core_final.account.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class PriceAop {

    private final AuthenticationService authService;

    @Pointcut("execution(* com.example.spring_boot_core_final.price.service.PriceService.*(..))")
    public void cut() {}

    @Before("cut()")
    public void beforeCut(JoinPoint joinPoint) {
        String userName = authService.getCurrentAccount().getName();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        log.info("----- {} class {}.{}({}) ----->", userName, className, methodName, args);
    }

    @AfterReturning(value = "cut()", returning = "result")
    public void afterCut(JoinPoint joinPoint, Object result) {
        String userName = authService.getCurrentAccount().getName();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        String values = (result instanceof Iterable || result instanceof Object[])
                ? result.toString()
                : String.valueOf(result);

        log.info("<----- {} class {}.{}({}) -----", userName, className, methodName, values);
    }
}