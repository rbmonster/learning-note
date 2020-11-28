package com.four.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * <pre>
 * @Description:
 * 编程式事务
 * </pre>
 *
 * @version v1.0
 * @ClassName: ProgramingTransactionController
 * @Author: sanwu
 * @Date: 2020/11/8 13:19
 */
@RestController
@RequestMapping("/program")
public class ProgramingTransactionController {

    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    private JdbcTemplate jdbcTemplate;

    private Random random;

    @Autowired
    public ProgramingTransactionController(JdbcTemplate jdbcTemplate, PlatformTransactionManager transactionManager, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionManager = transactionManager;
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * 使用transaction manager实现编程性事务
     */
    @GetMapping("/manager")
    public void testManager() {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            String sql = "INSERT INTO `demo` (`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (?, 11, ?, '1', '123', '12', '12.000', '2020-11-19 19:36:07', '2020-11-07 19:36:11', '2020-11-07 19:36:18', '123', '123', NULL, NULL, NULL, '0') ";
            Object[] objects = new Object[]{String.valueOf(random.nextInt(1000000)), "test"};
            jdbcTemplate.update(sql, objects);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
    }

    /**
     * 使用transaction Template 实现编程性事务
     */
    @GetMapping("/template")
    public void testTemplate() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    String sql = "INSERT INTO `demo` (`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (?, 11, ?, '1', '123', '12', '12.000', '2020-11-19 19:36:07', '2020-11-07 19:36:11', '2020-11-07 19:36:18', '123', '123', NULL, NULL, NULL, '0') ";
                    Object[] objects = new Object[]{String.valueOf(random.nextInt(1000000)), "test"};
                    jdbcTemplate.update(sql, objects);
                    // ....  业务代码
                } catch (Exception e){
                    //回滚
                    transactionStatus.setRollbackOnly();
                }

            }
        });
    }
}
