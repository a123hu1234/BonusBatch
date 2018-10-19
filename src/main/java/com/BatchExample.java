package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.huateng.batch.controller.ComputeBonus;
import com.huateng.batch.controller.ImportAccountController;
import com.huateng.batch.controller.ImportCardController;
import com.huateng.batch.controller.ImportCustController;


@SpringBootApplication
public class BatchExample {
	public static void main(String[] args) {
		try {
		ApplicationContext context = SpringApplication.run(BatchExample.class, args);

		//ImportCustController importCustController = context.getBean(ImportCustController.class);
		//ImportCardController importCardController = context.getBean(ImportCardController.class);
		//ImportAccountController importAccountController = context.getBean(ImportAccountController.class);
		//DailyController dailyController = context.getBean(DailyController.class);
		ComputeBonus  controller = context.getBean(ComputeBonus.class);
		
		//IntoAcctController controller = context.getBean(IntoAcctController.class);
			//String custsReturn  = controller.imp("C:\\Users\\63101\\Desktop\\CBS\\CBS_CUSTINFO_20180611");
			//String cardsReturn  = controller.imp("C:\\Users\\63101\\Desktop\\CBS\\CBS_CARDINFO_20180611");
		//	String AccountsReturn  = importAccountController.imp("C:\\Users\\63101\\Desktop\\CBS\\CBS_ACCOUNTINFO_20180612");

	//	ImportCustController importCustController = context.getBean(ImportCustController.class);
	//	ImportCardController importCardController = context.getBean(ImportCardController.class);
	//	ImportAccountController importAccountController = context.getBean(ImportAccountController.class);
	//	DailyController dailyController = context.getBean(DailyController.class);
		//ComputeBonus  controller = context.getBean(ComputeBonus.class);
		
		////IntoAcctController controller = context.getBean(IntoAcctController.class);
		//	String custsReturn  = importCustController.imp("C:\\Users\\63101\\Desktop\\CBS\\CBS_CUSTINFO_20180611");
	//		String cardsReturn  = importCardController.imp("C:\\Users\\63101\\Desktop\\CBS\\CBS_CARDINFO_20180611");
	//		String AccountsReturn  = importAccountController.imp("C:\\Users\\63101\\Desktop\\CBS\\CBS_ACCOUNTINFO_20180611");

		//ImportCustController importCustController = context.getBean(ImportCustController.class);
		//ImportCardController importCardController = context.getBean(ImportCardController.class);
		//ImportAccountController importAccountController = context.getBean(ImportAccountController.class);
	//	DailyController dailyController = context.getBean(DailyController.class);
	//	ComputeBonus  controller = context.getBean(ComputeBonus.class);
		
		//IntoAcctController controller = context.getBean(IntoAcctController.class);
		//	String custsReturn  = importCustController.imp("C:\\Users\\63101\\Desktop\\CBS\\CBS_CUSTINFO_20180611");
		//	String cardsReturn  = importCardController.imp("C:\\Users\\63101\\Desktop\\CBS\\CBS_CARDINFO_20180611");
		//	String AccountsReturn  = importAccountController.imp("C:\\Users\\63101\\Desktop\\CBS\\CBS_ACCOUNTINFO_20180611");

			//String sReturn = dailyController.imp("C:\\Users\\11299\\Desktop\\TBL_TXN_ORA_DAILY");
			String sReturn = controller.imp("C:\\Users\\11299\\Desktop\\TBL_TXN_ORA_DAILY");
			//System.out.println(sReturn);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
