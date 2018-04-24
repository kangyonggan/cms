package com.kangyonggan.cms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举注解。有此注解的枚举，可以被EnumTag调用。
 *
 * @author kangyonggan
 * @date 4/24/18
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Enum {

    /**
     * 枚举的key，重复则报错。
     *
     * @return
     */
    String key() default "";

    /**
     * 枚举的代码
     *
     * @return
     */
    String code() default "code";

    /**
     * 枚举的名字
     *
     * @return
     */
    String name() default "name";

    /**
     * 枚举的描述
     *
     * @return
     */
    String description() default "";

}
