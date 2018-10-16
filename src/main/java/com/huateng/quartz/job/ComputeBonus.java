package com.huateng.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.huateng.util.SpringSourceUtil;

/***
 * 计算积分并插入待入账积分表TBL_TXN_ORA_DAILY
 * @author data
 *
 */
public class ComputeBonus implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SpringSourceUtil.getBean("");
		
	}
	
}
