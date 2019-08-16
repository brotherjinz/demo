package com.web.info.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.info.args.ConfigArgs;
import com.web.info.dao.ConfigDao;
import com.web.info.model.Config;
import com.web.info.service.ConfigService;
import com.web.info.system.PageResult;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private ConfigDao configDao;

	@Override
	public Config selectCfg(ConfigArgs args) {
		return configDao.querycfg(args);
	}

	@Override
	public Object queryPager(ConfigArgs args) {
		PageResult result = configDao.queryPager(args);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (result.getPageList() != null) {
			for (int i = 0; i < result.getPageList().size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				Object[] obj = (Object[]) result.getPageList().get(i);
				map.put("CONFIGID", obj[0].toString());
				map.put("PATHFOLDER", obj[1] == null ? "" : obj[1].toString());
				map.put("PATHTEMP", obj[2] == null ? "" : obj[2].toString());
				map.put("PATHURL", obj[3] == null ? "" : obj[3].toString());
				map.put("STATIONCODE", obj[4] == null ? "" : obj[4].toString());
				map.put("STATIONNAME", obj[5] == null ? "" : obj[5].toString());
				map.put("STATION_DB_DRIVER_CLASS_NAME", obj[6] == null ? "" : obj[6].toString());
				map.put("STATION_DB_URL", obj[7] == null ? "" : obj[7].toString());
				map.put("STATION_DB_PASSWORD", obj[8] == null ? "" : obj[8].toString());
				map.put("STATION_DB_TYPE", obj[9] == null ? "" : obj[9].toString());
				map.put("STATION_DB_USERNAME", obj[10] == null ? "" : obj[10].toString());
				map.put("STATION_DB_SID", obj[11] == null ? "" : obj[11].toString());
				list.add(map);
			}
		}
		jsonMap.put("rows", list);
		jsonMap.put("total", result.getTotalRow());
		return jsonMap;
	}

	@Override
	public boolean config(ConfigArgs args) {
		try {
			Config cfg = new Config();
			BeanUtils.copyProperties(args, cfg);
			if (args.getOPERATION().equals("add")) {
				// 执行增加之前首先判断库中有没有相同的，如果有则不可新增
				Config cc = configDao.querycfg(args);
				if (cc == null) {
					configDao.add(cfg);
				} else {
					return false;
				}

			} else if (args.getOPERATION().equals("edit")) {
				configDao.update(cfg);
			} else if (args.getOPERATION().equals("delete")) {
				configDao.delete(cfg);
			}
			return true;
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Config> selectListCfg() {
		// TODO Auto-generated method stub
		return configDao.queryListCfg();
	}
}
