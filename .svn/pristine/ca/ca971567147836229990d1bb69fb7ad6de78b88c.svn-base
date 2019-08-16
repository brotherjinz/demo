package com.web.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

/**
 * 重写一个动态数据源的类，继承Spring AbstractRoutingDataSource类，并且实现determineCurrentLookupKey()方法。 看determineCurrentLookupKey()方法的注释，意思大致就是决定当前数据源的key。
 * 
 * @author Administrator
 * 
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private Logger log = Logger.getLogger(this.getClass());
	private Map<Object, Object> _targetDataSources;

	/**
	 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
	 * @describe 数据源为空或者为0时，自动切换至默认数据源，即在配置文件中定义的dataSource数据源
	 */

	@Override
	protected Object determineCurrentLookupKey() {
		String dataSourceName = DataSourceContextHolder.getDateSourceType();
		if (dataSourceName == null) {
			dataSourceName = DataSourceConst.default_db;
		} else {
			this.selectDataSource(dataSourceName);
		}
		log.debug("--------> use datasource " + dataSourceName);
		return dataSourceName;
	}

	/**
	 * 到数据库中查找名称为dataSourceName的数据源
	 * 
	 * @author Geloin
	 * @date Jan 20, 2014 12:15:41 PM
	 * @param dataSourceName
	 */
	private void selectDataSource(String dataSourceName) {
		Object sid = DataSourceContextHolder.getDateSourceType();
		if (StringUtils.isEmpty(dataSourceName) || dataSourceName.trim().equals("default_db")) {
			DataSourceContextHolder.setDataSourseType("default_db");
			return;
		}
		Object obj = this._targetDataSources.get(dataSourceName);
		if (obj != null && sid.equals(dataSourceName)) {
			return;
		} else {
			DataSource dataSource = this.getDataSource(dataSourceName);
			if (null != dataSource) {
				this.setDataSource(dataSourceName, dataSource);
			}
		}
	}

	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		this._targetDataSources = targetDataSources;
		super.setTargetDataSources(this._targetDataSources);
		afterPropertiesSet();
	}

	private void addTargetDataSource(String key, DataSource dataSource) {
		this._targetDataSources.put(key, dataSource);
		this.setTargetDataSources(this._targetDataSources);
	}

	private DataSource createDataSource(String driverClassName, String url, String username, String password) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	/**
	 * 到数据库中查询名称为dataSourceName的数据源
	 * 
	 * @author Geloin
	 * @date Jan 20, 2014 12:18:12 PM
	 * @param dataSourceName
	 * @return
	 */
	private DataSource getDataSource(String dataSourceName) {
		this.selectDataSource(DataSourceConst.default_db);
		this.determineCurrentLookupKey();
		Connection conn = null;
		try {
			conn = this.getConnection();
			StringBuilder builder = new StringBuilder();
			builder.append(" SELECT CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE, STATIONNAME, STATION_DB_DRIVER_CLASS_NAME, STATION_DB_URL,STATION_DB_SID, STATION_DB_PASSWORD, STATION_DB_TYPE, STATION_DB_USERNAME FROM CONFIG WHERE STATIONCODE = ? ");
			PreparedStatement ps = conn.prepareStatement(builder.toString());
			ps.setString(1, dataSourceName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				Integer type = rs.getInt("STATION_DB_TYPE");
				if (type != null && type.intValue() == DataSourceConst.DataSourceType.intValue()) {
					// DB
					String url = "jdbc:oracle:thin:@" + rs.getString("STATION_DB_URL") + "/" + rs.getString("STATION_DB_SID");
					String userName = rs.getString("STATION_DB_USERNAME");
					String password = rs.getString("STATION_DB_PASSWORD");
					String driverClassName = rs.getString("STATION_DB_DRIVER_CLASS_NAME");
					DataSource dataSource = this.createDataSource(driverClassName, url, userName, password);
					return dataSource;
				} else {
					// JNDI
					String jndiName = rs.getString("STATIONCODE");
					JndiDataSourceLookup jndiLookUp = new JndiDataSourceLookup();
					DataSource dataSource = jndiLookUp.getDataSource(jndiName);
					return dataSource;
				}

			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
		return null;
	}

	/**
	 * 将已存在的数据源存储到内存中
	 * 
	 * @author Geloin
	 * @date Jan 20, 2014 12:24:13 PM
	 * @param dataSourceName
	 * @param dataSource
	 */
	private void setDataSource(String dataSourceName, DataSource dataSource) {
		this.addTargetDataSource(dataSourceName, dataSource);
		DataSourceContextHolder.setDataSourseType(dataSourceName);
	}

}
