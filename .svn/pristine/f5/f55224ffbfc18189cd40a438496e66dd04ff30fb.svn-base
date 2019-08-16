package com.web.util;


public class DataSourceContextHolder {

	
	
	/**
	 * 为了线程安全，设置每个线程单独的变量
	 */
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal contextHolder = new ThreadLocal();

	/**
	 * @Description:设置当前数据源的key
	 * @param
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public static void setDataSourseType(String dataSourceType) {
		contextHolder.set(dataSourceType);
	}

	/**
	 * @Description:获取当前数据源的key
	 * @param
	 * @return String
	 */
	public static String getDateSourceType() {
		return (String) contextHolder.get();
	}

	/**
	 * @Description:清除当前数据源的key
	 * @param
	 * @return void
	 */
	public static void clearDataSourceType() {
		contextHolder.remove();
	}

}
