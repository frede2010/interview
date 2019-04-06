package com.furui.annotation.init;

import java.lang.annotation.*;

/**
 * 通过注解进行赋值
 * @author furui
 * @date 2019/4/4 0004
 */
@Documented
@Inherited
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Init {
    public String value() default "";
}
