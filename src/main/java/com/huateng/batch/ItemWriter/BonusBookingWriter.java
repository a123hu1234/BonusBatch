package com.huateng.batch.ItemWriter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huateng.batch.dao.BonusPlanDetailDao;
import com.huateng.batch.dao.TblBonusDetailDao;
import com.huateng.batch.dao.TblBonusPlanDao;
import com.huateng.batch.dao.TblTxnDetailDao;
import com.huateng.batch.model.IntoAcctBean;
import com.huateng.batch.model.TblBonusAccItf;
import com.huateng.batch.model.TblBonusDetail;
import com.huateng.batch.model.TblBonusPlan;
import com.huateng.batch.model.TblBonusPlanDetail;
import com.huateng.batch.model.TblTxnDetail;
import com.huateng.util.SSNUtil;

@Component
public class BonusBookingWriter implements ItemWriter<TblBonusAccItf>{
	
	private Logger logger = LoggerFactory.getLogger(BonusBookingWriter.class);
	
	@Autowired
	private TblBonusPlanDao tblBonusPlanDao;
	@Autowired
	private BonusPlanDetailDao tblBonusPlanDetailDao;
	
	@Autowired
	private TblBonusDetailDao tblBonusDetailDao;
	
	@Autowired
	private TblTxnDetailDao tblTxnDetailDao;

	@Override
	public void write(List<? extends TblBonusAccItf> items) throws Exception {
		
		
		List<TblBonusPlan> planList = new ArrayList<>();
		List<TblBonusPlanDetail> planDetailList = new ArrayList<>();
		List<TblTxnDetail> txnDetailList = new ArrayList<>();
		List<TblBonusDetail> bonusDetail = new ArrayList<>();
		
		double sum = 0;
		
		
		for(TblBonusAccItf itf :items) {
			String SSN = SSNUtil.getInstance().getSSN();
			planList.add(new TblBonusPlan() {
				{
					init(itf);
				}
			});
			planDetailList.add(new TblBonusPlanDetail() {
				{
					init(itf);
				}
			});
			
			txnDetailList.add(new TblTxnDetail() {
				{
					init(itf,SSN);
				}
			});
			
			bonusDetail.add(new TblBonusDetail() {
				{
					init(itf,SSN);
				}
			});
			sum += itf.getTxnBonus();
			
		}
		
		logger.info("本次更新积分账户信息条数:" + planList.size());
		tblBonusPlanDao.updatePlan(planList);
		logger.info("本次更新积分账户有效期信息条数:" + planDetailList.size());
		tblBonusPlanDetailDao.updateDetail(planDetailList);
		logger.info("插入积分入明细记录信息条数:" + bonusDetail.size());
		tblBonusDetailDao.saveList(bonusDetail);
		logger.info("插入积分入账交易记录信息条数:" + bonusDetail.size());
		tblTxnDetailDao.saveList(txnDetailList);
		//TODO 更新活动信息 见 BonusAccItfDao 785
		
		
	}

	

}
