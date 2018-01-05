package org.grimlock.minimvc.annotation;

import java.lang.annotation.*;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 16:11 2018-1-4
 * @Modified By:
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default "";
}
