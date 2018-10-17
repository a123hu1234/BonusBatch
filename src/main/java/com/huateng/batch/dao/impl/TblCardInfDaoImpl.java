package com.huateng.batch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.huateng.batch.BeanRowMapperTool.BeanRowMapper;
import com.huateng.batch.dao.TblCardInfDaoI;
import com.huateng.batch.dao.TblCardInfTempDaoI;
import com.huateng.batch.model.TblCardInf;
import com.huateng.batch.model.TblCardInfTmp;

@Repository
public class TblCardInfDaoImpl implements TblCardInfDaoI {
	private Logger log  = LoggerFactory.getLogger(TblCardInfDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(List<? extends TblCardInf> items) {

		jdbcTemplate.execute("insert into " + TblCardInf.getTableClum() +" values (" + TblCardInfTmp.getBeanClum() +")", new PreparedStatementCallback<int[]>() {

			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				for(TblCardInf bean:items) {
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
	public void delete(List<? extends TblCardInf> items) {
		
		for (int i = 0; i < items.size(); i++) {
			jdbcTemplate
			.update("delete from  tbl_card_inf a  where  exists (select 1 from tbl_card_inf_tmp b  where b.card_id='"
					+ items.get(i).getCardId() + "' and  b.card_id =a.card_id) ");
		}
		
		
	}

}
