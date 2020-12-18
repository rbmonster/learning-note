package com.design.readwritedb.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.design.readwritedb.router.DynamicDataSourceRouter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * <pre>
 * 描述：数据源配置
 *
 *  注入事务数据源使用默认 DataSourceTransactionManagerAutoConfiguration 用于事务管理
 * </pre>
 *
 * @类名：com.xy.tms.datasource.config.DataSourceConfig
 * @作者： sanwu
 * @创建日期: 2019/12/31 15:56
 */
@Slf4j
@AutoConfigureBefore({DruidDataSourceAutoConfigure.class})
@Configuration
public class DataSourceConfig {

    /***
     * 注意这里用的 Druid 连接池
     */
    @Bean(name = "dbMaster")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource dbMaster() {
        log.info("generate master dataSource independently");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dbSlave")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource dbSlave() {
        log.info("generate slave dataSource independently");
        return DruidDataSourceBuilder.create().build();
    }

    /***
     * @Primary： 相同的bean中，优先使用用@Primary注解的bean.
     * @Qualifier:： 这个注解则指定某个bean有没有资格进行注入。
     */
    @Primary
    @Bean(name = "dataSource") // 对应Bean: DataSource
    public DataSource dynamicDataSource(@Qualifier("dbMaster") DataSource master, @Qualifier("dbSlave") DataSource slave) {
        DynamicDataSourceRouter dataSourceRouter = new DynamicDataSourceRouter();
        log.info(" ---------------------- dynamic dataSource configure begin----------------------");
        DruidDataSource druidDataSourceMaster = (DruidDataSource) master;
        DruidDataSource druidDataSourceSlave = (DruidDataSource) slave;
        log.info("master configuration: ");
        log.info("Test validate sql connection: " + druidDataSourceMaster.getValidationQuery());
        log.info("The minimum number of free connection pools: " + druidDataSourceMaster.getMinIdle());
        log.info("RemoveAbandoned function: " + druidDataSourceMaster.removeAbandoned());
        log.info("The time limit of overtime connection: " + druidDataSourceMaster.getRemoveAbandonedTimeout());
        log.info("slave configuration: ");
        log.info("Test validate sql connection: " + druidDataSourceSlave.getValidationQuery());
        log.info("The minimum number of free connection pools: " + druidDataSourceSlave.getMinIdle());
        log.info("RemoveAbandoned function: " + druidDataSourceSlave.removeAbandoned());
        log.info("The time limit of overtime connection: " + druidDataSourceSlave.getRemoveAbandonedTimeout());
        log.info(" ---------------------- dynamic dataSource configure END----------------------");

        //配置多数据源
        Map<Object, Object> map = new HashMap<>(5);
        map.put(DataSourceEnum.SLAVE.getName(), slave);
        map.put(DataSourceEnum.MASTER.getName(), master);    // key需要跟ThreadLocal中的值对应
        // master 作为默认数据源
        dataSourceRouter.setDefaultTargetDataSource(master);
        dataSourceRouter.setTargetDataSources(map);
        return dataSourceRouter;
    }

}
