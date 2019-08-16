package com.web.info.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web.info.dao.ScheduleJobDao;
import com.web.info.model.ScheduleJob;
import com.web.info.system.PageResult;
@Repository
public class ScheduleJobDaoImpl extends BaseDao<ScheduleJob> implements ScheduleJobDao {

	@Override
	public ScheduleJob getScheduleJobById(String scheduleJobId) {
		String sql = " SELECT * FROM TASK_SCHEDULE  where SCHEDULEJOBID='"+scheduleJobId+"'";
		List<ScheduleJob> list = super.listBySql(sql, ScheduleJob.class, true);
		return list.get(0);
	}
	@Override
	public PageResult getJob(String sqlWhere, String page, String limit) {
		PageResult templist = null;
		try {
			String sql = "SELECT t.scheduleJobId,t.jobName,t.aliasName,t.jobGroup,t.jobClass,t.status,t.cronExpression,t.description,t.createTime,t.updateTime FROM task_schedule t WHERE 1=1 ";
			templist = super.getPageListSql(page, limit, sql);// this.getPageListSql(page, limit, sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return templist;
	}
	
	@Override
	public List<ScheduleJob> getRunJob() {
		String sql = "SELECT t.scheduleJobId,t.jobName,t.aliasName,t.jobGroup,t.jobClass,t.status,t.cronExpression,t.description,t.createTime,t.updateTime FROM task_schedule t WHERE 1=1 and t.status = '1'";
		List<ScheduleJob> list = super.listBySql(sql, ScheduleJob.class, true);
		return list;
	}
	
}
