package com.design.readwritedb.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <pre>
 * 描述：在访问数据库前会调用该类的 determineCurrentLookupKey() 方法获取数据库实例的 key
 * AbstractRoutingDataSource抽象类
 * 		1.AbstractRoutingDataSource中determineTargetDataSource()方法中获取数据源 
 * 			Object lookupKey = determineCurrentLookupKey();
 * 			DataSource dataSource = this.resolvedDataSources.get(lookupKey);
 * 			根据determineCurrentLookupKey()得到Datasource,并且此方法是抽象方法,应用可以实现
 *     2.resolvedDataSources 的值根据 targetDataSources 所得 afterPropertiesSet()方法[该方法在@Bean所在方法执行完成后执行]中:
 * 			Map.Entry<Object, Object> entry : this.targetDataSources.entrySet()
 *     3.然后在xml中使用<bean>或者代码中@Bean 设置 dataSource 的defaultTargetDataSource(默认数据源)和 targetDataSources(多数据源)
 * </pre>
 *
 * @类名：com.xy.tms.datasource.router.DynamicDataSourceRouter
 * @作者： sanwu
 * @创建日期: 2019/12/31 15:56
 */
@Slf4j
public class DynamicDataSourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String datasource = DataSourceContextHolder.getCurrentDataSource();
        log.debug("当前使用数据源为: " + datasource);
        return datasource;
    }
}
