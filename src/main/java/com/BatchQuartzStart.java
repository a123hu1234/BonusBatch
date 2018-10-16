package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.huateng.quartz.QuartzManage;

@SpringBootApplication
public class BatchQuartzStart {
	public static void main(String[] args) {
		ApplicationContext contex = SpringApplication.run(BatchQuartzStart.class, args);

		QuartzManage manage = contex.getBean(QuartzManage.class);
		//manage.addJob(HelloWorld.class, "HelloWorld", "HelloWorld", "0 */1 * * * ?", null);
		manage.start();
		
	}
}
