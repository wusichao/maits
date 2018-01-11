package com.wusc.token.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * create by wusc on 2018/1/8
 */
@Documented
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface isLogged {
}
