package com.ikolosov.apt.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE,
		ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface CustomAnnotation {
}
