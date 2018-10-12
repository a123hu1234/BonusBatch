package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.huateng.batch.controller.DemoController;
import com.huateng.batch.controller.ImportAccountController;
import com.huateng.batch.controller.ImportCardController;
import com.huateng.batch.controller.ImportCustController;

@SpringBootApplication
public class BatchExample {
	public static void main(String[] args) {
		try {
		ApplicationContext context = SpringApplication.run(BatchExample.class, args);
		ImportCustController importCustController = context.getBean(ImportCustController.class);
		ImportCardController importCardController = context.getBean(ImportCardController.class);
		ImportAccountController importAccountController = context.getBean(ImportAccountController.class);
		
			//String custsReturn  = controller.imp("C:\\Users\\63101\\Desktop\\CBS\\CBS_CUSTINFO_20180611");
			//String cardsReturn  = controller.imp("C:\\Users\\63101\\Desktop\\CBS\\CBS_CARDINFO_20180611");
			String AccountsReturn  = importAccountController.imp("C:\\Users\\63101\\Desktop\\CBS\\CBS_ACCOUNTINFO_20180612");
			//System.out.println(sReturn);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
