package com.web.info.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.web.info.args.ScheduleJobArgs;
import com.web.info.dao.ScheduleJobDao;
import com.web.info.model.ScheduleJob;
import com.web.info.service.ImageDataService;
import com.web.util.SpringContext;

public class ImageDataJob implements Job {

	Logger log = Logger.getLogger(ImageDataJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// 定时执行任务类
		ImageDataService imageDataService = (ImageDataService) SpringContext.getBean("imageDataServiceImpl");
		ScheduleJobDao scheduleJobDao = (ScheduleJobDao) SpringContext.getBean("scheduleJobDaoImpl");
		boolean res = imageDataService.imageData();
		//任务执行完成后将定时任务的执行状态改为未启动
		//从数据库查找原信息
		ScheduleJobArgs o=new ScheduleJobArgs();
		o.setScheduleJobId("030d6dc1-5a41-5bcc-aefd-00d757b021ad");
		ScheduleJob scheduleJob=scheduleJobDao.getScheduleJobById(o.getScheduleJobId());
		if (res) {
			scheduleJob.setSTATUS(0);
			scheduleJobDao.update(scheduleJob);
			log.info("成功");
		} else {
			log.info("失败");
		}
	}

}
