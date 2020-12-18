package com.design.readwritedb.config;

/**
 * <pre>
 * 描述：数据库类型枚举类
 * </pre>
 *
 * @类名：com.xy.tms.datasource.config.DataSourceEnum
 * @作者： sanwu
 * @创建日期: 2019/12/31 15:56
 */
public enum DataSourceEnum {

	// 主表
	MASTER("master"),
	// 从表
	SLAVE("slave");

	private String name;

	private DataSourceEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
