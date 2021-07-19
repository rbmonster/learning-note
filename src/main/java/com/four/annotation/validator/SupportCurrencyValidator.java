package com.four.annotation.validator;

import com.four.annotation.annotationobj.SupportCurrency;
import com.four.annotation.util.CurrencyUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SupportCurrencyValidator implements ConstraintValidator<SupportCurrency, String> {
    private boolean required;

    private Integer length;

    @Override
    public void initialize(SupportCurrency constraintAnnotation) {
        this.length = constraintAnnotation.length();
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext constraintValidatorContext) {
        if (!required) {
            return true;
        }
        if (StringUtils.isEmpty(currency) ) {
            return false;
        }
        if (currency.length() != length) {
            return false;
        }
        return CurrencyUtils.support(currency);
    }
}
