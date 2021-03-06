
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
import com.huateng.batch.dao.TblCardInfDaoI;
import com.huateng.batch.dao.TblCardInfTempDaoI;
import com.huateng.batch.model.IntoAcctBean;
import com.huateng.batch.model.TblCardInf;
import com.huateng.batch.model.TblCardInfTmp;
import com.huateng.batch.model.TblCardInf;
import com.huateng.batch.model.TblCardInfTmp;

@Component
public class CardTempItemWriter implements ItemWriter<TblCardInfTmp>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	BeanRowMapper<TblCardInfTmp> tblCardInfTmpMapper = new BeanRowMapper<TblCardInfTmp>(TblCardInfTmp.class);
	@Autowired
	TblCardInfTempDaoI tblCardInfTempDaoI;
	
	@Override
	public void write(List<? extends TblCardInfTmp> items) throws Exception {
		
		tblCardInfTempDaoI.save(items);
	}

	public void afterPropertiesSet() throws Exception {
		//	Assert.notNull(dataSource, "dataSource limit must be set");
		//Assert.notNull(sql, "sql limit must be set");
		}
	


}

