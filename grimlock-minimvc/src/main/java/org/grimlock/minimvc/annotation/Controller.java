package org.grimlock.minimvc.annotation;

import java.lang.annotation.*;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 16:05 2018-1-4
 * @Modified By:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}
