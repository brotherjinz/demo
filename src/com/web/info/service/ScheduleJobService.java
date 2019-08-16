package com.web.info.service;

import net.sf.json.JSONObject;

import com.web.info.args.ScheduleJobArgs;
/**
 * 定时任务service
 */
public interface ScheduleJobService {

	/**
     * 获取所有任务 列表
     */
    public JSONObject getJob(String sqlWhere,String page,String limit);
    /**
    * 运行一次任务
    *
    * @param scheduleJobId the schedule job id
    * @return
    */
    public int runOnce(ScheduleJobArgs o);
    
    
    /**
     * 启动任务
     * @param o
     * @return
     */
    public int resumeJob(ScheduleJobArgs o);
    
    /**
     * 暂停任务
     */
    public int pauseJob(ScheduleJobArgs o);
	
}
