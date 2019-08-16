package com.web.info.job;

import java.util.List;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.web.info.dao.ScheduleJobDao;
import com.web.info.model.ScheduleJob;
import com.web.util.ScheduleUtils;

/**
 * spring容器加载完毕做一件事情（利用ContextRefreshedEvent事件）
 *
 */
@Component("BeanDefineConfigue")
public class BeanDefineConfigue implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ScheduleJobDao jobDao;

	/** 调度工厂Bean */
	@Autowired
	private Scheduler scheduler;
	private String flag = "0";

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (flag.equals("0")) {
			// 查找数据库中已经开启的定时任务，将其创建到Scheduler工厂中
			List<ScheduleJob> list = jobDao.getRunJob();
			if (list != null && list.size() > 0) {
				for (ScheduleJob job : list) {
					if (job.getSTATUS() != null && job.getSTATUS() == 1) {
						try {
							ScheduleUtils.createScheduleJob(scheduler, job);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			flag = "1";
		} else {

		}

	}
}
