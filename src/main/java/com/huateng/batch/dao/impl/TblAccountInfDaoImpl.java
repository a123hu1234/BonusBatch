
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
import com.huateng.batch.dao.TblAccountInfDaoI;
import com.huateng.batch.model.TblAccountInf;
import com.huateng.batch.model.TblAccountInfTmp;

@Repository
public class TblAccountInfDaoImpl implements TblAccountInfDaoI {
	private Logger log  = LoggerFactory.getLogger(TblAccountInfDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(TblAccountInf item) {

		BeanRowMapper<TblAccountInf> BeanPropertyRowMapper = new BeanRowMapper<TblAccountInf>(TblAccountInf.class);
		String sql = BeanPropertyRowMapper.getInsertSql(TblAccountInf.class);
		try {
			Map map = BeanPropertyRowMapper.getSortMap(item);

			List list = BeanPropertyRowMapper.getMappedProperties();

			jdbcTemplate.update(sql, new PreparedStatementSetter() {
				public void setValues(PreparedStatement stmt) throws SQLException {
					// 映射数据库字段

					System.out.println(BeanPropertyRowMapper.getMappedFields());
					System.out.println(BeanPropertyRowMapper.getMappedProperties());
					for (int i = 0; i < list.size(); i++) {
						stmt.setString(i + 1, map.get(list.get(i)) != null ? map.get(list.get(i)).toString() : null);
					}

				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
	}

	public void delete(TblAccountInf bean) {

	}

	public void save(TblAccountInfTmp item) {
		// TODO Auto-generated method stub
		
	}

	public void delete(TblAccountInfTmp bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(List<? extends TblAccountInf> items) {

		jdbcTemplate.execute("insert into " + TblAccountInf.getTableClum() +" values (" + TblAccountInfTmp.getBeanClum() +")", new PreparedStatementCallback<int[]>() {

			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
		String sReturn = ":custId,:custName,:usageKey,:openBrh,:custType,:custIdType,:custIdNum,:custSex,:hunyzk,"
				+ ":xuelii,:custBirthday,:openDate,:closeDate,:custMobile,:custAddr,:custLevel,:custBonusStatus,:modifyDate,:modifyTime,:"
				+ "extCoulmn1,:extCoulmn2,:extCoulmn3,:extCoulmn4,:"
				+ "familyAddr,:familyAddrMobile,:officeAddr,:"
				+ "officeAddrMobile,:certAddr,:certAddrMobile,:"
				+ "chanlNo,:relatType,:renzDate";
				for(TblAccountInf bean:items) {
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
	public void delete(List<? extends TblAccountInf> items) {
		
		for (int i = 0; i < items.size(); i++) {
			jdbcTemplate
			.update("delete from  tbl_cust_inf a  where  exists (select 1 from tbl_cust_inf_tmp b  where b.cust_id='"
					+ items.get(i).getAcctId() + "' and  b.cust_id =a.cust_id) ");
		}
		
		
	}

}

