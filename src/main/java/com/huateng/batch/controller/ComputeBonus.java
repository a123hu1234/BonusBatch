package com.huateng.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/***
 * 计算积分并入待入账表
 * @author data
 *
 */
@Component
public class ComputeBonus {



	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("computeBonusJob")
	Job computeBonusJob;
	
	public JobParameters jobParameters;
	// @RequestMapping("/read")
	public String imp(String path) throws Exception {

		//String path = fileName + ".dat";
		//客
		jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.addString("input.file.name", path).toJobParameters();
		jobLauncher.run(computeBonusJob, jobParameters);
		//卡
		/*jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.addString("input.file.name", path).toJobParameters();
		jobLauncher.run(importCardTmpJob, jobParameters);*/
		
		//帐
		/*jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.addString("input.file.name", path).toJobParameters();
		jobLauncher.run(importAccountTmpJob, jobParameters);*/
		return "ok";
	}



}
