package com.huateng.batch.ItemWriter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huateng.batch.dao.BonusPlanDetailDao;
import com.huateng.batch.model.IntoAcctBean;
import com.huateng.batch.model.TblBonusPlanDetail;

@Component
public class PlanDetailWriter implements ItemWriter<IntoAcctBean>{
	Logger logger = LoggerFactory.getLogger(IntoAcctWriter.class);
	
	@Autowired
	private BonusPlanDetailDao dao;
	
	@Override
	public void write(List<? extends IntoAcctBean> items) throws Exception {
		List<TblBonusPlanDetail> list = new ArrayList<>();
		for(IntoAcctBean bean : items) {
			list.add(bean.getDetail());
		}
		logger.info("本次执行插入积分账户有效期表的数据条数:" + list.size());
		if(!list.isEmpty()) {
			dao.saveList(list);
			  
			
		}else {
			logger.info("本次执行插入积分账户数据条数为0");
		}
	}

}
