package com.phoenix.signal.controller.platform.utils.aop;

import com.phoenix.signal.controller.platform.model.BasicLog;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAop {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAop.class);
    private static final ThreadLocal<BasicLog> logThreadLocal = new ThreadLocal<>();

    @Pointcut("@annotation(com.phoenix.signal.controller.platform.utils.aop.Log)")
    public void logPointCut(){}

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String contextPath =request.getContextPath();
        String requestUrl = request.getRequestURI();
        if(StringUtils.isNotBlank(contextPath)){
            requestUrl.substring(contextPath.length());
        }

        //isHandleLog
        LOGGER.info("url={},method={},ip={},class_method={},args={}", request.getRequestURI(),request.getMethod(),request.getRemoteAddr(),joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),joinPoint.getArgs());
    }

    @AfterReturning("logPointCut()")
    public void doAfter(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Log log = method.getAnnotation(Log.class);
        String value = null;
        if(log != null){
            value = log.value();
        }
        LOGGER.info(new Date()+"-----"+ log.type().getDesc() +"-----"+value);
    }


}
