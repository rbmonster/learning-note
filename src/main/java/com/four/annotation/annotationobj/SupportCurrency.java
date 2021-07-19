package com.four.annotation.annotationobj;

import com.four.annotation.validator.SupportCurrencyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE;

@Documented
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = SupportCurrencyValidator.class
)
public @interface SupportCurrency {

    boolean required() default true;

    int length() default 3;

//    String message() default "{javax.validation.constraints.DecimalMin.message}";
    String message() default "系统不支持该币种交易";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
