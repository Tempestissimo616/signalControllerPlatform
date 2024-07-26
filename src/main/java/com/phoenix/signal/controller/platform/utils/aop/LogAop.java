package com.phoenix.signal.controller.platform.utils.aop;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import com.phoenix.signal.controller.platform.model.BasicLog;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogAop {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAop.class);
    private static final ThreadLocal<BasicLog> logThreadLocal = new ThreadLocal<>();
    private static final boolean isPrintingLog = true;

    @Pointcut("@annotation(com.phoenix.signal.controller.platform.utils.aop.Log)")
    public void logPointCut(){}

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String contextPath =request.getContextPath();
        String requestUrl = request.getRequestURI();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Log log = method.getAnnotation(Log.class);

        if(StringUtils.isNotBlank(contextPath)){
            requestUrl.substring(contextPath.length());
        }

        BasicLog basicLog = new BasicLog();
        basicLog.setCreatedTime(LocalDateTime.now());
        logThreadLocal.set(basicLog);

        basicLog.setRequestUrl(requestUrl);
        basicLog.setRequestIp(request.getRemoteAddr());
        basicLog.setContentType(request.getContentType());
        basicLog.setLogType(log.type());
        basicLog.setRequestMethod(request.getMethod());
        String requestBodyString = handleRequestParam(request,method,basicLog);
        basicLog.setClassName(method.getDeclaringClass().getName());
        basicLog.setMethodName(method.getName());
        basicLog.setReferer(request.getHeader("Referer"));
        basicLog.setUserAgent(request.getHeader("User-Agent"));
        String origin = request.getHeader("Origin");

        if(StringUtils.isNotBlank(origin) && origin.contains("swagger-ui")){
            basicLog.setOrigin("swagger-ui");
        }else{
            basicLog.setOrigin(origin);
        }

        String requestLog = basicLog.getRequestMethod() + "," + basicLog.getRequestUrl() + "," + basicLog.getContentType() + ",isRequestBody:" + basicLog.getJsonRequestBody() + "," + basicLog.getRequestParam();
        LOGGER.info(requestLog);


    }
   //可改成around

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

        BasicLog basicLog = logThreadLocal.get();
        logThreadLocal.remove();

        basicLog.setResponseSuccess(Boolean.TRUE);
        LocalDateTime requestTime = basicLog.getCreatedTime();
        Long startTimeMs = LocalDateTimeUtil.toEpochMilli(requestTime);
        Long endTimeMs = LocalDateTimeUtil.toEpochMilli(LocalDateTime.now());
        Long timeDiff = endTimeMs - startTimeMs;
        String diffStr;
        if(timeDiff >= 1000){
            BigDecimal secondDiff = NumberUtil.div(new BigDecimal(timeDiff), 1000, 2);
            diffStr = secondDiff + "s";
        }else{
            diffStr = timeDiff + "ms";
        }

        basicLog.setDiffTimeDesc(diffStr);
        basicLog.setDiffTime(timeDiff);
        basicLog.setResponseTime(endTimeMs);

        //logService saveLog

        //可添加LogService 把信息储存到数据库

        // 打印请求日志
        printLog(basicLog);
    }


    private String handleRequestParam(HttpServletRequest request, Method method, BasicLog basicLog) {
        // 判断是否是JSON参数请求
        Annotation[][] annotations = method.getParameterAnnotations();
        boolean isRequestBody = isRequestBody(annotations);
        // 是否是JSON请求映射参数
        basicLog.setJsonRequestBody(isRequestBody);
        String requestBodyString = handleRequestParam(request, basicLog, isRequestBody);
        try {
            // 设置token  doesn have so far
//            Optional.ofNullable(BaseTokenThreadLocal.<DxToken>getToken()).ifPresent(token -> basicLog.setToken(token.toString()));
        } catch (Exception e) {
            LOGGER.error("handleRequestParam error...", e);
        }
        return requestBodyString;
    }


    private String handleRequestParam(HttpServletRequest request, BasicLog basicLog, boolean isRequestBody){
        String requestBodyString = null;
        String parameterMapString = null;
        if(isRequestBody){
            requestBodyString =(String) request.getAttribute("requestBody");
        }
        Map<String,String[]> paramsMap =request.getParameterMap();

        if (paramsMap != null && !paramsMap.isEmpty()) {
            // 使用 LinkedHashMap 保持参数顺序，如果需要的话
            Map<String,String> flattenedParams = new LinkedHashMap<>();
            paramsMap.forEach((key, values) -> {
                // 只取第一个值，如果有多个值，可以调整这里的逻辑
                flattenedParams.put(key, Arrays.toString(values));
            });

            // 转换为字符串
            parameterMapString = flattenedParams.entrySet().stream()
                    .map(e -> e.getKey() + "=" + e.getValue())
                    .collect(Collectors.joining(", ", "{", "}"));
        }


        String requestInfo = null;
        if (StringUtils.isNotBlank(requestBodyString) && StringUtils.isNotBlank(parameterMapString)) {
            requestInfo = "paramMap:" + parameterMapString + ",requestBody:" + requestBodyString;
        } else if (StringUtils.isNotBlank(requestBodyString)) {
            requestInfo = requestBodyString;
        } else if (StringUtils.isNotBlank(parameterMapString)) {
            requestInfo = parameterMapString;
        }
        // 请求参数
        basicLog.setRequestParam(requestInfo);
        return requestBodyString;
    }

    private boolean isRequestBody(Annotation[][] annotations){
        if(annotations == null){
            return false;
        }

        for(Annotation[] annotationList : annotations){
            if(ArrayUtils.isNotEmpty(annotationList)){
                for(Annotation annotation : annotationList){
                    if(annotation instanceof RequestBody){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void printLog(BasicLog basicLog){
        if(isPrintingLog){
            if(basicLog != null){
                LOGGER.info(basicLog.toString());
            }
        }
    }


}
