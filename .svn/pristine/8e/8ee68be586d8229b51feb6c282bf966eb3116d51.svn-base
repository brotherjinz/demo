package com.web.info.dao;

import java.util.List;

import com.web.info.args.EchartsArgs;
import com.web.info.args.ScheduleRecordArgs;
import com.web.info.dto.EchartsDto;
import com.web.info.dto.ScheduleRecordDto;
import com.web.info.model.ScheduleRecord;

public interface ScheduleRecordDao extends IBase<ScheduleRecord> {
	List<ScheduleRecordDto> queryList(ScheduleRecordArgs args);
	
	List<ScheduleRecordDto> imgQueryList(String vs);
	
	//查询统计
	List<EchartsDto> queryEcharts(EchartsArgs arsg);
}
