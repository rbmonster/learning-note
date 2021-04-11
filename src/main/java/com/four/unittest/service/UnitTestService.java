package com.four.unittest.service;

import com.four.unittest.controller.UnitController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
}
