package com.web.info.service.impl;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.info.args.ScheduleJobArgs;
import com.web.info.dao.ScheduleJobDao;
import com.web.info.dao.impl.BaseDao;
import com.web.info.model.ScheduleJob;
import com.web.info.service.ScheduleJobService;
import com.web.info.system.PageResult;
import com.web.util.ScheduleUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("scheduleJobService")
public class ScheduleJobServiceImp extends BaseDao<ScheduleJob> implements ScheduleJobService {

	/*@Autowired
	protected BaseDao<T> baseDao;*/
	
	@Autowired
	private ScheduleJobDao jobDao;
	
	/** 调度工厂Bean */
	@Autowired
	private Scheduler scheduler;

	
	@Override
	public JSONObject getJob(String sqlWhere,String page,String limit) {
		PageResult result = jobDao.getJob(sqlWhere, page, limit);
		JSONArray arr = new JSONArray();
		JSONObject returnjsonobj = new JSONObject();
		for (int i = 0; i < result.getPageList().size(); i++) {
			Object[] obj = (Object[]) result.getPageList().get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("scheduleJobId", obj[0] == null ? "" : obj[0].toString());
			jsonObject.put("jobName", obj[1] == null ? "" : obj[1].toString());
			jsonObject.put("aliasName", obj[2] == null ? "" : obj[2].toString());
			jsonObject.put("jobGroup", obj[3] == null ? "" : obj[3].toString());
			jsonObject.put("jobClass", obj[4] == null ? "" : obj[4].toString());
			jsonObject.put("status", obj[5] == null ? "" : obj[5].toString());
			jsonObject.put("cronExpression", obj[6] == null ? "" : obj[6].toString());
			jsonObject.put("description", obj[7] == null ? "" : obj[7].toString());
			jsonObject.put("createTime", obj[8] == null ? "" : obj[8].toString());
			jsonObject.put("updateTime", obj[9] == null ? "" : obj[9].toString());
			arr.add(jsonObject);
		}
		returnjsonobj.put("rows", arr);
		returnjsonobj.put("total", result.getTotalRow());
		return returnjsonobj;
	}
	
	@Override
	@Transactional
	public int runOnce(ScheduleJobArgs o) {
		int res=0;
		try {
			//从数据库查找原信息
			ScheduleJob scheduleJob=jobDao.getScheduleJobById(o.getScheduleJobId());
			if(scheduleJob.getSTATUS()!=null && scheduleJob.getSTATUS()==1){
				//运行一次任务
				res=2;
			}else{
				//当任务没启动，必须先创建
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
				//时间短可能促发多次
				//ScheduleUtils.pauseJob(scheduler,scheduleJob.getJobName(), scheduleJob.getJobGroup());
				//然后立刻运行一次任务
				ScheduleUtils.runOnce(scheduler, scheduleJob.getJOBNAME(), scheduleJob.getJOBGROUP());
				//更新数据库
				scheduleJob.setSTATUS(3);
				jobDao.update(scheduleJob);
				
				/*try {
					//休眠3秒，等任务完成，完成不了就加长休眠时间吧...
			        //Thread.sleep(3000);
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }*/
				//再删除任务
				ScheduleUtils.deleteScheduleJob(scheduler,scheduleJob.getJOBNAME(), scheduleJob.getJOBGROUP());
				res=1;
			}			
		} catch (Exception e) {
			//log.error("运行一次定时任务失败", e);
		}
		return res;
	}
	
	@Override
	@Transactional
	public int resumeJob(ScheduleJobArgs o) {
		int res=0;
		try {
			//从数据库查找原信息
			ScheduleJob scheduleJob=jobDao.getScheduleJobById(o.getScheduleJobId());
			if(scheduleJob.getSTATUS()!=null && scheduleJob.getSTATUS()==0){
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
				//更新数据库
				scheduleJob.setSTATUS(1);
				scheduleJob.setUPDATETIME(super.getNowDate());
				jobDao.update(scheduleJob);
				res=1;
			}else{
				res=2;
			}
		} catch (Exception e) {
			//logger.error("恢复定时任务失败", e);
		}
		return res;
	}
	
