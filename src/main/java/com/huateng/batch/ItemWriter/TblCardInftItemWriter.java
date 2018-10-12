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

import com.huateng.batch.BeanRowMapperTool.BeanRowMapper;
import com.huateng.batch.dao.TblCardInfDaoI;
import com.huateng.batch.model.TblCardInf;
import com.huateng.batch.model.TblCardInfTmp;

@Component("tblCardInftItemWriter") 
public class TblCardInftItemWriter implements ItemWriter<TblCardInfTmp> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TblCardInfDaoI TblCardInfDaoImpl;
	BeanRowMapper<TblCardInfTmp> tblCardInfTmpMapper = new BeanRowMapper<TblCardInfTmp>(TblCardInfTmp.class);
	




	public void afterPropertiesSet() throws Exception {
		//Assert.notNull(dataSource, "dataSource limit must be set");
		//Assert.notNull(sql, "sql limit must be set");
	}


	@Override
	public void write(List<? extends TblCardInfTmp> items) throws Exception {
		for(Object bean :items) {
			TblCardInfDaoImpl.delete((TblCardInf)bean);
			TblCardInfDaoImpl.save((TblCardInf)bean);
		}
		
	}

	


}
