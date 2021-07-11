package com.four.annotation.annotationobj;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnrichUserInfo {

    boolean useJwtInfo() default true;

}
