package com.four.annotation.util;

import com.four.annotation.model.Currency;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Locale;

public class CurrencyUtils {

    public static  boolean support(String input) {
        if (StringUtils.isEmpty(input)){
            return false;
        }
        return Arrays.stream(Currency.values()).anyMatch(currency -> currency.name().equals(input.toUpperCase(Locale.ROOT)));
    }
}
