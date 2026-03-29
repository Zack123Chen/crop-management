package com.example.cropmanagement.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // 🎯 探针固定位置：拦截 controller 包下所有类的所有方法
    @Around("execution(* com.example.cropmanagement.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // 1. 获取当前请求的详细信息（URL, 方法类型）
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 2. 记录“波形”进入
        logger.info("============== ⚡️ 示波器捕获信号 ==============");
        logger.info("📍 URL    : {}", request.getRequestURL().toString());
        logger.info("🛠️ METHOD : {}", request.getMethod());
        logger.info("🎯 TARGET : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        logger.info("📥 PARAMS : {}", Arrays.toString(joinPoint.getArgs()));

        // 3. 让原本的代码继续跑（电流通过）
        Object result = joinPoint.proceed();

        // 4. 记录“波形”输出
        long executionTime = System.currentTimeMillis() - start;
        logger.info("📤 RESULT : {}", result);
        logger.info("⏱️ TIME   : {} ms", executionTime);
        logger.info("==============================================");

        return result;
    }
}