package com.web.info.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.web.info.args.ScheduleJdArgs;
import com.web.info.dao.ScheduleJdDao;
import com.web.info.dto.ScheduleJdDto;
import com.web.info.model.ScheduleJD;

@Repository
public class ScheduleJdDaoImpl extends BaseDao<ScheduleJD> implements ScheduleJdDao {

	@Override
	public ScheduleJdDto MaxJd(ScheduleJdArgs args) {
		StringBuffer sql = new StringBuffer();
		List<Object> arr = new ArrayList<Object>();
		sql.append(" select jd_id, columnname,  t.columnvalue_start,t.columnvalue_end, scheduleorder, scheduletime, tablename from task_schedule_jd t where 1 = 1 and t.scheduleorder = (select max(scheduleorder) from task_schedule_jd)");
		if(StringUtils.isNotEmpty(args.getTABLENAME())) {
			sql.append(" and t.tablename = ?");
			arr.add(args.getTABLENAME());
		}
		List<ScheduleJdDto> ls =super.listBySql(sql.toString(),arr.toArray(), ScheduleJdDto.class, false);
		return ls !=null && ls.size()>0?ls.get(0):null;
	}

}
