package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.huateng.batch.controller.DemoController;
import com.huateng.batch.controller.ImportCustController;

//@SpringBootApplication
public class BatchExample {
	public static void main(String[] args) {
		try {
		ApplicationContext context = SpringApplication.run(BatchExample.class, args);
		ImportCustController controller = context.getBean(ImportCustController.class);
		
			String sReturn  = controller.imp("C:\\Users\\63101\\Desktop\\CBS_CUSTINFO_20180611");
			System.out.println(sReturn);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
