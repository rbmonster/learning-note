package com.four.unittest.service;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;


public interface UnitInterface {

    void send(String str);

    default String register(String str) {
        return "register fail return default";
    }

    BigDecimal calculate(double num);
}
