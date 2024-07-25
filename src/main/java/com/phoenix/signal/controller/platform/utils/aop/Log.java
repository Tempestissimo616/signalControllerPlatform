package com.phoenix.signal.controller.platform.utils.aop;

import com.phoenix.signal.controller.platform.type.SysLogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    /**
     * 描述
     */
    String value() default "";

    /**
     * 日志类型
     */
    SysLogType type() default SysLogType.OTHER;
}
