package com.common;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 日志注解类
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})  //只能在方法上使用注解
public @interface Log {
    /**
     * 日志描述，使用@AliasFor 别名
     */
    @AliasFor("desc")
    String value() default "";

    @AliasFor("value")
    String desc() default "";

    /**
     * 是否记录日志
     * @return
     */
    boolean ignore() default false;
}
