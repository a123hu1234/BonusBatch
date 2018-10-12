package com.huateng.batch.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.huateng.batch.BeanRowMapperTool.BeanRowMapper;
import com.huateng.batch.model.TblCustInf;

@Repository
public class TblCustInfDaoImpl implements TblCustInfDaoI {
	private Logger log  = LoggerFactory.getLogger(TblCustInfDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(TblCustInf item) {

		BeanRowMapper<TblCustInf> BeanPropertyRowMapper = new BeanRowMapper<TblCustInf>(TblCustInf.class);
		String sql = BeanPropertyRowMapper.getInsertSql(TblCustInf.class);
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

	@Override
	public void delete(TblCustInf bean) {
		jdbcTemplate
				.update("delete from  tbl_cust_inf a  where  exists (select 1 from tbl_cust_inf_tmp b  where b.cust_id="
						+ bean.getCustId() + " and  b.cust_id =a.cust_id) ");

	}

}
