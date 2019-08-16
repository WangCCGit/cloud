package com.it.cloud.ztfilter;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckToken {

	boolean value() default true;
}
