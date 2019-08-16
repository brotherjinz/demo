package com.web.info.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.info.args.EchartsArgs;
import com.web.info.args.UtilArgs;
import com.web.info.dao.ScheduleRecordDao;
import com.web.info.dto.UtilDto;
import com.web.info.service.FaultService;


@Controller
@RequestMapping("echarts/")
public class EchartsController {
	@Autowired
	private ScheduleRecordDao scheduleRecordDao;
	@Autowired
	private FaultService faultService;
	//初始化图形统计图数据queryFault
	@ResponseBody
	@RequestMapping(value = "echarts_data", method = { RequestMethod.GET, RequestMethod.POST })
	public Object queryEcharts(EchartsArgs args){
		return scheduleRecordDao.queryEcharts(args);
	}
	/**
	 * 根据探测站编码查询故障数据
	 * @param args
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getfault", method = { RequestMethod.GET, RequestMethod.POST })
	public List<UtilDto> queryfault(UtilArgs args){
		if(StringUtils.isEmpty(args.getStationcode())){
			args.setStationcode("0");
		}
		return faultService.queryFault(args);
	}
	
	
}
