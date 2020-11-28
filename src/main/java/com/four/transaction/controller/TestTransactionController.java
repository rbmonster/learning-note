package com.four.transaction.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <pre>
 * @Description:
 * 声明式事务
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestTransactionController
 * @Author: sanwu
 * @Date: 2020/11/7 19:08
 */
@RestController
@RequestMapping("/transaction")
public class TestTransactionController implements ApplicationContextAware {

    private final JdbcTemplate jdbcTemplate;

    Random random = new Random();

    final TransactionService transactionService;

    @Autowired
    public TestTransactionController(JdbcTemplate jdbcTemplate, TransactionService transactionService) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public List getIdFromDb(@PathVariable("id") String id) {
        String sql = "select * from demo where demo_id =" + id;
        return jdbcTemplate.queryForList(sql);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = RuntimeException.class, readOnly = false, timeout = -1)
    @GetMapping("/update")
    public String update() {
        Thread thread = Thread.currentThread();
        String sql = "INSERT INTO `demo` (`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (?, 11, ?, '1', '123', '12', '12.000', '2020-11-19 19:36:07', '2020-11-07 19:36:11', '2020-11-07 19:36:18', '123', '123', NULL, NULL, NULL, '0') ";
        Object[] objects = new Object[]{String.valueOf(random.nextInt(1000000)), "test"};
        jdbcTemplate.update(sql, objects);
        return "123";
//        throw new RuntimeException("123123");
    }

    @Transactional(propagation = Propagation.NEVER)
    void testTransaction() {
        String sql = "INSERT INTO `demo_detail` (`detail_id`, `demo_id`, `detail_remark`, `detail_start`, `detail_end`, `min_value`, `max_value`, `rage`) VALUES (?, 123, ?, '2020-11-07', '2020-11-07', '2', '1', '12')";
        Object[] objects = new Object[]{String.valueOf(random.nextInt(1000000)), "test"};
        jdbcTemplate.update(sql, objects);
    }

    @GetMapping("private")
    @Transactional
    public String testPrivate() {
        String sql = "INSERT INTO `demo_detail` (`detail_id`, `demo_id`, `detail_remark`, `detail_start`, `detail_end`, `min_value`, `max_value`, `rage`) VALUES (?, 123, ?, '2020-11-07', '2020-11-07', '2', '1', '12')";
        Object[] objects = new Object[]{String.valueOf(random.nextInt(1000000)), "test"};
        jdbcTemplate.update(sql, objects);
        transactionService.testTransaction();
        return "111";
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
