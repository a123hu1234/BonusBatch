package com.huateng.quartz;

import java.util.List;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/***
 * 定时任务管理类
 * 
 * @author data
 * 
 */
@Repository
public class QuartzManage {
	private Logger log = LoggerFactory.getLogger(QuartzManage.class);

	@Autowired
	private Scheduler scheduler;

	/***
	 * 添加一个定时任务
	 */
	public void addJob(Class<? extends Job> jobClass, String jobName, String jobGroupName, String jobTime,
			Map<String, Object> paramas) {
		try {
			// 创建jobDetail实例，绑定Job实现类
			// 指明job的名称，所在组的名称，以及绑定job类
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName)// 任务名称和组构成任务key
					.build();
			// 传参
			if (paramas != null) {
				for (Map.Entry<String, Object> entry : paramas.entrySet()) {
					jobDetail.getJobDataMap().put(entry.getKey(), entry.getValue());
				}
			}
			// 定义调度触发规则
			// 使用cornTrigger规则
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)// 触发器key
					.startAt(DateBuilder.futureDate(1, IntervalUnit.SECOND))
					.withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).startNow().build();
			// 把作业和触发器注册到任务调度中
			scheduler.scheduleJob(jobDetail, trigger);
			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	/**
	 * 删除一个定时任务
	 */
	public void deleteJob() {

	}

	/***
	 * 停止一个定时任务
	 */
	public void pauseJob() {

	}

	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 */
	public List<Map<String, Object>> queryAllJob() {
	//	TriggerKey key =TriggerKey.triggerKey("HelloWorld");
		//CronTrigger triger = scheduler.getTrigger(key);
		//scheduler.getJobDetail(null).
		return null;
	}

	/**
	 * 获取所有正在运行的job
	 * 
	 * @return
	 */
	public List<Map<String, Object>> queryRunJob() {
		return null;
	}

	/**
	 * 恢复一个job
	 * 
	 * @param jobName
	 * @param jobGroupName
	 */
	public void resumeJob(String jobName, String jobGroupName) {

	}

	/**
	 * 立即执行一个job
	 * 
	 * @param jobName
	 * @param jobGroupName
	 */
	public void runAJobNow(String jobName, String jobGroupName) {

	}

	/***
	 * 修改 一个job的 时间表达式
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param jobTime
	 */
	public void updateJob(String jobName, String jobGroupName, String jobTime) {

	}

	public void start() {
		// 启动

		try {
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (SchedulerException e) {
			log.error(e.getMessage(),e);
			
		}

	}
}
