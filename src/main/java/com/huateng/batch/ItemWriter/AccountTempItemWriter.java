
package com.huateng.batch.ItemWriter;
/*
 * 
 * pringbatch 写入总线集合
 */

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.huateng.batch.BeanRowMapperTool.BeanRowMapper;
import com.huateng.batch.dao.TblAccountInfTempDaoI;
import com.huateng.batch.model.TblAccountInfTmp;

@Component
public class AccountTempItemWriter implements ItemWriter<TblAccountInfTmp>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	BeanRowMapper<TblAccountInfTmp> tblAccountInfTmpMapper = new BeanRowMapper<TblAccountInfTmp>(TblAccountInfTmp.class);
	@Autowired
	TblAccountInfTempDaoI tblAccountInfTempDaoI;
	
	@Override
	public void write(List<? extends TblAccountInfTmp> items) throws Exception {
		
		tblAccountInfTempDaoI.save(items);
	}

	public void afterPropertiesSet() throws Exception {
		//	Assert.notNull(dataSource, "dataSource limit must be set");
		//Assert.notNull(sql, "sql limit must be set");
		}
	


}

