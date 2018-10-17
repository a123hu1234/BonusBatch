package com.huateng.batch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

import com.huateng.batch.dao.TblBonusDetailDao;
import com.huateng.batch.model.TblBonusDetail;

@Component
public class TblBonusDetailDaoImpl implements TblBonusDetailDao{
	
	@Autowired
	private JdbcTemplate jdbcTempLate;

	@Override
	public int[] saveList(List<? extends TblBonusDetail> list) {
		String sql = " insert into " + TblBonusDetail.getTableClum() +" values (" + TblBonusDetail.getBeanClum() + ")";
		int[] sReturn = jdbcTempLate.execute(sql, new PreparedStatementCallback<int[]>() {

			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				for(TblBonusDetail detail :list) {
					ps.setString(1,detail.getMerchants());
					ps.setDouble(2,detail.getTxnBonus());
					ps.setDouble(3,detail.getValidBonus());
					ps.setDouble(4,detail.getBpValidBonus());
					ps.setDouble(5,detail.getTxnAmtOra());
					ps.setDouble(6,detail.getTxnCntOra());
					ps.setDouble(7,detail.getExtCoulmn4());
					ps.setString(8,detail.getCustId());
					ps.setString(9,detail.getAcctId());
					ps.setString(10,detail.getUsageKey());
					ps.setString(11,detail.getTxnType());
					ps.setString(12,detail.getTxnCode());
					ps.setString(13,detail.getBonusCdFlag());
					ps.setString(14,detail.getBonusSsn());
					ps.setString(15,detail.getBpPlanType());
					ps.setString(16,detail.getValidDate());
					ps.setString(17,detail.getActivityId());
					ps.setString(18,detail.getRuleId());
					ps.setString(19,detail.getBonusSsnOra());
					ps.setString(20,detail.getTxnCodeOra());
					ps.setString(21,detail.getTxnDescOra());
					ps.setString(22,detail.getTxnDateOra());
					ps.setString(23,detail.getTxnDate());
					ps.setString(24,detail.getTxnTime());
					ps.setString(25,detail.getCreateDate());
					ps.setString(26,detail.getCreateTime());
					ps.setString(27,detail.getStlmDate());
					ps.setString(28,detail.getActivityNm());
					ps.setString(29,detail.getRuleNm());
					ps.setString(30,detail.getDetailDesc());
					ps.setString(31,detail.getChannelNo());
					ps.setString(32,detail.getExtCoulmn1());
					ps.setString(33,detail.getExtCoulmn2());
					ps.setString(34,detail.getExtCoulmn3());
					ps.setString(35,detail.getReturnFlag());
					ps.addBatch();
				}
				return ps.executeBatch();
			}
			
		});
		return sReturn;
	}

}
