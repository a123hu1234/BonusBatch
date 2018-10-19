<<<<<<< HEAD

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

=======
package com.huateng.batch.ItemWriter;
/*
 * 
 * pringbatch 写入总线集合
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.huateng.batch.BeanRowMapperTool.BeanRowMapper;
import com.huateng.batch.dao.TblCustInfDaoI;
import com.huateng.batch.dao.TblCustInfTempDaoI;
import com.huateng.batch.model.IntoAcctBean;
import com.huateng.batch.model.TblCustInf;
import com.huateng.batch.model.TblCustInfTmp;
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
>>>>>>> 40b4d4adb8788e3f95106901134864cf7924b2a5
