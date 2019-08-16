package com.web.info.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.info.args.ScheduleJdArgs;
import com.web.info.dao.ScheduleJdDao;
import com.web.info.dto.ScheduleJdDto;
import com.web.info.service.ScheduleJdService;

@Service
public class ScheduleJdServiceImpl implements ScheduleJdService {
	
	@Autowired
	private ScheduleJdDao scheduleJdDao;

	@Override
	public ScheduleJdDto jdMax(ScheduleJdArgs args) {
		return scheduleJdDao.MaxJd(args);
	}
}
