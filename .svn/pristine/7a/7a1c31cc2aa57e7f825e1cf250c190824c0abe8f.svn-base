package com.web.info.dao;


import java.util.List;

import com.web.info.dao.IBase;
import com.web.info.model.ScheduleJob;
import com.web.info.system.PageResult;
/**
 * 动态任务数据层
 */
public interface ScheduleJobDao extends IBase<ScheduleJob>{
	/**
	 * 根据Id获取任务
	 */
	public ScheduleJob getScheduleJobById(String scheduleJobId);
	
	/**
	 * 自动任务查询列表
	 * @param sqlWhere
	 * @param page
	 * @param limit
	 * @return
	 */
	public PageResult getJob(String sqlWhere, String page, String limit);
	
	/**
	 * 启动项目时调用
	 * 获取所有启动的任务
	 */
	public List<ScheduleJob> getRunJob();
	
}
