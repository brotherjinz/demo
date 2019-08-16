package com.web.info.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.web.info.args.ConfigArgs;
import com.web.info.dao.ConfigDao;
import com.web.info.model.Config;
import com.web.info.system.PageResult;

@Repository
public class ConfigDaoImpl extends BaseDao<Config> implements ConfigDao {

	@Override
	public Config querycfg(ConfigArgs args) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM CONFIG T WHERE 1=1");
		if (StringUtils.isNotEmpty(args.getSTATIONCODE())) {
			sql.append(" AND STATIONCODE='" + args.getSTATIONCODE() + "'");
		}
		List<Config> list = super.listBySql(sql.toString(), Config.class, true);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public PageResult queryPager(ConfigArgs args) {
		StringBuffer sql = new StringBuffer();
		PageResult result = null;
		try {
			sql.append(" SELECT CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE, STATIONNAME, STATION_DB_DRIVER_CLASS_NAME, STATION_DB_URL, STATION_DB_PASSWORD, STATION_DB_TYPE, STATION_DB_USERNAME,STATION_DB_SID FROM CONFIG ");
			result = super.getPageListSql(args.getPage(), args.getRows(), sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Config> queryListCfg() {
		// TODO 查询全部探测站
		StringBuffer sql = new StringBuffer(" SELECT * FROM CONFIG");
		return listBySql(sql.toString(), Config.class, true);
	}

}
