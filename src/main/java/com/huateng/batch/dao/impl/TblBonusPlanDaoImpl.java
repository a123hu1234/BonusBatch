package com.huateng.batch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

import com.huateng.batch.dao.TblBonusPlanDao;
import com.huateng.batch.model.TblBonusPlan;

/***
 * 
 * @author 插入新的积分账户信息
 *
 */
@Component
public class TblBonusPlanDaoImpl implements TblBonusPlanDao {
	private Logger logger = LoggerFactory.getLogger(TblBonusPlanDaoImpl.class);

	@Autowired
	private JdbcTemplate template;

	@Override
	public int[] insertPlan(List<? extends TblBonusPlan> list) {
		String sql = "insert into " + TblBonusPlan.getTableClum() + " values(" + TblBonusPlan.getBeanClum() + ")";
		
		logger.info("开始执行sql:" + sql);
		int[] sReturn = template.execute(sql,
				new PreparedStatementCallback<int[]>() {

					@Override
					public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						for (TblBonusPlan plan : list) {

							ps.setString(1, plan.getUsageKey());
							ps.setString(2, plan.getCustId());
							ps.setString(3, plan.getAcctId());
							ps.setString(4, plan.getLockStatus());
							ps.setString(5, plan.getBpPlanType());
							ps.setDouble(6, plan.getTotalBonus());
							ps.setDouble(7, plan.getValidBonus());
							ps.setDouble(8, plan.getApplyBonus());
							ps.setDouble(9, plan.getExpireBonus());
							ps.setString(10, plan.getCreateOper());
							ps.setString(11, plan.getCreateDate());
							ps.setString(12, plan.getCreateTime());
							ps.setString(13, plan.getModifyOper());
							ps.setString(14, plan.getModifyDate());
							ps.setString(15, plan.getModifyTime());
							ps.setString(16, plan.getExtCoulmn1());
							ps.setString(17, plan.getExtCoulmn2());
							ps.setString(18, plan.getExtCoulmn3());
							ps.setDouble(19, plan.getExtCoulmn4());
							ps.addBatch();
						}
						return ps.executeBatch();
					}

				});
		
		return sReturn;
	}
	
	public int[] updatePlan(List<? extends TblBonusPlan> list) {
		
		String sql ="UPDATE TBL_BONUS_PLAN set "//
				+ " TOTAL_BONUS = TOTAL_BONUS + :totalBonus,"//
				+ " VALID_BONUS = VALID_BONUS + :validBonus,"//
				+ " MODIFY_OPER = :modifyOper,"//
				+ " MODIFY_DATE = :modifyDate,"//
				+ " MODIFY_TIME = :modifyTime"//
				+ " WHERE USAGE_KEY = :usageKey"//
				+ " and CUST_ID = :custId"//
				+ " and ACCT_ID = :acctId"//
				+ " and BP_PLAN_TYPE= :bpPlanType";//
		
		logger.info("开始执行sql:" + sql);
		
		int[] sReturn = template.execute(sql,new PreparedStatementCallback<int[]>() {

			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				for(TblBonusPlan plan :list) {
					ps.setDouble(1, plan.getTotalBonus());
					ps.setDouble(2, plan.getValidBonus());
					ps.setString(3, plan.getModifyOper());
					ps.setString(4, plan.getModifyDate());
					ps.setString(5, plan.getModifyTime());
					ps.setString(6, plan.getUsageKey());
					ps.setString(7, plan.getCustId());
					ps.setString(8, plan.getAcctId());
					ps.setString(9, plan.getBpPlanType());
					
					ps.addBatch();
				}
				
				return ps.executeBatch();
			}
			
		});
		return sReturn;
	}

}
