package com.design.readwritedb.router;

import com.design.readwritedb.config.DataSourceEnum;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * 描述：动态数据源的上下文 threadlocal
 * </pre>
 *
 * @类名：com.xy.tms.datasource.router.DataSourceContextHolder
 * @作者： sanwu
 * @创建日期: 2019/12/31 15:56
 */
public class DataSourceContextHolder {
    private final static ThreadLocal<String> local = new ThreadLocal<>();

    public static void putDataSource(String name) {
        local.set(name);
    }

    public static String getCurrentDataSource() {
        String datasource = local.get();
        if (StringUtils.isBlank(datasource)||!EnumUtils.isValidEnum(DataSourceEnum.class, datasource.toUpperCase())){
            datasource = DataSourceEnum.MASTER.getName();
        }
        return datasource;
    }

    public static void removeCurrentDataSource() {
        local.remove();
    }

}