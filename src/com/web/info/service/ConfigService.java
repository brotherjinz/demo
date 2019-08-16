package com.web.info.service;

import java.util.List;

import com.web.info.args.ConfigArgs;
import com.web.info.model.Config;

public interface ConfigService {
	Config selectCfg(ConfigArgs args);
	
	Object queryPager(ConfigArgs args);
	
	List<Config> selectListCfg();
	
	boolean config(ConfigArgs args);
}
