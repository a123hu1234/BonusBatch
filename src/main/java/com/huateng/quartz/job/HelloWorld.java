package com.huateng.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HelloWorld implements Job{
	 private Logger loger = LoggerFactory.getLogger(HelloWorld.class);
 
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("sdfs");
		loger.info("hello World");
	}
	
}
