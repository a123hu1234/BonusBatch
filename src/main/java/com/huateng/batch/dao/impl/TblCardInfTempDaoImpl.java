
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

import com.huateng.batch.dao.TblCardInfTempDaoI;
import com.huateng.batch.model.TblCardInf;
import com.huateng.batch.model.TblCardInfTmp;

@Repository
public class TblCardInfTempDaoImpl implements TblCardInfTempDaoI {
	private Logger log  = LoggerFactory.getLogger(TblCardInfTempDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;



	public void delete(TblCardInf bean) {
		jdbcTemplate
				.update("delete from  tbl_cust_inf a  where  exists (select 1 from tbl_cust_inf_tmp b  where b.cust_id="
						+ bean.getCardId() + " and  b.cust_id =a.cust_id) ");

	}

	@Override
	public void save(List<? extends TblCardInfTmp> items) {
		jdbcTemplate.execute("insert into " + TblCardInfTmp.getTableClum() +" values (" + TblCardInfTmp.getBeanClum() +")", new PreparedStatementCallback<int[]>() {

			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						for (TblCardInfTmp bean : items) {
							ps.setString(1, bean.getCustId());
							ps.setString(2, bean.getCardId());
							ps.setString(3, bean.getCardPrdName());
							ps.setString(4, bean.getCardBank());
							ps.setString(5, bean.getFakarq());
							ps.setString(6, bean.getCardType());
							ps.setString(7, bean.getUsageKey());
							ps.setString(8, bean.getAcctNo());
							ps.setString(9, bean.getCardPrd());
							ps.setString(10, bean.getKaaajz());
							ps.setString(11, bean.getKaaaxz());
							ps.setString(12, bean.getKaaazl());
							ps.setString(13, bean.getCardLevel());
							ps.setString(14, bean.getExtCoulmn1());
							ps.setString(15, bean.getExtCoulmn2());
							ps.setString(16, bean.getExtCoulmn3());
							ps.setBigDecimal(17, bean.getExtCoulmn4());
							ps.setString(18, bean.getCardSta());
							ps.setString(19, bean.getChanlNo());
							ps.setString(20, bean.getRenzDate());
							ps.addBatch();
						}

						return ps.executeBatch();
					}
		});
		
	}

	@Override
	public void delete(List<? extends TblCardInfTmp> items) {
		// TODO Auto-generated method stub
		
	}


	

}

