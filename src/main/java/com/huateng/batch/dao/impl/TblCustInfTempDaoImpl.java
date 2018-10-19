<<<<<<< HEAD

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

import com.huateng.batch.dao.TblCustInfTempDaoI;
import com.huateng.batch.model.TblCustInf;
import com.huateng.batch.model.TblCustInfTmp;

@Repository
public class TblCustInfTempDaoImpl implements TblCustInfTempDaoI {
	private Logger log  = LoggerFactory.getLogger(TblCustInfTempDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;



	public void delete(TblCustInf bean) {
		jdbcTemplate
				.update("delete from  tbl_cust_inf a  where  exists (select 1 from tbl_cust_inf_tmp b  where b.cust_id="
						+ bean.getCustId() + " and  b.cust_id =a.cust_id) ");

	}

	@Override
	public void save(List<? extends TblCustInfTmp> items) {
		jdbcTemplate.execute("insert into " + TblCustInfTmp.getTableClum() +" values (" + TblCustInfTmp.getBeanClum() +")", new PreparedStatementCallback<int[]>() {

			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						for (TblCustInfTmp bean : items) {
							ps.setString(1, bean.getCustId());
							ps.setString(2, bean.getCustName());
							ps.setString(3, bean.getUsageKey());
							ps.setString(4, bean.getOpenBrh());
							ps.setString(5, bean.getCustType());
							ps.setString(6, bean.getCustIdType());
							ps.setString(7, bean.getCustIdNum());
							ps.setString(8, bean.getCustSex());
							ps.setString(9, bean.getHunyzk());
							ps.setString(10, bean.getXuelii());
							ps.setString(11, bean.getCustBirthday());
							ps.setString(12, bean.getOpenDate());
							ps.setString(13, bean.getCloseDate());
							ps.setString(14, bean.getCustMobile());
							ps.setString(15, bean.getCustAddr());
							ps.setString(16, bean.getCustLevel());
							ps.setString(17, bean.getCustBonusStatus());
							ps.setString(18, bean.getModifyDate());
							ps.setString(19, bean.getModifyTime());
							ps.setString(20, bean.getExtCoulmn1());
							ps.setString(21, bean.getExtCoulmn2());
							ps.setString(22, bean.getExtCoulmn3());
							ps.setString(23, bean.getExtCoulmn4());
							ps.setString(24, bean.getFamilyAddr());
							ps.setString(25, bean.getFamilyAddrMobile());
							ps.setString(26, bean.getOfficeAddr());
							ps.setString(27, bean.getOfficeAddrMobile());
							ps.setString(28, bean.getCertAddr());
							ps.setString(29, bean.getCertAddrMobile());
							ps.setString(30, bean.getChanlNo());
							ps.setString(31, bean.getRelatType());
							ps.setString(32, bean.getRenzDate());
							ps.addBatch();
						}

						return ps.executeBatch();
					}
		});
		
	}

	@Override
	public void delete(List<? extends TblCustInfTmp> items) {
		// TODO Auto-generated method stub
		
	}


	

}

=======
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
import com.huateng.batch.dao.TblCustInfDaoI;
import com.huateng.batch.dao.TblCustInfTempDaoI;
import com.huateng.batch.model.BonusBean;
import com.huateng.batch.model.TblBonusAccItf;
import com.huateng.batch.model.TblCustInf;
import com.huateng.batch.model.TblCustInfTmp;

@Repository
public class TblCustInfTempDaoImpl implements TblCustInfTempDaoI {
	private Logger log  = LoggerFactory.getLogger(TblCustInfTempDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;



	public void delete(TblCustInf bean) {
		jdbcTemplate
				.update("delete from  tbl_cust_inf a  where  exists (select 1 from tbl_cust_inf_tmp b  where b.cust_id="
						+ bean.getCustId() + " and  b.cust_id =a.cust_id) ");

	}

	@Override
	public void save(List<? extends TblCustInfTmp> items) {
		jdbcTemplate.execute("insert into " + TblCustInfTmp.getTableClum() +" values (" + TblCustInfTmp.getBeanClum() +")", new PreparedStatementCallback<int[]>() {

			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						for (TblCustInfTmp bean : items) {
							ps.setString(1, bean.getCustId());
							ps.setString(2, bean.getCustName());
							ps.setString(3, bean.getUsageKey());
							ps.setString(4, bean.getOpenBrh());
							ps.setString(5, bean.getCustType());
							ps.setString(6, bean.getCustIdType());
							ps.setString(7, bean.getCustIdNum());
							ps.setString(8, bean.getCustSex());
							ps.setString(9, bean.getHunyzk());
							ps.setString(10, bean.getXuelii());
							ps.setString(11, bean.getCustBirthday());
							ps.setString(12, bean.getOpenDate());
							ps.setString(13, bean.getCloseDate());
							ps.setString(14, bean.getCustMobile());
							ps.setString(15, bean.getCustAddr());
							ps.setString(16, bean.getCustLevel());
							ps.setString(17, bean.getCustBonusStatus());
							ps.setString(18, bean.getModifyDate());
							ps.setString(19, bean.getModifyTime());
							ps.setString(20, bean.getExtCoulmn1());
							ps.setString(21, bean.getExtCoulmn2());
							ps.setString(22, bean.getExtCoulmn3());
							ps.setString(23, bean.getExtCoulmn4());
							ps.setString(24, bean.getFamilyAddr());
							ps.setString(25, bean.getFamilyAddrMobile());
							ps.setString(26, bean.getOfficeAddr());
							ps.setString(27, bean.getOfficeAddrMobile());
							ps.setString(28, bean.getCertAddr());
							ps.setString(29, bean.getCertAddrMobile());
							ps.setString(30, bean.getChanlNo());
							ps.setString(31, bean.getRelatType());
							ps.setString(32, bean.getRenzDate());
							ps.addBatch();
						}

						return ps.executeBatch();
					}
		});
		
	}

	@Override
	public void delete(List<? extends TblCustInfTmp> items) {
		// TODO Auto-generated method stub
		
	}


	

}
>>>>>>> 40b4d4adb8788e3f95106901134864cf7924b2a5
