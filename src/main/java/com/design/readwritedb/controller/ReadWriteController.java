package com.design.readwritedb.controller;

import com.design.readwritedb.aop.DS;
import com.design.readwritedb.config.DataSourceEnum;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: ReadWriteController
 * @Author: sanwu
 * @Date: 2020/12/17 15:27
 */
@RestController
public class ReadWriteController {

    JdbcTemplate jdbcTemplate;

    final PlatformTransactionManager platformTransactionManager;

    @Autowired
    public ReadWriteController(JdbcTemplate jdbcTemplate, PlatformTransactionManager platformTransactionManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.platformTransactionManager = platformTransactionManager;
    }

    @GetMapping("/readwrite")
    public Map<String, Object> getFromDb(){
        String sql = "select * from container_load limit 1";
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql);
        return stringObjectMap;

    }

    @DS(name = DataSourceEnum.SLAVE)
    @GetMapping("/readwriteByAnnotation")
    public Map<String, Object> getFromDb1(){
        String sql = "select * from container_load limit 1";
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql);
        return stringObjectMap;
    }


}
