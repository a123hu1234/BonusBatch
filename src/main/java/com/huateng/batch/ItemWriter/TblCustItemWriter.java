package com.huateng.batch.ItemWriter;
/*
 * 
 * pringbatch 写入总线集合
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.huateng.batch.dao.TblCustInfDaoI;
import com.huateng.batch.model.TblCustInf;
import com.huateng.batch.model.TblCustInfTmp;

@Component("custItemWriter") 
public class TblCustItemWriter implements ItemWriter<TblCustInfTmp> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TblCustInfDaoI TblCustInfDaoImpl;



	public void afterPropertiesSet() throws Exception {
		//Assert.notNull(dataSource, "dataSource limit must be set");
		//Assert.notNull(sql, "sql limit must be set");
	}

	@Override
	public void write(List<? extends TblCustInfTmp> items) throws Exception {
		for(Object bean :items) {
			TblCustInfDaoImpl.delete((TblCustInf)bean);
			TblCustInfDaoImpl.save((TblCustInf)bean);
		}
		
	}

	


}
