package com.design.readwritedb.aop;

import com.design.readwritedb.config.DataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * @Description:
 * dbname
 * </pre>
 *
 * @version v1.0
 * @ClassName: DS
 * @Author: sanwu
 * @Date: 2020/12/17 16:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DS {

    DataSourceEnum name() default DataSourceEnum.MASTER;
}
