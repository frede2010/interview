package com.furui.annotation.valid;

import java.lang.annotation.*;

/**
 * 通过注解进行校验
 * @author furui
 * @date 2019/4/4 0004
 */
@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
    int min() default 0;
    int max() default 100;
    boolean isNotNull() default true;
    boolean isNotBlank() default true;
}
