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

import com.huateng.batch.dao.BonusPlanDetailDao;
import com.huateng.batch.model.TblBonusPlanDetail;

@Component
public class BonusPlanDetailDaoImpl implements BonusPlanDetailDao {
	
	private Logger logger = LoggerFactory.getLogger(BonusPlanDetailDaoImpl.class);
	@Autowired
	private JdbcTemplate template;

	@Override
	public int[] saveList(List<? extends TblBonusPlanDetail> list) {
		
		String sql = "insert into " + TblBonusPlanDetail.getTableClum() + " values ("
				+ TblBonusPlanDetail.getBeanClum() + ")";
		
		logger.info(sql);

		int[] sReturn = template.execute(sql, new PreparedStatementCallback<int[]>() {

					@Override
					public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						for (TblBonusPlanDetail detail : list) {
							System.out.println("=======" + detail.getExpiredStatus() + "," + detail.getExtCoulmn4()
									+ "," + detail.getLockStatus());
							ps.setString(1, detail.getUsageKey());
							ps.setString(2, detail.getCustId());
							ps.setString(3, detail.getAcctId());
							ps.setString(4, detail.getBpPlanType());
							ps.setDouble(5, detail.getTotalBonus());
							ps.setDouble(6, detail.getValidBonus());
							ps.setDouble(7, detail.getApplyBonus());
							ps.setDouble(8, detail.getExpireBonus());
							ps.setString(9, detail.getValidDate());
							ps.setString(10, detail.getExpiredStatus());
							ps.setString(11, detail.getCreateOper());
							ps.setString(12, detail.getCreateDate());
							ps.setString(13, detail.getCreateTime());
							ps.setString(14, detail.getModifyOper());
							ps.setString(15, detail.getModifyDate());
							ps.setString(16, detail.getModifyTime());
							ps.setString(17, detail.getExtCoulmn1());
							ps.setString(18, detail.getExtCoulmn2());
							ps.setString(19, detail.getExtCoulmn3());
							ps.setDouble(20, detail.getExtCoulmn4());
							ps.setString(21, detail.getLockStatus());
							ps.addBatch();
						}
						return ps.executeBatch();
					}

				});

		return sReturn;
	}

	@Override
	public int[] updateDetail(List<TblBonusPlanDetail> planDetailList) {
		String sql = "update TBL_BONUS_PLAN_DETAIL set "//
				+ "TOTAL_BONUS = TOTAL_BONUS + :totalBonus,"//1
				+ "VALID_BONUS = VALID_BONUS + :validBonus,"//2
				+ "MODIFY_OPER = :modifyOper, "//3
				+ "MODIFY_DATE = :modifyDate, "//4
				+ "MODIFY_TIME = :modifyTime "//5
				+ "WHERE "//
				+ "USAGE_KEY = :usageKey "//6
				+ " and CUST_ID=:custId"//7
				+ " and ACCT_ID=:acctId"//8
				+ " and BP_PLAN_TYPE=:bpPlanType"//9
				+ " and VALID_DATE = :validDate";//10
		
		logger.info(sql);
		template.execute(sql, new PreparedStatementCallback<int[]>() {

			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				for(TblBonusPlanDetail detail :planDetailList) {
					
					ps.setDouble(1,detail.getTotalBonus());
					ps.setDouble(2, detail.getValidBonus());
					ps.setString(3, detail.getModifyOper());
					ps.setString(4, detail.getModifyDate());
					ps.setString(5, detail.getModifyTime());
					ps.setString(6, detail.getUsageKey());
					ps.setString(7, detail.getCustId());
					ps.setString(8, detail.getAcctId());
					ps.setString(9, detail.getBpPlanType());
					ps.setString(10, detail.getValidDate());
					
					ps.addBatch();
				}
				
				
				return ps.executeBatch();
			}

		});
		return null;
	}

}
