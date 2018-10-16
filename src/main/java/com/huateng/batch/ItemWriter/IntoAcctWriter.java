package com.huateng.batch.ItemWriter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huateng.batch.dao.TblBonusPlanDao;
import com.huateng.batch.model.IntoAcctBean;
import com.huateng.batch.model.TblBonusPlan;

@Component
public class IntoAcctWriter implements ItemWriter<IntoAcctBean>{
	Logger logger = LoggerFactory.getLogger(IntoAcctWriter.class);
	
	@Autowired
	private TblBonusPlanDao dao;
	
	@Override
	public void write(List<? extends IntoAcctBean> items) throws Exception {
		List<TblBonusPlan> list = new ArrayList<>();
		for(IntoAcctBean bean : items) {
			list.add(bean.getPlan());
		}
		logger.info("本次执行插入积分账户的数据条数:" + list.size());
		if(!list.isEmpty()) {
			int[] sReturn = dao.insertPlan(list);
			
			
		}else {
			logger.info("本次执行插入积分账户数据条数为0");
		}
	}

}
