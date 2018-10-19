
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
import com.huateng.batch.dao.TblCustInfDaoI;
import com.huateng.batch.dao.TblCustInfTempDaoI;
import com.huateng.batch.model.TblCustInf;
import com.huateng.batch.model.TblCustInfTmp;

@Component
public class CustItemWriter implements ItemWriter<TblCustInf>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	BeanRowMapper<TblCustInfTmp> tblCustInfTmpMapper = new BeanRowMapper<TblCustInfTmp>(TblCustInfTmp.class);
	@Autowired
	TblCustInfTempDaoI tblCustInfTempDaoI;
	
	@Autowired
	TblCustInfDaoI tblCustInfDaoI;
	
	
	@Override
	public void write(List<? extends TblCustInf> items) throws Exception {
		
		tblCustInfDaoI.delete(items);
		tblCustInfDaoI.save(items);
	}

	public void afterPropertiesSet() throws Exception {
		//	Assert.notNull(dataSource, "dataSource limit must be set");
		//Assert.notNull(sql, "sql limit must be set");
		}
	


}

