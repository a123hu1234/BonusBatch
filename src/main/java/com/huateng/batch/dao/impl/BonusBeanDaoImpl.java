package com.huateng.batch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

import com.huateng.batch.dao.BonusBeanDao;
import com.huateng.batch.model.BonusBean;
import com.huateng.batch.model.TblBonusAccItf;

/***
 * 将计算积分后的待入账数据入库
 * @author 11299
 *
 */
@Component
public class BonusBeanDaoImpl implements BonusBeanDao{
	@Autowired
	private JdbcTemplate template;

	@Override
	public void save(List<? extends BonusBean> list) {
		template.execute("insert into " + TblBonusAccItf.getTableClum() +" values (" + TblBonusAccItf.getBeanClum() +")", new PreparedStatementCallback<int[]>() {

			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				for(BonusBean bean:list) {
					for(TblBonusAccItf inf : bean.getTblBonusAccInfList()) {
					//	ps.setString(0, inf.getTaskId());
						ps.setLong(1, inf.getTaskDateSeq());
						ps.setString(2, inf.getTaskDate());
						ps.setString(3, inf.getUsageKey());
						ps.setString(4, inf.getCustId());
						ps.setString(5, inf.getAcctId());
						ps.setString(6, inf.getAcctType());
						ps.setString(7, inf.getActivityId());
						ps.setString(8, inf.getRuleId());
						ps.setString(9, inf.getBonusSsnOra());
						ps.setString(10, inf.getTxnCodeOra());
						ps.setString(11, inf.getTxnDescOra());
						ps.setDouble(12, inf.getTxnAmtOra());
						ps.setDouble(13, inf.getTxnCntOra());
						ps.setString(14, inf.getTxnDateOra());
						ps.setDouble(15, inf.getTxnBonus());
						ps.setString(16, inf.getBpPlanType());
						ps.setString(17, inf.getValidDate());
						ps.setString(18, inf.getFlag());
						ps.setString(19, inf.getExtCoulmn1());
						ps.setString(20, inf.getExtCoulmn2());
						ps.setString(21, inf.getExtCoulmn3());
						ps.setString(22, "");
						ps.setString(23, "");
						ps.setString(24, "");
						ps.setString(25, inf.getTxnTime());
						ps.setString(26, inf.getFirstFlag());
						ps.setString(27, inf.getProductType());
						ps.setString(28, inf.getPeriod());
						ps.setString(29, inf.getClientSource());
						ps.setString(30, inf.getTaskId());
						ps.addBatch();
					}
					
				}
				
				return ps.executeBatch();
			}
		});
		
	}
	
}
