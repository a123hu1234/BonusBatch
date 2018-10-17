package com.huateng.batch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

import com.huateng.batch.dao.TblTxnDetailDao;
import com.huateng.batch.model.TblBonusDetail;
import com.huateng.batch.model.TblTxnDetail;

@Component
public class TblTxnDetailDaoImpl implements TblTxnDetailDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public int[] saveList(List<TblTxnDetail> txnDetailList) {
		String sql = " insert into " + TblTxnDetail.getTableClum() + " values (" + TblTxnDetail.getBeanClum() + ")";
		int[] sReturn = template.execute(sql, new PreparedStatementCallback<int[]>() {

			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				for (TblTxnDetail detail : txnDetailList) {

					ps.setString(1, detail.getUsageKey());
					ps.setString(2, detail.getAcqSsn());
					ps.setString(3, detail.getBonusSsn());
					ps.setString(4, detail.getKeyReversal());
					ps.setString(5, detail.getChnlNo());
					ps.setString(6, detail.getTxnDate());
					ps.setString(7, detail.getTxnTime());
					ps.setString(8, detail.getTxnType());
					ps.setString(9, detail.getTxnCode());
					ps.setString(10, detail.getTxnDesc());
					ps.setString(11, detail.getTxnExtInfo());
					ps.setString(12, detail.getCustId());
					ps.setString(13, detail.getAcctId());
					ps.setString(14, detail.getBpPlanType());
					ps.setString(15, detail.getCustIdRef());
					ps.setString(16, detail.getAcctIdRef());
					ps.setString(17, detail.getBpPlanTypeRef());
					ps.setDouble(18, detail.getTxnBonus());
					ps.setString(19, detail.getBonusCdFlag());
					ps.setString(20, detail.getTxnMchtNo());
					ps.setString(21, detail.getBrhId());
					ps.setString(22, detail.getReplyCode());
					ps.setString(23, detail.getTxnStatus());
					ps.setString(24, detail.getReplyMessage());
					ps.setString(25, detail.getCreateDate());
					ps.setString(26, detail.getCreateTime());
					ps.setString(27, detail.getOrderId());
					ps.setString(28, detail.getOprUser());
					ps.setString(29, detail.getCheckUser());
					ps.setDouble(30, detail.getExtCoulmn4());
					ps.setString(31, detail.getExtCoulmn3());
					ps.setString(32, detail.getExtCoulmn2());
					ps.setString(33, detail.getExtCoulmn1());
					ps.setString(34, detail.getOraTxnDate());
					ps.setString(35, detail.getReturnFlag());
					ps.setString(36, detail.getProductType());
					ps.setString(37, detail.getPeriod());
					ps.setString(38, detail.getClientSource());
					
					ps.addBatch();
				}
				return ps.executeBatch();
			}

		});

		return sReturn;
	}

}
