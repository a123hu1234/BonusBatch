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
import com.huateng.batch.dao.TblAccountInfDaoI;
import com.huateng.batch.dao.TblAccountInfTempDaoI;
import com.huateng.batch.model.IntoAcctBean;
import com.huateng.batch.model.TblAccountInf;
import com.huateng.batch.model.TblAccountInfTmp;
import com.huateng.batch.model.TblAccountInf;
import com.huateng.batch.model.TblAccountInfTmp;

@Component
public class AccountItemWriter implements ItemWriter<TblAccountInf>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	BeanRowMapper<TblAccountInfTmp> tblAccountInfTmpMapper = new BeanRowMapper<TblAccountInfTmp>(TblAccountInfTmp.class);
	@Autowired
	TblAccountInfTempDaoI tblAccountInfTempDaoI;
	
	@Autowired
	TblAccountInfDaoI tblAccountInfDaoI;
	
	
	@Override
	public void write(List<? extends TblAccountInf> items) throws Exception {
		
		tblAccountInfDaoI.delete(items);
		tblAccountInfDaoI.save(items);
	}

	public void afterPropertiesSet() throws Exception {
		//	Assert.notNull(dataSource, "dataSource limit must be set");
		//Assert.notNull(sql, "sql limit must be set");
		}
	


}
