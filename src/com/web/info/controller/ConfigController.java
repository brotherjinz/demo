package com.web.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.info.args.ConfigArgs;
import com.web.info.service.ConfigService;
import com.web.util.DataSourceConst;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("config/")
public class ConfigController {
	@Autowired
	private ConfigService configService;

	@ResponseBody
	@RequestMapping(value = "queryPager", method = { RequestMethod.GET, RequestMethod.POST })
	public Object queryPager(ConfigArgs args) {
		return configService.queryPager(args);
	}

	/**
	 * 增删改
	 * 
	 * @param args
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "cinfig", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean cinfig(ConfigArgs args) {
		if ("1".equals(args.getSTATION_DB_TYPE())) {
			args.setSTATION_DB_DRIVER_CLASS_NAME(DataSourceConst.oracle);
		}
		return configService.config(args);
	}
}
