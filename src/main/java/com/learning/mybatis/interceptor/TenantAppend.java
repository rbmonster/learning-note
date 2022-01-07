package com.learning.mybatis.interceptor;

import org.apache.ibatis.mapping.SqlCommandType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TenantAppend {

    SqlCommandType[] sqlType() default {SqlCommandType.SELECT, SqlCommandType.UPDATE};
}
