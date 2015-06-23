package com.ikolosov.apt.annotation;

import java.lang.annotation.*;

/**
 * @author ikolosov
 */
@Documented
@Target({ElementType.TYPE,
		ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface CustomAnnotation {
}
