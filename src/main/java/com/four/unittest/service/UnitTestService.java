package com.four.unittest.service;

import com.four.unittest.controller.UnitController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UnitTestService {


    @Autowired
    UnitController unitController;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String testSql() {
        log.info("test jdbc update");
        return "hehe";
    }


    public String testJdbcTemplate() {
        List<Object> query = jdbcTemplate.query("select * from demo limit 2",
                (resultSet, i) -> resultSet.getString(2));
        return query.get(0).toString();
    }


    public String testParameter(int n, String str, BigDecimal bigDecimal, List<Integer> list, Map<Double, String> map) {
        return n + str + bigDecimal + list + map;
    }
}
