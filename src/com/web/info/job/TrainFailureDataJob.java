package com.web.info.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.web.info.args.ScheduleJobArgs;
import com.web.info.args.UtilArgs;
import com.web.info.dao.ScheduleJobDao;
import com.web.info.model.ScheduleJob;
import com.web.info.service.ConfigService;
import com.web.info.service.FaultService;
import com.web.util.SpringContext;

/**
 * 列车表定时任务
 * <p>
 * Title: TrainData
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 李亚洲
 * @date 2018年8月13日
 */
public class TrainFailureDataJob implements Job {

	private static Logger log = Logger.getLogger(TrainFailureDataJob.class);

	@Override
	public void execute(JobExecutionContext arg) throws JobExecutionException {
		FaultService faultService = (FaultService) SpringContext.getBean("faultServiceImpl");
		ConfigService configService = (ConfigService) SpringContext.getBean("configServiceImpl");
//		DataBaseService baseService = (DataBaseService) SpringContext.getBean("dataBaseServiceImpl");
		ScheduleJobDao scheduleJobDao = (ScheduleJobDao) SpringContext.getBean("scheduleJobDaoImpl");
//		DataBaseArgs dataArgs = new DataBaseArgs();
		/*List<DataBaseDto> listdatabase = baseService.queryDataBase(dataArgs);*/
		UtilArgs args = new UtilArgs();
		args.setSZ(true);
		//查询数据源中当前已配置所有探测站,遍历所有探测站同步数据
		boolean fdata=false;
		// 任务执行完成后将定时任务的执行状态改为未启动
		// 从数据库查找原信息
		ScheduleJobArgs o = new ScheduleJobArgs();
		o.setScheduleJobId("030d6dc1-5a41-5bcc-aefd-00d757b021ad");
		ScheduleJob scheduleJob = scheduleJobDao.getScheduleJobById(o.getScheduleJobId());
//		for (DataBaseDto dataBaseDto : listdatabase) {
//		 fdata = faultService.faultData(argsS,dataBaseDto.getC_NAME());
//		}
		if (fdata) {
			scheduleJob.setSTATUS(0);
			scheduleJobDao.update(scheduleJob);
			log.info("自动同步任务执行成功");
		} else {
			log.info("自动同步任务执行失败");
		}

	}

}
