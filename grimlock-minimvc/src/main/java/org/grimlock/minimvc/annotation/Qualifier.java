package org.grimlock.minimvc.annotation;

import java.lang.annotation.*;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 16:07 2018-1-4
 * @Modified By:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Qualifier {
    String value() default "";
}
