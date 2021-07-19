package com.four.annotation.model;

import com.four.annotation.annotationobj.SupportCurrency;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class Book {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;

    @SupportCurrency
    private String currency;
}
