package com.huateng.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/***
 * 
 * @author 11299
 *
 */
public class AccountJobListener implements JobExecutionListener{ 

    long startTime;
    long endTime;
    @Override
    public void beforeJob(JobExecution jobExecution) {
    	jobExecution.getJobId();
        startTime = System.currentTimeMillis();
        System.out.println(jobExecution.getJobInstance().getJobName()+"任务处理开始");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        System.out.println(jobExecution.getJobInstance().getJobName()+"任务处理结束");
        System.out.println(jobExecution.getJobInstance().getJobName()+"耗时:" + (endTime - startTime) + "ms");
    }
    
 

}
