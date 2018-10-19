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
import org.springframework.stereotype.Repository;

import com.huateng.batch.dao.TblAccountInfTempDaoI;
import com.huateng.batch.model.TblAccountInf;
import com.huateng.batch.model.TblAccountInfTmp;

@Repository
public class TblAccountInfTempDaoImpl implements TblAccountInfTempDaoI {
	private Logger log  = LoggerFactory.getLogger(TblAccountInfTempDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;



	public void delete(TblAccountInf bean) {
		jdbcTemplate
				.update("delete from  tbl_cust_inf a  where  exists (select 1 from tbl_cust_inf_tmp b  where b.cust_id="
						+ bean.getAcctId() + " and  b.cust_id =a.cust_id) ");

	}

	@Override
	public void save(List<? extends TblAccountInfTmp> items) {
		jdbcTemplate.execute("insert into " + TblAccountInfTmp.getTableClum() +" values (" + TblAccountInfTmp.getBeanClum() +")", new PreparedStatementCallback<int[]>() {

			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						for (TblAccountInfTmp bean : items) {
							ps.setString(1,bean.getCustId());
							ps.setString(2,bean.getAcctId());
							ps.setString(3,bean.getChangeDate());
							ps.setString(4,bean.getAcctName());
							ps.setString(5,bean.getCurrency());
							ps.setBigDecimal(6,bean.getTxnAmt());
							ps.setString(7,bean.getKmh());
							ps.setString(8,bean.getProductNo());
							ps.setString(9,bean.getPeriod());
							ps.setString(10,bean.getOpenBank());
							ps.setString(11,bean.getOpenDate());
							ps.setString(12,bean.getAcctState());
							ps.setString(13,bean.getOutsideAcct());
							ps.setString(14,bean.getCardNo());
							ps.setString(15,bean.getExtCoulmn1());
							ps.setString(16,bean.getExtCoulmn2());
							ps.setString(17,bean.getExtCoulmn3());
							ps.setString(18,bean.getExtCoulmn4());
							ps.setString(19,bean.getExtCoulmn5());
							ps.setString(20,bean.getChanlNo());
							ps.addBatch();
						}

						return ps.executeBatch();
					}
		});
		
	}

	@Override
	public void delete(List<? extends TblAccountInfTmp> items) {
		// TODO Auto-generated method stub
		
	}


	

}