	@Override
	@Transactional
	public int pauseJob(ScheduleJobArgs o) {
		int res=0;
		try {
			//从数据库查找原信息
			ScheduleJob scheduleJob=jobDao.getScheduleJobById(o.getScheduleJobId());
			if(scheduleJob.getSTATUS()!=null && scheduleJob.getSTATUS()==1){
				//判断jobKey为不为空，如为空，任务已停止
				//先暂停任务
				//ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());		
				ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJOBNAME(), scheduleJob.getJOBGROUP());
				//更新数据库
				scheduleJob.setSTATUS(0);
				scheduleJob.setUPDATETIME(super.getNowDate());;
				jobDao.update(scheduleJob);
				res=1;
			}else{	
				//任务没启动，谈何暂停...
				res=2;			
			}
		} catch (Exception e) {
			//logger.error("暂停定时任务失败", e);
		}
		return res;
	}
	/*@Override
	public void initScheduleJob() {
		//查找启用的任务
		ScheduleJob aj=new ScheduleJob();
		aj.setStatus(1);	
		List<ScheduleJob> scheduleJobList = baseDao.find(aj);	
		if (CollectionUtils.isNotEmpty(scheduleJobList)) {
			for (ScheduleJob scheduleJob : scheduleJobList) {
				CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobName(),scheduleJob.getJobGroup());
				try {
					if (cronTrigger == null) {
						// 不存在，创建一个
						ScheduleUtils.createScheduleJob(scheduler, scheduleJob);	
					} else {
						// 已存在，那么更新相应的定时设置
						ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
					}
				} catch (Exception e) {
					logger.error("创建定时任务失败",e);
				}
			}
		}
	}*/

	/*@Override
	@Transactional
	public int creatScheduleJob(ScheduleJob o) {
		int res=0;
		try {
			o.setCreateTime(new Date());	
			//当状态为启用时
			if(o.getStatus()!=null && o.getStatus()==1){
				ScheduleUtils.createScheduleJob(scheduler,o);		
			}
			//更新数据库
			super.insert(o);
			res=1;
		} catch (Exception e) {
			logger.error("创建任务失败",e);
		}	
		return res;
	}*/
	/*@Override
	@Transactional
	public int updateScheduleJob(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			//先删除
			ScheduleUtils.deleteScheduleJob(scheduler,scheduleJob.getJobName(),scheduleJob.getJobGroup());
			//当状态为启用时
			if(o.getStatus()!=null && o.getStatus()==1){
				ScheduleUtils.createScheduleJob(scheduler, o);		
			}
			//更新数据库
			o.setUpdateTime(new Date());
			dao.update(o);
			res=1;
		} catch (Exception e) {
			logger.error("创建任务失败",e);
		}	
		return res;
	}*/

	/*@Override
	@Transactional
	public int deleteScheduleJob(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			//先删除
			ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(),scheduleJob.getJobGroup());
			//更新数据库
			dao.delete(o);
			res=1;
		}catch (Exception e) {
			logger.error("删除任务失败", e);
		}
		return res;
	}*/

	/*@Override
	@Transactional
	public int pauseJob(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			if(scheduleJob.getStatus()!=null && scheduleJob.getStatus()==1){
				//判断jobKey为不为空，如为空，任务已停止
				//先暂停任务
				//ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());		
				ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
				//更新数据库
				scheduleJob.setStatus(0);
				scheduleJob.setUpdateTime(new Date());
				dao.update(scheduleJob);
				res=1;
			}else{	
				//任务没启动，谈何暂停...
				res=2;			
			}
		} catch (Exception e) {
			logger.error("暂停定时任务失败", e);
		}
		return res;
	}*/


}
