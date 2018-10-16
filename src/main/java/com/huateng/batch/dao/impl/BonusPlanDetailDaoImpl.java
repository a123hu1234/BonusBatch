package com.huateng.batch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

import com.huateng.batch.dao.BonusPlanDetailDao;
import com.huateng.batch.model.TblBonusPlanDetail;

@Component
public class BonusPlanDetailDaoImpl implements BonusPlanDetailDao {
	@Autowired
	private JdbcTemplate template;

	@Override
	public int[] saveList(List<TblBonusPlanDetail> list) {
		System.out.println("=======");
		int[] sReturn = template.execute("insert into " + TblBonusPlanDetail.getTableClum() + " values (" + TblBonusPlanDetail.getBeanClum() + ")",
				new PreparedStatementCallback<int[]>() {

					@Override
					public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						for (TblBonusPlanDetail detail: list) {
							System.out.println("=======" + detail.getExpiredStatus() + "," + detail.getExtCoulmn4()+"," + detail.getLockStatus());
							ps.setString(1,detail.getUsageKey());
							ps.setString(2,detail.getCustId());
							ps.setString(3,detail.getAcctId());
							ps.setString(4,detail.getBpPlanType());
							ps.setDouble(5,detail.getTotalBonus());
							ps.setDouble(6,detail.getValidBonus());
							ps.setDouble(7,detail.getApplyBonus());
							ps.setDouble(8,detail.getExpireBonus());
							ps.setString(9,detail.getValidDate());
							ps.setString(10,detail.getExpiredStatus());
							ps.setString(11,detail.getCreateOper());
							ps.setString(12,detail.getCreateDate());
							ps.setString(13,detail.getCreateTime());
							ps.setString(14,detail.getModifyOper());
							ps.setString(15,detail.getModifyDate());
							ps.setString(16,detail.getModifyTime());
							ps.setString(17,detail.getExtCoulmn1());
							ps.setString(18,detail.getExtCoulmn2());
							ps.setString(19,detail.getExtCoulmn3());
							ps.setDouble(20,detail.getExtCoulmn4());
							ps.setString(21,detail.getLockStatus());
							ps.addBatch();
						}
						return ps.executeBatch();
					}

				});
		
		return sReturn;
	}

}
