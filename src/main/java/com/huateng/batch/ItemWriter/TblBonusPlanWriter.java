package com.huateng.batch.ItemWriter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huateng.batch.dao.TblBonusPlanDao;
import com.huateng.batch.model.TblBonusPlan;

/***
 * 
 * @author 11299
 *
 */
@Component
public class TblBonusPlanWriter implements ItemWriter<TblBonusPlan>{
	Logger logger = LoggerFactory.getLogger(IntoAcctWriter.class);
	
	@Autowired
	private TblBonusPlanDao dao;
	
	@Override
	public void write(List<? extends TblBonusPlan> items) throws Exception {
		
		logger.info("本次执行插入积分账户的数据条数:" + items.size());
		if(!items.isEmpty()) {
			int[] sReturn = dao.insertPlan(items);
			
			
		}else {
			logger.info("本次执行插入积分账户数据条数为0");
		}
	}

}
