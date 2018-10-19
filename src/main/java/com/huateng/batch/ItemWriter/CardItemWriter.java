
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
import com.huateng.batch.dao.TblCardInfDaoI;
import com.huateng.batch.dao.TblCardInfTempDaoI;
import com.huateng.batch.model.TblCardInf;
import com.huateng.batch.model.TblCardInfTmp;

@Component
public class CardItemWriter implements ItemWriter<TblCardInf>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	BeanRowMapper<TblCardInfTmp> tblCardInfTmpMapper = new BeanRowMapper<TblCardInfTmp>(TblCardInfTmp.class);
	@Autowired
	TblCardInfTempDaoI tblCardInfTempDaoI;
	
	@Autowired
	TblCardInfDaoI tblCardInfDaoI;
	
	
	@Override
	public void write(List<? extends TblCardInf> items) throws Exception {
		
		tblCardInfDaoI.delete(items);
		tblCardInfDaoI.save(items);
	}

	public void afterPropertiesSet() throws Exception {
		//	Assert.notNull(dataSource, "dataSource limit must be set");
		//Assert.notNull(sql, "sql limit must be set");
		}
	


}

