package com.four.transaction.controller;

import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: TransactionService
 * @Author: sanwu
 * @Date: 2020/11/28 23:24
 */
@Service
public class TransactionService {

    private final JdbcTemplate jdbcTemplate;

    Random random = new Random();

    @Autowired
    public TransactionService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.NEVER)
    public void testTransaction() {
        String sql = "INSERT INTO `demo_detail` (`detail_id`, `demo_id`, `detail_remark`, `detail_start`, `detail_end`, `min_value`, `max_value`, `rage`) VALUES (?, 123, ?, '2020-11-07', '2020-11-07', '2', '1', '12')";
        Object[] objects = new Object[]{String.valueOf(random.nextInt(1000000)), "test"};
        jdbcTemplate.update(sql, objects);
    }
}
