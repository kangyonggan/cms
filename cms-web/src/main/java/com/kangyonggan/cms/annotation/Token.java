package com.kangyonggan.cms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kangyonggan
 * @date 2018/5/1 0001
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Token {

    /**
     * TOKEN的key
     *
     * @return
     */
    String key();

    /**
     * TOKEN的操作类型
     *
     * @return
     */
    Type type() default Type.GENERATE;

    enum Type {
        /**
         * 生成TOKEN
         */
        GENERATE,
        /**
         * 校验TOKEN
         */
        CHECK
    }
}

